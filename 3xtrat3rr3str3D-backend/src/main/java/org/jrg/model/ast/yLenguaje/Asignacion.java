package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class Asignacion extends NodoASTY {

    private final NodoASTY variable;
    private final NodoASTY valor;

    /**
     * Crear una asignacion con su variable y valor.
     */
    public Asignacion(NodoASTY variable, NodoASTY valor, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
        this.valor = valor;
    }

    public NodoASTY getVariable() {
        return this.variable;
    }

    public NodoASTY getValor() {
        return this.valor;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarAsignacion(this);
    }
}