package org.jrg.service.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jrg.model.resultado.CuartetaResultado;

// traducir cuartetas de codigo de tres direcciones a codigo C
public class TraductorC {

    // valores de params pendientes del siguiente call
    private List<String> paramsPendientes;
    // tipos de params pendientes del siguiente call
    private List<String> tiposParamsPendientes;
    // builtins usados en orden de aparicion
    private List<String> builtinsUsados;
    // prototipos de funciones en orden de aparicion
    private List<String> prototipos;
    // variables globales con su tipo en C
    private Map<String, String> globales;
    // nombres ya declarados en la funcion actual
    private Set<String> declaradas;
    // nombre de la funcion actual
    private String funcionActual;
    // indicar si ya se emitio la primera funcion
    private boolean primeraFuncionEmitida;
    // indicar si ya aparecio el primer func_begin
    private boolean dentroDeFuncion;
    // lineas del cuerpo de la funcion en proceso
    private StringBuilder bufferFuncion;
    // cuartetas crudas del cuerpo de la funcion en proceso
    private List<CuartetaResultado> crudasFuncion;
    // nombres llamados en la funcion en proceso
    private Set<String> llamadasFuncion;
    // tipos de params del func_begin en proceso
    private String tiposParamsFuncion;
    // tipo de retorno del func_begin en proceso
    private String tipoRetornoFuncion;
    // retornos conocidos por nombre de funcion
    private Map<String, String> retornosFuncion;
    // cuartetas previas a funciones para reemitir en la siguiente
    private List<CuartetaResultado> preMain;
    // tipos de params de la funcion actual por nombre
    private Map<String, String> tiposParamsActuales;
    // tipos de nombres declarados por nombre
    private Map<String, String> tiposDeclarados;

    /**
     * Crear un traductor de cuartetas a codigo C.
     */
    public TraductorC() {
        // inicializar las estructuras internas
        this.paramsPendientes = new ArrayList<>();
        this.tiposParamsPendientes = new ArrayList<>();
        this.builtinsUsados = new ArrayList<>();
        this.prototipos = new ArrayList<>();
        this.globales = new HashMap<>();
        this.declaradas = new HashSet<>();
        this.funcionActual = "";
        this.primeraFuncionEmitida = false;
        this.dentroDeFuncion = false;
        this.bufferFuncion = null;
        this.crudasFuncion = new ArrayList<>();
        this.llamadasFuncion = new HashSet<>();
        this.tiposParamsFuncion = "_";
        this.tipoRetornoFuncion = "_";
        this.retornosFuncion = new HashMap<>();
        this.preMain = new ArrayList<>();
        this.tiposParamsActuales = new HashMap<>();
        this.tiposDeclarados = new HashMap<>();
    }

    /**
     * Traducir una lista de cuartetas a codigo C.
     */
    public String traducir(List<CuartetaResultado> cuartetas) {
        // reiniciar el estado interno
        this.paramsPendientes = new ArrayList<>();
        this.tiposParamsPendientes = new ArrayList<>();
        this.builtinsUsados = new ArrayList<>();
        this.prototipos = new ArrayList<>();
        this.globales = new HashMap<>();
        this.declaradas = new HashSet<>();
        this.funcionActual = "";
        this.primeraFuncionEmitida = false;
        this.dentroDeFuncion = false;
        // devolver un programa minimo si no hay cuartetas
        if (cuartetas == null || cuartetas.isEmpty()) {
            return encabezado() + "int main(void) {\n    return 0;\n}\n";
        }
        // acumular el cuerpo de las funciones
        StringBuilder cuerpo = new StringBuilder();
        // recorrer cada cuarteta de la lista
        for (int i = 0; i < cuartetas.size(); i++) {
            CuartetaResultado c = cuartetas.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            String operador = c.getOperador();
            // procesar marcadores de funcion por separado
            if ("func_begin".equals(operador)) {
                iniciarFuncion(c);
                continue;
            }
            if ("func_end".equals(operador)) {
                terminarFuncion(cuerpo);
                continue;
            }
            // registrar globales fuera de funciones
            if (dentroDeFuncion == false) {
                procesarLineaGlobal(c);
                continue;
            }
            // guardar la cuarteta cruda para traducir al cerrar
            crudasFuncion.add(c);
        }
        // cerrar la funcion si falto su marcador de fin
        if (bufferFuncion != null) {
            terminarFuncion(cuerpo);
        }
        // ensamblar el programa completo
        return ensamblar(cuerpo.toString());
    }

