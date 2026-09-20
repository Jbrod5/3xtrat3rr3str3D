package org.jrg.model.error;

public class ErrorCompilacion {

    private final TipoError tipo;
    private final int linea;
    private final int columna;
    private final String descripcion;

    /**
     * Crear un error con tipo posicion y descripcion.
     */
    public ErrorCompilacion(TipoError tipo, int linea, int columna, String descripcion) {
        this.tipo = tipo;
        this.linea = linea;
        this.columna = columna;

        // normalizar descripcion nula
        if (descripcion == null) {
            this.descripcion = "";
        } else {
            this.descripcion = descripcion;
        }
    }

    /**
     * Crear un error sin posicion especifica.
     */
    public ErrorCompilacion(TipoError tipo, String descripcion) {
        this(tipo, 0, 0, descripcion);
    }

    /**
     * Crear un error con una descripcion vacia.
     */
    public ErrorCompilacion(TipoError tipo, int linea, int columna) {
        this(tipo, linea, columna, "");
    }

    /**
     * Obtener el tipo de error.
     */
    public TipoError getTipo() {
        return tipo;
    }

    /**
     * Obtener la linea del error.
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Obtener la columna del error.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Obtener la descripcion del error.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtener la descripcion del error.
     */
    public String getMensaje() {
        return descripcion;
    }

    /**
     * Verificar si el error pertenece al tipo indicado.
     */
    public boolean esDeTipo(TipoError tipoError) {
        return tipo == tipoError;
    }
}
