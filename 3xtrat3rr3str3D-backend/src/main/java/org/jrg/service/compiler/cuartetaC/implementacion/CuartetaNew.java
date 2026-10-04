package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// instanciar un struct u objeto con campos en heap
public class CuartetaNew extends Cuarteta {

    /**
     * Crear una instanciacion con operador explicito.
     */
    public CuartetaNew(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

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
            ctx.limpiarParams();
            return "";
        }

        // contar campos del struct o usar uno por defecto
        int campos = 1;
        if (getArg1() != null && ctx.ordenCampos.containsKey(getArg1())) {

            campos = ctx.ordenCampos.get(getArg1()).size();
            if (campos <= 0) {
                campos = 1;
            }

        }

        // resolver donde vive la base segun campo propio o slot
        StringBuilder lineas = new StringBuilder();
        String destino = null;
        if (ctx.esCampoPropio(getResultado())) {
            destino = ctx.baseDeCampo(getResultado());
        } else {

            SlotHS slot = ctx.declararSlot(getResultado(), "entero");
            destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";

            // recordar el struct duenio del objeto
            if (getArg1() != null && getArg1().isEmpty() == false && getArg1().equals("_") == false) {
                ctx.mapaBases.put(getResultado(), getArg1());
            }

        }

        // tomar la base actual y avanzar por los campos
        lineas.append(destino).append(" = heappointer;\n    ");
        lineas.append("heappointer = heappointer + ").append(String.valueOf(campos)).append(";");

        // llamar al constructor solo con new de clase conocida
        if ("new".equals(getOperador()) && getArg1() != null && ctx.structs.containsKey(getArg1())) {

            // armar el nombre con conteo de params reales
            int numeroArgs = ctx.paramsPendientes.size();
            String ctor = getArg1() + "_" + getArg1();
            if (numeroArgs > 0) {
                ctor = ctor + "_" + numeroArgs;
            }

            if (ctx.funcionesConocidas.contains(ctor)) {

                // pasar la base como this primero
                lineas.append("\n    stackpointer = stackpointer + 1;\n    ");
                lineas.append("stackinteger[stackpointer] = ").append(destino).append(";");

                // pasar cada pendiente empujando la pila
                for (int i = 0; i < ctx.paramsPendientes.size(); i++) {

                    String valor = ctx.expresionOperando(ctx.paramsPendientes.get(i));
                    String tipoP = ctx.tiposParamsPendientes.get(i);
                    String arreglo = ctx.arregloDe(ctx.paramsPendientes.get(i));
                    if (arreglo == null) {
                        arreglo = ctx.arregloPara(tipoP);
                    }

                    lineas.append("\n    stackpointer = stackpointer + 1;\n    ");
                    lineas.append(arreglo).append("[stackpointer] = ").append(valor).append(";");

                }

                lineas.append("\n    ").append(ctor).append("();");

            }

        }

        // limpiar los pendientes en cualquier caso
        ctx.limpiarParams();
        return lineas.toString();

    }

}
