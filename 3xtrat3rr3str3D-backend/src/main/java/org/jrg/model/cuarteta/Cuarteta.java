package org.jrg.model.cuarteta;

// clase que representa una cuarteta para codigo de tres direcciones
public class Cuarteta {

    private final String operador;
    private final String arg1;
    private final String arg2;
    private final String resultado;
    private final String tipoArg1; // tipo del primer argumento
    private final String tipoArg2; // tipo del segundo argumento
    private final String tipoResultado; // tipo del resultado

    // crear la cuarteta con sus cuatro campos
    public Cuarteta(String operador, String arg1, String arg2, String resultado) {
        // usar el constructor completo con tipos en _
        this(operador, arg1, arg2, resultado, "_", "_", "_");
    }

    // crear la cuarteta con sus cuatro campos y sus tipos
    public Cuarteta(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {
        // asignar los valores recibidos
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
        this.tipoArg1 = tipoArg1;
        this.tipoArg2 = tipoArg2;
        this.tipoResultado = tipoResultado;
    }

    // obtener el operador
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

    // generar la representacion en cadena
    @Override
    public String toString() {
        // usar el formato solicitado
        return "(" + operador + ", " + arg1 + ", " + arg2 + ", " + resultado + ")";
    }
}
