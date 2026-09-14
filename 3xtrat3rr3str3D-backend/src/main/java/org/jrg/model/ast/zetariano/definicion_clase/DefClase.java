package org.jrg.model.ast.zetariano.definicion_clase;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class DefClase extends NodoASTZetariano {

    private final String nombre;
    private final List<NodoASTZetariano> miembros;

    public DefClase(String nombre, List<NodoASTZetariano> miembros, int linea, int columna) {
        super(linea, columna);
        this.nombre = nombre;
        this.miembros = new ArrayList<>();
        if (miembros != null) {
            this.miembros.addAll(miembros);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<NodoASTZetariano> getMiembros() {
        return miembros;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarDefClase(this);
    }
}
