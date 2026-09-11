package org.jrg.controller.colores;

import org.jrg.service.colores.ColoresService;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColoresController {

    private final ColoresService coloresService;

    /**
     * Inicializar el controlador con el servicio de colores.
     */
    public ColoresController(ColoresService coloresService) {
        this.coloresService = coloresService;
    }

    /**
     * Manejar la peticion de colores devolviendo la lista de tokens.
     */
    public void obtenerColores(Context ctx) {
        try {
            // obtener el codigo recibido en el cuerpo de la peticion
            String codigo = ctx.body();

            // obtener el lenguaje desde query param o header, por defecto piglatin
            String lenguaje = ctx.queryParam("lenguaje");
            if (lenguaje == null) {
                lenguaje = ctx.header("X-Lenguaje");
            }
            if (lenguaje == null) {
                lenguaje = "piglatin";
            }

            // invocar el servicio para obtener la lista de colores
            List<Object> colores = coloresService.obtenerColores(codigo, lenguaje);

            // construir la respuesta exitosa con la clave colores
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", true);
            respuesta.put("colores", colores);

            ctx.status(200).json(respuesta);

        } catch (Exception e) {
            System.err.println("error al obtener colores: " + e.getMessage());

            // construir la respuesta de error
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", false);
            respuesta.put("mensaje", e.getMessage());

            ctx.status(500).json(respuesta);
        }
    }
}