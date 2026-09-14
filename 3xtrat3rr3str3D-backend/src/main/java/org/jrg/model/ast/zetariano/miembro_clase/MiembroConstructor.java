package org.jrg.model.ast.zetariano.miembro_clase;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class MiembroConstructor extends NodoASTZetariano {

    private final NodoASTZetariano constructor;

    public MiembroConstructor(NodoASTZetariano constructor, int linea, int columna) {
        super(linea, columna);
        this.constructor = constructor;
    }

    public NodoASTZetariano getConstructor() {
        return constructor;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarMiembroConstructor(this);
    }
}
