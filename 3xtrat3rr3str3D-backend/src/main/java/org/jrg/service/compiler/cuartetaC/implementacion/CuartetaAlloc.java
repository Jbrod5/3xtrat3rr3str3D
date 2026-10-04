package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// reservar un bloque en heap con base en heappointer
public class CuartetaAlloc extends Cuarteta {

    /**
     * Crear una reserva con operador explicito.
     */
    public CuartetaAlloc(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

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

        // resolver la dimension a expresion
        String dim = ctx.expresionOperando(getArg2());

        // declarar el destino como base entera
        // usar temporal intermedio cuando el destino es campo propio
        String nombreDestino = getResultado();
        boolean destinoCampo = ctx.esCampoPropio(getResultado());
        if (destinoCampo) {
            nombreDestino = "__reserva" + ctx.contadorCadenas;
            ctx.contadorCadenas = ctx.contadorCadenas + 1;
        }

        SlotHS slot = ctx.declararSlot(nombreDestino, "entero");

        // recordar el struct base del arreglo si trae
        if (getArg1() != null && getArg1().isEmpty() == false && getArg1().equals("_") == false) {
            ctx.mapaBases.put(nombreDestino, getArg1());
        }

        // tomar la base actual y avanzar el puntero
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        String reserva = destino + " = heappointer;\n    heappointer = heappointer + " + dim + ";";

        // guardar en el heap cuando el destino es campo propio
        if (destinoCampo) {
            reserva = reserva + "\n    " + ctx.guardarEnCampo(getResultado(), destino);
        }

        return reserva;

    }

}
