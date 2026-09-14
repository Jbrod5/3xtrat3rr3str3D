package org.jrg.model.ast.pigLatin.declaracion_variable;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class DeclEstructuraConValores extends NodoAST {
    private final String identificador;
    private final String tipo;
    private final NodoAST atributos;

    public DeclEstructuraConValores(String identificador, String tipo, NodoAST atributos, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.tipo = tipo;
        this.atributos = atributos;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public NodoAST getAtributos() {
        return atributos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitDeclEstructuraConValores(this);
    }
}
