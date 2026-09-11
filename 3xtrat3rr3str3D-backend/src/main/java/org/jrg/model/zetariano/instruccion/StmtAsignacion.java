package org.jrg.model.zetariano.instruccion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class StmtAsignacion extends NodoASTZetariano {

    private final NodoASTZetariano asignacion;

    public StmtAsignacion(NodoASTZetariano asignacion, int linea, int columna) {
        super(linea, columna);
        this.asignacion = asignacion;
    }

    public NodoASTZetariano getAsignacion() {
        return asignacion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtAsignacion(this);
    }
}
