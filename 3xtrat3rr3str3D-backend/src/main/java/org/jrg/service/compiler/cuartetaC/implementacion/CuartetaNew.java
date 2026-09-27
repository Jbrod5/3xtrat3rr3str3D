package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// instanciar un struct u objeto con campos en heap
public class CuartetaNew extends CuartetaC {

    /**
     * Crear una instanciacion con operador explicito.
     */
    public CuartetaNew(String operador, String arg1, String arg2, String resultado,
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
            ctx.limpiarParams();
            return "";
        }
        // contar campos del struct o usar uno por defecto
        int campos = 1;
        if (arg1 != null && ctx.ordenCampos.containsKey(arg1)) {
            campos = ctx.ordenCampos.get(arg1).size();
            if (campos <= 0) {
                campos = 1;
            }
        }
        // declarar el destino como base entera
        SlotHS slot = ctx.declararSlot(resultado, "entero");
        // recordar el struct duenio del objeto
        if (arg1 != null && arg1.isEmpty() == false && arg1.equals("_") == false) {
            ctx.mapaBases.put(resultado, arg1);
        }
        // tomar la base actual y avanzar por los campos
        String destino = slot.getArreglo() + "[fp + " + slot.getIndice() + "]";
        StringBuilder lineas = new StringBuilder();
        lineas.append(destino).append(" = hptr;\n    ");
        lineas.append("hptr = hptr + ").append(String.valueOf(campos)).append(";");
        // llamar al constructor solo con new de clase conocida
        if ("new".equals(operador) && arg1 != null && ctx.structs.containsKey(arg1)) {
            // armar el nombre con conteo de params reales
            int numeroArgs = ctx.paramsPendientes.size();
            String ctor = arg1 + "_" + arg1;
            if (numeroArgs > 0) {
                ctor = ctor + "_" + numeroArgs;
            }
            if (ctx.funcionesConocidas.contains(ctor)) {
                // pasar la base como this primero
                lineas.append("\n    sptr = sptr + 1;\n    ");
                lineas.append("stackinteger[sptr] = ").append(destino).append(";");
                // pasar cada pendiente empujando la pila
                for (int i = 0; i < ctx.paramsPendientes.size(); i++) {
                    String valor = ctx.expresionOperando(ctx.paramsPendientes.get(i));
                    String tipoP = ctx.tiposParamsPendientes.get(i);
                    String arreglo = ctx.arregloDe(ctx.paramsPendientes.get(i));
                    if (arreglo == null) {
                        arreglo = ctx.arregloPara(tipoP);
                    }
                    lineas.append("\n    sptr = sptr + 1;\n    ");
                    lineas.append(arreglo).append("[sptr] = ").append(valor).append(";");
                }
                lineas.append("\n    ").append(ctor).append("();");
            }
        }
        // limpiar los pendientes en cualquier caso
        ctx.limpiarParams();
        return lineas.toString();
    }
}
