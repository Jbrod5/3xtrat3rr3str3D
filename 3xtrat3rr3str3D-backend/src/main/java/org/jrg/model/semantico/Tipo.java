package org.jrg.model.semantico;

import java.util.ArrayList;
import java.util.List;
import org.jrg.util.MiniUUID;

public class Tipo {

    private final String id;
    private String nombre;
    private boolean esPrimitivo;
    private int dimension;
    private Tipo tipoBase;
    private final List<Simbolo> campos;
    private Ambito ambito;

    /**
     * Crear un tipo primitivo o nominal sin dimension.
     */
    public Tipo(String nombre, boolean esPrimitivo) {
        this(nombre, esPrimitivo, 0, null, new ArrayList<>(), null);
    }

    /**
     * Crear un tipo con dimension de arreglo.
     */
    public Tipo(String nombre, boolean esPrimitivo, int dimension) {
        this(nombre, esPrimitivo, dimension, null, new ArrayList<>(), null);
    }

    /**
     * Crear un tipo con tipo base y dimension.
     */
    public Tipo(String nombre, boolean esPrimitivo, Tipo tipoBase, int dimension) {
        this(nombre, esPrimitivo, dimension, tipoBase, new ArrayList<>(), null);
    }

    /**
     * Crear un tipo con campos.
     */
    public Tipo(String nombre, boolean esPrimitivo, List<Simbolo> campos) {
        this(nombre, esPrimitivo, 0, null, campos, null);
    }

    /**
     * Crear un tipo con campos y ambito.
     */
    public Tipo(String nombre, boolean esPrimitivo, List<Simbolo> campos, Ambito ambito) {
        this(nombre, esPrimitivo, 0, null, campos, ambito);
    }

    /**
     * Crear un tipo con todos sus datos semanticos.
     */
    public Tipo(String nombre, boolean esPrimitivo, int dimension, Tipo tipoBase, List<Simbolo> campos, Ambito ambito) {
        this.id = MiniUUID.generate();
        // normalizar nombre nulo
        if (nombre == null) {
            this.nombre = "";
        } else {
            this.nombre = nombre;
        }

        this.esPrimitivo = esPrimitivo;
        // validar rango negativo
        if (dimension < 0) {
            this.dimension = 0;
        } else {
            this.dimension = dimension;
        }

        this.tipoBase = tipoBase;
        this.campos = new ArrayList<>();
        // copiar campos si existen
        if (campos != null) {
            this.campos.addAll(campos);
        }

        this.ambito = ambito;
    }

    /**
     * Obtener el identificador del tipo.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtener el nombre del tipo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asignar el nombre del tipo.
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
     * Verificar si el tipo es primitivo.
     */
    public boolean isEsPrimitivo() {
        return esPrimitivo;
    }

    /**
     * Verificar si el tipo es primitivo.
     */
    public boolean esPrimitivo() {
        return esPrimitivo;
    }

    /**
     * Asignar si el tipo es primitivo.
     */
    public void setEsPrimitivo(boolean esPrimitivo) {
        this.esPrimitivo = esPrimitivo;
    }

    /**
     * Obtener la dimension del arreglo.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Asignar la dimension del arreglo.
     */
    public void setDimension(int dimension) {
        // validar rango negativo
        if (dimension < 0) {
            this.dimension = 0;
        } else {
            this.dimension = dimension;
        }
    }

    /**
     * Obtener el tipo base del arreglo.
     */
    public Tipo getTipoBase() {
        return tipoBase;
    }

    /**
     * Asignar el tipo base del arreglo.
     */
    public void setTipoBase(Tipo tipoBase) {
        this.tipoBase = tipoBase;
    }

    /**
     * Obtener los campos declarados en el tipo.
     */
    public List<Simbolo> getCampos() {
        return campos;
    }

    /**
     * Agregar un campo al tipo.
     */
    public void agregarCampo(Simbolo campo) {
        // validar duplicado antes de agregar
        if (campo != null && !campos.contains(campo)) {
            campos.add(campo);
        }
    }

    /**
     * Buscar un campo por nombre.
     */
    public Simbolo buscarCampo(String nombre) {
        if (nombre == null) {
            return null;
        }

        // recorrer lista de campos
        for (Simbolo campo : campos) {
            if (nombre.equals(campo.getNombre())) {
                return campo;
            }
        }

        return null;
    }

    /**
     * Verificar si el tipo contiene un campo.
     */
    public boolean tieneCampo(String nombre) {
        return buscarCampo(nombre) != null;
    }

    /**
     * Obtener el ambito donde fue declarado el tipo.
     */
    public Ambito getAmbito() {
        return ambito;
    }

    /**
     * Asignar el ambito donde fue declarado el tipo.
     */
    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

    /**
     * Obtener el nombre del ambito del tipo.
     */
    public String getNombreAmbito() {
        if (ambito == null) {
            return null;
        }

        return ambito.getNombre();
    }

    /**
     * Verificar si el tipo representa un arreglo.
     */
    public boolean esArray() {
        return dimension > 0;
    }
}
