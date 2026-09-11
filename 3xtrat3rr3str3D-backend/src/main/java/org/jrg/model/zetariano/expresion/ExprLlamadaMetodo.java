package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprLlamadaMetodo extends NodoASTZetariano {

    private final NodoASTZetariano objeto;
    private final String nombre;
    private final NodoASTZetariano argumentos;

    public ExprLlamadaMetodo(NodoASTZetariano objeto, String nombre, NodoASTZetariano argumentos, int linea, int columna) {
        super(linea, columna);
        this.objeto = objeto;
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    public NodoASTZetariano getObjeto() {
        return objeto;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoASTZetariano getArgumentos() {
        return argumentos;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprLlamadaMetodo(this);
    }
}
