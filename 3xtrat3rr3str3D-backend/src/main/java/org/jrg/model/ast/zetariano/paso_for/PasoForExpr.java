package org.jrg.model.ast.zetariano.paso_for;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class PasoForExpr extends NodoASTZetariano {

    private final NodoASTZetariano expresion;

    public PasoForExpr(NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarPasoForExpr(this);
    }
}
