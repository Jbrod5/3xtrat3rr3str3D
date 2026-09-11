package org.jrg.model.pigLatin;

import org.jrg.model.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.pigLatin.base.NodoAST;

public class ValorPrimitivo extends NodoAST {
    private final String valor;

    public ValorPrimitivo(String valor, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitValorPrimitivo(this);
    }
}
