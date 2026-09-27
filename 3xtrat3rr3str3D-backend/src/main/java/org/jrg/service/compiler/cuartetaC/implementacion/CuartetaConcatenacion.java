package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// concatenar dos textos con reserva en heap
public class CuartetaConcatenacion extends CuartetaC {

    /**
     * Crear una concatenacion con operador explicito.
     */
    public CuartetaConcatenacion(String operador, String arg1, String arg2, String resultado,
                    String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // omitir destinos sin nombre valido
        if (resultado == null || resultado.isEmpty() || resultado.equals("_")) {
            return "";
        }

        // preparar los operandos convirtiendo numericos
        StringBuilder previas = new StringBuilder();
        String primero = preparar(ctx, arg1, tipoArg1, previas);
        String segundo = preparar(ctx, arg2, tipoArg2, previas);

        // declarar el temporal siempre texto
        SlotHS slot = ctx.declararSlot(resultado, "cadena");
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";

        // agregar reserva copia y concatenado al cuerpo
        previas.append(destino).append(" = malloc(strlen(").append(primero).append(") + strlen(").append(segundo).append(") + 1);\n    ");
        previas.append("strcpy(").append(destino).append(", ").append(primero).append(");\n    ");
        previas.append("strcat(").append(destino).append(", ").append(segundo).append(");");
        return previas.toString();

    }

    // preparar un operando convirtiendo numericos a texto
    private String preparar(ContextoTraduccion ctx, String valor, String tipo, StringBuilder previas) {

        // resolver el valor a expresion
        String texto = ctx.expresionOperando(valor);

        // averiguar el arreglo real del operando
        String arreglo = ctx.arregloDe(valor);
        if (arreglo == null) {
            arreglo = ctx.arregloPara(tipo);
        }

        // elegir el formato segun el arreglo
        String formato = null;
        if ("stackinteger".equals(arreglo) || "heapinteger".equals(arreglo)) {
            formato = "%d";
        } else if ("stackfloat".equals(arreglo) || "heapfloat".equals(arreglo)) {
            formato = "%f";
        } else if ("stackchar".equals(arreglo) || "heapchar".equals(arreglo)) {
            formato = "%c";
        } else if ("stackboolean".equals(arreglo) || "heapboolean".equals(arreglo)) {
            formato = "%d";
        }

        // devolver directo cuando ya es texto
        if (formato == null) {
            return texto;
        }

        // convertir con sprintf a temporal propio
        String tempStr = "__strhs_" + ctx.contadorCadenas;
        ctx.contadorCadenas = ctx.contadorCadenas + 1;
        previas.append("char* ").append(tempStr).append(" = malloc(32);\n    ");
        previas.append("sprintf(").append(tempStr).append(", \"").append(formato).append("\", ").append(texto).append(");\n    ");
        return tempStr;

    }

}
