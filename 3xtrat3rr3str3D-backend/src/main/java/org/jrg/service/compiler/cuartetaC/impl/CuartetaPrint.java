package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una impresion a C
public class CuartetaPrint extends CuartetaC {

    /**
     * Crear una cuarteta de impresion con operador explicito.
     */
    public CuartetaPrint(String operador, String arg1, String arg2, String resultado,
                          String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de impresion con operador fijo.
     */
    public CuartetaPrint(String arg1, String arg2, String resultado,
                          String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador print
        super("print", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // delegar al traductor de print segun el tipo
        return ctx.traducirPrint(arg1, tipoArg1);
    }
}
