package org.jrg.model.zetariano.instruccion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class StmtContinue extends NodoASTZetariano {

    public StmtContinue(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtContinue(this);
    }
}
