package org.jrg.analisis.comun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jrg.model.semantico.Ambito;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;

/**
 * Ambito semantico con tablas separadas para simbolos y sobrecargas.
 */
public class AmbitoSemantico {

    private final Ambito ambito;
    private final AmbitoSemantico padre;
    private final List<AmbitoSemantico> hijos;
    private final Map<String, List<Simbolo>> simbolosPorNombre;
    private final Map<String, List<Simbolo>> metodosPorNombre;
    private final Map<String, List<Simbolo>> constructoresPorNombre;
    private final Map<String, Tipo> tiposPorNombre;
    private final List<Simbolo> simbolos;
    private final List<Tipo> tipos;

    /**
     * Crear un ambito con un ambito padre.
     */
    public AmbitoSemantico(String nombre, AmbitoSemantico padre) {
        if (padre == null) {
            this.ambito = new Ambito(nombre);
        } else {
            this.ambito = new Ambito(nombre, padre.ambito);
        }
        this.padre = padre;
        this.hijos = new ArrayList<>();
        this.simbolosPorNombre = new HashMap<>();
        this.metodosPorNombre = new HashMap<>();
        this.constructoresPorNombre = new HashMap<>();
        this.tiposPorNombre = new HashMap<>();
        this.simbolos = new ArrayList<>();
        this.tipos = new ArrayList<>();
        // registrar este ambito en el padre para poder recorrer el arbol
        if (padre != null) {
            padre.hijos.add(this);
        }
    }

    /**
     * Declarar un simbolo variable en este ambito.
     */
    public boolean declararSimbolo(Simbolo simbolo, boolean enAmbito) {
        if (simbolo == null) {
            return false;
        }
        String nombre = simbolo.getNombre();
        List<Simbolo> existentes = simbolosPorNombre.get(nombre);
        if (existentes == null) {
            existentes = new ArrayList<>();
            simbolosPorNombre.put(nombre, existentes);
        }
        if (!existentes.isEmpty()) {
            return false;
        }
        existentes.add(simbolo);
        simbolos.add(simbolo);
        if (enAmbito) {
            ambito.agregarSimbolo(simbolo);
        }
        return true;
    }

    /**
     * Declarar un metodo sobrecargado.
     */
    public void declararMetodo(Simbolo metodo) {
        if (metodo == null) {
            return;
        }
        String nombre = metodo.getNombre();
        List<Simbolo> existentes = metodosPorNombre.get(nombre);
        if (existentes == null) {
            existentes = new ArrayList<>();
            metodosPorNombre.put(nombre, existentes);
        }
        existentes.add(metodo);
    }

    /**
     * Declarar un constructor sobrecargado.
     */
    public void declararConstructor(Simbolo constructor) {
        if (constructor == null) {
            return;
        }
        String nombre = constructor.getNombre();
        List<Simbolo> existentes = constructoresPorNombre.get(nombre);
        if (existentes == null) {
            existentes = new ArrayList<>();
            constructoresPorNombre.put(nombre, existentes);
        }
        existentes.add(constructor);
    }

    /**
     * Declarar un tipo nominal en este ambito.
     */
    public void declararTipo(Tipo tipo) {
        if (tipo == null || tiposPorNombre.containsKey(tipo.getNombre())) {
            return;
        }
        tiposPorNombre.put(tipo.getNombre(), tipo);
        tipos.add(tipo);
        ambito.agregarTipo(tipo);
    }

    /**
     * Buscar un simbolo variable en este ambito y sus padres.
     */
    public Simbolo buscarSimbolo(String nombre) {
        // buscar simbolo en ambito local
        Simbolo local = buscarSimboloLocal(nombre);
        if (local != null) {
            return local;
        }

        if (padre == null) {
            return null;
        }

        // delegar busqueda al ambito padre
        return padre.buscarSimbolo(nombre);
    }

    /**
     * Buscar un simbolo variable solo en este ambito.
     */
    public Simbolo buscarSimboloLocal(String nombre) {
        if (nombre == null) {
            return null;
        }

        // obtener lista de candidatos por nombre
        List<Simbolo> candidatos = simbolosPorNombre.get(nombre);
        if (candidatos == null || candidatos.isEmpty()) {
            return null;
        }

        return candidatos.get(candidatos.size() - 1);
    }

    /**
     * Verificar si existe un simbolo local con el nombre indicado.
     */
    public boolean contieneSimboloLocal(String nombre) {
        return buscarSimboloLocal(nombre) != null;
    }

