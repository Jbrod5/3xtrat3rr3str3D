package org.jrg.model.ast.pigLatin;

import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;

import java.util.List;

public class SeccionImportaciones extends NodoAST {
    private final List<NodoAST> importaciones;

    public SeccionImportaciones(List<NodoAST> importaciones, int linea, int columna) {
        super(linea, columna);
        this.importaciones = importaciones;
    }

    public List<NodoAST> getImportaciones() {
        return importaciones;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitSeccionImportaciones(this);
    }
}
