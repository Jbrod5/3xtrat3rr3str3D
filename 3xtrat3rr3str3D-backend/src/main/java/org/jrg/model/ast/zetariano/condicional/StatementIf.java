package org.jrg.model.ast.zetariano.condicional;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class StatementIf extends NodoASTZetariano {

    private final NodoASTZetariano condicion;
    private final NodoASTZetariano bloque;
    private final List<NodoASTZetariano> condicionesSinoSi;
    private final List<NodoASTZetariano> bloquesSinoSi;
    private final NodoASTZetariano bloqueSino;

    public StatementIf(
            NodoASTZetariano condicion,
            NodoASTZetariano bloque,
            List<NodoASTZetariano> condicionesSinoSi,
            List<NodoASTZetariano> bloquesSinoSi,
            NodoASTZetariano bloqueSino,
            int linea,
            int columna) {
        super(linea, columna);
        this.condicion = condicion;
        this.bloque = bloque;
        this.condicionesSinoSi = new ArrayList<>();
        if (condicionesSinoSi != null) {
            this.condicionesSinoSi.addAll(condicionesSinoSi);
        }
        this.bloquesSinoSi = new ArrayList<>();
        if (bloquesSinoSi != null) {
            this.bloquesSinoSi.addAll(bloquesSinoSi);
        }
        this.bloqueSino = bloqueSino;
    }

    public NodoASTZetariano getCondicion() {
        return condicion;
    }

    public NodoASTZetariano getBloque() {
        return bloque;
    }

    public List<NodoASTZetariano> getCondicionesSinoSi() {
        return condicionesSinoSi;
    }

    public List<NodoASTZetariano> getBloquesSinoSi() {
        return bloquesSinoSi;
    }

    public NodoASTZetariano getBloqueSino() {
        return bloqueSino;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStatementIf(this);
    }
}
