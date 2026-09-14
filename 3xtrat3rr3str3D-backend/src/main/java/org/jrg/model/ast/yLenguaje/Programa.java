package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class Programa extends NodoASTY {

    private final NodoASTY seccionEstructuras;
    private final NodoASTY seccionFunciones;

    /**
     * Crear un programa con sus secciones.
     */
    public Programa(NodoASTY seccionEstructuras, NodoASTY seccionFunciones, int linea, int columna) {
        super(linea, columna);
        this.seccionEstructuras = seccionEstructuras;
        this.seccionFunciones = seccionFunciones;
    }

    public NodoASTY getSeccionEstructuras() {
        return this.seccionEstructuras;
    }

    public NodoASTY getSeccionFunciones() {
        return this.seccionFunciones;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarPrograma(this);
    }
}