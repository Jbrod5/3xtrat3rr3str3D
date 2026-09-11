package org.jrg.model.pigLatin.variable_asignable;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ValorAsignableArray extends NodoAST {
    private final NodoAST base;
    private final NodoAST indice;

    public ValorAsignableArray(NodoAST base, NodoAST indice, int linea, int columna) {
        super(linea, columna);
        this.base = base;
        this.indice = indice;
    }

    public NodoAST getBase() {
        return base;
    }

    public NodoAST getIndice() {
        return indice;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitValorAsignableArray(this);
    }
}
