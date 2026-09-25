grammar YLenguaje;

tokens { INDENT, DEDENT }

// ==============================================================================================================================
// - - - - - - - - - - - - - - - - - - - - - - - -  REGLAS SINTACTICAS (Parser) - - - - - - - - - - - - - - - - - - - - - - - - -
// ==============================================================================================================================



// ==========================================
// ESTRUCTURA PRINCIPAL
// ==========================================

programa
    : seccion_estructuras?
      seccion_funciones
      EOF
    ;

seccion_estructuras
    // permitir lineas en blanco entre el tag y las definiciones
    // seccion_estructuras
    //     : ESTRUCTURAS_TAG definicion_struct*
    //     ;
    : ESTRUCTURAS_TAG (definicion_struct | NEWLINE)*
    ;

seccion_funciones
    // permitir lineas en blanco entre el tag y las definiciones
    // seccion_funciones
    //     : FUNCIONES_TAG definicion_funcion*
    //     ;
    : FUNCIONES_TAG (definicion_funcion | NEWLINE)*
    ;




// ==========================================
// TIPOS DE DATOS
// ==========================================

tipo_dato
    : TIPO_ENTERO
    | TIPO_CADENA
    | TIPO_FLOTANTE
    | TIPO_CARACTER
    | TIPO_BOOLEANO
    | IDENTIFICADOR // para referirse a una estructura definida por el usuario
    ;


// ==========================================
// DEFINICION DE ESTRUCTURAS
// ==========================================

definicion_struct
    // estructura MiEstructura:
    //     cadena nombre
    // estructura Persona:
    //     entero edad
    //     cadena nombre
    //     flotante promedio
    //     caracter letra
    //     entero miArray[10]
    //     MiEstructura miEstructura
    : ESTRUCTURA IDENTIFICADOR DOS_PUNTOS NEWLINE INDENT atributo_struct+ DEDENT     # defEstructura
    ;

atributo_struct
    // entero edad
    // cadena nombre
    // MiEstructura miEstructura
    // tipo_dato tambien acepta el nombre de otra estructura, asi se anidan estructuras
    : tipo_dato IDENTIFICADOR NEWLINE                                             # atributoSimple
    // entero miArray[10]
    // el tamano del arreglo debe ser una constante entera
    | tipo_dato IDENTIFICADOR CORCHETE_IZQ NUMERO_ENTERO CORCHETE_DER NEWLINE      # atributoArray
    ;


// ==========================================
// DEFINICION DE FUNCIONES
// ==========================================

definicion_funcion
    // definir funcionSinRetorno(entero miEntero):
    //     miEntero = 90 * 10
    : DEFINIR IDENTIFICADOR PAR_IZQ parametros? PAR_DER DOS_PUNTOS cuerpo_funcion                                   # defFuncionSinRetorno
    // definir funcionConRetorno(entero miEntero) -> entero :
    //     miEntero = 10 + 10
    //     retornar 160
    | DEFINIR IDENTIFICADOR PAR_IZQ parametros? PAR_DER FLECHA tipo_dato DOS_PUNTOS cuerpo_funcion                  # defFuncionConRetorno
    ;

parametros
    // entero miEntero, cadena nombre
    : parametro (COMA parametro)*
    ;

parametro
    // entero miEntero
    : tipo_dato IDENTIFICADOR                                              # paramSimple
    // [] entero miArray
    // los arreglos siempre se pasan por referencia
    | CORCHETE_IZQ CORCHETE_DER tipo_dato IDENTIFICADOR                    # paramArray
    // {} MiEstructura miEstructura
    // las estructuras siempre se pasan por referencia
    | LLAVE_IZQ LLAVE_DER IDENTIFICADOR IDENTIFICADOR                      # paramEstructura
    ;

cuerpo_funcion
    // cuerpo indentado que contiene una o mas instrucciones
    : NEWLINE INDENT instruccion+ NEWLINE? DEDENT
    ;


// ==========================================
// INSTRUCCIONES
// ==========================================

instruccion
    : declaracion_variable terminador                # stmtDeclaracion
    | asignacion terminador                          # stmtAsignacion
    | definicion_struct                              # stmtEstructuraLocal
    | condicional                                    # stmtCondicional
    | seleccion                                      # stmtSeleccion
    | ciclo                                          # stmtCiclo
    | retorno terminador                             # stmtRetorno
    | CONTINUAR terminador                           # stmtContinuar
    | ROMPER terminador                              # stmtRomper
    | expresion terminador                           # stmtExpresion
    ;


// terminador
//     : PUNTO_Y_COMA? NEWLINE
//     ;
terminador
    : NEWLINE
    ;

retorno
    // retornar 160
    : RETORNAR expresion
    ;


// ==========================================
// DECLARACION DE VARIABLES
// ==========================================

