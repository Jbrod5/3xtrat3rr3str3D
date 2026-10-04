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

        // resolver operandos aceptando referencias con punto de un nivel
        String primero = operandoMiembro(ctx, getArg1());
        String segundo = operandoMiembro(ctx, getArg2());

        // guardar directo en el heap cuando el destino es campo propio
        if (ctx.esCampoPropio(getResultado())) {

            // elegir registros del arreglo del campo
            String tipoCampo = ctx.structs.get(ctx.nombreClaseActual).get(getResultado());
            String regCampo = ctx.registroPara(ctx.arregloHeapPara(tipoCampo));
            String regOtro = regCampo.replace("AX_", "BX_");
            previas.append(regCampo).append(" = ").append(primero).append(";\n    ").append(regOtro).append(" = ").append(segundo).append(";\n    ").append(regCampo).append(" = ").append(regCampo).append(" ").append(getOperador()).append(" ").append(regOtro).append(";\n    ").append(ctx.guardarEnCampo(getResultado(), regCampo));

            return previas.toString();

        }

        // guardar directo en el heap cuando el destino trae punto
        if (getResultado() != null && getResultado().contains(".")) {

            // partir objeto y campo del destino
            int corteDestino = getResultado().indexOf('.');
            String objetoDestino = getResultado().substring(0, corteDestino).trim();
            String campoDestino = getResultado().substring(corteDestino + 1).trim();

            // resolver el struct del objeto por base mapa o params
            String structDestino = ctx.baseDe(objetoDestino);
            if (structDestino == null) {
                String tipoParamDestino = ctx.tiposDeParams.get(objetoDestino);
                if (tipoParamDestino != null && ctx.structs.containsKey(tipoParamDestino)) {
                    structDestino = tipoParamDestino;
                }
            }

            // operar contra el heap cuando hay layout
            if (structDestino != null && campoDestino != null) {

                int offDestino = ctx.offsetDe(structDestino, campoDestino);
                if (offDestino >= 0 && ctx.structs.get(structDestino) != null) {

                    String tipoCampoDestino = ctx.structs.get(structDestino).get(campoDestino);
                    String arregloCampo = ctx.arregloHeapPara(tipoCampoDestino);
                    String regCampo = ctx.registroPara(arregloCampo);
                    String regOtro = regCampo.replace("AX_", "BX_");
                    String baseDestino = ctx.expresionOperando(objetoDestino);
                    previas.append(regCampo).append(" = ").append(arregloCampo).append("[").append(baseDestino).append(" + ").append(String.valueOf(offDestino)).append("];\n    ");
                    previas.append(regOtro).append(" = ").append(segundo).append(";\n    ");
                    previas.append(regCampo).append(" = ").append(regCampo).append(" ").append(getOperador()).append(" ").append(regOtro).append(";\n    ");
                    previas.append(arregloCampo).append("[").append(baseDestino).append(" + ").append(String.valueOf(offDestino)).append("] = ").append(regCampo).append(";");

                    return previas.toString();

                }

            }

        }

        // declarar el destino con el tipo del resultado
        SlotHS slot = ctx.declararSlot(getResultado(), getTipoResultado());

        // elegir registros del arreglo destino
        String regA = ctx.registroPara(slot.getArreglo());
        String regB = regA.replace("AX_", "BX_");

        // cargar operar y guardar con registros
        String destino = slot.getArreglo() + "[framepointer + " + slot.getIndice() + "]";
        return regA + " = " + primero + ";\n    " + regB + " = " + segundo + ";\n    " + regA + " = " + regA + " " + getOperador() + " " + regB + ";\n    " + destino + " = " + regA + ";";

    }

    // resolver un operando aceptando referencia con punto de un nivel
    private String operandoMiembro(ContextoTraduccion ctx, String arg) {

        // devolver directo si no trae punto
        if (arg == null || arg.contains(".") == false) {
            return ctx.expresionOperando(arg);
        }

        // partir objeto y campo del operando
        int corte = arg.indexOf('.');
        String objeto = arg.substring(0, corte).trim();
        String campo = arg.substring(corte + 1).trim();

        // resolver el struct del objeto por base mapa o params
        String structNombre = ctx.baseDe(objeto);
        if (structNombre == null) {
            String tipoParam = ctx.tiposDeParams.get(objeto);
            if (tipoParam != null && ctx.structs.containsKey(tipoParam)) {
                structNombre = tipoParam;
            }
        }

        // leer del heap cuando hay layout
        if (structNombre != null && campo != null) {

            int off = ctx.offsetDe(structNombre, campo);
            if (off >= 0 && ctx.structs.get(structNombre) != null) {

                String tipoCampo = ctx.structs.get(structNombre).get(campo);
                String base = ctx.expresionOperando(objeto);
                return ctx.arregloHeapPara(tipoCampo) + "[" + base + " + " + off + "]";

            }

        }

        // caer a la resolucion normal sin layout
        return ctx.expresionOperando(arg);

    }

}
