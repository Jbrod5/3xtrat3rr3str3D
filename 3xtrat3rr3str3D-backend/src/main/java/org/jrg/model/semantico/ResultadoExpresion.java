package org.jrg.model.semantico;

public class ResultadoExpresion {

    private final Tipo tipo;
    private final Simbolo simbolo;
    private final Constante constante;
    private final boolean asignable;

    /**
     * Crear un resultado de expresion.
     */
    public ResultadoExpresion(Tipo tipo, Simbolo simbolo, Constante constante, boolean asignable) {
        this.tipo = tipo;
        this.simbolo = simbolo;
        this.constante = constante;
        this.asignable = asignable;
    }

    /**
     * Crear un resultado con tipo y capacidad de asignacion.
     */
    public ResultadoExpresion(Tipo tipo, boolean asignable) {
        this(tipo, null, null, asignable);
    }

    /**
     * Obtener el tipo inferido.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Obtener el simbolo resuelto.
     */
    public Simbolo getSimbolo() {
        return simbolo;
    }

    /**
     * Obtener el valor constante.
     */
    public Constante getConstante() {
        return constante;
    }

    /**
     * Verificar si la expresion representa un asignable.
     */
    public boolean isAsignable() {
        return asignable;
    }
}
