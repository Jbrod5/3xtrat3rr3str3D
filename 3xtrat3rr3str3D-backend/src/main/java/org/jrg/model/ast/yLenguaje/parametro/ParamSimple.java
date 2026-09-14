package org.jrg.model.ast.yLenguaje.parametro;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ParamSimple extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;

    /**
     * Crear un parametro simple con su tipo y nombre.
     */
    public ParamSimple(NodoASTY tipo, String nombre, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
    }

    public NodoASTY getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarParamSimple(this);
    }
}