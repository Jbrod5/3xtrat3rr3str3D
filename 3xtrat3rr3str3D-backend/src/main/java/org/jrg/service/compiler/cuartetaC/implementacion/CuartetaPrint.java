package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una impresion a C
public class CuartetaPrint extends CuartetaC {

    /**
     * Crear una cuarteta de impresion con operador explicito.
     */
    public CuartetaPrint(String operador, String arg1, String arg2, String resultado,
                          String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el constructor base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de impresion con operador fijo.
     */
    public CuartetaPrint(String arg1, String arg2, String resultado,
                          String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el base con operador fijo
        super("print", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // ver que print toca segun el tipo
        // return ctx.traducirPrint(arg1, tipoArg1);
        // preferir el tipo declarado en C, que manda sobre lo inferido
        String tipoEfectivo = tipoArg1;
        if (arg1 != null) {
            String declarado = ctx.tiposDeclarados.get(arg1);
            if (declarado != null && declarado.isEmpty() == false) {
                tipoEfectivo = declarado;
            }
        }
        return ctx.traducirPrint(arg1, tipoEfectivo);
    }
}
