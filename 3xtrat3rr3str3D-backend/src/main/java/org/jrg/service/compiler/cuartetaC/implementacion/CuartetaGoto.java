package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;

// saltar siempre a una etiqueta
public class CuartetaGoto extends Cuarteta {

    /**
     * Crear un salto con operador explicito.
     */
    public CuartetaGoto(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // saltar a la etiqueta del primer argumento
        return "goto " + getArg1() + ";";
    }

}
