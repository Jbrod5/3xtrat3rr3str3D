package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class SeccionEstructuras extends NodoASTY {

    private final List<NodoASTY> estructuras;

    /**
     * Crear una seccion de estructuras con su lista de definiciones.
     */
    public SeccionEstructuras(List<NodoASTY> estructuras, int linea, int columna) {
        super(linea, columna);
        this.estructuras = estructuras;
    }

    public List<NodoASTY> getEstructuras() {
        return this.estructuras;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarSeccionEstructuras(this);
    }
}