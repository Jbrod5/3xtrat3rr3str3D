package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class CuerpoFuncion extends NodoASTY {

    private final List<NodoASTY> instrucciones;

    /**
     * Crear un cuerpo de funcion con su lista de instrucciones.
     */
    public CuerpoFuncion(List<NodoASTY> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.instrucciones = instrucciones;
    }

    public List<NodoASTY> getInstrucciones() {
        return this.instrucciones;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarCuerpoFuncion(this);
    }
}