    // generar los includes del programa
    private String encabezado() {
        // concatenar los includes necesarios
        StringBuilder sb = new StringBuilder();
        sb.append("#include <stdio.h>\n");
        sb.append("#include <stdlib.h>\n");
        sb.append("#include <string.h>\n");
        sb.append("#include <stdbool.h>\n");
        return sb.toString();
    }

    // ensamblar includes mas builtins mas prototipos mas globales mas cuerpo
    private String ensamblar(String cuerpo) {
        // crear el acumulador de salida
        StringBuilder salida = new StringBuilder();
        // agregar los includes
        salida.append(encabezado());
        // agregar las definiciones de builtins usados
        for (int i = 0; i < builtinsUsados.size(); i++) {
            salida.append(definicionBuiltin(builtinsUsados.get(i)));
        }
        // agregar los prototipos de funciones
        for (int i = 0; i < prototipos.size(); i++) {
            salida.append(prototipos.get(i)).append(";\n");
        }
        // agregar las variables globales
        for (Map.Entry<String, String> entrada : globales.entrySet()) {
            salida.append(entrada.getValue()).append(" ").append(entrada.getKey()).append(";\n");
        }
        // separar la cabecera del cuerpo con linea en blanco
        if (builtinsUsados.isEmpty() == false || prototipos.isEmpty() == false || globales.isEmpty() == false) {
            salida.append("\n");
        }
        // agregar el cuerpo de las funciones
        salida.append(cuerpo);
        return salida.toString();
    }

    // iniciar el buffer de una funcion sin emitir la firma aun
    private void iniciarFuncion(CuartetaResultado c) {
        // marcar que ya se entro a una funcion
        this.dentroDeFuncion = true;
        // guardar el nombre de la funcion actual
        this.funcionActual = c.getArg1();
        // guardar los tipos y el retorno del marcador
        this.tiposParamsFuncion = c.getArg2();
        this.tipoRetornoFuncion = c.getResultado();
        // registrar el retorno conocido por nombre
        if (c.getArg1() != null) {
            this.retornosFuncion.put(c.getArg1(), c.getResultado());
        }
        // reiniciar las declaraciones con las globales
        this.declaradas = new HashSet<>();
        for (String global : globales.keySet()) {
            this.declaradas.add(global);
        }
        // reiniciar los tipos conocidos con las globales
        this.tiposDeclarados = new HashMap<>();
        for (Map.Entry<String, String> entrada : globales.entrySet()) {
            this.tiposDeclarados.put(entrada.getKey(), entrada.getValue());
        }
        // limpiar params pendientes por seguridad
        this.paramsPendientes = new ArrayList<>();
        this.tiposParamsPendientes = new ArrayList<>();
        // crear el buffer del cuerpo y las listas de apoyo
        this.bufferFuncion = new StringBuilder();
        this.crudasFuncion = new ArrayList<>();
        this.llamadasFuncion = new HashSet<>();
        this.tiposParamsActuales = new HashMap<>();
    }

