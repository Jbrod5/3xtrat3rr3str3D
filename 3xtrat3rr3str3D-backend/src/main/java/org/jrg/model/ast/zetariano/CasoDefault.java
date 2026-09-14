package org.jrg.model.ast.zetariano;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class CasoDefault extends NodoASTZetariano {

    private final List<NodoASTZetariano> instrucciones;

    public CasoDefault(List<NodoASTZetariano> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.instrucciones = new ArrayList<>();
        if (instrucciones != null) {
            this.instrucciones.addAll(instrucciones);
        }
    }

    public List<NodoASTZetariano> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarCasoDefault(this);
    }
}
