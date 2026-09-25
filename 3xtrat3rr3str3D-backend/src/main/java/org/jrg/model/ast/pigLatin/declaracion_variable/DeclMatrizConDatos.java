package org.jrg.model.ast.pigLatin.declaracion_variable;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class DeclMatrizConDatos extends NodoAST {
    private final String identificador;
    private final NodoAST tamanoFilas;
    private final NodoAST tamanoColumnas;
    private final NodoAST tipo;
    private final List<List<NodoAST>> filas;

    public DeclMatrizConDatos(String identificador, NodoAST tamanoFilas, NodoAST tamanoColumnas, NodoAST tipo, List<List<NodoAST>> filas, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.tamanoFilas = tamanoFilas;
        this.tamanoColumnas = tamanoColumnas;
        this.tipo = tipo;
        this.filas = new ArrayList<>();
        // copiar filas si existen
        if (filas != null) {
            for (int i = 0; i < filas.size(); i++) {
                List<NodoAST> fila = new ArrayList<>();
                if (filas.get(i) != null) {
                    fila.addAll(filas.get(i));
                }
                this.filas.add(fila);
            }
        }
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

    public List<List<NodoAST>> getFilas() {
        return filas;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitDeclMatrizConDatos(this);
    }
}