    // cerrar la funcion emitiendo la firma con nombres reales
    private void terminarFuncion(StringBuilder cuerpo) {
        // recolectar los nombres llamados en el cuerpo
        for (int i = 0; i < crudasFuncion.size(); i++) {
            CuartetaResultado c = crudasFuncion.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            // guardar nombres de funciones y metodos llamados
            if ("call".equals(c.getOperador()) || "call_method".equals(c.getOperador())) {
                if (c.getArg1() != null) {
                    llamadasFuncion.add(c.getArg1());
                }
            }
        }
        // recolectar identificadores candidatos en orden de aparicion
        List<String> identificadores = new ArrayList<>();
        for (int i = 0; i < crudasFuncion.size(); i++) {
            recolectarIdentificadores(crudasFuncion.get(i), identificadores);
        }
        // contar los parametros desde los tipos del marcador
        int cantidadParams = contarParametros(tiposParamsFuncion);
        // dividir los tipos por coma cuando existan
        String[] partesTipos = dividirTipos(tiposParamsFuncion);
        // construir nombres y tipos de params en orden
        List<String> nombresParams = new ArrayList<>();
        List<String> tiposParamsC = new ArrayList<>();
        for (int i = 0; i < cantidadParams; i++) {
            // extraer el tipo del parametro actual
            String tipoParam = "int";
            if (i < partesTipos.length) {
                tipoParam = mapearTipo(partesTipos[i].trim());
            }
            // usar el identificador real si se logro recoger
            String nombreParam = "p" + (i + 1);
            if (i < identificadores.size()) {
                nombreParam = identificadores.get(i);
            }
            nombresParams.add(nombreParam);
            tiposParamsC.add(tipoParam);
            // guardar el tipo por nombre para los builtins
            tiposParamsActuales.put(nombreParam, tipoParam);
        }
        // construir la firma con los nombres reales
        String firma = firmaConListas(funcionActual, tipoRetornoFuncion, nombresParams, tiposParamsC);
        // separar funciones con linea en blanco
        if (this.primeraFuncionEmitida) {
            cuerpo.append("\n");
        }
        this.primeraFuncionEmitida = true;
        // agregar el prototipo si no existe
        if (prototipos.contains(firma) == false) {
            prototipos.add(firma);
        }
        // emitir la firma con llave de apertura
        cuerpo.append(firma).append(" {\n");
        // reemitir las cuartetas previas y traducir el cuerpo
        traducirRaws(preMain);
        preMain = new ArrayList<>();
        traducirRaws(crudasFuncion);
        // vaciar el cuerpo acumulado
        cuerpo.append(bufferFuncion.toString());
        // agregar retorno en main
        if ("main".equals(this.funcionActual)) {
            cuerpo.append("    return 0;\n}");
        } else {
            cuerpo.append("}");
        }
        // limpiar el estado de la funcion actual
        this.funcionActual = "";
        this.dentroDeFuncion = false;
        this.bufferFuncion = null;
        this.crudasFuncion = new ArrayList<>();
        this.llamadasFuncion = new HashSet<>();
        // agregar salto de linea final
        cuerpo.append("\n");
    }

    // contar los parametros desde el string de tipos separados por coma
    private int contarParametros(String tiposParams) {
        // devolver cero si no hay parametros
        if (tiposParams == null || tiposParams.equals("_") || tiposParams.isEmpty()) {
            return 0;
        }
        // dividir los tipos por coma
        String[] partes = tiposParams.split(",");
        return partes.length;
    }

    // dividir los tipos de params por coma
    private String[] dividirTipos(String tiposParams) {
        // devolver arreglo vacio si no hay parametros
        if (tiposParams == null || tiposParams.equals("_") || tiposParams.isEmpty()) {
            return new String[0];
        }
        return tiposParams.split(",");
    }

