package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// operar dos valores con registros AX y BX
public class CuartetaAritmetica extends CuartetaC {

    /**
     * Crear una aritmetica con operador explicito.
     */
    public CuartetaAritmetica(String operador, String arg1, String arg2, String resultado,
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
        // declarar el destino con el tipo del resultado
        SlotHS slot = ctx.declararSlot(resultado, tipoResultado);
        // resolver los operandos a expresiones
        String primero = ctx.expresionOperando(arg1);
        String segundo = ctx.expresionOperando(arg2);
        // elegir registros del arreglo destino
        String regA = ctx.registroPara(slot.getArreglo());
        String regB = regA.replace("AX_", "BX_");
        // cargar operar y guardar con registros
        String destino = slot.getArreglo() + "[fp + " + slot.getIndice() + "]";
        return regA + " = " + primero + ";\n    " + regB + " = " + segundo + ";\n    " + regA + " = " + regA + " " + operador + " " + regB + ";\n    " + destino + " = " + regA + ";";
    }
}
