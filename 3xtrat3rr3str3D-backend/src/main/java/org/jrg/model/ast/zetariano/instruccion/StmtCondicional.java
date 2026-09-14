package org.jrg.model.ast.zetariano.instruccion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class StmtCondicional extends NodoASTZetariano {

    private final NodoASTZetariano condicional;

    public StmtCondicional(NodoASTZetariano condicional, int linea, int columna) {
        super(linea, columna);
        this.condicional = condicional;
    }

    public NodoASTZetariano getCondicional() {
        return condicional;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtCondicional(this);
    }
}
