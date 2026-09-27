package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// leer un elemento por indice desde la base del arreglo
public class CuartetaAccesoArreglo extends CuartetaC {

    /**
     * Crear un acceso a arreglo con operador explicito.
     */
    public CuartetaAccesoArreglo(String operador, String arg1, String arg2, String resultado,
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
        // resolver el struct base de los elementos si hay
        String baseElem = ctx.mapaBases.get(arg1);
        // elegir arreglo de heap segun el base o entero por defecto
        String arregloElem = "heapinteger";
        String tipoSlot = "entero";
        if (baseElem != null && baseElem.isEmpty() == false && baseElem.equals("_") == false) {
            arregloElem = ctx.arregloHeapPara(baseElem);
            tipoSlot = baseElem;
        }
        // declarar el destino para el valor leido
        SlotHS slot = ctx.redeclararSlot(resultado, tipoSlot);
        // recordar el base para accesos encadenados
        if (baseElem != null && baseElem.isEmpty() == false && baseElem.equals("_") == false) {
            ctx.mapaBases.put(resultado, baseElem);
        }
        // leer del heap con base mas indice
        String base = ctx.expresionOperando(arg1);
        String indice = ctx.expresionOperando(arg2);
        String destino = slot.getArreglo() + "[fp + " + slot.getIndice() + "]";
        return destino + " = " + arregloElem + "[" + base + " + " + indice + "];";
    }
}
