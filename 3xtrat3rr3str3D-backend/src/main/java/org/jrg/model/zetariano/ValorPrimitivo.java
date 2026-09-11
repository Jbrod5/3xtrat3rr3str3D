package org.jrg.model.zetariano;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ValorPrimitivo extends NodoASTZetariano {

    private final String valor;

    public ValorPrimitivo(String valor, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarValorPrimitivo(this);
    }
}
