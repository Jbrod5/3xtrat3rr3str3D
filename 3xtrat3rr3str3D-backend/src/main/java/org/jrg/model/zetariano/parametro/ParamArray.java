package org.jrg.model.zetariano.parametro;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ParamArray extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String identificador;
    private final int dimensiones;

    public ParamArray(NodoASTZetariano tipo, String identificador, int dimensiones, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.identificador = identificador;
        this.dimensiones = dimensiones;
    }

    public NodoASTZetariano getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getDimensiones() {
        return dimensiones;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarParamArray(this);
    }
}
