package org.jrg.analisis.pigLatin.cuartetas;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.pigLatin.ListaAtributosInstancia;
import org.jrg.model.ast.pigLatin.ListaExpresiones;
import org.jrg.model.ast.pigLatin.asignacion.AsignacionGeneral;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoConNombre;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoPosicional;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableArray;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayConDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayEstructura;
import org.jrg.model.ast.pigLatin.TipoDato;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArraySinDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclMatrizConDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclMatrizSinDatos;
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

    // extraer el nombre del tipo base declarado con respaldo entero
    private String nombreTipoBase(NodoAST tipo, String respaldo) {
        // usar el nombre cuando es tipo de dato conocido
        if (tipo instanceof TipoDato) {
            String nombre = ((TipoDato) tipo).getTipo();
            if (nombre != null && nombre.isEmpty() == false) {
                return nombre;
            }
        }
        // inferir del respaldo cuando no hay nombre
        String inferido = ctx.inferirTipoDe(respaldo, ctx.getTiposConocidos());
        if (inferido == null || inferido.isEmpty()) {
            return "entero";
        }
        return inferido;
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
        // usar el tipo base declarado para la reserva
        String tipoBase = nombreTipoBase(nodo.getTipo(), tamano);
        ctx.getCuartetas().add(new Cuarteta("alloc", tipoBase, textoTamano, nodo.getIdentificador(), tipoBase, "entero", tipoBase));
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
        // usar el tipo base declarado para la reserva
        String tipoBaseArr = nombreTipoBase(nodo.getTipo(), tamano);
        ctx.getCuartetas().add(new Cuarteta("alloc", tipoBaseArr, textoTamano, nodo.getIdentificador(), tipoBaseArr, "entero", tipoBaseArr));
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
        // usar el tipo de estructura declarado para la reserva
        String tipoEstruct = nodo.getTipo();
        if (tipoEstruct == null || tipoEstruct.isEmpty()) {
            tipoEstruct = "entero";
        }
        ctx.getCuartetas().add(new Cuarteta("alloc", tipoEstruct, textoTamano, nodo.getIdentificador(), tipoEstruct, "entero", tipoEstruct));
        return null;
    }

    // reservar una matriz con filas por columnas del tipo base
    public String visitDeclMatrizSinDatos(DeclMatrizSinDatos nodo) {
        // extraer el tipo base declarado
        String tipoBase = nombreTipoBase(nodo.getTipo(), null);
        // evaluar el tamano de filas
        String filas = null;
        if (nodo.getTamanoFilas() != null) {
            filas = nodo.getTamanoFilas().accept(generador);
        }
        // usar guion bajo si el resultado es nulo
        String textoFilas = "_";
        if (filas != null) {
            textoFilas = filas;
        }
        // evaluar el tamano de columnas
        String columnas = null;
        if (nodo.getTamanoColumnas() != null) {
            columnas = nodo.getTamanoColumnas().accept(generador);
        }
        // usar guion bajo si el resultado es nulo
        String textoColumnas = "_";
        if (columnas != null) {
            textoColumnas = columnas;
        }
        // multiplicar filas por columnas en un temporal
        String tempTotal = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("*", textoFilas, textoColumnas, tempTotal, ctx.tipoAritmetico(textoFilas), ctx.tipoAritmetico(textoColumnas), ctx.tipoResultadoAritmetico(textoFilas, textoColumnas)));
        // agregar la reserva de memoria con el total a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("alloc", tipoBase, tempTotal, nodo.getIdentificador(), tipoBase, "entero", tipoBase));
        return null;
    }

    // reservar una matriz y llenarla con sus filas de valores
    public String visitDeclMatrizConDatos(DeclMatrizConDatos nodo) {
        // extraer el tipo base declarado
        String tipoBase = nombreTipoBase(nodo.getTipo(), null);
        // evaluar el tamano de filas
        String filas = null;
        if (nodo.getTamanoFilas() != null) {
            filas = nodo.getTamanoFilas().accept(generador);
        }
        // usar guion bajo si el resultado es nulo
        String textoFilas = "_";
        if (filas != null) {
            textoFilas = filas;
        }
        // evaluar el tamano de columnas
        String columnas = null;
        if (nodo.getTamanoColumnas() != null) {
            columnas = nodo.getTamanoColumnas().accept(generador);
        }
        // usar guion bajo si el resultado es nulo
        String textoColumnas = "_";
        if (columnas != null) {
            textoColumnas = columnas;
        }
        // multiplicar filas por columnas en un temporal
        String tempTotal = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("*", textoFilas, textoColumnas, tempTotal, ctx.tipoAritmetico(textoFilas), ctx.tipoAritmetico(textoColumnas), ctx.tipoResultadoAritmetico(textoFilas, textoColumnas)));
        // agregar la reserva de memoria con el total a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("alloc", tipoBase, tempTotal, nodo.getIdentificador(), tipoBase, "entero", tipoBase));
        // recorrer cada fila con sus valores
        if (nodo.getFilas() != null) {
            for (int f = 0; f < nodo.getFilas().size(); f++) {
                List<NodoAST> fila = nodo.getFilas().get(f);
                // omitir filas nulas
                if (fila == null) {
                    continue;
                }
                for (int c = 0; c < fila.size(); c++) {
                    // evaluar el valor actual
                    String val = fila.get(c).accept(generador);
                    // usar guion bajo si el resultado es nulo
                    String textoVal = "_";
                    if (val != null) {
                        textoVal = val;
                    }
                    // calcular el indice lineal como fila por columnas mas columna
                    String tempFila = ctx.getTemporales().nuevoTemporal();
                    ctx.getCuartetas().add(new Cuarteta("*", String.valueOf(f), textoColumnas, tempFila, "entero", ctx.tipoAritmetico(textoColumnas), ctx.tipoResultadoAritmetico(String.valueOf(f), textoColumnas)));
                    String tempIndice = ctx.getTemporales().nuevoTemporal();
                    ctx.getCuartetas().add(new Cuarteta("+", tempFila, String.valueOf(c), tempIndice, ctx.tipoAritmetico(tempFila), "entero", ctx.tipoResultadoAritmetico(tempFila, String.valueOf(c))));
                    // agregar la asignacion a la posicion actual a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("[]=", nodo.getIdentificador(), tempIndice, textoVal, "_", "entero", "_"));
                }
            }
        }
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
        // manejar la asignacion a posicion de arreglo con []=
        if (a.getVariable() instanceof ValorAsignableArray) {
            // evaluar el valor a asignar
            String derArr = null;
            if (a.getValor() != null) {
                derArr = a.getValor().accept(generador);
            }
            // usar valor por defecto si el resultado es nulo
            if (derArr == null) {
                derArr = "_";
            }
            // convertir la variable al tipo concreto
            ValorAsignableArray acceso = (ValorAsignableArray) a.getVariable();
            // evaluar la base del acceso
            String base = null;
            if (acceso.getBase() != null) {
                base = acceso.getBase().accept(generador);
            }
            // usar valor por defecto si el resultado es nulo
            if (base == null) {
                base = "_";
            }
            // evaluar el indice del acceso
            String indice = null;
            if (acceso.getIndice() != null) {
                indice = acceso.getIndice().accept(generador);
            }
            // usar valor por defecto si el resultado es nulo
            if (indice == null) {
                indice = "_";
            }
            // agregar la asignacion a la posicion a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta("[]=", base, indice, derArr, "_", "entero", "_"));
            return null;
        }
        String izq = a.getVariable().accept(generador);
        String der = a.getValor().accept(generador);
        ctx.getCuartetas().add(new Cuarteta(":=", der, "_", izq, ctx.inferirTipoDe(der, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }
}
