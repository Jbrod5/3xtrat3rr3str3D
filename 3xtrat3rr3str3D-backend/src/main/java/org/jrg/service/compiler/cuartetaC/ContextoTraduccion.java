package org.jrg.service.compiler.cuartetaC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jrg.model.resultado.CuartetaResultado;

// estado compartido para la traduccion de cuartetas a maquina Heap Stack
public class ContextoTraduccion {

    // slots por nombre en la funcion actual
    public Map<String, SlotHS> slots;
    // cantidad de locales y temporales reservados
    public int cuentaLocales;
    // cantidad de params de la funcion actual
    public int cuentaParams;
    // tabla de cadenas literales con su nombre strN
    public Map<String, String> tablaCadenas;
    // contador de cadenas para la tabla
    public int contadorCadenas;
    // campos por nombre de struct con tipo fuente
    public Map<String, Map<String, String>> structs;
    // orden de campos por nombre de struct
    public Map<String, List<String>> ordenCampos;
    // nombres de structs en orden de aparicion
    public List<String> ordenStructs;
    // nombre de la funcion actual
    public String funcionActual;
    // clase del metodo en proceso o vacio fuera de metodos
    public String nombreClaseActual;
    // tipos de retorno conocidos por nombre de funcion
    public Map<String, String> retornosFuncion;
    // nombres de funciones conocidas por func_begin
    public List<String> funcionesConocidas;
    // struct base de cada arreglo instancia u objeto
    public Map<String, String> mapaBases;
    // orden de locales y temporales precontados
    public List<String> ordenLocales;
    // valores pendientes del siguiente call
    public List<String> paramsPendientes;
    // tipos pendientes del siguiente call
    public List<String> tiposParamsPendientes;
    // builtins usados para la cabecera
    public List<String> builtinsUsados;

    /**
     * Crear un contexto vacio.
     */
    public ContextoTraduccion() {
        // inicializar las estructuras internas
        this.slots = new HashMap<>();
        this.cuentaLocales = 0;
        this.cuentaParams = 0;
        this.tablaCadenas = new HashMap<>();
        this.contadorCadenas = 0;
        this.structs = new HashMap<>();
        this.ordenCampos = new HashMap<>();
        this.ordenStructs = new ArrayList<>();
        this.funcionActual = "";
        this.nombreClaseActual = "";
        this.retornosFuncion = new HashMap<>();
        this.funcionesConocidas = new ArrayList<>();
        this.mapaBases = new HashMap<>();
        this.ordenLocales = new ArrayList<>();
        this.paramsPendientes = new ArrayList<>();
        this.tiposParamsPendientes = new ArrayList<>();
        this.builtinsUsados = new ArrayList<>();
    }

    /**
     * Empezar una funcion limpiando los slots.
     */
    public void iniciarFuncion(int cuentaParams) {
        // guardar la cantidad de params y limpiar locales
        this.cuentaParams = cuentaParams;
        this.cuentaLocales = 0;
        this.slots.clear();
        // limpiar bases que solo valen dentro de cada funcion
        this.mapaBases.clear();
        // limpiar el preconteo de locales
        this.ordenLocales.clear();
    }

