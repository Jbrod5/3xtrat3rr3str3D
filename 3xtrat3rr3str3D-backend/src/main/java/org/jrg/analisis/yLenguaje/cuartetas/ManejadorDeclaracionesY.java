package org.jrg.analisis.yLenguaje.cuartetas;

import java.util.List;

import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArrayConValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArraySinValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclConTipoYValor;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatrizConValores;
import org.jrg.model.ast.yLenguaje.ListaExpresiones;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatriz;
import org.jrg.model.ast.yLenguaje.instruccion.StmtAsignacion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtContinuar;
import org.jrg.model.ast.yLenguaje.instruccion.StmtDeclaracion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtEstructuraLocal;
import org.jrg.model.ast.yLenguaje.instruccion.StmtExpresion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtRetorno;
import org.jrg.model.ast.yLenguaje.instruccion.StmtRomper;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarArray;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarMiembro;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarSimple;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de declaraciones y asignables en el lenguaje Y
public class ManejadorDeclaracionesY {

    private final ContextoCuartetasY ctx;
    private final GeneradorCuartetasY generador;

    // crear la manejadora con contexto y generador
    public ManejadorDeclaracionesY(ContextoCuartetasY ctx, GeneradorCuartetasY generador) {
        this.ctx = ctx;
        this.generador = generador;
    }

    // visitar la declaracion interna de la instruccion
    public String visitarStmtDeclaracion(StmtDeclaracion nodo) {
        // visitar la declaracion si existe
        if (nodo.getDeclaracion() != null) {
            nodo.getDeclaracion().accept(generador);
        }

        return null;
    }

    // visitar la asignacion interna de la instruccion
    public String visitarStmtAsignacion(StmtAsignacion nodo) {
        // visitar la asignacion si existe
        if (nodo.getAsignacion() != null) {
            nodo.getAsignacion().accept(generador);
        }

        return null;
    }

    // omitir structs locales porque solo son tipos
    public String visitarStmtEstructuraLocal(StmtEstructuraLocal nodo) {
        // no agregar cuartetas a la lista porque los structs locales son solo tipos :D
        return null;
    }

    // generar el retorno con valor o sin valor
    public String visitarStmtRetorno(StmtRetorno nodo) {
        // evaluar la expresion de retorno si existe
        if (nodo.getExpresion() != null) {

            // obtener el valor de retorno
            String valor = nodo.getExpresion().accept(generador);

            if (valor == null) {
                valor = "_";
            }

            // agregar el retorno con valor a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta("return", valor, "_", "_", ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));
        } else {

            // agregar el retorno sin valor a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta("return", "_", "_", "_", "_", "_", "_"));
        }

        return null;
    }

    // generar salto a la etiqueta de continuar si existe
    public String visitarStmtContinuar(StmtContinuar nodo) {
        // agregar salto a la etiqueta de continuar si existe a la lista de cuartetas
        if (ctx.getEtiquetaContinueActual() != null) {
            ctx.getCuartetas().add(new Cuarteta("goto", ctx.getEtiquetaContinueActual(), "_", "_", "_", "_", "_"));
        }

        return null;
    }

    // generar salto a la etiqueta de romper si existe
    public String visitarStmtRomper(StmtRomper nodo) {
        // agregar salto a la etiqueta de romper si existe a la lista de cuartetas
        if (ctx.getEtiquetaBreakActual() != null) {
            ctx.getCuartetas().add(new Cuarteta("goto", ctx.getEtiquetaBreakActual(), "_", "_", "_", "_", "_"));
        }

        return null;
    }

    // visitar la expresion interna de la instruccion
    public String visitarStmtExpresion(StmtExpresion nodo) {

        // visitar la expresion si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(generador);
        }

