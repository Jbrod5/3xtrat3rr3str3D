package org.jrg.model.ast.zetariano.miembro_clase;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class MiembroAtributo extends NodoASTZetariano {

    private final NodoASTZetariano atributo;

    public MiembroAtributo(NodoASTZetariano atributo, int linea, int columna) {
        super(linea, columna);
        this.atributo = atributo;
    }

    public NodoASTZetariano getAtributo() {
        return atributo;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarMiembroAtributo(this);
    }
}
