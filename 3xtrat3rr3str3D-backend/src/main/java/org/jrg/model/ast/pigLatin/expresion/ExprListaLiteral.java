package org.jrg.model.ast.pigLatin.expresion;

import java.util.List;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class ExprListaLiteral extends NodoAST {
    private final List<NodoAST> elementos;

    public ExprListaLiteral(List<NodoAST> elementos, int linea, int columna) {
        super(linea, columna);
        this.elementos = elementos;
    }

    public List<NodoAST> getElementos() {
        return elementos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprListaLiteral(this);
    }
}
