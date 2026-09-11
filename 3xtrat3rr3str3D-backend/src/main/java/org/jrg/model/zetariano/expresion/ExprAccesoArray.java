package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprAccesoArray extends NodoASTZetariano {

    private final NodoASTZetariano objeto;
    private final NodoASTZetariano indice;

    public ExprAccesoArray(NodoASTZetariano objeto, NodoASTZetariano indice, int linea, int columna) {
        super(linea, columna);
        this.objeto = objeto;
        this.indice = indice;
    }

    public NodoASTZetariano getObjeto() {
        return objeto;
    }

    public NodoASTZetariano getIndice() {
        return indice;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprAccesoArray(this);
    }
}
