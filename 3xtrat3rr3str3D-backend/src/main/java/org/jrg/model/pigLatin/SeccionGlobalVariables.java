package org.jrg.model.pigLatin;

import org.jrg.model.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.pigLatin.base.NodoAST;

import java.util.List;

public class SeccionGlobalVariables extends NodoAST {
    private final List<NodoAST> declaraciones;

    public SeccionGlobalVariables(List<NodoAST> declaraciones, int linea, int columna) {
        super(linea, columna);
        this.declaraciones = declaraciones;
    }

    public List<NodoAST> getDeclaraciones() {
        return declaraciones;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitSeccionGlobalVariables(this);
    }
}
