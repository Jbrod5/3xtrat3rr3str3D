package org.jrg.model.semantico;

public class TipoResultado {

    private final String nombre;
    private final boolean esPrimitivo;
    private final int dimension;
    private final String nombreAmbito;

    /**
     * Crear una representacion de tipo para la respuesta HTTP.
     */
    public TipoResultado(Tipo tipo) {
        if (tipo == null) {
            this.nombre = "";
            this.esPrimitivo = false;
            this.dimension = 0;
            this.nombreAmbito = "";
            return;
        }
        this.nombre = tipo.getNombre();
        this.esPrimitivo = tipo.esPrimitivo();
        this.dimension = tipo.getDimension();
        if (tipo.getNombreAmbito() == null) {
            this.nombreAmbito = "";
        } else {
            this.nombreAmbito = tipo.getNombreAmbito();
        }
    }

    /**
     * Obtener el nombre del tipo.
     */
    public String getNombre() {
        return nombre;
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
     * Obtener la dimension del tipo.
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Obtener el nombre del ambito del tipo.
     */
    public String getNombreAmbito() {
        return nombreAmbito;
    }
}
