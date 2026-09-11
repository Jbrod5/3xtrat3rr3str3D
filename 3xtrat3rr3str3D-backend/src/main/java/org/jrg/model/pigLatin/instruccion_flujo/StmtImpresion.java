package org.jrg.model.pigLatin.instruccion_flujo;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class StmtImpresion extends NodoAST {
    private final NodoAST impresion;

    public StmtImpresion(NodoAST impresion, int linea, int columna) {
        super(linea, columna);
        this.impresion = impresion;
    }

    public NodoAST getImpresion() {
        return impresion;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtImpresion(this);
    }
}
