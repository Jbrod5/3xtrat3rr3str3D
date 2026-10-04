package org.jrg.service.compiler.cuartetaC.implementacion;

import java.util.Map;
import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.SlotHS;

// guardar un valor en el slot del destino
public class CuartetaAsignacionSimple extends Cuarteta {

    /**
     * Crear una asignacion con operador explicito.
     */
    public CuartetaAsignacionSimple(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

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

        // pasar el struct base a la variable para accesos encadenados
        if (getArg1() != null && getResultado().contains(".") == false) {

            String baseOrigen = ctx.mapaBases.get(getArg1());
            if (baseOrigen != null) {
                ctx.mapaBases.put(getResultado(), baseOrigen);
            } else if (getTipoResultado() != null && ctx.structs.containsKey(getTipoResultado())) {
                // usar el tipo declarado cuando el origen no trae base
                ctx.mapaBases.put(getResultado(), getTipoResultado());
            }

        }

        // escribir en miembro con punto cuando el destino trae objeto
        if (getResultado().contains(".")) {

            int corte = getResultado().indexOf('.');
            String objetoDest = getResultado().substring(0, corte).trim();
            String campoDest = getResultado().substring(corte + 1).trim();

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
            String valor = ctx.expresionOperando(getArg1());
            return ctx.arregloHeapPara(tipoFuente) + "[" + base + " + " + offset + "] = " + valor + ";";

        }

        // escribir en el heap cuando el destino es campo sin slot
        if (ctx.slots.containsKey(getResultado()) == false && ctx.nombreClaseActual != null && ctx.nombreClaseActual.isEmpty() == false) {

            Map<String, String> campos = ctx.structs.get(ctx.nombreClaseActual);
            if (campos != null && campos.containsKey(getResultado())) {

                String tipoCampo = campos.get(getResultado());
                int off = ctx.offsetDe(ctx.nombreClaseActual, getResultado());
                if (off >= 0) {

                    // resolver la base del this actual
                    String valor = ctx.expresionOperando("this");
                    return ctx.arregloHeapPara(tipoCampo) + "[" + valor + " + " + off + "] = " + ctx.expresionOperando(getArg1()) + ";";

                }

            }

        }

        // declarar el destino con el tipo del getResultado() si trae
        String tipoDestino = getTipoResultado();
        if (tipoDestino == null || tipoDestino.isEmpty() || tipoDestino.equals("_")) {
            tipoDestino = getTipoArg1();
        }

        // acumular lineas previas para conversiones con malloc
        StringBuilder previas = new StringBuilder();

        // resolver el valor del origen
        String valor = ctx.expresionOperando(getArg1());

        // detectar si el destino es campo propio del heap
        boolean destinoCampo = ctx.esCampoPropio(getResultado());

        // resolver el arreglo destino sin crear slots para campos
        String arregloDestino = null;
        SlotHS slot = null;
        if (destinoCampo) {
            arregloDestino = ctx.arregloHeapPara(tipoDestino);
        } else {
            slot = ctx.declararSlot(getResultado(), tipoDestino);
            arregloDestino = slot.getArreglo();
        }

        // convertir texto a numero cuando el destino es numerico
        if (esTextoOrigen(ctx, getArg1(), getTipoArg1()) && esNumericoDestino(tipoDestino, arregloDestino)) {
            valor = conversionTextoANumero(valor, tipoDestino, arregloDestino);
        }

        // convertir numero a texto cuando el destino es texto
        if (esNumericoOrigen(ctx, getArg1(), getTipoArg1()) && esTextoDestino(tipoDestino, arregloDestino)) {
            valor = conversionNumeroATexto(ctx, previas, valor, getTipoArg1(), getArg1());
        }

        // guardar en el heap cuando el destino es campo propio
        if (destinoCampo) {
            return previas.toString() + ctx.guardarEnCampo(getResultado(), valor);
        }

        // guardar en su arreglo con su direccion
        return previas.toString() + slot.getArreglo() + "[framepointer + " + slot.getIndice() + "] = " + valor + ";";

    }

    // verificar si el origen trae texto que pide conversion
    private boolean esTextoOrigen(ContextoTraduccion ctx, String arg1, String tipoOrigen) {

        // tratar guion como desconocido sin conversion salvo prueba de slot
        if (tipoOrigen == null || tipoOrigen.isEmpty() || tipoOrigen.equals("_")) {
            return esArregloTexto(ctx, arg1);
        }

        if (tipoOrigen.equals("cadena") || tipoOrigen.equals("textum") || tipoOrigen.equals("String") || tipoOrigen.equals("char*")) {
            return true;
        }

        // el literal entre comillas siempre es texto
        if (arg1 != null && arg1.startsWith("\"")) {
            return true;
        }

        return false;

    }

    // verificar si el valor vive en un arreglo de texto
    private boolean esArregloTexto(ContextoTraduccion ctx, String arg1) {

        // sin nombre no hay slot que revisar
        if (arg1 == null || arg1.isEmpty() || arg1.equals("_")) {
            return false;
        }

        // preguntar el arreglo real del slot
        String arreglo = ctx.arregloDe(arg1);
        return "stackstring".equals(arreglo) || "heapstring".equals(arreglo);

    }

