package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un menos unario con opcode propio a C
public class CuartetaUminus extends CuartetaC {

    /**
     * Crear una cuarteta de menos unario con operador explicito.
     */
    public CuartetaUminus(String operador, String arg1, String arg2, String resultado,
                           String tipoArg1, String tipoArg2, String tipoResultado) {
        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de menos unario con operador fijo.
     */
    public CuartetaUminus(String arg1, String arg2, String resultado,
                           String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el constructor base con opcode uminus
        super("uminus", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // mapear el tipo del resultado a C
        String tipoUni = ctx.mapearTipo(tipoResultado);
        // construir la negacion aritmetica del valor
        return ctx.ladoIzquierdo(resultado, tipoUni) + " = -" + ctx.crearValor(arg1).obtenerCodigoC(ctx) + ";";
    }
}
