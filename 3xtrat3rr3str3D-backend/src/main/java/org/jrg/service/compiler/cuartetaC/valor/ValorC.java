package org.jrg.service.compiler.cuartetaC.valor;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;

// clase base abstracta para la traduccion de operandos a codigo C
public abstract class ValorC {

    // texto original del operando
    protected final String texto;

    /**
     * Crear un valor con su texto original.
     */
    public ValorC(String texto) {
        // asignar el texto recibido
        this.texto = texto;
    }

    /**
     * Obtener la forma en codigo C del operando.
     */
    public abstract String obtenerCodigoC(ContextoTraduccion ctx);

    /**
     * Obtener el texto original del operando.
     */
    public String getTexto() {
        return texto;
    }
}
