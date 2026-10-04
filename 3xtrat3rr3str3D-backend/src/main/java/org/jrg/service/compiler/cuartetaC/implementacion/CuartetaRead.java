package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// leer de consola al slot con el formato del tipo destino
public class CuartetaRead extends Cuarteta {

    /**
     * Crear una lectura con operador explicito.
     */
    public CuartetaRead(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // consumir un token sin guardar cuando no hay destino
        if (getResultado() == null || getResultado().equals("_")) {
            return "scanf(\"%*s\");";
        }

        // resolver la familia del tipo destino
        String tipoDestino = getTipoResultado();
        if (tipoDestino == null || tipoDestino.isEmpty()) {
            tipoDestino = "_";
        }

        // leer texto con el builtin que salta blancos y devuelve linea
        if (tipoDestino.equals("cadena") || tipoDestino.equals("textum") || tipoDestino.equals("String") || tipoDestino.equals("char*")) {

            // declarar el destino como texto
            SlotHS slotTexto = ctx.declararSlot(getResultado(), "cadena");
            String destinoTexto = slotTexto.getArreglo() + "[framepointer + " + slotTexto.getIndice() + "]";

            // registrar el builtin para la cabecera
            if (ctx.builtinsUsados.contains("leer") == false) {
                ctx.builtinsUsados.add("leer");
            }

            return destinoTexto + " = leer();";

        }

        // leer flotante con %f
        if (tipoDestino.equals("flotante") || tipoDestino.equals("decimalis") || tipoDestino.equals("double")) {

            // declarar el destino como flotante
            SlotHS slotFlotante = ctx.declararSlot(getResultado(), "flotante");
            String destinoFlotante = slotFlotante.getArreglo() + "[framepointer + " + slotFlotante.getIndice() + "]";
            return "scanf(\"%f\", &" + destinoFlotante + ");";

        }

        // leer caracter saltando blancos con espacio previo
        if (tipoDestino.equals("caracter") || tipoDestino.equals("littera") || tipoDestino.equals("char")) {

            // declarar el destino como caracter
            SlotHS slotCaracter = ctx.declararSlot(getResultado(), "caracter");
            String destinoCaracter = slotCaracter.getArreglo() + "[framepointer + " + slotCaracter.getIndice() + "]";
            return "scanf(\" %c\", &" + destinoCaracter + ");";

        }

        // leer booleano como cero o uno
        if (tipoDestino.equals("booleano") || tipoDestino.equals("bool") || tipoDestino.equals("boolean")) {

            // declarar el destino como booleano
            SlotHS slotBool = ctx.declararSlot(getResultado(), "booleano");
            String destinoBool = slotBool.getArreglo() + "[framepointer + " + slotBool.getIndice() + "]";
            return "scanf(\"%d\", &" + destinoBool + ");";

        }

        // declarar el destino entero por defecto
        SlotHS slot = ctx.declararSlot(getResultado(), "entero");

        // leer con scanf sobre el slot
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return "scanf(\"%d\", &" + destino + ");";

    }

}
