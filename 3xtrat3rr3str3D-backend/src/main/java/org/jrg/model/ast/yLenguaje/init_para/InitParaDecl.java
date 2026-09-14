package org.jrg.model.ast.yLenguaje.init_para;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class InitParaDecl extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;
    private final NodoASTY expresion;

    /**
     * Crear una inicializacion de para con declaracion.
     */
    public InitParaDecl(NodoASTY tipo, String nombre, NodoASTY expresion, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.expresion = expresion;
    }

    public NodoASTY getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoASTY getExpresion() {
        return this.expresion;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarInitParaDecl(this);
    }
}