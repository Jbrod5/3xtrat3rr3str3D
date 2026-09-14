package org.jrg.model.ast.zetariano.declaracion_variable;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class DeclConListaLiteral extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String identificador;
    private final int dimensiones;
    private final NodoASTZetariano listaExpresiones;

    public DeclConListaLiteral(NodoASTZetariano tipo, String identificador, int dimensiones, NodoASTZetariano listaExpresiones, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.identificador = identificador;
        this.dimensiones = dimensiones;
        this.listaExpresiones = listaExpresiones;
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

    public NodoASTZetariano getListaExpresiones() {
        return listaExpresiones;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarDeclConListaLiteral(this);
    }
}
