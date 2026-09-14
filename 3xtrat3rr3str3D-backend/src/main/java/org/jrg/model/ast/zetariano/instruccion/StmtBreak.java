package org.jrg.model.ast.zetariano.instruccion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class StmtBreak extends NodoASTZetariano {

    public StmtBreak(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtBreak(this);
    }
}
