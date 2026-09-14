package org.jrg.model.ast.yLenguaje.atributo_struct;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class AtributoArray extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;
    private final int tamano;

    /**
     * Crear un atributo array con su tipo, nombre y tamano.
     */
    public AtributoArray(NodoASTY tipo, String nombre, int tamano, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamano = tamano;
    }

    public NodoASTY getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getTamano() {
        return this.tamano;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarAtributoArray(this);
    }
}