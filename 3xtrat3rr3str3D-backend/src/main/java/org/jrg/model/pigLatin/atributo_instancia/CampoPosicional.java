package org.jrg.model.pigLatin.atributo_instancia;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class CampoPosicional extends NodoAST {
    private final NodoAST valor;

    public CampoPosicional(NodoAST valor, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
    }

    public NodoAST getValor() {
        return valor;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitCampoPosicional(this);
    }
}
