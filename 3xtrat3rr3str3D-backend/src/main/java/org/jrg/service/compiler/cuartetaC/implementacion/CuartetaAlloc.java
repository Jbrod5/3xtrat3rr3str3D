package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// reservar un bloque en heap con base en hptr
public class CuartetaAlloc extends CuartetaC {

    /**
     * Crear una reserva con operador explicito.
     */
    public CuartetaAlloc(String operador, String arg1, String arg2, String resultado,
                   String tipoArg1, String tipoArg2, String tipoResultado) {
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
        // resolver la dimension a expresion
        String dim = ctx.expresionOperando(arg2);
        // declarar el destino como base entera
        SlotHS slot = ctx.declararSlot(resultado, "entero");
        // recordar el struct base del arreglo si trae
        if (arg1 != null && arg1.isEmpty() == false && arg1.equals("_") == false) {
            ctx.mapaBases.put(resultado, arg1);
        }
        // tomar la base actual y avanzar el puntero
        String destino = slot.getArreglo() + "[fp + " + slot.getIndice() + "]";
        return destino + " = hptr;\n    hptr = hptr + " + dim + ";";
    }
}
