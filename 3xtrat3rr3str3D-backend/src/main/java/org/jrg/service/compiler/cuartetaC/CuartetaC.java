package org.jrg.service.compiler.cuartetaC;

// clase base abstracta para la traduccion de cuartetas a maquina Heap Stack
public abstract class CuartetaC {

    protected final String operador;

    protected final String arg1;

    protected final String arg2;

    protected final String resultado;

    protected final String tipoArg1;

    protected final String tipoArg2;

    protected final String tipoResultado;

    /**
     * Crear una cuarteta con sus campos y sus tipos.
     */
    public CuartetaC(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // asignar los valores recibidos
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
        this.tipoArg1 = tipoArg1;
        this.tipoArg2 = tipoArg2;
        this.tipoResultado = tipoResultado;

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    public abstract String obtenerCodigoC(ContextoTraduccion ctx);

    /**
     * Obtener el operador para clasificar.
     */
    public String getOperador() {
        return operador;
    }

    /**
     * Obtener el primer argumento para inspeccionar.
     */
    public String getArg1() {
        return arg1;
    }

    /**
     * Obtener el segundo argumento para inspeccionar.
     */
    public String getArg2() {
        return arg2;
    }

    /**
     * Obtener el resultado para inspeccionar.
     */
    public String getResultado() {
        return resultado;
    }

}
