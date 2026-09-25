grammar PigLatin;

// ==============================================================================================================================
// - - - - - - - - - - - - - - - - - - - - - - - -  REGLAS SINTACTICAS (Parser) - - - - - - - - - - - - - - - - - - - - - - - - -
// ==============================================================================================================================



// ==========================================
// ESTRUCTURA PRINCIPAL
// ==========================================

programa
    : seccion_importaciones?
      seccion_global_variables?
      seccion_maior
      EOF
    ;

seccion_importaciones
    // import carpeta.Objeto1.z
    // import carpeta.Funciones.y
    : (IMPORT ruta_importacion)*
    ;

ruta_importacion
    // carpeta.Objeto1.z
    // carpeta.Funciones.y
    : IDENTIFICADOR (PUNTO IDENTIFICADOR)*
    ;

seccion_global_variables
    // VARIABILES >
    //     esto edad : numerus 20;
    //     esto cifrado : falsus;
    // la seccion completa es opcional
    : VARIABILES MAYOR_QUE declaracion_variable*
    ;

seccion_maior
    // MAIOR >         ...                FINIS  ;
    : MAIOR MAYOR_QUE instruccion_flujo* FINIS PUNTO_Y_COMA
    ;


// ==========================================
// TIPOS DE DATOS
// ==========================================

tipo_dato
    : NUMERUS
    | TEXTUM
    | DECIMALIS
    | LITTERA
    | BOOL
    | IDENTIFICADOR // para referirse a una estructura o clase importada
    ;


// ==========================================
// VALORES ASIGNABLES
// ==========================================

variable_asignable
    // x
    : IDENTIFICADOR                                             # valorAsignableSimple
    // array[3]
    | variable_asignable CORCHETE_IZQ expresion CORCHETE_DER    # valorAsignableArray
    // estudiante.nombre
    | variable_asignable PUNTO IDENTIFICADOR                    # valorAsignableMiembroEstructura
    ;


// ==========================================
// EXPRESIONES Y VALORES
// ==========================================

expresion
    : PAR_IZQ expresion PAR_DER                                    # exprParentesis

    // novus Persona(12, "Profesor")
    // crea un objeto nuevo a partir de una clase importada de un archivo .z
    | NOVUS IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER       # exprInstanciaObjeto

    // calcularPoder(fuerza)
    | IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER             # exprLlamadaFuncion

    // miObjeto.getNombre()
    // misObjetos[9].hablar(miObjeto.getNombre())
    | expresion PUNTO IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER    # exprLlamadaMetodo

    // array[3]
    | expresion CORCHETE_IZQ expresion CORCHETE_DER                # exprAccesoPosicionArray

    // miObjeto.apellidos
    | expresion PUNTO IDENTIFICADOR                                # exprAccesoMiembroEstructura

    // edad++
    | variable_asignable INCREMENTO                                # exprPostIncremento
    // edad--
    | variable_asignable DECREMENTO                                # exprPostDecremento

    // {"Valeria", 25, {"Avenida Central", 500}}
    // lista de valores usada para inicializar estructuras y objetos
    | LLAVE_IZQ lista_expresiones LLAVE_DER                        # exprListaLiteral

    // -3
    | RESTA expresion                                              # exprNegativa
    // non valor_verdadero
    | NON expresion                                                # exprNegada
    // ++edad
    | INCREMENTO variable_asignable                                # exprPreIncremento
    // --edad
    | DECREMENTO variable_asignable                                # exprPreDecremento

    // fuerza * 2
    | expresion ( MULT | DIV )    expresion                        # exprMultiplicacionDivision
    // 17 / 2
    | expresion ( SUMA | RESTA )  expresion                        # exprSumaResta

    // 1 = 1
    | expresion ( IGUAL_QUE       | DIFERENTE_QUE
                | MAYOR_QUE       | MENOR_QUE
                | MAYOR_IGUAL_QUE | MENOR_IGUAL_QUE ) expresion    # exprRelacional

    // verum || 1 = 1
    | expresion AND expresion                                      # exprAnd
    | expresion OR expresion                                       # exprOr

    | valor_primitivo                                              # exprPrimitivo
    ;

valor_primitivo
    : ENTERO
    | DECIMAL
    | CADENA
    | CARACTER
    | VERUM
    | FALSUS
    | IDENTIFICADOR
    ;

lista_expresiones
    // "Calle Real", 42
    : expresion (COMA expresion)*
    ;

lista_atributos_instancia
    // "Valeria", 25, {"Avenida Central", 500}
    // nombre: "Valeria", edad: 25
    // un mismo literal de instancia puede mezclar campos con nombre y campos por posicion
    : atributo_instancia (COMA atributo_instancia)* COMA?
    ;