    /**
     * Buscar una variable en este ambito y sus padres.
     */
    public Simbolo buscarVariable(String nombre) {
        Simbolo simbolo = buscarSimbolo(nombre);
        if (simbolo == null) {
            return null;
        }
        if (simbolo.getCategoria() == org.jrg.model.semantico.CategoriaSimbolo.VARIABLE
                || simbolo.getCategoria() == org.jrg.model.semantico.CategoriaSimbolo.PARAMETRO
                || simbolo.getCategoria() == org.jrg.model.semantico.CategoriaSimbolo.CAMPO_ESTRUCTURA
                || simbolo.getCategoria() == org.jrg.model.semantico.CategoriaSimbolo.CONSTANTE
                || simbolo.getCategoria() == org.jrg.model.semantico.CategoriaSimbolo.ARREGLO
                || simbolo.getCategoria() == org.jrg.model.semantico.CategoriaSimbolo.OBJETO) {
            return simbolo;
        }
        return null;
    }

    /**
     * Obtener los metodos locales con el nombre indicado.
     */
    public List<Simbolo> obtenerMetodosLocal(String nombre) {
        List<Simbolo> locales = metodosPorNombre.get(nombre);
        if (locales == null) {
            return new ArrayList<>();
        }
        List<Simbolo> resultado = new ArrayList<>();
        resultado.addAll(locales);
        return resultado;
    }

    /**
     * Buscar metodos en este ambito y sus padres.
     */
    public List<Simbolo> buscarMetodos(String nombre) {
        // obtener metodos locales como base
        List<Simbolo> resultado = obtenerMetodosLocal(nombre);
        if (padre != null) {
            List<Simbolo> padres = padre.buscarMetodos(nombre);
            if (padres != null) {
                resultado.addAll(padres);
            }
        }

        // devolver lista combinada
        return resultado;
    }

    /**
     * Obtener los constructores locales con el nombre indicado.
     */
    public List<Simbolo> obtenerConstructoresLocal(String nombre) {
        List<Simbolo> locales = constructoresPorNombre.get(nombre);
        if (locales == null) {
            return new ArrayList<>();
        }
        List<Simbolo> resultado = new ArrayList<>();
        resultado.addAll(locales);
        return resultado;
    }

    /**
     * Buscar constructores en este ambito.
     */
    public List<Simbolo> buscarConstructores(String nombre) {
        return obtenerConstructoresLocal(nombre);
    }

    /**
     * Buscar un tipo en este ambito y sus padres.
     */
    public Tipo buscarTipo(String nombre) {
        Tipo local = tiposPorNombre.get(nombre);
        if (local != null) {
            return local;
        }
        if (padre == null) {
            return null;
        }
        return padre.buscarTipo(nombre);
    }

    /**
     * Verificar si un simbolo existe en los padres.
     */
    public boolean contieneEnPadres(String nombre) {
        if (padre == null) {
            return false;
        }
        return padre.buscarSimbolo(nombre) != null;
    }

    /**
     * Verificar si un tipo existe en los padres.
     */
    public boolean contieneTipoEnPadres(String nombre) {
        if (padre == null) {
            return false;
        }
        return padre.buscarTipo(nombre) != null;
    }

    /**
     * Obtener el ambito base.
     */
    public Ambito obtenerAmbito() {
        return ambito;
    }

    /**
     * Obtener el ambito padre.
     */
    public AmbitoSemantico obtenerPadre() {
        return padre;
    }

    /**
     * Obtener los simbolos declarados en este ambito.
     */
    public List<Simbolo> obtenerSimbolos() {
        return simbolos;
    }

    /**
     * Obtener los tipos declarados en este ambito.
     */
    public List<Tipo> obtenerTipos() {
        return tipos;
    }

    /**
     * Obtener los ambitos hijos registrados en este ambito.
     */
    public List<AmbitoSemantico> obtenerHijos() {
        return hijos;
    }

    /**
     * Obtener todos los metodos y constructores declarados en este ambito.
     */
    public List<Simbolo> obtenerTodosLosMetodos() {
        List<Simbolo> resultado = new ArrayList<>();
        for (List<Simbolo> lista : metodosPorNombre.values()) {
            resultado.addAll(lista);
        }
        for (List<Simbolo> lista : constructoresPorNombre.values()) {
            resultado.addAll(lista);
        }
        return resultado;
    }

    /**
     * Obtener el nombre del ambito.
     */
    public String obtenerNombre() {
        return ambito.getNombre();
    }
}