package org.jrg.model.pigLatin.expresion;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ExprPostIncremento extends NodoAST {
    private final NodoAST variable;

    public ExprPostIncremento(NodoAST variable, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
    }

    public NodoAST getVariable() {
        return variable;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprPostIncremento(this);
    }
}
