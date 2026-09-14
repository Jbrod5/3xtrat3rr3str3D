package org.jrg.model.ast.yLenguaje.parametro;

import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;

public class ParamEstructura extends NodoASTY {

    private final String tipoEstructura;
    private final String nombre;

    /**
     * Crear un parametro estructura con su tipo y nombre.
     */
    public ParamEstructura(String tipoEstructura, String nombre, int linea, int columna) {
        super(linea, columna);
        this.tipoEstructura = tipoEstructura;
        this.nombre = nombre;
    }

    public String getTipoEstructura() {
        return this.tipoEstructura;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public <T> T accept(YAstVisitor<T> visitor) {
        return visitor.visitarParamEstructura(this);
    }
}