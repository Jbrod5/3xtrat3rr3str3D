package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// leer un elemento por indice desde la base del arreglo
public class CuartetaAccesoArreglo extends Cuarteta {

    /**
     * Crear un acceso a arreglo con operador explicito.
     */
    public CuartetaAccesoArreglo(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

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

        // resolver el tipo elemento por campo base registrada o entero
        String tipoElem = ctx.tipoElementoDe(getArg1());
        if (tipoElem == null || tipoElem.isEmpty()) {
            tipoElem = "entero";
        }

        // elegir arreglo de heap segun el elemento
        String arregloElem = ctx.arregloHeapPara(tipoElem);
        String tipoSlot = tipoElem;

        // declarar el destino para el valor leido
        SlotHS slot = ctx.redeclararSlot(getResultado(), tipoSlot);

        // recordar el elemento para accesos encadenados
        if (tipoElem != null && tipoElem.isEmpty() == false) {
            ctx.mapaBases.put(getResultado(), tipoElem);
        }

        // leer del heap con base mas indice
        String base = ctx.expresionOperando(getArg1());
        String indice = ctx.expresionOperando(getArg2());
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return destino + " = " + arregloElem + "[" + base + " + " + indice + "];";

    }

}
