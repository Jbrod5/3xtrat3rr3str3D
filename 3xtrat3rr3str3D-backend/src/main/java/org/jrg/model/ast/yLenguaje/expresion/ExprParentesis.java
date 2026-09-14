package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprParentesis extends NodoASTY {

    private final NodoASTY expresion;

    /**
     * Crear una expresion entre parentesis.
     */
    public ExprParentesis(NodoASTY expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTY getExpresion() {
        return this.expresion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprParentesis(this);
    }
}