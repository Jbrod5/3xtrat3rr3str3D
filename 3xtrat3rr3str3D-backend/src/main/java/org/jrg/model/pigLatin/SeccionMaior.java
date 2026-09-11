package org.jrg.model.pigLatin;

import org.jrg.model.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.pigLatin.base.NodoAST;

import java.util.List;

public class SeccionMaior extends NodoAST {
    private final List<NodoAST> instrucciones;

    public SeccionMaior(List<NodoAST> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.instrucciones = instrucciones;
    }

    public List<NodoAST> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitSeccionMaior(this);
    }
}
