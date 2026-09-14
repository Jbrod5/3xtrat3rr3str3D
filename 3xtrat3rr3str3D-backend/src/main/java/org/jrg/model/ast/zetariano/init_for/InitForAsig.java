package org.jrg.model.ast.zetariano.init_for;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class InitForAsig extends NodoASTZetariano {

    private final NodoASTZetariano variable;
    private final NodoASTZetariano expresion;

    public InitForAsig(NodoASTZetariano variable, NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
        this.expresion = expresion;
    }

    public NodoASTZetariano getVariable() {
        return variable;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarInitForAsig(this);
    }
}
