package org.jrg.analisis.pigLatin.semantico;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.semantico.Constante;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;

class TipoSemanticoPigLatin {

    private final Tipo tipo;
    private final Constante constante;
    private final boolean asignable;
    private final Simbolo simbolo;
    private final List<Tipo> tiposElementos;

    private final boolean valido;

    TipoSemanticoPigLatin() {
        this.tipo = null;
        this.constante = null;
        this.asignable = false;
        this.simbolo = null;
        this.tiposElementos = new ArrayList<>();
        this.valido = false;
    }

    TipoSemanticoPigLatin(Tipo tipo) {
        this.tipo = tipo;
        this.constante = null;
        this.asignable = false;
        this.simbolo = null;
        this.tiposElementos = new ArrayList<>();
        this.valido = tipo != null;
    }

    TipoSemanticoPigLatin(Tipo tipo, Constante constante) {
        this.tipo = tipo;
        this.constante = constante;
        this.asignable = false;
        this.simbolo = null;
        this.tiposElementos = new ArrayList<>();
        this.valido = tipo != null;
    }

    TipoSemanticoPigLatin(Tipo tipo, Constante constante, boolean asignable, Simbolo simbolo) {
        this.tipo = tipo;
        this.constante = constante;
        this.asignable = asignable;
        this.simbolo = simbolo;
        this.tiposElementos = new ArrayList<>();
        this.valido = tipo != null;
    }

    TipoSemanticoPigLatin(Tipo tipo, List<Tipo> tiposElementos, boolean valido) {
        this.tipo = tipo;
        this.constante = null;
        this.asignable = false;
        this.simbolo = null;

        // copiar lista de elementos
        if (tiposElementos == null) {
            this.tiposElementos = new ArrayList<>();
        } else {
            this.tiposElementos = new ArrayList<>(tiposElementos);
        }
        this.valido = valido;
    }

    TipoSemanticoPigLatin(Tipo tipo, Constante constante, boolean asignable, Simbolo simbolo, boolean valido) {
        this.tipo = tipo;
        this.constante = constante;
        this.asignable = asignable;
        this.simbolo = simbolo;
        this.tiposElementos = new ArrayList<>();
        this.valido = valido;
    }

    Tipo obtenerTipo() {
        return tipo;
    }

    Constante obtenerConstante() {
        return constante;
    }

    boolean esAsignable() {
        return asignable;
    }

    Simbolo obtenerSimbolo() {
        return simbolo;
    }

    List<Tipo> obtenerTiposElementos() {
        return tiposElementos;
    }

    boolean esDesconocido() {
        return tipo == null;
    }

    boolean esValido() {
        return valido && tipo != null && !"desconocido".equals(tipo.getNombre()) && !"vacio".equals(tipo.getNombre());
    }
}
