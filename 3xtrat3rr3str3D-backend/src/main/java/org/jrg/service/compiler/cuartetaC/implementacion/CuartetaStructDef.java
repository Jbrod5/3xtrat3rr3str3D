package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.riscv.ContextoRiscV;

// marcar definicion de struct sin emitir codigo
public class CuartetaStructDef extends Cuarteta {

    /**
     * Crear un marcador de struct con operador explicito.
     */
    public CuartetaStructDef(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // los structs solo alimentan tablas
        return "";

    }

    /**
     * Sacar las lineas de ensamblador RISC-V de la cuarteta.
     */
    @Override
    public String obtenerCodigoRiscv(ContextoRiscV ctx) {

        // los structs solo alimentan tablas
        return "";

    }

}
