package org.jrg.model.ast.zetariano.ciclo;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class CicloWhile extends NodoASTZetariano {

    private final NodoASTZetariano condicion;
    private final NodoASTZetariano bloque;

    public CicloWhile(NodoASTZetariano condicion, NodoASTZetariano bloque, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloque = bloque;
    }

    public NodoASTZetariano getCondicion() {
        return condicion;
    }

    public NodoASTZetariano getBloque() {
        return bloque;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarCicloWhile(this);
    }
}
