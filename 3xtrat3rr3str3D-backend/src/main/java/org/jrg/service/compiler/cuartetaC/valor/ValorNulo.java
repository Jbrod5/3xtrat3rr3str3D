package org.jrg.service.compiler.cuartetaC.valor;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;

// traducir nulos y desconocidos a su forma en C
public class ValorNulo extends ValorC {

    /**
     * Crear un valor nulo con su texto original.
     */
    public ValorNulo(String texto) {
        // usar el constructor base
        super(texto);
    }

    /**
     * Obtener la forma en codigo C del operando.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // traducir el desconocido a cero
        if ("_".equals(texto)) {
            return "0";
        }
        // traducir nulo a NULL
        return "NULL";
    }
}
