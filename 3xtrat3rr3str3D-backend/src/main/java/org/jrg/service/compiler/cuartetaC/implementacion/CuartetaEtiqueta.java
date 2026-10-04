package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;

// marcar una etiqueta con punto y coma
public class CuartetaEtiqueta extends Cuarteta {

    /**
     * Crear una etiqueta con operador explicito.
     */
    public CuartetaEtiqueta(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // marcar la etiqueta con punto y coma por si queda sola
        return getArg1() + ":;";
    }

}
