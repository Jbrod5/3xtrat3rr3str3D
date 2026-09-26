package org.jrg.model.resultado;

import org.jrg.model.cuarteta.Cuarteta;

// clase para mandar la cuarteta en JSON
public class CuartetaResultado {

    private final String operador;
    private final String arg1;
    private final String arg2;
    private final String resultado;

    private final String tipoArg1;     // tipo del primer argumento
    private final String tipoArg2;     // tipo del segundo argumento
    private final String tipoResultado; // tipo del resultado

    // crear el resultado con sus cuatro campos y sus tipos
    public CuartetaResultado(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {
        // asignar los valores recibidos
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
        this.tipoArg1 = tipoArg1;
        this.tipoArg2 = tipoArg2;
        this.tipoResultado = tipoResultado;
    }

    // crear el resultado a partir de una cuarteta
    public CuartetaResultado(Cuarteta cuarteta) {
        // extraer cada campo desde la cuarteta
        this.operador = cuarteta.getOperador();
        this.arg1 = cuarteta.getArg1();
        this.arg2 = cuarteta.getArg2();
        this.resultado = cuarteta.getResultado();
        this.tipoArg1 = cuarteta.getTipoArg1();
        this.tipoArg2 = cuarteta.getTipoArg2();
        this.tipoResultado = cuarteta.getTipoResultado();
    }

    // dar el operador para el JSON
    public String getOperador() {
        return operador;
    }

    // obtener el primer argumento
    public String getArg1() {
        return arg1;
    }

    // obtener el segundo argumento
    public String getArg2() {
        return arg2;
    }

    // obtener el resultado
    public String getResultado() {
        return resultado;
    }

    // obtener el tipo del primer argumento
    public String getTipoArg1() {
        return tipoArg1;
    }

    // obtener el tipo del segundo argumento
    public String getTipoArg2() {
        return tipoArg2;
    }

    // obtener el tipo del resultado
    public String getTipoResultado() {
        return tipoResultado;
    }
}
