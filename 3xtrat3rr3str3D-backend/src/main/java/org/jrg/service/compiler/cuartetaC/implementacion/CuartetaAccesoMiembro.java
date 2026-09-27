package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// leer un campo por offset desde la base del objeto
public class CuartetaAccesoMiembro extends CuartetaC {

    /**
     * Crear un acceso a miembro con operador explicito.
     */
    public CuartetaAccesoMiembro(String operador, String arg1, String arg2, String resultado,
                           String tipoArg1, String tipoArg2, String tipoResultado) {
        // va al base
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

        // resolver el struct duenio del objeto
        String structNombre = ctx.baseDe(arg1);

        // buscar el tipo fuente del campo
        String tipoFuente = null;
        int offset = -1;
        if (structNombre != null && arg2 != null) {

            offset = ctx.offsetDe(structNombre, arg2);
            if (offset >= 0 && ctx.structs.get(structNombre) != null) {
                tipoFuente = ctx.structs.get(structNombre).get(arg2);
            }

        }

        // marcar sin layout cuando no se conoce el campo
        if (offset < 0) {

            SlotHS slotMal = ctx.redeclararSlot(resultado, "entero");
            String destinoMal = slotMal.getArreglo() + "[framepointer + " + slotMal.getIndice() + "]";
            return "// sin layout para " + arg2 + ";\n    " + destinoMal + " = 0;";

        }

        // declarar el destino con el tipo del campo
        SlotHS slot = ctx.redeclararSlot(resultado, tipoFuente);

        // leer del heap con base mas offset
        String base = ctx.expresionOperando(arg1);
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return destino + " = " + ctx.arregloHeapPara(tipoFuente) + "[" + base + " + " + offset + "];";

    }

}
