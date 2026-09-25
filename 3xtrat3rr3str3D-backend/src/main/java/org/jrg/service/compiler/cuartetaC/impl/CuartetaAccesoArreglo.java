package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un acceso a arreglo a C
public class CuartetaAccesoArreglo extends CuartetaC {

    /**
     * Crear una cuarteta de acceso a arreglo con operador explicito.
     */
    public CuartetaAccesoArreglo(String operador, String arg1, String arg2, String resultado,
                                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de acceso a arreglo con operador fijo.
     */
    public CuartetaAccesoArreglo(String arg1, String arg2, String resultado,
                                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador corchete
        super("=[]", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // mapear el tipo del resultado a C
        String tipo = ctx.mapearTipo(tipoResultado);
        // construir la lectura del arreglo con indice traducido
        return ctx.ladoIzquierdo(resultado, tipo) + " = " + ctx.accesoCampo(arg1) + "[" + ctx.traducirValor(arg2) + "];";
    }
}