        return null;
    }

    // declarar una variable con valor inicial opcional
    public String visitarDeclConTipoYValor(DeclConTipoYValor nodo) {
        // agregar la asignacion a la lista de cuartetas inicial si hay valor
        if (nodo.getValor() != null) {

            // evaluar el valor inicial
            String valor = nodo.getValor().accept(generador);

            if (valor == null) {
                valor = "_";
            }

            // agregar la asignacion a la variable a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta(":=", valor, "_", nodo.getNombre(), ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));
        }
        return null;
    }

    // reservar un arreglo solo con tamano
    public String visitarDeclArraySinValores(DeclArraySinValores nodo) {
        // evaluar el tamano del arreglo
        String tamano = "_";
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(generador);
        }

        // si es nulo usar lo de respaldo
        if (tamano == null) {
            tamano = "_";
        }

        // agregar la reserva de memoria a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("alloc", tamano, "_", nodo.getNombre(), ctx.inferirTipoDe(tamano, ctx.getTiposConocidos()), "_", "_"));

        return null;
    }

    // reservar un arreglo y llenarlo con sus valores
    public String visitarDeclArrayConValores(DeclArrayConValores nodo) {
        // evaluar el tamano del arreglo
        String tamano = "_";
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(generador);
        }

        if (tamano == null) {
            tamano = "_";
        }

        // agregar la reserva de memoria a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("alloc", tamano, "_", nodo.getNombre(), ctx.inferirTipoDe(tamano, ctx.getTiposConocidos()), "_", "_"));

        // obtener la lista de valores iniciales
        NodoASTY lista = nodo.getListaValores();

        // recorrer los valores si la lista existe
        if (lista instanceof ListaExpresiones) {

            // convertir la lista al tipo concreto
            ListaExpresiones listaExpresiones = (ListaExpresiones) lista;

            // recorrer cada valor de la lista
            if (listaExpresiones.getExpresiones() != null) {
                for (int i = 0; i < listaExpresiones.getExpresiones().size(); i++) {

                    // evaluar el valor actual
                    String valor = listaExpresiones.getExpresiones().get(i).accept(generador);

                    if (valor == null) {
                        valor = "_";
                    }

                    // agregar la asignacion a la posicion actual a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("[]=", nodo.getNombre(), String.valueOf(i), valor, "_", "entero", "_"));
                }
            }
        } else {

            // visitar la lista si tiene otro formato
            if (lista != null) {
                lista.accept(generador);
            }

        }

        return null;
    }

    // reservar una matriz con filas por columnas del tipo base
    public String visitarDeclMatriz(DeclMatriz nodo) {
        // extraer el tipo base declarado
        String tipoBase = ctx.nombreDeTipo(nodo.getTipo());
        // evaluar el tamano de filas
        String filas = "_";
        if (nodo.getTamanoFilas() != null) {
            filas = nodo.getTamanoFilas().accept(generador);
        }

        if (filas == null) {
            filas = "_";
        }

        // evaluar el tamano de columnas
        String columnas = "_";
        if (nodo.getTamanoColumnas() != null) {
            columnas = nodo.getTamanoColumnas().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (columnas == null) {
            columnas = "_";
        }

        // multiplicar filas por columnas en un temporal
        String tempTotal = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("*", filas, columnas, tempTotal, ctx.tipoAritmetico(filas), ctx.tipoAritmetico(columnas), ctx.tipoResultadoAritmetico(filas, columnas)));

        // agregar la reserva de memoria con el total a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("alloc", tipoBase, tempTotal, nodo.getNombre(), tipoBase, "entero", tipoBase));

        return null;
    }

    // reservar una matriz y llenarla con sus filas de valores
    public String visitarDeclMatrizConValores(DeclMatrizConValores nodo) {
        // extraer el tipo base declarado
        String tipoBase = ctx.nombreDeTipo(nodo.getTipo());
        // evaluar el tamano de filas
        String filas = "_";
        if (nodo.getTamanoFilas() != null) {
            filas = nodo.getTamanoFilas().accept(generador);
        }

        if (filas == null) {
            filas = "_";
        }

        // evaluar el tamano de columnas
        String columnas = "_";
        if (nodo.getTamanoColumnas() != null) {
            columnas = nodo.getTamanoColumnas().accept(generador);
        }

        if (columnas == null) {
            columnas = "_";
        }

        // multiplicar filas por columnas en un temporal
        String tempTotal = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("*", filas, columnas, tempTotal, ctx.tipoAritmetico(filas), ctx.tipoAritmetico(columnas), ctx.tipoResultadoAritmetico(filas, columnas)));

        // agregar la reserva de memoria con el total a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("alloc", tipoBase, tempTotal, nodo.getNombre(), tipoBase, "entero", tipoBase));

        // recorrer cada fila con sus valores
        if (nodo.getFilas() != null) {
            for (int f = 0; f < nodo.getFilas().size(); f++) {
                List<NodoASTY> fila = nodo.getFilas().get(f);
                // omitir filas nulas
                if (fila == null) {
                    continue;
                }
                for (int c = 0; c < fila.size(); c++) {
                    // evaluar el valor actual
                    String val = fila.get(c).accept(generador);
                    // si es nulo usar lo de respaldo
                    if (val == null) {
                        val = "_";
                    }
                    // calcular el indice lineal como fila por columnas mas columna
                    String tempFila = ctx.getTemporales().nuevoTemporal();
                    ctx.getCuartetas().add(new Cuarteta("*", String.valueOf(f), columnas, tempFila, "entero", ctx.tipoAritmetico(columnas), ctx.tipoResultadoAritmetico(String.valueOf(f), columnas)));
                    String tempIndice = ctx.getTemporales().nuevoTemporal();
                    ctx.getCuartetas().add(new Cuarteta("+", tempFila, String.valueOf(c), tempIndice, ctx.tipoAritmetico(tempFila), "entero", ctx.tipoResultadoAritmetico(tempFila, String.valueOf(c))));
                    // agregar la asignacion a la posicion actual a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("[]=", nodo.getNombre(), tempIndice, val, "_", "entero", "_"));
                }
            }
        }

        return null;
    }

    // devolver el nombre de la variable directamente
    public String visitarVarSimple(VarSimple nodo) {
        // devolver el nombre directamente
        return nodo.getNombre();
    }

    // componer la referencia de arreglo con base e indice
    public String visitarVarArray(VarArray nodo) {
        // evaluar la base del acceso
        String base = "_";
        if (nodo.getBase() != null) {
            base = nodo.getBase().accept(generador);
        }

        if (base == null) {
            base = "_";
        }

        // evaluar el indice del acceso
        String indice = "_";
        if (nodo.getIndice() != null) {
            indice = nodo.getIndice().accept(generador);
        }

        if (indice == null) {
            indice = "_";
        }

        // devolver la referencia compuesta sin agregar cuarteta a la lista :D
        return base + "[" + indice + "]";
    }

    // componer la referencia de miembro con base y campo
    public String visitarVarMiembro(VarMiembro nodo) {
        // evaluar la base del acceso
        String base = "_";
        if (nodo.getBase() != null) {
            base = nodo.getBase().accept(generador);
        }

        if (base == null) {
            base = "_";
        }

        // devolver la referencia compuesta sin agregar cuarteta a la lista
        return base + "." + nodo.getMiembro();
    }
}
