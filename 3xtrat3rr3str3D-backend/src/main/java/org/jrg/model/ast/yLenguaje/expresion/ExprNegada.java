package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprNegada extends NodoASTY {

    private final NodoASTY expresion;

    /**
     * Crear una expresion negada.
     */
    public ExprNegada(NodoASTY expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTY getExpresion() {
        return this.expresion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprNegada(this);
    }
}