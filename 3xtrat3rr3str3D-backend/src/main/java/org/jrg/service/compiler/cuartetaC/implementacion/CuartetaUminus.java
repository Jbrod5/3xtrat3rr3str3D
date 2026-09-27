package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// negar un numero con su mismo tipo
public class CuartetaUminus extends CuartetaC {

    /**
     * Crear un menos unario con operador explicito.
     */
    public CuartetaUminus(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

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

        // declarar el destino con el tipo del resultado
        SlotHS slot = ctx.declararSlot(resultado, tipoResultado);

        // resolver el operando a expresion
        String valor = ctx.expresionOperando(arg1);

        // negar con registro y guardar en su arreglo
        String reg = ctx.registroPara(slot.getArreglo());
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return reg + " = -" + valor + ";\n    " + destino + " = " + reg + ";";

    }

}
