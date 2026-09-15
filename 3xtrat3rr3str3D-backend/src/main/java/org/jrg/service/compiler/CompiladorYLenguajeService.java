package org.jrg.service.compiler;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.jrg.analisis.yLenguaje.YLenguajeASTBuilder;
import org.jrg.analisis.yLenguaje.lexer.YLenguajeIndentTokenSource;
import org.jrg.analisis.yLenguaje.semantico.AnalizadorSemanticoY;
import org.jrg.antlrBase.yLenguaje.YLenguajeLexer;
import org.jrg.antlrBase.yLenguaje.YLenguajeParser;
import org.jrg.model.ast.yLenguaje.Programa;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.error.ErrorCompilacion;
import org.jrg.model.error.TipoError;
import org.jrg.model.resultado.ResultadoAnalisis;
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
        // el TokenSource calcula la indentacion segun la columna del primer token de cada linea
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

        if (ast instanceof Programa) {
            try {
                AnalizadorSemanticoY analizador = new AnalizadorSemanticoY(recolector);
                analizador.analizar((Programa) ast);
                simbolos = analizador.obtenerSimbolos();
                tipos = analizador.obtenerTipos();
            } catch (RuntimeException e) {
                recolector.agregar(TipoError.SEMANTICO, 1, 1, "error durante el analisis semantico: " + e.getMessage());
            }
        }

        return construirResultado(recolector, arbolTextual, "", "", simbolos, tipos, new ArrayList<>());
    }

    // construir el resultado final del analisis
    private ResultadoAnalisis construirResultado(RecolectorErrores recolector, String arbolTextual, String astMermaid, String codigoPigLatin, List<Simbolo> simbolos, List<Tipo> tipos, List<Object> pasosPila) {

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

        return new ResultadoAnalisis(exito, errores, arbolTextual, astMermaid, codigoPigLatin, simbolosResultado, tiposResultado, pasosPila, simbolos, tipos);
    }
}