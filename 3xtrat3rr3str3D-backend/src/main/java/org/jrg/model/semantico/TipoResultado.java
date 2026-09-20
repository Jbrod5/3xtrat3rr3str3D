package org.jrg.model.semantico;

import java.util.ArrayList;
import java.util.List;

public class TipoResultado {

    private final String nombre;
    private final boolean esPrimitivo;
    private final int dimension;
    private final String nombreAmbito;
    private final List<CampoTipoResultado> campos;

    /**
     * Crear una representacion de tipo para la respuesta HTTP.
     */
    public TipoResultado(Tipo tipo) {
        this.campos = new ArrayList<>();
        // validar tipo nulo
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
        // normalizar ambito nulo
        if (tipo.getNombreAmbito() == null) {
            this.nombreAmbito = "";
        } else {
            this.nombreAmbito = tipo.getNombreAmbito();
        }

        // recorrer campos para el DTO
        if (tipo.getCampos() != null) {
            for (int i = 0; i < tipo.getCampos().size(); i++) {
                this.campos.add(new CampoTipoResultado(tipo.getCampos().get(i)));
            }
        }
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean isEsPrimitivo() {
        return this.esPrimitivo;
    }

    public boolean esPrimitivo() {
        return this.esPrimitivo;
    }

    public int getDimension() {
        return this.dimension;
    }

    public String getNombreAmbito() {
        return this.nombreAmbito;
    }

    public List<CampoTipoResultado> getCampos() {
        return this.campos;
    }
}