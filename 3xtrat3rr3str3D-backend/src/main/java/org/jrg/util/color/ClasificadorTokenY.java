package org.jrg.util.color;

import org.jrg.antlrBase.yLenguaje.YLenguajeLexer;

public class ClasificadorTokenY {

    public static String clasificar(int tokenType) {
        switch (tokenType) {
            case YLenguajeLexer.ESTRUCTURAS_TAG:
            case YLenguajeLexer.FUNCIONES_TAG:
            case YLenguajeLexer.ESTRUCTURA:
            case YLenguajeLexer.DEFINIR:
            case YLenguajeLexer.RETORNAR:
            case YLenguajeLexer.SI:
            case YLenguajeLexer.ENTONCES:
            case YLenguajeLexer.SINO:
            case YLenguajeLexer.CONTRARIO:
            case YLenguajeLexer.ELEGIR:
            case YLenguajeLexer.CASO:
            case YLenguajeLexer.SIEMPRE:
            case YLenguajeLexer.ROMPER:
            case YLenguajeLexer.CONTINUAR:
            case YLenguajeLexer.PARA:
            case YLenguajeLexer.MIENTRAS:
            case YLenguajeLexer.HACER:
                return "keyword";

            case YLenguajeLexer.TIPO_ENTERO:
            case YLenguajeLexer.TIPO_CADENA:
            case YLenguajeLexer.TIPO_FLOTANTE:
            case YLenguajeLexer.TIPO_CARACTER:
            case YLenguajeLexer.TIPO_BOOLEANO:
                return "type";

            case YLenguajeLexer.VERDADERO:
            case YLenguajeLexer.FALSO:
                return "boolean";

            case YLenguajeLexer.IDENTIFICADOR:
                return "identifier";

            case YLenguajeLexer.NUMERO_ENTERO:
            case YLenguajeLexer.NUMERO_DECIMAL:
                return "number";

            case YLenguajeLexer.CADENA:
                return "string";

            case YLenguajeLexer.CARACTER:
                return "character";

            case YLenguajeLexer.COMENTARIO_LINEA:
                return "comment";

            case YLenguajeLexer.FLECHA:
            case YLenguajeLexer.INCREMENTO:
            case YLenguajeLexer.DECREMENTO:
            case YLenguajeLexer.IGUAL_QUE:
            case YLenguajeLexer.DIFERENTE_QUE:
            case YLenguajeLexer.MAYOR_QUE:
            case YLenguajeLexer.MENOR_QUE:
            case YLenguajeLexer.AND:
            case YLenguajeLexer.OR:
            case YLenguajeLexer.NEGACION:
            case YLenguajeLexer.ASIGNACION:
            case YLenguajeLexer.SUMA:
            case YLenguajeLexer.RESTA:
            case YLenguajeLexer.MULT:
            case YLenguajeLexer.DIV:
                return "operator";

            case YLenguajeLexer.PUNTO_Y_COMA:
            case YLenguajeLexer.DOS_PUNTOS:
            case YLenguajeLexer.COMA:
            case YLenguajeLexer.PUNTO:
                return "delimiter";

            case YLenguajeLexer.CORCHETE_IZQ:
            case YLenguajeLexer.CORCHETE_DER:
            case YLenguajeLexer.LLAVE_IZQ:
            case YLenguajeLexer.LLAVE_DER:
            case YLenguajeLexer.PAR_IZQ:
            case YLenguajeLexer.PAR_DER:
                return "bracket";

            default:
                return "unknown";
        }
    }
}