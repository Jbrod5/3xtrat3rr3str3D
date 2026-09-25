package org.jrg.analisis.zetariano.cuartetas;

import org.jrg.model.ast.zetariano.Bloque;
import org.jrg.model.ast.zetariano.CasoDefault;
import org.jrg.model.ast.zetariano.CasoSwitch;
import org.jrg.model.ast.zetariano.ListaExpresiones;
import org.jrg.model.ast.zetariano.Parametros;
import org.jrg.model.ast.zetariano.Programa;
import org.jrg.model.ast.zetariano.TipoDato;
import org.jrg.model.ast.zetariano.ValorPrimitivo;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;

// generar cuartetas del programa y su base en Zetariano
public class ManejadorProgramaZetariano {

    // estado compartido de la generacion
    private final ContextoCuartetasZetariano ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasZetariano generador;

    // crear la manejadora con contexto y generador
    public ManejadorProgramaZetariano(ContextoCuartetasZetariano ctx, GeneradorCuartetasZetariano generador) {
        // asignar las dependencias recibidas
        this.ctx = ctx;
        this.generador = generador;
    }

    // visitar la definicion de clase del programa
    public String visitarPrograma(Programa nodo) {
        // visitar la definicion de clase si existe
        if (nodo.getDefinicionClase() != null) {
            nodo.getDefinicionClase().accept(generador);
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

    // recorrer las instrucciones del bloque
    public String visitarBloque(Bloque nodo) {
        // recorrer las instrucciones del bloque
        if (nodo.getInstrucciones() != null) {

            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {

                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(generador);
                }

            }

        }

        return null;
    }

    // recorrer las instrucciones del caso switch
    public String visitarCasoSwitch(CasoSwitch nodo) {
        // recorrer las instrucciones del caso
        if (nodo.getInstrucciones() != null) {

            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {

                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(generador);
                }

            }
        }

        return null;
    }

    // recorrer las instrucciones del caso por defecto
    public String visitarCasoDefault(CasoDefault nodo) {
        // recorrer las instrucciones del caso por defecto
        if (nodo.getInstrucciones() != null) {

            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {

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
