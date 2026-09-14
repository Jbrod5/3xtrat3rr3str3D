package org.jrg.model.ast.pigLatin.instruccion_lectura;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class LecturaConsolaSimple extends NodoAST {

    public LecturaConsolaSimple(int linea, int columna) {
        super(linea, columna);
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitLecturaConsolaSimple(this);
    }
}
