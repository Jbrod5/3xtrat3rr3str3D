package org.jrg.model.ast.yLenguaje;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class CasoSeleccion extends NodoASTY {

    private final NodoASTY valor;
    private final List<NodoASTY> instrucciones;

    /**
     * Crear un caso de seleccion con su valor e instrucciones.
     */
    public CasoSeleccion(NodoASTY valor, List<NodoASTY> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
        this.instrucciones = instrucciones;
    }

    public NodoASTY getValor() {
        return this.valor;
    }

    public List<NodoASTY> getInstrucciones() {
        return this.instrucciones;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarCasoSeleccion(this);
    }
}