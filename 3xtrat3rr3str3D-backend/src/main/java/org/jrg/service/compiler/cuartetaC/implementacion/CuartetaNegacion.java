package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una negacion logica a C
public class CuartetaNegacion extends CuartetaC {

    /**
     * Crear una cuarteta de negacion con operador explicito.
     */
    public CuartetaNegacion(String operador, String arg1, String arg2, String resultado,
                             String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el constructor base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de negacion con operador fijo.
     */
    public CuartetaNegacion(String arg1, String arg2, String resultado,
                             String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el base con operador fijo
        super("!", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // construir la negacion con resultado booleano
        return ctx.ladoIzquierdo(resultado, "bool") + " = !" + ctx.crearValor(arg1).obtenerCodigoC(ctx) + ";";
    }
}
