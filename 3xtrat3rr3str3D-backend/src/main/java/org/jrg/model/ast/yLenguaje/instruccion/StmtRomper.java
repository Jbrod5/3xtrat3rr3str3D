package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtRomper extends NodoASTY {

    /**
     * Crear una instruccion romper.
     */
    public StmtRomper(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtRomper(this);
    }
}