package org.jrg.model.pigLatin.declaracion_variable;

import java.util.List;
import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class DeclArrayConDatos extends NodoAST {
    private final String identificador;
    private final NodoAST tamano;
    private final NodoAST tipo;
    private final List<NodoAST> valores;

    public DeclArrayConDatos(String identificador, NodoAST tamano, NodoAST tipo, List<NodoAST> valores, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.tamano = tamano;
        this.tipo = tipo;
        this.valores = valores;
    }

    public String getIdentificador() {
        return identificador;
    }

    public NodoAST getTamano() {
        return tamano;
    }

    public NodoAST getTipo() {
        return tipo;
    }

    public List<NodoAST> getValores() {
        return valores;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitDeclArrayConDatos(this);
    }
}
