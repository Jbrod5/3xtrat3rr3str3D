package org.jrg.model.ast.yLenguaje.definicion_funcion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class DefFuncionConRetorno extends NodoASTY {

    private final String nombre;
    private final NodoASTY tipoRetorno;
    private final NodoASTY parametros;
    private final NodoASTY cuerpo;

    /**
     * Crear una definicion de funcion con retorno.
     */
    public DefFuncionConRetorno(String nombre, NodoASTY tipoRetorno, NodoASTY parametros, NodoASTY cuerpo, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.tipoRetorno = tipoRetorno;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoASTY getTipoRetorno() {
        return this.tipoRetorno;
    }

    public NodoASTY getParametros() {
        return this.parametros;
    }

    public NodoASTY getCuerpo() {
        return this.cuerpo;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDefFuncionConRetorno(this);
    }
}