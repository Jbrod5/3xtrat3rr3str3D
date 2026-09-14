package org.jrg.model.semantico;

public class CampoTipoResultado {

    private final String id;
    private final String nombre;
    private final String tipo;

    /**
     * Crear una representacion de campo de tipo para la respuesta HTTP.
     */
    public CampoTipoResultado(Simbolo campo) {
        if (campo == null) {
            this.id = "";
            this.nombre = "";
            this.tipo = "";
            return;
        }
        if (campo.getId() == null) {
            this.id = "";
        } else {
            this.id = campo.getId();
        }
        this.nombre = campo.getNombre();
        this.tipo = representarTipo(campo.getTipo());
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

    // construir una representacion textual del tipo
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