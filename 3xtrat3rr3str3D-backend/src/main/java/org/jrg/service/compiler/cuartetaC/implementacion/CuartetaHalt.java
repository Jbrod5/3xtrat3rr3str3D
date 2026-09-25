package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir halt como retorno de main a C
public class CuartetaHalt extends CuartetaC {

    /**
     * Crear una cuarteta de halt con operador explicito.
     */
    public CuartetaHalt(String operador, String arg1, String arg2, String resultado,
                         String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de halt con operador fijo.
     */
    public CuartetaHalt(String arg1, String arg2, String resultado,
                         String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador halt
        super("halt", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir el retorno exitoso del programa
        return "return 0;";
    }
}
