package org.jrg.model.zetariano.base;

public abstract class NodoASTZetariano {

    private final int linea;
    private final int columna;

    public NodoASTZetariano(int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
    }

    public abstract <T> T accept(ZetarianoAstVisitor<T> visitor);

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
}
