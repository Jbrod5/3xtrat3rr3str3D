package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprPostDecremento extends NodoASTY {

    private final NodoASTY variable;

    /**
     * Crear una expresion de post decremento.
     */
    public ExprPostDecremento(NodoASTY variable, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
    }

    public NodoASTY getVariable() {
        return this.variable;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprPostDecremento(this);
    }
}