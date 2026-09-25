package org.jrg.service.compiler.cuartetaC;

// clase base abstracta para la traduccion de cuartetas a codigo C
public abstract class CuartetaC {

    // operador de la cuarteta
    protected final String operador;
    // primer argumento de la cuarteta
    protected final String arg1;
    // segundo argumento de la cuarteta
    protected final String arg2;
    // resultado de la cuarteta
    protected final String resultado;
    // tipo del primer argumento
    protected final String tipoArg1;
    // tipo del segundo argumento
    protected final String tipoArg2;
    // tipo del resultado
    protected final String tipoResultado;

    /**
     * Crear una cuarteta con sus campos y sus tipos.
     */
    public CuartetaC(String operador, String arg1, String arg2, String resultado,
                     String tipoArg1, String tipoArg2, String tipoResultado) {
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
     * Obtener la linea de codigo C para la cuarteta.
     */
    public abstract String obtenerCodigoC(ContextoTraduccion ctx);

    /**
     * Obtener el operador de la cuarteta.
     */
    public String getOperador() {
        return operador;
    }

    /**
     * Obtener el primer argumento de la cuarteta.
     */
    public String getArg1() {
        return arg1;
    }

    /**
     * Obtener el segundo argumento de la cuarteta.
     */
    public String getArg2() {
        return arg2;
    }

    /**
     * Obtener el resultado de la cuarteta.
     */
    public String getResultado() {
        return resultado;
    }

    /**
     * Obtener el tipo del primer argumento.
     */
    public String getTipoArg1() {
        return tipoArg1;
    }

    /**
     * Obtener el tipo del segundo argumento.
     */
    public String getTipoArg2() {
        return tipoArg2;
    }

    /**
     * Obtener el tipo del resultado.
     */
    public String getTipoResultado() {
        return tipoResultado;
    }
}
