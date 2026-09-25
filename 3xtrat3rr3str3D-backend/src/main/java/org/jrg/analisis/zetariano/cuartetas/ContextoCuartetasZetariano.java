package org.jrg.analisis.zetariano.cuartetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.cuarteta.GeneradorTemporales;

// estado compartido para la generacion de cuartetas del lenguaje Zetariano
public class ContextoCuartetasZetariano {

    // lista de cuartetas generadas
    private final List<Cuarteta> cuartetas;
    // generador de temporales y etiquetas
    private final GeneradorTemporales temporales;
    // etiqueta actual para romper
    private String etiquetaBreakActual;
    // etiqueta actual para continuar
    private String etiquetaContinueActual;
    // nombre de la clase actual para prefijar funciones
    private String nombreClaseActual;
    // tipos conocidos de temporales y variables
    private final Map<String, String> tiposConocidos;
    // tipos de variables declaradas por nombre
    private final Map<String, String> tiposDeVariables;

    /**
     * Crear el contexto con estructuras vacias.
     */
    public ContextoCuartetasZetariano() {
        // inicializar la lista y el generador
        this.cuartetas = new ArrayList<>();
        this.temporales = new GeneradorTemporales();
        this.etiquetaBreakActual = null;
        this.etiquetaContinueActual = null;
        this.nombreClaseActual = null;
        this.tiposConocidos = new HashMap<>();
        this.tiposDeVariables = new HashMap<>();
    }

    /**
     * Obtener la lista de cuartetas generadas.
     */
    public List<Cuarteta> getCuartetas() {
        return cuartetas;
    }

    /**
     * Obtener el generador de temporales y etiquetas.
     */
    public GeneradorTemporales getTemporales() {
        return temporales;
    }

    /**
     * Obtener la etiqueta actual para romper.
     */
    public String getEtiquetaBreakActual() {
        return etiquetaBreakActual;
    }

    /**
     * Fijar la etiqueta actual para romper.
     */
    public void setEtiquetaBreakActual(String etiquetaBreakActual) {
        this.etiquetaBreakActual = etiquetaBreakActual;
    }

    /**
     * Obtener la etiqueta actual para continuar.
     */
    public String getEtiquetaContinueActual() {
        return etiquetaContinueActual;
    }

    /**
     * Fijar la etiqueta actual para continuar.
     */
    public void setEtiquetaContinueActual(String etiquetaContinueActual) {
        this.etiquetaContinueActual = etiquetaContinueActual;
    }

    /**
     * Obtener el nombre de la clase actual para prefijar funciones.
     */
    public String getNombreClaseActual() {
        return nombreClaseActual;
    }

    /**
     * Fijar el nombre de la clase actual para prefijar funciones.
     */
    public void setNombreClaseActual(String nombreClaseActual) {
        this.nombreClaseActual = nombreClaseActual;
    }

    /**
     * Obtener los tipos conocidos de temporales y variables.
     */
    public Map<String, String> getTiposConocidos() {
        return tiposConocidos;
    }

    /**
     * Obtener los tipos de variables declaradas por nombre.
     */
    public Map<String, String> getTiposDeVariables() {
        return tiposDeVariables;
    }

    // registrar el tipo de una variable declarada
    public void registrarTipoVariable(String nombre, String tipo) {
        // omitir nombres o tipos nulos
        if (nombre == null || tipo == null) {
            return;
        }

        // guardar el tipo para usos posteriores
        this.tiposDeVariables.put(nombre, tipo);
    }

    // inferir el tipo de un literal por su forma
    public String inferirTipoLiteral(String valor) {
        // devolver guion bajo si el valor es nulo o vacio de tipo
        if (valor == null || valor.equals("_")) {
            return "_";
        }

        // detectar cadena por comilla doble inicial
        if (valor.startsWith("\"")) {
            return "cadena";
        }

        // detectar caracter por comilla simple inicial
        if (valor.startsWith("'")) {
            return "caracter";
        }

        // detectar booleanos de los tres lenguajes
        if (valor.equals("verum") || valor.equals("verdadero") || valor.equals("true") || valor.equals("falsus") || valor.equals("falso") || valor.equals("false")) {
            return "booleano";
        }

        // detectar flotante por punto decimal
        if (valor.contains(".")) {
            return "flotante";
        }

        // detectar entero si empieza con digito
        if (valor.length() > 0 && Character.isDigit(valor.charAt(0))) {
            return "entero";
        }

        // cualquier otra cosa es de tipo desconocido
        return "_";
    }

    // inferir el tipo de un nombre usando el mapa o su forma literal
    public String inferirTipoDe(String nombre, Map<String, String> tipos) {
        // devolver guion bajo si el nombre es nulo o vacio de tipo
        if (nombre == null || nombre.equals("_")) {
            return "_";
        }

        // buscar en el mapa de tipos conocidos
        String tipo = tipos.get(nombre);
        if (tipo != null) {
            return tipo;
        }

        // buscar en los tipos de variables declaradas
        String tipoVariable = tiposDeVariables.get(nombre);
        if (tipoVariable != null) {
            return tipoVariable;
        }

        // inferir por la forma del literal
        return inferirTipoLiteral(nombre);
    }

    // verificar si un tipo corresponde a cadena de texto
    public boolean esTipoCadena(String tipo) {
        // comparar contra los nombres de cadena de los tres lenguajes
        if ("cadena".equals(tipo) || "textum".equals(tipo) || "String".equals(tipo)) {
            return true;
        }

        return false;
    }

    // inferir el tipo de un operando aritmetico con entero por defecto
    public String tipoAritmetico(String nombre) {
        // inferir el tipo conocido del operando
        String tipo = inferirTipoDe(nombre, tiposConocidos);

        // usar entero cuando el tipo es desconocido
        if (tipo.equals("_")) {
            return "entero";
        }

        return tipo;
    }

    // inferir el tipo resultado de una operacion aritmetica
    public String tipoResultadoAritmetico(String a, String b) {
        // inferir los tipos de ambos operandos
        String tipoA = inferirTipoDe(a, tiposConocidos);
        String tipoB = inferirTipoDe(b, tiposConocidos);

        // usar el tipo comun cuando ambos coinciden y es conocido
        if (tipoA.equals(tipoB)) {
            if (tipoA.equals("_")) {
                return "entero";
            }

            return tipoA;
        }

        // convertir al tipo de mayor jerarquia cuando algun operando es flotante
        // if (esTipoNumerico(tipoA) && esTipoNumerico(tipoB)) {
        //     return "flotante";
        // }

        if (esTipoFlotante(tipoA) || esTipoFlotante(tipoB)) {
            return "flotante";
        }

        // usar entero por defecto en caso mixto
        return "entero";
    }

    // verificar si un tipo es numerico para convertir al tipo de mayor jerarquia
    public boolean esTipoNumerico(String tipo) {
        // comparar contra enteros de los tres lenguajes
        if ("entero".equals(tipo) || "numerus".equals(tipo) || "int".equals(tipo)) {
            return true;
        }

        // comparar contra flotantes de los tres lenguajes
        if ("flotante".equals(tipo) || "decimalis".equals(tipo) || "double".equals(tipo)) {
            return true;
        }

        return false;
    }

    // verificar si un tipo es flotante en cualquier vocabulario
    public boolean esTipoFlotante(String tipo) {
        // comparar contra flotantes de los tres lenguajes
        if ("flotante".equals(tipo) || "decimalis".equals(tipo) || "double".equals(tipo)) {
            return true;
        }

        return false;
    }
}
