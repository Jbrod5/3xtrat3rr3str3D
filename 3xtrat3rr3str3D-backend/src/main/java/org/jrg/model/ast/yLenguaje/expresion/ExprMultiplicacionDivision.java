package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprMultiplicacionDivision extends NodoASTY {

    private final NodoASTY izquierdo;
    private final String operador;
    private final NodoASTY derecho;

    /**
     * Crear una expresion de multiplicacion o division.
     */
    public ExprMultiplicacionDivision(NodoASTY izquierdo, String operador, NodoASTY derecho, int linea, int columna) {
        super(linea, columna);
        this.izquierdo = izquierdo;
        this.operador = operador;
        this.derecho = derecho;
    }

    public NodoASTY getIzquierdo() {
        return this.izquierdo;
    }

    public String getOperador() {
        return this.operador;
    }

    public NodoASTY getDerecho() {
        return this.derecho;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprMultiplicacionDivision(this);
    }
}