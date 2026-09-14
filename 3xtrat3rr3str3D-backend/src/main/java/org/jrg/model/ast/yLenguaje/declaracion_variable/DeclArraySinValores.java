package org.jrg.model.ast.yLenguaje.declaracion_variable;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class DeclArraySinValores extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;
    private final NodoASTY tamano;

    /**
     * Crear una declaracion de array sin valores iniciales.
     */
    public DeclArraySinValores(NodoASTY tipo, String nombre, NodoASTY tamano, int linea, int columna) {
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

    public NodoASTY getTamano() {
        return this.tamano;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDeclArraySinValores(this);
    }
}