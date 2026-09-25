package org.jrg.service.compiler.cuartetaC.valor;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;

// traducir un literal que se escribe tal cual en C
public class ValorLiteral extends ValorC {

    /**
     * Crear un valor literal con su texto original.
     */
    public ValorLiteral(String texto) {
        // delegar al constructor de la clase base
        super(texto);
    }

    /**
     * Obtener la forma en codigo C del operando.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // devolver el texto sin cambios
        return texto;
    }
}
