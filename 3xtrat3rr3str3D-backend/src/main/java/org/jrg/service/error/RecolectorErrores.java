package org.jrg.service.error;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.error.ErrorCompilacion;
import org.jrg.model.error.TipoError;

public class RecolectorErrores {

    private final List<ErrorCompilacion> errores;

    /**
     * Crear un recolector de errores vacio.
     */
    public RecolectorErrores() {
        this(new ArrayList<>());
    }

    /**
     * Crear un recolector con errores iniciales.
     */
    public RecolectorErrores(List<ErrorCompilacion> errores) {
        this.errores = new ArrayList<>();
        if (errores != null) {
            this.errores.addAll(errores);
        }
    }

    /**
     * Agregar un error al recolector.
     */
    public boolean agregar(ErrorCompilacion error) {
        if (error == null) {
            return false;
        }
        return errores.add(error);
    }

    /**
     * Agregar un error al recolector.
     */
    public boolean agregarError(ErrorCompilacion error) {
        return agregar(error);
    }

    /**
     * Crear y agregar un error al recolector.
     */
    public ErrorCompilacion agregar(TipoError tipo, int linea, int columna, String descripcion) {
        ErrorCompilacion error = new ErrorCompilacion(tipo, linea, columna, descripcion);
        agregar(error);
        return error;
    }

    /**
     * Crear y agregar un error lexico.
     */
    public ErrorCompilacion agregarLexico(int linea, int columna, String descripcion) {
        return agregar(TipoError.LEXICO, linea, columna, descripcion);
    }

    /**
     * Crear y agregar un error sintactico.
     */
    public ErrorCompilacion agregarSintactico(int linea, int columna, String descripcion) {
        return agregar(TipoError.SINTACTICO, linea, columna, descripcion);
    }

    /**
     * Crear y agregar un error semantico.
     */
    public ErrorCompilacion agregarSemantico(int linea, int columna, String descripcion) {
        return agregar(TipoError.SEMANTICO, linea, columna, descripcion);
    }

    /**
     * Obtener todos los errores registrados.
     */
    public List<ErrorCompilacion> getErrores() {
        return new ArrayList<>(errores);
    }

    /**
     * Obtener todos los errores registrados.
     */
    public List<ErrorCompilacion> obtenerErrores() {
        return getErrores();
    }

    /**
     * Obtener los errores de un tipo.
     */
    public List<ErrorCompilacion> obtenerErrores(TipoError tipo) {
        List<ErrorCompilacion> filtrados = new ArrayList<>();
        for (ErrorCompilacion error : errores) {
            if (error != null && error.esDeTipo(tipo)) {
                filtrados.add(error);
            }
        }
        return filtrados;
    }

    /**
     * Obtener la cantidad total de errores.
     */
    public int cantidad() {
        return errores.size();
    }

    /**
     * Obtener la cantidad de errores de un tipo.
     */
    public int cantidad(TipoError tipo) {
        return obtenerErrores(tipo).size();
    }

    /**
     * Verificar si existen errores registrados.
     */
    public boolean tieneErrores() {
        return !errores.isEmpty();
    }

    /**
     * Eliminar todos los errores registrados.
     */
    public void limpiar() {
        errores.clear();
    }
}
