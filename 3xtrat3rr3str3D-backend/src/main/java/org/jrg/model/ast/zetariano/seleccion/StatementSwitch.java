package org.jrg.model.ast.zetariano.seleccion;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class StatementSwitch extends NodoASTZetariano {

    private final NodoASTZetariano expresion;
    private final List<NodoASTZetariano> casos;
    private final NodoASTZetariano casoDefecto;

    public StatementSwitch(NodoASTZetariano expresion, List<NodoASTZetariano> casos, NodoASTZetariano casoDefecto, int linea, int columna) {
        super(linea, columna);
        this.expresion = expresion;
        this.casos = new ArrayList<>();
        if (casos != null) {
            this.casos.addAll(casos);
        }
        this.casoDefecto = casoDefecto;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    public List<NodoASTZetariano> getCasos() {
        return casos;
    }

    public NodoASTZetariano getCasoDefecto() {
        return casoDefecto;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarStatementSwitch(this);
    }
}
