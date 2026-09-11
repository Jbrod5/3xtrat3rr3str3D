package org.jrg.model.pigLatin.declaracion_variable;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class DeclArrayEstructura extends NodoAST {
    private final String identificador;
    private final NodoAST tamano;
    private final String tipo;

    public DeclArrayEstructura(String identificador, NodoAST tamano, String tipo, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.tamano = tamano;
        this.tipo = tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public NodoAST getTamano() {
        return tamano;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitDeclArrayEstructura(this);
    }
}
