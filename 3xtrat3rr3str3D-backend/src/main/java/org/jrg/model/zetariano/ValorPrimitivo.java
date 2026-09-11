package org.jrg.model.zetariano;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;
import org.jrg.model.base.TipoPrimitivo;

public class ValorPrimitivo extends NodoASTZetariano {

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
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarValorPrimitivo(this);
    }
}
