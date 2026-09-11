package org.jrg.model.zetariano.expresion;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprTernario extends NodoASTZetariano {

    private final NodoASTZetariano condicion;
    private final NodoASTZetariano valorVerdadero;
    private final NodoASTZetariano valorFalso;

    public ExprTernario(NodoASTZetariano condicion, NodoASTZetariano valorVerdadero, NodoASTZetariano valorFalso, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.valorVerdadero = valorVerdadero;
        this.valorFalso = valorFalso;
    }

    public NodoASTZetariano getCondicion() {
        return condicion;
    }

    public NodoASTZetariano getValorVerdadero() {
        return valorVerdadero;
    }

    public NodoASTZetariano getValorFalso() {
        return valorFalso;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprTernario(this);
    }
}
