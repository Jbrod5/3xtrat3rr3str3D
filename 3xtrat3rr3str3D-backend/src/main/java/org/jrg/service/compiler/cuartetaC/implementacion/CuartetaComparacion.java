package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una comparacion a C
public class CuartetaComparacion extends CuartetaC {

    /**
     * Crear una cuarteta de comparacion con operador explicito.
     */
    public CuartetaComparacion(String operador, String arg1, String arg2, String resultado,
                                String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de comparacion con operador igual por defecto.
     */
    public CuartetaComparacion(String arg1, String arg2, String resultado,
                                String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador igual
        super("==", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir la comparacion binaria con resultado booleano
        return ctx.ladoIzquierdo(resultado, "bool") + " = (" + ctx.crearValor(arg1).obtenerCodigoC(ctx) + " " + operador + " " + ctx.crearValor(arg2).obtenerCodigoC(ctx) + ");";
    }
}
