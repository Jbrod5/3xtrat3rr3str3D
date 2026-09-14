package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprAnd extends NodoASTY {

    private final NodoASTY izquierdo;
    private final NodoASTY derecho;

    /**
     * Crear una expresion logica and.
     */
    public ExprAnd(NodoASTY izquierdo, NodoASTY derecho, int linea, int columna) {
        super(linea, columna);
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    public NodoASTY getIzquierdo() {
        return this.izquierdo;
    }

    public NodoASTY getDerecho() {
        return this.derecho;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprAnd(this);
    }
}