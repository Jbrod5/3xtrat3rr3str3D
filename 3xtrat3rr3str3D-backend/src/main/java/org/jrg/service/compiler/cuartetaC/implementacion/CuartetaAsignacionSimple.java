package org.jrg.service.compiler.cuartetaC.implementacion;

import java.util.Map;
import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// guardar un valor en el slot del destino
public class CuartetaAsignacionSimple extends CuartetaC {

    /**
     * Crear una asignacion con operador explicito.
     */
    public CuartetaAsignacionSimple(String operador, String arg1, String arg2, String resultado,
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
        // pasar el struct base a la variable para accesos encadenados
        if (arg1 != null && resultado.contains(".") == false) {
            String baseOrigen = ctx.mapaBases.get(arg1);
            if (baseOrigen != null) {
                ctx.mapaBases.put(resultado, baseOrigen);
            }
        }
        // escribir en miembro con punto cuando el destino trae objeto
        if (resultado.contains(".")) {
            int corte = resultado.indexOf('.');
            String objetoDest = resultado.substring(0, corte).trim();
            String campoDest = resultado.substring(corte + 1).trim();
            // resolver el struct duenio del objeto
            String structDest = ctx.baseDe(objetoDest);
            // buscar el tipo fuente del campo
            String tipoFuente = null;
            int offset = -1;
            if (structDest != null) {
                offset = ctx.offsetDe(structDest, campoDest);
                if (offset >= 0 && ctx.structs.get(structDest) != null) {
                    tipoFuente = ctx.structs.get(structDest).get(campoDest);
                }
            }
            // marcar sin layout cuando no se conoce el campo
            if (offset < 0) {
                return "// sin layout para " + campoDest + ";";
            }
            // escribir en el heap con base mas offset
            String base = ctx.expresionOperando(objetoDest);
            String valor = ctx.expresionOperando(arg1);
            return ctx.arregloHeapPara(tipoFuente) + "[" + base + " + " + offset + "] = " + valor + ";";
        }
        // escribir en el heap cuando el destino es campo sin slot
        if (ctx.slots.containsKey(resultado) == false && ctx.nombreClaseActual != null && ctx.nombreClaseActual.isEmpty() == false) {
            Map<String, String> campos = ctx.structs.get(ctx.nombreClaseActual);
            if (campos != null && campos.containsKey(resultado)) {
                String tipoCampo = campos.get(resultado);
                int off = ctx.offsetDe(ctx.nombreClaseActual, resultado);
                if (off >= 0) {
                    // resolver la base del this actual
                    String valor = ctx.expresionOperando("this");
                    return ctx.arregloHeapPara(tipoCampo) + "[" + valor + " + " + off + "] = " + ctx.expresionOperando(arg1) + ";";
                }
            }
        }
        // declarar el destino con el tipo del resultado si trae
        String tipoDestino = tipoResultado;
        if (tipoDestino == null || tipoDestino.isEmpty() || tipoDestino.equals("_")) {
            tipoDestino = tipoArg1;
        }
        SlotHS slot = ctx.declararSlot(resultado, tipoDestino);
        // resolver el valor del origen
        String valor = ctx.expresionOperando(arg1);
        // guardar en su arreglo con su direccion
        return slot.getArreglo() + "[fp + " + slot.getIndice() + "] = " + valor + ";";
    }
}
