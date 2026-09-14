package org.jrg.model.ast.yLenguaje.declaracion_variable;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class DeclConTipoYValor extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;
    private final NodoASTY valor;

    /**
     * Crear una declaracion con tipo y valor opcional.
     */
    public DeclConTipoYValor(NodoASTY tipo, String nombre, NodoASTY valor, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.valor = valor;
    }

    public NodoASTY getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoASTY getValor() {
        return this.valor;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDeclConTipoYValor(this);
    }
}