package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprParentesis extends NodoASTZetariano {

    private final NodoASTZetariano expresion;

    public ExprParentesis(NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprParentesis(this);
    }
}
