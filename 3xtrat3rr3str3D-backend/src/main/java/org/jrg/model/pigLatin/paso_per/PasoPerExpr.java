package org.jrg.model.pigLatin.paso_per;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class PasoPerExpr extends NodoAST {
    private final NodoAST expresion;

    public PasoPerExpr(NodoAST expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoAST getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitPasoPerExpr(this);
    }
}
