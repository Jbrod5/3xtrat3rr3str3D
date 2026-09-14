package org.jrg.model.ast.zetariano.expresion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class ExprInstanciaObjeto extends NodoASTZetariano {

    private final String nombreClase;
    private final NodoASTZetariano argumentos;

    public ExprInstanciaObjeto(String nombreClase, NodoASTZetariano argumentos, int linea, int columna) {
        super(linea, columna);
        this.nombreClase = nombreClase;
        this.argumentos = argumentos;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public NodoASTZetariano getArgumentos() {
        return argumentos;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprInstanciaObjeto(this);
    }
}
