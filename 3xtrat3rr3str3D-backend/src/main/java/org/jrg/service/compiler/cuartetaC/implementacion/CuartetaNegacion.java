package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// negar un booleano con resultado booleano
public class CuartetaNegacion extends CuartetaC {

    /**
     * Crear una negacion con operador explicito.
     */
    public CuartetaNegacion(String operador, String arg1, String arg2, String resultado,
                      String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // omitir destinos sin nombre valido
        if (resultado == null || resultado.isEmpty() || resultado.equals("_")) {
            return "";
        }
        // declarar el destino siempre booleano
        SlotHS slot = ctx.declararSlot(resultado, "booleano");
        // resolver el operando a expresion
        String valor = ctx.expresionOperando(arg1);
        // negar con registro y guardar el booleano
        String destino = slot.getArreglo() + "[fp + " + slot.getIndice() + "]";
        return "AX_BOOLEAN = !" + valor + ";\n    " + destino + " = AX_BOOLEAN;";
    }
}
