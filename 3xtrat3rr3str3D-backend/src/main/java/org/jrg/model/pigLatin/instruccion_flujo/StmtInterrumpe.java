package org.jrg.model.pigLatin.instruccion_flujo;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class StmtInterrumpe extends NodoAST {

    public StmtInterrumpe(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtInterrumpe(this);
    }
}
