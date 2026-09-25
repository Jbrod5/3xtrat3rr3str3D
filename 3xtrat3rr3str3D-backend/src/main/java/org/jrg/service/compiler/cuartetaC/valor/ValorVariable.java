package org.jrg.service.compiler.cuartetaC.valor;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;

// traducir un nombre leyendo campos de clase con this cuando aplica
public class ValorVariable extends ValorC {

    /**
     * Crear un valor variable con su texto original.
     */
    public ValorVariable(String texto) {
        // delegar al constructor de la clase base
        super(texto);
    }

    /**
     * Obtener la forma en codigo C del operando.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // leer campos de la clase actual con this
        return ctx.accesoCampo(texto);
    }
}
