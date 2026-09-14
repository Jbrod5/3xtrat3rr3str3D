package org.jrg.model.ast.yLenguaje.ciclo;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class CicloHacer extends NodoASTY {

    private final NodoASTY bloque;
    private final NodoASTY condicion;

    /**
     * Crear un ciclo hacer con su bloque y condicion.
     */
    public CicloHacer(NodoASTY bloque, NodoASTY condicion, int linea, int columna) {
        super(linea, columna);
        this.bloque = bloque;
        this.condicion = condicion;
    }

    public NodoASTY getBloque() {
        return this.bloque;
    }

    public NodoASTY getCondicion() {
        return this.condicion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarCicloHacer(this);
    }
}