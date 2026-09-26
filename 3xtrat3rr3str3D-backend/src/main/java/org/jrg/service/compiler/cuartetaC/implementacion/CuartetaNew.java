package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir instanciacion de objetos y structs a C
public class CuartetaNew extends CuartetaC {

    /**
     * Crear una cuarteta de instanciacion con operador explicito.
     */
    public CuartetaNew(String operador, String arg1, String arg2, String resultado,
                        String tipoArg1, String tipoArg2, String tipoResultado) {
        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de instanciacion con operador new por defecto.
     */
    public CuartetaNew(String arg1, String arg2, String resultado,
                        String tipoArg1, String tipoArg2, String tipoResultado) {
        // cae al base con operador fijo
        super("new", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // traducir instanciacion de objeto en heap con malloc
        if ("new".equals(operador)) {
            String nombreClase = arg1;
            String destinoNew = resultado;
            // reservar heap solo para tipos con struct conocido
            if (nombreClase != null && (ctx.clases.contains(nombreClase) || ctx.structs.containsKey(nombreClase))) {
                // contar args antes de limpiar para el nombre con numero
                int numeroArgs = ctx.paramsPendientes.size();
                String argsNew = ctx.unirParams();
                ctx.limpiarParams();
                // marcar el destino como puntero a la clase
                if (destinoNew != null) {
                    ctx.structDeNombre.put(destinoNew, nombreClase);
                    ctx.punteros.add(destinoNew);
                }
                String lineaNew = ctx.ladoIzquierdo(destinoNew, "struct " + nombreClase + "*") + " = malloc(sizeof(struct " + nombreClase + "));";
                // llamar al constructor cuando existe en las cuartetas
                String ctor = nombreClase + "_" + nombreClase;
                // agregar sufijo con el conteo para constructores sobrecargados
                if (numeroArgs > 0) {
                    ctor = ctor + "_" + numeroArgs;
                }
                if (ctx.funcionesConocidas.contains(ctor)) {
                    lineaNew = lineaNew + "\n    " + ctor + "(" + destinoNew;
                    if (argsNew.isEmpty() == false) {
                        lineaNew = lineaNew + ", " + argsNew;
                    }
                    lineaNew = lineaNew + ");";
                }
                return lineaNew;
            }
            // usar marcador cuando el tipo es desconocido
            ctx.limpiarParams();
            String tipoNew = ctx.mapearTipo(arg1);
            return ctx.ladoIzquierdo(resultado, tipoNew) + " = 0;";
        }
        // traducir instanciacion de struct con su tipo real
        ctx.limpiarParams();
        // usar struct cuando el tipo esta definido
        if (arg1 != null && ctx.structs.containsKey(arg1)) {
            // mapear el resultado a su struct
            if (resultado != null) {
                ctx.structDeNombre.put(resultado, arg1);
            }
            return ctx.prefijoDeclaracion(resultado, "struct " + arg1) + ";";
        }
        // usar marcador cuando el tipo es desconocido
        String tipo = ctx.mapearTipo(arg1);
        return ctx.prefijoDeclaracion(resultado, tipo) + " = 0;";
    }
}