atributo_instancia
    // nombre: "Valeria"
    : IDENTIFICADOR DOS_PUNTOS expresion    # campoConNombre
    // "Valeria"
    | expresion                             # campoPosicional
    ;


// ==========================================
// DECLARACION DE VARIABLES
// ==========================================

declaracion_variable
    // esto miObjeto : novus Persona(12, "Profesor");
    // esto otroObjeto : novus Persona(12, miObjeto, novus Persona());
    : ESTO IDENTIFICADOR DOS_PUNTOS NOVUS IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER PUNTO_Y_COMA                             # declObjetoNovus

    // esto mi_direccion : Direccion {"Calle Real", 42};
    // esto ciudadano : Persona {"Valeria", 25, {"Avenida Central", 500}};
    // esto ciudadano2 : Persona {nombre: "Valeria", edad: 25};
    // los valores pueden ir por posicion o indicando el nombre del campo, incluso mezclados
    | ESTO IDENTIFICADOR DOS_PUNTOS IDENTIFICADOR LLAVE_IZQ lista_atributos_instancia LLAVE_DER PUNTO_Y_COMA                          # declEstructuraConValores

    // esto edad : numerus 20;
    // esto total : numerus fuerza * 2;
    // esto nombre : textum "Somos la resistencia";
    | ESTO IDENTIFICADOR DOS_PUNTOS tipo_dato (ASIGNACION? expresion)? PUNTO_Y_COMA                                                  # declConTipoYValor

    // esto cifrado : falsus;
    | ESTO IDENTIFICADOR DOS_PUNTOS (VERUM | FALSUS) PUNTO_Y_COMA                                                                    # declBooleanaImplicita

    // series mis_enteros[2] : numerus;
    | SERIES IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER DOS_PUNTOS tipo_dato PUNTO_Y_COMA                                     # declArraySinDatos

    // series mis_enteros[2] : numerus {1, 1};
    | SERIES IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER DOS_PUNTOS tipo_dato LLAVE_IZQ lista_expresiones LLAVE_DER PUNTO_Y_COMA   # declArrayConDatos

    // series resistencia[3] : Persona;
    // arreglo de estructuras u objetos importados, sin inicializar
    | SERIES IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER DOS_PUNTOS IDENTIFICADOR PUNTO_Y_COMA                                 # declArrayEstructura

    // series matriz[2][3] : numerus;
    | SERIES IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER CORCHETE_IZQ expresion CORCHETE_DER DOS_PUNTOS tipo_dato PUNTO_Y_COMA   # declMatrizSinDatos
    // series matriz[2][3] : numerus {{3, 2, 1}, {4, 5, 6}};
    | SERIES IDENTIFICADOR CORCHETE_IZQ expresion CORCHETE_DER CORCHETE_IZQ expresion CORCHETE_DER DOS_PUNTOS tipo_dato LLAVE_IZQ fila_matriz_pig (COMA fila_matriz_pig)* LLAVE_DER PUNTO_Y_COMA   # declMatrizConDatos
    ;

// fila de matriz con valores entre llaves
fila_matriz_pig
    // {3, 2, 1}
    : LLAVE_IZQ lista_expresiones LLAVE_DER
    ;


// ==========================================
// ASIGNACION
// ==========================================

asignacion
    // x = 3;
    // array[3] = 3;
    // estudiante.nombre = 3;
    // miObjeto.nombre = "Yennifer";
    : variable_asignable ASIGNACION expresion PUNTO_Y_COMA                                    # asignacionGeneral
    ;


// ==========================================
// INSTRUCCIONES DE FLUJO
// ==========================================

instruccion_flujo
    : asignacion                                               # stmtAsignacion
    | condicional                                              # stmtCondicional
    | ciclo                                                    # stmtCiclo
    | instruccion_lectura                                      # stmtLectura
    | instruccion_impresion                                    # stmtImpresion
    | INTERRUMPE PUNTO_Y_COMA                                  # stmtInterrumpe
    | PERGE PUNTO_Y_COMA                                       # stmtPerge
    | expresion PUNTO_Y_COMA                                   # stmtExpresion
    ;

bloque
    : LLAVE_IZQ instruccion_flujo* LLAVE_DER
    ;


// ==========================================
// CONDICIONALES Y CICLOS
// ==========================================

condicional
    // si (edad > 18) {
    //     cifrado = verum;
    // } finis;
    // si (x > 10 && y < 5) { ... } aliter (x > 10) { ... } aliter { ... } finis;
    : SI PAR_IZQ expresion PAR_DER bloque
      (ALITER PAR_IZQ expresion PAR_DER bloque)*
      (ALITER bloque)?
      FINIS_BLOQUE PUNTO_Y_COMA                                # statementSi
    ;

