package org.jrg.model.pigLatin.expresion;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class ExprLlamadaMetodo extends NodoAST {
    private final NodoAST objeto;
    private final String nombre;
    private final NodoAST argumentos;

    public ExprLlamadaMetodo(NodoAST objeto, String nombre, NodoAST argumentos, int linea, int columna) {
        super(linea, columna);
        this.objeto = objeto;
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    public NodoAST getObjeto() {
        return objeto;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoAST getArgumentos() {
        return argumentos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprLlamadaMetodo(this);
    }
}
