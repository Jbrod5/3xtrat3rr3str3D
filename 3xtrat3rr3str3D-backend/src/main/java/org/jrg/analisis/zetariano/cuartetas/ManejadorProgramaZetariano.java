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
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAsignacionSimple;

// generar cuartetas del programa y su base en Zetariano
public class ManejadorProgramaZetariano {

    private final ContextoCuartetasZetariano ctx;

    private final GeneradorCuartetasZetariano generador;

    // crear la manejadora con contexto y generador
    public ManejadorProgramaZetariano(ContextoCuartetasZetariano ctx, GeneradorCuartetasZetariano generador) {

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

    // visitar el valor primitivo cargandolo en un temporal
    public String visitarValorPrimitivo(ValorPrimitivo nodo) {

        // devolver el identificador directo sin crear temporal
        if (nodo.getTipoDato() == TipoPrimitivo.IDENTIFICADOR) {
            return nodo.getValor();
        }

        // guardar el nulo como literal especial
        if (nodo.getTipoDato() == TipoPrimitivo.NULO) {

            String tempNulo = ctx.getTemporales().nuevoTemporal();
            ctx.getCuartetas().add(new CuartetaAsignacionSimple("=", "null", "_", tempNulo, "_", "_", "_"));
            return tempNulo;

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
