package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un retorno de funcion a C
public class CuartetaRetorno extends CuartetaC {

    /**
     * Crear una cuarteta de retorno con operador explicito.
     */
    public CuartetaRetorno(String operador, String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de retorno con operador fijo.
     */
    public CuartetaRetorno(String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador return
        super("return", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // usar retorno con cero dentro de main sin valor
        if (arg1 == null || arg1.equals("_")) {
            if ("main".equals(ctx.funcionActual)) {
                return "return 0;";
            }
            return "return;";
        }
        // construir el retorno con el valor traducido
        return "return " + ctx.traducirValor(arg1) + ";";
    }
}
