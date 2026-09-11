package org.jrg.model.pigLatin.expresion;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ExprInstanciaObjeto extends NodoAST {
    private final String tipo;
    private final NodoAST argumentos;

    public ExprInstanciaObjeto(String tipo, NodoAST argumentos, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.argumentos = argumentos;
    }

    public String getTipo() {
        return tipo;
    }

    public NodoAST getArgumentos() {
        return argumentos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprInstanciaObjeto(this);
    }
}
