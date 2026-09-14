package org.jrg.model.ast.zetariano.variable_asignable;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

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
