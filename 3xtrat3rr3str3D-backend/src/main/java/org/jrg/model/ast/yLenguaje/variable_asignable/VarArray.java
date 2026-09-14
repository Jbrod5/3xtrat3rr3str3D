package org.jrg.model.ast.yLenguaje.variable_asignable;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class VarArray extends NodoASTY {

    private final NodoASTY base;
    private final NodoASTY indice;

    /**
     * Crear un acceso a array con su base e indice.
     */
    public VarArray(NodoASTY base, NodoASTY indice, int linea, int columna) {
        super(linea, columna);
        this.base = base;
        this.indice = indice;
    }

    public NodoASTY getBase() {
        return this.base;
    }

    public NodoASTY getIndice() {
        return this.indice;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarVarArray(this);
    }
}