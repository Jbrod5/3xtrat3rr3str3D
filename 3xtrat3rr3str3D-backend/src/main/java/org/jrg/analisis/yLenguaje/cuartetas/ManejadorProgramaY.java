package org.jrg.analisis.yLenguaje.cuartetas;

import org.jrg.model.ast.yLenguaje.Asignacion;
import org.jrg.model.ast.yLenguaje.Bloque;
import org.jrg.model.ast.yLenguaje.CasoDefecto;
import org.jrg.model.ast.yLenguaje.CasoSeleccion;
import org.jrg.model.ast.yLenguaje.CuerpoFuncion;
import org.jrg.model.ast.yLenguaje.ListaExpresiones;
import org.jrg.model.ast.yLenguaje.Parametros;
import org.jrg.model.ast.yLenguaje.Programa;
import org.jrg.model.ast.yLenguaje.SeccionEstructuras;
import org.jrg.model.ast.yLenguaje.SeccionFunciones;
import org.jrg.model.ast.yLenguaje.TipoDato;
import org.jrg.model.ast.yLenguaje.ValorPrimitivo;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarArray;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas del programa y sus secciones en el lenguaje Y
public class ManejadorProgramaY {

    // estado compartido de la generacion
    private final ContextoCuartetasY ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasY generador;

    // crear la manejadora con contexto y generador
    public ManejadorProgramaY(ContextoCuartetasY ctx, GeneradorCuartetasY generador) {
        // asignar las dependencias recibidas
        this.ctx = ctx;
        this.generador = generador;
    }

    // visitar las secciones del programa
    public String visitarPrograma(Programa nodo) {
        // visitar la seccion de estructuras si existe
        if (nodo.getSeccionEstructuras() != null) {
            nodo.getSeccionEstructuras().accept(generador);
        }

        // visitar la seccion de funciones si existe
        if (nodo.getSeccionFunciones() != null) {
            nodo.getSeccionFunciones().accept(generador);
        }

        return null;
    }

    // recorrer cada estructura de la seccion
    public String visitarSeccionEstructuras(SeccionEstructuras nodo) {

        // recorrer cada estructura de la seccion
        if (nodo.getEstructuras() != null) {
            for (NodoASTY estructura : nodo.getEstructuras()) {

                // visitar la estructura actual si existe
                if (estructura != null) {
                    estructura.accept(generador);
                }
            }
        }

        return null;
    }

    // recorrer cada funcion de la seccion
    public String visitarSeccionFunciones(SeccionFunciones nodo) {

        // recorrer cada funcion de la seccion
        if (nodo.getFunciones() != null) {
            for (NodoASTY funcion : nodo.getFunciones()) {

                // visitar la funcion actual si existe
                if (funcion != null) {
                    funcion.accept(generador);
                }

            }
        }

        return null;
    }

    // visitar el tipo de dato sin generar cuartetas
    public String visitarTipoDato(TipoDato nodo) {
        // TODO
        return null;
    }

    // visitar los parametros sin generar cuartetas
    public String visitarParametros(Parametros nodo) {
        // TODO
        return null;
    }

    // recorrer las instrucciones del cuerpo de la funcion
    public String visitarCuerpoFuncion(CuerpoFuncion nodo) {

        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {

            for (NodoASTY instruccion : nodo.getInstrucciones()) {

                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(generador);
                }
            }

        }

        return null;
    }

    // recorrer las instrucciones del bloque
    public String visitarBloque(Bloque nodo) {

        // recorrer las instrucciones del bloque
        if (nodo.getInstrucciones() != null) {

            for (NodoASTY instruccion : nodo.getInstrucciones()) {

                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(generador);
                }

            }

        }

        return null;
    }

    // generar la asignacion de valor a variable
    public String visitarAsignacion(Asignacion nodo) {
        // manejar la asignacion a posicion de arreglo con []=
        if (nodo.getVariable() instanceof VarArray) {
            // evaluar el valor a asignar
            String derechaArr = "_";
            if (nodo.getValor() != null) {
                derechaArr = nodo.getValor().accept(generador);
            }
            // usar valor por defecto si el resultado es nulo
            if (derechaArr == null) {
                derechaArr = "_";
            }
            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo.getVariable();
            // evaluar la base del acceso
            String base = "_";
            if (acceso.getBase() != null) {
                base = acceso.getBase().accept(generador);
            }
            // usar valor por defecto si el resultado es nulo
            if (base == null) {
                base = "_";
            }
            // evaluar el indice del acceso
            String indice = "_";
            if (acceso.getIndice() != null) {
                indice = acceso.getIndice().accept(generador);
            }
            // usar valor por defecto si el resultado es nulo
            if (indice == null) {
                indice = "_";
            }
            // agregar la asignacion a la posicion a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta("[]=", base, indice, derechaArr, "_", "entero", "_"));
            return null;
        }

        // evaluar la variable destino
        String izquierda = "_";
        if (nodo.getVariable() != null) {
            izquierda = nodo.getVariable().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (izquierda == null) {
            izquierda = "_";
        }

        // evaluar el valor a asignar
        String derecha = "_";
        if (nodo.getValor() != null) {
            derecha = nodo.getValor().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (derecha == null) {
            derecha = "_";
        }

        // agregar la asignacion a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta(":=", derecha, "_", izquierda, ctx.inferirTipoDe(derecha, ctx.getTiposConocidos()), "_", "_"));

        return null;
    }

    // recorrer las instrucciones del caso de seleccion
    public String visitarCasoSeleccion(CasoSeleccion nodo) {

        // recorrer las instrucciones del caso
        if (nodo.getInstrucciones() != null) {

            for (NodoASTY instruccion : nodo.getInstrucciones()) {

                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(generador);
                }
            }

        }

        return null;
    }

    // recorrer las instrucciones del caso por defecto
    public String visitarCasoDefecto(CasoDefecto nodo) {

        // recorrer las instrucciones del caso por defecto
        if (nodo.getInstrucciones() != null) {
            for (NodoASTY instruccion : nodo.getInstrucciones()) {

                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(generador);
                }

            }
        }

        return null;
    }

    // visitar el valor primitivo sin generar cuartetas
    public String visitarValorPrimitivo(ValorPrimitivo nodo) {
        // TODO
        return null;
    }

    // visitar la lista de expresiones sin generar cuartetas
    public String visitarListaExpresiones(ListaExpresiones nodo) {
        // TODO
        return null;
    }
}
