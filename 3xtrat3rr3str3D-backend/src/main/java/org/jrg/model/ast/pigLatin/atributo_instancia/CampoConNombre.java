package org.jrg.model.ast.pigLatin.atributo_instancia;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class CampoConNombre extends NodoAST {
    private final String nombre;
    private final NodoAST valor;

    public CampoConNombre(String nombre, NodoAST valor, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoAST getValor() {
        return valor;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitCampoConNombre(this);
    }
}
