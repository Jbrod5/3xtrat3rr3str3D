package org.jrg.model.ast.yLenguaje.paso_para;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class PasoParaAsig extends NodoASTY {

    private final NodoASTY variable;
    private final NodoASTY expresion;

    /**
     * Crear un paso de para con asignacion.
     */
    public PasoParaAsig(NodoASTY variable, NodoASTY expresion, int linea, int columna) {
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
        return visitor.visitarPasoParaAsig(this);
    }
}