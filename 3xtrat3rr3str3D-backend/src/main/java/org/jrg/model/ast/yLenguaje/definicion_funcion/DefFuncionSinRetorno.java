package org.jrg.model.ast.yLenguaje.definicion_funcion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class DefFuncionSinRetorno extends NodoASTY {

    private final String nombre;
    private final NodoASTY parametros;
    private final NodoASTY cuerpo;

    /**
     * Crear una definicion de funcion sin retorno.
     */
    public DefFuncionSinRetorno(String nombre, NodoASTY parametros, NodoASTY cuerpo, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoASTY getParametros() {
        return this.parametros;
    }

    public NodoASTY getCuerpo() {
        return this.cuerpo;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDefFuncionSinRetorno(this);
    }
}