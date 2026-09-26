package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;

// traducir una lectura declarando la variable si es nueva a C
public class CuartetaRead extends CuartetaC {

    /**
     * Crear una cuarteta de lectura con operador explicito.
     */
    public CuartetaRead(String operador, String arg1, String arg2, String resultado,
                         String tipoArg1, String tipoArg2, String tipoResultado) {
        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Crear una cuarteta de lectura con operador fijo.
     */
    public CuartetaRead(String arg1, String arg2, String resultado,
                         String tipoArg1, String tipoArg2, String tipoResultado) {
        // cae al base con operador fijo
        super("read", arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);
    }

    /**
     * Obtener la linea de codigo C para la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {
        // copiar el destino para declarar sin mutar el campo
        String destino = resultado;
        // omitir la lectura sin variable destino
        if (destino == null || destino.equals("_")) {
            return "";
        }
        // iniciar la linea sin declaracion por defecto
        String linea = "";
        // declarar la variable si aun no existe
        if (destino != null && destino.equals("_") == false && ctx.declaradas.contains(destino) == false) {
            linea = "int " + destino + ";\n    ";
            ctx.declaradas.add(destino);
            ctx.tiposDeclarados.put(destino, "int");
        }
        // construir la lectura con scanf sobre el destino
        return linea + "scanf(\"%d\", &" + destino + ");";
    }
}
