package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// leer un campo por offset desde la base del objeto
public class CuartetaAccesoMiembro extends Cuarteta {

    /**
     * Crear un acceso a miembro con operador explicito.
     */
    public CuartetaAccesoMiembro(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // va al base
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

        // resolver el struct duenio del objeto
        String structNombre = ctx.baseDe(getArg1());

        // usar el struct explicito cuando trae layout conocido
        if (getTipoArg1() != null && getTipoArg1().isEmpty() == false && getTipoArg1().equals("_") == false) {
            if (ctx.structs.containsKey(getTipoArg1())) {
                structNombre = getTipoArg1();
            }
        }

        // usar el tipo del parametro cuando la base es param
        if (structNombre == null) {
            String tipoParam = ctx.tiposDeParams.get(getArg1());
            if (tipoParam != null && ctx.structs.containsKey(tipoParam)) {
                structNombre = tipoParam;
            }
        }

        // leer la base expresada para el heap
        String base = null;

        // si es campo propio sin slot leerlo desde el this actual
        if (structNombre == null && getArg1() != null && ctx.slots.containsKey(getArg1()) == false && ctx.nombreClaseActual != null && ctx.nombreClaseActual.isEmpty() == false) {

            // buscar el campo en la clase del metodo en curso
            if (ctx.structs.get(ctx.nombreClaseActual) != null && ctx.structs.get(ctx.nombreClaseActual).containsKey(getArg1())) {

                // tomar el tipo del campo como struct duenio del siguiente nivel
                String tipoCampo = ctx.structs.get(ctx.nombreClaseActual).get(getArg1());
                int offCampo = ctx.offsetDe(ctx.nombreClaseActual, getArg1());

                // armar la lectura del campo desde el this
                if (offCampo >= 0) {
                    String baseThis = ctx.expresionOperando("this");
                    base = ctx.arregloHeapPara(tipoCampo) + "[" + baseThis + " + " + offCampo + "]";
                    structNombre = tipoCampo;
                }

            }

        }

        // buscar el tipo fuente del campo
        String tipoFuente = null;
        int offset = -1;
        if (structNombre != null && getArg2() != null) {

            offset = ctx.offsetDe(structNombre, getArg2());
            if (offset >= 0 && ctx.structs.get(structNombre) != null) {
                tipoFuente = ctx.structs.get(structNombre).get(getArg2());
            }

        }

        // marcar sin layout cuando no se conoce el campo
        if (offset < 0) {

            SlotHS slotMal = ctx.redeclararSlot(getResultado(), "entero");
            String destinoMal = slotMal.getArreglo() + "[framepointer + " + slotMal.getIndice() + "]";
            return "// sin layout para " + getArg2() + ";\n    " + destinoMal + " = 0;";

        }

        // declarar el destino con el tipo del campo
        SlotHS slot = ctx.redeclararSlot(getResultado(), tipoFuente);

        // pasar el struct base al temporal para accesos encadenados
        if (tipoFuente != null && ctx.structs.containsKey(tipoFuente)) {
            ctx.mapaBases.put(getResultado(), tipoFuente);
        }

        // leer del heap con base mas offset
        if (base == null) {
            base = ctx.expresionOperando(getArg1());
        }
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return destino + " = " + ctx.arregloHeapPara(tipoFuente) + "[" + base + " + " + offset + "];";

    }

}
