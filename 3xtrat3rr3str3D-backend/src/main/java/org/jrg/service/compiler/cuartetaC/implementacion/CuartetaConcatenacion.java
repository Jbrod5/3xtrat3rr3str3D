package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una concatenacion de strings con malloc strcpy strcat
public class CuartetaConcatenacion extends CuartetaC {

    /**
     * Crear una cuarteta de concatenacion con operador explicito.
     */
    public CuartetaConcatenacion(String operador, String arg1, String arg2, String resultado,
                                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de concatenacion con operador suma fijo.
     */
    public CuartetaConcatenacion(String arg1, String arg2, String resultado,
                                  String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador suma
        super("+", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // convertir operandos numericos a string antes de concatenar
        StringBuilder previasConcat = new StringBuilder();
        // preparar el primer operando con posible conversion
        String primero = ctx.prepararOperandoParaConcat(arg1, tipoArg1, previasConcat);
        // preparar el segundo operando con posible conversion
        String segundo = ctx.prepararOperandoParaConcat(arg2, tipoArg2, previasConcat);
        // declarar el temporal solo la primera vez
        String prefijo = ctx.prefijoDeclaracion(resultado, "char*");
        // agregar conversiones previas mas reserva copia y concatenado al cuerpo
        return previasConcat.toString() + prefijo + " = malloc(strlen(" + primero + ") + strlen(" + segundo + ") + 1);\n    strcpy(" + resultado + ", " + primero + ");\n    strcat(" + resultado + ", " + segundo + ");";
    }
}