    // verificar si el destino guarda numeros o booleanos
    private boolean esNumericoDestino(String tipoDestino, String arregloDestino) {

        // usar el arreglo real cuando el tipo viene vacio
        if (tipoDestino == null || tipoDestino.isEmpty() || tipoDestino.equals("_")) {
            return arregloDestino.equals("stackinteger") || arregloDestino.equals("stackfloat") || arregloDestino.equals("stackchar") || arregloDestino.equals("stackboolean") || arregloDestino.equals("heapinteger") || arregloDestino.equals("heapfloat") || arregloDestino.equals("heapchar") || arregloDestino.equals("heapboolean");
        }

        if (tipoDestino.equals("entero") || tipoDestino.equals("numerus") || tipoDestino.equals("int")) {
            return true;
        }

        if (tipoDestino.equals("flotante") || tipoDestino.equals("decimalis") || tipoDestino.equals("double")) {
            return true;
        }

        if (tipoDestino.equals("caracter") || tipoDestino.equals("littera") || tipoDestino.equals("char")) {
            return true;
        }

        if (tipoDestino.equals("booleano") || tipoDestino.equals("bool") || tipoDestino.equals("boolean")) {
            return true;
        }

        return false;

    }

    // armar la conversion de texto al numero del destino
    private String conversionTextoANumero(String valor, String tipoDestino, String arregloDestino) {

        // decidir por tipo declarado o por arreglo real
        boolean esFlotante = tipoDestino.equals("flotante") || tipoDestino.equals("decimalis") || tipoDestino.equals("double") || arregloDestino.equals("stackfloat") || arregloDestino.equals("heapfloat");
        boolean esCaracter = tipoDestino.equals("caracter") || tipoDestino.equals("littera") || tipoDestino.equals("char") || arregloDestino.equals("stackchar") || arregloDestino.equals("heapchar");

        // convertir con atof para flotantes
        if (esFlotante) {
            return "atof(" + valor + ")";
        }

        // tomar el primer caracter para caracteres
        if (esCaracter) {
            return valor + "[0]";
        }

        // convertir con atoi para enteros y booleanos
        return "atoi(" + valor + ")";

    }

    // verificar si el origen trae numero que pide conversion a texto
    private boolean esNumericoOrigen(ContextoTraduccion ctx, String arg1, String tipoOrigen) {

        // tratar guion como desconocido salvo prueba de slot
        if (tipoOrigen == null || tipoOrigen.isEmpty() || tipoOrigen.equals("_")) {
            return esArregloNumerico(ctx, arg1);
        }

        if (tipoOrigen.equals("entero") || tipoOrigen.equals("numerus") || tipoOrigen.equals("int")) {
            return true;
        }

        if (tipoOrigen.equals("flotante") || tipoOrigen.equals("decimalis") || tipoOrigen.equals("double")) {
            return true;
        }

        if (tipoOrigen.equals("caracter") || tipoOrigen.equals("littera") || tipoOrigen.equals("char")) {
            return true;
        }

        if (tipoOrigen.equals("booleano") || tipoOrigen.equals("bool") || tipoOrigen.equals("boolean")) {
            return true;
        }

        return false;

    }

    // verificar si el destino guarda texto
    private boolean esTextoDestino(String tipoDestino, String arregloDestino) {

        // usar el arreglo real cuando el tipo viene vacio
        if (tipoDestino == null || tipoDestino.isEmpty() || tipoDestino.equals("_")) {
            return arregloDestino.equals("stackstring") || arregloDestino.equals("heapstring");
        }

        if (tipoDestino.equals("cadena") || tipoDestino.equals("textum") || tipoDestino.equals("String") || tipoDestino.equals("char*")) {
            return true;
        }

        return false;

    }

    // verificar si el valor vive en un arreglo numerico
    private boolean esArregloNumerico(ContextoTraduccion ctx, String arg1) {

        // sin nombre no hay slot que revisar
        if (arg1 == null || arg1.isEmpty() || arg1.equals("_")) {
            return false;
        }

        // preguntar el arreglo real del slot
        String arreglo = ctx.arregloDe(arg1);
        if ("stackinteger".equals(arreglo) || "heapinteger".equals(arreglo)) {
            return true;
        }

        if ("stackfloat".equals(arreglo) || "heapfloat".equals(arreglo)) {
            return true;
        }

        if ("stackchar".equals(arreglo) || "heapchar".equals(arreglo)) {
            return true;
        }

        if ("stackboolean".equals(arreglo) || "heapboolean".equals(arreglo)) {
            return true;
        }

        return false;

    }

    // elegir el formato segun el tipo o el arreglo real del origen
    private String formatoParaOrigen(ContextoTraduccion ctx, String arg1, String tipoOrigen) {

        // usar el tipo declarado cuando trae familia
        if (tipoOrigen != null && tipoOrigen.isEmpty() == false && tipoOrigen.equals("_") == false) {

            if (tipoOrigen.equals("flotante") || tipoOrigen.equals("decimalis") || tipoOrigen.equals("double")) {
                return "%f";
            }

            if (tipoOrigen.equals("caracter") || tipoOrigen.equals("littera") || tipoOrigen.equals("char")) {
                return "%c";
            }

            return "%d";

        }

        // usar el arreglo real cuando el tipo es guion
        String arreglo = ctx.arregloDe(arg1);
        if ("stackfloat".equals(arreglo) || "heapfloat".equals(arreglo)) {
            return "%f";
        }

        if ("stackchar".equals(arreglo) || "heapchar".equals(arreglo)) {
            return "%c";
        }

        return "%d";

    }

    // armar la conversion de numero a texto con sprintf
    private String conversionNumeroATexto(ContextoTraduccion ctx, StringBuilder previas, String valor, String tipoOrigen, String arg1) {

        // elegir el formato segun la familia del origen
        String formato = formatoParaOrigen(ctx, arg1, tipoOrigen);

        // reservar un temporal propio para no pisar operandos
        String temporal = "__strhs_" + ctx.contadorCadenas;
        ctx.contadorCadenas = ctx.contadorCadenas + 1;
        previas.append("char* ").append(temporal).append(" = malloc(32);\n    ");
        previas.append("sprintf(").append(temporal).append(", \"").append(formato).append("\", ").append(valor).append(");\n    ");
        return temporal;

    }

}
