package org.jrg.service.compiler;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jrg.analisis.comun.AmbitoSemantico;
import org.jrg.analisis.zetariano.ZetarianoASTBuilder;
import org.jrg.analisis.zetariano.cuartetas.GeneradorCuartetasZetariano;
import org.jrg.analisis.zetariano.semantico.AnalizadorSemanticoZetariano;
import org.jrg.antlrBase.zetariano.ZetarianoLexer;
import org.jrg.antlrBase.zetariano.ZetarianoParser;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.error.ErrorCompilacion;
import org.jrg.model.error.TipoError;
import org.jrg.model.resultado.CuartetaResultado;
import org.jrg.model.resultado.ResultadoAnalisis;
import org.jrg.model.resultado.ResultadoGcc;
import org.jrg.model.semantico.Ambito;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.SimboloResultado;
import org.jrg.model.semantico.Tipo;
import org.jrg.model.semantico.TipoResultado;
import org.jrg.model.ast.zetariano.Programa;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.definicion_clase.DefClase;
import org.jrg.service.error.EscuchaErroresAntlr;
import org.jrg.service.error.RecolectorErrores;

/**
 * Ejecutar el pipeline de compilacion para el lenguaje Zetariano.
 */
public class CompiladorZetarianoService {

    /**
     * Analizar un programa Zetariano y devolver el resultado completo.
     */
    public ResultadoAnalisis analizar(String codigoFuente) {
        // delegar sin nombre de archivo
        return analizar(codigoFuente, null);
    }

    /**
     * Analizar un programa Zetariano con el nombre del archivo para validar la clase.
     */
    public ResultadoAnalisis analizar(String codigoFuente, String nombreArchivo) {
        // crear el recolector de errores del proceso
        RecolectorErrores recolector = new RecolectorErrores();

        // validar codigo vacio
        if (codigoFuente == null || codigoFuente.trim().isEmpty()) {
            recolector.agregar(TipoError.SINTACTICO, 1, 1, "el codigo fuente esta vacio");
            return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
        }

        // crear el lexer y adjuntar la escucha de errores lexicos
        ZetarianoLexer lexer = new ZetarianoLexer(CharStreams.fromString(codigoFuente));
        lexer.removeErrorListeners();
        EscuchaErroresAntlr escuchaLexica = new EscuchaErroresAntlr(recolector, TipoError.LEXICO);
        lexer.addErrorListener(escuchaLexica);

        // crear el stream de tokens y el parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ZetarianoParser parser = new ZetarianoParser(tokens);
        parser.removeErrorListeners();
        EscuchaErroresAntlr escuchaSintactica = new EscuchaErroresAntlr(recolector, TipoError.SINTACTICO);
        parser.addErrorListener(escuchaSintactica);

        // intentar parsear el programa
        ZetarianoParser.ProgramaContext arbolCst = null;
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
        NodoASTZetariano ast = null;
        try {

            ZetarianoASTBuilder constructorAst = new ZetarianoASTBuilder();
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
                AnalizadorSemanticoZetariano analizador = new AnalizadorSemanticoZetariano(recolector);
                analizador.visitarPrograma((Programa) ast);

                // recorrer el arbol de ambitos del global para recolectar los simbolos
                if (analizador.obtenerAmbitoGlobal() != null) {
                    AmbitoSemantico raiz = analizador.obtenerAmbitoGlobal();
                    colectarSimbolos(raiz, simbolos, tipos);
                }

                // validar que el nombre del archivo coincida con el nombre de la clase
                if (nombreArchivo != null && nombreArchivo.isEmpty() == false && ast instanceof Programa) {
                    Programa programa = (Programa) ast;

                    // extraer la definicion de clase si existe
                    if (programa.getDefinicionClase() instanceof DefClase) {
                        String nombreClase = ((DefClase) programa.getDefinicionClase()).getNombre();

                        // reportar error si los nombres no coinciden
                        if (nombreClase != null && nombreClase.equals(nombreArchivo) == false) {
                            recolector.agregar(TipoError.SEMANTICO, 1, 1, "el nombre del archivo '" + nombreArchivo + "' no coincide con el nombre de la clase '" + nombreClase + "'");
                        }

                    }
                }

                // generar cuartetas a partir del ast :D
                GeneradorCuartetasZetariano generadorCuartetas = new GeneradorCuartetasZetariano();

                // registrar los tipos de variables en el generador
                for (int i = 0; i < simbolos.size(); i++) {
                    Simbolo simboloActual = simbolos.get(i);

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

        // avisar que el C puede ser invalido si hubo errores semanticos
        if (recolector.tieneErrores() && cuartetas.isEmpty() == false) {
            recolector.agregar(TipoError.SEMANTICO, 0, 0, "El codigo C generado puede ser invalido porque hay errores semanticos previos");
        }

        // compilar con gcc para verificar aunque haya errores semanticos
        ResultadoGcc resultadoGcc = null;
        if (codigoC != null && codigoC.isEmpty() == false) {
            CompiladorC compiladorC = new CompiladorC();
            resultadoGcc = compiladorC.compilar(codigoC);
        }

        return construirResultado(recolector, arbolTextual, "", "", simbolos, tipos, new ArrayList<>(), cuartetas, codigoC, resultadoGcc);
    }

    // recorrer el arbol de ambitos y recolectar simbolos y tipos
    private void colectarSimbolos(AmbitoSemantico ambito, List<Simbolo> simbolos, List<Tipo> tipos) {
        if (ambito == null) {
            return;
        }

        simbolos.addAll(ambito.obtenerSimbolos());
        simbolos.addAll(ambito.obtenerTodosLosMetodos());
        tipos.addAll(ambito.obtenerTipos());

        List<AmbitoSemantico> hijos = ambito.obtenerHijos();
        for (int i = 0; i < hijos.size(); i++) {
            colectarSimbolos(hijos.get(i), simbolos, tipos);
        }

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
}