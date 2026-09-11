package org.jrg.model.zetariano.parametro;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ParamSimple extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String identificador;

    public ParamSimple(NodoASTZetariano tipo, String identificador, int linea, int columna) {
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
        return visitor.visitarParamSimple(this);
    }
}
