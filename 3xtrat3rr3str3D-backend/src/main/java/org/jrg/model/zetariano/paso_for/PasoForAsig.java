package org.jrg.model.zetariano.paso_for;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class PasoForAsig extends NodoASTZetariano {

    private final NodoASTZetariano variable;
    private final NodoASTZetariano expresion;

    public PasoForAsig(NodoASTZetariano variable, NodoASTZetariano expresion, int linea, int columna) {
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
        return visitor.visitarPasoForAsig(this);
    }
}
