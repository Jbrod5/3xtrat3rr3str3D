package org.jrg.analisis.pigLatin.semantico;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.error.TipoError;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.semantico.Ambito;
import org.jrg.model.semantico.CategoriaSimbolo;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;
import org.jrg.service.error.RecolectorErrores;

// memoria del analizador semantico del  Pig Latin :D
class ContextoSemanticoPigLatin {



    // recolector donde se acumulan los errores semanticos
    private final RecolectorErrores recolectorErrores;
    
    // ambito raiz del programa
    private Ambito ambitoGlobal;
    
    // pila de ambitos activos,  el actual es siempre el ultimo que entro :D
    private final Deque<Ambito> ambitos;
    
    // tipos auxiliares que no viven en un ambito (por ejemplo los arreglos)
    private final List<Tipo> tiposExtra;
    
    // tipos primitivos del lenguaje: numerus, decimalis, textum, littera, bool etc etc
    private final Map<String, Tipo> tiposPrimitivos;
    
    // tipos no primitivos (clases, structs, importados) indexados por nombre
    private final Map<String, Tipo> tiposNoPrimitivos;
    
    // tipos donde el esquema de campos se conoce para validar miembros
    private final Set<String> tiposConEsquema;
    
    // contador para nombrar ambitos hijos
    private int contadorAmbitos;
    
    // profundidad actual de ciclos anidados
    private int profundidadCiclos;






    // crear el contexto con un recolector de errores :D
    ContextoSemanticoPigLatin(RecolectorErrores recolectorErrores) {

        // si no viene recolector se crea uno nuevo
        if (recolectorErrores == null) {
            this.recolectorErrores = new RecolectorErrores();
        } else {
            this.recolectorErrores = recolectorErrores;
        }

        // inicializar las estructuras internas
        this.ambitos = new ArrayDeque<>();
        this.tiposExtra = new ArrayList<>();
        this.tiposPrimitivos = new HashMap<>();
        this.tiposNoPrimitivos = new HashMap<>();
        this.tiposConEsquema = new HashSet<>();
        this.contadorAmbitos = 0;
        this.profundidadCiclos = 0;
    }

    // reiniciar el contexto y dejarlo listo para un nuevo analisis
    void iniciar() {

        // crear el ambito global
        this.ambitoGlobal = new Ambito("global");

        // limpiar todo lo anterior
        this.ambitos.clear();
        this.tiposExtra.clear();
        this.tiposPrimitivos.clear();
        this.tiposNoPrimitivos.clear();
        this.tiposConEsquema.clear();
        this.contadorAmbitos = 0;
        this.profundidadCiclos = 0;

        // registrar los tipos primitivos del lenguaje
        registrarTiposPrimitivos();

        // apilar el ambito global como ambito actual
        this.ambitos.addLast(this.ambitoGlobal);
    }

    // registrar los cinco tipos primitivos del lenguaje :D
    void registrarTiposPrimitivos() {
        agregarTipoPrimitivo("numerus", true);
        agregarTipoPrimitivo("decimalis", true);
        agregarTipoPrimitivo("textum", true);
        agregarTipoPrimitivo("littera", true);
        agregarTipoPrimitivo("bool", true);
    }

    // crear un tipo primitivo y guardarlo en el mapa y en el ambito global
    void agregarTipoPrimitivo(String nombre, boolean esPrimitivo) {

        Tipo tipo = new Tipo(nombre, esPrimitivo);
        this.tiposPrimitivos.put(nombre, tipo);

        if (this.ambitoGlobal != null) {
            this.ambitoGlobal.agregarTipo(tipo);
        }
    }

    // agregar un error semantico tomando linea y columna del nodo
    void agregarError(NodoAST nodo, String descripcion) {
        int linea = 0;
        int columna = 0;

        if (nodo != null) {
            linea = nodo.getLinea();
            columna = nodo.getColumna();
        }

        agregarError(linea, columna, descripcion);
    }

