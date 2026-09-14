package org.jrg.model.ast.pigLatin.expresion;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class ExprNegativa extends NodoAST {
    private final String operador;
    private final NodoAST expresion;

    public ExprNegativa(String operador, NodoAST expresion, int linea, int columna) {
        super(linea, columna);
        this.operador = operador;
        this.expresion = expresion;
    }

    public String getOperador() {
        return operador;
    }

    public NodoAST getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprNegativa(this);
    }
}
