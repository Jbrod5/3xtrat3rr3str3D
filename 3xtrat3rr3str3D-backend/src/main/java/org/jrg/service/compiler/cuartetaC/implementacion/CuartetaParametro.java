package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// acumular un parametro sin generar linea de C
public class CuartetaParametro extends CuartetaC {

    /**
     * Crear una cuarteta de parametro con operador explicito.
     */
    public CuartetaParametro(String operador, String arg1, String arg2, String resultado,
                              String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de parametro con operador fijo.
     */
    public CuartetaParametro(String arg1, String arg2, String resultado,
                              String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador param
        super("param", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // guardar el valor en la lista de pendientes
        ctx.paramsPendientes.add(arg1);
        // guardar el tipo en la lista de pendientes
        ctx.tiposParamsPendientes.add(tipoArg1);
        // devolver vacio porque param no genera linea
        return "";
    }
}
