package org.jrg.model.ast.yLenguaje.declaracion_variable;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class DeclMatrizConValores extends NodoASTY {

    private final NodoASTY tipo;
    private final String nombre;
    private final NodoASTY tamanoFilas;
    private final NodoASTY tamanoColumnas;
    private final List<List<NodoASTY>> filas;

    /**
     * Crear una declaracion de matriz con valores iniciales por filas.
     */
    public DeclMatrizConValores(NodoASTY tipo, String nombre, NodoASTY tamanoFilas, NodoASTY tamanoColumnas, List<List<NodoASTY>> filas, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.tamanoFilas = tamanoFilas;
        this.tamanoColumnas = tamanoColumnas;
        this.filas = new ArrayList<>();
        // copiar filas si existen
        if (filas != null) {
            for (int i = 0; i < filas.size(); i++) {
                List<NodoASTY> fila = new ArrayList<>();
                if (filas.get(i) != null) {
                    fila.addAll(filas.get(i));
                }
                this.filas.add(fila);
            }
        }
    }

    public NodoASTY getTipo() {
        return this.tipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoASTY getTamanoFilas() {
        return this.tamanoFilas;
    }

    public NodoASTY getTamanoColumnas() {
        return this.tamanoColumnas;
    }

    public List<List<NodoASTY>> getFilas() {
        return this.filas;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarDeclMatrizConValores(this);
    }
}
