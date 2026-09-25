package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// representar la definicion de struct que gestiona el orquestador
public class CuartetaStructDef extends CuartetaC {

    /**
     * Crear una cuarteta de definicion de struct con operador explicito.
     */
    public CuartetaStructDef(String operador, String arg1, String arg2, String resultado,
                              String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de definicion de struct con operador fijo.
     */
    public CuartetaStructDef(String arg1, String arg2, String resultado,
                              String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador struct_def
        super("struct_def", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
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
