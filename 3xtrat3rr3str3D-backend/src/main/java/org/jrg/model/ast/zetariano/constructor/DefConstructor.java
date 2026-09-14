package org.jrg.model.ast.zetariano.constructor;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class DefConstructor extends NodoASTZetariano {

    private final String nombre;
    private final NodoASTZetariano parametros;
    private final List<NodoASTZetariano> instrucciones;

    public DefConstructor(String nombre, NodoASTZetariano parametros, List<NodoASTZetariano> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.parametros = parametros;
        this.instrucciones = new ArrayList<>();
        if (instrucciones != null) {
            this.instrucciones.addAll(instrucciones);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public NodoASTZetariano getParametros() {
        return parametros;
    }

    public List<NodoASTZetariano> getInstrucciones() {
        return instrucciones;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarDefConstructor(this);
    }
}
