package org.jrg.model.cuarteta;

// clase que representa una cuarteta para codigo de tres direcciones
public class Cuarteta {

    // operador de la cuarteta
    private final String operador;
    // primer argumento
    private final String arg1;
    // segundo argumento
    private final String arg2;
    // resultado de la operacion
    private final String resultado;

    // crear la cuarteta con sus cuatro campos
    public Cuarteta(String operador, String arg1, String arg2, String resultado) {
        // asignar los valores recibidos
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
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

    // generar la representacion en cadena
    @Override
    public String toString() {
        // usar el formato solicitado
        return "(" + operador + ", " + arg1 + ", " + arg2 + ", " + resultado + ")";
    }
}
