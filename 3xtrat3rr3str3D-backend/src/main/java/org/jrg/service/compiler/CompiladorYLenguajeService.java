package org.jrg.service.compiler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.jrg.analisis.yLenguaje.YLenguajeASTBuilder;
import org.jrg.analisis.yLenguaje.cuartetas.GeneradorCuartetasY;
import org.jrg.analisis.yLenguaje.lexer.YLenguajeIndentTokenSource;
import org.jrg.analisis.yLenguaje.semantico.AnalizadorSemanticoY;
import org.jrg.antlrBase.yLenguaje.YLenguajeLexer;
import org.jrg.antlrBase.yLenguaje.YLenguajeParser;
import org.jrg.model.ast.yLenguaje.Programa;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.error.ErrorCompilacion;
import org.jrg.model.error.TipoError;
import org.jrg.model.resultado.CuartetaResultado;
import org.jrg.model.resultado.ResultadoAnalisis;
import org.jrg.model.resultado.ResultadoGcc;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.SimboloResultado;
import org.jrg.model.semantico.Tipo;
import org.jrg.model.semantico.TipoResultado;
import org.jrg.service.error.EscuchaErroresAntlr;
import org.jrg.service.error.RecolectorErrores;

/**
 * Ejecutar el pipeline de compilacion para el lenguaje Y.
 */
public class CompiladorYLenguajeService {

    /**
     * Analizar un programa Y y devolver el resultado completo.
     */
    public ResultadoAnalisis analizar(String codigoFuente) {
        // crear el recolector de errores del proceso
        RecolectorErrores recolector = new RecolectorErrores();

        // validar codigo vacio
        if (codigoFuente == null || codigoFuente.trim().isEmpty()) {
            recolector.agregar(TipoError.SINTACTICO, 1, 1, "el codigo fuente esta vacio");
            return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
        }

        // crear el lexer base y adjuntar la escucha de errores lexicos
        YLenguajeLexer lexerBase = new YLenguajeLexer(CharStreams.fromString(codigoFuente));
        lexerBase.removeErrorListeners();

        EscuchaErroresAntlr escuchaLexica = new EscuchaErroresAntlr(recolector, TipoError.LEXICO);
        lexerBase.addErrorListener(escuchaLexica);

        // envolver el lexer para insertar tokens INDENT y DEDENT
        // el TokenSource calcula la indentacion segun la columna del primer token de cada linea :D
        TokenSource fuenteConIndentacion = new YLenguajeIndentTokenSource(lexerBase);

        // crear el stream de tokens y el parser
        CommonTokenStream tokens = new CommonTokenStream(fuenteConIndentacion);
        YLenguajeParser parser = new YLenguajeParser(tokens);
        parser.removeErrorListeners();

        EscuchaErroresAntlr escuchaSintactica = new EscuchaErroresAntlr(recolector, TipoError.SINTACTICO);
        parser.addErrorListener(escuchaSintactica);

        // intentar parsear el programa
        YLenguajeParser.ProgramaContext arbolCst = null;
        try {
            arbolCst = parser.programa();
        } catch (RuntimeException e) {
            recolector.agregar(TipoError.SINTACTICO, 1, 1, "error inesperado en el parsing: " + e.getMessage());

            return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
        }

        // si hubo errores lexicos o sintacticos detener el analisis
        if (recolector.tieneErrores() || arbolCst == null) {
            return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
        }

        // construir el arbol de sintaxis abstracta
        NodoASTY ast = null;
        try {

            YLenguajeASTBuilder constructorAst = new YLenguajeASTBuilder();
            ast = arbolCst.accept(constructorAst);

        } catch (RuntimeException e) {
            recolector.agregar(TipoError.SEMANTICO, 1, 1, "error al construir el ast: " + e.getMessage());

            return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
        }

        // extraer el arbol sintactico textual generado por ANTLR
        String arbolTextual = arbolCst.toStringTree(parser);

        // ejecutar el analisis semantico sobre el ast
        List<Simbolo> simbolos = new ArrayList<>();
        List<Tipo> tipos = new ArrayList<>();
        List<CuartetaResultado> cuartetas = new ArrayList<>();

        if (ast instanceof Programa) {
            try {
                AnalizadorSemanticoY analizador = new AnalizadorSemanticoY(recolector);
                analizador.analizar((Programa) ast);
                simbolos = analizador.obtenerSimbolos();
                tipos = analizador.obtenerTipos();

                // generar cuartetas a partir del ast
                GeneradorCuartetasY generadorCuartetas = new GeneradorCuartetasY();

                // registrar los tipos de variables en el generador
                List<Simbolo> simbolosDelAnalisis = analizador.obtenerSimbolos();
                for (int i = 0; i < simbolosDelAnalisis.size(); i++) {
                    Simbolo simboloActual = simbolosDelAnalisis.get(i);

                    // omitir simbolos sin tipo
                    if (simboloActual == null || simboloActual.getTipo() == null) {
                        continue;
                    }

                    // registrar el nombre con su tipo
                    generadorCuartetas.registrarTipoVariable(simboloActual.getNombre(), simboloActual.getTipo().getNombre());
                }

                ast.accept(generadorCuartetas);
                List<Cuarteta> cuartetasCrudas = generadorCuartetas.getCuartetas();
                for (int i = 0; i < cuartetasCrudas.size(); i++) {
                    cuartetas.add(new CuartetaResultado(cuartetasCrudas.get(i)));
                }

            } catch (RuntimeException e) {
                recolector.agregar(TipoError.SEMANTICO, 1, 1, "error durante el analisis semantico: " + e.getMessage());
            }
        }

        // generar codigo C a partir de las cuartetas
        TraductorC traductorC = new TraductorC();
        String codigoC = traductorC.traducir(cuartetas);

        // agregar error que avise que el C puede ser invalido si hubo errores semanticos
        if (recolector.tieneErrores() && cuartetas.isEmpty() == false) {
            recolector.agregar(TipoError.SEMANTICO, 0, 0, "El codigo C generado puede ser invalido porque hay errores semanticos previos");
        }

        // compilar con gcc para verificar aunque haya errores semanticossssss
        ResultadoGcc resultadoGcc = null;
        if (codigoC != null && codigoC.isEmpty() == false) {
            CompiladorC compiladorC = new CompiladorC();
            resultadoGcc = compiladorC.compilar(codigoC);
        }

        return construirResultado(recolector, arbolTextual, "", "", simbolos, tipos, new ArrayList<>(), cuartetas, codigoC, resultadoGcc);
    }

