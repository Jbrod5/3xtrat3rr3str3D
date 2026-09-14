package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtSeleccion extends NodoASTY {

    private final NodoASTY seleccion;

    /**
     * Crear una instruccion de seleccion.
     */
    public StmtSeleccion(NodoASTY seleccion, int linea, int columna) {
        super(linea, columna);
        this.seleccion = seleccion;
    }

    public NodoASTY getSeleccion() {
        return this.seleccion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtSeleccion(this);
    }
}