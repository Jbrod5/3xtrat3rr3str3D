package org.jrg.model.pigLatin;

import org.jrg.model.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.base.TipoPrimitivo;

public class ValorPrimitivo extends NodoAST {
    private final String valor;
    private final TipoPrimitivo tipoDato;

    public ValorPrimitivo(String valor, TipoPrimitivo tipoDato, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
        this.tipoDato = tipoDato;
    }

    public String getValor() {
        return valor;
    }

    public TipoPrimitivo getTipoDato() {
        return tipoDato;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitValorPrimitivo(this);
    }
}
