package org.jrg.model.ast.pigLatin.variable_asignable;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;

public class ValorAsignableMiembroEstructura extends NodoAST {
    private final NodoAST base;
    private final String miembro;

    public ValorAsignableMiembroEstructura(NodoAST base, String miembro, int linea, int columna) {
        super(linea, columna);
        this.base = base;
        this.miembro = miembro;
    }

    public NodoAST getBase() {
        return base;
    }

    public String getMiembro() {
        return miembro;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitValorAsignableMiembroEstructura(this);
    }
}
