package org.jrg.model.pigLatin.instruccion_impresion;

import java.util.List;
import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ImpresionEncadenada extends NodoAST {
    private final List<NodoAST> elementos;

    public ImpresionEncadenada(List<NodoAST> elementos, int linea, int columna) {
        super(linea, columna);
        this.elementos = elementos;
    }

    public List<NodoAST> getElementos() {
        return elementos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitImpresionEncadenada(this);
    }
}
