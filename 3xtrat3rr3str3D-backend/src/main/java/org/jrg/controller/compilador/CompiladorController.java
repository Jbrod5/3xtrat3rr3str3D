package org.jrg.controller.compilador;

import java.util.HashMap;
import java.util.Map;

import org.jrg.model.resultado.ResultadoAnalisis;
import org.jrg.service.compiler.CompiladorPigLatinService;
import org.jrg.service.compiler.CompiladorYLenguajeService;
import org.jrg.service.compiler.CompiladorZetarianoService;

import io.javalin.http.Context;

public class CompiladorController {

    private final CompiladorPigLatinService compiladorPigLatin;
    private final CompiladorZetarianoService compiladorZetariano;
    private final CompiladorYLenguajeService compiladorY;



    public CompiladorController(CompiladorPigLatinService pigLatin, CompiladorZetarianoService zetariano, CompiladorYLenguajeService compiladorY) {
        this.compiladorPigLatin = pigLatin;
        this.compiladorZetariano = zetariano;
        this.compiladorY = compiladorY;
    }

    /**
     * Compilar codigo recibido como texto plano eligiendo el lenguaje por query param.
     */
    public void analizar(Context ctx) {
        try {
            String codigo = ctx.body();
            String lenguaje = ctx.queryParam("lenguaje");
            String ruta = ctx.queryParam("ruta");

            if (lenguaje == null || lenguaje.isEmpty()) {
                lenguaje = ctx.header("X-Lenguaje");
            }

            if (lenguaje == null || lenguaje.isEmpty()) {
                lenguaje = "piglatin";
            }

            ResultadoAnalisis resultado = ejecutarCompilacion(lenguaje, codigo, ruta);
            ctx.status(200).json(resultado);

        } catch (RuntimeException e) {
            ctx.status(500).json(construirError(e));
        }
    }

    /**
     * Traducir codigo recibido como texto plano a un lenguaje destino.
     */
    public void traducir(Context ctx) {
        try {
            String codigo = ctx.body();
            String lenguaje = ctx.queryParam("lenguaje");
            String ruta = ctx.queryParam("ruta");

            if (lenguaje == null || lenguaje.isEmpty()) {
                lenguaje = "piglatin";
            }

            ResultadoAnalisis resultado = ejecutarCompilacion(lenguaje, codigo, ruta);
            ctx.status(200).json(resultado);

        } catch (RuntimeException e) {
            ctx.status(500).json(construirError(e));
        }
    }

    /**
     * Compilar codigo recibido como JSON con los campos codigo y lenguaje.
     */
    public void compilar(Context ctx) {
        try {
            Map<String, String> cuerpo = ctx.bodyAsClass(Map.class);

            String codigo = cuerpo.get("codigo");
            String lenguaje = cuerpo.get("lenguaje");
            String ruta = cuerpo.get("ruta");

            if (lenguaje == null || lenguaje.isEmpty()) {
                lenguaje = "piglatin";
            }

            ResultadoAnalisis resultado = ejecutarCompilacion(lenguaje, codigo, ruta);
            ctx.status(200).json(resultado);

        } catch (RuntimeException e) {
            ctx.status(500).json(construirError(e));
        }
    }


    // ejecutar la compilacion delegando al servicio correspondiente
    private ResultadoAnalisis ejecutarCompilacion(String lenguaje, String codigo, String ruta) {
        if ("zetariano".equalsIgnoreCase(lenguaje) || "zet".equalsIgnoreCase(lenguaje)) {
            return this.compiladorZetariano.analizar(codigo);
        }

        if ("y".equalsIgnoreCase(lenguaje) || "ylenguaje".equalsIgnoreCase(lenguaje)) {
            return this.compiladorY.analizar(codigo);
        }

        // pig latin recibe la ruta base para resolver imports
        return this.compiladorPigLatin.analizar(codigo, ruta);
    }

    // construir una respuesta de error uniforme
    private Map<String, Object> construirError(RuntimeException e) {
        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("exito", false);
        respuesta.put("mensaje", e.getMessage());

        return respuesta;
    }
}