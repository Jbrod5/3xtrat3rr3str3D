package org.jrg.analisis.pigLatin.cuartetas;

import org.jrg.model.ast.pigLatin.Bloque;
import org.jrg.model.ast.pigLatin.ElementoImprimir;
import org.jrg.model.ast.pigLatin.ListaAtributosInstancia;
import org.jrg.model.ast.pigLatin.ListaExpresiones;
import org.jrg.model.ast.pigLatin.Programa;
import org.jrg.model.ast.pigLatin.RutaImportacion;
import org.jrg.model.ast.pigLatin.SeccionGlobalVariables;
import org.jrg.model.ast.pigLatin.SeccionImportaciones;
import org.jrg.model.ast.pigLatin.SeccionMaior;
import org.jrg.model.ast.pigLatin.TipoDato;
import org.jrg.model.ast.pigLatin.ValorPrimitivo;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas del programa y sus secciones en Pig Latin
public class ManejadorProgramaPigLatin {

    private final ContextoCuartetasPigLatin ctx;
    private final GeneradorCuartetasPigLatin generador;

    // crear la manejadora con contexto y generador
    public ManejadorProgramaPigLatin(ContextoCuartetasPigLatin ctx, GeneradorCuartetasPigLatin generador) {
        this.ctx = ctx;
        this.generador = generador;
    }

    // visitar el programa con main de apertura y cierre
    public String visitPrograma(Programa nodo) {
        // visitar seccion de variables globales si existe
        if (nodo.getSeccionGlobalVariables() != null) {
            nodo.getSeccionGlobalVariables().accept(generador);
        }

        // agregar marcador de inicio del main a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_begin", "main", "_", "void", "_", "_", "void"));

        // visitar seccion maior si existe
        if (nodo.getSeccionMaior() != null) {
            nodo.getSeccionMaior().accept(generador);
        }

        // agregar marcador de fin del main a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_end", "main", "_", "_", "_", "_", "_"));

        return null;
    }

    // visitar la seccion de importaciones sin generar cuartetas
    public String visitSeccionImportaciones(SeccionImportaciones nodo) {
        // no genera cuartetas
        return null;
    }

    // visitar la ruta de importacion sin generar cuartetas
    public String visitRutaImportacion(RutaImportacion nodo) {
        // TODO: implementar si es necesario
        return null;
    }

    // visitar las declaraciones de variables globales
    public String visitSeccionGlobalVariables(SeccionGlobalVariables nodo) {
        // visitar declaraciones si existen
        if (nodo.getDeclaraciones() != null) {
            for (NodoAST decl : nodo.getDeclaraciones()) {
                if (decl != null) {
                    decl.accept(generador);
                }
            }
        }
        return null;
    }

    // visitar las instrucciones del bloque principal
    public String visitSeccionMaior(SeccionMaior nodo) {
        // visitar instrucciones del bloque principal
        if (nodo.getInstrucciones() != null) {
            for (NodoAST inst : nodo.getInstrucciones()) {
                if (inst != null) {
                    inst.accept(generador);
                }
            }
        }
        return null;
    }

    // visitar el tipo de dato sin generar cuartetas
    public String visitTipoDato(TipoDato nodo) {
        return null;
    }

    // visitar el valor primitivo sin generar cuartetas
    public String visitValorPrimitivo(ValorPrimitivo nodo) {
        return null;
    }

    // visitar la lista de expresiones sin generar cuartetas
    public String visitListaExpresiones(ListaExpresiones nodo) {
        return null;
    }

    // visitar la lista de atributos sin generar cuartetas
    public String visitListaAtributosInstancia(ListaAtributosInstancia nodo) {
        // no genera cuarteta por si solo
        return null;
    }

    // visitar las instrucciones de un bloque
    public String visitBloque(Bloque nodo) {

        // visitar instrucciones del bloque
        if (nodo.getInstrucciones() != null) {
            for (NodoAST inst : nodo.getInstrucciones()) {
                if (inst != null) {
                    inst.accept(generador);
                }
            }
        }

        return null;
    }

    // visitar el elemento a imprimir y devolver su resultado
    public String visitElementoImprimir(ElementoImprimir nodo) {
        // visitar la expresion interna y devolver su resultado
        return nodo.getExpresion().accept(generador);
    }
}
