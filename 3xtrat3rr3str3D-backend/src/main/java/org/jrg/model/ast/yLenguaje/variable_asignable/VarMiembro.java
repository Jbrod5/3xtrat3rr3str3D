package org.jrg.model.ast.yLenguaje.variable_asignable;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class VarMiembro extends NodoASTY {

    private final NodoASTY base;
    private final String miembro;

    /**
     * Crear un acceso a miembro con su base y nombre del miembro.
     */
    public VarMiembro(NodoASTY base, String miembro, int linea, int columna) {
        super(linea, columna);
        this.base = base;
        this.miembro = miembro;
    }

    public NodoASTY getBase() {
        return this.base;
    }

    public String getMiembro() {
        return this.miembro;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarVarMiembro(this);
    }
}