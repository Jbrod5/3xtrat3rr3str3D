package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;
import org.jrg.model.base.TipoPrimitivo;

public class ValorPrimitivo extends NodoASTY {

    private final String valor;
    private final TipoPrimitivo tipo;

    /**
     * Crear un valor primitivo con su valor y tipo.
     */
    public ValorPrimitivo(String valor, TipoPrimitivo tipo, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getValor() {
        return this.valor;
    }

    public TipoPrimitivo getTipo() {
        return this.tipo;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarValorPrimitivo(this);
    }
}