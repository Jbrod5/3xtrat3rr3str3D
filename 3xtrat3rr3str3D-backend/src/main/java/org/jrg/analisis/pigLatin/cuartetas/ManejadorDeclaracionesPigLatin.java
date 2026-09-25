package org.jrg.analisis.pigLatin.cuartetas;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.pigLatin.ListaAtributosInstancia;
import org.jrg.model.ast.pigLatin.ListaExpresiones;
import org.jrg.model.ast.pigLatin.asignacion.AsignacionGeneral;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoConNombre;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoPosicional;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayConDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayEstructura;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArraySinDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclBooleanaImplicita;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclConTipoYValor;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclEstructuraConValores;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclObjetoNovus;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de declaraciones y asignaciones en Pig Latin
public class ManejadorDeclaracionesPigLatin {

    // estado compartido de la generacion
    private final ContextoCuartetasPigLatin ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasPigLatin generador;

    // crear la manejadora con contexto y generador
    public ManejadorDeclaracionesPigLatin(ContextoCuartetasPigLatin ctx, GeneradorCuartetasPigLatin generador) {
        // asignar las dependencias recibidas
        this.ctx = ctx;
        this.generador = generador;
    }

    // crear un objeto con new y asignarlo a su variable
    public String visitDeclObjetoNovus(DeclObjetoNovus nodo) {
        // evaluar argumentos
        List<String> args = new ArrayList<>();
        if (nodo.getArgumentos() != null) {
            if (nodo.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) nodo.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(generador);
                    // usar guion bajo si el argumento es nulo
                    if (a == null) {
                        args.add("_");
                    } else {
                        args.add(a);
                    }
                }
            }
        }
        // agregar parametros a la lista de cuartetas
        for (int i = 0; i < args.size(); i++) {
            ctx.getCuartetas().add(new Cuarteta("param", args.get(i), "_", "_", ctx.inferirTipoDe(args.get(i), ctx.getTiposConocidos()), "_", "_"));
        }
        // crear objeto
        String temp = ctx.getTemporales().nuevoTemporal();
        // registrar el temporal con el tipo del objeto
        ctx.getTiposConocidos().put(temp, nodo.getTipo());
        ctx.getCuartetas().add(new Cuarteta("new", nodo.getTipo(), String.valueOf(args.size()), temp, nodo.getTipo(), "_", nodo.getTipo()));
        // asignar a variable
        ctx.getCuartetas().add(new Cuarteta(":=", temp, "_", nodo.getIdentificador(), ctx.inferirTipoDe(temp, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }

    // crear una estructura con new_struct y sus atributos
    public String visitDeclEstructuraConValores(DeclEstructuraConValores nodo) {
        // crear nueva instancia de estructura
        String temp = ctx.getTemporales().nuevoTemporal();
        // registrar el temporal con el tipo de la estructura
        ctx.getTiposConocidos().put(temp, nodo.getTipo());
        ctx.getCuartetas().add(new Cuarteta("new_struct", nodo.getTipo(), "_", temp, nodo.getTipo(), "_", nodo.getTipo()));
        // evaluar los atributos
        if (nodo.getAtributos() instanceof ListaAtributosInstancia) {
            List<NodoAST> attrs = ((ListaAtributosInstancia) nodo.getAtributos()).getAtributos();
            for (int i = 0; i < attrs.size(); i++) {
                String val = attrs.get(i).accept(generador);
                // obtener el tipo del struct actual
                String tipoStruct = nodo.getTipo();
                // obtener los nombres de campos registrados
                List<String> nombresCampos = ctx.getCamposDeStructs().get(tipoStruct);
                // resolver el nombre del campo por indice
                String nombreCampo = String.valueOf(i);
                if (nombresCampos != null && i < nombresCampos.size()) {
                    nombreCampo = nombresCampos.get(i);
                }
                // usar guion bajo si el valor es nulo
                String textoVal = "_";
                if (val != null) {
                    textoVal = val;
                }
                ctx.getCuartetas().add(new Cuarteta(".,=", temp, nombreCampo, textoVal, "_", "_", ctx.inferirTipoDe(val, ctx.getTiposConocidos())));
            }
        }
        // asignar la estructura a la variable
        ctx.getCuartetas().add(new Cuarteta(":=", temp, "_", nodo.getIdentificador(), ctx.inferirTipoDe(temp, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }

    // declarar una variable con valor inicial opcional
    public String visitDeclConTipoYValor(DeclConTipoYValor nodo) {
        // si hay valor inicial, agregar asignacion a la lista de cuartetas
        if (nodo.getValor() != null) {
            String val = nodo.getValor().accept(generador);
            // usar guion bajo si el valor es nulo
            String textoVal = "_";
            if (val != null) {
                textoVal = val;
            }
            ctx.getCuartetas().add(new Cuarteta(":=", textoVal, "_", nodo.getIdentificador(), ctx.inferirTipoDe(val, ctx.getTiposConocidos()), "_", "_"));
        }
        return null;
    }

    // declarar una booleana con su valor implicito
    public String visitDeclBooleanaImplicita(DeclBooleanaImplicita nodo) {
        // agregar asignacion con el valor implicito del nodo a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta(":=", nodo.getValor(), "_", nodo.getIdentificador(), ctx.inferirTipoLiteral(nodo.getValor()), "_", "_"));
        return null;
    }

    // reservar un arreglo solo con tamano
    public String visitDeclArraySinDatos(DeclArraySinDatos nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(generador);
        }
        // usar guion bajo si el tamano es nulo
        String textoTamano = "_";
        if (tamano != null) {
            textoTamano = tamano;
        }
        ctx.getCuartetas().add(new Cuarteta("alloc", textoTamano, "_", nodo.getIdentificador(), ctx.inferirTipoDe(tamano, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }

    // reservar un arreglo y llenarlo con sus valores
    public String visitDeclArrayConDatos(DeclArrayConDatos nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(generador);
        }
        // usar guion bajo si el tamano es nulo
        String textoTamano = "_";
        if (tamano != null) {
            textoTamano = tamano;
        }
        ctx.getCuartetas().add(new Cuarteta("alloc", textoTamano, "_", nodo.getIdentificador(), ctx.inferirTipoDe(tamano, ctx.getTiposConocidos()), "_", "_"));
        if (nodo.getValores() != null) {
            for (int i = 0; i < nodo.getValores().size(); i++) {
                String val = nodo.getValores().get(i).accept(generador);
                // usar guion bajo si el valor es nulo
                String textoVal = "_";
                if (val != null) {
                    textoVal = val;
                }
                ctx.getCuartetas().add(new Cuarteta("[]=", nodo.getIdentificador(), String.valueOf(i), textoVal, "_", "entero", "_"));
            }
        }
        return null;
    }

    // reservar un arreglo de estructuras solo con tamano
    public String visitDeclArrayEstructura(DeclArrayEstructura nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(generador);
        }
        // usar guion bajo si el tamano es nulo
        String textoTamano = "_";
        if (tamano != null) {
            textoTamano = tamano;
        }
        ctx.getCuartetas().add(new Cuarteta("alloc", textoTamano, "_", nodo.getIdentificador(), ctx.inferirTipoDe(tamano, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }

    // visitar el valor del campo con nombre
    public String visitCampoConNombre(CampoConNombre nodo) {
        // visitar el valor del campo
        return nodo.getValor().accept(generador);
    }

    // visitar el valor posicional del campo
    public String visitCampoPosicional(CampoPosicional nodo) {
        // visitar el valor posicional
        return nodo.getValor().accept(generador);
    }

    // generar la asignacion general de valor a variable
    public String visitAsignacionGeneral(AsignacionGeneral a) {
        String izq = a.getVariable().accept(generador);
        String der = a.getValor().accept(generador);
        ctx.getCuartetas().add(new Cuarteta(":=", der, "_", izq, ctx.inferirTipoDe(der, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }
}
