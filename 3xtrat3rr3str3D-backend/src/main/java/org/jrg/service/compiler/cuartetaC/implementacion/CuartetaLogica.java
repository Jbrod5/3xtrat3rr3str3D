package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un operador logico binario a C
public class CuartetaLogica extends CuartetaC {

    /**
     * Crear una cuarteta logica con operador explicito.
     */
    public CuartetaLogica(String operador, String arg1, String arg2, String resultado,
                           String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta logica con operador and por defecto.
     */
    public CuartetaLogica(String arg1, String arg2, String resultado,
                           String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador and
        super("&&", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir la operacion logica binaria con resultado booleano
        return ctx.ladoIzquierdo(resultado, "bool") + " = (" + ctx.crearValor(arg1).obtenerCodigoC(ctx) + " " + operador + " " + ctx.crearValor(arg2).obtenerCodigoC(ctx) + ");";
    }
}
