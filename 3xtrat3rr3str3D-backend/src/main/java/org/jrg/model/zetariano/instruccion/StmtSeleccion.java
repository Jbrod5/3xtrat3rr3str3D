package org.jrg.model.zetariano.instruccion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class StmtSeleccion extends NodoASTZetariano {

    private final NodoASTZetariano seleccion;

    public StmtSeleccion(NodoASTZetariano seleccion, int linea, int columna) {
        super(linea, columna);
        this.seleccion = seleccion;
    }

    public NodoASTZetariano getSeleccion() {
        return seleccion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtSeleccion(this);
    }
}
