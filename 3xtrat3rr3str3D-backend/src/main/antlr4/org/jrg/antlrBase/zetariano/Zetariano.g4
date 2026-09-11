grammar Zetariano;


// ==============================================================================================================================
// - - - - - - - - - - - - - - - - - - - - - - - -  REGLAS SINTACTICAS (Parser) - - - - - - - - - - - - - - - - - - - - - - - - -
// ==============================================================================================================================


// ==========================================
// ESTRUCTURA PRINCIPAL
// ==========================================

programa
    // public class Persona { ... }
    // el archivo debe llamarse igual que la clase definida en el
    : definicion_clase EOF
    ;

definicion_clase
    : PUBLIC CLASS IDENTIFICADOR LLAVE_IZQ miembro_clase* LLAVE_DER      # defClase
    ;

miembro_clase
    : atributo_clase        # miembroAtributo
    | constructor            # miembroConstructor
    | metodo                 # miembroMetodo
    ;


// ==========================================
// TIPOS DE DATOS
// ==========================================

tipo_dato
    : INT
    | DOUBLE
    | CHAR
    | BOOLEAN
    | STRING
    | IDENTIFICADOR // para referirse a otra clase definida por el usuario
    ;


// ==========================================
// ATRIBUTOS DE LA CLASE
// ==========================================

atributo_clase
    // String nombre;
    // int edad;
    // public int edad;                  el modificador public es opcional en un atributo
    : PUBLIC? tipo_dato IDENTIFICADOR PUNTO_Y_COMA                                  # atributoSimple
    // int[] calificaciones;
    | PUBLIC? tipo_dato (CORCHETE_IZQ CORCHETE_DER)+ IDENTIFICADOR PUNTO_Y_COMA      # atributoArray
    ;


// ==========================================
// CONSTRUCTORES Y METODOS
// ==========================================

constructor
    // public Persona(String nombreParametro, int edadParametro) { ... }
    // public Persona() { ... }
    // se permite sobrecargar el constructor con distintas listas de parametros
    : PUBLIC IDENTIFICADOR PAR_IZQ parametros? PAR_DER LLAVE_IZQ instruccion* LLAVE_DER    # defConstructor
    ;

metodo
    // public void saludar() { ... }
    : PUBLIC VOID IDENTIFICADOR PAR_IZQ parametros? PAR_DER LLAVE_IZQ instruccion* LLAVE_DER       # metodoSinRetorno
    // public int calcularAnioNacimiento(int anioActual) { ... }
    | PUBLIC tipo_dato IDENTIFICADOR PAR_IZQ parametros? PAR_DER LLAVE_IZQ instruccion* LLAVE_DER   # metodoConRetorno
    ;

parametros
    // String nombreParametro, int edadParametro
    : parametro (COMA parametro)*
    ;

parametro
    // int edadParametro
    : tipo_dato IDENTIFICADOR                                    # paramSimple
    // int[] calificaciones
    | tipo_dato (CORCHETE_IZQ CORCHETE_DER)+ IDENTIFICADOR       # paramArray
    ;


// ==========================================
// INSTRUCCIONES
// ==========================================

instruccion
    : declaracion_variable                # stmtDeclaracion
    | asignacion                          # stmtAsignacion
    | condicional                         # stmtCondicional
    | seleccion                           # stmtSeleccion
    | ciclo                               # stmtCiclo
    | RETURN expresion? PUNTO_Y_COMA      # stmtReturn
    | BREAK PUNTO_Y_COMA                  # stmtBreak
    | CONTINUE PUNTO_Y_COMA               # stmtContinue
    | expresion PUNTO_Y_COMA              # stmtExpresion
    ;


// ==========================================
// DECLARACION DE VARIABLES
// ==========================================

declaracion_variable
    // int edad = 25;
    // double altura = 1.75;
    // char inicial = 'A';
    // boolean esEstudiante = true;
    // Persona alumno1;                     (queda apuntando a null)
    // Persona alumno1 = new Persona("Carlos", 20);
    : tipo_dato (CORCHETE_IZQ CORCHETE_DER)* IDENTIFICADOR (ASIGNACION expresion)? PUNTO_Y_COMA                                 # declConTipo
    // String[] nombres = {"Carlos", "Ana", "Pedro"};
    | tipo_dato (CORCHETE_IZQ CORCHETE_DER)+ IDENTIFICADOR ASIGNACION LLAVE_IZQ lista_expresiones LLAVE_DER PUNTO_Y_COMA        # declConListaLiteral
    ;


