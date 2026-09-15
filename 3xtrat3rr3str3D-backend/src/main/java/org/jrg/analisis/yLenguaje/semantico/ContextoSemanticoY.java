package org.jrg.analisis.yLenguaje.semantico;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrg.analisis.comun.AmbitoSemantico;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.error.TipoError;
import org.jrg.model.semantico.CategoriaSimbolo;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;
import org.jrg.service.error.RecolectorErrores;

// contexto semantico del lenguaje Y
// es la memoria del analizador: guarda ambitos, simbolos, tipos y errores
public class ContextoSemanticoY {

    // recolector donde se acumulan los errores semanticos
    private final RecolectorErrores recolectorErrores;
    // ambito raiz del programa
    private AmbitoSemantico ambitoGlobal;
    // pila de ambitos activos donde el actual es siempre el ultimo que entro
    private final Deque<AmbitoSemantico> ambitos;
    // tipos primitivos del lenguaje: entero, flotante, cadena, caracter, booleano :D
    private final Map<String, Tipo> tiposPrimitivos;
    // profundidad actual de ciclos anidados
    private int profundidadCiclos;

    /**
     * Crear el contexto semantico para el lenguaje Y.
     */
    public ContextoSemanticoY(RecolectorErrores recolectorErrores) {
        // si no viene recolector se crea uno nuevo
        if (recolectorErrores == null) {
            this.recolectorErrores = new RecolectorErrores();
        } else {
            this.recolectorErrores = recolectorErrores;
        }

        // inicializar las estructuras internas :D
        this.ambitos = new ArrayDeque<>();
        this.tiposPrimitivos = new HashMap<>();
        this.profundidadCiclos = 0;
    }

    /**
     * Iniciar el contexto registrando los tipos primitivos.
     */
    public void iniciar() {
        // crear el ambito global
        this.ambitoGlobal = new AmbitoSemantico("global", null);

        // limpiar todo lo anterior
        this.ambitos.clear();
        this.tiposPrimitivos.clear();
        this.profundidadCiclos = 0;

        // registrar los tipos primitivos del lenguaje
        registrarTiposPrimitivos();

        // apilar el ambito global como ambito actual
        this.ambitos.addLast(this.ambitoGlobal);
    }

    // registrar los cinco tipos primitivos del lenguaje
    private void registrarTiposPrimitivos() {
        agregarTipoPrimitivo("entero");
        agregarTipoPrimitivo("cadena");
        agregarTipoPrimitivo("flotante");
        agregarTipoPrimitivo("caracter");
        agregarTipoPrimitivo("booleano");
    }

    // crear un tipo primitivo y registrarlo en el ambito global
    private void agregarTipoPrimitivo(String nombre) {
        Tipo tipo = new Tipo(nombre, true);
        this.tiposPrimitivos.put(nombre, tipo);

        if (this.ambitoGlobal != null) {
            this.ambitoGlobal.declararTipo(tipo);
        }

    }

