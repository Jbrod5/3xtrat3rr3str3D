package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class Parametros extends NodoASTY {

    private final List<NodoASTY> parametros;

    /**
     * Crear una lista de parametros.
     */
    public Parametros(List<NodoASTY> parametros, int linea, int columna) {
        super(linea, columna);
        this.parametros = parametros;
    }

    public List<NodoASTY> getParametros() {
        return this.parametros;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarParametros(this);
    }
}