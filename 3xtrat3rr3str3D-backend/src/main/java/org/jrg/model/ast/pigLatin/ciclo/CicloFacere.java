package org.jrg.model.ast.pigLatin.ciclo;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class CicloFacere extends NodoAST {
    private final NodoAST bloque;
    private final NodoAST condicion;

    public CicloFacere(NodoAST bloque, NodoAST condicion, int linea, int columna) {
        super(linea, columna);
        this.bloque = bloque;
        this.condicion = condicion;
    }

    public NodoAST getBloque() {
        return bloque;
    }

    public NodoAST getCondicion() {
        return condicion;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitCicloFacere(this);
    }
}
