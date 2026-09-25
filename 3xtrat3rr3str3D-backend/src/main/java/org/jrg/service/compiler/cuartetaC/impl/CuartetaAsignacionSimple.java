package org.jrg.service.compiler.cuartetaC.impl;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una asignacion simple a C
public class CuartetaAsignacionSimple extends CuartetaC {

    /**
     * Crear una cuarteta de asignacion simple con operador explicito.
     */
    public CuartetaAsignacionSimple(String operador, String arg1, String arg2, String resultado,
                                     String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de asignacion simple con operador fijo.
     */
    public CuartetaAsignacionSimple(String arg1, String arg2, String resultado,
                                     String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador dos puntos igual
        super(":=", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // copiar el destino para operar sin mutar el campo
        String destino = resultado;
        // iniciar la linea sin declaracion por defecto
        String linea = "";
        // escribir en miembro con punto cuando el destino trae objeto
        if (destino != null && destino.contains(".")) {
            int corte = destino.indexOf('.');
            String objetoDest = destino.substring(0, corte).trim();
            String campoDest = destino.substring(corte + 1).trim();
            String accesoDest = ".";
            if (ctx.esPuntero(objetoDest)) {
                accesoDest = "->";
            }
            // declarar el objeto si aun no existe y no es campo ni this
            String declObj = "";
            if (ctx.esPuntero(objetoDest) == false && "this".equals(objetoDest) == false && ctx.declaradas.contains(objetoDest) == false && ctx.globales.containsKey(objetoDest) == false && ctx.esCampoActual(objetoDest) == false) {
                String structAdivinado = ctx.structPorCampo(campoDest);
                if (structAdivinado != null) {
                    declObj = "struct " + structAdivinado + " " + objetoDest + ";\n    ";
                    ctx.declaradas.add(objetoDest);
                    ctx.tiposDeclarados.put(objetoDest, "struct " + structAdivinado);
                    ctx.structDeNombre.put(objetoDest, structAdivinado);
                }
            }
            return declObj + ctx.traducirValor(objetoDest) + accesoDest + campoDest + " = " + ctx.traducirValor(arg1) + ";";
        }
        // propagar el struct del valor al destino
        if (arg1 != null && destino != null) {
            String structOrigen = ctx.structDeNombre.get(arg1);
            if (structOrigen != null) {
                ctx.structDeNombre.put(destino, structOrigen);
                // propagar la marca de puntero a heap
                if (ctx.punteros.contains(arg1)) {
                    ctx.punteros.add(destino);
                }
            }
        }
        // escribir en el campo con this sin declarar nada
        if (ctx.esCampoActual(destino)) {
            return "this->" + destino + " = " + ctx.traducirValor(arg1) + ";";
        }
        // declarar la variable si aun no existe
        if (destino != null && ctx.declaradas.contains(destino) == false) {
            String tipo = ctx.tipoDestinoPara(destino, arg1, tipoArg1);
            linea = tipo + " " + destino + ";\n    ";
            ctx.declaradas.add(destino);
            ctx.tiposDeclarados.put(destino, tipo);
        }
        // omitir asignaciones sin destino valido
        if (destino == null) {
            return linea;
        }
        return linea + destino + " = " + ctx.traducirValor(arg1) + ";";
    }
}
