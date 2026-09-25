package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una etiqueta a C
public class CuartetaEtiqueta extends CuartetaC {

    /**
     * Crear una cuarteta de etiqueta con operador explicito.
     */
    public CuartetaEtiqueta(String operador, String arg1, String arg2, String resultado,
                             String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de etiqueta con operador fijo.
     */
    public CuartetaEtiqueta(String arg1, String arg2, String resultado,
                             String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador label
        super("label", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir la etiqueta con sentencia vacia
        return arg1 + ":;";
    }
}
