package org.jrg.model.ast.pigLatin;

import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;

import java.util.List;

public class ListaAtributosInstancia extends NodoAST {
    private final List<NodoAST> atributos;

    public ListaAtributosInstancia(List<NodoAST> atributos, int linea, int columna) {
        super(linea, columna);
        this.atributos = atributos;
    }

    public List<NodoAST> getAtributos() {
        return atributos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitListaAtributosInstancia(this);
    }
}
