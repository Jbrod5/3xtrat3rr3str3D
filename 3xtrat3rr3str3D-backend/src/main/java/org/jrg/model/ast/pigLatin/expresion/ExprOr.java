package org.jrg.model.ast.pigLatin.expresion;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class ExprOr extends NodoAST {
    private final NodoAST operandoIzquierdo;
    private final String operador;
    private final NodoAST operandoDerecho;

    public ExprOr(NodoAST operandoIzquierdo, String operador, NodoAST operandoDerecho, int linea, int columna) {
        super(linea, columna);
        this.operandoIzquierdo = operandoIzquierdo;
        this.operador = operador;
        this.operandoDerecho = operandoDerecho;
    }

    public NodoAST getOperandoIzquierdo() {
        return operandoIzquierdo;
    }

    public String getOperador() {
        return operador;
    }

    public NodoAST getOperandoDerecho() {
        return operandoDerecho;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprOr(this);
    }
}
