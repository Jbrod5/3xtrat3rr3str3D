package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;
import org.jrg.model.base.TipoPrimitivo;

public class ExprPrimitivo extends NodoASTZetariano {

    private final NodoASTZetariano valor;
    private final TipoPrimitivo tipoDato;

    public ExprPrimitivo(NodoASTZetariano valor, TipoPrimitivo tipoDato, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
        this.tipoDato = tipoDato;
    }

    public NodoASTZetariano getValor() {
        return valor;
    }

    public TipoPrimitivo getTipoDato() {
        return tipoDato;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprPrimitivo(this);
    }
}
