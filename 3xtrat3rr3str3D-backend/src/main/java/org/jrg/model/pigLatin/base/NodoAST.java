package org.jrg.model.pigLatin.base;

public abstract class NodoAST {

    private  final int linea;
    private  final int columna;




    public NodoAST(int linea, int columna){
        this.linea = linea;
        this.columna = columna;
    }


    public abstract  <T> T accept(LatinusAstVisitor<T> visitor);

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }
}
