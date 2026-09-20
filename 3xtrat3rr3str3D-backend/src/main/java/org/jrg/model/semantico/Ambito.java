package org.jrg.model.semantico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jrg.util.MiniUUID;

public class Ambito {

    private final String id;
    private final String nombre;
    private Ambito padre;
    private final List<Simbolo> simbolos;
    private final Map<String, Simbolo> simbolosPorNombre;
    private final List<Tipo> tipos;
    private final Map<String, Tipo> tiposPorNombre;
    private final List<Ambito> ambitos;
    private final Map<String, Ambito> ambitosPorNombre;
    private int siguientePosicion;

    /**
     * Crear un ambito global o nominal.
     */
    public Ambito(String nombre) {
        this(nombre, null);
    }

    /**
     * Crear un ambito con un ambito padre.
     */
    public Ambito(String nombre, Ambito padre) {
        this.id = MiniUUID.generate();
        // normalizar nombre nulo
        if (nombre == null) {
            this.nombre = "";
        } else {
            this.nombre = nombre;
        }

        this.padre = padre;
        this.simbolos = new ArrayList<>();
        this.simbolosPorNombre = new HashMap<>();
        this.tipos = new ArrayList<>();
        this.tiposPorNombre = new HashMap<>();
        this.ambitos = new ArrayList<>();
        this.ambitosPorNombre = new HashMap<>();
        this.siguientePosicion = 0;
    }

    /**
     * Obtener el identificador del ambito.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtener el nombre del ambito.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtener el ambito padre.
     */
    public Ambito getPadre() {
        return padre;
    }

    /**
     * Asignar el ambito padre.
     */
    public void setPadre(Ambito padre) {
        this.padre = padre;
    }

    /**
     * Agregar un simbolo al ambito.
     */
    public boolean agregarSimbolo(Simbolo simbolo) {
        // validar duplicado
        if (simbolo == null || simbolosPorNombre.containsKey(simbolo.getNombre())) {
            return false;
        }

        simbolo.setAmbito(this);
        // asignar posicion relativa
        if (simbolo.getPosicionRelativa() < 0) {
            simbolo.setPosicionRelativa(siguientePosicion);
            siguientePosicion++;
        } else if (simbolo.getPosicionRelativa() >= siguientePosicion) {
            siguientePosicion = simbolo.getPosicionRelativa() + 1;
        }

        simbolos.add(simbolo);
        simbolosPorNombre.put(simbolo.getNombre(), simbolo);

        return true;
    }

    /**
     * Buscar un simbolo en este ambito y en sus padres.
     */
    public Simbolo buscarSimbolo(String nombre) {
        Simbolo simbolo = buscarSimboloLocal(nombre);
        // retornar coincidencia local
        if (simbolo != null) {
            return simbolo;
        }

        if (padre == null) {
            return null;
        }

        return padre.buscarSimbolo(nombre);
    }

    /**
     * Buscar un simbolo solo en este ambito.
     */
    public Simbolo buscarSimboloLocal(String nombre) {
        if (nombre == null) {
            return null;
        }
        return simbolosPorNombre.get(nombre);
    }

    /**
     * Verificar si el ambito contiene un simbolo.
     */
    public boolean contieneSimbolo(String nombre) {
        return buscarSimbolo(nombre) != null;
    }

    /**
     * Eliminar un simbolo del ambito.
     */
    public boolean eliminarSimbolo(String nombre) {
        Simbolo simbolo = simbolosPorNombre.remove(nombre);
        // validar existencia
        if (simbolo == null) {
            return false;
        }

        simbolos.remove(simbolo);

        return true;
    }

    /**
     * Obtener los simbolos del ambito.
     */
    public List<Simbolo> getSimbolos() {
        return simbolos;
    }

    /**
     * Obtener los simbolos del ambito.
     */
    public List<Simbolo> obtenerSimbolos() {
        return simbolos;
    }

    /**
     * Agregar un tipo al ambito.
     */
    public boolean agregarTipo(Tipo tipo) {
        if (tipo == null || tiposPorNombre.containsKey(tipo.getNombre())) {
            return false;
        }
        tipo.setAmbito(this);
        tipos.add(tipo);
        tiposPorNombre.put(tipo.getNombre(), tipo);
        return true;
    }

    /**
     * Buscar un tipo en este ambito y en sus padres.
     */
    public Tipo buscarTipo(String nombre) {
        Tipo tipo = buscarTipoLocal(nombre);
        if (tipo != null) {
            return tipo;
        }
        if (padre == null) {
            return null;
        }
        return padre.buscarTipo(nombre);
    }

    /**
     * Buscar un tipo solo en este ambito.
     */
    public Tipo buscarTipoLocal(String nombre) {
        if (nombre == null) {
            return null;
        }
        return tiposPorNombre.get(nombre);
    }

    /**
     * Obtener los tipos del ambito.
     */
    public List<Tipo> getTipos() {
        return tipos;
    }

    /**
     * Obtener los tipos del ambito.
     */
    public List<Tipo> obtenerTipos() {
        return tipos;
    }

    /**
     * Agregar un ambito hijo.
     */
    public boolean agregarAmbito(Ambito ambito) {
        if (ambito == null || ambitosPorNombre.containsKey(ambito.getNombre())) {
            return false;
        }
        if (ambito.padre == null) {
            ambito.setPadre(this);
        }
        ambitos.add(ambito);
        ambitosPorNombre.put(ambito.getNombre(), ambito);
        return true;
    }

    /**
     * Buscar un ambito por nombre.
     */
    public Ambito buscarAmbito(String nombre) {
        if (nombre == null) {
            return null;
        }

        Ambito ambito = ambitosPorNombre.get(nombre);
        if (ambito != null) {
            return ambito;
        }

        // recorrer hijos en profundidad
        for (Ambito hijo : ambitos) {
            Ambito resultado = hijo.buscarAmbito(nombre);
            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    /**
     * Obtener los ambitos hijos.
     */
    public List<Ambito> getAmbitos() {
        return ambitos;
    }

    /**
     * Obtener los ambitos hijos.
     */
    public List<Ambito> obtenerAmbitos() {
        return ambitos;
    }

    /**
     * Obtener los ambitos hijos.
     */
    public List<Ambito> getHijos() {
        return ambitos;
    }

    /**
     * Obtener la siguiente posicion disponible.
     */
    public int getSiguientePosicion() {
        return siguientePosicion;
    }

    /**
     * Reservar la siguiente posicion disponible.
     */
    public int reservarPosicion() {
        int posicion = siguientePosicion;
        siguientePosicion++;

        return posicion;
    }

    /**
     * Obtener el nivel del ambito desde la raiz.
     */
    public int getNivel() {
        int nivel = 0;
        Ambito actual = padre;
        // subir por la cadena de padres
        while (actual != null) {
            nivel++;
            actual = actual.padre;
        }

        return nivel;
    }

    /**
     * Obtener la ruta de ambitos desde la raiz.
     */
    public List<String> obtenerRuta() {
        List<String> ruta = new ArrayList<>();
        List<Ambito> cadena = new ArrayList<>();
        Ambito actual = this;
        // recolectar cadena hasta la raiz
        while (actual != null) {
            cadena.add(actual);
            actual = actual.padre;
        }

        // recorrer en orden inverso
        for (int i = cadena.size() - 1; i >= 0; i--) {
            ruta.add(cadena.get(i).nombre);
        }

        return ruta;
    }

    /**
     * Verificar si el ambito es la raiz.
     */
    public boolean esGlobal() {
        return padre == null;
    }
}
