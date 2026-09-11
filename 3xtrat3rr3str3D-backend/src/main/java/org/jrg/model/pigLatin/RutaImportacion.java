package org.jrg.model.pigLatin;

import org.jrg.model.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.pigLatin.base.NodoAST;

import java.util.List;

public class RutaImportacion extends NodoAST {
    private final List<String> identificadores;

    public RutaImportacion(List<String> identificadores, int linea, int columna) {
        super(linea, columna);
        this.identificadores = identificadores;
    }

    public List<String> getIdentificadores() {
        return identificadores;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitRutaImportacion(this);
    }
}
