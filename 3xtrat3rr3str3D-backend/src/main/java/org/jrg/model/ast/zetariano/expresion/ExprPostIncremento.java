package org.jrg.model.ast.zetariano.expresion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class ExprPostIncremento extends NodoASTZetariano {

    private final NodoASTZetariano variable;

    public ExprPostIncremento(NodoASTZetariano variable, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
    }

    public NodoASTZetariano getVariable() {
        return variable;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprPostIncremento(this);
    }
}
