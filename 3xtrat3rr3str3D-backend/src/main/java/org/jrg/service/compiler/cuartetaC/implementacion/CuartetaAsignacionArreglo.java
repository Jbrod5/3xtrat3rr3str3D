package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// escribir un elemento por indice desde la base del arreglo
public class CuartetaAsignacionArreglo extends CuartetaC {

    /**
     * Crear una asignacion a arreglo con operador explicito.
     */
    public CuartetaAsignacionArreglo(String operador, String arg1, String arg2, String resultado,
                               String tipoArg1, String tipoArg2, String tipoResultado) {
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // resolver el struct base de los elementos si hay
        String baseElem = ctx.mapaBases.get(arg1);

        // elegir arreglo de heap segun el base o entero por defecto
        String arregloElem = "heapinteger";
        if (baseElem != null && baseElem.isEmpty() == false && baseElem.equals("_") == false) {
            arregloElem = ctx.arregloHeapPara(baseElem);
        }

        // escribir en el heap con base mas indice
        String base = ctx.expresionOperando(arg1);
        String indice = ctx.expresionOperando(arg2);
        String valor = ctx.expresionOperando(resultado);
        return arregloElem + "[" + base + " + " + indice + "] = " + valor + ";";

    }

}
