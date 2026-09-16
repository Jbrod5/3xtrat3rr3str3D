package org.jrg.model.resultado;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.jrg.model.error.ErrorCompilacion;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.SimboloResultado;
import org.jrg.model.semantico.Tipo;
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
    private final List<Simbolo> simbolosCrudos;
    private final List<Tipo> tiposCrudos;
    private final List<CuartetaResultado> cuartetas;
    private final String codigoC;

    /**
     * Crear un resultado de analisis sin simbolos crudos.
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
        // this(exito, errores, arbolSintactico, astMermaid, codigoPigLatin,
        //         simbolos, tipos, pasosPila, null, null, null);
        this(exito, errores, arbolSintactico, astMermaid, codigoPigLatin,
                simbolos, tipos, pasosPila, null, null, null, null);
    }

    /**
     * Crear un resultado de analisis con simbolos crudos para uso interno.
     */
    public ResultadoAnalisis(
            boolean exito,
            List<ErrorCompilacion> errores,
            String arbolSintactico,
            String astMermaid,
            String codigoPigLatin,
            List<SimboloResultado> simbolos,
            List<TipoResultado> tipos,
            List<Object> pasosPila,
            List<Simbolo> simbolosCrudos,
            List<Tipo> tiposCrudos) {
        // this(exito, errores, arbolSintactico, astMermaid, codigoPigLatin,
        //         simbolos, tipos, pasosPila, simbolosCrudos, tiposCrudos, null);
        this(exito, errores, arbolSintactico, astMermaid, codigoPigLatin,
                simbolos, tipos, pasosPila, simbolosCrudos, tiposCrudos, null, null);
    }

    /**
     * Crear un resultado de analisis con simbolos crudos y cuartetas.
     */
    public ResultadoAnalisis(
            boolean exito,
            List<ErrorCompilacion> errores,
            String arbolSintactico,
            String astMermaid,
            String codigoPigLatin,
            List<SimboloResultado> simbolos,
            List<TipoResultado> tipos,
            List<Object> pasosPila,
            List<Simbolo> simbolosCrudos,
            List<Tipo> tiposCrudos,
            List<CuartetaResultado> cuartetas) {
        // this(exito, errores, arbolSintactico, astMermaid, codigoPigLatin,
        //         simbolos, tipos, pasosPila, simbolosCrudos, tiposCrudos, cuartetas, null);
        this(exito, errores, arbolSintactico, astMermaid, codigoPigLatin,
                simbolos, tipos, pasosPila, simbolosCrudos, tiposCrudos, cuartetas, null);
    }

    /**
     * Crear un resultado de analisis con simbolos crudos cuartetas y codigo C.
     */
    public ResultadoAnalisis(
            boolean exito,
            List<ErrorCompilacion> errores,
            String arbolSintactico,
            String astMermaid,
            String codigoPigLatin,
            List<SimboloResultado> simbolos,
            List<TipoResultado> tipos,
            List<Object> pasosPila,
            List<Simbolo> simbolosCrudos,
            List<Tipo> tiposCrudos,
            List<CuartetaResultado> cuartetas,
            String codigoC) {
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
        this.simbolosCrudos = new ArrayList<>();
        if (simbolosCrudos != null) {
            this.simbolosCrudos.addAll(simbolosCrudos);
        }
        this.tiposCrudos = new ArrayList<>();
        if (tiposCrudos != null) {
            this.tiposCrudos.addAll(tiposCrudos);
        }
        this.cuartetas = new ArrayList<>();
        if (cuartetas != null) {
            this.cuartetas.addAll(cuartetas);
        }
        if (codigoC == null) {
            this.codigoC = "";
        } else {
            this.codigoC = codigoC;
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

    /**
     * Obtener los simbolos crudos para uso interno entre servicios.
     */
    @JsonIgnore
    public List<Simbolo> getSimbolosCrudos() {
        return this.simbolosCrudos;
    }

    /**
     * Obtener los tipos crudos para uso interno entre servicios.
     */
    @JsonIgnore
    public List<Tipo> getTiposCrudos() {
        return this.tiposCrudos;
    }

    /**
     * Obtener las cuartetas generadas por el compilador.
     */
    public List<CuartetaResultado> getCuartetas() {
        return this.cuartetas;
    }

    /**
     * Obtener el codigo C generado a partir de las cuartetas.
     */
    public String getCodigoC() {
        return this.codigoC;
    }
}