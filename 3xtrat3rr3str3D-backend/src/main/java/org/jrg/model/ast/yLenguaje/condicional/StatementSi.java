package org.jrg.model.ast.yLenguaje.condicional;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

import java.util.List;

public class StatementSi extends NodoASTY {

    private final NodoASTY condicionPrincipal;
    private final NodoASTY bloquePrincipal;
    private final List<NodoASTY> condicionesSino;
    private final List<NodoASTY> bloquesSino;
    private final NodoASTY bloqueContrario;

    /**
     * Crear una condicional si con sus partes.
     */
    public StatementSi(NodoASTY condicionPrincipal, NodoASTY bloquePrincipal, List<NodoASTY> condicionesSino, List<NodoASTY> bloquesSino, NodoASTY bloqueContrario, int linea, int columna) {
        super(linea, columna);
        this.condicionPrincipal = condicionPrincipal;
        this.bloquePrincipal = bloquePrincipal;
        this.condicionesSino = condicionesSino;
        this.bloquesSino = bloquesSino;
        this.bloqueContrario = bloqueContrario;
    }

    public NodoASTY getCondicionPrincipal() {
        return this.condicionPrincipal;
    }

    public NodoASTY getBloquePrincipal() {
        return this.bloquePrincipal;
    }

    public List<NodoASTY> getCondicionesSino() {
        return this.condicionesSino;
    }

    public List<NodoASTY> getBloquesSino() {
        return this.bloquesSino;
    }

    public NodoASTY getBloqueContrario() {
        return this.bloqueContrario;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarStatementSi(this);
    }
}