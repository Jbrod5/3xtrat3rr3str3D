package org.jrg.model.zetariano.ciclo;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class CicloFor extends NodoASTZetariano {

    private final NodoASTZetariano inicializacion;
    private final NodoASTZetariano condicion;
    private final NodoASTZetariano paso;
    private final NodoASTZetariano bloque;

    public CicloFor(NodoASTZetariano inicializacion, NodoASTZetariano condicion, NodoASTZetariano paso, NodoASTZetariano bloque, int linea, int columna) {
        super(linea, columna);
        this.inicializacion = inicializacion;
        this.condicion = condicion;
        this.paso = paso;
        this.bloque = bloque;
    }

    public NodoASTZetariano getInicializacion() {
        return inicializacion;
    }

    public NodoASTZetariano getCondicion() {
        return condicion;
    }

    public NodoASTZetariano getPaso() {
        return paso;
    }

    public NodoASTZetariano getBloque() {
        return bloque;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarCicloFor(this);
    }
}