    // construir el resultado final del analisis sin cuartetas
    private ResultadoAnalisis construirResultado(RecolectorErrores recolector, String arbolTextual, String astMermaid, String codigoPigLatin, List<Simbolo> simbolos, List<Tipo> tipos, List<Object> pasosPila) {
        return construirResultado(recolector, arbolTextual, astMermaid, codigoPigLatin, simbolos, tipos, pasosPila, null, null, null);
    }

    // construir el resultado final del analisis con cuartetas
    private ResultadoAnalisis construirResultado(RecolectorErrores recolector, String arbolTextual, String astMermaid, String codigoPigLatin, List<Simbolo> simbolos, List<Tipo> tipos, List<Object> pasosPila, List<CuartetaResultado> cuartetas) {
        return construirResultado(recolector, arbolTextual, astMermaid, codigoPigLatin, simbolos, tipos, pasosPila, cuartetas, null, null);
    }

    // construir el resultado final del analisis con cuartetas codigo C y gcc
    private ResultadoAnalisis construirResultado(RecolectorErrores recolector, String arbolTextual, String astMermaid, String codigoPigLatin, List<Simbolo> simbolos, List<Tipo> tipos, List<Object> pasosPila, List<CuartetaResultado> cuartetas, String codigoC, ResultadoGcc resultadoGcc) {

        List<ErrorCompilacion> errores = recolector.obtenerErrores();
        List<SimboloResultado> simbolosResultado = new ArrayList<>();

        if (simbolos != null) {
            for (int i = 0; i < simbolos.size(); i++) {
                simbolosResultado.add(new SimboloResultado(simbolos.get(i)));
            }
        }

        List<TipoResultado> tiposResultado = new ArrayList<>();
        if (tipos != null) {
            for (int i = 0; i < tipos.size(); i++) {
                tiposResultado.add(new TipoResultado(tipos.get(i)));
            }
        }

        boolean exito = errores.isEmpty();


        return new ResultadoAnalisis(exito, errores, arbolTextual, astMermaid, codigoPigLatin, simbolosResultado, tiposResultado, pasosPila, simbolos, tipos, cuartetas, codigoC, resultadoGcc);
    }
    /**
     * Analizar varios archivos Y con ambito compartido para ver hermanos.
     */
    public ResultadoAnalisis analizarConjunto(List<Path> archivos, int indicePedido) {
        // crear el recolector del proceso
        RecolectorErrores recolector = new RecolectorErrores();
        // validar la lista de archivos
        if (archivos == null || archivos.isEmpty() || indicePedido < 0 || indicePedido >= archivos.size()) {
            recolector.agregar(TipoError.SINTACTICO, 1, 1, "conjunto de archivos invalido");
            return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
        }
        // leer y parsear cada archivo a su programa
        List<Programa> programas = new ArrayList<>();
        List<String> arboles = new ArrayList<>();
        for (int i = 0; i < archivos.size(); i++) {
            programas.add(null);
            arboles.add("");
        }
        for (int i = 0; i < archivos.size(); i++) {
            String contenido = null;
            try {
                contenido = Files.readString(archivos.get(i));
            } catch (Exception e) {
                contenido = null;
            }
            // omitir archivos ilegibles, solo el pedido reporta
            if (contenido == null || contenido.trim().isEmpty()) {
                if (i == indicePedido) {
                    recolector.agregar(TipoError.SINTACTICO, 1, 1, "no se pudo leer el archivo pedido");
                    return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
                }
                continue;
            }
            // crear el lexer base del archivo
            YLenguajeLexer lexerBase = new YLenguajeLexer(CharStreams.fromString(contenido));
            lexerBase.removeErrorListeners();
            // usar recolector de paso para no mezclar errores de hermanos
            RecolectorErrores recolectorPaso = new RecolectorErrores();
            EscuchaErroresAntlr escuchaLexica = new EscuchaErroresAntlr(recolectorPaso, TipoError.LEXICO);
            lexerBase.addErrorListener(escuchaLexica);
            // envolver el lexer para insertar tokens INDENT y DEDENT
            TokenSource fuenteConIndentacion = new YLenguajeIndentTokenSource(lexerBase);
            // crear el stream de tokens y el parser
            CommonTokenStream tokens = new CommonTokenStream(fuenteConIndentacion);
            YLenguajeParser parser = new YLenguajeParser(tokens);
            parser.removeErrorListeners();
            EscuchaErroresAntlr escuchaSintactica = new EscuchaErroresAntlr(recolectorPaso, TipoError.SINTACTICO);
            parser.addErrorListener(escuchaSintactica);
            // intentar parsear el programa
            YLenguajeParser.ProgramaContext arbolCst = null;
            try {
                arbolCst = parser.programa();
            } catch (RuntimeException e) {
                arbolCst = null;
            }
            // omitir el archivo si no parseo, solo el pedido reporta
            if (arbolCst == null || recolectorPaso.tieneErrores()) {
                if (i == indicePedido) {
                    recolector.agregar(TipoError.SINTACTICO, 1, 1, "no se pudo parsear el archivo pedido");
                    return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
                }
                continue;
            }
            // construir el arbol de sintaxis abstracta
            try {
                YLenguajeASTBuilder constructorAst = new YLenguajeASTBuilder();
                NodoASTY ast = arbolCst.accept(constructorAst);
                if (ast instanceof Programa) {
                    programas.set(i, (Programa) ast);
                    arboles.set(i, arbolCst.toStringTree(parser));
                }
            } catch (RuntimeException e) {
                // omitir el archivo si su ast falla, solo el pedido reporta
                if (i == indicePedido) {
                    recolector.agregar(TipoError.SEMANTICO, 1, 1, "error al construir el ast del archivo pedido");
                    return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
                }
            }
        }
        // crear un solo analizador para compartir structs y funciones
        AnalizadorSemanticoY analizador = new AnalizadorSemanticoY(recolector);
        // iniciar el contexto una sola vez para todo el conjunto
        analizador.obtenerContexto().iniciar();
        // ordenar hermanos primero y el pedido al final para que vea todo
        List<Integer> orden = new ArrayList<>();
        for (int i = 0; i < programas.size(); i++) {
            if (i != indicePedido) {
                orden.add(i);
            }
        }
        orden.add(indicePedido);
        // contar errores antes del pedido para ubicar solo los suyos
        int antesPedido = recolector.cantidad();
        for (int k = 0; k < orden.size(); k++) {
            int i = orden.get(k);
            if (i == indicePedido) {
                antesPedido = recolector.cantidad();
            }
            if (programas.get(i) != null) {
                programas.get(i).accept(analizador);
            }
        }
        // quedarse solo con los errores del archivo pedido
        List<ErrorCompilacion> erroresPedido = new ArrayList<>();
        List<ErrorCompilacion> todos = recolector.obtenerErrores();
        for (int i = antesPedido; i < todos.size(); i++) {
            erroresPedido.add(todos.get(i));
        }
        // crear un recolector limpio solo con lo del pedido
        RecolectorErrores recolectorPedido = new RecolectorErrores(erroresPedido);
        // juntar simbolos y tipos de todo el conjunto
        List<Simbolo> simbolos = analizador.obtenerSimbolos();
        List<Tipo> tipos = analizador.obtenerTipos();
        // generar cuartetas de cada archivo en orden
        List<CuartetaResultado> cuartetas = new ArrayList<>();
        List<Simbolo> simbolosDelAnalisis = analizador.obtenerSimbolos();
        for (int k = 0; k < orden.size(); k++) {
            int i = orden.get(k);
            if (programas.get(i) == null) {
                continue;
            }
            // generar cuartetas a partir del ast
            GeneradorCuartetasY generadorCuartetas = new GeneradorCuartetasY();
            // registrar los tipos de variables en el generador
            for (int j = 0; j < simbolosDelAnalisis.size(); j++) {
                Simbolo simboloActual = simbolosDelAnalisis.get(j);
                // omitir simbolos sin tipo
                if (simboloActual == null || simboloActual.getTipo() == null) {
                    continue;
                }
                // registrar el nombre con su tipo
                generadorCuartetas.registrarTipoVariable(simboloActual.getNombre(), simboloActual.getTipo().getNombre());
            }
            programas.get(i).accept(generadorCuartetas);
            List<Cuarteta> cuartetasCrudas = generadorCuartetas.getCuartetas();
            for (int j = 0; j < cuartetasCrudas.size(); j++) {
                cuartetas.add(new CuartetaResultado(cuartetasCrudas.get(j)));
            }
        }
        // generar codigo C a partir de las cuartetas
        TraductorC traductorC = new TraductorC();
        String codigoC = traductorC.traducir(cuartetas);
        // agregar error que avise que el C puede ser invalido si hubo errores
        if (recolectorPedido.tieneErrores() && cuartetas.isEmpty() == false) {
            recolectorPedido.agregar(TipoError.SEMANTICO, 0, 0, "El codigo C generado puede ser invalido porque hay errores semanticos previos");
        }
        // compilar con gcc para verificar aunque haya errores semanticos
        ResultadoGcc resultadoGcc = null;
        if (codigoC != null && codigoC.isEmpty() == false) {
            CompiladorC compiladorC = new CompiladorC();
            resultadoGcc = compiladorC.compilar(codigoC);
        }
        return construirResultado(recolectorPedido, arboles.get(indicePedido), "", "", simbolos, tipos, new ArrayList<>(), cuartetas, codigoC, resultadoGcc);
    }
}