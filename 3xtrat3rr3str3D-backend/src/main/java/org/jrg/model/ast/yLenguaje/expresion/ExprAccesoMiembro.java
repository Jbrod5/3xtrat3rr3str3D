package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ExprAccesoMiembro extends NodoASTY {

    private final NodoASTY objeto;
    private final String miembro;

    /**
     * Crear una expresion de acceso a miembro.
     */
    public ExprAccesoMiembro(NodoASTY objeto, String miembro, int linea, int columna) {
        super(linea, columna);
        this.objeto = objeto;
        this.miembro = miembro;
    }

    public NodoASTY getObjeto() {
        return this.objeto;
    }

    public String getMiembro() {
        return this.miembro;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprAccesoMiembro(this);
    }
}