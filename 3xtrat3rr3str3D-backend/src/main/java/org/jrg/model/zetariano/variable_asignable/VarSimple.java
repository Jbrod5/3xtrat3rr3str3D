package org.jrg.model.zetariano.variable_asignable;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class VarSimple extends NodoASTZetariano {

    private final String identificador;

    public VarSimple(String identificador, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
    }

    public String getIdentificador() {
        return identificador;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarVarSimple(this);
    }
}
