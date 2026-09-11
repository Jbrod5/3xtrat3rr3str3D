package org.jrg.model.zetariano.variable_asignable;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class VarMiembro extends NodoASTZetariano {

    private final NodoASTZetariano variable;
    private final String miembro;

    public VarMiembro(NodoASTZetariano variable, String miembro, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
        this.miembro = miembro;
    }

    public NodoASTZetariano getVariable() {
        return variable;
    }

    public String getMiembro() {
        return miembro;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarVarMiembro(this);
    }
}
