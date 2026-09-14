package org.jrg.model.ast.zetariano.variable_asignable;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class VarArray extends NodoASTZetariano {

    private final NodoASTZetariano variable;
    private final NodoASTZetariano indice;

    public VarArray(NodoASTZetariano variable, NodoASTZetariano indice, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
        this.indice = indice;
    }

    public NodoASTZetariano getVariable() {
        return variable;
    }

    public NodoASTZetariano getIndice() {
        return indice;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarVarArray(this);
    }
}
