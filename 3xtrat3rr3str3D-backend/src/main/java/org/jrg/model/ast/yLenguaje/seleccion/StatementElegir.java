package org.jrg.model.ast.yLenguaje.seleccion;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class StatementElegir extends NodoASTY {

    private final NodoASTY expresion;
    private final List<NodoASTY> casos;
    private final NodoASTY casoDefecto;

    /**
     * Crear una seleccion elegir con sus casos.
     */
    public StatementElegir(NodoASTY expresion, List<NodoASTY> casos, NodoASTY casoDefecto, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
        this.casos = casos;
        this.casoDefecto = casoDefecto;
    }

    public NodoASTY getExpresion() {
        return this.expresion;
    }

    public List<NodoASTY> getCasos() {
        return this.casos;
    }

    public NodoASTY getCasoDefecto() {
        return this.casoDefecto;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStatementElegir(this);
    }
}