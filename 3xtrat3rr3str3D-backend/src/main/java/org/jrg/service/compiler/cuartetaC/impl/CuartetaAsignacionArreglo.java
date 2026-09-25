package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una asignacion a arreglo a C
public class CuartetaAsignacionArreglo extends CuartetaC {

    /**
     * Crear una cuarteta de asignacion a arreglo con operador explicito.
     */
    public CuartetaAsignacionArreglo(String operador, String arg1, String arg2, String resultado,
                                      String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de asignacion a arreglo con operador fijo.
     */
    public CuartetaAsignacionArreglo(String arg1, String arg2, String resultado,
                                      String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador corchete igual
        super("[]=", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir la escritura del arreglo con indice y valor traducidos
        return ctx.accesoCampo(arg1) + "[" + ctx.traducirValor(arg2) + "] = " + ctx.traducirValor(resultado) + ";";
    }
}
