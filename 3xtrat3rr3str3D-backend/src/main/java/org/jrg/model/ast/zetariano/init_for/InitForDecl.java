package org.jrg.model.ast.zetariano.init_for;

import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;

public class InitForDecl extends NodoASTZetariano {

    private final NodoASTZetariano tipo;
    private final String identificador;
    private final NodoASTZetariano expresion;

    public InitForDecl(NodoASTZetariano tipo, String identificador, NodoASTZetariano expresion, int linea, int columna) {
        super(linea, columna);
        this.tipo = tipo;
        this.identificador = identificador;
        this.expresion = expresion;
    }

    public NodoASTZetariano getTipo() {
        return tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public NodoASTZetariano getExpresion() {
        return expresion;
    }

    @Override
    public <T> T accept(ZetarianoAstVisitor<T> visitor) {
        return visitor.visitarInitForDecl(this);
    }
}