    // agregar un error semantico con linea y columna explicitas
    void agregarError(int linea, int columna, String descripcion) {
        this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, descripcion);
    }

    // devolver el ambito activo (el ultimo que entro)
    Ambito ambitoActual() {
        if (this.ambitos.isEmpty()) {
            return this.ambitoGlobal;
        }

        return this.ambitos.peekLast();
    }

    // entrar a un ambito nuevo hijo del actual
    void entrarAmbito(String nombre) {

        // incrementar el contador para generar un nombre unico
        this.contadorAmbitos++;
        Ambito padre = ambitoActual();
        String nombreAmbito = nombre + "_" + this.contadorAmbitos;
        Ambito nuevoAmbito = new Ambito(nombreAmbito, padre);

        // enlazar el nuevo ambito con su padre
        if (padre != null) {
            padre.agregarAmbito(nuevoAmbito);
        }

        // apilarlo como ambito actual
        this.ambitos.addLast(nuevoAmbito);
    }

    // salir del ambito actual regresando al padre
    void salirAmbito() {

        // nunca sacar el ambito global
        if (this.ambitos.size() > 1) {
            this.ambitos.removeLast();
        }
    }

    // entrar a un ciclo incrementando la profundidad
    void entrarCiclo() {
        this.profundidadCiclos++;
    }

    // salir del ciclo actual decrementando la profundidad
    void salirCiclo() {
        if (this.profundidadCiclos > 0) {
            this.profundidadCiclos--;
        }
    }

    // verificar si estamos dentro de un ciclo
    boolean estaDentroDeCiclo() {
        return this.profundidadCiclos > 0;
    }

    // generar un nombre unico para un ambito hijo
    String nombreAmbitoHijo(String prefijo) {
        this.contadorAmbitos++;
        return prefijo + "_" + this.contadorAmbitos;
    }

    // buscar un tipo primitivo por nombre
    Tipo tipoPrimitivo(String nombre) {
        if (nombre == null) {
            return null;
        }

        return this.tiposPrimitivos.get(nombre);
    }

    // mapear un TipoPrimitivo del AST a su tipo del lenguaje
    Tipo tipoPrimitivo(TipoPrimitivo tipoPrimitivo) {
        if (tipoPrimitivo == null) {
            return null;
        }
        if (tipoPrimitivo == TipoPrimitivo.ENTERO) {
            return tipoPrimitivo("numerus");
        }
        if (tipoPrimitivo == TipoPrimitivo.DECIMAL) {
            return tipoPrimitivo("decimalis");
        }
        if (tipoPrimitivo == TipoPrimitivo.CADENA) {
            return tipoPrimitivo("textum");
        }
        if (tipoPrimitivo == TipoPrimitivo.CARACTER) {
            return tipoPrimitivo("littera");
        }
        if (tipoPrimitivo == TipoPrimitivo.BOOLEANO) {
            return tipoPrimitivo("bool");
        }

        return null;
    }

    // buscar o crear un tipo no primitivo
    // si crear es false y no existe devuelve null
    // si crear es true lo registra y reporta error
    Tipo tipoNoPrimitivo(String nombre, NodoAST nodo, boolean crear) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }

        // devolverlo si ya existe
        Tipo existente = this.tiposNoPrimitivos.get(nombre);
        if (existente != null) {
            return existente;
        }

        // no crear si no se pidio
        if (!crear) {
            return null;
        }

        // crear el tipo no primitivo
        Tipo tipo = new Tipo(nombre, false);
        this.tiposNoPrimitivos.put(nombre, tipo);
        if (this.ambitoGlobal != null) {
            this.ambitoGlobal.agregarTipo(tipo);
        }

        // reportar que el tipo no estaba declarado
        if (nodo != null) {
            agregarError(nodo, "tipo no declarado");
        }

        return tipo;
    }

    // buscar un tipo no primitivo ya registrado por nombre
    Tipo tipoNoPrimitivoExistente(String nombre) {
        if (nombre == null) {
            return null;
        }

        return this.tiposNoPrimitivos.get(nombre);
    }

    // registrar un tipo no primitivo que viene de un import
    // como no se lee el archivo importado se crea sin reportar error :D
    Tipo registrarTipoNoPrimitivoImportado(String nombre, NodoAST nodo) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }

        // devolverlo si ya estaba registrado
        Tipo existente = this.tiposNoPrimitivos.get(nombre);
        if (existente != null) {
            return existente;
        }

        // crear el tipo placeholder
        Tipo tipo = new Tipo(nombre, false);
        this.tiposNoPrimitivos.put(nombre, tipo);
        if (this.ambitoGlobal != null) {
            this.ambitoGlobal.agregarTipo(tipo);
        }

        return tipo;
    }

    // marcar un tipo como que ya conocemos su esquema de campos
    void marcarEsquemaConocido(Tipo tipo) {
        if (tipo != null) {
            this.tiposConEsquema.add(tipo.getNombre());
        }
    }

    // verificar si conocemos el esquema de campos de un tipo
    boolean tieneEsquemaConocido(Tipo tipo) {
        if (tipo == null) {
            return false;
        }

        return this.tiposConEsquema.contains(tipo.getNombre());
    }

    // resolver un tipo por nombre: primitivo o no primitivo
    Tipo tipoParaNombre(String nombre, NodoAST nodo) {

        // primero buscar primitivo
        Tipo primitivo = tipoPrimitivo(nombre);
        if (primitivo != null) {
            return primitivo;
        }

        // si no, buscar o crear no primitivo
        return tipoNoPrimitivo(nombre, nodo, true);
    }

    // devolver un tipo marcador para "desconocido"
    Tipo tipoDesconocido() {
        return new Tipo("desconocido", false);
    }

    // devolver un tipo marcador para "vacio" (por ejemplo funciones void)
    Tipo tipoVacio() {
        return new Tipo("vacio", false);
    }

    // construir el tipo arreglo a partir de un tipo base
    Tipo tipoArray(Tipo tipoBase) {
        if (tipoBase == null) {
            return null;
        }

        // dimension 1, tipo base apuntado y sin campos
        Tipo tipo = new Tipo(tipoBase.getNombre(), tipoBase.esPrimitivo(), 1, tipoBase, new ArrayList<>(), null);

        // guardarlo como tipo extra para reportes
        agregarTipoExtra(tipo);

        return tipo;
    }

    // obtener el tipo de los elementos de un arreglo
    Tipo tipoElemento(Tipo tipo) {
        if (tipo == null || tipo.getDimension() <= 0) {
            return null;
        }

        if (tipo.getTipoBase() != null) {
            return tipo.getTipoBase();
        }

        return new Tipo(tipo.getNombre(), tipo.esPrimitivo());
    }

    // agregar un tipo auxiliar evitando duplicados por identidad
    void agregarTipoExtra(Tipo tipo) {
        if (tipo == null) {
            return;
        }

        // comparar por identidad, no por equals
        for (Tipo existente : this.tiposExtra) {
            if (existente == tipo) {
                return;
            }
        }

        this.tiposExtra.add(tipo);
    }

    // declarar una variable o arreglo en el ambito actual
    boolean declarar(NodoAST nodo, String nombre, Tipo tipo, CategoriaSimbolo categoria, Integer tamano) {
        if (nombre == null || nombre.isEmpty()) {
            agregarError(nodo, "declaracion sin identificador");
            return false;
        }

        // no permitir duplicados en el mismo ambito
        if (ambitoActual().buscarSimboloLocal(nombre) != null) {
            agregarError(nodo, "declaracion duplicada en el mismo ambito");
            return false;
        }

        // crear el simbolo con sus datos
        Simbolo simbolo = new Simbolo(nombre, categoria, tipo, null, tamano, new ArrayList<>(), -1, ambitoActual(), -1, nodo == null ? 0 : nodo.getLinea(), nodo == null ? 0 : nodo.getColumna());

        // intentar agregarlo al ambito
        if (!ambitoActual().agregarSimbolo(simbolo)) {
            agregarError(nodo, "declaracion duplicada en el mismo ambito");
            return false;
        }

        return true;
    }

    // resolver un simbolo por nombre buscando en todos los ambitos
    Simbolo resolver(String nombre, NodoAST nodo) {
        Simbolo simbolo = ambitoActual().buscarSimbolo(nombre);

        // reportar error si no existe
        if (simbolo == null && nodo != null) {
            agregarError(nodo, "variable no declarada");
        }

        return simbolo;
    }

    // resolver si existe por claridad semantica :D
    Simbolo resolverLocalOGlobal(String nombre, NodoAST nodo) {
        return resolver(nombre, nodo);
    }

    // verificar si un tipo es numerico (entero o decimal)
    boolean esNumerico(Tipo tipo) {
        if (tipo == null) {
            return false;
        }

        return "numerus".equals(tipo.getNombre()) || "decimalis".equals(tipo.getNombre());
    }

    // verificar si un tipo es entero
    boolean esEntero(Tipo tipo) {
        return tipo != null && "numerus".equals(tipo.getNombre());
    }

    // verificar si un tipo es booleano
    boolean esBooleano(Tipo tipo) {
        return tipo != null && "bool".equals(tipo.getNombre());
    }

    // verificar si un tipo es texto
    boolean esTexto(Tipo tipo) {
        return tipo != null && "textum".equals(tipo.getNombre());
    }

    // verificar si un tipo es no primitivo (no primitivo y sin dimension)
    boolean esNoPrimitivo(Tipo tipo) {
        return tipo != null && !tipo.esPrimitivo() && tipo.getDimension() == 0;
    }

    // verificar compatibilidad general entre dos tipos
    boolean esCompatible(Tipo esperado, Tipo real) {
        if (esperado == null || real == null) {
            return false;
        }

        // mismo objeto, compatibles
        if (esperado == real) {
            return true;
        }

        // dos numericos siempre compatibles
        if (esNumerico(esperado) && esNumerico(real)) {
            return true;
        }

        // dos arreglos: misma dimension, mismo nombre y bases compatibles
        if (esperado.getDimension() > 0 && real.getDimension() > 0) {
            return esperado.getDimension() == real.getDimension() && sonNombresCompatibles(esperado.getNombre(), real.getNombre()) && sonTiposBaseCompatibles(esperado.getTipoBase(), real.getTipoBase());
        }

        // tipos simples: misma dimension y mismo nombre
        return esperado.getDimension() == real.getDimension() && sonNombresCompatibles(esperado.getNombre(), real.getNombre());
    }

    // verificar si un tipo real se puede asignar a un tipo esperado
    boolean esAsignable(Tipo esperado, Tipo real) {
        if (esperado == null || real == null) {
            return false;
        }

        // mismo objeto, asignable
        if (esperado == real) {
            return true;
        }

        // entero se puede asignar a decimal
        if (esEntero(real) && "decimalis".equals(esperado.getNombre())) {
            return true;
        }

        // en el resto delegar a esCompatible
        return esCompatible(esperado, real);
    }

    // comparar dos nombres de tipo
    boolean sonNombresCompatibles(String nombreEsperado, String nombreReal) {
        if (nombreEsperado == null || nombreReal == null) {
            return false;
        }

        return nombreEsperado.equals(nombreReal);
    }

    // comparar dos tipos base de arreglos
    boolean sonTiposBaseCompatibles(Tipo baseEsperada, Tipo baseReal) {
        if (baseEsperada == null || baseReal == null) {
            return false;
        }

        return esCompatible(baseEsperada, baseReal);
    }

    // calcular el tipo de mayor jerarquia entre dos tipos
    // si hay decimalis gana decimalis, si no gana numerus :D
    Tipo tipoMayorJerarquia(Tipo izquierdo, Tipo derecho) {
        if (izquierdo == null) {
            return derecho;
        }
        if (derecho == null) {
            return izquierdo;
        }

        // concatenacion de textos
        if (esTexto(izquierdo) && esTexto(derecho)) {
            return izquierdo;
        }

        // ambos numericos
        if (esNumerico(izquierdo) && esNumerico(derecho)) {
            if ("decimalis".equals(izquierdo.getNombre()) || "decimalis".equals(derecho.getNombre())) {
                return tipoPrimitivo("decimalis");
            }
            return tipoPrimitivo("numerus");
        }

        return null;
    }

    // buscar un campo dentro de un tipo no primitivo
    Simbolo campo(Tipo tipo, String nombre, NodoAST nodo) {

        // solo se puede acceder a miembros de tipos no primitivoes
        if (!esNoPrimitivo(tipo)) {
            if (nodo != null) {
                agregarError(nodo, "acceso a miembro invalido");
            }

            return null;
        }

        // buscar el campo en el tipo
        Simbolo existente = tipo.buscarCampo(nombre);
        if (existente != null) {
            return existente;
        }

        // si no conocemos el esquema no podemos decir que no exista
        if (!tieneEsquemaConocido(tipo)) {
            return null;
        }

        // el esquema si se conoce y el campo no existe
        if (nodo != null) {
            agregarError(nodo, "miembro no declarado");
        }

        return null;
    }

    // aprender un campo de un tipo no primitivo a partir del uso
    Simbolo aprenderCampo(Tipo tipo, String nombre, Tipo tipoCampo, NodoAST nodo) {

        // solo valido para tipos no primitivoes con nombre
        if (!esNoPrimitivo(tipo) || nombre == null || nombre.isEmpty()) {
            return null;
        }

        // devolverlo si ya se conocia
        Simbolo existente = tipo.buscarCampo(nombre);
        if (existente != null) {
            return existente;
        }

        // crear el campo y agregarlo al tipo
        Simbolo campo = new Simbolo(nombre, CategoriaSimbolo.CAMPO_ESTRUCTURA, tipoCampo, null, nodo == null ? 0 : nodo.getLinea(), nodo == null ? 0 : nodo.getColumna());
        tipo.agregarCampo(campo);

        // ya conocemos el esquema del tipo :D
        marcarEsquemaConocido(tipo);

        return campo;
    }

    // validar los valores posicionales contra los campos del tipo
    void validarCamposPosicionales(Tipo tipo, List<Tipo> tiposValores, NodoAST nodo) {

        // solo se puede validar si conocemos el esquema
        if (tipo == null || tiposValores == null || !tieneEsquemaConocido(tipo)) {
            return;
        }

        List<Simbolo> campos = tipo.getCampos();

        // si hay mas valores que campos reportar error
        if (tiposValores.size() > campos.size()) {
            agregarError(nodo, "cantidad de atributos excede los miembros declarados");
            return;
        }

        // comparar valor por valor contra el campo en la misma posicion
        for (int i = 0; i < tiposValores.size(); i++) {
            Tipo esperado = null;
            if (i < campos.size()) {
                esperado = campos.get(i).getTipo();
            }
            validarCompatibilidad(esperado, tiposValores.get(i), nodo);
        }
    }

    // validar que un tipo real sea compatible con el esperado
    void validarCompatibilidad(Tipo esperado, Tipo real, NodoAST nodo) {
        if (esperado == null || real == null) {
            return;
        }
        if (!esCompatible(esperado, real)) {
            agregarError(nodo, "tipo incompatibles");
        }
    }

    /**
     * Obtener todos los simbolos registrados en el analisis.
     */
    public List<Simbolo> obtenerSimbolos() {
        List<Simbolo> resultado = new ArrayList<>();
        colectarSimbolos(this.ambitoGlobal, resultado);
        return resultado;
    }

    // recorrer el arbol de ambitos acumulando simbolos
    void colectarSimbolos(Ambito ambito, List<Simbolo> resultado) {
        if (ambito == null) {
            return;
        }

        resultado.addAll(ambito.getSimbolos());
        for (Ambito hijo : ambito.getAmbitos()) {
            colectarSimbolos(hijo, resultado);
        }
    }

    /**
     * Obtener todos los tipos registrados en el analisis.
     */
    public List<Tipo> obtenerTipos() {
        List<Tipo> resultado = new ArrayList<>();

        // agregar los tipos del ambito global
        if (this.ambitoGlobal != null) {
            resultado.addAll(this.ambitoGlobal.getTipos());
        }

        // agregar los tipos auxiliares (arreglos, etc etcccccc)
        resultado.addAll(this.tiposExtra);
        return resultado;
    }

    // obtener el recolector de errores
    RecolectorErrores obtenerRecolectorErrores() {
        return this.recolectorErrores;
    }
}