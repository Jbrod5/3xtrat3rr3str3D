package org.jrg.service.colores;

import org.jrg.service.color.ColorService;
import org.jrg.service.color.TokenColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ColoresService {

    /**
     * Obtener la lista de tokens coloreados a partir del codigo recibido.
     */
    public List<Object> obtenerColores(String codigo, String lenguaje) {
        List<TokenColor> coloresToken;

        if ("piglatin".equalsIgnoreCase(lenguaje) || "lat".equalsIgnoreCase(lenguaje)) {
            coloresToken = ColorService.obtenerColoresPigLatin(codigo);
        } else if ("zetariano".equalsIgnoreCase(lenguaje) || "zet".equalsIgnoreCase(lenguaje)) {
            coloresToken = ColorService.obtenerColoresZetariano(codigo);
        } else {
            // por defecto intentar piglatin
            coloresToken = ColorService.obtenerColoresPigLatin(codigo);
        }

        List<Object> colores = new ArrayList<>();
        for (TokenColor tc : coloresToken) {
            Map<String, Object> tokenInfo = new java.util.HashMap<>();
            tokenInfo.put("start", tc.start);
            tokenInfo.put("length", tc.length);
            tokenInfo.put("category", tc.category);
            colores.add(tokenInfo);
        }

        return colores;
    }

    // compatibilidad con version anterior sin parametro lenguaje
    public List<Object> obtenerColores(String codigo) {
        return obtenerColores(codigo, "piglatin");
    }
}