package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// representar el fin de funcion que gestiona el orquestador
public class CuartetaFuncEnd extends CuartetaC {

    /**
     * Crear una cuarteta de fin de funcion con operador explicito.
     */
    public CuartetaFuncEnd(String operador, String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el constructor base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de fin de funcion con operador fijo.
     */
    public CuartetaFuncEnd(String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el base con operador fijo
        super("func_end", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // marcar como no soportada porque la gestiona el orquestador
        return "// cuarteta no soportada: " + operador;
    }
}
