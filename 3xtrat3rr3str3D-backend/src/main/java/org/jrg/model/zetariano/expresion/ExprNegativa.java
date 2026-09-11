package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprNegativa extends NodoASTZetariano {

    private final String operador;
    private final NodoASTZetariano expresion;

    public ExprNegativa(String operador, NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.operador = operador;
        this.expresion = expresion;
    }

    public String getOperador() {
        return operador;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprNegativa(this);
    }
}
