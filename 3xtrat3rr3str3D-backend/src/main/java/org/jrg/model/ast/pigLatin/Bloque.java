package org.jrg.model.ast.pigLatin;

import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;

import java.util.List;

public class Bloque extends NodoAST {
    private final List<NodoAST> instrucciones;

    public Bloque(List<NodoAST> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.instrucciones = instrucciones;
    }

    public List<NodoAST> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitBloque(this);
    }
}
