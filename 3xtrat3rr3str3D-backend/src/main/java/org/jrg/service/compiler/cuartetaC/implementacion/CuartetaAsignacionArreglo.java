package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;

// escribir un elemento por indice desde la base del arreglo
public class CuartetaAsignacionArreglo extends Cuarteta {

    /**
     * Crear una asignacion a arreglo con operador explicito.
     */
    public CuartetaAsignacionArreglo(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // resolver el tipo elemento por campo base registrada o entero
        String tipoElem = ctx.tipoElementoDe(getArg1());
        if (tipoElem == null || tipoElem.isEmpty()) {
            tipoElem = "entero";
        }

        // elegir arreglo de heap segun el elemento
        String arregloElem = ctx.arregloHeapPara(tipoElem);

        // escribir en el heap con base mas indice
        String base = ctx.expresionOperando(getArg1());
        String indice = ctx.expresionOperando(getArg2());
        String valor = ctx.expresionOperando(getResultado());
        return arregloElem + "[" + base + " + " + indice + "] = " + valor + ";";

    }

}
