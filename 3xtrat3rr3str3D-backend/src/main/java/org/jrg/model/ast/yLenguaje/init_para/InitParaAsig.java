package org.jrg.model.ast.yLenguaje.init_para;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class InitParaAsig extends NodoASTY {

    private final NodoASTY variable;
    private final NodoASTY expresion;

    /**
     * Crear una inicializacion de para con asignacion.
     */
    public InitParaAsig(NodoASTY variable, NodoASTY expresion, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
        this.expresion = expresion;
    }

    public NodoASTY getVariable() {
        return this.variable;
    }

    public NodoASTY getExpresion() {
        return this.expresion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarInitParaAsig(this);
    }
}