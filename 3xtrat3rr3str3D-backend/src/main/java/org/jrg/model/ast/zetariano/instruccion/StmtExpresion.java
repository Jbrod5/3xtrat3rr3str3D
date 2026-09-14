package org.jrg.model.ast.zetariano.instruccion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class StmtExpresion extends NodoASTZetariano {

    private final NodoASTZetariano expresion;

    public StmtExpresion(NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtExpresion(this);
    }
}
