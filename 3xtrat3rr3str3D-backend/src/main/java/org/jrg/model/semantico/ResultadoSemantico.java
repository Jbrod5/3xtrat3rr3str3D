package org.jrg.model.semantico;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.error.ErrorCompilacion;

public class ResultadoSemantico {

    private final boolean exito;
    private final List<ErrorCompilacion> errores;
    private final List<SimboloResultado> simbolos;
    private final List<TipoResultado> tipos;

    /**
     * Crear un resultado semantico con errores simbolos y tipos.
     */
    public ResultadoSemantico(List<ErrorCompilacion> errores, List<Simbolo> simbolos, List<Tipo> tipos) {
        this.errores = new ArrayList<>();
        if (errores != null) {
            this.errores.addAll(errores);
        }
        this.simbolos = new ArrayList<>();
        if (simbolos != null) {
            for (Simbolo simbolo : simbolos) {
                this.simbolos.add(new SimboloResultado(simbolo));
            }
        }
        this.tipos = new ArrayList<>();
        if (tipos != null) {
            for (Tipo tipo : tipos) {
                this.tipos.add(new TipoResultado(tipo));
            }
        }
        this.exito = this.errores.isEmpty();
    }

    /**
     * Verificar si el analisis no registro errores.
     */
    public boolean isExito() {
        return exito;
    }

    /**
     * Obtener los errores semanticos.
     */
    public List<ErrorCompilacion> getErrores() {
        return errores;
    }

    /**
     * Obtener los simbolos serializables.
     */
    public List<SimboloResultado> getSimbolos() {
        return simbolos;
    }

    /**
     * Obtener los tipos serializables.
     */
    public List<TipoResultado> getTipos() {
        return tipos;
    }
}
