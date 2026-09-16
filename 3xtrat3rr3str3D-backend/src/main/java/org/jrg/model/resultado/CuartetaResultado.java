package org.jrg.model.resultado;

import org.jrg.model.cuarteta.Cuarteta;

// clase para el resultado de serializacion JSON de una cuarteta
public class CuartetaResultado {

    // operador
    private final String operador;
    // primer argumento
    private final String arg1;
    // segundo argumento
    private final String arg2;
    // resultado
    private final String resultado;

    // crear el resultado a partir de una cuarteta
    public CuartetaResultado(Cuarteta cuarteta) {
        // extraer cada campo desde la cuarteta
        this.operador = cuarteta.getOperador();
        this.arg1 = cuarteta.getArg1();
        this.arg2 = cuarteta.getArg2();
        this.resultado = cuarteta.getResultado();
    }

    // obtener el operador para serializacion
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
}
