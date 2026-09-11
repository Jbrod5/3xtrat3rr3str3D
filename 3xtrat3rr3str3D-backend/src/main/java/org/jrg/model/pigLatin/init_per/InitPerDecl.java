package org.jrg.model.pigLatin.init_per;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class InitPerDecl extends NodoAST {
    private final String identificador;
    private final NodoAST tipo;
    private final String asignacion;
    private final NodoAST valor;

    public InitPerDecl(String identificador, NodoAST tipo, String asignacion, NodoAST valor, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.tipo = tipo;
        this.asignacion = asignacion;
        this.valor = valor;
    }

    public String getIdentificador() {
        return identificador;
    }

    public NodoAST getTipo() {
        return tipo;
    }

    public String getAsignacion() {
        return asignacion;
    }

    public NodoAST getValor() {
        return valor;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitInitPerDecl(this);
    }
}
