package org.jrg.model.ast.pigLatin.instruccion_flujo;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class StmtCiclo extends NodoAST {
    private final NodoAST ciclo;

    public StmtCiclo(NodoAST ciclo, int linea, int columna) {
        super(linea, columna);
        this.ciclo = ciclo;
    }

    public NodoAST getCiclo() {
        return ciclo;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtCiclo(this);
    }
}
