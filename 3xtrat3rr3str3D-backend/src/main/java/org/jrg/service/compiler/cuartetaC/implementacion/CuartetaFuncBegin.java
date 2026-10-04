package org.jrg.service.compiler.cuartetaC.implementacion;

import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.riscv.ContextoRiscV;

// marcar inicio de funcion sin emitir codigo
public class CuartetaFuncBegin extends Cuarteta {

    /**
     * Crear un marcador de inicio con operador explicito.
     */
    public CuartetaFuncBegin(String operador, String arg1, String arg2, String resultado, String tipoArg1, String tipoArg2, String tipoResultado) {

        // va al base
        super(operador, arg1, arg2, resultado, tipoArg1, tipoArg2, tipoResultado);

    }

    /**
     * Sacar las lineas de codigo de la cuarteta.
     */
    @Override
    public String obtenerCodigoC(ContextoTraduccion ctx) {

        // el marco lo arma el traductor
        return "";

    }

    /**
     * Sacar las lineas de ensamblador RISC-V de la cuarteta.
     */
    @Override
    public String obtenerCodigoRiscv(ContextoRiscV ctx) {

        // el marco lo arma el traductor
        return "";

    }

}