declaracion_variable
    // entero sinInicializacion
    // entero edadUsuario = 25
    // flotante temperatura = 36.6
    // caracter inicial = 'A'
    // Persona alumno1
    // Punto p1 = {10, 20, 85.5}
    : tipo_dato IDENTIFICADOR (ASIGNACION expresion)?                                                          # declConTipoYValor
    // entero numeros[10]
    | tipo_dato IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER                                              # declArraySinValores
    // entero numeros[5] = {10, 20, 30, 40, 50}
    | tipo_dato IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER ASIGNACION LLAVE_IZQ lista_expresiones LLAVE_DER    # declArrayConValores
    // entero matriz[3][3]
    | tipo_dato IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER CORCHETE_IZQ expresion CORCHETE_DER          # declMatriz
    ;


// ==========================================
// ASIGNACION
// ==========================================

asignacion
    // x = 3
    // numeros[2] = numeros[0] * 3
    // alumno1.nombre = "Yennifer"
    // p3 = p1
    : variable_asignable ASIGNACION expresion
    ;

variable_asignable
    // x
    : IDENTIFICADOR                                                       # varSimple
    // numeros[2]
    | variable_asignable CORCHETE_IZQ expresion CORCHETE_DER              # varArray
    // alumno1.nombre
    | variable_asignable PUNTO IDENTIFICADOR                              # varMiembro
    ;


// ==========================================
// CONDICIONALES
// ==========================================

condicional
    // si(edad > 18) entonces
    //     imprimir("Codigo si es mayor de edad")
    // sino (edad == 18) entonces
    //     imprimir("Codigo si tiene exactamente 18")
    // contrario
    //     imprimir("Codigo si es menor de edad")
    // tanto "sino" como "contrario" son opcionales, "sino" puede repetirse varias veces
    : SI PAR_IZQ expresion PAR_DER ENTONCES bloque
      (SINO PAR_IZQ expresion PAR_DER ENTONCES bloque)*
      (CONTRARIO bloque)?                                                 # statementSi
    ;

bloque
    // cuerpo indentado con una o mas instrucciones
    : NEWLINE INDENT instruccion+ NEWLINE? DEDENT
    // una sola instruccion en la misma linea, sin indentacion nueva
    | instruccion
    ;

seleccion
    // elegir(opcion) {
    //     caso 1:
    //         x = 10
    //         romper
    //     caso 2:
    //         x = 20
    //         romper
    //     siempre:
    //         x = 30
    //         romper
    // }
    // a diferencia de si/sino/contrario, elegir siempre usa llaves
    :  ELEGIR PAR_IZQ expresion PAR_DER DOS_PUNTOS NEWLINE INDENT caso_seleccion+ caso_defecto? DEDENT   # statementElegir
    ;

caso_seleccion
    // caso 1:
    //     x = 10
    //     romper
    : CASO valor_primitivo DOS_PUNTOS NEWLINE INDENT instruccion+ DEDENT
    ;

caso_defecto
    // siempre:
    //     x = 30
    //     romper
    : SIEMPRE DOS_PUNTOS NEWLINE INDENT instruccion+ DEDENT
    ;


// ==========================================
// CICLOS
// ==========================================

ciclo
    // para(entero i = 0; i < 10; i++):
    //     si(i == 3) entonces
    //         continuar
    //     si(i == 8) entonces
    //         romper
    : PARA PAR_IZQ init_para PUNTO_Y_COMA expresion PUNTO_Y_COMA paso_para PAR_DER DOS_PUNTOS bloque    # cicloPara
    // mientras(contador < 5) hacer
    //     contador++
    //     si(contador == 2) entonces
    //         continuar
    | MIENTRAS PAR_IZQ expresion PAR_DER HACER bloque                                         # cicloMientras
    // hacer:
    //     intentos++
    //     si(intentos == 4) entonces
    //         romper
    // mientras(intentos < 10)
    | HACER DOS_PUNTOS bloque MIENTRAS PAR_IZQ expresion PAR_DER                                         # cicloHacer
    ;

init_para
    // entero i = 0
    : tipo_dato IDENTIFICADOR ASIGNACION expresion            # initParaDecl
    // i = 0
    | variable_asignable ASIGNACION expresion                 # initParaAsig
    ;

paso_para
    // i++
    : expresion                                               # pasoParaExpr
    // i = i + 1
    | variable_asignable ASIGNACION expresion                 # pasoParaAsig
    ;


// ==========================================
// EXPRESIONES Y VALORES
// ==========================================

expresion
    : PAR_IZQ expresion PAR_DER                                              # exprParentesis

    // Llamada a funcion, incluye las funciones especiales imprimir() y leer()
    // imprimir("Imprimir")
    // leer()
    | IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER                       # exprLlamadaFuncion

    // numeros[0]
    | expresion CORCHETE_IZQ expresion CORCHETE_DER                          # exprAccesoArray

    // alumno1.nombre
    | expresion PUNTO IDENTIFICADOR                                          # exprAccesoMiembro

    // edad++
    | variable_asignable INCREMENTO                                         # exprPostIncremento
    // edad--
    | variable_asignable DECREMENTO                                         # exprPostDecremento

    // {10, 20, 30, 40, 50}
    // lista de valores usada para inicializar arreglos y estructuras
    | LLAVE_IZQ lista_expresiones LLAVE_DER                                  # exprListaLiteral

    // -3
    | RESTA expresion                                                       # exprNegativa
    // !valorVerdadero
    | NEGACION expresion                                                    # exprNegada

    // numeros[0] * 3
    | expresion (MULT | DIV) expresion                                      # exprMultiplicacionDivision
    // a + b
    | expresion (SUMA | RESTA) expresion                                    # exprSumaResta

    // edad > 18
    // edad <= 18
    // existen seis operadores relacionales en este lenguaje
    | expresion (IGUAL_QUE | DIFERENTE_QUE | MAYOR_QUE | MENOR_QUE | MAYOR_IGUAL | MENOR_IGUAL) expresion   # exprRelacional

    // a > 5 && b < 10
    | expresion AND expresion                                                # exprAnd
    // a > 20 || b > 0
    | expresion OR expresion                                                 # exprOr

    | valor_primitivo                                                       # exprPrimitivo
    ;

