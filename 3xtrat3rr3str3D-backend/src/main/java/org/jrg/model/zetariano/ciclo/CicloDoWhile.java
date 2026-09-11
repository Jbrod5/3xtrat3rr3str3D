package org.jrg.model.zetariano.ciclo;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class CicloDoWhile extends NodoASTZetariano {

    private final NodoASTZetariano bloque;
    private final NodoASTZetariano condicion;

    public CicloDoWhile(NodoASTZetariano bloque, NodoASTZetariano condicion, int linea, int columna) {
        super(linea, columna);
        this.bloque = bloque;
        this.condicion = condicion;
    }

    public NodoASTZetariano getBloque() {
        return bloque;
    }

    public NodoASTZetariano getCondicion() {
        return condicion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarCicloDoWhile(this);
    }
}
