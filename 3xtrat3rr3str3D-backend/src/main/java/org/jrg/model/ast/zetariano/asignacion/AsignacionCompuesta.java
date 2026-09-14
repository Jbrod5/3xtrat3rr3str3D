package org.jrg.model.ast.zetariano.asignacion;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class AsignacionCompuesta extends NodoASTZetariano {

    private final NodoASTZetariano variable;
    private final String operador;
    private final NodoASTZetariano expresion;

    public AsignacionCompuesta(NodoASTZetariano variable, String operador, NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
        this.operador = operador;
        this.expresion = expresion;
    }

    public NodoASTZetariano getVariable() {
        return variable;
    }

    public String getOperador() {
        return operador;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarAsignacionCompuesta(this);
    }
}
