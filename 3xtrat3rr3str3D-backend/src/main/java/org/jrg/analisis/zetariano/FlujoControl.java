package org.jrg.analisis.zetariano;

class FlujoControl {

    private boolean puedeContinuar;

    FlujoControl() {
        this.puedeContinuar = true;
    }

    FlujoControl copiar() {
        FlujoControl copia = new FlujoControl();
        copia.puedeContinuar = puedeContinuar;
        return copia;
    }

    boolean puedeContinuar() {
        return puedeContinuar;
    }

    boolean todosRetornan() {
        return !puedeContinuar;
    }

    void marcarRetorno() {
        puedeContinuar = false;
    }

    void combinarCon(FlujoControl otro) {
        if (otro != null && otro.puedeContinuar) {
            puedeContinuar = true;
        }
    }

    void establecerContinuacion(boolean continuar) {
        puedeContinuar = continuar;
    }

    void marcarContinuacion() {
        puedeContinuar = true;
    }
}
