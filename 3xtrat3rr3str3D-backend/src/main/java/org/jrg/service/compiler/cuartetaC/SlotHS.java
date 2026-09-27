package org.jrg.service.compiler.cuartetaC;

// marca de posicion de una variable en la pila con su arreglo
public class SlotHS {

    private final int indice;  // indice relativo a framepointer donde vive el valor

    private final String arreglo;  // arreglo tipado donde vive el valor

    /**
     * Crear un slot con indice y arreglo.
     */
    public SlotHS(int indice, String arreglo) {

        // guardar los valores recibidos
        this.indice = indice;
        this.arreglo = arreglo;

    }

    /**
     * Obtener el indice relativo a framepointer.
     */
    public int getIndice() {
        return indice;
    }

    /**
     * Obtener el arreglo tipado del slot.
     */
    public String getArreglo() {
        return arreglo;
    }

}
