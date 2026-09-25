package org.jrg.model.ast.zetariano.declaracion_variable;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class DeclConMatrizLiteral extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String identificador;
    private final int dimensiones;
    private final List<Object> valores;

    /**
     * Crear una declaracion de matriz con valores anidados por niveles.
     */
    public DeclConMatrizLiteral(NodoASTZetariano tipo, String identificador, int dimensiones, List<Object> valores, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.identificador = identificador;
        this.dimensiones = dimensiones;
        this.valores = new ArrayList<>();
        // copiar valores si existen
        if (valores != null) {
            this.valores.addAll(valores);
        }
    }

    public NodoASTZetariano getTipo() {
        return this.tipo;
    }

    public String getIdentificador() {
        return this.identificador;
    }

    public int getDimensiones() {
        return this.dimensiones;
    }

    public List<Object> getValores() {
        return this.valores;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarDeclConMatrizLiteral(this);
    }
}
