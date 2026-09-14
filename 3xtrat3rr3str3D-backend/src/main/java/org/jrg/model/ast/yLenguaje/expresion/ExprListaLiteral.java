package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class ExprListaLiteral extends NodoASTY {

    private final List<NodoASTY> elementos;

    /**
     * Crear una expresion de lista literal.
     */
    public ExprListaLiteral(List<NodoASTY> elementos, int linea, int columna) {
        super(linea, columna);
        this.elementos = elementos;
    }

    public List<NodoASTY> getElementos() {
        return this.elementos;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprListaLiteral(this);
    }
}