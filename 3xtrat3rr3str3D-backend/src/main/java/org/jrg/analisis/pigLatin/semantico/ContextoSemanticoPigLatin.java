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
import org.jrg.model.pigLatin.base.NodoAST;
import org.jrg.model.semantico.Ambito;
import org.jrg.model.semantico.CategoriaSimbolo;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;
import org.jrg.service.error.RecolectorErrores;

class ContextoSemanticoPigLatin {

    private final RecolectorErrores recolectorErrores;
    private Ambito ambitoGlobal;
    private final Deque<Ambito> ambitos;
    private final List<Tipo> tiposExtra;
    private final Map<String, Tipo> tiposPrimitivos;
    private final Map<String, Tipo> tiposNominales;
    private final Set<String> tiposConEsquema;
    private int contadorAmbitos;
    private int profundidadCiclos;

    ContextoSemanticoPigLatin(RecolectorErrores recolectorErrores) {
        if (recolectorErrores == null) {
            this.recolectorErrores = new RecolectorErrores();
        } else {
            this.recolectorErrores = recolectorErrores;
        }
        this.ambitos = new ArrayDeque<>();
        this.tiposExtra = new ArrayList<>();
        this.tiposPrimitivos = new HashMap<>();
        this.tiposNominales = new HashMap<>();
        this.tiposConEsquema = new HashSet<>();
        this.contadorAmbitos = 0;
        this.profundidadCiclos = 0;
    }

    void iniciar() {
        this.ambitoGlobal = new Ambito("global");
        this.ambitos.clear();
        this.tiposExtra.clear();
        this.tiposPrimitivos.clear();
        this.tiposNominales.clear();
        this.tiposConEsquema.clear();
        this.contadorAmbitos = 0;
        this.profundidadCiclos = 0;
        registrarTiposPrimitivos();
        this.ambitos.addLast(this.ambitoGlobal);
    }

    void registrarTiposPrimitivos() {
        agregarTipoPrimitivo("numerus", true);
        agregarTipoPrimitivo("decimalis", true);
        agregarTipoPrimitivo("textum", true);
        agregarTipoPrimitivo("littera", true);
        agregarTipoPrimitivo("bool", true);
    }

    void agregarTipoPrimitivo(String nombre, boolean esPrimitivo) {
        Tipo tipo = new Tipo(nombre, esPrimitivo);
        this.tiposPrimitivos.put(nombre, tipo);
        if (this.ambitoGlobal != null) {
            this.ambitoGlobal.agregarTipo(tipo);
        }
    }

    void agregarError(NodoAST nodo, String descripcion) {
        int linea = 0;
        int columna = 0;
        if (nodo != null) {
            linea = nodo.getLinea();
            columna = nodo.getColumna();
        }
        agregarError(linea, columna, descripcion);
    }

