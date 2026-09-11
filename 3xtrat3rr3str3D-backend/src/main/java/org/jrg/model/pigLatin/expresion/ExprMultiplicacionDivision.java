package org.jrg.model.pigLatin.expresion;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ExprMultiplicacionDivision extends NodoAST {
    private final NodoAST operandoIzquierdo;
    private final String operador;
    private final NodoAST operandoDerecho;

    public ExprMultiplicacionDivision(NodoAST operandoIzquierdo, String operador, NodoAST operandoDerecho, int linea, int columna) {
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
        return visitor.visitExprMultiplicacionDivision(this);
    }
}
