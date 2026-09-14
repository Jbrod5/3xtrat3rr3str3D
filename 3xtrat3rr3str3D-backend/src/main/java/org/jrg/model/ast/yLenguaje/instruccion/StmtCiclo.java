package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtCiclo extends NodoASTY {

    private final NodoASTY ciclo;

    /**
     * Crear una instruccion de ciclo.
     */
    public StmtCiclo(NodoASTY ciclo, int linea, int columna) {
        super(linea, columna);
        this.ciclo = ciclo;
    }

    public NodoASTY getCiclo() {
        return this.ciclo;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtCiclo(this);
    }
}