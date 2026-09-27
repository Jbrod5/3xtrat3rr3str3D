package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// devolver un valor por registro AX y limpiar el marco
public class CuartetaRetorno extends CuartetaC {

    /**
     * Crear un retorno con operador explicito.
     */
    public CuartetaRetorno(String operador, String arg1, String arg2, String resultado,
                     String tipoArg1, String tipoArg2, String tipoResultado) {
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // armar el epilogo que limpia el marco
        String epilogo = "stackpointer = framepointer;\n    framestackpointer = framestackpointer - 1;\n    framepointer = framestack[framestackpointer];\n    return;";

        // retornar solo el epilogo cuando no hay valor
        if (arg1 == null || arg1.equals("_")) {
            return epilogo;
        }

        // dejar el valor en su registro natural
        String valor = ctx.expresionOperando(arg1);
        String regNat = "AX_INT";
        String arreglo = ctx.arregloDe(arg1);
        if ("stackfloat".equals(arreglo)) {
            regNat = "AX_FLOAT";
        } else if ("stackstring".equals(arreglo)) {
            regNat = "AX_STRING";
        } else if ("stackchar".equals(arreglo)) {
            regNat = "AX_CHAR";
        } else if ("stackboolean".equals(arreglo)) {
            regNat = "AX_BOOLEAN";
        } else if ("double".equals(tipoArg1) || "decimalis".equals(tipoArg1) || "flotante".equals(tipoArg1)) {
            regNat = "AX_FLOAT";
        } else if ("char*".equals(tipoArg1) || "cadena".equals(tipoArg1) || "textum".equals(tipoArg1) || "String".equals(tipoArg1)) {
            regNat = "AX_STRING";
        } else if ("char".equals(tipoArg1) || "caracter".equals(tipoArg1) || "littera".equals(tipoArg1)) {
            regNat = "AX_CHAR";
        } else if ("bool".equals(tipoArg1) || "boolean".equals(tipoArg1) || "booleano".equals(tipoArg1)) {
            regNat = "AX_BOOLEAN";
        }

        // pasar al registro del retorno declarado para que el que llama lea bien
        String regDest = regNat;
        String retornoDecl = ctx.retornosFuncion.get(ctx.funcionActual);
        if (retornoDecl != null && retornoDecl.isEmpty() == false && retornoDecl.equals("_") == false && retornoDecl.equals("void") == false) {
            String arrDest = ctx.arregloPara(retornoDecl);
            regDest = ctx.registroPara(arrDest);
        }

        // convertir con asignacion cuando cambian de registro
        if (regDest.equals(regNat)) {
            return regNat + " = " + valor + ";\n    " + epilogo;
        }

        return regNat + " = " + valor + ";\n    " + regDest + " = " + regNat + ";\n    " + epilogo;

    }

}
