package org.jrg.util.color;

import org.jrg.antlrBase.pigLatin.PigLatinLexer;

public class ClasificadorTokenPigLatin {

    public static String clasificar(int tokenType) {
        switch (tokenType) {
            case PigLatinLexer.IMPORT:
            case PigLatinLexer.VARIABILES:
            case PigLatinLexer.MAIOR:
            case PigLatinLexer.FINIS:
            case PigLatinLexer.ESTO:
            case PigLatinLexer.SERIES:
            case PigLatinLexer.NOVUS:
            case PigLatinLexer.SI:
            case PigLatinLexer.ALITER:
            case PigLatinLexer.DUM:
            case PigLatinLexer.FACERE:
            case PigLatinLexer.PER:
            case PigLatinLexer.INTERRUMPE:
            case PigLatinLexer.PERGE:
            case PigLatinLexer.FINIS_BLOQUE:
            case PigLatinLexer.NON:
            case PigLatinLexer.LEER:
            case PigLatinLexer.IMPRIMIR:
                return "keyword";

            case PigLatinLexer.NUMERUS:
            case PigLatinLexer.TEXTUM:
            case PigLatinLexer.DECIMALIS:
            case PigLatinLexer.LITTERA:
            case PigLatinLexer.BOOL:
                return "type";

            case PigLatinLexer.VERUM:
            case PigLatinLexer.FALSUS:
                return "boolean";

            case PigLatinLexer.IDENTIFICADOR:
                return "identifier";

            case PigLatinLexer.ENTERO:
            case PigLatinLexer.DECIMAL:
                return "number";

            case PigLatinLexer.CADENA:
                return "string";

            case PigLatinLexer.CARACTER:
                return "character";

            case PigLatinLexer.COMENTARIO_LINEA:
            case PigLatinLexer.COMENTARIO_BLOQUE:
                return "comment";

            case PigLatinLexer.ASIGNACION:
            case PigLatinLexer.SUMA:
            case PigLatinLexer.RESTA:
            case PigLatinLexer.MULT:
            case PigLatinLexer.DIV:
            case PigLatinLexer.IGUAL_QUE:
            case PigLatinLexer.DIFERENTE_QUE:
            case PigLatinLexer.MAYOR_QUE:
            case PigLatinLexer.MENOR_QUE:
            case PigLatinLexer.MAYOR_IGUAL_QUE:
            case PigLatinLexer.MENOR_IGUAL_QUE:
            case PigLatinLexer.AND:
            case PigLatinLexer.OR:
            case PigLatinLexer.INCREMENTO:
            case PigLatinLexer.DECREMENTO:
                return "operator";

            case PigLatinLexer.PUNTO_Y_COMA:
            case PigLatinLexer.DOS_PUNTOS:
            case PigLatinLexer.COMA:
            case PigLatinLexer.PUNTO:
                return "delimiter";

            case PigLatinLexer.CORCHETE_IZQ:
            case PigLatinLexer.CORCHETE_DER:
            case PigLatinLexer.LLAVE_IZQ:
            case PigLatinLexer.LLAVE_DER:
            case PigLatinLexer.PAR_IZQ:
            case PigLatinLexer.PAR_DER:
                return "bracket";

            default:
                return "unknown";
        }
    }
}