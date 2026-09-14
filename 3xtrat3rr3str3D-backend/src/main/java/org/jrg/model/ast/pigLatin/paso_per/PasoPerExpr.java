package org.jrg.model.ast.pigLatin.paso_per;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

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
