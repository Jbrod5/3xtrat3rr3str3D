package org.jrg.service.compiler.cuartetaC.valor;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;

// traducir una constante booleana de los tres lenguajes a C
public class ValorBooleano extends ValorC {

    /**
     * Crear un valor booleano con su texto original.
     */
    public ValorBooleano(String texto) {
        // delegar al constructor de la clase base
        super(texto);
    }

    /**
     * Obtener la forma en codigo C del operando.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // traducir constantes verdaderas de los tres lenguajes
        if ("verum".equals(texto) || "verdadero".equals(texto) || "true".equals(texto)) {
            return "true";
        }
        // traducir constantes falsas de los tres lenguajes
        return "false";
    }
}
