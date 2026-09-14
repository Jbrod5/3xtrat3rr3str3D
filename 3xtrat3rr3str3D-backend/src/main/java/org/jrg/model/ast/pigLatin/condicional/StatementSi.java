package org.jrg.model.ast.pigLatin.condicional;

import java.util.List;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class StatementSi extends NodoAST {
    private final NodoAST condicion;
    private final NodoAST bloque;
    private final List<NodoAST> condicionesAliter;
    private final List<NodoAST> bloquesAliter;
    private final NodoAST bloqueAliter;

    public StatementSi(NodoAST condicion, NodoAST bloque, List<NodoAST> condicionesAliter, List<NodoAST> bloquesAliter, NodoAST bloqueAliter, int linea, int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloque = bloque;
        this.condicionesAliter = condicionesAliter;
        this.bloquesAliter = bloquesAliter;
        this.bloqueAliter = bloqueAliter;
    }

    public NodoAST getCondicion() {
        return condicion;
    }

    public NodoAST getBloque() {
        return bloque;
    }

    public List<NodoAST> getCondicionesAliter() {
        return condicionesAliter;
    }

    public List<NodoAST> getBloquesAliter() {
        return bloquesAliter;
    }

    public NodoAST getBloqueAliter() {
        return bloqueAliter;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitStatementSi(this);
    }
}
