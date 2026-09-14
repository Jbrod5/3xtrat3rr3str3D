package org.jrg.model.ast.yLenguaje.declaracion_variable;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class DeclMatriz extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;
    private final NodoASTY tamanoFilas;
    private final NodoASTY tamanoColumnas;

    /**
     * Crear una declaracion de matriz.
     */
    public DeclMatriz(NodoASTY tipo, String nombre, NodoASTY tamanoFilas, NodoASTY tamanoColumnas, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamanoFilas = tamanoFilas;
        this.tamanoColumnas = tamanoColumnas;
    }

    public NodoASTY getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoASTY getTamanoFilas() {
        return this.tamanoFilas;
    }

    public NodoASTY getTamanoColumnas() {
        return this.tamanoColumnas;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDeclMatriz(this);
    }
}