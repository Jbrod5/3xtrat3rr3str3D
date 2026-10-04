package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// pasar args por pila llamar y recoger retorno en AX
public class CuartetaLlamada extends Cuarteta {

    /**
     * Crear una llamada con operador explicito.
     */
    public CuartetaLlamada(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // copiar el nombre para operar sin mutar el campo
        String nombre = getArg1();

        // distinguir llamada simple de llamada a metodo con receptor
        boolean esLlamadaSimple = "call".equals(getOperador());

        // atender impresion con su forma de maquina
        // solo en llamada simple para no tragarse metodos homonimos
        if (esLlamadaSimple && ("imprimir".equals(nombre) || "println".equals(nombre) || "print".equals(nombre))) {

            String salida = "";
            if (ctx.paramsPendientes.isEmpty()) {
                salida = "printf(\"\\n\");\n    fflush(stdout);";
            } else {

                // resolver el unico argumento pendiente
                String valor = ctx.expresionOperando(ctx.paramsPendientes.get(0));

                // usar guion bajo si no hay tipo pendiente
                String tipo0 = "_";
                if (ctx.tiposParamsPendientes.isEmpty() == false) {
                    tipo0 = ctx.tiposParamsPendientes.get(0);
                }
                String arreglo = ctx.arregloDe(ctx.paramsPendientes.get(0));
                if (arreglo == null) {
                    arreglo = ctx.arregloPara(tipo0);
                }

                String formato = "%d";
                String reg = "AX_INT";
                if ("stackstring".equals(arreglo)) {
                    formato = "%s";
                    reg = "AX_STRING";
                } else if ("stackfloat".equals(arreglo)) {
                    formato = "%f";
                    reg = "AX_FLOAT";
                } else if ("stackchar".equals(arreglo)) {
                    formato = "%c";
                    reg = "AX_CHAR";
                } else if ("stackboolean".equals(arreglo)) {
                    formato = "%d";
                    reg = "AX_BOOLEAN";
                }

                salida = reg + " = " + valor + ";\n    printf(\"" + formato + "\\n\", " + reg + ");\n    fflush(stdout);";

            }

            ctx.limpiarParams();
            return salida;

        }

        // atender leer y readln con su builtin
        // solo en llamada simple para no tragarse metodos homonimos
        if (esLlamadaSimple && ("leer".equals(nombre) || "readln".equals(nombre))) {

            if (ctx.builtinsUsados.contains("leer") == false) {
                ctx.builtinsUsados.add("leer");
            }

            ctx.limpiarParams();

            // leer siempre devuelve texto
            SlotHS slot = ctx.redeclararSlot(getResultado(), "cadena");
            String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
            return destino + " = leer();";

        }

        // pasar cada pendiente empujando la pila
        StringBuilder lineas = new StringBuilder();

        // calificar llamadas a metodos de la clase actual sin receptor
        String nombreLlamada = nombre;
        boolean calificado = false;
        if ("call".equals(getOperador()) && nombre != null && nombre.indexOf('_') < 0 && ctx.nombreClaseActual.isEmpty() == false) {

            String candidato = ctx.nombreClaseActual + "_" + nombre;
            int numeroArgs = ctx.paramsPendientes.size();
            if (numeroArgs > 0) {
                candidato = candidato + "_" + numeroArgs;
            }

            if (ctx.funcionesConocidas.contains(candidato)) {
                nombreLlamada = candidato;
                calificado = true;
            }

        }

        // pasar el this primero cuando se califico a metodo
        if (calificado) {

            String baseThis = ctx.expresionOperando("this");
            lineas.append("stackpointer = stackpointer + 1;\n    ");
            lineas.append("stackinteger[stackpointer] = ").append(baseThis).append(";\n    ");

        }

        for (int i = 0; i < ctx.paramsPendientes.size(); i++) {

            String valor = ctx.expresionOperando(ctx.paramsPendientes.get(i));
            String tipoP = ctx.tiposParamsPendientes.get(i);
            String arreglo = ctx.arregloDe(ctx.paramsPendientes.get(i));
            if (arreglo == null) {
                arreglo = ctx.arregloPara(tipoP);
            }

            lineas.append("stackpointer = stackpointer + 1;\n    ");
            lineas.append(arreglo).append("[stackpointer] = ").append(valor).append(";\n    ");

        }

        // calificar con el receptor cuando trae metodo
        if ("call_method".equals(getOperador())) {

            String receptorTipo = null;
            if (ctx.tiposParamsPendientes.isEmpty() == false) {
                receptorTipo = ctx.tiposParamsPendientes.get(0);
            }

            // buscar el struct base del receptor cuando el tipo viene vacio
            if ((receptorTipo == null || ctx.structs.containsKey(receptorTipo) == false) && ctx.paramsPendientes.isEmpty() == false) {

                String baseObj = ctx.mapaBases.get(ctx.paramsPendientes.get(0));
                if (baseObj != null && ctx.structs.containsKey(baseObj)) {
                    receptorTipo = baseObj;
                }

            }

            if (receptorTipo != null && ctx.structs.containsKey(receptorTipo)) {
                nombreLlamada = receptorTipo + "_" + nombre;
            }

        }

        // contar args reales sin receptor para el nombre con numero
        int numeroReales = ctx.paramsPendientes.size();
        if ("call_method".equals(getOperador()) && numeroReales > 0) {
            numeroReales = numeroReales - 1;
        }

        if (numeroReales > 0 && nombreLlamada.equals(nombre) == false) {

            String conNumero = nombreLlamada + "_" + numeroReales;
            if (ctx.funcionesConocidas.contains(conNumero)) {
                nombreLlamada = conNumero;
            }

        }

        // limpiar los pendientes antes de seguir
        ctx.limpiarParams();

        // llamar sin guardar cuando no hay destino
        if (getResultado() == null || getResultado().isEmpty() || getResultado().equals("_")) {
            lineas.append(nombreLlamada).append("();");
            return lineas.toString();
        }

        // declarar el destino con el retorno conocido si hay
        String tipoRet = getTipoResultado();
        String conocido = ctx.retornosFuncion.get(nombreLlamada);
        if (conocido != null && conocido.isEmpty() == false && conocido.equals("_") == false) {
            tipoRet = conocido;
        }

        // llamar y recoger el retorno directo en el heap si es campo propio
        if (ctx.esCampoPropio(getResultado())) {

            // elegir el registro del tipo del campo
            String tipoCampoLlamada = ctx.structs.get(ctx.nombreClaseActual).get(getResultado());
            String regCampo = ctx.registroPara(ctx.arregloHeapPara(tipoCampoLlamada));

            // elegir el registro del tipo retornado
            String regOrigen = ctx.registroPara(ctx.arregloPara(tipoRet));
            lineas.append(nombreLlamada).append("();\n    ");
            lineas.append(regCampo).append(" = ").append(regOrigen).append(";\n    ");
            lineas.append(ctx.guardarEnCampo(getResultado(), regCampo));

            return lineas.toString();

        }

        SlotHS slot = ctx.redeclararSlot(getResultado(), tipoRet);

        // llamar y recoger el retorno desde AX
        String reg = ctx.registroPara(slot.getArreglo());
        lineas.append(nombreLlamada).append("();\n    ");
        lineas.append(slot.getArreglo()).append("[framepointer + ").append(String.valueOf(slot.getIndice())).append("] = ").append(reg).append(";");
        return lineas.toString();

    }

}
