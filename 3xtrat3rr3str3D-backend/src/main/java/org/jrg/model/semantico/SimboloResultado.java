package org.jrg.model.semantico;

public class SimboloResultado {

    private final String id;
    private final String nombre;
    private final String tipo;
    private final String categoria;
    private final String ambito;
    private final int fila;
    private final int columna;
    private final int numParametros;
    private final int posicionRelativa;
    private final Integer tamano;
    private final Object valor;

    /**
     * Crear una representacion de simbolo para la respuesta HTTP.
     */
    public SimboloResultado(Simbolo simbolo) {
        // validar simbolo nulo
        if (simbolo == null) {
            this.id = "";
            this.nombre = "";
            this.tipo = "";
            this.categoria = "";
            this.ambito = "";
            this.fila = 0;
            this.columna = 0;
            this.numParametros = 0;
            this.posicionRelativa = 0;
            this.tamano = null;
            this.valor = null;
            return;
        }

        // normalizar identificador nulo
        if (simbolo.getId() == null) {
            this.id = "";
        } else {
            this.id = simbolo.getId();
        }

        this.nombre = simbolo.getNombre();
        this.tipo = representarTipo(simbolo.getTipo());
        // normalizar categoria nula
        if (simbolo.getCategoria() == null) {
            this.categoria = "";
        } else {
            this.categoria = simbolo.getCategoria().name();
        }

        // normalizar ambito nulo
        if (simbolo.getNombreAmbito() == null) {
            this.ambito = "";
        } else {
            this.ambito = simbolo.getNombreAmbito();
        }

        this.fila = simbolo.getFila();
        this.columna = simbolo.getColumna();
        this.numParametros = simbolo.getNumParametros();
        this.posicionRelativa = simbolo.getPosicionRelativa();
        this.tamano = simbolo.getTamano();
        // extraer valor si existe
        if (simbolo.getValor() == null) {
            this.valor = null;
        } else {
            this.valor = simbolo.getValor().getValor();
        }
    }

    public String getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getAmbito() {
        return this.ambito;
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public int getNumParametros() {
        return this.numParametros;
    }

    public int getPosicionRelativa() {
        return this.posicionRelativa;
    }

    public Integer getTamano() {
        return this.tamano;
    }

    public Object getValor() {
        return this.valor;
    }

    // construir una representacion textual del tipo
    private String representarTipo(Tipo tipo) {
        if (tipo == null) {
            return "";
        }

        StringBuilder texto = new StringBuilder();
        texto.append(tipo.getNombre());
        // recorrer dimensiones del arreglo
        for (int i = 0; i < tipo.getDimension(); i++) {
            texto.append("[]");
        }

        return texto.toString();
    }
}