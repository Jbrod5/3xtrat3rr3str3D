package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprAccesoMiembro extends NodoASTZetariano {

    private final NodoASTZetariano objeto;
    private final String miembro;

    public ExprAccesoMiembro(NodoASTZetariano objeto, String miembro, int linea, int columna) {
        super(linea, columna);
        this.objeto = objeto;
        this.miembro = miembro;
    }

    public NodoASTZetariano getObjeto() {
        return objeto;
    }

    public String getMiembro() {
        return miembro;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprAccesoMiembro(this);
    }
}
