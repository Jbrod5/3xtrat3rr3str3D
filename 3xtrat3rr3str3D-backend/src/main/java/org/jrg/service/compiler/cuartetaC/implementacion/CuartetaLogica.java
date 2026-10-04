package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// operar dos booleanos con resultado booleano
public class CuartetaLogica extends Cuarteta {

    /**
     * Crear una logica con operador explicito.
     */
    public CuartetaLogica(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // omitir destinos sin nombre valido
        if (getResultado() == null || getResultado().isEmpty() || getResultado().equals("_")) {
            return "";
        }

        // declarar el destino siempre booleano
        SlotHS slot = ctx.declararSlot(getResultado(), "booleano");

        // resolver los operandos a expresiones
        String primero = ctx.expresionOperando(getArg1());
        String segundo = ctx.expresionOperando(getArg2());

        // operar con registros y guardar el booleano
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return "AX_BOOLEAN = (" + primero + " " + getOperador() + " " + segundo + ");\n    " + destino + " = AX_BOOLEAN;";

    }

}
