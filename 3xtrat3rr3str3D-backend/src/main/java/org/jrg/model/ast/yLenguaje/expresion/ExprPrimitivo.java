package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprPrimitivo extends NodoASTY {

    private final NodoASTY valor;

    /**
     * Crear una expresion primitiva.
     */
    public ExprPrimitivo(NodoASTY valor, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
    }

    public NodoASTY getValor() {
        return this.valor;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprPrimitivo(this);
    }
}