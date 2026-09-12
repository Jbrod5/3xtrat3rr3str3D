package org.jrg.model.semantico;

import java.util.ArrayList;
import java.util.List;
import org.jrg.util.MiniUUID;

public class FuncionPendiente {

    private final String id;
    private final String nombre;
    private final List<Tipo> tiposParametros;
    private final Tipo tipoRetorno;
    private final int linea;
    private final int columna;
    private Ambito ambito;
    private Simbolo simboloResuelto;
    private boolean resuelta;

    /**
     * Crear una funcion pendiente sin tipo de retorno conocido.
     */
    public FuncionPendiente(String nombre, List<Tipo> tiposParametros, int linea, int columna) {
        this(nombre, tiposParametros, (Tipo) null, linea, columna);
    }

    /**
     * Crear una funcion pendiente con tipo de retorno.
     */
    public FuncionPendiente(String nombre, List<Tipo> tiposParametros, Tipo tipoRetorno, int linea, int columna) {
        this(nombre, tiposParametros, tipoRetorno, null, linea, columna);
    }

    /**
     * Crear una funcion pendiente con ambito.
     */
    public FuncionPendiente(String nombre, Ambito ambito, List<Tipo> tiposParametros, int linea, int columna) {
        this(nombre, tiposParametros, null, ambito, linea, columna);
    }

    /**
     * Crear una funcion pendiente con todos sus datos.
     */
    public FuncionPendiente(String nombre, List<Tipo> tiposParametros, Tipo tipoRetorno, Ambito ambito, int linea, int columna) {
        this.id = MiniUUID.generate();
        if (nombre == null) {
            this.nombre = "";
        } else {
            this.nombre = nombre;
        }
        this.tiposParametros = new ArrayList<>();
        if (tiposParametros != null) {
            this.tiposParametros.addAll(tiposParametros);
        }
        this.tipoRetorno = tipoRetorno;
        this.ambito = ambito;
        this.linea = linea;
        this.columna = columna;
        this.resuelta = false;
    }

    /**
     * Obtener el identificador de la funcion pendiente.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtener el nombre de la funcion.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtener los tipos de los parametros.
     */
    public List<Tipo> getTiposParametros() {
        return tiposParametros;
    }

    /**
     * Obtener los tipos de los parametros.
     */
    public List<Tipo> getParametros() {
        return tiposParametros;
    }

    /**
     * Agregar el tipo de un parametro.
     */
    public void agregarParametro(Tipo tipo) {
        if (tipo != null && !tiposParametros.contains(tipo)) {
            tiposParametros.add(tipo);
        }
    }

    /**
     * Obtener el tipo de retorno.
     */
    public Tipo getTipoRetorno() {
        return tipoRetorno;
    }

    /**
     * Obtener el ambito de la llamada.
     */
    public Ambito getAmbito() {
        return ambito;
    }

    /**
     * Asignar el ambito de la llamada.
     */
    public void setAmbito(Ambito ambito) {
        this.ambito = ambito;
    }

    /**
     * Obtener la linea de la llamada.
     */
    public int getLinea() {
        return linea;
    }

    /**
     * Obtener la columna de la llamada.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Verificar si la funcion fue resuelta.
     */
    public boolean isResuelta() {
        return resuelta;
    }

    /**
     * Verificar si la funcion fue resuelta.
     */
    public boolean estaResuelta() {
        return resuelta;
    }

    /**
     * Obtener el simbolo que resolvio la funcion.
     */
    public Simbolo getSimboloResuelto() {
        return simboloResuelto;
    }

    /**
     * Marcar la funcion como resuelta.
     */
    public void marcarResuelta(Simbolo simbolo) {
        this.simboloResuelto = simbolo;
        this.resuelta = true;
    }

    /**
     * Marcar la funcion como pendiente.
     */
    public void marcarPendiente() {
        this.simboloResuelto = null;
        this.resuelta = false;
    }

    /**
     * Obtener la firma de la funcion pendiente.
     */
    public String getFirma() {
        StringBuilder firma = new StringBuilder();
        firma.append(nombre);
        firma.append("(");
        for (int i = 0; i < tiposParametros.size(); i++) {
            if (i > 0) {
                firma.append(",");
            }
            Tipo tipo = tiposParametros.get(i);
            if (tipo == null) {
                firma.append("desconocido");
            } else {
                firma.append(tipo.getNombre());
            }
        }
        firma.append(")");
        return firma.toString();
    }
}
