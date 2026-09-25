package org.jrg.analisis.pigLatin;

import org.jrg.antlrBase.pigLatin.PigLatinBaseVisitor;
import org.jrg.antlrBase.pigLatin.PigLatinParser;
import org.jrg.model.ast.pigLatin.*;
import org.jrg.model.ast.pigLatin.asignacion.AsignacionGeneral;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoConNombre;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoPosicional;
import org.jrg.model.ast.pigLatin.ciclo.CicloDum;
import org.jrg.model.ast.pigLatin.ciclo.CicloFacere;
import org.jrg.model.ast.pigLatin.ciclo.CicloPer;
import org.jrg.model.ast.pigLatin.condicional.StatementSi;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayConDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayEstructura;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArraySinDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclMatrizConDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclMatrizSinDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclBooleanaImplicita;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclConTipoYValor;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclEstructuraConValores;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclObjetoNovus;
import org.jrg.model.ast.pigLatin.expresion.ExprAccesoMiembroEstructura;
import org.jrg.model.ast.pigLatin.expresion.ExprAccesoPosicionArray;
import org.jrg.model.ast.pigLatin.expresion.ExprAnd;
import org.jrg.model.ast.pigLatin.expresion.ExprInstanciaObjeto;
import org.jrg.model.ast.pigLatin.expresion.ExprLlamadaFuncion;
import org.jrg.model.ast.pigLatin.expresion.ExprLlamadaMetodo;
import org.jrg.model.ast.pigLatin.expresion.ExprListaLiteral;
import org.jrg.model.ast.pigLatin.expresion.ExprMultiplicacionDivision;
import org.jrg.model.ast.pigLatin.expresion.ExprNegada;
import org.jrg.model.ast.pigLatin.expresion.ExprNegativa;
import org.jrg.model.ast.pigLatin.expresion.ExprOr;
import org.jrg.model.ast.pigLatin.expresion.ExprParentesis;
import org.jrg.model.ast.pigLatin.expresion.ExprPostDecremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPostIncremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPreDecremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPreIncremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPrimitivo;
import org.jrg.model.ast.pigLatin.expresion.ExprRelacional;
import org.jrg.model.ast.pigLatin.expresion.ExprSumaResta;
import org.jrg.model.ast.pigLatin.init_per.InitPerAsig;
import org.jrg.model.ast.pigLatin.init_per.InitPerDecl;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtAsignacion;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtCiclo;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtCondicional;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtExpresion;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtImpresion;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtInterrumpe;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtLectura;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtPerge;
import org.jrg.model.ast.pigLatin.instruccion_impresion.ImpresionEncadenada;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaAVariable;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaSimple;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerAsig;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerExpr;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableArray;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableMiembroEstructura;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableSimple;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.base.TipoPrimitivo;

import java.util.ArrayList;
import java.util.List;

public class PigLatinASTBuilder extends PigLatinBaseVisitor<NodoAST> {

    @Override
    public NodoAST visitPrograma(PigLatinParser.ProgramaContext ctx) {
        // obtener seccion de importaciones si existe
        NodoAST seccionImportaciones = null;
        if (ctx.seccion_importaciones() != null) {
            seccionImportaciones = visit(ctx.seccion_importaciones());
        }
        // obtener seccion de variables globales si existe
        NodoAST seccionGlobalVariables = null;
        if (ctx.seccion_global_variables() != null) {
            seccionGlobalVariables = visit(ctx.seccion_global_variables());
        }
        // obtener seccion mayor obligatoria
        NodoAST seccionMaior = visit(ctx.seccion_maior());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo programa
        return new Programa(seccionImportaciones, seccionGlobalVariables, seccionMaior, linea, columna);
    }

