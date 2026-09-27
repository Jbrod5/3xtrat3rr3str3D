package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// saltar a una etiqueta si la condicion es falsa
public class CuartetaIfFalse extends CuartetaC {

    /**
     * Crear un salto condicional con operador explicito.
     */
    public CuartetaIfFalse(String operador, String arg1, String arg2, String resultado,
                     String tipoArg1, String tipoArg2, String tipoResultado) {
        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // resolver la condicion a expresion
        String cond = ctx.expresionOperando(arg1);
        // saltar a la etiqueta si es falsa
        return "AX_BOOLEAN = " + cond + ";\n    if (!AX_BOOLEAN) goto " + arg2 + ";";
    }
}
