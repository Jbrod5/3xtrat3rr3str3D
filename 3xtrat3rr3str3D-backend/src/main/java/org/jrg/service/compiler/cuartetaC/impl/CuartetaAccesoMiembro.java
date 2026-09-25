package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un acceso a miembro con flecha para punteros a C
public class CuartetaAccesoMiembro extends CuartetaC {

    /**
     * Crear una cuarteta de acceso a miembro con operador explicito.
     */
    public CuartetaAccesoMiembro(String operador, String arg1, String arg2, String resultado,
                                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de acceso a miembro con operador fijo.
     */
    public CuartetaAccesoMiembro(String arg1, String arg2, String resultado,
                                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador punto
        super(".", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // usar punto por defecto para structs por valor
        String acceso = ".";
        // usar flecha cuando el objeto es puntero a heap
        if (ctx.esPuntero(arg1)) {
            acceso = "->";
        }
        // resolver el tipo del miembro desde la clase del objeto
        String tipo = ctx.tipoMiembro(arg1, arg2, tipoResultado);
        // construir la lectura del miembro con su acceso
        return ctx.ladoIzquierdo(resultado, tipo) + " = " + ctx.traducirValor(arg1) + acceso + arg2 + ";";
    }
}
