package org.jrg.model.ast.pigLatin;

import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;

import java.util.List;

public class ListaExpresiones extends NodoAST {
    private final List<NodoAST> expresiones;

    public ListaExpresiones(List<NodoAST> expresiones, int linea, int columna) {
        super(linea, columna);
        this.expresiones = expresiones;
    }

    public List<NodoAST> getExpresiones() {
        return expresiones;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitListaExpresiones(this);
    }
}
