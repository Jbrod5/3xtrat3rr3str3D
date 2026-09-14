package org.jrg.model.ast.yLenguaje.paso_para;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class PasoParaExpr extends NodoASTY {

    private final NodoASTY expresion;

    /**
     * Crear un paso de para con expresion.
     */
    public PasoParaExpr(NodoASTY expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTY getExpresion() {
        return this.expresion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarPasoParaExpr(this);
    }
}