    /**
     * Registrar un parametro con su indice de entrada.
     */
    public void registrarParam(String nombre, String tipoFuente) {
        // omitir nombres nulos o vacios
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return;
        }
        // usar el siguiente indice libre despues de fp
        int indice = 1 + this.slots.size();
        this.slots.put(nombre, new SlotHS(indice, arregloPara(tipoFuente)));
    }

    /**
     * Declarar un slot para un nombre si aun no existe.
     */
    public SlotHS declararSlot(String nombre, String tipoFuente) {
        // devolver el existente sin duplicar
        SlotHS previo = this.slots.get(nombre);
        if (previo != null) {
            return previo;
        }
        // usar el siguiente indice libre despues de params y locales
        int indice = 1 + this.cuentaParams + this.cuentaLocales;
        this.cuentaLocales = this.cuentaLocales + 1;
        SlotHS slot = new SlotHS(indice, arregloPara(tipoFuente));
        this.slots.put(nombre, slot);
        return slot;
    }

    /**
     * Reasignar el arreglo de un slot sin mover su indice.
     */
    public SlotHS redeclararSlot(String nombre, String tipoFuente) {
        // declarar normal si no existe
        SlotHS previo = this.slots.get(nombre);
        if (previo == null) {
            return declararSlot(nombre, tipoFuente);
        }
        // conservar el indice y cambiar solo el arreglo
        SlotHS nuevo = new SlotHS(previo.getIndice(), arregloPara(tipoFuente));
        this.slots.put(nombre, nuevo);
        return nuevo;
    }

    /**
     * Obtener la direccion fp mas indice de un nombre.
     */
    public String direccionDe(String nombre) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return null;
        }
        SlotHS slot = this.slots.get(nombre);
        if (slot == null) {
            return null;
        }
        return "fp + " + slot.getIndice();
    }

    /**
     * Obtener el arreglo tipado de un nombre.
     */
    public String arregloDe(String nombre) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return null;
        }
        SlotHS slot = this.slots.get(nombre);
        if (slot == null) {
            return null;
        }
        return slot.getArreglo();
    }

    /**
     * Mapear un tipo fuente a su arreglo de pila.
     */
    public String arregloPara(String tipo) {
        // usar enteros cuando el tipo es desconocido
        if (tipo == null || tipo.equals("_") || tipo.isEmpty()) {
            return "stackinteger";
        }
        // mapear enteros
        if (tipo.equals("entero") || tipo.equals("numerus") || tipo.equals("int")) {
            return "stackinteger";
        }
        // mapear flotantes
        if (tipo.equals("flotante") || tipo.equals("decimalis") || tipo.equals("double")) {
            return "stackfloat";
        }
        // mapear cadenas
        if (tipo.equals("cadena") || tipo.equals("textum") || tipo.equals("String") || tipo.equals("char*")) {
            return "stackstring";
        }
        // mapear caracteres
        if (tipo.equals("caracter") || tipo.equals("littera") || tipo.equals("char")) {
            return "stackchar";
        }
        // mapear booleanos
        if (tipo.equals("booleano") || tipo.equals("bool") || tipo.equals("boolean")) {
            return "stackboolean";
        }
        // usar enteros como respaldo para structs y clases (bases de heap)
        return "stackinteger";
    }

    /**
     * Mapear un tipo fuente a su arreglo de heap.
     */
    public String arregloHeapPara(String tipo) {
        // usar enteros cuando el tipo es desconocido
        if (tipo == null || tipo.equals("_") || tipo.isEmpty()) {
            return "heapinteger";
        }
        // mapear flotantes
        if (tipo.equals("flotante") || tipo.equals("decimalis") || tipo.equals("double")) {
            return "heapfloat";
        }
        // mapear cadenas
        if (tipo.equals("cadena") || tipo.equals("textum") || tipo.equals("String") || tipo.equals("char*")) {
            return "heapstring";
        }
        // mapear caracteres
        if (tipo.equals("caracter") || tipo.equals("littera") || tipo.equals("char")) {
            return "heapchar";
        }
        // mapear booleanos
        if (tipo.equals("booleano") || tipo.equals("bool") || tipo.equals("boolean")) {
            return "heapboolean";
        }
        // usar enteros para numericos bases y desconocidos
        return "heapinteger";
    }

    /**
     * Resolver el struct base de un nombre o de this.
     */
    public String baseDe(String nombre) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return null;
        }
        // resolver this con la clase actual
        if ("this".equals(nombre)) {
            if (nombreClaseActual == null || nombreClaseActual.isEmpty()) {
                return null;
            }
            return nombreClaseActual;
        }
        return mapaBases.get(nombre);
    }

    /**
     * Buscar el indice de un campo dentro de su struct.
     */
    public int offsetDe(String structNombre, String campo) {
        // omitir nulos o vacios
        if (structNombre == null || campo == null) {
            return -1;
        }
        List<String> orden = ordenCampos.get(structNombre);
        if (orden == null) {
            return -1;
        }
        return orden.indexOf(campo);
    }

    /**
     * Obtener el registro AX segun el arreglo.
     */
    public String registroPara(String arreglo) {
        // elegir por nombre de arreglo
        if ("stackfloat".equals(arreglo) || "heapfloat".equals(arreglo)) {
            return "AX_FLOAT";
        }
        if ("stackstring".equals(arreglo) || "heapstring".equals(arreglo)) {
            return "AX_STRING";
        }
        if ("stackchar".equals(arreglo) || "heapchar".equals(arreglo)) {
            return "AX_CHAR";
        }
        if ("stackboolean".equals(arreglo) || "heapboolean".equals(arreglo)) {
            return "AX_BOOLEAN";
        }
        return "AX_INT";
    }

    /**
     * Obtener el arreglo real de un valor para formatos.
     */
    public String arregloRealDe(String valor) {
        // omitir nulos y vacios
        if (valor == null || valor.isEmpty()) {
            return null;
        }
        // detectar cadenas por comilla doble inicial
        if (valor.startsWith("\"")) {
            return "stackstring";
        }
        // detectar caracter por comilla simple inicial
        if (valor.startsWith("'")) {
            return "stackchar";
        }
        // usar el arreglo del slot si existe
        String arreglo = arregloDe(valor);
        if (arreglo != null) {
            return arreglo;
        }
        // detectar booleanos de los tres lenguajes
        if (valor.equals("verum") || valor.equals("verdadero") || valor.equals("true") || valor.equals("falsus") || valor.equals("falso") || valor.equals("false")) {
            return "stackboolean";
        }
        // detectar nulos de objeto
        if (valor.equals("null") || valor.equals("NULL") || valor.equals("_")) {
            return "stackinteger";
        }
        // detectar entero si empieza con digito
        if (Character.isDigit(valor.charAt(0))) {
            return "stackinteger";
        }
        // detectar flotante por punto decimal
        if (valor.contains(".")) {
            return "stackfloat";
        }
        // detectar entero con signo
        if (valor.startsWith("-") && valor.length() > 1) {
            return "stackinteger";
        }
        return null;
    }

    /**
     * Resolver un operando a expresion C lista para usar.
     */
    public String expresionOperando(String valor) {
        // usar cero para nulos vacios y guiones
        if (valor == null || valor.isEmpty() || valor.equals("_")) {
            return "0";
        }
        // pedir nombre de tabla para cadenas entre comillas
        if (valor.startsWith("\"")) {
            return pedirCadena(valor);
        }
        // dejar literales de caracter tal cual
        if (valor.startsWith("'")) {
            return valor;
        }
        // mapear booleanos de los tres lenguajes
        if ("verum".equals(valor) || "verdadero".equals(valor) || "true".equals(valor)) {
            return "1";
        }
        if ("falsus".equals(valor) || "falso".equals(valor) || "false".equals(valor)) {
            return "0";
        }
        // usar cero para nulos de objeto
        if ("null".equals(valor) || "NULL".equals(valor)) {
            return "0";
        }
        // leer slots ya declarados de su arreglo
        String direccion = direccionDe(valor);
        if (direccion != null) {
            return arregloDe(valor) + "[" + direccion + "]";
        }
        // leer campos de la clase actual con this
        if (nombreClaseActual != null && nombreClaseActual.isEmpty() == false) {
            Map<String, String> campos = structs.get(nombreClaseActual);
            if (campos != null && campos.containsKey(valor)) {
                String tipoCampo = campos.get(valor);
                int off = offsetDe(nombreClaseActual, valor);
                SlotHS slotThis = slots.get("this");
                if (off >= 0 && slotThis != null) {
                    String baseThis = slotThis.getArreglo() + "[fp + " + slotThis.getIndice() + "]";
                    return arregloHeapPara(tipoCampo) + "[" + baseThis + " + " + off + "]";
                }
            }
        }
        // dejar numeros tal cual
        if (esNumerico(valor)) {
            return valor;
        }
        if (valor.contains(".")) {
            return valor;
        }
        if (valor.startsWith("-") && valor.length() > 1) {
            return valor;
        }
        // devolver el nombre crudo si no se reconoce
        return valor;
    }

    /**
     * Pedir el nombre strN de un literal creando la entrada.
     */
    public String pedirCadena(String literal) {
        // devolver la existente sin duplicar
        if (literal == null) {
            literal = "\"\"";
        }
        String previo = this.tablaCadenas.get(literal);
        if (previo != null) {
            return previo;
        }
        // crear el nombre nuevo con el contador
        String nombre = "str" + this.contadorCadenas;
        this.contadorCadenas = this.contadorCadenas + 1;
        this.tablaCadenas.put(literal, nombre);
        return nombre;
    }

    /**
     * Verificar si un nombre es un temporal generado.
     */
    public boolean esTemporal(String nombre) {
        // rechazar nulos y nombres cortos
        if (nombre == null || nombre.length() < 2) {
            return false;
        }
        // verificar que empiece con t
        if (nombre.charAt(0) != 't') {
            return false;
        }
        // verificar que el resto sean digitos
        for (int i = 1; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verificar si un nombre es una etiqueta generada.
     */
    public boolean esEtiqueta(String nombre) {
        // rechazar nulos y nombres cortos
        if (nombre == null || nombre.length() < 2) {
            return false;
        }
        // verificar que empiece con L mayuscula
        if (nombre.charAt(0) != 'L') {
            return false;
        }
        // verificar que el resto sean digitos
        for (int i = 1; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verificar si una cadena es un numero entero.
     */
    public boolean esNumerico(String texto) {
        // rechazar nulos y vacios
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(texto);
            return true;
        } catch (NumberFormatException e) {
            // usar falso cuando el texto no es numero
            return false;
        }
    }

    /**
     * Registrar un struct con sus campos en orden.
     */
    public void registrarStruct(String nombre, String camposTexto) {
        // omitir nombres nulos o vacios
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return;
        }
        // crear el mapa de campos y su orden
        Map<String, String> campos = new HashMap<>();
        List<String> orden = new ArrayList<>();
        // recorrer los campos separados por coma
        if (camposTexto != null && camposTexto.equals("_") == false && camposTexto.isEmpty() == false) {
            String[] partes = camposTexto.split(",");
            for (int i = 0; i < partes.length; i++) {
                String recorte = partes[i].trim();
                int dosPuntos = recorte.indexOf(':');
                // omitir partes sin formato nombre tipo
                if (dosPuntos <= 0) {
                    continue;
                }
                String campo = recorte.substring(0, dosPuntos).trim();
                String tipo = recorte.substring(dosPuntos + 1).trim();
                // omitir campos o tipos vacios
                if (campo.isEmpty() || tipo.isEmpty()) {
                    continue;
                }
                // guardar el orden solo la primera vez
                if (campos.containsKey(campo) == false) {
                    orden.add(campo);
                }
                campos.put(campo, tipo);
            }
        }
        // guardar el struct y su orden de campos
        structs.put(nombre, campos);
        ordenCampos.put(nombre, orden);
        // guardar el orden de definicion sin duplicados
        if (ordenStructs.contains(nombre) == false) {
            ordenStructs.add(nombre);
        }
    }

    /**
     * Preprocesar las definiciones de structs de la lista.
     */
    public void preprocesarStructs(List<CuartetaResultado> cuartetas) {
        // reiniciar las estructuras de structs
        this.structs = new HashMap<>();
        this.ordenCampos = new HashMap<>();
        this.ordenStructs = new ArrayList<>();
        // registrar definiciones en una pasada global
        for (int i = 0; i < cuartetas.size(); i++) {
            CuartetaResultado c = cuartetas.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            // registrar cada definicion de struct
            if ("struct_def".equals(c.getOperador())) {
                registrarStruct(c.getArg1(), c.getArg2());
            }
        }
    }

    /**
     * Preparar un operando para concatenacion convirtiendo numericos a string.
     */
    public String prepararOperandoParaConcat(String valor, String tipo, StringBuilder lineas) {
        // resolver el valor y su arreglo real
        String texto = expresionOperando(valor);
        String arreglo = arregloRealDe(valor);
        if (arreglo == null) {
            arreglo = arregloPara(tipo);
        }
        // elegir el formato segun el arreglo
        String formato = formatoPara(arreglo);
        // devolver directo cuando ya es texto
        if (formato == null) {
            return texto;
        }
        // convertir con sprintf a temporal propio
        String tempStr = "__strhs_" + this.contadorCadenas;
        this.contadorCadenas = this.contadorCadenas + 1;
        lineas.append("char* ").append(tempStr).append(" = malloc(32);\n    ");
        lineas.append("sprintf(").append(tempStr).append(", \"").append(formato).append("\", ").append(texto).append(");\n    ");
        // devolver el nombre del temporal de string
        return tempStr;
    }

    /**
     * Elegir el formato printf segun el arreglo.
     */
    public String formatoPara(String arreglo) {
        // devolver nulo cuando ya es texto
        if ("stackstring".equals(arreglo) || "heapstring".equals(arreglo)) {
            return null;
        }
        // elegir por nombre de arreglo
        if ("stackinteger".equals(arreglo) || "heapinteger".equals(arreglo)) {
            return "%d";
        }
        if ("stackfloat".equals(arreglo) || "heapfloat".equals(arreglo)) {
            return "%f";
        }
        if ("stackchar".equals(arreglo) || "heapchar".equals(arreglo)) {
            return "%c";
        }
        if ("stackboolean".equals(arreglo) || "heapboolean".equals(arreglo)) {
            return "%d";
        }
        return null;
    }

    /**
     * Limpiar los params pendientes despues de un call.
     */
    public void limpiarParams() {
        // vaciar ambas listas en orden
        paramsPendientes.clear();
        tiposParamsPendientes.clear();
    }

}
