package org.jrg.service.color;

import org.jrg.antlrBase.pigLatin.PigLatinLexer;
import org.jrg.antlrBase.yLenguaje.YLenguajeLexer;
import org.jrg.antlrBase.zetariano.ZetarianoLexer;
import org.jrg.util.color.ClasificadorTokenPigLatin;
import org.jrg.util.color.ClasificadorTokenY;
import org.jrg.util.color.ClasificadorTokenZetariano;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ColorService {

    public static List<TokenColor> obtenerColoresPigLatin(String codigo) {
        // crear lista para tokens coloreados
        List<TokenColor> colores = new ArrayList<>();
        // crear lexer piglatin
        PigLatinLexer lexer = new PigLatinLexer(CharStreams.fromString(codigo));
        // crear stream de tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        // recorrer cada token
        for (Token token : tokens.getTokens()) {
            // omitir token EOF
            if (token.getType() == Token.EOF) {
                continue;
            }
            // clasificar token
            String categoria = ClasificadorTokenPigLatin.clasificar(token.getType());
            // filtrar unknown y comment
            if (!"unknown".equals(categoria) && !"comment".equals(categoria)) {
                // calcular longitud
                int longitud = token.getStopIndex() - token.getStartIndex() + 1;
                // agregar token color
                colores.add(new TokenColor(token.getStartIndex(), longitud, categoria));
            }
        }
        return colores;
    }

    public static List<TokenColor> obtenerColoresZetariano(String codigo) {
        // crear lista para tokens coloreados
        List<TokenColor> colores = new ArrayList<>();
        // crear lexer zetariano
        ZetarianoLexer lexer = new ZetarianoLexer(CharStreams.fromString(codigo));
        // crear stream de tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        // recorrer cada token
        for (Token token : tokens.getTokens()) {
            // omitir token EOF
            if (token.getType() == Token.EOF) {
                continue;
            }
            // clasificar token
            String categoria = ClasificadorTokenZetariano.clasificar(token.getType());
            // filtrar unknown y comment
            if (!"unknown".equals(categoria) && !"comment".equals(categoria)) {
                // calcular longitud
                int longitud = token.getStopIndex() - token.getStartIndex() + 1;
                // agregar token color
                colores.add(new TokenColor(token.getStartIndex(), longitud, categoria));
            }
        }
        return colores;
    }

    public static List<TokenColor> obtenerColoresY(String codigo) {
        // crear lista para tokens coloreados
        List<TokenColor> colores = new ArrayList<>();
        // crear lexer de Y
        YLenguajeLexer lexer = new YLenguajeLexer(CharStreams.fromString(codigo));
        // crear stream de tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        // recorrer cada token
        for (Token token : tokens.getTokens()) {
            // omitir token EOF
            if (token.getType() == Token.EOF) {
                continue;
            }
            // clasificar token
            String categoria = ClasificadorTokenY.clasificar(token.getType());
            // filtrar unknown y comment
            if (!"unknown".equals(categoria) && !"comment".equals(categoria)) {
                // calcular longitud
                int longitud = token.getStopIndex() - token.getStartIndex() + 1;
                // agregar token color
                colores.add(new TokenColor(token.getStartIndex(), longitud, categoria));
            }
        }
        return colores;
    }
}