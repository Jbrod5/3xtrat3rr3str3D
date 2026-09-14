package org.jrg.model.ast.yLenguaje.ciclo;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class CicloMientras extends NodoASTY {

    private final NodoASTY condicion;
    private final NodoASTY bloque;

    /**
     * Crear un ciclo mientras con su condicion y bloque.
     */
    public CicloMientras(NodoASTY condicion, NodoASTY bloque, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloque = bloque;
    }

    public NodoASTY getCondicion() {
        return this.condicion;
    }

    public NodoASTY getBloque() {
        return this.bloque;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarCicloMientras(this);
    }
}