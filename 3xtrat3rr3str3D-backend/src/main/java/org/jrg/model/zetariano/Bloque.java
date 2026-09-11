package org.jrg.model.zetariano;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class Bloque extends NodoASTZetariano {

    private final List<NodoASTZetariano> instrucciones;

    public Bloque(List<NodoASTZetariano> instrucciones, int linea, int columna) {
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
        return visitor.visitarBloque(this);
    }
}
