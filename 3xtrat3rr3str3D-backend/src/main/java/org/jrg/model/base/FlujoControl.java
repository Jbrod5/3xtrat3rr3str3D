package org.jrg.model.base;


public class FlujoControl {

    private boolean puedeContinuar;

    /**
     * Crear un flujo de control que inicialmente puede continuar.
     */
    public FlujoControl() {
        this.puedeContinuar = true;
    }

    /**
     * Crear una copia del flujo de control actual.
     */
    public FlujoControl copiar() {
        FlujoControl copia = new FlujoControl();
        copia.puedeContinuar = this.puedeContinuar;
        return copia;
    }

    /**
     * Verificar si el flujo puede continuar tras la ultima instruccion.
     */
    public boolean puedeContinuar() {
        return this.puedeContinuar;
    }

    /**
     * Verificar si todos los caminos retornan en el bloque actual.
     */
    public boolean todosRetornan() {
        return !this.puedeContinuar;
    }

    /**
     * Marcar el flujo como terminado por un return.
     */
    public void marcarRetorno() {
        this.puedeContinuar = false;
    }

    /**
     * Combinar el flujo actual con otro flujo alternativo.
     */
    public void combinarCon(FlujoControl otro) {
        if (otro != null && otro.puedeContinuar) {
            this.puedeContinuar = true;
        }
    }

    /**
     * Establecer el estado de continuacion del flujo.
     */
    public void establecerContinuacion(boolean continuar) {
        this.puedeContinuar = continuar;
    }

    /**
     * Marcar el flujo como continuable.
     */
    public void marcarContinuacion() {
        this.puedeContinuar = true;
    }
}