package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class TipoDato extends NodoASTY {

    private final String nombre;

    /**
     * Crear un tipo de dato con su nombre.
     */
    public TipoDato(String nombre, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarTipoDato(this);
    }
}