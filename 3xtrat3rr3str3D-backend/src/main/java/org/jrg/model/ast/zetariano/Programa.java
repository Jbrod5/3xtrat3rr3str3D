package org.jrg.model.ast.zetariano;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class Programa extends NodoASTZetariano {

    private final NodoASTZetariano definicionClase;

    public Programa(NodoASTZetariano definicionClase, int linea, int columna) {
        super(linea, columna);
        this.definicionClase = definicionClase;
    }

    public NodoASTZetariano getDefinicionClase() {
        return definicionClase;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarPrograma(this);
    }
}
