package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtCondicional extends NodoASTY {

    private final NodoASTY condicional;

    /**
     * Crear una instruccion condicional.
     */
    public StmtCondicional(NodoASTY condicional, int linea, int columna) {
        super(linea, columna);
        this.condicional = condicional;
    }

    public NodoASTY getCondicional() {
        return this.condicional;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtCondicional(this);
    }
}