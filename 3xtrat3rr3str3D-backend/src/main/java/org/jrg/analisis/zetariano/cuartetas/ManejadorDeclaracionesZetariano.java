package org.jrg.analisis.zetariano.cuartetas;

import org.jrg.model.ast.zetariano.ListaExpresiones;
import org.jrg.model.ast.zetariano.asignacion.AsignacionCompuesta;
import org.jrg.model.ast.zetariano.asignacion.AsignacionSimple;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConListaLiteral;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConTipo;
import org.jrg.model.ast.zetariano.TipoDato;
import org.jrg.model.ast.zetariano.instruccion.StmtAsignacion;
import org.jrg.model.ast.zetariano.instruccion.StmtBreak;
import org.jrg.model.ast.zetariano.instruccion.StmtContinue;
import org.jrg.model.ast.zetariano.instruccion.StmtDeclaracion;
import org.jrg.model.ast.zetariano.instruccion.StmtExpresion;
import org.jrg.model.ast.zetariano.instruccion.StmtReturn;
import org.jrg.model.ast.zetariano.variable_asignable.VarArray;
import org.jrg.model.ast.zetariano.variable_asignable.VarMiembro;
import org.jrg.model.ast.zetariano.variable_asignable.VarSimple;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de declaraciones y asignaciones en Zetariano
public class ManejadorDeclaracionesZetariano {

    // estado compartido de la generacion
    private final ContextoCuartetasZetariano ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasZetariano generador;

