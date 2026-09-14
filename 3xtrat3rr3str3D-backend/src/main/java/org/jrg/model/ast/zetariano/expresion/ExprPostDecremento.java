package org.jrg.model.ast.zetariano.expresion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class ExprPostDecremento extends NodoASTZetariano {

    private final NodoASTZetariano variable;

    public ExprPostDecremento(NodoASTZetariano variable, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
    }

    public NodoASTZetariano getVariable() {
        return variable;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprPostDecremento(this);
    }
}
