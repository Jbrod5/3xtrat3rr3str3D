package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprPostIncremento extends NodoASTY {

    private final NodoASTY variable;

    /**
     * Crear una expresion de post incremento.
     */
    public ExprPostIncremento(NodoASTY variable, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
    }

    public NodoASTY getVariable() {
        return this.variable;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprPostIncremento(this);
    }
}