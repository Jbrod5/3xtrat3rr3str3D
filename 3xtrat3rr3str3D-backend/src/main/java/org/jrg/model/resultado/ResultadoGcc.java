package org.jrg.model.resultado;

// representar el resultado de compilar el codigo C con gcc
public class ResultadoGcc {

    private final boolean compilo;
    private final String salida;
    private final String comando;
    private final String rutaBinario;

    // crear el resultado normalizando nulos a vacio :D
    public ResultadoGcc(boolean compilo, String salida, String comando, String rutaBinario) {
        this.compilo = compilo;
        // normalizar salida nula
        if (salida == null) {
            this.salida = "";
        } else {
            this.salida = salida;
        }
        // normalizar comando nulo
        if (comando == null) {
            this.comando = "";
        } else {
            this.comando = comando;
        }
        // normalizar ruta nula
        if (rutaBinario == null) {
            this.rutaBinario = "";
        } else {
            this.rutaBinario = rutaBinario;
        }
    }

    /**
     * Indicar si gcc compilo sin errores.
     */
    public boolean isCompilo() {
        return compilo;
    }

    /**
     * Obtener la salida capturada de gcc.
     */
    public String getSalida() {
        return salida;
    }

    /**
     * Obtener el comando gcc ejecutado.
     */
    public String getComando() {
        return comando;
    }

    /**
     * Obtener la ruta del binario generado o vacio si fallo.
     */
    public String getRutaBinario() {
        return rutaBinario;
    }
}
