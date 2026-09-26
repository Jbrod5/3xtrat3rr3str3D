package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir llamadas a funcion y a metodo a C
public class CuartetaLlamada extends CuartetaC {

    /**
     * Crear una cuarteta de llamada con operador explicito.
     */
    public CuartetaLlamada(String operador, String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de llamada con operador call por defecto.
     */
    public CuartetaLlamada(String arg1, String arg2, String resultado,
                            String tipoArg1, String tipoArg2, String tipoResultado) {
        // cae al base con operador fijo
        super("call", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // traducir llamadas a metodo con receptor como primer arg
        if ("call_method".equals(operador)) {
            String metodo = arg1;
            // resolver la clase desde el tipo del objeto receptor
            String receptorTipo = null;
            if (ctx.paramsPendientes.isEmpty() == false && ctx.tiposParamsPendientes.isEmpty() == false) {
                receptorTipo = ctx.tiposParamsPendientes.get(0);
            }
            // calificar el metodo con la clase del receptor
            String nombreLlamada = metodo;
            if (receptorTipo != null && ctx.clases.contains(receptorTipo) && metodo != null && metodo.indexOf('_') < 0) {
                nombreLlamada = receptorTipo + "_" + metodo;
            }
            // poner _N sin contar el receptor si hay sobrecarga
            int numeroReales = ctx.paramsPendientes.size() - 1;
            if (numeroReales < 0) {
                numeroReales = 0;
            }
            if (numeroReales > 0 && nombreLlamada.equals(metodo) == false) {
                nombreLlamada = nombreLlamada + "_" + numeroReales;
            }
            String argsMetodo = ctx.unirParams();
            ctx.limpiarParams();
            // agregar llamada directa para metodos void conocidos al cuerpo
            String retornoConocido = ctx.retornosFuncion.get(nombreLlamada);
            if ("void".equals(retornoConocido)) {
                return nombreLlamada + "(" + argsMetodo + ");";
            }
            // usar el retorno conocido cuando trae tipo valido
            String tipoCall = ctx.mapearTipo(tipoResultado);
            if (retornoConocido != null && retornoConocido.isEmpty() == false && retornoConocido.equals("_") == false) {
                tipoCall = ctx.mapearTipoConClases(retornoConocido);
            }
            return ctx.ladoIzquierdo(resultado, tipoCall) + " = " + nombreLlamada + "(" + argsMetodo + ");";
        }
        // traducir llamadas a funcion o builtin
        return traducirLlamada(ctx);
    }

    // traducir una llamada a funcion o builtin
    private String traducirLlamada(ContextoTraduccion ctx) {
        // copiar el nombre para operar sin mutar el campo
        String nombre = arg1;
        // unir los params pendientes separados por coma
        String args = ctx.unirParams();
        // resolver builtins imprimir println y print por tipo
        if ("imprimir".equals(nombre) || "println".equals(nombre) || "print".equals(nombre)) {
            String variante = ctx.varianteBuiltin(nombre);
            ctx.limpiarParams();
            return variante + "(" + args + ");";
        }
        // contar args para el nombre con numero antes de limpiar
        int numeroArgs = ctx.paramsPendientes.size();
        ctx.limpiarParams();
        // calificar llamadas a metodos de la clase actual sin receptor
        String nombreLlamada = nombre;
        boolean calificado = false;
        if (nombre != null && nombre.indexOf('_') < 0) {
            String claseActual = ctx.claseDeFuncion(ctx.funcionActual);
            if (claseActual != null) {
                String candidato = claseActual + "_" + nombre;
                if (numeroArgs > 0) {
                    candidato = candidato + "_" + numeroArgs;
                }
                if (ctx.funcionesConocidas.contains(candidato)) {
                    nombreLlamada = candidato;
                    calificado = true;
                }
            }
        }
        // agregar el receptor implicito cuando se califico a metodo
        if (calificado) {
            if (args.isEmpty()) {
                args = "this";
            } else {
                args = "this, " + args;
            }
        }
        // detectar llamadas sin retorno por el marcador
        boolean esVoid = "void".equals(tipoResultado);
        // detectar llamadas a funciones void ya definidas
        if (esVoid == false) {
            String retornoConocido = ctx.retornosFuncion.get(nombreLlamada);
            boolean tipoDesconocido = tipoResultado == null || tipoResultado.equals("_");
            if ("void".equals(retornoConocido) && tipoDesconocido) {
                esVoid = true;
            }
        }
        // agregar llamada sin retorno para void al cuerpo
        if (esVoid) {
            return nombreLlamada + "(" + args + ");";
        }
        // usar el retorno conocido cuando trae tipo valido
        String tipo = ctx.mapearTipo(tipoResultado);
        String retornoConocido = ctx.retornosFuncion.get(nombreLlamada);
        if (retornoConocido != null && retornoConocido.isEmpty() == false && retornoConocido.equals("_") == false) {
            tipo = ctx.mapearTipoConClases(retornoConocido);
        }
        // declarar el destino con el tipo del retorno
        return ctx.prefijoDeclaracion(resultado, tipo) + " = " + nombreLlamada + "(" + args + ");";
    }
}
