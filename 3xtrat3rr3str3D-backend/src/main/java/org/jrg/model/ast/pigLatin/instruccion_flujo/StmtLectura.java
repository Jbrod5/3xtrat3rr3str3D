package org.jrg.model.ast.pigLatin.instruccion_flujo;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class StmtLectura extends NodoAST {
    private final NodoAST lectura;

    public StmtLectura(NodoAST lectura, int linea, int columna) {
        super(linea, columna);
        this.lectura = lectura;
    }

    public NodoAST getLectura() {
        return lectura;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStmtLectura(this);
    }
}
