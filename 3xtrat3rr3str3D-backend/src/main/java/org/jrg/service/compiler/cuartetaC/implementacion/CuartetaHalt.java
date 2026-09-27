package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// terminar el programa limpiando el marco
public class CuartetaHalt extends CuartetaC {

    /**
     * Crear un halt con operador explicito.
     */
    public CuartetaHalt(String operador, String arg1, String arg2, String resultado,
                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // limpiar el marco y salir con cero
        return "stackpointer = framepointer;\n    framestackpointer = framestackpointer - 1;\n    framepointer = framestack[framestackpointer];\n    return 0;";
    }

}
