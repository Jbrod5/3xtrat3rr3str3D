package org.jrg.model.ast.yLenguaje.declaracion_variable;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class DeclArrayConValores extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;
    private final NodoASTY tamano;
    private final NodoASTY listaValores;

    /**
     * Crear una declaracion de array con valores iniciales.
     */
    public DeclArrayConValores(NodoASTY tipo, String nombre, NodoASTY tamano, NodoASTY listaValores, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamano = tamano;
        this.listaValores = listaValores;
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

    public NodoASTY getListaValores() {
        return this.listaValores;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDeclArrayConValores(this);
    }
}