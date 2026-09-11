package org.jrg.model.pigLatin.variable_asignable;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ValorAsignableSimple extends NodoAST {
    private final String identificador;

    public ValorAsignableSimple(String identificador, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitValorAsignableSimple(this);
    }
}
