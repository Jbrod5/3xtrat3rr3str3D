package org.jrg.model.zetariano.instruccion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class StmtCiclo extends NodoASTZetariano {

    private final NodoASTZetariano ciclo;

    public StmtCiclo(NodoASTZetariano ciclo, int linea, int columna) {
        super(linea, columna);
        this.ciclo = ciclo;
    }

    public NodoASTZetariano getCiclo() {
        return ciclo;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStmtCiclo(this);
    }
}
