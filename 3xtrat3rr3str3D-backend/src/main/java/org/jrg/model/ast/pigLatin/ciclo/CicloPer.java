package org.jrg.model.ast.pigLatin.ciclo;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class CicloPer extends NodoAST {
    private final NodoAST inicializacion;
    private final NodoAST condicion;
    private final NodoAST paso;
    private final NodoAST bloque;

    public CicloPer(NodoAST inicializacion, NodoAST condicion, NodoAST paso, NodoAST bloque, int linea, int columna) {
        super(linea, columna);
        this.inicializacion = inicializacion;
        this.condicion = condicion;
        this.paso = paso;
        this.bloque = bloque;
    }

    public NodoAST getInicializacion() {
        return inicializacion;
    }

    public NodoAST getCondicion() {
        return condicion;
    }

    public NodoAST getPaso() {
        return paso;
    }

    public NodoAST getBloque() {
        return bloque;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitCicloPer(this);
    }
}