    // crear la manejadora con contexto y generador
    public ManejadorDeclaracionesZetariano(ContextoCuartetasZetariano ctx, GeneradorCuartetasZetariano generador) {
        // asignar las dependencias recibidas
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

    // generar el retorno con valor o sin valor
    public String visitarStmtReturn(StmtReturn nodo) {

        // evaluar la expresion de retorno si existe
        if (nodo.getExpresion() != null) {

            // obtener el valor de retorno
            String valor = nodo.getExpresion().accept(generador);

            // usar valor por defecto si el resultado es nulo
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

    // generar salto a la etiqueta de romper si existe
    public String visitarStmtBreak(StmtBreak nodo) {
        // agregar salto a la etiqueta de romper si existe a la lista de cuartetas
        if (ctx.getEtiquetaBreakActual() != null) {
            ctx.getCuartetas().add(new Cuarteta("goto", ctx.getEtiquetaBreakActual(), "_", "_", "_", "_", "_"));
        }

        return null;
    }

    // generar salto a la etiqueta de continuar si existe
    public String visitarStmtContinue(StmtContinue nodo) {
        // agregar salto a la etiqueta de continuar si existe a la lista de cuartetas
        if (ctx.getEtiquetaContinueActual() != null) {
            ctx.getCuartetas().add(new Cuarteta("goto", ctx.getEtiquetaContinueActual(), "_", "_", "_", "_", "_"));
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

    // declarar variable o arreglo con valor inicial opcional
    public String visitarDeclConTipo(DeclConTipo nodo) {

        // verificar si la declaracion es un arreglo
        if (nodo.getDimensiones() > 0) {

            // extraer el nombre del tipo base
            String tipoBase = "_";
            if (nodo.getTipo() instanceof TipoDato) {
                tipoBase = ((TipoDato) nodo.getTipo()).getTipo();
            }

            // usar valor por defecto si el nombre es nulo
            if (tipoBase == null) {
                tipoBase = "_";
            }

            // agregar la reserva de memoria con la cantidad de dimensiones a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta("alloc", tipoBase, String.valueOf(nodo.getDimensiones()), nodo.getIdentificador(), tipoBase, "entero", tipoBase));

            // agregar la asignacion inicial si hay valor a la lista de cuartetas
            if (nodo.getValor() != null) {

                // evaluar el valor inicial
                String valor = nodo.getValor().accept(generador);

                // usar valor por defecto si el resultado es nulo
                if (valor == null) {
                    valor = "_";
                }

                // agregar la asignacion a la variable a la lista de cuartetas
                ctx.getCuartetas().add(new Cuarteta(":=", valor, "_", nodo.getIdentificador(), ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));
            }

            return null;
        }

        // agregar la asignacion inicial si hay valor a la lista de cuartetas
        if (nodo.getValor() != null) {

            // evaluar el valor inicial
            String valor = nodo.getValor().accept(generador);

            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }

            // agregar la asignacion a la variable a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta(":=", valor, "_", nodo.getIdentificador(), ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));
        }

        return null;
    }

    // reservar un arreglo y llenarlo con su lista literal
    public String visitarDeclConListaLiteral(DeclConListaLiteral nodo) {
        // obtener la lista de valores iniciales
        NodoASTZetariano lista = nodo.getListaExpresiones();

        // recorrer los valores si la lista existe
        if (lista instanceof ListaExpresiones) {

            // convertir la lista al tipo concreto
            ListaExpresiones listaExpresiones = (ListaExpresiones) lista;

            // contar la cantidad de valores
            int cantidad = 0;
            if (listaExpresiones.getExpresiones() != null) {
                cantidad = listaExpresiones.getExpresiones().size();
            }

            // agregar la reserva de memoria con la cantidad de valores a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta("alloc", String.valueOf(cantidad), "_", nodo.getIdentificador(), "entero", "_", "_"));

            // recorrer cada valor de la lista
            if (listaExpresiones.getExpresiones() != null) {
                for (int i = 0; i < listaExpresiones.getExpresiones().size(); i++) {

                    // evaluar el valor actual
                    String valor = listaExpresiones.getExpresiones().get(i).accept(generador);

                    // usar valor por defecto si el resultado es nulo
                    if (valor == null) {
                        valor = "_";
                    }

                    // agregar la asignacion a la posicion actual a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("[]=", nodo.getIdentificador(), String.valueOf(i), valor, "_", "entero", "_"));
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

    // generar asignacion simple con caso especial a arreglo
    public String visitarAsignacionSimple(AsignacionSimple nodo) {
        // evaluar el valor a asignar
        String derecha = "_";
        if (nodo.getExpresion() != null) {
            derecha = nodo.getExpresion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (derecha == null) {
            derecha = "_";
        }

        // manejar la asignacion a posicion de arreglo con []=
        if (nodo.getVariable() instanceof VarArray) {

            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo.getVariable();

            // evaluar la base del acceso
            String base = "_";
            if (acceso.getVariable() != null) {
                base = acceso.getVariable().accept(generador);
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
            ctx.getCuartetas().add(new Cuarteta("[]=", base, indice, derecha, "_", "entero", "_"));

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

        // agregar la asignacion a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta(":=", derecha, "_", izquierda, ctx.inferirTipoDe(derecha, ctx.getTiposConocidos()), "_", "_"));

        return null;
    }

    // generar asignacion compuesta con operacion y temporal
    public String visitarAsignacionCompuesta(AsignacionCompuesta nodo) {

        // extraer el operador aritmetico quitando el igual final
        String aritmetico = "_";
        if (nodo.getOperador() != null) {
            aritmetico = nodo.getOperador();

            // quitar el igual final si existe
            if (aritmetico.endsWith("=")) {
                aritmetico = aritmetico.substring(0, aritmetico.length() - 1);
            }

        }

        //evaluar la variable destino
        String izquierda = "_";
        if (nodo.getVariable() != null) {
            izquierda = nodo.getVariable().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (izquierda == null) {
            izquierda = "_";
        }

        // evaluar la expresion derecha
        String derecha = "_";
        if (nodo.getExpresion() != null) {
            derecha = nodo.getExpresion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (derecha == null) {
            derecha = "_";
        }

        // agregar la operacion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // inferir el tipo resultado de la operacion
        String tipoResComp = ctx.tipoResultadoAritmetico(izquierda, derecha);

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoResComp);
        ctx.getCuartetas().add(new Cuarteta(aritmetico, izquierda, derecha, temp, ctx.tipoAritmetico(izquierda), ctx.tipoAritmetico(derecha), tipoResComp));

        // manejar la asignacion a posicion de arreglo con []=
        if (nodo.getVariable() instanceof VarArray) {

            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo.getVariable();

            // evaluar la base del acceso
            String base = "_";

            if (acceso.getVariable() != null) {
                base = acceso.getVariable().accept(generador);
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
            ctx.getCuartetas().add(new Cuarteta("[]=", base, indice, temp, "_", "entero", "_"));

            return null;
        }

        // agregar la asignacion del temporal a la variable a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta(":=", temp, "_", izquierda, ctx.inferirTipoDe(temp, ctx.getTiposConocidos()), "_", "_"));

        return null;
    }

    // devolver el identificador directamente sin cuartetas
    public String visitarVarSimple(VarSimple nodo) {
        // devolver el identificador directamente
        return nodo.getIdentificador();
    }

    // componer la referencia de arreglo con base e indice
    public String visitarVarArray(VarArray nodo) {
        // evaluar la base del acceso
        String base = "_";
        if (nodo.getVariable() != null) {
            base = nodo.getVariable().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (base == null) {
            base = "_";
        }

        // evaluar el indice del acceso
        String indice = "_";
        if (nodo.getIndice() != null) {
            indice = nodo.getIndice().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (indice == null) {
            indice = "_";
        }

        // devolver la referencia compuesta sin agregar cuarteta a la lista
        return base + "[" + indice + "]";
    }

    // componer la referencia de miembro con base y campo
    public String visitarVarMiembro(VarMiembro nodo) {
        // evaluar la base del acceso
        String base = "_";
        if (nodo.getVariable() != null) {
            base = nodo.getVariable().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (base == null) {
            base = "_";
        }

        // devolver la referencia compuesta sin agregar cuarteta a la lista
        return base + "." + nodo.getMiembro();
    }
}
