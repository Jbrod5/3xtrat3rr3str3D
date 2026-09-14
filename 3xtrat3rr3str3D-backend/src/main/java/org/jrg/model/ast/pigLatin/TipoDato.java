package org.jrg.model.ast.pigLatin;

import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;

public class TipoDato extends NodoAST {
    private final String tipo;

    public TipoDato(String tipo, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitTipoDato(this);
    }
}
