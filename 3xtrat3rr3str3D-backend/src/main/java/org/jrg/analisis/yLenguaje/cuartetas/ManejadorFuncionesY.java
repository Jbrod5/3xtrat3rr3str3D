package org.jrg.analisis.yLenguaje.cuartetas;

import org.jrg.model.ast.yLenguaje.Parametros;
import org.jrg.model.ast.yLenguaje.TipoDato;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionConRetorno;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionSinRetorno;
import org.jrg.model.ast.yLenguaje.parametro.ParamArray;
import org.jrg.model.ast.yLenguaje.parametro.ParamEstructura;
import org.jrg.model.ast.yLenguaje.parametro.ParamSimple;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de funciones en el lenguaje Y
public class ManejadorFuncionesY {

    private final ContextoCuartetasY ctx;
    private final GeneradorCuartetasY generador;

    // crear la manejadora con contexto y generador
    public ManejadorFuncionesY(ContextoCuartetasY ctx, GeneradorCuartetasY generador) {
        this.ctx = ctx;
        this.generador = generador;
    }

    // agregar los marcadores de inicio y fin de funcion sin retorno
    public String visitarDefFuncionSinRetorno(DefFuncionSinRetorno nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametros(nodo.getParametros());

        // agregar marcador de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_begin", nodo.getNombre(), tiposParams, "void", tiposParams, "_", "void"));

        // visitar el cuerpo de la funcion si existe
        if (nodo.getCuerpo() != null) {
            nodo.getCuerpo().accept(generador);
        }

        // agregar marcador de fin a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_end", nodo.getNombre(), "_", "_", "_", "_", "_"));

        return null;
    }

    // agregar los marcadores de inicio y fin de funcion con retorno
    public String visitarDefFuncionConRetorno(DefFuncionConRetorno nodo) {

        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametros(nodo.getParametros());

        // extraer el tipo de retorno
        String tipoRetorno = "_";

        if (nodo.getTipoRetorno() instanceof TipoDato) {
            tipoRetorno = ((TipoDato) nodo.getTipoRetorno()).getNombre();
        }

        if (tipoRetorno == null) {
            tipoRetorno = "_";
        }

        // agregar marcador de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_begin", nodo.getNombre(), tiposParams, tipoRetorno, tiposParams, "_", tipoRetorno));

        // visitar el cuerpo de la funcion si existe
        if (nodo.getCuerpo() != null) {
            nodo.getCuerpo().accept(generador);
        }

        // agregar marcador de fin a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_end", nodo.getNombre(), "_", "_", "_", "_", "_"));

        return null;
    }

    // construir el string de params con formato nombre tipo separados por coma
    public String extraerTiposParametros(NodoASTY parametrosNodo) {

        // devolver guion bajo si no hay parametros
        if (parametrosNodo == null) {
            return "_";
        }

        // verificar que sea Parametros
        if (!(parametrosNodo instanceof Parametros)) {
            return "_";
        }

        // convertir al tipo concreto
        Parametros parametros = (Parametros) parametrosNodo;

        // verificar que la lista no sea nula
        if (parametros.getParametros() == null || parametros.getParametros().isEmpty()) {
            return "_";
        }

        // acumular los params separados por coma
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parametros.getParametros().size(); i++) {
            NodoASTY p = parametros.getParametros().get(i);
            String nombre = "_";
            String tipo = "_";
            if (p instanceof ParamSimple) {
                nombre = ((ParamSimple) p).getNombre();
                NodoASTY tipoNodo = ((ParamSimple) p).getTipo();
                if (tipoNodo instanceof TipoDato) {
                    tipo = ((TipoDato) tipoNodo).getNombre();
                }
            } else if (p instanceof ParamArray) {
                nombre = ((ParamArray) p).getNombre();
                NodoASTY tipoNodo = ((ParamArray) p).getTipo();
                if (tipoNodo instanceof TipoDato) {
                    tipo = ((TipoDato) tipoNodo).getNombre() + "[]";
                }
            } else if (p instanceof ParamEstructura) {
                nombre = ((ParamEstructura) p).getNombre();
                tipo = ((ParamEstructura) p).getTipoEstructura();
            }
            if (nombre == null) {
                nombre = "_";
            }
            if (tipo == null) {
                tipo = "_";
            }
            if (i > 0) {
                sb.append(",");
            }
            sb.append(nombre).append(":").append(tipo);
        }

        return sb.toString();
    }

    // visitar el parametro simple sin generar cuartetas
    public String visitarParamSimple(ParamSimple nodo) {
        // TODO
        return null;
    }

    // visitar el parametro arreglo sin generar cuartetas
    public String visitarParamArray(ParamArray nodo) {
        // TODO
        return null;
    }

    // visitar el parametro estructura sin generar cuartetas
    public String visitarParamEstructura(ParamEstructura nodo) {
        // TODO
        return null;
    }
}
