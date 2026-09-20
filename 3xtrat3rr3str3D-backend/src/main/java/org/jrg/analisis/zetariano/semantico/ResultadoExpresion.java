package org.jrg.analisis.zetariano.semantico;

import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;

class ResultadoExpresion {

    private final Tipo tipo;
    private final boolean asignable;
    private final boolean valido;
    private final Simbolo simbolo;

    ResultadoExpresion(Tipo tipo) {
        this(tipo, false, tipo != null, null);
    }

    ResultadoExpresion(Tipo tipo, boolean asignable) {
        this(tipo, asignable, tipo != null, null);
    }

    ResultadoExpresion(Tipo tipo, boolean asignable, boolean valido) {
        this(tipo, asignable, valido, null);
    }

    ResultadoExpresion(Tipo tipo, boolean asignable, boolean valido, Simbolo simbolo) {
        // asignar campos del resultado
        this.tipo = tipo;
        this.asignable = asignable;
        this.valido = valido;
        this.simbolo = simbolo;
    }

    Tipo obtenerTipo() {
        return tipo;
    }

    boolean esAsignable() {
        return asignable;
    }

    boolean esValido() {
        return valido;
    }

    Simbolo obtenerSimbolo() {
        return simbolo;
    }
}
