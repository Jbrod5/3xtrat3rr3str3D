package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un salto condicional a C
public class CuartetaIfFalse extends CuartetaC {

    /**
     * Crear una cuarteta de salto condicional con operador explicito.
     */
    public CuartetaIfFalse(String operador, String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de salto condicional con operador fijo.
     */
    public CuartetaIfFalse(String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador if_false
        super("if_false", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir el salto cuando la condicion es falsa
        return "if (!" + ctx.traducirValor(arg1) + ") goto " + arg2 + ";";
    }
}
