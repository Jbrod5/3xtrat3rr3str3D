package org.jrg.model.ast.yLenguaje.base;

public abstract class NodoASTY {

    private final int linea;
    private final int columna;

    public NodoASTY(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }

    public abstract <T> T accept(YAstVisitor<T> visitor);

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
}