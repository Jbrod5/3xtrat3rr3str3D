package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class ListaExpresiones extends NodoASTY {

    private final List<NodoASTY> expresiones;

    /**
     * Crear una lista de expresiones.
     */
    public ListaExpresiones(List<NodoASTY> expresiones, int linea, int columna) {
        super(linea, columna);
        this.expresiones = expresiones;
    }

    public List<NodoASTY> getExpresiones() {
        return this.expresiones;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarListaExpresiones(this);
    }
}