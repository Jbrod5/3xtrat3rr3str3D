package org.jrg.model.ast.yLenguaje.instruccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class StmtAsignacion extends NodoASTY {

    private final NodoASTY asignacion;

    /**
     * Crear una instruccion de asignacion.
     */
    public StmtAsignacion(NodoASTY asignacion, int linea, int columna) {
        super(linea, columna);
        this.asignacion = asignacion;
    }

    public NodoASTY getAsignacion() {
        return this.asignacion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStmtAsignacion(this);
    }
}