    // construir la firma desde listas de nombres y tipos
    private String firmaConListas(String nombre, String tipoRetorno, List<String> nombres, List<String> tipos) {
        // acumular cada parametro con su nombre
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nombres.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tipos.get(i)).append(" ").append(nombres.get(i));
        }
        // forzar int en main aunque el marcador diga void
        if ("main".equals(nombre)) {
            // usar void cuando no hay parametros
            if (sb.length() == 0) {
                return "int main(void)";
            }
            return "int main(" + sb.toString() + ")";
        }
        // usar void cuando no hay parametros
        String lista = sb.toString();
        if (lista.isEmpty()) {
            lista = "void";
        }
        return mapearTipo(tipoRetorno) + " " + nombre + "(" + lista + ")";
    }

    // traducir una lista de cuartetas crudas al buffer de la funcion
    private void traducirRaws(List<CuartetaResultado> raws) {
        // recorrer cada cuarteta de la lista
        for (int i = 0; i < raws.size(); i++) {
            CuartetaResultado c = raws.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            // traducir la cuarteta individual
            String linea = traducirCuarteta(c);
            // omitir lineas vacias como param
            if (linea == null || linea.isEmpty()) {
                continue;
            }
            // emitir labels sin indentacion
            if (linea.endsWith(":;")) {
                bufferFuncion.append(linea).append("\n");
            } else {
                bufferFuncion.append("    ").append(linea).append("\n");
            }
        }
    }

    // recolectar identificadores candidatos de una cuarteta del cuerpo
    private void recolectarIdentificadores(CuartetaResultado c, List<String> identificadores) {
        // omitir cuartetas nulas
        if (c == null) {
            return;
        }
        String operador = c.getOperador();
        // revisar valores segun el operador
        if ("param".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if (":=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("+".equals(operador) || "-".equals(operador) || "*".equals(operador) || "/".equals(operador) || "%".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("==".equals(operador) || "!=".equals(operador) || "<".equals(operador) || ">".equals(operador) || "<=".equals(operador) || ">=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("&&".equals(operador) || "||".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("!".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("if_false".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("return".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("print".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if (".".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if (".,=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getResultado(), identificadores);
            return;
        }
        if ("=[]".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("[]=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            agregarCandidato(c.getResultado(), identificadores);
            return;
        }
        if ("alloc".equals(operador)) {
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        // omitir calls news labels gotos halts y reads por destino
    }

    // agregar un nombre a la lista si es candidato a parametro
    private void agregarCandidato(String nombre, List<String> identificadores) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return;
        }
        // omitir temporales generados
        if (esTemporal(nombre)) {
            return;
        }
        // omitir etiquetas
        if (esEtiqueta(nombre)) {
            return;
        }
        // omitir numeros
        if (nombre.length() > 0 && Character.isDigit(nombre.charAt(0))) {
            return;
        }
        // omitir cadenas y caracteres
        if (nombre.startsWith("\"") || nombre.startsWith("'")) {
            return;
        }
        // omitir booleanos de los tres lenguajes
        if (nombre.equals("verum") || nombre.equals("verdadero") || nombre.equals("true") || nombre.equals("falsus") || nombre.equals("falso") || nombre.equals("false")) {
            return;
        }
        // omitir nulos
        if (nombre.equals("null") || nombre.equals("NULL")) {
            return;
        }
        // omitir funciones llamadas
        if (llamadasFuncion.contains(nombre)) {
            return;
        }
        // omitir builtins conocidos
        if (nombre.equals("imprimir") || nombre.equals("println") || nombre.equals("leer") || nombre.equals("readln") || nombre.equals("print")) {
            return;
        }
        // omitir variables globales
        if (globales.containsKey(nombre)) {
            return;
        }
        // agregar sin duplicados en orden de aparicion
        if (identificadores.contains(nombre) == false) {
            identificadores.add(nombre);
        }
    }

    // verificar si un nombre es una etiqueta generada
    private boolean esEtiqueta(String nombre) {
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

    // procesar una cuarteta fuera de funciones
    private void procesarLineaGlobal(CuartetaResultado c) {
        String operador = c.getOperador();
        // registrar destinos de asignacion como globales
        if (":=".equals(operador)) {
            String destino = c.getResultado();
            // ignorar destinos temporales o vacios
            if (destino == null || destino.equals("_") || esTemporal(destino)) {
                // guardar la cuarteta para reemitirla despues
                preMain.add(c);
                return;
            }
            // registrar la global con su tipo si es nueva
            if (globales.containsKey(destino) == false) {
                globales.put(destino, mapearTipo(c.getTipoArg1()));
                declaradas.add(destino);
            }
        }
        // guardar la cuarteta para reemitirla en la siguiente funcion
        preMain.add(c);
        // omitir cualquier emision directa fuera de funciones
    }

    // traducir una cuarteta individual a una linea de C
    private String traducirCuarteta(CuartetaResultado c) {
        String operador = c.getOperador();
        // acumular params sin generar linea
        if ("param".equals(operador)) {
            paramsPendientes.add(c.getArg1());
            tiposParamsPendientes.add(c.getTipoArg1());
            return "";
        }
        // traducir llamadas a funcion
        if ("call".equals(operador)) {
            return traducirLlamada(c);
        }
        // traducir llamadas a metodo
        if ("call_method".equals(operador)) {
            String args = unirParams();
            limpiarParams();
            String tipo = mapearTipo(c.getTipoResultado());
            return prefijoDeclaracion(c.getResultado(), tipo) + " = " + c.getArg1() + "(" + args + ");";
        }
        // traducir carga de literal
        if ("=".equals(operador)) {
            String tipo = mapearTipo(c.getTipoResultado());
            return prefijoDeclaracion(c.getResultado(), tipo) + " = " + traducirValor(c.getArg1()) + ";";
        }
        // traducir asignacion simple
        if (":=".equals(operador)) {
            String destino = c.getResultado();
            String linea = "";
            // declarar la variable si aun no existe
            if (declaradas.contains(destino) == false) {
                String tipo = mapearTipo(c.getTipoArg1());
                linea = tipo + " " + destino + ";\n    ";
                declaradas.add(destino);
                tiposDeclarados.put(destino, tipo);
            }
            return linea + destino + " = " + traducirValor(c.getArg1()) + ";";
        }
        // traducir operaciones aritmeticas
        if ("+".equals(operador) || "-".equals(operador) || "*".equals(operador) || "/".equals(operador) || "%".equals(operador)) {
            String tipo = mapearTipo(c.getTipoResultado());
            return prefijoDeclaracion(c.getResultado(), tipo) + " = " + traducirValor(c.getArg1()) + " " + operador + " " + traducirValor(c.getArg2()) + ";";
        }
        // traducir comparaciones
        if ("==".equals(operador) || "!=".equals(operador) || "<".equals(operador) || ">".equals(operador) || "<=".equals(operador) || ">=".equals(operador)) {
            return prefijoDeclaracion(c.getResultado(), "bool") + " = (" + traducirValor(c.getArg1()) + " " + operador + " " + traducirValor(c.getArg2()) + ");";
        }
        // traducir operadores logicos binarios
        if ("&&".equals(operador) || "||".equals(operador)) {
            return prefijoDeclaracion(c.getResultado(), "bool") + " = (" + traducirValor(c.getArg1()) + " " + operador + " " + traducirValor(c.getArg2()) + ");";
        }
        // traducir negacion logica
        if ("!".equals(operador)) {
            return prefijoDeclaracion(c.getResultado(), "bool") + " = !" + traducirValor(c.getArg1()) + ";";
        }
        // traducir etiquetas y saltos
        if ("label".equals(operador)) {
            return c.getArg1() + ":;";
        }
        if ("goto".equals(operador)) {
            return "goto " + c.getArg1() + ";";
        }
        if ("if_false".equals(operador)) {
            return "if (!" + traducirValor(c.getArg1()) + ") goto " + c.getArg2() + ";";
        }
        // traducir retorno
        if ("return".equals(operador)) {
            // usar retorno con cero dentro de main sin valor
            if (c.getArg1() == null || c.getArg1().equals("_")) {
                if ("main".equals(this.funcionActual)) {
                    return "return 0;";
                }
                return "return;";
            }
            return "return " + traducirValor(c.getArg1()) + ";";
        }
        // traducir impresion
        if ("print".equals(operador)) {
            return traducirPrint(c.getArg1(), c.getTipoArg1());
        }
        // traducir lectura declarando la variable si es nueva
        if ("read".equals(operador)) {
            String destino = c.getResultado();
            String linea = "";
            // declarar la variable si aun no existe
            if (destino != null && destino.equals("_") == false && declaradas.contains(destino) == false) {
                linea = "int " + destino + ";\n    ";
                declaradas.add(destino);
                tiposDeclarados.put(destino, "int");
            }
            return linea + "scanf(\"%d\", &" + destino + ");";
        }
        // traducir acceso a miembro
        if (".".equals(operador)) {
            String tipo = mapearTipo(c.getTipoResultado());
            return prefijoDeclaracion(c.getResultado(), tipo) + " = " + c.getArg1() + "." + c.getArg2() + ";";
        }
        // traducir asignacion a miembro
        if (".,=".equals(operador)) {
            return c.getArg1() + "." + c.getArg2() + " = " + traducirValor(c.getResultado()) + ";";
        }
        // traducir acceso a arreglo
        if ("=[]".equals(operador)) {
            String tipo = mapearTipo(c.getTipoResultado());
            return prefijoDeclaracion(c.getResultado(), tipo) + " = " + c.getArg1() + "[" + traducirValor(c.getArg2()) + "];";
        }
        // traducir asignacion a arreglo
        if ("[]=".equals(operador)) {
            return c.getArg1() + "[" + traducirValor(c.getArg2()) + "] = " + traducirValor(c.getResultado()) + ";";
        }
        // traducir reserva de memoria
        if ("alloc".equals(operador)) {
            String tipo = mapearTipo(c.getArg1());
            return prefijoDeclaracion(c.getResultado(), tipo + "*") + " = malloc(sizeof(" + tipo + ") * " + expresionDimension(c.getArg2()) + ");";
        }
        // traducir instanciaciones como placeholder
        if ("new".equals(operador) || "new_struct".equals(operador)) {
            limpiarParams();
            String tipo = mapearTipo(c.getArg1());
            return prefijoDeclaracion(c.getResultado(), tipo) + " = 0;";
        }
        // traducir halt como retorno de main
        if ("halt".equals(operador)) {
            return "return 0;";
        }
        // marcar operadores desconocidos con comentario
        return "// cuarteta no soportada: " + operador;
    }

    // traducir una llamada a funcion o builtin
    private String traducirLlamada(CuartetaResultado c) {
        String nombre = c.getArg1();
        String args = unirParams();
        // resolver builtins imprimir y println por tipo
        if ("imprimir".equals(nombre) || "println".equals(nombre)) {
            String variante = varianteBuiltin(nombre);
            limpiarParams();
            return variante + "(" + args + ");";
        }
        limpiarParams();
        // detectar llamadas sin retorno por el marcador
        boolean esVoid = "void".equals(c.getTipoResultado());
        // detectar llamadas a funciones void ya definidas
        if (esVoid == false) {
            String retornoConocido = retornosFuncion.get(nombre);
            boolean tipoDesconocido = c.getTipoResultado() == null || c.getTipoResultado().equals("_");
            if ("void".equals(retornoConocido) && tipoDesconocido) {
                esVoid = true;
            }
        }
        // emitir llamada sin retorno para void
        if (esVoid) {
            return nombre + "(" + args + ");";
        }
        String tipo = mapearTipo(c.getTipoResultado());
        return prefijoDeclaracion(c.getResultado(), tipo) + " = " + nombre + "(" + args + ");";
    }

    // elegir la variante del builtin segun el tipo del primer argumento
    private String varianteBuiltin(String nombre) {
        String sufijo = "int";
        // usar la version sin argumentos si no hay params
        if (paramsPendientes.isEmpty()) {
            sufijo = "";
        } else {
            // inferir el tipo del primer argumento pendiente
            String tipo = inferirTipoParametro(paramsPendientes.get(0));
            // mapear el tipo C al sufijo correspondiente
            if ("char*".equals(tipo)) {
                sufijo = "str";
            } else if ("double".equals(tipo)) {
                sufijo = "float";
            } else if ("char".equals(tipo)) {
                sufijo = "char";
            } else if ("bool".equals(tipo)) {
                sufijo = "bool";
            } else {
                sufijo = "int";
            }
        }
        // construir el nombre de la variante
        String variante = nombre;
        if (sufijo.isEmpty() == false) {
            variante = nombre + "_" + sufijo;
        }
        // registrar la variante para definirla en la cabecera
        if (builtinsUsados.contains(variante) == false) {
            builtinsUsados.add(variante);
        }
        return variante;
    }

    // inferir el tipo C de un argumento de builtin
    private String inferirTipoParametro(String nombre) {
        // usar entero si el nombre es nulo o vacio de tipo
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return "int";
        }
        // buscar en los tipos declarados incluyendo temporales
        String declarado = tiposDeclarados.get(nombre);
        if (declarado != null) {
            return declarado;
        }
        // buscar en los params de la funcion actual
        String deParam = tiposParamsActuales.get(nombre);
        if (deParam != null) {
            return deParam;
        }
        // inferir por la forma del literal con entero por defecto
        return mapearTipo(inferirTipoLiteralParametro(nombre));
    }

    // inferir el tipo fuente de un valor por su forma
    private String inferirTipoLiteralParametro(String valor) {
        // rechazar nulos y vacios
        if (valor == null || valor.isEmpty()) {
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
        if (Character.isDigit(valor.charAt(0))) {
            return "entero";
        }
        // cualquier otra cosa es de tipo desconocido
        return "_";
    }

    // generar la definicion C de un builtin usado
    private String definicionBuiltin(String variante) {
        // definir imprimir sin argumentos
        if ("imprimir".equals(variante)) {
            return "void imprimir(void) { printf(\"\\n\"); }\n";
        }
        // definir println sin argumentos
        if ("println".equals(variante)) {
            return "void println(void) { printf(\"\\n\"); }\n";
        }
        // extraer el nombre base y el sufijo
        int guion = variante.lastIndexOf('_');
        // usar definicion generica si no hay sufijo
        if (guion < 0) {
            return "";
        }
        String base = variante.substring(0, guion);
        String sufijo = variante.substring(guion + 1);
        String tipo = "int";
        String formato = "%d";
        // mapear el sufijo al tipo y formato
        if ("str".equals(sufijo)) {
            tipo = "char*";
            formato = "%s";
        } else if ("float".equals(sufijo)) {
            tipo = "double";
            formato = "%f";
        } else if ("char".equals(sufijo)) {
            tipo = "char";
            formato = "%c";
        } else if ("bool".equals(sufijo)) {
            tipo = "bool";
            formato = "%d";
        }
        return "void " + variante + "(" + tipo + " x) { printf(\"" + formato + "\\n\", x); }\n";
    }

    // unir los params pendientes separados por coma
    private String unirParams() {
        // acumular los argumentos traducidos
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paramsPendientes.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(traducirValor(paramsPendientes.get(i)));
        }
        return sb.toString();
    }

    // limpiar los params pendientes despues de un call
    private void limpiarParams() {
        // vaciar ambas listas en orden
        paramsPendientes.clear();
        tiposParamsPendientes.clear();
    }

    // mapear un tipo del compilador a un tipo de C
    private String mapearTipo(String tipo) {
        // usar int cuando el tipo es desconocido
        if (tipo == null || tipo.equals("_") || tipo.isEmpty()) {
            return "int";
        }
        // conservar arreglos agregando corchetes al tipo base
        if (tipo.endsWith("[]")) {
            String base = tipo.substring(0, tipo.length() - 2);
            return mapearTipo(base) + "[]";
        }
        // mapear enteros
        if (tipo.equals("entero") || tipo.equals("numerus") || tipo.equals("int")) {
            return "int";
        }
        // mapear flotantes
        if (tipo.equals("flotante") || tipo.equals("decimalis") || tipo.equals("double")) {
            return "double";
        }
        // mapear cadenas
        if (tipo.equals("cadena") || tipo.equals("textum") || tipo.equals("String")) {
            return "char*";
        }
        // mapear caracteres
        if (tipo.equals("caracter") || tipo.equals("littera") || tipo.equals("char")) {
            return "char";
        }
        // mapear booleanos
        if (tipo.equals("booleano") || tipo.equals("bool") || tipo.equals("boolean")) {
            return "bool";
        }
        // conservar void
        if (tipo.equals("void")) {
            return "void";
        }
        // usar int como fallback para tipos desconocidos
        return "int";
    }

    // traducir constantes especiales a su forma en C
    private String traducirValor(String valor) {
        // conservar nulos y vacios sin cambios
        if (valor == null || valor.isEmpty()) {
            return valor;
        }
        // traducir el desconocido a cero
        if (valor.equals("_")) {
            return "0";
        }
        // traducir constantes verdaderas
        if (valor.equals("verum") || valor.equals("verdadero") || valor.equals("true")) {
            return "true";
        }
        // traducir constantes falsas
        if (valor.equals("falsus") || valor.equals("falso") || valor.equals("false")) {
            return "false";
        }
        // traducir nulo a NULL
        if (valor.equals("null") || valor.equals("NULL")) {
            return "NULL";
        }
        return valor;
    }

    // traducir un print segun el tipo del argumento
    private String traducirPrint(String valor, String tipoArg) {
        String texto = traducirValor(valor);
        // elegir el formato segun el tipo
        if ("cadena".equals(tipoArg) || "textum".equals(tipoArg) || "String".equals(tipoArg) || "char*".equals(tipoArg)) {
            return "printf(\"%s\\n\", " + texto + ");";
        }
        if ("flotante".equals(tipoArg) || "decimalis".equals(tipoArg) || "double".equals(tipoArg)) {
            return "printf(\"%f\\n\", " + texto + ");";
        }
        if ("caracter".equals(tipoArg) || "littera".equals(tipoArg) || "char".equals(tipoArg)) {
            return "printf(\"%c\\n\", " + texto + ");";
        }
        return "printf(\"%d\\n\", " + texto + ");";
    }

    // construir la expresion de dimension para alloc
    private String expresionDimension(String dimension) {
        // usar uno cuando la dimension es desconocida
        if (dimension == null || dimension.equals("_") || dimension.isEmpty()) {
            return "1";
        }
        // usar la dimension directa cuando es numerica
        if (esNumerico(dimension)) {
            return dimension;
        }
        // proteger dimensiones variables con comparacion positiva
        return "(" + dimension + " > 0 ? " + dimension + " : 1)";
    }

    // verificar si una cadena es un numero entero
    private boolean esNumerico(String texto) {
        // rechazar nulos y vacios
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // verificar si un nombre es un temporal generado
    private boolean esTemporal(String nombre) {
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

    // construir el prefijo con declaracion solo la primera vez
    private String prefijoDeclaracion(String nombre, String tipo) {
        // devolver guion bajo si el nombre es nulo o vacio de tipo
        if (nombre == null || nombre.equals("_")) {
            return "_";
        }
        // devolver solo el nombre si ya fue declarado
        if (declaradas.contains(nombre)) {
            return nombre;
        }
        // registrar el nombre con su tipo y devolverlo declarado
        declaradas.add(nombre);
        tiposDeclarados.put(nombre, tipo);
        return tipo + " " + nombre;
    }
}
