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
import org.jrg.model.ast.yLenguaje.variable_asignable.VarMiembro;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAsignacionSimple;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAsignacionMiembro;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAsignacionArreglo;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAccesoMiembro;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAccesoArreglo;

// generar cuartetas del programa y sus secciones en el lenguaje Y
public class ManejadorProgramaY {

    private final ContextoCuartetasY ctx;
    private final GeneradorCuartetasY generador;

    // crear la manejadora con contexto y generador
    public ManejadorProgramaY(ContextoCuartetasY ctx, GeneradorCuartetasY generador) {

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

            if (derechaArr == null) {
                derechaArr = "_";
            }

            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo.getVariable();

            // evaluar la base del acceso
            String base = "_";
            if (acceso.getBase() instanceof VarArray || acceso.getBase() instanceof VarMiembro) {
                base = materializarBase(acceso.getBase());
            } else if (acceso.getBase() != null) {
                base = acceso.getBase().accept(generador);
            }

            if (base == null) {
                base = "_";
            }

            // evaluar el indice del acceso
            String indice = "_";
            if (acceso.getIndice() != null) {
                indice = acceso.getIndice().accept(generador);
            }

            // si es nulo usar lo de respaldo
            if (indice == null) {
                indice = "_";
            }

            // agregar la asignacion a la posicion a la lista de cuartetas
            ctx.getCuartetas().add(new CuartetaAsignacionArreglo("[]=", base, indice, derechaArr, "_", "entero", "_"));
            return null;

        }

        // evaluar la variable destino
        String izquierda = "_";
        if (nodo.getVariable() instanceof VarMiembro) {

            // convertir la variable al tipo concreto
            VarMiembro accesoMiembro = (VarMiembro) nodo.getVariable();

            // materializar la base si viene compuesta con indice o punto
            if (accesoMiembro.getBase() instanceof VarArray || accesoMiembro.getBase() instanceof VarMiembro) {
                String baseMiembro = materializarBase(accesoMiembro.getBase());

                // evaluar el valor a asignar
                String derechaMiembro = "_";
                if (nodo.getValor() != null) {
                    derechaMiembro = nodo.getValor().accept(generador);
                }

                if (derechaMiembro == null) {
                    derechaMiembro = "_";
                }

                // agregar la asignacion al miembro con .,=
                ctx.getCuartetas().add(new CuartetaAsignacionMiembro(".,=", baseMiembro, accesoMiembro.getMiembro(), derechaMiembro, "_", "_", "_"));

                return null;
            }

        }

        if (nodo.getVariable() != null) {
            izquierda = nodo.getVariable().accept(generador);
        }

        if (izquierda == null) {
            izquierda = "_";
        }

        // evaluar el valor a asignar
        String derecha = "_";
        if (nodo.getValor() != null) {
            derecha = nodo.getValor().accept(generador);
        }

        if (derecha == null) {
            derecha = "_";
        }

        // agregar la asignacion a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaAsignacionSimple(":=", derecha, "_", izquierda, ctx.inferirTipoDe(derecha, ctx.getTiposConocidos()), "_", "_"));

        return null;

    }

    // materializar una base compuesta en un temporal listo para usar
    private String materializarBase(NodoASTY nodo) {

        // verificar si el nodo es nulo
        if (nodo == null) {
            return "_";
        }

        // anidar lectura de arreglo para bases con indice
        if (nodo instanceof VarArray) {

            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo;

            // materializar la base interna primero para encadenar al infinito
            String baseInterna = materializarBase(acceso.getBase());

            // evaluar el indice del acceso
            String indice = "_";
            if (acceso.getIndice() != null) {
                indice = acceso.getIndice().accept(generador);
            }

            if (indice == null) {
                indice = "_";
            }

            // leer la base intermedia como entero
            String tempBase = ctx.getTemporales().nuevoTemporal();
            ctx.getTiposConocidos().put(tempBase, "entero");
            ctx.getCuartetas().add(new CuartetaAccesoArreglo("=[]", baseInterna, indice, tempBase, "_", "_", "entero"));

            return tempBase;

        }

        // anidar lectura de miembro para bases con punto
        if (nodo instanceof VarMiembro) {

            // convertir la variable al tipo concreto
            VarMiembro acceso = (VarMiembro) nodo;

            // materializar la base interna primero para encadenar al infinito
            String baseInterna = materializarBase(acceso.getBase());

            // leer el miembro intermedio con temporal
            String tempBase = ctx.getTemporales().nuevoTemporal();
            ctx.getCuartetas().add(new CuartetaAccesoMiembro(".", baseInterna, acceso.getMiembro(), tempBase, "_", "_", "_"));

            return tempBase;

        }

        // devolver nombres y temporales directos sin tocar nada
        String directo = nodo.accept(generador);
        if (directo == null) {
            return "_";
        }

        return directo;

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

    // visitar el valor primitivo cargandolo en un temporal
    public String visitarValorPrimitivo(ValorPrimitivo nodo) {

        // devolver el identificador directo sin crear temporal
        if (nodo.getTipo() == TipoPrimitivo.IDENTIFICADOR) {
            return nodo.getValor();
        }

        // guardar el literal en un temporal
        String temp = ctx.getTemporales().nuevoTemporal();

        // adivinar el tipo del literal
        String tipoLiteral = ctx.inferirTipoLiteral(nodo.getValor());

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoLiteral);
        ctx.getCuartetas().add(new CuartetaAsignacionSimple("=", nodo.getValor(), "_", temp, tipoLiteral, "_", tipoLiteral));
        return temp;

    }

    // visitar la lista de expresiones sin generar cuartetas
    public String visitarListaExpresiones(ListaExpresiones nodo) {
        // TODO
        return null;
    }

}
