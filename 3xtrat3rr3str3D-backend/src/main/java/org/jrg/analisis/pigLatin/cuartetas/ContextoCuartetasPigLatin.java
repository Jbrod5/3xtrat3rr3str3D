package org.jrg.analisis.pigLatin.cuartetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.cuarteta.GeneradorTemporales;

// estado compartido para la generacion de cuartetas de Pig Latin
public class ContextoCuartetasPigLatin {

    private final List<Cuarteta> cuartetas;
    private final GeneradorTemporales temporales;
    private String etiquetaBreakActual;
    private String etiquetaContinueActual;
    private final Map<String, String> tiposConocidos;
    private final Map<String, List<String>> camposDeStructs;
    private final Map<String, String> tiposDeVariables;

    // crear el contexto con estructuras vacias
    public ContextoCuartetasPigLatin() {
        // inicializar la lista y el generador
        this.cuartetas = new ArrayList<>();
        this.temporales = new GeneradorTemporales();
        this.etiquetaBreakActual = null;
        this.etiquetaContinueActual = null;
        this.tiposConocidos = new HashMap<>();
        this.camposDeStructs = new HashMap<>();
        this.tiposDeVariables = new HashMap<>();
    }

    // obtener la lista de cuartetas generadas
    public List<Cuarteta> getCuartetas() {
        return cuartetas;
    }

    // obtener el generador de temporales y etiquetas
    public GeneradorTemporales getTemporales() {
        return temporales;
    }

    // obtener la etiqueta actual para break
    public String getEtiquetaBreakActual() {
        return etiquetaBreakActual;
    }

    // fijar la etiqueta actual para break
    public void setEtiquetaBreakActual(String etiquetaBreakActual) {
        this.etiquetaBreakActual = etiquetaBreakActual;
    }

    // obtener la etiqueta actual para continue
    public String getEtiquetaContinueActual() {
        return etiquetaContinueActual;
    }

    // fijar la etiqueta actual para continue
    public void setEtiquetaContinueActual(String etiquetaContinueActual) {
        this.etiquetaContinueActual = etiquetaContinueActual;
    }

    // obtener los tipos conocidos de temporales y variables
    public Map<String, String> getTiposConocidos() {
        return tiposConocidos;
    }

    // obtener los nombres de campos por nombre de struct en orden
    public Map<String, List<String>> getCamposDeStructs() {
        return camposDeStructs;
    }

    // obtener los tipos de variables declaradas por nombre
    public Map<String, String> getTiposDeVariables() {
        return tiposDeVariables;
    }

    // registrar los campos de un struct importado en orden
    public void registrarCamposDeStruct(String nombreStruct, List<String> campos) {

        // omitir nombres o listas nulas
        if (nombreStruct == null || campos == null) {
            return;
        }

        // guardar los campos para resolver escrituras por nombre
        this.camposDeStructs.put(nombreStruct, campos);
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

    // adivinar el tipo del literal por como se ve
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

        // cualquier otra cosa es de tipo desconocidooo
        return "_";
    }

    // adivinar el tipo con el mapa o por como se ve
    public String inferirTipoDe(String nombre, Map<String, String> tipos) {

        // devolver guion bajo si el nombre es nulo o vacio de tipo :D
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

        // adivinar por como se ve el literal
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

    // adivinar el tipo del numero, int si no se sabe
    public String tipoAritmetico(String nombre) {
        // adivinar el tipo del operando si ya se sabe
        String tipo = inferirTipoDe(nombre, tiposConocidos);

        // usar entero cuando el tipo es desconocido
        if (tipo.equals("_")) {
            return "entero";
        }

        return tipo;
    }

    // adivinar que tipo sale de la cuenta
    public String tipoResultadoAritmetico(String a, String b) {

        // adivinar los tipos de los dos lados
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
        if (esTipoFlotante(tipoA) || esTipoFlotante(tipoB)) {
            return "flotante";
        }

        // usar entero por defecto en caso mixto
        return "entero";
    }

    // verificar si un tipo es numerico para convertir al tipo de mayor jerarquia
    public boolean esTipoNumerico(String tipo) {

        // comparar con enteros de los tres lenguajes
        if ("entero".equals(tipo) || "numerus".equals(tipo) || "int".equals(tipo)) {
            return true;
        }

        // comparar con flotantes de los tres lenguajes
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
