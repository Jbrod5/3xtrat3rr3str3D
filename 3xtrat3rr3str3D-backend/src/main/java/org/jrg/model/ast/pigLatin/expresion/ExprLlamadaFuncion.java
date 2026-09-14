package org.jrg.model.ast.pigLatin.expresion;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class ExprLlamadaFuncion extends NodoAST {
    private final String nombre;
    private final NodoAST argumentos;

    public ExprLlamadaFuncion(String nombre, NodoAST argumentos, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoAST getArgumentos() {
        return argumentos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitExprLlamadaFuncion(this);
    }
}
