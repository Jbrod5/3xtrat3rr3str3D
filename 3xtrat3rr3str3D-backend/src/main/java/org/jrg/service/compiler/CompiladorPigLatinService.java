package org.jrg.service.compiler;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.jrg.analisis.pigLatin.PigLatinASTBuilder;
import org.jrg.analisis.pigLatin.cuartetas.GeneradorCuartetasPigLatin;
import org.jrg.analisis.pigLatin.semantico.AnalizadorSemanticoPigLatin;
import org.jrg.antlrBase.pigLatin.PigLatinLexer;
import org.jrg.antlrBase.pigLatin.PigLatinParser;
import org.jrg.model.ast.pigLatin.Programa;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.error.ErrorCompilacion;
import org.jrg.model.error.TipoError;
import org.jrg.model.resultado.CuartetaResultado;
import org.jrg.model.resultado.ResultadoAnalisis;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.SimboloResultado;
import org.jrg.model.semantico.Tipo;
import org.jrg.model.semantico.TipoResultado;
import org.jrg.service.error.EscuchaErroresAntlr;
import org.jrg.service.error.RecolectorErrores;

/**
 * Ejecutar el pipeline de compilacion para el lenguaje Pig Latin.
 */
public class CompiladorPigLatinService {

    /**
     * Analizar un programa Pig Latin sin contexto de proyecto.
     */
    public ResultadoAnalisis analizar(String codigoFuente) {
        return analizar(codigoFuente, null);
    }

    /**
     * Analizar un programa Pig Latin con contexto de proyecto para imports.
     */
    public ResultadoAnalisis analizar(String codigoFuente, String rutaBase) {

        // crear el recolector de errores del proceso
        RecolectorErrores recolector = new RecolectorErrores();

        // validar codigo vacio
        if (codigoFuente == null || codigoFuente.trim().isEmpty()) {
            recolector.agregar(TipoError.SINTACTICO, 1, 1, "el codigo fuente esta vacio");
            return construirResultado(recolector, "", "", "", null, null, new ArrayList<>());
        }

        // crear el lexer y adjuntar la escucha de errores lexicos
        PigLatinLexer lexer = new PigLatinLexer(CharStreams.fromString(codigoFuente));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new EscuchaErroresAntlr(recolector, TipoError.LEXICO));

        // crear el stream de tokens y el parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PigLatinParser parser = new PigLatinParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(new EscuchaErroresAntlr(recolector, TipoError.SINTACTICO));

        // intentar parsear el programa
        PigLatinParser.ProgramaContext arbolCst = null;
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
        NodoAST ast = null;
        try {
            ast = arbolCst.accept(new PigLatinASTBuilder());
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
                AnalizadorSemanticoPigLatin analizador = new AnalizadorSemanticoPigLatin(recolector);
                analizador.analizar((Programa) ast, rutaBase);
                simbolos = analizador.obtenerSimbolos();
                tipos = analizador.obtenerTipos();

                // primero agregar las cuartetas de los imports (funciones importadas)
                List<Cuarteta> cuartetasImportadas = analizador.getCuartetasImportadas();
                for (int i = 0; i < cuartetasImportadas.size(); i++) {
                    cuartetas.add(new CuartetaResultado(cuartetasImportadas.get(i)));
                }
                // luego las cuartetas del archivo principal
                // generar cuartetas a partir del ast
                GeneradorCuartetasPigLatin generadorCuartetas = new GeneradorCuartetasPigLatin();

                // registrar los campos de cada tipo importado en el generador
                List<Tipo> tiposDelAnalisis = analizador.obtenerTipos();
                for (int i = 0; i < tiposDelAnalisis.size(); i++) {
                    Tipo tipoActual = tiposDelAnalisis.get(i);
                    // omitir tipos nulos
                    if (tipoActual == null) {
                        continue;
                    }
                    List<Simbolo> campos = tipoActual.getCampos();
                    // omitir tipos sin campos
                    if (campos == null || campos.isEmpty()) {
                        continue;
                    }
                    // recolectar los nombres de los campos
                    List<String> nombresCampos = new ArrayList<>();
                    for (int j = 0; j < campos.size(); j++) {
                        Simbolo campo = campos.get(j);
                        if (campo != null) {
                            nombresCampos.add(campo.getNombre());
                        }
                    }
                    // registrar los campos en el generador
                    generadorCuartetas.registrarCamposDeStruct(tipoActual.getNombre(), nombresCampos);
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

        // return construirResultado(recolector, arbolTextual, "", "", simbolos, tipos, new ArrayList<>(), cuartetas);
        return construirResultado(recolector, arbolTextual, "", "", simbolos, tipos, new ArrayList<>(), cuartetas, codigoC);
    }

    // construir el resultado final del analisis sin cuartetas
    private ResultadoAnalisis construirResultado(RecolectorErrores recolector, String arbolTextual, String astMermaid, String codigoPigLatin, List<Simbolo> simbolos, List<Tipo> tipos, List<Object> pasosPila) {
        // return construirResultado(recolector, arbolTextual, astMermaid, codigoPigLatin, simbolos, tipos, pasosPila, null);
        return construirResultado(recolector, arbolTextual, astMermaid, codigoPigLatin, simbolos, tipos, pasosPila, null, null);
    }

    // construir el resultado final del analisis con cuartetas
    private ResultadoAnalisis construirResultado(RecolectorErrores recolector, String arbolTextual, String astMermaid, String codigoPigLatin, List<Simbolo> simbolos, List<Tipo> tipos, List<Object> pasosPila, List<CuartetaResultado> cuartetas) {
        // return construirResultado(recolector, arbolTextual, astMermaid, codigoPigLatin, simbolos, tipos, pasosPila, cuartetas, null);
        return construirResultado(recolector, arbolTextual, astMermaid, codigoPigLatin, simbolos, tipos, pasosPila, cuartetas, null);
    }

    // construir el resultado final del analisis con cuartetas y codigo C
    private ResultadoAnalisis construirResultado(RecolectorErrores recolector, String arbolTextual, String astMermaid, String codigoPigLatin, List<Simbolo> simbolos, List<Tipo> tipos, List<Object> pasosPila, List<CuartetaResultado> cuartetas, String codigoC) {

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
        // return new ResultadoAnalisis(exito, errores, arbolTextual, astMermaid, codigoPigLatin, simbolosResultado, tiposResultado, pasosPila, simbolos, tipos, cuartetas);
        return new ResultadoAnalisis(exito, errores, arbolTextual, astMermaid, codigoPigLatin, simbolosResultado, tiposResultado, pasosPila, simbolos, tipos, cuartetas, codigoC);
    }
}