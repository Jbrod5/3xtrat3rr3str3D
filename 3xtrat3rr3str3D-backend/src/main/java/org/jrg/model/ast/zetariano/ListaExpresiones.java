package org.jrg.model.ast.zetariano;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class ListaExpresiones extends NodoASTZetariano {

    private final List<NodoASTZetariano> expresiones;

    public ListaExpresiones(List<NodoASTZetariano> expresiones, int linea, int columna) {
        super(linea, columna);
        this.expresiones = new ArrayList<>();
        if (expresiones != null) {
            this.expresiones.addAll(expresiones);
        }
    }

    public List<NodoASTZetariano> getExpresiones() {
        return expresiones;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarListaExpresiones(this);
    }
}
