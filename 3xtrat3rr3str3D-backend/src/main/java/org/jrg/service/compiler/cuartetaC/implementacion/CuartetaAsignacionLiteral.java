package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una asignacion de literal a C
public class CuartetaAsignacionLiteral extends CuartetaC {

    /**
     * Crear una cuarteta de asignacion de literal con operador explicito.
     */
    public CuartetaAsignacionLiteral(String operador, String arg1, String arg2, String resultado,
                                      String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de asignacion de literal con operador fijo.
     */
    public CuartetaAsignacionLiteral(String arg1, String arg2, String resultado,
                                      String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador igual
        super("=", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // mapear el tipo del resultado a C
        String tipo = ctx.mapearTipo(tipoResultado);
        // usar puntero generico cuando el literal es nulo
        if ("null".equals(arg1) || "NULL".equals(arg1)) {
            tipo = "void*";
            // marcar el temporal como nulo conocido
            if (resultado != null && resultado.equals("_") == false) {
                ctx.nulosConocidos.add(resultado);
            }
        }
        // construir la asignacion con el valor traducido
        return ctx.ladoIzquierdo(resultado, tipo) + " = " + ctx.crearValor(arg1).obtenerCodigoC(ctx) + ";";
    }
}
