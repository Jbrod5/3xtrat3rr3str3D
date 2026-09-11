package org.jrg.model.pigLatin.declaracion_variable;

import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.pigLatin.base.LatinusAstVisitor;

public class DeclObjetoNovus extends NodoAST {
    private final String identificador;
    private final String tipo;
    private final NodoAST argumentos;

    public DeclObjetoNovus(String identificador, String tipo, NodoAST argumentos, int linea, int columna) {
        super(linea, columna);
        this.identificador = identificador;
        this.tipo = tipo;
        this.argumentos = argumentos;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTipo() {
        return tipo;
    }

    public NodoAST getArgumentos() {
        return argumentos;
    }

    @Override
    public <T> T accept(LatinusAstVisitor<T> visitor) {
        return visitor.visitDeclObjetoNovus(this);
    }
}
