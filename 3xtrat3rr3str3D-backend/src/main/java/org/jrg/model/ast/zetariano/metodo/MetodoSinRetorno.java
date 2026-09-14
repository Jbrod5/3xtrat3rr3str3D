package org.jrg.model.ast.zetariano.metodo;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class MetodoSinRetorno extends NodoASTZetariano {

    private final String nombre;
    private final NodoASTZetariano parametros;
    private final List<NodoASTZetariano> instrucciones;

    public MetodoSinRetorno(String nombre, NodoASTZetariano parametros, List<NodoASTZetariano> instrucciones, int linea, int columna) {
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
        return visitor.visitarMetodoSinRetorno(this);
    }
}
