package org.jrg.model.ast.yLenguaje.expresion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class ExprLlamadaFuncion extends NodoASTY {

    private final String nombre;
    private final List<NodoASTY> argumentos;

    /**
     * Crear una expresion de llamada a funcion.
     */
    public ExprLlamadaFuncion(String nombre, List<NodoASTY> argumentos, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.argumentos = argumentos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public List<NodoASTY> getArgumentos() {
        return this.argumentos;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarExprLlamadaFuncion(this);
    }
}