package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una reserva de memoria a C
public class CuartetaAlloc extends CuartetaC {

    /**
     * Crear una cuarteta de reserva con operador explicito.
     */
    public CuartetaAlloc(String operador, String arg1, String arg2, String resultado,
                          String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de reserva con operador fijo.
     */
    public CuartetaAlloc(String arg1, String arg2, String resultado,
                          String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador alloc
        super("alloc", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // mapear el tipo base del arreglo a C
        String tipo = ctx.mapearTipo(arg1);
        // construir la reserva con malloc y dimension protegida
        return ctx.prefijoDeclaracion(resultado, tipo + "*") + " = malloc(sizeof(" + tipo + ") * " + ctx.expresionDimension(arg2) + ");";
    }
}
