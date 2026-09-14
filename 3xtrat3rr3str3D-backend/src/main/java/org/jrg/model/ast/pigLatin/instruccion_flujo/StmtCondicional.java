package org.jrg.model.ast.pigLatin.instruccion_flujo;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class StmtCondicional extends NodoAST {
    private final NodoAST condicional;

    public StmtCondicional(NodoAST condicional, int linea, int columna) {
        super(linea, columna);
        this.condicional = condicional;
    }

    public NodoAST getCondicional() {
        return condicional;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtCondicional(this);
    }
}
