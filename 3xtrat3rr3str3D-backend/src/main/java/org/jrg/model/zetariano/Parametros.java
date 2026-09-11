package org.jrg.model.zetariano;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class Parametros extends NodoASTZetariano {

    private final List<NodoASTZetariano> parametros;

    public Parametros(List<NodoASTZetariano> parametros, int linea, int columna) {
        super(linea, columna);
        this.parametros = new ArrayList<>();
        if (parametros != null) {
            this.parametros.addAll(parametros);
        }
    }

    public List<NodoASTZetariano> getParametros() {
        return parametros;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarParametros(this);
    }
}
