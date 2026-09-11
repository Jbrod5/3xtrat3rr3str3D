package org.jrg.model.zetariano;

import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class CasoSwitch extends NodoASTZetariano {

    private final NodoASTZetariano valor;
    private final java.util.List<NodoASTZetariano> instrucciones;

    public CasoSwitch(NodoASTZetariano valor, java.util.List<NodoASTZetariano> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.valor = valor;
        this.instrucciones = new java.util.ArrayList<>();
        if (instrucciones != null) {
            this.instrucciones.addAll(instrucciones);
        }
    }

    public NodoASTZetariano getValor() {
        return valor;
    }

    public java.util.List<NodoASTZetariano> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarCasoSwitch(this);
    }
}