    void agregarError(int linea, int columna, String descripcion) {
        this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, descripcion);
    }

    Ambito ambitoActual() {
        if (this.ambitos.isEmpty()) {
            return this.ambitoGlobal;
        }
        return this.ambitos.peekLast();
    }

    void entrarAmbito(String nombre) {
        this.contadorAmbitos++;
        Ambito padre = ambitoActual();
        String nombreAmbito = nombre + "_" + this.contadorAmbitos;
        Ambito nuevoAmbito = new Ambito(nombreAmbito, padre);
        if (padre != null) {
            padre.agregarAmbito(nuevoAmbito);
        }
        this.ambitos.addLast(nuevoAmbito);
    }

    void salirAmbito() {
        if (this.ambitos.size() > 1) {
            this.ambitos.removeLast();
        }
    }

    void entrarCiclo() {
        this.profundidadCiclos++;
    }

    void salirCiclo() {
        if (this.profundidadCiclos > 0) {
            this.profundidadCiclos--;
        }
    }

    boolean estaDentroDeCiclo() {
        return this.profundidadCiclos > 0;
    }

    String nombreAmbitoHijo(String prefijo) {
        this.contadorAmbitos++;
        return prefijo + "_" + this.contadorAmbitos;
    }

    Tipo tipoPrimitivo(String nombre) {
        if (nombre == null) {
            return null;
        }
        return this.tiposPrimitivos.get(nombre);
    }

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

    Tipo tipoNominal(String nombre, NodoAST nodo, boolean crear) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        Tipo existente = this.tiposNominales.get(nombre);
        if (existente != null) {
            return existente;
        }
        if (!crear) {
            return null;
        }
        Tipo tipo = new Tipo(nombre, false);
        this.tiposNominales.put(nombre, tipo);
        if (this.ambitoGlobal != null) {
            this.ambitoGlobal.agregarTipo(tipo);
        }
        if (nodo != null) {
            agregarError(nodo, "tipo no declarado");
        }
        return tipo;
    }

    Tipo tipoNominalExistente(String nombre) {
        if (nombre == null) {
            return null;
        }
        return this.tiposNominales.get(nombre);
    }

    Tipo registrarTipoNominalImportado(String nombre, NodoAST nodo) {
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }
        Tipo existente = this.tiposNominales.get(nombre);
        if (existente != null) {
            return existente;
        }
        Tipo tipo = new Tipo(nombre, false);
        this.tiposNominales.put(nombre, tipo);
        if (this.ambitoGlobal != null) {
            this.ambitoGlobal.agregarTipo(tipo);
        }
        return tipo;
    }

    void marcarEsquemaConocido(Tipo tipo) {
        if (tipo != null) {
            this.tiposConEsquema.add(tipo.getNombre());
        }
    }

    boolean tieneEsquemaConocido(Tipo tipo) {
        if (tipo == null) {
            return false;
        }
        return this.tiposConEsquema.contains(tipo.getNombre());
    }

    Tipo tipoParaNombre(String nombre, NodoAST nodo) {
        Tipo primitivo = tipoPrimitivo(nombre);
        if (primitivo != null) {
            return primitivo;
        }
        return tipoNominal(nombre, nodo, true);
    }

    Tipo tipoDesconocido() {
        return new Tipo("desconocido", false);
    }

    Tipo tipoVacio() {
        return new Tipo("vacio", false);
    }

    Tipo tipoArray(Tipo tipoBase) {
        if (tipoBase == null) {
            return null;
        }
        Tipo tipo = new Tipo(tipoBase.getNombre(), tipoBase.esPrimitivo(), 1, tipoBase, new ArrayList<>(), null);
        agregarTipoExtra(tipo);
        return tipo;
    }

    Tipo tipoElemento(Tipo tipo) {
        if (tipo == null || tipo.getDimension() <= 0) {
            return null;
        }
        if (tipo.getTipoBase() != null) {
            return tipo.getTipoBase();
        }
        return new Tipo(tipo.getNombre(), tipo.esPrimitivo());
    }

    void agregarTipoExtra(Tipo tipo) {
        if (tipo == null) {
            return;
        }
        for (Tipo existente : this.tiposExtra) {
            if (existente == tipo) {
                return;
            }
        }
        this.tiposExtra.add(tipo);
    }

    boolean declarar(NodoAST nodo, String nombre, Tipo tipo, CategoriaSimbolo categoria, Integer tamano) {
        if (nombre == null || nombre.isEmpty()) {
            agregarError(nodo, "declaracion sin identificador");
            return false;
        }
        if (ambitoActual().buscarSimboloLocal(nombre) != null) {
            agregarError(nodo, "declaracion duplicada en el mismo ambito");
            return false;
        }
        Simbolo simbolo = new Simbolo(
            nombre,
            categoria,
            tipo,
            null,
            tamano,
            new ArrayList<>(),
            -1,
            ambitoActual(),
            -1,
            nodo == null ? 0 : nodo.getLinea(),
            nodo == null ? 0 : nodo.getColumna()
        );
        if (!ambitoActual().agregarSimbolo(simbolo)) {
            agregarError(nodo, "declaracion duplicada en el mismo ambito");
            return false;
        }
        return true;
    }

    Simbolo resolver(String nombre, NodoAST nodo) {
        Simbolo simbolo = ambitoActual().buscarSimbolo(nombre);
        if (simbolo == null && nodo != null) {
            agregarError(nodo, "variable no declarada");
        }
        return simbolo;
    }

    Simbolo resolverLocalOGlobal(String nombre, NodoAST nodo) {
        return resolver(nombre, nodo);
    }

    boolean esNumerico(Tipo tipo) {
        if (tipo == null) {
            return false;
        }
        return "numerus".equals(tipo.getNombre()) || "decimalis".equals(tipo.getNombre());
    }

    boolean esEntero(Tipo tipo) {
        return tipo != null && "numerus".equals(tipo.getNombre());
    }

    boolean esBooleano(Tipo tipo) {
        return tipo != null && "bool".equals(tipo.getNombre());
    }

    boolean esTexto(Tipo tipo) {
        return tipo != null && "textum".equals(tipo.getNombre());
    }

    boolean esNominal(Tipo tipo) {
        return tipo != null && !tipo.esPrimitivo() && tipo.getDimension() == 0;
    }

    boolean esCompatible(Tipo esperado, Tipo real) {
        if (esperado == null || real == null) {
            return false;
        }
        if (esperado == real) {
            return true;
        }
        if (esNumerico(esperado) && esNumerico(real)) {
            return true;
        }
        if (esperado.getDimension() > 0 && real.getDimension() > 0) {
            return esperado.getDimension() == real.getDimension()
                && sonNombresCompatibles(esperado.getNombre(), real.getNombre())
                && sonTiposBaseCompatibles(esperado.getTipoBase(), real.getTipoBase());
        }
        return esperado.getDimension() == real.getDimension()
            && sonNombresCompatibles(esperado.getNombre(), real.getNombre());
    }

    boolean esAsignable(Tipo esperado, Tipo real) {
        if (esperado == null || real == null) {
            return false;
        }
        if (esperado == real) {
            return true;
        }
        if (esEntero(real) && "decimalis".equals(esperado.getNombre())) {
            return true;
        }
        return esCompatible(esperado, real);
    }

    boolean sonNombresCompatibles(String nombreEsperado, String nombreReal) {
        if (nombreEsperado == null || nombreReal == null) {
            return false;
        }
        return nombreEsperado.equals(nombreReal);
    }

    boolean sonTiposBaseCompatibles(Tipo baseEsperada, Tipo baseReal) {
        if (baseEsperada == null || baseReal == null) {
            return false;
        }
        return esCompatible(baseEsperada, baseReal);
    }

    Tipo tipoPromovido(Tipo izquierdo, Tipo derecho) {
        if (izquierdo == null) {
            return derecho;
        }
        if (derecho == null) {
            return izquierdo;
        }
        if (esTexto(izquierdo) && esTexto(derecho)) {
            return izquierdo;
        }
        if (esNumerico(izquierdo) && esNumerico(derecho)) {
            if ("decimalis".equals(izquierdo.getNombre()) || "decimalis".equals(derecho.getNombre())) {
                return tipoPrimitivo("decimalis");
            }
            return tipoPrimitivo("numerus");
        }
        return null;
    }

    Simbolo campo(Tipo tipo, String nombre, NodoAST nodo) {
        if (!esNominal(tipo)) {
            if (nodo != null) {
                agregarError(nodo, "acceso a miembro invalido");
            }
            return null;
        }
        Simbolo existente = tipo.buscarCampo(nombre);
        if (existente != null) {
            return existente;
        }
        if (!tieneEsquemaConocido(tipo)) {
            return null;
        }
        if (nodo != null) {
            agregarError(nodo, "miembro no declarado");
        }
        return null;
    }

    Simbolo aprenderCampo(Tipo tipo, String nombre, Tipo tipoCampo, NodoAST nodo) {
        if (!esNominal(tipo) || nombre == null || nombre.isEmpty()) {
            return null;
        }
        Simbolo existente = tipo.buscarCampo(nombre);
        if (existente != null) {
            return existente;
        }
        Simbolo campo = new Simbolo(
            nombre,
            CategoriaSimbolo.CAMPO_ESTRUCTURA,
            tipoCampo,
            null,
            nodo == null ? 0 : nodo.getLinea(),
            nodo == null ? 0 : nodo.getColumna()
        );
        tipo.agregarCampo(campo);
        marcarEsquemaConocido(tipo);
        return campo;
    }

    void validarCamposPosicionales(Tipo tipo, List<Tipo> tiposValores, NodoAST nodo) {
        if (tipo == null || tiposValores == null || !tieneEsquemaConocido(tipo)) {
            return;
        }
        List<Simbolo> campos = tipo.getCampos();
        if (tiposValores.size() > campos.size()) {
            agregarError(nodo, "cantidad de atributos excede los miembros declarados");
            return;
        }
        for (int i = 0; i < tiposValores.size(); i++) {
            Tipo esperado = null;
            if (i < campos.size()) {
                esperado = campos.get(i).getTipo();
            }
            validarCompatibilidad(esperado, tiposValores.get(i), nodo);
        }
    }

    void validarCompatibilidad(Tipo esperado, Tipo real, NodoAST nodo) {
        if (esperado == null || real == null) {
            return;
        }
        if (!esCompatible(esperado, real)) {
            agregarError(nodo, "tipo incompatibles");
        }
    }

    List<Simbolo> obtenerSimbolos() {
        List<Simbolo> resultado = new ArrayList<>();
        colectarSimbolos(this.ambitoGlobal, resultado);
        return resultado;
    }

    void colectarSimbolos(Ambito ambito, List<Simbolo> resultado) {
        if (ambito == null) {
            return;
        }
        resultado.addAll(ambito.getSimbolos());
        for (Ambito hijo : ambito.getAmbitos()) {
            colectarSimbolos(hijo, resultado);
        }
    }

    List<Tipo> obtenerTipos() {
        List<Tipo> resultado = new ArrayList<>();
        if (this.ambitoGlobal != null) {
            resultado.addAll(this.ambitoGlobal.getTipos());
        }
        resultado.addAll(this.tiposExtra);
        return resultado;
    }

    RecolectorErrores obtenerRecolectorErrores() {
        return this.recolectorErrores;
    }
}
