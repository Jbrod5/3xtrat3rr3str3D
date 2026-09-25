package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un salto incondicional a C
public class CuartetaGoto extends CuartetaC {

    /**
     * Crear una cuarteta de salto con operador explicito.
     */
    public CuartetaGoto(String operador, String arg1, String arg2, String resultado,
                         String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de salto con operador fijo.
     */
    public CuartetaGoto(String arg1, String arg2, String resultado,
                         String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador goto
        super("goto", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir el salto hacia la etiqueta destino
        return "goto " + arg1 + ";";
    }
}
