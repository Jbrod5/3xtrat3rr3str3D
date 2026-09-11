package org.jrg.model.zetariano.expresion;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class ExprInstanciaArreglo extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final List<NodoASTZetariano> dimensiones;

    public ExprInstanciaArreglo(NodoASTZetariano tipo, List<NodoASTZetariano> dimensiones, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.dimensiones = new ArrayList<>();
        if (dimensiones != null) {
            this.dimensiones.addAll(dimensiones);
        }
    }

    public NodoASTZetariano getTipo() {
        return tipo;
    }

    public List<NodoASTZetariano> getDimensiones() {
        return dimensiones;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarExprInstanciaArreglo(this);
    }
}
