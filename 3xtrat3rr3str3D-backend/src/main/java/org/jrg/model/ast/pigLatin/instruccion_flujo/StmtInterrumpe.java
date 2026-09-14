package org.jrg.model.ast.pigLatin.instruccion_flujo;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class StmtInterrumpe extends NodoAST {

    public StmtInterrumpe(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtInterrumpe(this);
    }
}
