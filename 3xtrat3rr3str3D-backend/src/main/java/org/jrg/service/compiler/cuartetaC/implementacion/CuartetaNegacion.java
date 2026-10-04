package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// negar un booleano con resultado booleano
public class CuartetaNegacion extends Cuarteta {

    /**
     * Crear una negacion con operador explicito.
     */
    public CuartetaNegacion(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // delegar al constructor de la clase base
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

        // resolver el operando a expresion
        String valor = ctx.expresionOperando(getArg1());

        // negar con registro y guardar el booleano
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return "AX_BOOLEAN = !" + valor + ";\n    " + destino + " = AX_BOOLEAN;";

    }

}
