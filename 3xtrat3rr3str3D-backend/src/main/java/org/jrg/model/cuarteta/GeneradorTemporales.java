package org.jrg.model.cuarteta;

// generador de temporales y etiquetas para las cuartetas
public class GeneradorTemporales {

    private int contadorTemporales;
    private int contadorEtiquetas;

    public GeneradorTemporales() {

        // inicializar los contadores en cero
        contadorTemporales = 0;
        contadorEtiquetas = 0;

    }

    // generar un nuevo temporal
    public String nuevoTemporal() {

        // crear el nombre del temporal con el contador actual
        String temporal = "t" + contadorTemporales;

        // incrementar el contador para la siguiente llamada :D
        contadorTemporales++;

        // devolver el nombre generado
        return temporal;

    }

    // generar una nueva etiqueta
    public String nuevaEtiqueta() {

        // crear el nombre de la etiqueta con el contador actual
        String etiqueta = "L" + contadorEtiquetas;

        // incrementar el contador para la siguiente llamada
        contadorEtiquetas++;

        // devolver la etiqueta generada
        return etiqueta;

    }

}
