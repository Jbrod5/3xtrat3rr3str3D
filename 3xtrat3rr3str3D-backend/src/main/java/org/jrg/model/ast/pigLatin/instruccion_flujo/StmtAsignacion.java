package org.jrg.model.ast.pigLatin.instruccion_flujo;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class StmtAsignacion extends NodoAST {
    private final NodoAST asignacion;

    public StmtAsignacion(NodoAST asignacion, int linea, int columna) {
        super(linea, columna);
        this.asignacion = asignacion;
    }

    public NodoAST getAsignacion() {
        return asignacion;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtAsignacion(this);
    }
}
