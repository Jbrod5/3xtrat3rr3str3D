package org.jrg.model.ast.zetariano.asignacion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class AsignacionSimple extends NodoASTZetariano {

    private final NodoASTZetariano variable;
    private final NodoASTZetariano expresion;

    public AsignacionSimple(NodoASTZetariano variable, NodoASTZetariano expresion, int linea, int columna) {
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
        return visitor.visitarAsignacionSimple(this);
    }
}