valor_primitivo
    : NUMERO_ENTERO
    | NUMERO_DECIMAL
    | CADENA
    | CARACTER
    | VERDADERO
    | FALSO
    | IDENTIFICADOR
    ;

lista_expresiones
    // "Hola", "Adios"
    : expresion (COMA expresion)*
    ;


// ==============================================================================================================================
// - - - - - - - - - - - - - - - - - - - - - - - - - - REGLAS LEXICAS (Lexer)- - - - - - - - - - - - - - - - - - - - - - - - - -
// ==============================================================================================================================


// ==========================================
// SECCIONES DE CODIGO
// ==========================================
ESTRUCTURAS_TAG : '%estructuras' ;
FUNCIONES_TAG    : '%funciones' ;


// ==========================================
// TIPOS DE DATOS
// ==========================================
TIPO_ENTERO    : 'entero' ;
TIPO_CADENA    : 'cadena' ;
TIPO_FLOTANTE  : 'flotante' ;
TIPO_CARACTER  : 'caracter' ;
TIPO_BOOLEANO  : 'booleano' ;


// ==========================================
// VALORES BOOLEANOS
// ==========================================
VERDADERO : 'verdadero' ;
FALSO     : 'falso' ;


// ==========================================
// DECLARACION Y ESTRUCTURAS
// ==========================================
ESTRUCTURA : 'estructura' ;
DEFINIR    : 'definir' ;
RETORNAR   : 'retornar' ;


// ==========================================
// IF Y CICLOS
// ==========================================
SI        : 'si' ;
ENTONCES  : 'entonces' ;
SINO      : 'sino' ;
CONTRARIO : 'contrario' ;
ELEGIR    : 'elegir' ;
CASO      : 'caso' ;
SIEMPRE   : 'siempre' ;
ROMPER    : 'romper' ;
CONTINUAR : 'continuar' ;
PARA      : 'para' ;
MIENTRAS  : 'mientras' ;
HACER     : 'hacer' ;


// ==========================================
// OPERADORES LOGICOS Y SIMBOLOS
// ==========================================
FLECHA         : '->' ;
INCREMENTO     : '++' ;
DECREMENTO     : '--' ;

IGUAL_QUE      : '==' ;
DIFERENTE_QUE  : '!=' ;
MAYOR_QUE      : '>' ;
MENOR_QUE      : '<' ;
MAYOR_IGUAL    : '>=' ;
MENOR_IGUAL    : '<=' ;

AND : '&&' ;
OR  : '||' ;
NEGACION : '!' ;

ASIGNACION : '=' ;
SUMA       : '+' ;
RESTA      : '-' ;
MULT       : '*' ;
DIV        : '/' ;

CORCHETE_IZQ : '[' ;
CORCHETE_DER : ']' ;
LLAVE_IZQ    : '{' ;
LLAVE_DER    : '}' ;
PAR_IZQ      : '(' ;
PAR_DER      : ')' ;

PUNTO_Y_COMA : ';' ;
DOS_PUNTOS   : ':' ;
COMA         : ',' ;
PUNTO        : '.' ;


// ==========================================
// LITERALES E IDENTIFICADORES
// ==========================================
CADENA
    : '"' .*? '"'
    ;

CARACTER
    : '\'' . '\''
    ;

NUMERO_DECIMAL
    : [0-9]+ '.' [0-9]+
    ;

NUMERO_ENTERO
    : [0-9]+
    ;

IDENTIFICADOR
    : [a-zA-Z_] [a-zA-Z0-9_]*
    ;


// ==========================================
// COMENTARIOS Y ESPACIOS
// ==========================================
COMENTARIO_LINEA
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;

// NEWLINE marca el final de cada instruccion y alimenta el calculo de
// INDENT y DEDENT en la capa previa al lexer
NEWLINE
    : ('\r'? '\n')+
    ;

// solo se descartan los espacios horizontales, los saltos de linea
// se conservan porque son significativos para la indentacion
ESPACIOS_BLANCO
    : [ \t]+ -> skip
    ;

// ==========================================
// TOKENS VIRTUALES
// ==========================================
// Para inicio y fin de indentacion :3
INDENT : 'INDENT' { false }? ;
DEDENT : 'DEDENT' { false }? ;