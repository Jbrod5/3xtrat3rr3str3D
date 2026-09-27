package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// imprimir un valor con formato segun su arreglo
public class CuartetaPrint extends CuartetaC {

    /**
     * Crear una impresion con operador explicito.
     */
    public CuartetaPrint(String operador, String arg1, String arg2, String resultado,
                   String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // resolver el valor a imprimir
        String valor = ctx.expresionOperando(arg1);

        // elegir formato por la forma del valor primero
        String formato = null;
        String reg = null;
        if (arg1 != null && arg1.startsWith("\"")) {
            formato = "%s";
            reg = "AX_STRING";
        } else if (arg1 != null && arg1.startsWith("'")) {
            formato = "%c";
            reg = "AX_CHAR";
        }

        // elegir formato segun el arreglo del slot si no hay forma
        if (formato == null) {

            String arreglo = ctx.arregloDe(arg1);
            if (arreglo == null) {
                arreglo = ctx.arregloPara(tipoArg1);
            }

            formato = "%d";
            reg = "AX_INT";
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

        }

        // imprimir por registro con salto y flush
        return reg + " = " + valor + ";\n    printf(\"" + formato + "\\n\", " + reg + ");\n    fflush(stdout);";

    }

}
