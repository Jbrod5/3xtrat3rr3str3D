package org.jrg.model.ast.yLenguaje.variable_asignable;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class VarSimple extends NodoASTY {

    private final String nombre;

    /**
     * Crear una variable simple con su nombre.
     */
    public VarSimple(String nombre, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarVarSimple(this);
    }
}