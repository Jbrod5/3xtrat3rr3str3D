package org.jrg.model.pigLatin.expresion;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ExprNegada extends NodoAST {
    private final String operador;
    private final NodoAST expresion;

    public ExprNegada(String operador, NodoAST expresion, int linea, int columna) {
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
        return visitor.visitExprNegada(this);
    }
}
