package org.jrg.model.ast.pigLatin;

import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;

public class Programa extends NodoAST {
    private final NodoAST seccionImportaciones;
    private final NodoAST seccionGlobalVariables;
    private final NodoAST seccionMaior;

    public Programa(NodoAST seccionImportaciones, NodoAST seccionGlobalVariables, NodoAST seccionMaior, int linea, int columna) {
        super(linea, columna);
        this.seccionImportaciones = seccionImportaciones;
        this.seccionGlobalVariables = seccionGlobalVariables;
        this.seccionMaior = seccionMaior;
    }

    public NodoAST getSeccionImportaciones() {
        return seccionImportaciones;
    }

    public NodoAST getSeccionGlobalVariables() {
        return seccionGlobalVariables;
    }

    public NodoAST getSeccionMaior() {
        return seccionMaior;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitPrograma(this);
    }
}
