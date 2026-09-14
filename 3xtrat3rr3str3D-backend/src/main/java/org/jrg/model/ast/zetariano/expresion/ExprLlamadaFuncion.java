package org.jrg.model.ast.zetariano.expresion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class ExprLlamadaFuncion extends NodoASTZetariano {

    private final String nombre;
    private final NodoASTZetariano argumentos;

    public ExprLlamadaFuncion(String nombre, NodoASTZetariano argumentos, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoASTZetariano getArgumentos() {
        return argumentos;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprLlamadaFuncion(this);
    }
}
