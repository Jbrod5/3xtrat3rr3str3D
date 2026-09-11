package org.jrg.util.color;

import org.jrg.antlrBase.zetariano.ZetarianoLexer;

public class ClasificadorTokenZetariano {

    public static String clasificar(int tokenType) {
        switch (tokenType) {
            case ZetarianoLexer.PUBLIC:
            case ZetarianoLexer.CLASS:
            case ZetarianoLexer.VOID:
            case ZetarianoLexer.NEW:
            case ZetarianoLexer.IF:
            case ZetarianoLexer.ELSE:
            case ZetarianoLexer.SWITCH:
            case ZetarianoLexer.CASE:
            case ZetarianoLexer.DEFAULT:
            case ZetarianoLexer.FOR:
            case ZetarianoLexer.WHILE:
            case ZetarianoLexer.DO:
            case ZetarianoLexer.BREAK:
            case ZetarianoLexer.CONTINUE:
            case ZetarianoLexer.RETURN:
                return "keyword";

            case ZetarianoLexer.INT:
            case ZetarianoLexer.DOUBLE:
            case ZetarianoLexer.CHAR:
            case ZetarianoLexer.BOOLEAN:
            case ZetarianoLexer.STRING:
                return "type";

            case ZetarianoLexer.TRUE:
            case ZetarianoLexer.FALSE:
                return "boolean";

            case ZetarianoLexer.NULL:
                return "null";

            case ZetarianoLexer.IDENTIFICADOR:
                return "identifier";

            case ZetarianoLexer.NUMERO_ENTERO:
            case ZetarianoLexer.NUMERO_DECIMAL:
                return "number";

            case ZetarianoLexer.CADENA:
                return "string";

            case ZetarianoLexer.CARACTER:
                return "character";

            case ZetarianoLexer.COMENTARIO_LINEA:
            case ZetarianoLexer.COMENTARIO_BLOQUE:
                return "comment";

            case ZetarianoLexer.ASIGNACION:
            case ZetarianoLexer.SUMA:
            case ZetarianoLexer.RESTA:
            case ZetarianoLexer.MULT:
            case ZetarianoLexer.DIV:
            case ZetarianoLexer.MOD:
            case ZetarianoLexer.IGUAL_QUE:
            case ZetarianoLexer.DIFERENTE_QUE:
            case ZetarianoLexer.MAYOR_QUE:
            case ZetarianoLexer.MENOR_QUE:
            case ZetarianoLexer.MAYOR_IGUAL_QUE:
            case ZetarianoLexer.MENOR_IGUAL_QUE:
            case ZetarianoLexer.AND:
            case ZetarianoLexer.OR:
            case ZetarianoLexer.NEGACION:
            case ZetarianoLexer.MAS_IGUAL:
            case ZetarianoLexer.MENOS_IGUAL:
            case ZetarianoLexer.MULT_IGUAL:
            case ZetarianoLexer.INCREMENTO:
            case ZetarianoLexer.DECREMENTO:
            case ZetarianoLexer.INTERROGACION:
                return "operator";

            case ZetarianoLexer.PUNTO_Y_COMA:
            case ZetarianoLexer.DOS_PUNTOS:
            case ZetarianoLexer.COMA:
            case ZetarianoLexer.PUNTO:
                return "delimiter";

            case ZetarianoLexer.CORCHETE_IZQ:
            case ZetarianoLexer.CORCHETE_DER:
            case ZetarianoLexer.LLAVE_IZQ:
            case ZetarianoLexer.LLAVE_DER:
            case ZetarianoLexer.PAR_IZQ:
            case ZetarianoLexer.PAR_DER:
                return "bracket";

            default:
                return "unknown";
        }
    }
}