    /**
     * Agregar un error semantico al recolector.
     */
    public void agregarError(NodoASTY nodo, String descripcion) {
        int linea = 0;
        int columna = 0;

        // tomar linea y columna del nodo si existe
        if (nodo != null) {
            linea = nodo.getLinea();
            columna = nodo.getColumna();
        }

        this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, descripcion);
    }

    /**
     * Obtener el ambito activo en este momento.
     */
    public AmbitoSemantico ambitoActual() {

        // si no hay ambitos devolver el global
        if (this.ambitos.isEmpty()) {
            return this.ambitoGlobal;
        }

        return this.ambitos.peekLast();
    }

    /**
     * Entrar a un ambito nuevo hijo del actual.
     */
    public void entrarAmbito(String nombre) {
        AmbitoSemantico padre = ambitoActual();
        AmbitoSemantico nuevo = new AmbitoSemantico(nombre, padre);


        // el constructor de AmbitoSemantico ya registra el hijo en el padre :D

        // enlazar el nuevo ambito con su padre
        //if (padre != null) {
        //    padre.obtenerAmbito().agregarAmbito(nuevo.obtenerAmbito());
        //}


        // apilarlo como ambito actual :D
        this.ambitos.addLast(nuevo);
    }

    /**
     * Salir del ambito actual regresando al padre.
     */
    public void salirAmbito() {

        // nunca sacar el ambito global
        if (this.ambitos.size() > 1) {
            this.ambitos.removeLast();
        }

    }

    /**
     * Entrar a un ciclo incrementando la profundidad.
     */
    public void entrarCiclo() {
        this.profundidadCiclos++;
    }

    /**
     * Salir del ciclo actual decrementando la profundidad.
     */
    public void salirCiclo() {
        if (this.profundidadCiclos > 0) {
            this.profundidadCiclos--;
        }
    }

    /**
     * Verificar si el analisis esta dentro de un ciclo.
     */
    public boolean estaDentroDeCiclo() {
        return this.profundidadCiclos > 0;
    }

    /**
     * Obtener un tipo primitivo por nombre.
     */
    public Tipo tipoPrimitivo(String nombre) {
        if (nombre == null) {
            return null;
        }
        return this.tiposPrimitivos.get(nombre);
    }

    /**
     * Buscar un tipo en los ambitos o en los primitivos.
     */
    public Tipo tipoPorNombre(String nombre) {
        if (nombre == null) {
            return null;
        }

        // primero buscar primitivo
        Tipo primitivo = tipoPrimitivo(nombre);
        if (primitivo != null) {
            return primitivo;
        }

        // buscar en el ambito actual
        if (ambitoActual() != null) {
            Tipo existente = ambitoActual().buscarTipo(nombre);
            if (existente != null) {
                return existente;
            }
        }

        // por ultimo buscar en el ambito global :c
        if (this.ambitoGlobal != null) {
            return this.ambitoGlobal.buscarTipo(nombre);
        }

        return null;
    }

    /**
     * Declarar un tipo en el ambito actual.
     */
    public boolean declararTipo(NodoASTY nodo, Tipo tipo) {
        if (tipo == null) {
            return false;
        }

        // no permitir tipos duplicados >:c
        if (ambitoActual().buscarTipo(tipo.getNombre()) != null) {
            agregarError(nodo, "el tipo '" + tipo.getNombre() + "' ya esta declarado");
            return false;
        }

        ambitoActual().declararTipo(tipo);
        return true;
    }

    /**
     * Declarar un simbolo en el ambito actual.
     */
    public boolean declarar(NodoASTY nodo, String nombre, Tipo tipo, CategoriaSimbolo categoria, Integer tamano) {
        if (nombre == null || nombre.isEmpty()) {
            agregarError(nodo, "declaracion sin identificador");
            return false;
        }

        // no permitir duplicados en el mismo ambito >:c
        if (ambitoActual().buscarSimboloLocal(nombre) != null) {
            agregarError(nodo, "declaracion duplicada en el mismo ambito");
            return false;
        }

        // crear el simbolo con sus datos
        Simbolo simbolo = new Simbolo(nombre, categoria, tipo);
        simbolo.setFila(nodo == null ? 0 : nodo.getLinea());
        simbolo.setColumna(nodo == null ? 0 : nodo.getColumna());
        simbolo.setTamano(tamano);

        // intentar agregarlo al ambito
        if (!ambitoActual().declararSimbolo(simbolo, true)) {
            agregarError(nodo, "declaracion duplicada");
            return false;
        }

        return true;
    }

    /**
     * Resolver un simbolo por nombre buscando en todos los ambitos.
     */
    public Simbolo resolver(String nombre, NodoASTY nodo) {
        Simbolo simbolo = ambitoActual().buscarSimbolo(nombre);

        // reportar error si no existe
        if (simbolo == null) {
            agregarError(nodo, "variable no declarada: " + nombre);
        }

        return simbolo;
    }

    /**
     * Construir el tipo de un arreglo a partir del tipo base.
     */
    public Tipo tipoArray(Tipo base) {
        if (base == null) {
            return null;
        }

        // dimension 1, tipo base apuntado y sin campos :D
        Tipo tipo = new Tipo(base.getNombre(), base.esPrimitivo(), 1, base, new ArrayList<>(), null);
        return tipo;
    }

    /**
     * Obtener el tipo de los elementos de un arreglo.
     */
    public Tipo tipoElemento(Tipo tipo) {
        if (tipo == null || tipo.getDimension() <= 0) {
            return null;
        }

        // si tiene tipo base apuntado, devolverlo
        if (tipo.getTipoBase() != null) {
            return tipo.getTipoBase();
        }

        return new Tipo(tipo.getNombre(), tipo.esPrimitivo());
    }

    /**
     * Verificar si un tipo es numerico.
     */
    public boolean esNumerico(Tipo tipo) {
        if (tipo == null) {
            return false;
        }

        // entero, flotante y caracter cuentan como numericos :D
        String nombre = tipo.getNombre();

        return "entero".equals(nombre) || "flotante".equals(nombre) || "caracter".equals(nombre);
    }

    /**
     * Verificar si un tipo es entero.
     */
    public boolean esEntero(Tipo tipo) {
        return tipo != null && "entero".equals(tipo.getNombre());
    }

    /**
     * Verificar si un tipo es flotante.
     */
    public boolean esFlotante(Tipo tipo) {
        return tipo != null && "flotante".equals(tipo.getNombre());
    }

    /**
     * Verificar si un tipo es caracter.
     */
    public boolean esCaracter(Tipo tipo) {
        return tipo != null && "caracter".equals(tipo.getNombre());
    }

    /**
     * Verificar si un tipo es booleano.
     */
    public boolean esBooleano(Tipo tipo) {
        return tipo != null && "booleano".equals(tipo.getNombre());
    }

    /**
     * Verificar si un tipo es cadena.
     */
    public boolean esCadena(Tipo tipo) {
        return tipo != null && "cadena".equals(tipo.getNombre());
    }

    /**
     * Verificar si un tipo es asignable a otro.
     */
    public boolean esCompatible(Tipo esperado, Tipo real) {
        if (esperado == null || real == null) {
            return false;
        }

        // mismo nombre y misma dimension son compatibles :D
        if (esperado.getNombre().equals(real.getNombre()) && esperado.getDimension() == real.getDimension()) {
            return true;
        }

        // entero o caracter se pueden asignar a flotante
        if (esFlotante(esperado)) {
            if (esEntero(real) || esCaracter(real)) {
                return true;
            }
        }

        // caracter se puede asignar a entero
        if (esEntero(esperado) && esCaracter(real)) {
            return true;
        }

        // dos arreglos: misma dimension y mismo nombre
        if (esperado.getDimension() > 0 && real.getDimension() > 0) {
            return esperado.getDimension() == real.getDimension() && esperado.getNombre().equals(real.getNombre());
        }

        return false;
    }

    /**
     * Obtener el tipo de mayor jerarquia entre dos tipos numericos.
     */
    public Tipo tipoMayorJerarquia(Tipo a, Tipo b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        // si alguno es flotante gana flotante :D
        if (esFlotante(a) || esFlotante(b)) {
            return tipoPrimitivo("flotante");
        }

        // si alguno es entero gana entero :D
        if (esEntero(a) || esEntero(b)) {
            return tipoPrimitivo("entero");
        }
        return a;
    }

    /**
     * Obtener todos los simbolos registrados.
     */
    public List<Simbolo> obtenerSimbolos() {
        List<Simbolo> resultado = new ArrayList<>();
        colectarSimbolos(this.ambitoGlobal, resultado);
        return resultado;
    }

    // recorrer el arbol de ambitos acumulando simbolos, metodos y constructores
    private void colectarSimbolos(AmbitoSemantico ambito, List<Simbolo> resultado) {
        if (ambito == null) {
            return;
        }

        resultado.addAll(ambito.obtenerSimbolos());
        resultado.addAll(ambito.obtenerTodosLosMetodos());

        // bajar recursivamente a los hijos
        for (AmbitoSemantico hijo : ambito.obtenerHijos()) {
            colectarSimbolos(hijo, resultado);
        }
    }

    /**
     * Obtener todos los tipos registrados.
     */
    public List<Tipo> obtenerTipos() {
        List<Tipo> resultado = new ArrayList<>();
        colectarTipos(this.ambitoGlobal, resultado);

        return resultado;
    }

    // recorrer el arbol de ambitos acumulando tipos
    private void colectarTipos(AmbitoSemantico ambito, List<Tipo> resultado) {
        if (ambito == null) {
            return;
        }

        resultado.addAll(ambito.obtenerTipos());

        // bajar recursivamente a los hijos
        for (AmbitoSemantico hijo : ambito.obtenerHijos()) {
            colectarTipos(hijo, resultado);
        }
    }

    /**
     * Obtener el recolector de errores.
     */
    public RecolectorErrores obtenerRecolectorErrores() {
        return this.recolectorErrores;
    }
}