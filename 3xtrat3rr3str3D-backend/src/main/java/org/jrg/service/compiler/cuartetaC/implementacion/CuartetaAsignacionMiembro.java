package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una asignacion a miembro con indice o nombre a C
public class CuartetaAsignacionMiembro extends CuartetaC {

    /**
     * Crear una cuarteta de asignacion a miembro con operador explicito.
     */
    public CuartetaAsignacionMiembro(String operador, String arg1, String arg2, String resultado,
                                      String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de asignacion a miembro con operador fijo.
     */
    public CuartetaAsignacionMiembro(String arg1, String arg2, String resultado,
                                      String tipoArg1, String tipoArg2, String tipoResultado) {
        // delegar al constructor de la clase base con operador punto igual
        super(".,=", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // copiar el campo para resolver indices sin mutar el campo
        String campo = arg2;
        // resolver indices numericos a nombres de campo
        if (campo != null && ctx.esNumerico(campo.trim())) {
            campo = ctx.nombreCampoPorIndice(arg1, campo);
        }
        // usar respaldo cuando el campo es nulo
        if (campo == null) {
            campo = "campo0";
        }
        // usar punto por defecto para structs por valor
        String accesoMiembro = ".";
        // usar flecha cuando el objeto es puntero a heap
        if (ctx.esPuntero(arg1)) {
            accesoMiembro = "->";
        }
        // construir la escritura del miembro con el valor traducido
        return ctx.crearValor(arg1).obtenerCodigoC(ctx) + accesoMiembro + campo + " = " + ctx.crearValor(resultado).obtenerCodigoC(ctx) + ";";
    }
}
