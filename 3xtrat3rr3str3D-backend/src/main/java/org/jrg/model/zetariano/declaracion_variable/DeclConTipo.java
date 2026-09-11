package org.jrg.model.zetariano.declaracion_variable;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class DeclConTipo extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String identificador;
    private final int dimensiones;
    private final NodoASTZetariano valor;

    public DeclConTipo(NodoASTZetariano tipo, String identificador, int dimensiones, NodoASTZetariano valor, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.identificador = identificador;
        this.dimensiones = dimensiones;
        this.valor = valor;
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

    public NodoASTZetariano getValor() {
        return valor;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarDeclConTipo(this);
    }
}
