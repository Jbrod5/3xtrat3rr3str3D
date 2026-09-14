package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class SeccionFunciones extends NodoASTY {

    private final List<NodoASTY> funciones;

    /**
     * Crear una seccion de funciones con su lista de definiciones.
     */
    public SeccionFunciones(List<NodoASTY> funciones, int linea, int columna) {
        super(linea, columna);
        this.funciones = funciones;
    }

    public List<NodoASTY> getFunciones() {
        return this.funciones;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarSeccionFunciones(this);
    }
}