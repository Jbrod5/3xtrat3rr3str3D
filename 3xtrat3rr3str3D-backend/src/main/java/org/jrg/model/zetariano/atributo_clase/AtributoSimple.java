package org.jrg.model.zetariano.atributo_clase;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class AtributoSimple extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String identificador;

    public AtributoSimple(NodoASTZetariano tipo, String identificador, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.identificador = identificador;
    }

    public NodoASTZetariano getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarAtributoSimple(this);
    }
}