ciclo
    // dum (x < 100) {
    //     x = x + 1;
    // } finis;
    : DUM PAR_IZQ expresion PAR_DER bloque FINIS_BLOQUE PUNTO_Y_COMA                                                 # cicloDum
    // facere {
    //     ...
    // } dum (x < 10);
    | FACERE bloque DUM PAR_IZQ expresion PAR_DER PUNTO_Y_COMA                                                       # cicloFacere
    // per (esto i : numerus 0; i < 10; i++) { ... }
    | PER PAR_IZQ init_per PUNTO_Y_COMA expresion PUNTO_Y_COMA paso_per PAR_DER bloque  (FINIS_BLOQUE PUNTO_Y_COMA)? # cicloPer
    ;

init_per
    // esto i : numerus 0
    : ESTO IDENTIFICADOR DOS_PUNTOS tipo_dato ASIGNACION? expresion                          # initPerDecl
    // i = 0
    | variable_asignable ASIGNACION expresion                                                # initPerAsig
    ;

paso_per
    // i++
    : expresion                                                                              # pasoPerExpr
    // i = i+1
    | variable_asignable ASIGNACION expresion                                                # pasoPerAsig
    ;


// ==========================================
// INSTRUCCIONES ESPECIALES (ENTRADA / SALIDA)
// ==========================================

instruccion_lectura
    // <<;
    : LEER PUNTO_Y_COMA?                                                                    # lecturaConsolaSimple
    // comandante <<
    // edad <<
    | variable_asignable LEER PUNTO_Y_COMA?                                                 # lecturaConsolaAVariable
    ;

instruccion_impresion
    // >> "Hola comandante!" ;
    // >> "Bienvenido" >> comandante ;
    // : IMPRIMIR elemento_imprimir (IMPRIMIR elemento_imprimir)* PUNTO_Y_COMA                 # impresionEncadenada
    // el punto y coma al final de imprimir es opcional
    : IMPRIMIR elemento_imprimir (IMPRIMIR elemento_imprimir)* PUNTO_Y_COMA?               # impresionEncadenada
    ;

elemento_imprimir
    : expresion
    ;


// ==============================================================================================================================
// - - - - - - - - - - - - - - - - - - - - - - - - - - REGLAS LEXICAS (Lexer)- - - - - - - - - - - - - - - - - - - - - - - - - -
// ==============================================================================================================================


// ==========================================
// IMPORTACIONES
// ==========================================
IMPORT : 'import' ;


// ==========================================
// SECCIONES DE CODIGO
// ==========================================
VARIABILES : 'VARIABILES' ;
MAIOR      : 'MAIOR' ;
FINIS      : 'FINIS' ;


// ==========================================
// TIPOS DE DATOS
// ==========================================
NUMERUS   : 'numerus' ;
TEXTUM    : 'textum' ;
DECIMALIS : 'decimalis' ;
LITTERA   : 'littera' ;
BOOL      : 'bool' ;


// ==========================================
// VALORES BOOLEANOS
// ==========================================
VERUM  : 'verum' ;
FALSUS : 'falsus' ;


// ==========================================
// DECLARACION Y OBJETOS
// ==========================================
ESTO   : 'esto' ;
SERIES : 'series' ;
NOVUS  : 'novus' ;


// ==========================================
// IF Y CICLOS
// ==========================================
SI          : 'si' ;
ALITER      : 'aliter' ;
DUM         : 'dum' ;
FACERE      : 'facere' ;
PER         : 'per' ;
INTERRUMPE  : 'interrumpe' ;
PERGE       : 'perge' ;
FINIS_BLOQUE: 'finis' ;


// ==========================================
// OPERADORES LOGICOS (EN PALABRAS)
// ==========================================
NON : 'non' ;


// ==========================================
// OPERADORES Y SIMBOLOS
// ==========================================
LEER    : '<<' ;
IMPRIMIR: '>>' ;

INCREMENTO : '++' ;
DECREMENTO : '--' ;

IGUAL_QUE      : '==' ;
DIFERENTE_QUE  : '!=' ;
MAYOR_IGUAL_QUE: '>=' ;
MENOR_IGUAL_QUE: '<=' ;
MAYOR_QUE      : '>' ;
MENOR_QUE      : '<' ;

AND : '&&' ;
OR  : '||' ;

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

DECIMAL
    : [0-9]+ '.' [0-9]+
    ;

ENTERO
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

COMENTARIO_BLOQUE
    : '##' .*? '##' -> channel(HIDDEN)
    ;

ESPACIOS_BLANCO
    : [ \t\r\n]+ -> skip
    ;
