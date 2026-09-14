package org.jrg.model.ast.zetariano.expresion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class ExprOr extends NodoASTZetariano {

    private final NodoASTZetariano operandoIzquierdo;
    private final String operador;
    private final NodoASTZetariano operandoDerecho;

    public ExprOr(NodoASTZetariano operandoIzquierdo, String operador, NodoASTZetariano operandoDerecho, int linea, int columna) {
        super(linea, columna);
        this.operandoIzquierdo = operandoIzquierdo;
        this.operador = operador;
        this.operandoDerecho = operandoDerecho;
    }

    public NodoASTZetariano getOperandoIzquierdo() {
        return operandoIzquierdo;
    }

    public String getOperador() {
        return operador;
    }

    public NodoASTZetariano getOperandoDerecho() {
        return operandoDerecho;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprOr(this);
    }
}
