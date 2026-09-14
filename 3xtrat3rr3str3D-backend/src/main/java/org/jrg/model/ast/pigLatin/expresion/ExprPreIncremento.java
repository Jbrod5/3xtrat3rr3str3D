package org.jrg.model.ast.pigLatin.expresion;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class ExprPreIncremento extends NodoAST {
    private final String operador;
    private final NodoAST variable;

    public ExprPreIncremento(String operador, NodoAST variable, int linea, int columna) {
        super(linea, columna);
        this.operador = operador;
        this.variable = variable;
    }

    public String getOperador() {
        return operador;
    }

    public NodoAST getVariable() {
        return variable;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprPreIncremento(this);
    }
}
