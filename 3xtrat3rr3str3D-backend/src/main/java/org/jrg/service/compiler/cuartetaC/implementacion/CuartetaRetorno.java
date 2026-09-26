package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir un retorno de funcion a C
public class CuartetaRetorno extends CuartetaC {

    /**
     * Crear una cuarteta de retorno con operador explicito.
     */
    public CuartetaRetorno(String operador, String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // usar el constructor base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de retorno con operador fijo.
     */
    public CuartetaRetorno(String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        super("return", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // usar retorno con cero dentro de main sin valor
        if (arg1 == null || arg1.equals("_")) {
            if ("main".equals(ctx.funcionActual)) {
                return "return 0;";
            }
            return "return;";
        }
        // detectar nulos literales o guardados en temporales
        boolean esNulo = "null".equals(arg1) || "NULL".equals(arg1) || ctx.nulosConocidos.contains(arg1);
        if (esNulo) {
            // retornar cero cuando la funcion devuelve entero
            if ("int".equals(ctx.mapearTipo(ctx.tipoRetornoFuncion))) {
                return "return 0;";
            }
            // retornar vacio cuando la funcion no devuelve valor
            if ("void".equals(ctx.mapearTipo(ctx.tipoRetornoFuncion))) {
                return "return;";
            }
        }
        // construir el retorno con el valor traducido
        return "return " + ctx.crearValor(arg1).obtenerCodigoC(ctx) + ";";
    }
}
