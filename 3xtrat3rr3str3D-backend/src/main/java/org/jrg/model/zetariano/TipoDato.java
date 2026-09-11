package org.jrg.model.zetariano;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class TipoDato extends NodoASTZetariano {

    private final String tipo;

    public TipoDato(String tipo, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarTipoDato(this);
    }
}
