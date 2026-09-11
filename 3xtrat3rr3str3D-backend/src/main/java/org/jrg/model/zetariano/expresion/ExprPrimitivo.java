package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprPrimitivo extends NodoASTZetariano {

    private final NodoASTZetariano valor;

    public ExprPrimitivo(NodoASTZetariano valor, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
    }

    public NodoASTZetariano getValor() {
        return valor;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprPrimitivo(this);
    }
}
