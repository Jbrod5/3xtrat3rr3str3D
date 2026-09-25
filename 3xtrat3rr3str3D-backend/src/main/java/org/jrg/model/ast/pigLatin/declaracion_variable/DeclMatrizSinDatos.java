package org.jrg.model.ast.pigLatin.declaracion_variable;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class DeclMatrizSinDatos extends NodoAST {
    private final String identificador;
    private final NodoAST tamanoFilas;
    private final NodoAST tamanoColumnas;
    private final NodoAST tipo;

    public DeclMatrizSinDatos(String identificador, NodoAST tamanoFilas, NodoAST tamanoColumnas, NodoAST tipo, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.tamanoFilas = tamanoFilas;
        this.tamanoColumnas = tamanoColumnas;
        this.tipo = tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public NodoAST getTamanoFilas() {
        return tamanoFilas;
    }

    public NodoAST getTamanoColumnas() {
        return tamanoColumnas;
    }

    public NodoAST getTipo() {
        return tipo;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitDeclMatrizSinDatos(this);
    }
}
