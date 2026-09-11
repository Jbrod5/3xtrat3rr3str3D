package org.jrg.model.pigLatin.instruccion_flujo;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class StmtExpresion extends NodoAST {
    private final NodoAST expresion;

    public StmtExpresion(NodoAST expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoAST getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtExpresion(this);
    }
}
