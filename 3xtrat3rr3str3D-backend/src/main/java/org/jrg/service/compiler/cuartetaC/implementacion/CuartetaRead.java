package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// leer un entero de consola a un slot
public class CuartetaRead extends CuartetaC {

    /**
     * Crear una lectura con operador explicito.
     */
    public CuartetaRead(String operador, String arg1, String arg2, String resultado,
                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // omitir la lectura sin variable destino
        if (resultado == null || resultado.equals("_")) {
            return "";
        }

        // declarar el destino siempre entero
        SlotHS slot = ctx.declararSlot(resultado, "entero");

        // leer con scanf sobre el slot
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return "scanf(\"%d\", &" + destino + ");";

    }

}
