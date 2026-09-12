package org.jrg.model.semantico;

public class SimboloResultado {

    private final String nombre;
    private final String tipo;
    private final String categoria;
    private final String ambito;
    private final int fila;
    private final int columna;

    /**
     * Crear una representacion de simbolo para la respuesta HTTP.
     */
    public SimboloResultado(Simbolo simbolo) {
        if (simbolo == null) {
            this.nombre = "";
            this.tipo = "";
            this.categoria = "";
            this.ambito = "";
            this.fila = 0;
            this.columna = 0;
            return;
        }
        this.nombre = simbolo.getNombre();
        this.tipo = representarTipo(simbolo.getTipo());
        if (simbolo.getCategoria() == null) {
            this.categoria = "";
        } else {
            this.categoria = simbolo.getCategoria().name();
        }
        if (simbolo.getNombreAmbito() == null) {
            this.ambito = "";
        } else {
            this.ambito = simbolo.getNombreAmbito();
        }
        this.fila = simbolo.getFila();
        this.columna = simbolo.getColumna();
    }

    /**
     * Obtener el nombre del simbolo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtener el tipo del simbolo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Obtener la categoria del simbolo.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Obtener el ambito del simbolo.
     */
    public String getAmbito() {
        return ambito;
    }

    /**
     * Obtener la fila del simbolo.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtener la columna del simbolo.
     */
    public int getColumna() {
        return columna;
    }

    private String representarTipo(Tipo tipo) {
        if (tipo == null) {
            return "";
        }
        StringBuilder texto = new StringBuilder();
        texto.append(tipo.getNombre());
        for (int i = 0; i < tipo.getDimension(); i++) {
            texto.append("[]");
        }
        return texto.toString();
    }
}
