package org.jrg.model.semantico;

import java.util.ArrayList;
import java.util.List;
import org.jrg.util.MiniUUID;

public class Simbolo {

    private final String id;
    private String nombre;
    private CategoriaSimbolo categoria;
    private Tipo tipo;
    private Constante valor;
    private Integer tamano;
    private final List<Tipo> tiposParametros;
    private int numParametros;
    private Ambito ambito;
    private int posicionRelativa;
    private int fila;
    private int columna;

    /**
     * Crear un simbolo con nombre categoria y tipo.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo) {
        this(nombre, categoria, tipo, null, null, new ArrayList<>(), -1, null, -1, 0, 0);
    }

    /**
     * Crear un simbolo con valor constante.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo, Constante valor) {
        this(nombre, categoria, tipo, valor, null, new ArrayList<>(), -1, null, -1, 0, 0);
    }

    /**
     * Crear un simbolo con valor y posicion.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo, Constante valor, int fila, int columna) {
        this(nombre, categoria, tipo, valor, null, new ArrayList<>(), -1, null, -1, fila, columna);
    }

    /**
     * Crear un simbolo con ambito y posicion.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo, Constante valor, Ambito ambito, int fila, int columna) {
        this(nombre, categoria, tipo, valor, null, new ArrayList<>(), -1, ambito, -1, fila, columna);
    }

    /**
     * Crear un simbolo con parametros y posicion.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo, Constante valor, List<Tipo> tiposParametros, int fila, int columna) {
        this(nombre, categoria, tipo, valor, null, tiposParametros, -1, null, -1, fila, columna);
    }

    /**
     * Crear un simbolo con todos sus datos semanticos.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo, Constante valor, Integer tamano, List<Tipo> tiposParametros, int numParametros, Ambito ambito, int posicionRelativa, int fila, int columna) {
        this.id = MiniUUID.generate();
        // normalizar nombre nulo
        if (nombre == null) {
            this.nombre = "";
        } else {
            this.nombre = nombre;
        }

        this.categoria = categoria;
        this.tipo = tipo;
        this.valor = valor;
        this.tamano = tamano;
        this.tiposParametros = new ArrayList<>();
        // copiar parametros si existen
        if (tiposParametros != null) {
            this.tiposParametros.addAll(tiposParametros);
        }

        if (numParametros < 0) {
            this.numParametros = this.tiposParametros.size();
        } else {
            this.numParametros = numParametros;
        }

        this.ambito = ambito;
        this.posicionRelativa = posicionRelativa;
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Crear un simbolo con tamano parametros y ambito.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo, Constante valor, Integer tamano, List<Tipo> tiposParametros, Ambito ambito, int posicionRelativa, int fila, int columna) {
        this(nombre, categoria, tipo, valor, tamano, tiposParametros, -1, ambito, posicionRelativa, fila, columna);
    }

    /**
     * Crear un simbolo con tamano y cantidad de parametros.
     */
    public Simbolo(String nombre, CategoriaSimbolo categoria, Tipo tipo, Constante valor, int tamano, int numParametros, Ambito ambito, int posicionRelativa, int fila, int columna) {
        this(nombre, categoria, tipo, valor, Integer.valueOf(tamano), new ArrayList<>(), numParametros, ambito, posicionRelativa, fila, columna);
    }

    /**
     * Obtener el identificador del simbolo.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtener el nombre del simbolo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asignar el nombre del simbolo.
     */
    public void setNombre(String nombre) {
        // normalizar nombre nulo
        if (nombre == null) {
            this.nombre = "";
        } else {
            this.nombre = nombre;
        }
    }

    /**
     * Obtener la categoria del simbolo.
     */
    public CategoriaSimbolo getCategoria() {
        return categoria;
    }

    /**
     * Asignar la categoria del simbolo.
     */
    public void setCategoria(CategoriaSimbolo categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtener el tipo del simbolo.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Asignar el tipo del simbolo.
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtener el valor constante del simbolo.
     */
    public Constante getValor() {
        return valor;
    }

    /**
     * Asignar el valor constante del simbolo.
     */
    public void setValor(Constante valor) {
        this.valor = valor;
    }

    /**
     * Obtener el tamano del simbolo.
     */
    public Integer getTamano() {
        return tamano;
    }

    /**
     * Asignar el tamano del simbolo.
     */
    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    /**
     * Obtener los tipos de los parametros.
     */
    public List<Tipo> getTiposParametros() {
        return tiposParametros;
    }

    /**
     * Obtener los tipos de los parametros.
     */
    public List<Tipo> getParametros() {
        return tiposParametros;
    }

    /**
     * Agregar el tipo de un parametro.
     */
    public void agregarParametro(Tipo tipo) {
        // validar duplicado antes de agregar
        if (tipo != null && !tiposParametros.contains(tipo)) {
            tiposParametros.add(tipo);
            numParametros = tiposParametros.size();
        }
    }

    /**
     * Asignar los tipos de los parametros.
     */
    public void setTiposParametros(List<Tipo> tiposParametros) {
        this.tiposParametros.clear();
        // copiar lista si existe
        if (tiposParametros != null) {
            this.tiposParametros.addAll(tiposParametros);
        }

        numParametros = this.tiposParametros.size();
    }

    /**
     * Obtener la cantidad de parametros.
     */
    public int getNumParametros() {
        return numParametros;
    }

    /**
     * Asignar la cantidad de parametros.
     */
    public void setNumParametros(int numParametros) {
        // validar rango negativo
        if (numParametros < 0) {
            this.numParametros = 0;
        } else {
            this.numParametros = numParametros;
        }
    }

    /**
     * Obtener el ambito del simbolo.
     */
    public Ambito getAmbito() {
        return ambito;
    }

    /**
     * Asignar el ambito del simbolo.
     */
    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

    /**
     * Obtener el nombre del ambito del simbolo.
     */
    public String getNombreAmbito() {
        if (ambito == null) {
            return null;
        }

        return ambito.getNombre();
    }

    /**
     * Obtener la posicion relativa al ambito.
     */
    public int getPosicionRelativa() {
        return posicionRelativa;
    }

    /**
     * Asignar la posicion relativa al ambito.
     */
    public void setPosicionRelativa(int posicionRelativa) {
        this.posicionRelativa = posicionRelativa;
    }

    /**
     * Obtener la fila del simbolo.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtener la linea del simbolo.
     */
    public int getLinea() {
        return fila;
    }

    /**
     * Asignar la fila del simbolo.
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Asignar la linea del simbolo.
     */
    public void setLinea(int linea) {
        this.fila = linea;
    }

    /**
     * Obtener la columna del simbolo.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Asignar la columna del simbolo.
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * Verificar si el simbolo es una funcion.
     */
    public boolean esFuncion() {
        return categoria == CategoriaSimbolo.FUNCION || categoria == CategoriaSimbolo.METODO;
    }

    /**
     * Verificar si el simbolo es una variable.
     */
    public boolean esVariable() {
        return categoria == CategoriaSimbolo.VARIABLE;
    }

    /**
     * Verificar si el simbolo es un parametro.
     */
    public boolean esParametro() {
        return categoria == CategoriaSimbolo.PARAMETRO;
    }
}
