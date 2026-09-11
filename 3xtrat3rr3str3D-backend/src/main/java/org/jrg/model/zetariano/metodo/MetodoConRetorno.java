package org.jrg.model.zetariano.metodo;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.zetariano.base.NodoASTZetariano;
import org.jrg.model.zetariano.base.ZetarianoAstVisitor;

public class MetodoConRetorno extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String nombre;
    private final NodoASTZetariano parametros;
    private final List<NodoASTZetariano> instrucciones;

    public MetodoConRetorno(NodoASTZetariano tipo, String nombre, NodoASTZetariano parametros, List<NodoASTZetariano> instrucciones, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.nombre = nombre;
        this.parametros = parametros;
        this.instrucciones = new ArrayList<>();
        if (instrucciones != null) {
            this.instrucciones.addAll(instrucciones);
        }
    }

    public NodoASTZetariano getTipo() {
        return tipo;
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
        return visitor.visitarMetodoConRetorno(this);
    }
}
