package org.jrg.model.ast.zetariano.instruccion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class StmtDeclaracion extends NodoASTZetariano {

    private final NodoASTZetariano declaracion;

    public StmtDeclaracion(NodoASTZetariano declaracion, int linea, int columna) {
        super(linea, columna);
        this.declaracion = declaracion;
    }

    public NodoASTZetariano getDeclaracion() {
        return declaracion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtDeclaracion(this);
    }
}
