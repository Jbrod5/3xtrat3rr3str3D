package org.jrg.model.ast.yLenguaje.ciclo;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class CicloPara extends NodoASTY {

    private final NodoASTY inicializacion;
    private final NodoASTY condicion;
    private final NodoASTY paso;
    private final NodoASTY bloque;

    /**
     * Crear un ciclo para con sus partes.
     */
    public CicloPara(NodoASTY inicializacion, NodoASTY condicion, NodoASTY paso, NodoASTY bloque, int linea, int columna) {
        super(linea, columna);
        this.inicializacion = inicializacion;
        this.condicion = condicion;
        this.paso = paso;
        this.bloque = bloque;
    }

    public NodoASTY getInicializacion() {
        return this.inicializacion;
    }

    public NodoASTY getCondicion() {
        return this.condicion;
    }

    public NodoASTY getPaso() {
        return this.paso;
    }

    public NodoASTY getBloque() {
        return this.bloque;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarCicloPara(this);
    }
}