// ==========================================
// ASIGNACION
// ==========================================

asignacion
    // x = 5;
    : variable_asignable ASIGNACION expresion PUNTO_Y_COMA                          # asignacionSimple
    // x += 3;
    // x -= 2;
    // x *= 2;
    | variable_asignable (MAS_IGUAL | MENOS_IGUAL | MULT_IGUAL) expresion PUNTO_Y_COMA    # asignacionCompuesta
    ;

variable_asignable
    // x
    : IDENTIFICADOR                                                       # varSimple
    // calificaciones[0]
    | variable_asignable CORCHETE_IZQ expresion CORCHETE_DER              # varArray
    // p1.edad
    | variable_asignable PUNTO IDENTIFICADOR                              # varMiembro
    ;


// ==========================================
// CONDICIONALES
// ==========================================

condicional
    // if (edad > 18) {
    //     println("Es mayor de edad.");
    // } else if (edad == 18) {
    //     println("¡Justo tiene 18 años!");
    // } else {
    //     println("Es menor de edad.");
    // }
    : IF PAR_IZQ expresion PAR_DER bloque
      (ELSE IF PAR_IZQ expresion PAR_DER bloque)*
      (ELSE bloque)?                                                       # statementIf
    ;

bloque
    // { ... }                            bloque con llaves
    : LLAVE_IZQ instruccion* LLAVE_DER
    // if (edad >= 18) println("Es adulto");      una sola instruccion sin llaves
    | instruccion
    ;

seleccion
    // switch (opcion) {
    //     case 1:
    //         println("Opción 1 seleccionada");
    //         break;
    //     case 2:
    //         println("Opción 2 seleccionada");
    //         // sin break, tambien ejecuta el default
    //     default:
    //         println("Opción no válida");
    //         break;
    // }
    : SWITCH PAR_IZQ expresion PAR_DER LLAVE_IZQ caso_switch* caso_default? LLAVE_DER    # statementSwitch
    ;

caso_switch
    // case 1:
    //     x = 10;
    //     break;
    : CASE valor_primitivo DOS_PUNTOS instruccion*
    ;

caso_default
    // default:
    //     println("Opción no válida");
    //     break;
    : DEFAULT DOS_PUNTOS instruccion*
    ;


// ==========================================
// CICLOS
// ==========================================

ciclo
    // for (int i = 0; i < 5; i++) { ... }
    // for ( ; ; ) { ... }                   todos los parametros son opcionales
    : FOR PAR_IZQ init_for? PUNTO_Y_COMA expresion? PUNTO_Y_COMA paso_for? PAR_DER bloque      # cicloFor
    // while (contador < 3) { ... }
    | WHILE PAR_IZQ expresion PAR_DER bloque                                                    # cicloWhile
    // do { ... } while (intentos < 5);
    | DO bloque WHILE PAR_IZQ expresion PAR_DER PUNTO_Y_COMA                                    # cicloDoWhile
    ;

init_for
    // int i = 0
    : tipo_dato IDENTIFICADOR ASIGNACION expresion            # initForDecl
    // i = 0
    | variable_asignable ASIGNACION expresion                 # initForAsig
    ;

paso_for
    // i++
    : expresion                                               # pasoForExpr
    // i = i + 1
    | variable_asignable ASIGNACION expresion                 # pasoForAsig
    ;


// ==========================================
// EXPRESIONES Y VALORES
// ==========================================

