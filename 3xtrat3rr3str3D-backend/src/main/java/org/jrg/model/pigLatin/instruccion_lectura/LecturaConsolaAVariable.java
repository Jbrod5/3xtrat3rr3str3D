package org.jrg.model.pigLatin.instruccion_lectura;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class LecturaConsolaAVariable extends NodoAST {
    private final NodoAST variable;

    public LecturaConsolaAVariable(NodoAST variable, int linea, int columna) {
        super(linea, columna);
        this.variable = variable;
    }

    public NodoAST getVariable() {
        return variable;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitLecturaConsolaAVariable(this);
    }
}