    @Override
    public NodoAST visitSeccion_importaciones(PigLatinParser.Seccion_importacionesContext ctx) {
        // crear lista para importaciones
        List<NodoAST> importaciones = new ArrayList<>();
        // recorrer cada ruta de importacion
        if (ctx.ruta_importacion() != null) {
            for (PigLatinParser.Ruta_importacionContext rutaCtx : ctx.ruta_importacion()) {
                importaciones.add(visit(rutaCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo seccion importaciones
        return new SeccionImportaciones(importaciones, linea, columna);
    }

    @Override
    public NodoAST visitRuta_importacion(PigLatinParser.Ruta_importacionContext ctx) {
        // crear lista para identificadores de la ruta
        List<String> identificadores = new ArrayList<>();
        // extraer cada identificador separado por punto
        for (org.antlr.v4.runtime.tree.TerminalNode id : ctx.IDENTIFICADOR()) {
            identificadores.add(id.getText());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ruta importacion
        return new RutaImportacion(identificadores, linea, columna);
    }

    @Override
    public NodoAST visitSeccion_global_variables(PigLatinParser.Seccion_global_variablesContext ctx) {
        // crear lista para declaraciones
        List<NodoAST> declaraciones = new ArrayList<>();
        // recorrer cada declaracion de variable
        if (ctx.declaracion_variable() != null) {
            for (PigLatinParser.Declaracion_variableContext declCtx : ctx.declaracion_variable()) {
                declaraciones.add(visit(declCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo seccion variables globales
        return new SeccionGlobalVariables(declaraciones, linea, columna);
    }

    @Override
    public NodoAST visitSeccion_maior(PigLatinParser.Seccion_maiorContext ctx) {
        // crear lista para instrucciones
        List<NodoAST> instrucciones = new ArrayList<>();
        // recorrer cada instruccion de flujo
        if (ctx.instruccion_flujo() != null) {
            for (PigLatinParser.Instruccion_flujoContext instCtx : ctx.instruccion_flujo()) {
                instrucciones.add(visit(instCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo seccion maior
        return new SeccionMaior(instrucciones, linea, columna);
    }

    @Override
    public NodoAST visitTipo_dato(PigLatinParser.Tipo_datoContext ctx) {
        // determinar tipo segun token presente
        String tipo;
        if (ctx.NUMERUS() != null) {
            tipo = "numerus";
        } else if (ctx.TEXTUM() != null) {
            tipo = "textum";
        } else if (ctx.DECIMALIS() != null) {
            tipo = "decimalis";
        } else if (ctx.LITTERA() != null) {
            tipo = "littera";
        } else if (ctx.BOOL() != null) {
            tipo = "bool";
        } else if (ctx.IDENTIFICADOR() != null) {
            tipo = ctx.IDENTIFICADOR().getText();
        } else {
            tipo = "";
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo tipo dato
        return new TipoDato(tipo, linea, columna);
    }

    @Override
    public NodoAST visitValorAsignableSimple(PigLatinParser.ValorAsignableSimpleContext ctx) {
        // obtener identificador simple
        String identificador = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable asignable simple
        return new ValorAsignableSimple(identificador, linea, columna);
    }

    @Override
    public NodoAST visitValorAsignableArray(PigLatinParser.ValorAsignableArrayContext ctx) {
        // visitar base del array
        NodoAST base = visit(ctx.variable_asignable());
        // visitar indice del array
        NodoAST indice = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable asignable array
        return new ValorAsignableArray(base, indice, linea, columna);
    }

    @Override
    public NodoAST visitValorAsignableMiembroEstructura(PigLatinParser.ValorAsignableMiembroEstructuraContext ctx) {
        // visitar base de la estructura
        NodoAST base = visit(ctx.variable_asignable());
        // obtener nombre del miembro
        String miembro = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable asignable miembro
        return new ValorAsignableMiembroEstructura(base, miembro, linea, columna);
    }

    @Override
    public NodoAST visitExprParentesis(PigLatinParser.ExprParentesisContext ctx) {
        // visitar expresion entre parentesis
        NodoAST expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion parentesis
        return new ExprParentesis(expresion, linea, columna);
    }

    @Override
    public NodoAST visitExprInstanciaObjeto(PigLatinParser.ExprInstanciaObjetoContext ctx) {
        // obtener tipo de la instancia
        String tipo = ctx.IDENTIFICADOR().getText();
        // visitar argumentos si existen
        NodoAST argumentos = null;
        if (ctx.lista_expresiones() != null) {
            argumentos = visit(ctx.lista_expresiones());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo instancia objeto
        return new ExprInstanciaObjeto(tipo, argumentos, linea, columna);
    }

    @Override
    public NodoAST visitExprLlamadaFuncion(PigLatinParser.ExprLlamadaFuncionContext ctx) {
        // obtener nombre de la funcion
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar argumentos si existen
        NodoAST argumentos = null;
        if (ctx.lista_expresiones() != null) {
            argumentos = visit(ctx.lista_expresiones());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo llamada funcion
        return new ExprLlamadaFuncion(nombre, argumentos, linea, columna);
    }

    @Override
    public NodoAST visitExprLlamadaMetodo(PigLatinParser.ExprLlamadaMetodoContext ctx) {
        // visitar objeto sobre el que se llama el metodo
        NodoAST objeto = visit(ctx.expresion());
        // obtener nombre del metodo
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar argumentos si existen
        NodoAST argumentos = null;
        if (ctx.lista_expresiones() != null) {
            argumentos = visit(ctx.lista_expresiones());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo llamada metodo
        return new ExprLlamadaMetodo(objeto, nombre, argumentos, linea, columna);
    }

    @Override
    public NodoAST visitExprAccesoPosicionArray(PigLatinParser.ExprAccesoPosicionArrayContext ctx) {
        // visitar array base
        NodoAST array = visit(ctx.expresion(0));
        // visitar indice de acceso
        NodoAST indice = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo acceso posicion array
        return new ExprAccesoPosicionArray(array, indice, linea, columna);
    }

    @Override
    public NodoAST visitExprAccesoMiembroEstructura(PigLatinParser.ExprAccesoMiembroEstructuraContext ctx) {
        // visitar objeto base
        NodoAST objeto = visit(ctx.expresion());
        // obtener nombre del miembro
        String miembro = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo acceso miembro estructura
        return new ExprAccesoMiembroEstructura(objeto, miembro, linea, columna);
    }

    @Override
    public NodoAST visitExprPostIncremento(PigLatinParser.ExprPostIncrementoContext ctx) {
        // visitar variable a incrementar
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo post incremento
        return new ExprPostIncremento(variable, linea, columna);
    }

    @Override
    public NodoAST visitExprPostDecremento(PigLatinParser.ExprPostDecrementoContext ctx) {
        // visitar variable a decrementar
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo post decremento
        return new ExprPostDecremento(variable, linea, columna);
    }

    @Override
    public NodoAST visitExprListaLiteral(PigLatinParser.ExprListaLiteralContext ctx) {
        // visitar lista de expresiones
        NodoAST listaExpresiones = visit(ctx.lista_expresiones());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lista literal
        return new ExprListaLiteral(((ListaExpresiones) listaExpresiones).getExpresiones(), linea, columna);
    }

    @Override
    public NodoAST visitExprNegativa(PigLatinParser.ExprNegativaContext ctx) {
        // obtener operador negativo
        String operador = ctx.RESTA().getText();
        // visitar expresion negada
        NodoAST expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion negativa
        return new ExprNegativa(operador, expresion, linea, columna);
    }

    @Override
    public NodoAST visitExprNegada(PigLatinParser.ExprNegadaContext ctx) {
        // obtener operador negacion
        String operador = ctx.NON().getText();
        // visitar expresion negada
        NodoAST expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion negada
        return new ExprNegada(operador, expresion, linea, columna);
    }

    @Override
    public NodoAST visitExprPreIncremento(PigLatinParser.ExprPreIncrementoContext ctx) {
        // obtener operador pre incremento
        String operador = ctx.INCREMENTO().getText();
        // visitar variable a incrementar
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo pre incremento
        return new ExprPreIncremento(operador, variable, linea, columna);
    }

    @Override
    public NodoAST visitExprPreDecremento(PigLatinParser.ExprPreDecrementoContext ctx) {
        // obtener operador pre decremento
        String operador = ctx.DECREMENTO().getText();
        // visitar variable a decrementar
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo pre decremento
        return new ExprPreDecremento(operador, variable, linea, columna);
    }

    @Override
    public NodoAST visitExprMultiplicacionDivision(PigLatinParser.ExprMultiplicacionDivisionContext ctx) {
        // visitar operando izquierdo
        NodoAST operandoIzquierdo = visit(ctx.expresion(0));
        // determinar operador multiplicacion o division
        String operador;
        if (ctx.MULT() != null) {
            operador = ctx.MULT().getText();
        } else {
            operador = ctx.DIV().getText();
        }
        // visitar operando derecho
        NodoAST operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo multiplicacion division
        return new ExprMultiplicacionDivision(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoAST visitExprSumaResta(PigLatinParser.ExprSumaRestaContext ctx) {
        // visitar operando izquierdo
        NodoAST operandoIzquierdo = visit(ctx.expresion(0));
        // determinar operador suma o resta
        String operador;
        if (ctx.SUMA() != null) {
            operador = ctx.SUMA().getText();
        } else {
            operador = ctx.RESTA().getText();
        }
        // visitar operando derecho
        NodoAST operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo suma resta
        return new ExprSumaResta(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoAST visitExprRelacional(PigLatinParser.ExprRelacionalContext ctx) {
        // visitar operando izquierdo
        NodoAST operandoIzquierdo = visit(ctx.expresion(0));
        // determinar operador relacional
        String operador;
        if (ctx.IGUAL_QUE() != null) {
            operador = ctx.IGUAL_QUE().getText();
        } else if (ctx.DIFERENTE_QUE() != null) {
            operador = ctx.DIFERENTE_QUE().getText();
        } else if (ctx.MAYOR_QUE() != null) {
            operador = ctx.MAYOR_QUE().getText();
        } else if (ctx.MENOR_QUE() != null) {
            operador = ctx.MENOR_QUE().getText();
        } else if (ctx.MAYOR_IGUAL_QUE() != null) {
            operador = ctx.MAYOR_IGUAL_QUE().getText();
        } else {
            operador = ctx.MENOR_IGUAL_QUE().getText();
        }
        // visitar operando derecho
        NodoAST operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion relacional
        return new ExprRelacional(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoAST visitExprAnd(PigLatinParser.ExprAndContext ctx) {
        // visitar operando izquierdo
        NodoAST operandoIzquierdo = visit(ctx.expresion(0));
        // obtener operador and
        String operador = ctx.AND().getText();
        // visitar operando derecho
        NodoAST operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion and
        return new ExprAnd(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoAST visitExprOr(PigLatinParser.ExprOrContext ctx) {
        // visitar operando izquierdo
        NodoAST operandoIzquierdo = visit(ctx.expresion(0));
        // obtener operador or
        String operador = ctx.OR().getText();
        // visitar operando derecho
        NodoAST operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion or
        return new ExprOr(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoAST visitExprPrimitivo(PigLatinParser.ExprPrimitivoContext ctx) {
        // visitar valor primitivo
        NodoAST valor = visit(ctx.valor_primitivo());
        // obtener tipo de dato del valor primitivo
        TipoPrimitivo tipoDato = TipoPrimitivo.DESCONOCIDO;
        if (valor instanceof ValorPrimitivo) {
            tipoDato = ((ValorPrimitivo) valor).getTipoDato();
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion primitivo
        return new ExprPrimitivo(valor, tipoDato, linea, columna);
    }

    @Override
    public NodoAST visitValor_primitivo(PigLatinParser.Valor_primitivoContext ctx) {
        // determinar valor segun token presente
        String valor;
        TipoPrimitivo tipoDato;
        if (ctx.ENTERO() != null) {
            valor = ctx.ENTERO().getText();
            tipoDato = TipoPrimitivo.ENTERO;
        } else if (ctx.DECIMAL() != null) {
            valor = ctx.DECIMAL().getText();
            tipoDato = TipoPrimitivo.DECIMAL;
        } else if (ctx.CADENA() != null) {
            valor = ctx.CADENA().getText();
            tipoDato = TipoPrimitivo.CADENA;
        } else if (ctx.CARACTER() != null) {
            valor = ctx.CARACTER().getText();
            tipoDato = TipoPrimitivo.CARACTER;
        } else if (ctx.VERUM() != null) {
            valor = "verum";
            tipoDato = TipoPrimitivo.BOOLEANO;
        } else if (ctx.FALSUS() != null) {
            valor = "falsus";
            tipoDato = TipoPrimitivo.BOOLEANO;
        } else if (ctx.IDENTIFICADOR() != null) {
            valor = ctx.IDENTIFICADOR().getText();
            tipoDato = TipoPrimitivo.IDENTIFICADOR;
        } else {
            valor = "";
            tipoDato = TipoPrimitivo.DESCONOCIDO;
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo valor primitivo
        return new ValorPrimitivo(valor, tipoDato, linea, columna);
    }

    @Override
    public NodoAST visitLista_expresiones(PigLatinParser.Lista_expresionesContext ctx) {
        // crear lista para expresiones
        List<NodoAST> expresiones = new ArrayList<>();
        // recorrer cada expresion de la lista
        for (PigLatinParser.ExpresionContext exprCtx : ctx.expresion()) {
            expresiones.add(visit(exprCtx));
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lista expresiones
        return new ListaExpresiones(expresiones, linea, columna);
    }

    @Override
    public NodoAST visitLista_atributos_instancia(PigLatinParser.Lista_atributos_instanciaContext ctx) {
        // crear lista para atributos
        List<NodoAST> atributos = new ArrayList<>();
        // recorrer cada atributo de instancia
        if (ctx.atributo_instancia() != null) {
            for (PigLatinParser.Atributo_instanciaContext attrCtx : ctx.atributo_instancia()) {
                atributos.add(visit(attrCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lista atributos instancia
        return new ListaAtributosInstancia(atributos, linea, columna);
    }

    @Override
    public NodoAST visitCampoConNombre(PigLatinParser.CampoConNombreContext ctx) {
        // obtener nombre del campo
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar valor del campo
        NodoAST valor = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo campo con nombre
        return new CampoConNombre(nombre, valor, linea, columna);
    }

    @Override
    public NodoAST visitCampoPosicional(PigLatinParser.CampoPosicionalContext ctx) {
        // visitar valor del campo posicional
        NodoAST valor = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo campo posicional
        return new CampoPosicional(valor, linea, columna);
    }

    @Override
    public NodoAST visitDeclObjetoNovus(PigLatinParser.DeclObjetoNovusContext ctx) {
        // obtener identificador de la variable
        String identificador = ctx.IDENTIFICADOR(0).getText();
        // obtener tipo del objeto
        String tipo = ctx.IDENTIFICADOR(1).getText();
        // visitar argumentos si existen
        NodoAST argumentos = null;
        if (ctx.lista_expresiones() != null) {
            argumentos = visit(ctx.lista_expresiones());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion objeto novus
        return new DeclObjetoNovus(identificador, tipo, argumentos, linea, columna);
    }

    @Override
    public NodoAST visitDeclEstructuraConValores(PigLatinParser.DeclEstructuraConValoresContext ctx) {
        // obtener identificador de la variable
        String identificador = ctx.IDENTIFICADOR(0).getText();
        // obtener tipo de la estructura
        String tipo = ctx.IDENTIFICADOR(1).getText();
        // visitar atributos de la instancia
        NodoAST atributos = visit(ctx.lista_atributos_instancia());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion estructura con valores
        return new DeclEstructuraConValores(identificador, tipo, atributos, linea, columna);
    }

    @Override
    public NodoAST visitDeclConTipoYValor(PigLatinParser.DeclConTipoYValorContext ctx) {
        // obtener identificador de la variable
        String identificador = ctx.IDENTIFICADOR().getText();
        // visitar tipo de dato
        NodoAST tipo = visit(ctx.tipo_dato());
        // obtener operador asignacion si existe
        String asignacion = null;
        if (ctx.ASIGNACION() != null) {
            asignacion = ctx.ASIGNACION().getText();
        }
        // visitar valor si existe
        NodoAST valor = null;
        if (ctx.expresion() != null) {
            valor = visit(ctx.expresion());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion con tipo y valor
        return new DeclConTipoYValor(identificador, tipo, asignacion, valor, linea, columna);
    }

    @Override
    public NodoAST visitDeclBooleanaImplicita(PigLatinParser.DeclBooleanaImplicitaContext ctx) {
        // obtener identificador de la variable
        String identificador = ctx.IDENTIFICADOR().getText();
        // determinar valor booleano
        String valor;
        if (ctx.VERUM() != null) {
            valor = "verum";
        } else {
            valor = "falsus";
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion booleana implicita
        return new DeclBooleanaImplicita(identificador, valor, linea, columna);
    }

    @Override
    public NodoAST visitDeclArraySinDatos(PigLatinParser.DeclArraySinDatosContext ctx) {
        // obtener identificador del array
        String identificador = ctx.IDENTIFICADOR().getText();
        // visitar tamano del array
        NodoAST tamano = visit(ctx.expresion());
        // visitar tipo de dato
        NodoAST tipo = visit(ctx.tipo_dato());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion array sin datos
        return new DeclArraySinDatos(identificador, tamano, tipo, linea, columna);
    }

    @Override
    public NodoAST visitDeclArrayConDatos(PigLatinParser.DeclArrayConDatosContext ctx) {
        // obtener identificador del array
        String identificador = ctx.IDENTIFICADOR().getText();
        // visitar tamano del array
        NodoAST tamano = visit(ctx.expresion());
        // visitar tipo de dato
        NodoAST tipo = visit(ctx.tipo_dato());
        // crear lista para valores
        List<NodoAST> valores = new ArrayList<>();
        // visitar lista de expresiones si existe
        if (ctx.lista_expresiones() != null) {
            NodoAST listaExpr = visit(ctx.lista_expresiones());
            valores = ((ListaExpresiones) listaExpr).getExpresiones();
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion array con datos
        return new DeclArrayConDatos(identificador, tamano, tipo, valores, linea, columna);
    }

    @Override
    public NodoAST visitDeclMatrizSinDatos(PigLatinParser.DeclMatrizSinDatosContext ctx) {
        // obtener identificador de la matriz
        String identificador = ctx.IDENTIFICADOR().getText();
        // visitar tamanos de filas y columnas
        NodoAST tamanoFilas = visit(ctx.expresion(0));
        NodoAST tamanoColumnas = visit(ctx.expresion(1));
        // visitar tipo de dato
        NodoAST tipo = visit(ctx.tipo_dato());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion matriz sin datos
        return new DeclMatrizSinDatos(identificador, tamanoFilas, tamanoColumnas, tipo, linea, columna);
    }

    @Override
    public NodoAST visitDeclMatrizConDatos(PigLatinParser.DeclMatrizConDatosContext ctx) {
        // obtener identificador de la matriz
        String identificador = ctx.IDENTIFICADOR().getText();
        // visitar tamanos de filas y columnas
        NodoAST tamanoFilas = visit(ctx.expresion(0));
        NodoAST tamanoColumnas = visit(ctx.expresion(1));
        // visitar tipo de dato
        NodoAST tipo = visit(ctx.tipo_dato());
        // recolectar las filas con sus valores
        List<List<NodoAST>> filas = new ArrayList<>();
        for (int i = 0; i < ctx.fila_matriz_pig().size(); i++) {
            // visitar la lista de la fila actual
            NodoAST lista = visit(ctx.fila_matriz_pig(i).lista_expresiones());
            // guardar los valores si es lista de expresiones
            List<NodoAST> valores = new ArrayList<>();
            if (lista instanceof ListaExpresiones) {
                valores.addAll(((ListaExpresiones) lista).getExpresiones());
            }
            filas.add(valores);
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion matriz con datos
        return new DeclMatrizConDatos(identificador, tamanoFilas, tamanoColumnas, tipo, filas, linea, columna);
    }

    @Override
    public NodoAST visitDeclArrayEstructura(PigLatinParser.DeclArrayEstructuraContext ctx) {
        // obtener identificador del array
        String identificador = ctx.IDENTIFICADOR(0).getText();
        // visitar tamano del array
        NodoAST tamano = visit(ctx.expresion());
        // obtener tipo de la estructura
        String tipo = ctx.IDENTIFICADOR(1).getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion array estructura
        return new DeclArrayEstructura(identificador, tamano, tipo, linea, columna);
    }

    @Override
    public NodoAST visitAsignacionGeneral(PigLatinParser.AsignacionGeneralContext ctx) {
        // visitar variable asignable
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener operador asignacion
        String operador = ctx.ASIGNACION().getText();
        // visitar valor asignado
        NodoAST valor = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo asignacion general
        return new AsignacionGeneral(variable, operador, valor, linea, columna);
    }

    @Override
    public NodoAST visitStmtAsignacion(PigLatinParser.StmtAsignacionContext ctx) {
        // visitar asignacion
        NodoAST asignacion = visit(ctx.asignacion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt asignacion
        return new StmtAsignacion(asignacion, linea, columna);
    }

    @Override
    public NodoAST visitStmtCondicional(PigLatinParser.StmtCondicionalContext ctx) {
        // visitar condicional
        NodoAST condicional = visit(ctx.condicional());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt condicional
        return new StmtCondicional(condicional, linea, columna);
    }

    @Override
    public NodoAST visitStmtCiclo(PigLatinParser.StmtCicloContext ctx) {
        // visitar ciclo
        NodoAST ciclo = visit(ctx.ciclo());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt ciclo
        return new StmtCiclo(ciclo, linea, columna);
    }

    @Override
    public NodoAST visitStmtLectura(PigLatinParser.StmtLecturaContext ctx) {
        // visitar instruccion lectura
        NodoAST lectura = visit(ctx.instruccion_lectura());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt lectura
        return new StmtLectura(lectura, linea, columna);
    }

    @Override
    public NodoAST visitStmtImpresion(PigLatinParser.StmtImpresionContext ctx) {
        // visitar instruccion impresion
        NodoAST impresion = visit(ctx.instruccion_impresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt impresion
        return new StmtImpresion(impresion, linea, columna);
    }

    @Override
    public NodoAST visitStmtInterrumpe(PigLatinParser.StmtInterrumpeContext ctx) {
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt interrumpe
        return new StmtInterrumpe(linea, columna);
    }

    @Override
    public NodoAST visitStmtPerge(PigLatinParser.StmtPergeContext ctx) {
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt perge
        return new StmtPerge(linea, columna);
    }

    @Override
    public NodoAST visitStmtExpresion(PigLatinParser.StmtExpresionContext ctx) {
        // visitar expresion
        NodoAST expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt expresion
        return new StmtExpresion(expresion, linea, columna);
    }

    @Override
    public NodoAST visitBloque(PigLatinParser.BloqueContext ctx) {
        // crear lista para instrucciones
        List<NodoAST> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del bloque
        if (ctx.instruccion_flujo() != null) {
            for (PigLatinParser.Instruccion_flujoContext instCtx : ctx.instruccion_flujo()) {
                instrucciones.add(visit(instCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo bloque
        return new Bloque(instrucciones, linea, columna);
    }

    @Override
    public NodoAST visitStatementSi(PigLatinParser.StatementSiContext ctx) {
        // visitar condicion principal
        NodoAST condicion = visit(ctx.expresion(0));
        // visitar bloque principal
        NodoAST bloque = visit(ctx.bloque(0));
        // crear listas para else if
        List<NodoAST> condicionesAliter = new ArrayList<>();
        List<NodoAST> bloquesAliter = new ArrayList<>();
        // recorrer cada else if
        for (int i = 1; i < ctx.expresion().size(); i++) {
            condicionesAliter.add(visit(ctx.expresion(i)));
            bloquesAliter.add(visit(ctx.bloque(i)));
        }
        // visitar bloque else final si existe
        NodoAST bloqueAliter = null;
        if (ctx.bloque().size() > ctx.expresion().size()) {
            bloqueAliter = visit(ctx.bloque(ctx.bloque().size() - 1));
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo statement si
        return new StatementSi(condicion, bloque, condicionesAliter, bloquesAliter, bloqueAliter, linea, columna);
    }

    @Override
    public NodoAST visitCicloDum(PigLatinParser.CicloDumContext ctx) {
        // visitar condicion del ciclo
        NodoAST condicion = visit(ctx.expresion());
        // visitar bloque del ciclo
        NodoAST bloque = visit(ctx.bloque());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo dum
        return new CicloDum(condicion, bloque, linea, columna);
    }

    @Override
    public NodoAST visitCicloFacere(PigLatinParser.CicloFacereContext ctx) {
        // visitar bloque del ciclo
        NodoAST bloque = visit(ctx.bloque());
        // visitar condicion del ciclo
        NodoAST condicion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo facere
        return new CicloFacere(bloque, condicion, linea, columna);
    }

    @Override
    public NodoAST visitCicloPer(PigLatinParser.CicloPerContext ctx) {
        // visitar inicializacion del ciclo
        NodoAST inicializacion = visit(ctx.init_per());
        // visitar condicion del ciclo
        NodoAST condicion = visit(ctx.expresion());
        // visitar paso del ciclo
        NodoAST paso = visit(ctx.paso_per());
        // visitar bloque del ciclo
        NodoAST bloque = visit(ctx.bloque());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo per
        return new CicloPer(inicializacion, condicion, paso, bloque, linea, columna);
    }

    @Override
    public NodoAST visitInitPerDecl(PigLatinParser.InitPerDeclContext ctx) {
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // visitar tipo de dato
        NodoAST tipo = visit(ctx.tipo_dato());
        // obtener operador asignacion si existe
        String asignacion = null;
        if (ctx.ASIGNACION() != null) {
            asignacion = ctx.ASIGNACION().getText();
        }
        // visitar valor inicial
        NodoAST valor = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo init per declaracion
        return new InitPerDecl(identificador, tipo, asignacion, valor, linea, columna);
    }

    @Override
    public NodoAST visitInitPerAsig(PigLatinParser.InitPerAsigContext ctx) {
        // visitar variable asignable
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener operador asignacion
        String operador = ctx.ASIGNACION().getText();
        // visitar valor asignado
        NodoAST valor = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo init per asignacion
        return new InitPerAsig(variable, operador, valor, linea, columna);
    }

    @Override
    public NodoAST visitPasoPerExpr(PigLatinParser.PasoPerExprContext ctx) {
        // visitar expresion de paso
        NodoAST expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo paso per expresion
        return new PasoPerExpr(expresion, linea, columna);
    }

    @Override
    public NodoAST visitPasoPerAsig(PigLatinParser.PasoPerAsigContext ctx) {
        // visitar variable asignable
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener operador asignacion
        String operador = ctx.ASIGNACION().getText();
        // visitar valor asignado
        NodoAST valor = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo paso per asignacion
        return new PasoPerAsig(variable, operador, valor, linea, columna);
    }

    @Override
    public NodoAST visitLecturaConsolaSimple(PigLatinParser.LecturaConsolaSimpleContext ctx) {
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lectura consola simple
        return new LecturaConsolaSimple(linea, columna);
    }

    @Override
    public NodoAST visitLecturaConsolaAVariable(PigLatinParser.LecturaConsolaAVariableContext ctx) {
        // visitar variable destino
        NodoAST variable = visit(ctx.variable_asignable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lectura consola a variable
        return new LecturaConsolaAVariable(variable, linea, columna);
    }

    @Override
    public NodoAST visitImpresionEncadenada(PigLatinParser.ImpresionEncadenadaContext ctx) {
        // crear lista para elementos de impresion
        List<NodoAST> elementos = new ArrayList<>();
        // recorrer cada elemento a imprimir
        for (PigLatinParser.Elemento_imprimirContext elemCtx : ctx.elemento_imprimir()) {
            elementos.add(visit(elemCtx));
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo impresion encadenada
        return new ImpresionEncadenada(elementos, linea, columna);
    }

    @Override
    public NodoAST visitElemento_imprimir(PigLatinParser.Elemento_imprimirContext ctx) {
        // visitar expresion a imprimir
        NodoAST expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo elemento imprimir
        return new ElementoImprimir(expresion, linea, columna);
    }
}