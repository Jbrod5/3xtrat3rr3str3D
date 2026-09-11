package org.jrg.model.zetariano.instruccion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class StmtReturn extends NodoASTZetariano {

    private final NodoASTZetariano expresion;

    public StmtReturn(NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtReturn(this);
    }
}
