package org.jrg.model.pigLatin.declaracion_variable;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class DeclBooleanaImplicita extends NodoAST {
    private final String identificador;
    private final String valor;

    public DeclBooleanaImplicita(String identificador, String valor, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.valor = valor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitDeclBooleanaImplicita(this);
    }
}
