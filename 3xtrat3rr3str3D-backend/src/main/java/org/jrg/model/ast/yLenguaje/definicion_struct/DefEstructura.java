package org.jrg.model.ast.yLenguaje.definicion_struct;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class DefEstructura extends NodoASTY {

    private final String nombre;
    private final List<NodoASTY> atributos;

    /**
     * Crear una definicion de estructura con su nombre y atributos.
     */
    public DefEstructura(String nombre, List<NodoASTY> atributos, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.atributos = atributos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<NodoASTY> getAtributos() {
        return this.atributos;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDefEstructura(this);
    }
}