package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una operacion aritmetica a C
public class CuartetaAritmetica extends CuartetaC {

    /**
     * Crear una cuarteta aritmetica con operador explicito.
     */
    public CuartetaAritmetica(String operador, String arg1, String arg2, String resultado,
                               String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el constructor base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta aritmetica con operador suma por defecto.
     */
    public CuartetaAritmetica(String arg1, String arg2, String resultado,
                               String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el base con operador fijo
        super("+", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // mapear el tipo del resultado a C
        String tipo = ctx.mapearTipo(tipoResultado);
        // construir la operacion binaria con el operador propio
        return ctx.ladoIzquierdo(resultado, tipo) + " = " + ctx.crearValor(arg1).obtenerCodigoC(ctx) + " " + operador + " " + ctx.crearValor(arg2).obtenerCodigoC(ctx) + ";";
    }
}
