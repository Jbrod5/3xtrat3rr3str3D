package org.jrg.model.ast.pigLatin.asignacion;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class AsignacionGeneral extends NodoAST {
    private final NodoAST variable;
    private final String operador;
    private final NodoAST valor;

    public AsignacionGeneral(NodoAST variable, String operador, NodoAST valor, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
        this.operador = operador;
        this.valor = valor;
    }

    public NodoAST getVariable() {
        return variable;
    }

    public String getOperador() {
        return operador;
    }

    public NodoAST getValor() {
        return valor;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitAsignacionGeneral(this);
    }
}
