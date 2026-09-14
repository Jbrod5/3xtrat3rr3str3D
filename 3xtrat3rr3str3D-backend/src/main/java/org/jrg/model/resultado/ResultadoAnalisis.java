package org.jrg.model.resultado;

import java.util.ArrayList;
import java.util.List;
import org.jrg.model.error.ErrorCompilacion;
import org.jrg.model.semantico.SimboloResultado;
import org.jrg.model.semantico.TipoResultado;

/**
 * Representar el resultado completo del analisis de un archivo fuente.
 */
public class ResultadoAnalisis {

    private final boolean exito;
    private final List<ErrorCompilacion> errores;
    private final String arbolSintactico;
    private final String astMermaid;
    private final String codigoPigLatin;
    private final List<SimboloResultado> simbolos;
    private final List<TipoResultado> tipos;
    private final List<Object> pasosPila;

    /**
     * Crear un resultado de analisis con todos sus componentes.
     */
    public ResultadoAnalisis(
            boolean exito,
            List<ErrorCompilacion> errores,
            String arbolSintactico,
            String astMermaid,
            String codigoPigLatin,
            List<SimboloResultado> simbolos,
            List<TipoResultado> tipos,
            List<Object> pasosPila) {
        this.exito = exito;
        this.errores = new ArrayList<>();
        if (errores != null) {
            this.errores.addAll(errores);
        }
        if (arbolSintactico == null) {
            this.arbolSintactico = "";
        } else {
            this.arbolSintactico = arbolSintactico;
        }
        if (astMermaid == null) {
            this.astMermaid = "";
        } else {
            this.astMermaid = astMermaid;
        }
        if (codigoPigLatin == null) {
            this.codigoPigLatin = "";
        } else {
            this.codigoPigLatin = codigoPigLatin;
        }
        this.simbolos = new ArrayList<>();
        if (simbolos != null) {
            this.simbolos.addAll(simbolos);
        }
        this.tipos = new ArrayList<>();
        if (tipos != null) {
            this.tipos.addAll(tipos);
        }
        this.pasosPila = new ArrayList<>();
        if (pasosPila != null) {
            this.pasosPila.addAll(pasosPila);
        }
    }

    /**
     * Verificar si el analisis no registro errores.
     */
    public boolean isExito() {
        return this.exito;
    }

    /**
     * Obtener los errores registrados durante el analisis.
     */
    public List<ErrorCompilacion> getErrores() {
        return this.errores;
    }

    /**
     * Obtener el arbol sintactico textual generado por ANTLR.
     */
    public String getArbolSintactico() {
        return this.arbolSintactico;
    }

    /**
     * Obtener el codigo Mermaid del arbol sintactico.
     */
    public String getAstMermaid() {
        return this.astMermaid;
    }

    /**
     * Obtener el codigo traducido al lenguaje Pig Latin.
     */
    public String getCodigoPigLatin() {
        return this.codigoPigLatin;
    }

    /**
     * Obtener la tabla de simbolos del programa.
     */
    public List<SimboloResultado> getSimbolos() {
        return this.simbolos;
    }

    /**
     * Obtener la tabla de tipos del programa.
     */
    public List<TipoResultado> getTipos() {
        return this.tipos;
    }

    /**
     * Obtener los pasos de la pila shift-reduce del analisis.
     */
    public List<Object> getPasosPila() {
        return this.pasosPila;
    }
}