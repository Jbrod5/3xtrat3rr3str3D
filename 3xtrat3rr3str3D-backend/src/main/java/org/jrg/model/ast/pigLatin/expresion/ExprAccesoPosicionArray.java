package org.jrg.model.ast.pigLatin.expresion;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class ExprAccesoPosicionArray extends NodoAST {
    private final NodoAST array;
    private final NodoAST indice;

    public ExprAccesoPosicionArray(NodoAST array, NodoAST indice, int linea, int columna) {
        super(linea, columna);
        this.array = array;
        this.indice = indice;
    }

    public NodoAST getArray() {
        return array;
    }

    public NodoAST getIndice() {
        return indice;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprAccesoPosicionArray(this);
    }
}
