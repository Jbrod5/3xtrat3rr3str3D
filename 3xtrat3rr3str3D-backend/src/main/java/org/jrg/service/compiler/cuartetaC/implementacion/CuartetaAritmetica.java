package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// operar dos valores con registros AX y BX
public class CuartetaAritmetica extends Cuarteta {

    /**
     * Crear una aritmetica con operador explicito.
     */
    public CuartetaAritmetica(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // omitir destinos sin nombre valido
        if (getResultado() == null || getResultado().isEmpty() || getResultado().equals("_")) {
            return "";
        }

        // acumular lineas previas para campos propios
        StringBuilder previas = new StringBuilder();

        // resolver los operandos a expresiones
        String primero = ctx.expresionOperando(getArg1());
        String segundo = ctx.expresionOperando(getArg2());

        // guardar directo en el heap cuando el destino es campo propio
        if (ctx.esCampoPropio(getResultado())) {

            // elegir registros del arreglo del campo
            String tipoCampo = ctx.structs.get(ctx.nombreClaseActual).get(getResultado());
            String regCampo = ctx.registroPara(ctx.arregloHeapPara(tipoCampo));
            String regOtro = regCampo.replace("AX_", "BX_");
            previas.append(regCampo).append(" = ").append(primero).append(";\n    ").append(regOtro).append(" = ").append(segundo).append(";\n    ").append(regCampo).append(" = ").append(regCampo).append(" ").append(getOperador()).append(" ").append(regOtro).append(";\n    ").append(ctx.guardarEnCampo(getResultado(), regCampo));

            return previas.toString();

        }

        // declarar el destino con el tipo del getResultado()
        SlotHS slot = ctx.declararSlot(getResultado(), getTipoResultado());

        // elegir registros del arreglo destino
        String regA = ctx.registroPara(slot.getArreglo());
        String regB = regA.replace("AX_", "BX_");

        // cargar operar y guardar con registros
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return regA + " = " + primero + ";\n    " + regB + " = " + segundo + ";\n    " + regA + " = " + regA + " " + getOperador() + " " + regB + ";\n    " + destino + " = " + regA + ";";

    }

}
