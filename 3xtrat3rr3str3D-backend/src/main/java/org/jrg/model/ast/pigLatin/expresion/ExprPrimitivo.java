package org.jrg.model.ast.pigLatin.expresion;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.base.TipoPrimitivo;

public class ExprPrimitivo extends NodoAST {
    private final NodoAST valor;
    private final TipoPrimitivo tipoDato;

    public ExprPrimitivo(NodoAST valor, TipoPrimitivo tipoDato, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
        this.tipoDato = tipoDato;
    }

    public NodoAST getValor() {
        return valor;
    }

    public TipoPrimitivo getTipoDato() {
        return tipoDato;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprPrimitivo(this);
    }
}
