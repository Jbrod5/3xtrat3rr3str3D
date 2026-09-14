package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprAccesoArray extends NodoASTY {

    private final NodoASTY objeto;
    private final NodoASTY indice;

    /**
     * Crear una expresion de acceso a array.
     */
    public ExprAccesoArray(NodoASTY objeto, NodoASTY indice, int linea, int columna) {
        super(linea, columna);
        this.objeto = objeto;
        this.indice = indice;
    }

    public NodoASTY getObjeto() {
        return this.objeto;
    }

    public NodoASTY getIndice() {
        return this.indice;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprAccesoArray(this);
    }
}