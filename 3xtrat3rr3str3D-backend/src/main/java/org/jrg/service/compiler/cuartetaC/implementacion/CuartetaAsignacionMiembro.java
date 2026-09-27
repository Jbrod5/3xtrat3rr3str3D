package org.jrg.service.compiler.cuartetaC.implementacion;

import java.util.List;
import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// escribir un campo por offset desde la base del objeto
public class CuartetaAsignacionMiembro extends CuartetaC {

    /**
     * Crear una asignacion a miembro con operador explicito.
     */
    public CuartetaAsignacionMiembro(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // delegar al constructor de la clase base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // resolver el campo por indice cuando viene numerico
        String campo = arg2;
        if (campo != null && ctx.esNumerico(campo.trim())) {
            campo = nombrePorIndice(ctx, arg1, campo.trim());
        }

        // omitir campos nulos
        if (campo == null) {
            campo = "campo0";
        }

        // resolver el struct duenio del objeto
        String structNombre = ctx.baseDe(arg1);

        // buscar el tipo fuente del campo
        String tipoFuente = null;
        int offset = -1;
        if (structNombre != null) {

            offset = ctx.offsetDe(structNombre, campo);
            if (offset >= 0 && ctx.structs.get(structNombre) != null) {
                tipoFuente = ctx.structs.get(structNombre).get(campo);
            }

        }

        // marcar sin layout cuando no se conoce el campo
        if (offset < 0) {
            return "// sin layout para " + campo + ";";
        }

        // escribir en el heap con base mas offset
        String base = ctx.expresionOperando(arg1);
        String valor = ctx.expresionOperando(resultado);
        return ctx.arregloHeapPara(tipoFuente) + "[" + base + " + " + offset + "] = " + valor + ";";

    }

    // resolver el nombre del campo por indice dentro del struct
    private String nombrePorIndice(ContextoTraduccion ctx, String objeto, String indice) {

        // buscar el struct del objeto
        String structNombre = ctx.baseDe(objeto);

        // verificar que el struct tenga orden de campos
        List<String> orden = null;
        if (structNombre != null) {
            orden = ctx.ordenCampos.get(structNombre);
        }

        // mapear el indice al nombre cuando es posible
        if (orden != null && indice != null) {

            try {

                int posicion = Integer.parseInt(indice.trim());
                if (posicion >= 0 && posicion < orden.size()) {
                    return orden.get(posicion);
                }

            } catch (NumberFormatException e) {
                // usar el respaldo cuando el indice no es numero
            }

        }

        // usar nombre de respaldo con el indice crudo
        if (indice == null) {
            return "campo0";
        }

        return "campo" + indice.trim();

    }

}