expresion
    : PAR_IZQ expresion PAR_DER                                                    # exprParentesis

    // new Persona("Carlos", 25)
    | NEW IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER                          # exprInstanciaObjeto
    // new int[5]
    // new int[3][3]
    | NEW tipo_dato (CORCHETE_IZQ expresion CORCHETE_DER)+                         # exprInstanciaArreglo

    // saludar()
    | IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER                             # exprLlamadaFuncion
    // p1.obtenerEdad()
    | expresion PUNTO IDENTIFICADOR PAR_IZQ lista_expresiones? PAR_DER             # exprLlamadaMetodo

    // numeros[0]
    | expresion CORCHETE_IZQ expresion CORCHETE_DER                                # exprAccesoArray
    // p1.edad
    | expresion PUNTO IDENTIFICADOR                                                # exprAccesoMiembro

    // a++
    | variable_asignable INCREMENTO                                                # exprPostIncremento
    // b--
    | variable_asignable DECREMENTO                                                # exprPostDecremento

    // -3
    | RESTA expresion                                                              # exprNegativa
    // !(a == 10)
    | NEGACION expresion                                                           # exprNegada

    // a * b
    // a / b
    // a % b
    | expresion (MULT | DIV | MOD) expresion                                       # exprMultiplicacionDivisionModulo
    // a + b
    // a - b
    | expresion (SUMA | RESTA) expresion                                           # exprSumaResta

    // a > b
    // a >= 18
    | expresion (IGUAL_QUE | DIFERENTE_QUE | MAYOR_QUE | MENOR_QUE
                | MAYOR_IGUAL_QUE | MENOR_IGUAL_QUE) expresion                     # exprRelacional

    // a > 5 && b < 10
    | expresion AND expresion                                                      # exprAnd
    // a > 20 || b > 0
    | expresion OR expresion                                                       # exprOr

    // (edad >= 18) ? "Es mayor de edad" : "Es menor de edad"
    | expresion INTERROGACION expresion DOS_PUNTOS expresion                       # exprTernario

    | valor_primitivo                                                              # exprPrimitivo
    ;

valor_primitivo
    : NUMERO_ENTERO
    | NUMERO_DECIMAL
    | CADENA
    | CARACTER
    | TRUE
    | FALSE
    | NULL
    | IDENTIFICADOR
    ;

lista_expresiones
    // "Carlos", "Ana", "Pedro"
    : expresion (COMA expresion)*
    ;


// ==============================================================================================================================
// - - - - - - - - - - - - - - - - - - - - - - - - - - REGLAS LEXICAS (Lexer)- - - - - - - - - - - - - - - - - - - - - - - - - -
// ==============================================================================================================================


// ==========================================
// PALABRAS RESERVADAS DE LA CLASE
// ==========================================
PUBLIC : 'public' ;
CLASS  : 'class' ;
VOID   : 'void' ;
NEW    : 'new' ;
NULL   : 'null' ;


// ==========================================
// TIPOS DE DATOS
// ==========================================
INT     : 'int' ;
DOUBLE  : 'double' ;
CHAR    : 'char' ;
BOOLEAN : 'boolean' ;
STRING  : 'String' ;


// ==========================================
// VALORES BOOLEANOS
// ==========================================
TRUE  : 'true' ;
FALSE : 'false' ;


// ==========================================
// IF, SWITCH Y CICLOS
// ==========================================
IF       : 'if' ;
ELSE     : 'else' ;
SWITCH   : 'switch' ;
CASE     : 'case' ;
DEFAULT  : 'default' ;
FOR      : 'for' ;
WHILE    : 'while' ;
DO       : 'do' ;
BREAK    : 'break' ;
CONTINUE : 'continue' ;
RETURN   : 'return' ;


// ==========================================
// OPERADORES Y SIMBOLOS
// ==========================================
INCREMENTO : '++' ;
DECREMENTO : '--' ;

IGUAL_QUE       : '==' ;
DIFERENTE_QUE   : '!=' ;
MAYOR_IGUAL_QUE : '>=' ;
MENOR_IGUAL_QUE : '<=' ;
MAYOR_QUE       : '>' ;
MENOR_QUE       : '<' ;

AND : '&&' ;
OR  : '||' ;
NEGACION : '!' ;

MAS_IGUAL   : '+=' ;
MENOS_IGUAL : '-=' ;
MULT_IGUAL  : '*=' ;
ASIGNACION  : '=' ;

INTERROGACION : '?' ;

SUMA : '+' ;
RESTA: '-' ;
MULT : '*' ;
DIV  : '/' ;
MOD  : '%' ;

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

COMENTARIO_BLOQUE
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

ESPACIOS_BLANCO
    : [ \t\r\n]+ -> skip
    ;
