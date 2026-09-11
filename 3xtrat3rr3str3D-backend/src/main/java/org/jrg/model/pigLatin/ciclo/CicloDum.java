package org.jrg.model.pigLatin.ciclo;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class CicloDum extends NodoAST {
    private final NodoAST condicion;
    private final NodoAST bloque;

    public CicloDum(NodoAST condicion, NodoAST bloque, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloque = bloque;
    }

    public NodoAST getCondicion() {
        return condicion;
    }

    public NodoAST getBloque() {
        return bloque;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitCicloDum(this);
    }
}
