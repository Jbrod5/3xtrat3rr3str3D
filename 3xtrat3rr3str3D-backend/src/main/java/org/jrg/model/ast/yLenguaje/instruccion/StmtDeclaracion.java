package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtDeclaracion extends NodoASTY {

    private final NodoASTY declaracion;

    /**
     * Crear una instruccion de declaracion.
     */
    public StmtDeclaracion(NodoASTY declaracion, int linea, int columna) {
        super(linea, columna);
        this.declaracion = declaracion;
    }

    public NodoASTY getDeclaracion() {
        return this.declaracion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtDeclaracion(this);
    }
}