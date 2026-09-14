package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtEstructuraLocal extends NodoASTY {

    private final NodoASTY estructura;

    /**
     * Crear una instruccion de estructura local.
     */
    public StmtEstructuraLocal(NodoASTY estructura, int linea, int columna) {
        super(linea, columna);
        this.estructura = estructura;
    }

    public NodoASTY getEstructura() {
        return this.estructura;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtEstructuraLocal(this);
    }
}