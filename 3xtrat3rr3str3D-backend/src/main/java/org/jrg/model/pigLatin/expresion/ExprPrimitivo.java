package org.jrg.model.pigLatin.expresion;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ExprPrimitivo extends NodoAST {
    private final NodoAST valor;

    public ExprPrimitivo(NodoAST valor, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
    }

    public NodoAST getValor() {
        return valor;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprPrimitivo(this);
    }
}
