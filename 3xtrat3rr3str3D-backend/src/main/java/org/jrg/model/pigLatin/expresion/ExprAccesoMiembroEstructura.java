package org.jrg.model.pigLatin.expresion;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ExprAccesoMiembroEstructura extends NodoAST {
    private final NodoAST objeto;
    private final String miembro;

    public ExprAccesoMiembroEstructura(NodoAST objeto, String miembro, int linea, int columna) {
        super(linea, columna);
        this.objeto = objeto;
        this.miembro = miembro;
    }

    public NodoAST getObjeto() {
        return objeto;
    }

    public String getMiembro() {
        return miembro;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprAccesoMiembroEstructura(this);
    }
}
