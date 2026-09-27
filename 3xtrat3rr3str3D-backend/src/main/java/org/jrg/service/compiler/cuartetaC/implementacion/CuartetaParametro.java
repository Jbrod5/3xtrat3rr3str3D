package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// acumular un param para el siguiente call sin generar linea
public class CuartetaParametro extends CuartetaC {

    /**
     * Crear un param con operador explicito.
     */
    public CuartetaParametro(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // acumular el valor con su tipo para el call
        ctx.paramsPendientes.add(arg1);
        ctx.tiposParamsPendientes.add(tipoArg1);

        // los params no generan linea propia
        return "";

    }

}
