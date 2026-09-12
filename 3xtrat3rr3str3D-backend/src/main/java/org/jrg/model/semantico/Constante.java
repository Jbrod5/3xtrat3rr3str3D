package org.jrg.model.semantico;

public class Constante {

    private final Object valor;
    private final Tipo tipo;
    private final int linea;
    private final int columna;

    /**
     * Crear una constante con valor y tipo.
     */
    public Constante(Object valor, Tipo tipo) {
        this(valor, tipo, 0, 0);
    }

    /**
     * Crear una constante con valor tipo y posicion.
     */
    public Constante(Object valor, Tipo tipo, int linea, int columna) {
        this.valor = valor;
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;
    }

    /**
     * Crear una constante intercambiando el orden de valor y tipo.
     */
    public Constante(Tipo tipo, Object valor) {
        this(valor, tipo, 0, 0);
    }

    /**
     * Crear una constante intercambiando el orden de valor y tipo con posicion.
     */
    public Constante(Tipo tipo, Object valor, int linea, int columna) {
        this(valor, tipo, linea, columna);
    }

    /**
     * Obtener el valor de la constante.
     */
    public Object getValor() {
        return valor;
    }

    /**
     * Obtener el tipo de la constante.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Obtener la linea de la constante.
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Obtener la columna de la constante.
     */
    public int getColumna() {
        return columna;
    }
}
