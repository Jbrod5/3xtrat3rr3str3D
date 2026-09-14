package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtRetorno extends NodoASTY {

    private final NodoASTY expresion;

    /**
     * Crear una instruccion retornar con su expresion.
     */
    public StmtRetorno(NodoASTY expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTY getExpresion() {
        return this.expresion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtRetorno(this);
    }
}