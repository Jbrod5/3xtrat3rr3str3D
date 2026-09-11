package org.jrg.model.zetariano.miembro_clase;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class MiembroMetodo extends NodoASTZetariano {

    private final NodoASTZetariano metodo;

    public MiembroMetodo(NodoASTZetariano metodo, int linea, int columna) {
        super(linea, columna);
        this.metodo = metodo;
    }

    public NodoASTZetariano getMetodo() {
        return metodo;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarMiembroMetodo(this);
    }
}
