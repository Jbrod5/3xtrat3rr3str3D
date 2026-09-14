package org.jrg.analisis.zetariano;

import org.jrg.antlrBase.zetariano.ZetarianoBaseVisitor;
import org.jrg.antlrBase.zetariano.ZetarianoParser;
import org.jrg.model.ast.zetariano.*;
import org.jrg.model.ast.zetariano.asignacion.AsignacionCompuesta;
import org.jrg.model.ast.zetariano.asignacion.AsignacionSimple;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoArray;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoSimple;
import org.jrg.model.ast.zetariano.condicional.StatementIf;
import org.jrg.model.ast.zetariano.constructor.DefConstructor;
import org.jrg.model.ast.zetariano.ciclo.CicloDoWhile;
import org.jrg.model.ast.zetariano.ciclo.CicloFor;
import org.jrg.model.ast.zetariano.ciclo.CicloWhile;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConListaLiteral;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConTipo;
import org.jrg.model.ast.zetariano.expresion.ExprAccesoArray;
import org.jrg.model.ast.zetariano.expresion.ExprAccesoMiembro;
import org.jrg.model.ast.zetariano.expresion.ExprAnd;
import org.jrg.model.ast.zetariano.expresion.ExprInstanciaArreglo;
import org.jrg.model.ast.zetariano.expresion.ExprInstanciaObjeto;
import org.jrg.model.ast.zetariano.expresion.ExprLlamadaFuncion;
import org.jrg.model.ast.zetariano.expresion.ExprLlamadaMetodo;
import org.jrg.model.ast.zetariano.expresion.ExprMultiplicacionDivisionModulo;
import org.jrg.model.ast.zetariano.expresion.ExprNegada;
import org.jrg.model.ast.zetariano.expresion.ExprNegativa;
import org.jrg.model.ast.zetariano.expresion.ExprOr;
import org.jrg.model.ast.zetariano.expresion.ExprParentesis;
import org.jrg.model.ast.zetariano.expresion.ExprPostDecremento;
import org.jrg.model.ast.zetariano.expresion.ExprPostIncremento;
import org.jrg.model.ast.zetariano.expresion.ExprPrimitivo;
import org.jrg.model.ast.zetariano.expresion.ExprRelacional;
import org.jrg.model.ast.zetariano.expresion.ExprSumaResta;
import org.jrg.model.ast.zetariano.expresion.ExprTernario;
import org.jrg.model.ast.zetariano.init_for.InitForAsig;
import org.jrg.model.ast.zetariano.init_for.InitForDecl;
import org.jrg.model.ast.zetariano.instruccion.StmtAsignacion;
import org.jrg.model.ast.zetariano.instruccion.StmtBreak;
import org.jrg.model.ast.zetariano.instruccion.StmtCiclo;
import org.jrg.model.ast.zetariano.instruccion.StmtCondicional;
import org.jrg.model.ast.zetariano.instruccion.StmtContinue;
import org.jrg.model.ast.zetariano.instruccion.StmtDeclaracion;
import org.jrg.model.ast.zetariano.instruccion.StmtExpresion;
import org.jrg.model.ast.zetariano.instruccion.StmtReturn;
import org.jrg.model.ast.zetariano.instruccion.StmtSeleccion;
import org.jrg.model.ast.zetariano.metodo.MetodoConRetorno;
import org.jrg.model.ast.zetariano.metodo.MetodoSinRetorno;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroAtributo;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroConstructor;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroMetodo;
import org.jrg.model.ast.zetariano.paso_for.PasoForAsig;
import org.jrg.model.ast.zetariano.paso_for.PasoForExpr;
import org.jrg.model.ast.zetariano.parametro.ParamArray;
import org.jrg.model.ast.zetariano.parametro.ParamSimple;
import org.jrg.model.ast.zetariano.CasoDefault;
import org.jrg.model.ast.zetariano.CasoSwitch;
import org.jrg.model.ast.zetariano.seleccion.StatementSwitch;
import org.jrg.model.ast.zetariano.variable_asignable.VarArray;
import org.jrg.model.ast.zetariano.variable_asignable.VarMiembro;
import org.jrg.model.ast.zetariano.variable_asignable.VarSimple;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.ast.zetariano.definicion_clase.DefClase;

import java.util.ArrayList;
import java.util.List;

public class ZetarianoASTBuilder extends ZetarianoBaseVisitor<NodoASTZetariano> {

    @Override
    public NodoASTZetariano visitPrograma(ZetarianoParser.ProgramaContext ctx) {
        // visitar definicion de clase
        NodoASTZetariano definicionClase = visit(ctx.definicion_clase());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo programa
        return new Programa(definicionClase, linea, columna);
    }

    @Override
    public NodoASTZetariano visitDefClase(ZetarianoParser.DefClaseContext ctx) {
        // obtener nombre de la clase
        String nombre = ctx.IDENTIFICADOR().getText();
        // crear lista para miembros
        List<NodoASTZetariano> miembros = new ArrayList<>();
        // recorrer cada miembro de la clase
        if (ctx.miembro_clase() != null) {
            for (ZetarianoParser.Miembro_claseContext miembroCtx : ctx.miembro_clase()) {
                miembros.add(visit(miembroCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo definicion clase
        return new DefClase(nombre, miembros, linea, columna);
    }

    @Override
    public NodoASTZetariano visitMiembroAtributo(ZetarianoParser.MiembroAtributoContext ctx) {
        // visitar atributo de clase
        NodoASTZetariano atributo = visit(ctx.atributo_clase());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo miembro atributo
        return new MiembroAtributo(atributo, linea, columna);
    }

    @Override
    public NodoASTZetariano visitMiembroConstructor(ZetarianoParser.MiembroConstructorContext ctx) {
        // visitar constructor
        NodoASTZetariano constructor = visit(ctx.constructor());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo miembro constructor
        return new MiembroConstructor(constructor, linea, columna);
    }

    @Override
    public NodoASTZetariano visitMiembroMetodo(ZetarianoParser.MiembroMetodoContext ctx) {
        // visitar metodo
        NodoASTZetariano metodo = visit(ctx.metodo());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo miembro metodo
        return new MiembroMetodo(metodo, linea, columna);
    }

    @Override
    public NodoASTZetariano visitTipo_dato(ZetarianoParser.Tipo_datoContext ctx) {
        // determinar tipo segun token presente
        String tipo;
        if (ctx.INT() != null) {
            tipo = "int";
        } else if (ctx.DOUBLE() != null) {
            tipo = "double";
        } else if (ctx.CHAR() != null) {
            tipo = "char";
        } else if (ctx.BOOLEAN() != null) {
            tipo = "boolean";
        } else if (ctx.STRING() != null) {
            tipo = "String";
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
    public NodoASTZetariano visitAtributoSimple(ZetarianoParser.AtributoSimpleContext ctx) {
        // visitar tipo de dato
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo atributo simple
        return new AtributoSimple(tipo, identificador, linea, columna);
    }

    @Override
    public NodoASTZetariano visitAtributoArray(ZetarianoParser.AtributoArrayContext ctx) {
        // visitar tipo de dato
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // contar dimensiones por corchetes
        int dimensiones = ctx.CORCHETE_IZQ().size();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo atributo array
        return new AtributoArray(tipo, identificador, dimensiones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitDefConstructor(ZetarianoParser.DefConstructorContext ctx) {
        // obtener nombre del constructor
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar parametros si existen
        NodoASTZetariano parametros = null;
        if (ctx.parametros() != null) {
            parametros = visit(ctx.parametros());
        }
        // crear lista para instrucciones
        List<NodoASTZetariano> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del constructor
        if (ctx.instruccion() != null) {
            for (ZetarianoParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo definicion constructor
        return new DefConstructor(nombre, parametros, instrucciones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitMetodoSinRetorno(ZetarianoParser.MetodoSinRetornoContext ctx) {
        // obtener nombre del metodo
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar parametros si existen
        NodoASTZetariano parametros = null;
        if (ctx.parametros() != null) {
            parametros = visit(ctx.parametros());
        }
        // crear lista para instrucciones
        List<NodoASTZetariano> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del metodo
        if (ctx.instruccion() != null) {
            for (ZetarianoParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo metodo sin retorno
        return new MetodoSinRetorno(nombre, parametros, instrucciones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitMetodoConRetorno(ZetarianoParser.MetodoConRetornoContext ctx) {
        // visitar tipo de retorno
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener nombre del metodo
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar parametros si existen
        NodoASTZetariano parametros = null;
        if (ctx.parametros() != null) {
            parametros = visit(ctx.parametros());
        }
        // crear lista para instrucciones
        List<NodoASTZetariano> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del metodo
        if (ctx.instruccion() != null) {
            for (ZetarianoParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo metodo con retorno
        return new MetodoConRetorno(tipo, nombre, parametros, instrucciones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitParametros(ZetarianoParser.ParametrosContext ctx) {
        // crear lista para parametros
        List<NodoASTZetariano> parametros = new ArrayList<>();
        // recorrer cada parametro
        if (ctx.parametro() != null) {
            for (ZetarianoParser.ParametroContext paramCtx : ctx.parametro()) {
                parametros.add(visit(paramCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo parametros
        return new Parametros(parametros, linea, columna);
    }

    @Override
    public NodoASTZetariano visitParamSimple(ZetarianoParser.ParamSimpleContext ctx) {
        // visitar tipo de dato
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo parametro simple
        return new ParamSimple(tipo, identificador, linea, columna);
    }

    @Override
    public NodoASTZetariano visitParamArray(ZetarianoParser.ParamArrayContext ctx) {
        // visitar tipo de dato
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // contar dimensiones por corchetes
        int dimensiones = ctx.CORCHETE_IZQ().size();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo parametro array
        return new ParamArray(tipo, identificador, dimensiones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtDeclaracion(ZetarianoParser.StmtDeclaracionContext ctx) {
        // visitar declaracion de variable
        NodoASTZetariano declaracion = visit(ctx.declaracion_variable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt declaracion
        return new StmtDeclaracion(declaracion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtAsignacion(ZetarianoParser.StmtAsignacionContext ctx) {
        // visitar asignacion
        NodoASTZetariano asignacion = visit(ctx.asignacion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt asignacion
        return new StmtAsignacion(asignacion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtCondicional(ZetarianoParser.StmtCondicionalContext ctx) {
        // visitar condicional
        NodoASTZetariano condicional = visit(ctx.condicional());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt condicional
        return new StmtCondicional(condicional, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtSeleccion(ZetarianoParser.StmtSeleccionContext ctx) {
        // visitar seleccion
        NodoASTZetariano seleccion = visit(ctx.seleccion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt seleccion
        return new StmtSeleccion(seleccion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtCiclo(ZetarianoParser.StmtCicloContext ctx) {
        // visitar ciclo
        NodoASTZetariano ciclo = visit(ctx.ciclo());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt ciclo
        return new StmtCiclo(ciclo, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtReturn(ZetarianoParser.StmtReturnContext ctx) {
        // visitar expresion de retorno si existe
        NodoASTZetariano expresion = null;
        if (ctx.expresion() != null) {
            expresion = visit(ctx.expresion());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt return
        return new StmtReturn(expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtBreak(ZetarianoParser.StmtBreakContext ctx) {
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt break
        return new StmtBreak(linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtContinue(ZetarianoParser.StmtContinueContext ctx) {
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt continue
        return new StmtContinue(linea, columna);
    }

    @Override
    public NodoASTZetariano visitStmtExpresion(ZetarianoParser.StmtExpresionContext ctx) {
        // visitar expresion
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt expresion
        return new StmtExpresion(expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitDeclConTipo(ZetarianoParser.DeclConTipoContext ctx) {
        // visitar tipo de dato
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // contar dimensiones por corchetes
        int dimensiones = ctx.CORCHETE_IZQ().size();
        // visitar valor inicial si existe
        NodoASTZetariano valor = null;
        if (ctx.expresion() != null) {
            valor = visit(ctx.expresion());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion con tipo
        return new DeclConTipo(tipo, identificador, dimensiones, valor, linea, columna);
    }

    @Override
    public NodoASTZetariano visitDeclConListaLiteral(ZetarianoParser.DeclConListaLiteralContext ctx) {
        // visitar tipo de dato
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // contar dimensiones por corchetes
        int dimensiones = ctx.CORCHETE_IZQ().size();
        // visitar lista de expresiones
        NodoASTZetariano listaExpresiones = visit(ctx.lista_expresiones());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion con lista literal
        return new DeclConListaLiteral(tipo, identificador, dimensiones, listaExpresiones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitAsignacionSimple(ZetarianoParser.AsignacionSimpleContext ctx) {
        // visitar variable asignable
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // visitar expresion valor
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo asignacion simple
        return new AsignacionSimple(variable, expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitAsignacionCompuesta(ZetarianoParser.AsignacionCompuestaContext ctx) {
        // visitar variable asignable
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // determinar operador compuesto
        String operador;
        if (ctx.MAS_IGUAL() != null) {
            operador = ctx.MAS_IGUAL().getText();
        } else if (ctx.MENOS_IGUAL() != null) {
            operador = ctx.MENOS_IGUAL().getText();
        } else {
            operador = ctx.MULT_IGUAL().getText();
        }
        // visitar expresion valor
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo asignacion compuesta
        return new AsignacionCompuesta(variable, operador, expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitVarSimple(ZetarianoParser.VarSimpleContext ctx) {
        // obtener identificador simple
        String identificador = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable simple
        return new VarSimple(identificador, linea, columna);
    }

    @Override
    public NodoASTZetariano visitVarArray(ZetarianoParser.VarArrayContext ctx) {
        // visitar variable base
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // visitar indice
        NodoASTZetariano indice = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable array
        return new VarArray(variable, indice, linea, columna);
    }

    @Override
    public NodoASTZetariano visitVarMiembro(ZetarianoParser.VarMiembroContext ctx) {
        // visitar variable base
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // obtener nombre del miembro
        String miembro = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable miembro
        return new VarMiembro(variable, miembro, linea, columna);
    }

    @Override
    public NodoASTZetariano visitStatementIf(ZetarianoParser.StatementIfContext ctx) {
        // visitar condicion principal
        NodoASTZetariano condicion = visit(ctx.expresion(0));
        // visitar bloque principal
        NodoASTZetariano bloque = visit(ctx.bloque(0));
        // crear listas para else if
        List<NodoASTZetariano> condicionesSinoSi = new ArrayList<>();
        List<NodoASTZetariano> bloquesSinoSi = new ArrayList<>();
        // recorrer cada else if
        for (int i = 1; i < ctx.expresion().size(); i++) {
            condicionesSinoSi.add(visit(ctx.expresion(i)));
            bloquesSinoSi.add(visit(ctx.bloque(i)));
        }
        // visitar bloque else final si existe
        NodoASTZetariano bloqueSino = null;
        if (ctx.bloque().size() > ctx.expresion().size()) {
            bloqueSino = visit(ctx.bloque(ctx.bloque().size() - 1));
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo statement if
        return new StatementIf(condicion, bloque, condicionesSinoSi, bloquesSinoSi, bloqueSino, linea, columna);
    }

    @Override
    public NodoASTZetariano visitBloque(ZetarianoParser.BloqueContext ctx) {
        // crear lista para instrucciones
        List<NodoASTZetariano> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del bloque
        if (ctx.instruccion() != null) {
            for (ZetarianoParser.InstruccionContext instCtx : ctx.instruccion()) {
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
    public NodoASTZetariano visitStatementSwitch(ZetarianoParser.StatementSwitchContext ctx) {
        // visitar expresion del switch
        NodoASTZetariano expresion = visit(ctx.expresion());
        // crear lista para casos
        List<NodoASTZetariano> casos = new ArrayList<>();
        // recorrer cada caso
        if (ctx.caso_switch() != null) {
            for (ZetarianoParser.Caso_switchContext casoCtx : ctx.caso_switch()) {
                casos.add(visit(casoCtx));
            }
        }
        // visitar caso default si existe
        NodoASTZetariano casoDefecto = null;
        if (ctx.caso_default() != null) {
            casoDefecto = visit(ctx.caso_default());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo statement switch
        return new StatementSwitch(expresion, casos, casoDefecto, linea, columna);
    }

    @Override
    public NodoASTZetariano visitCaso_switch(ZetarianoParser.Caso_switchContext ctx) {
        // visitar valor primitivo del caso
        NodoASTZetariano valor = visit(ctx.valor_primitivo());
        // crear lista para instrucciones
        List<NodoASTZetariano> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del caso
        if (ctx.instruccion() != null) {
            for (ZetarianoParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo caso switch
        return new CasoSwitch(valor, instrucciones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitCaso_default(ZetarianoParser.Caso_defaultContext ctx) {
        // crear lista para instrucciones
        List<NodoASTZetariano> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del default
        if (ctx.instruccion() != null) {
            for (ZetarianoParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo caso default
        return new CasoDefault(instrucciones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitCicloFor(ZetarianoParser.CicloForContext ctx) {
        // visitar inicializacion si existe
        NodoASTZetariano inicializacion = null;
        if (ctx.init_for() != null) {
            inicializacion = visit(ctx.init_for());
        }
        // visitar condicion si existe
        NodoASTZetariano condicion = null;
        if (ctx.expresion() != null) {
            condicion = visit(ctx.expresion());
        }
        // visitar paso si existe
        NodoASTZetariano paso = null;
        if (ctx.paso_for() != null) {
            paso = visit(ctx.paso_for());
        }
        // visitar bloque del ciclo
        NodoASTZetariano bloque = visit(ctx.bloque());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo for
        return new CicloFor(inicializacion, condicion, paso, bloque, linea, columna);
    }

    @Override
    public NodoASTZetariano visitCicloWhile(ZetarianoParser.CicloWhileContext ctx) {
        // visitar condicion del ciclo
        NodoASTZetariano condicion = visit(ctx.expresion());
        // visitar bloque del ciclo
        NodoASTZetariano bloque = visit(ctx.bloque());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo while
        return new CicloWhile(condicion, bloque, linea, columna);
    }

    @Override
    public NodoASTZetariano visitCicloDoWhile(ZetarianoParser.CicloDoWhileContext ctx) {
        // visitar bloque del ciclo
        NodoASTZetariano bloque = visit(ctx.bloque());
        // visitar condicion del ciclo
        NodoASTZetariano condicion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo do while
        return new CicloDoWhile(bloque, condicion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitInitForDecl(ZetarianoParser.InitForDeclContext ctx) {
        // visitar tipo de dato
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        // visitar expresion inicial
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo init for declaracion
        return new InitForDecl(tipo, identificador, expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitInitForAsig(ZetarianoParser.InitForAsigContext ctx) {
        // visitar variable asignable
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // visitar expresion valor
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo init for asignacion
        return new InitForAsig(variable, expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitPasoForExpr(ZetarianoParser.PasoForExprContext ctx) {
        // visitar expresion de paso
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo paso for expresion
        return new PasoForExpr(expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitPasoForAsig(ZetarianoParser.PasoForAsigContext ctx) {
        // visitar variable asignable
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // visitar expresion valor
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo paso for asignacion
        return new PasoForAsig(variable, expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprParentesis(ZetarianoParser.ExprParentesisContext ctx) {
        // visitar expresion entre parentesis
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion parentesis
        return new ExprParentesis(expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprInstanciaObjeto(ZetarianoParser.ExprInstanciaObjetoContext ctx) {
        // obtener nombre de la clase
        String nombreClase = ctx.IDENTIFICADOR().getText();
        // visitar argumentos si existen
        NodoASTZetariano argumentos = null;
        if (ctx.lista_expresiones() != null) {
            argumentos = visit(ctx.lista_expresiones());
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo instancia objeto
        return new ExprInstanciaObjeto(nombreClase, argumentos, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprInstanciaArreglo(ZetarianoParser.ExprInstanciaArregloContext ctx) {
        // visitar tipo del arreglo
        NodoASTZetariano tipo = visit(ctx.tipo_dato());
        // crear lista para dimensiones
        List<NodoASTZetariano> dimensiones = new ArrayList<>();
        // recorrer cada expresion de dimension
        for (ZetarianoParser.ExpresionContext exprCtx : ctx.expresion()) {
            dimensiones.add(visit(exprCtx));
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo instancia arreglo
        return new ExprInstanciaArreglo(tipo, dimensiones, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprLlamadaFuncion(ZetarianoParser.ExprLlamadaFuncionContext ctx) {
        // obtener nombre de la funcion
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar argumentos si existen
        NodoASTZetariano argumentos = null;
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
    public NodoASTZetariano visitExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx) {
        // visitar objeto base
        NodoASTZetariano objeto = visit(ctx.expresion());
        // obtener nombre del metodo
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar argumentos si existen
        NodoASTZetariano argumentos = null;
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
    public NodoASTZetariano visitExprAccesoArray(ZetarianoParser.ExprAccesoArrayContext ctx) {
        // visitar array base
        NodoASTZetariano objeto = visit(ctx.expresion(0));
        // visitar indice de acceso
        NodoASTZetariano indice = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo acceso array
        return new ExprAccesoArray(objeto, indice, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx) {
        // visitar objeto base
        NodoASTZetariano objeto = visit(ctx.expresion());
        // obtener nombre del miembro
        String miembro = ctx.IDENTIFICADOR().getText();
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo acceso miembro
        return new ExprAccesoMiembro(objeto, miembro, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprPostIncremento(ZetarianoParser.ExprPostIncrementoContext ctx) {
        // visitar variable a incrementar
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo post incremento
        return new ExprPostIncremento(variable, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprPostDecremento(ZetarianoParser.ExprPostDecrementoContext ctx) {
        // visitar variable a decrementar
        NodoASTZetariano variable = visit(ctx.variable_asignable());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo post decremento
        return new ExprPostDecremento(variable, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprNegativa(ZetarianoParser.ExprNegativaContext ctx) {
        // obtener operador negativo
        String operador = ctx.RESTA().getText();
        // visitar expresion negada
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion negativa
        return new ExprNegativa(operador, expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprNegada(ZetarianoParser.ExprNegadaContext ctx) {
        // obtener operador negacion
        String operador = ctx.NEGACION().getText();
        // visitar expresion negada
        NodoASTZetariano expresion = visit(ctx.expresion());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion negada
        return new ExprNegada(operador, expresion, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprMultiplicacionDivisionModulo(ZetarianoParser.ExprMultiplicacionDivisionModuloContext ctx) {
        // visitar operando izquierdo
        NodoASTZetariano operandoIzquierdo = visit(ctx.expresion(0));
        // determinar operador multiplicacion division modulo
        String operador;
        if (ctx.MULT() != null) {
            operador = ctx.MULT().getText();
        } else if (ctx.DIV() != null) {
            operador = ctx.DIV().getText();
        } else {
            operador = ctx.MOD().getText();
        }
        // visitar operando derecho
        NodoASTZetariano operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo multiplicacion division modulo
        return new ExprMultiplicacionDivisionModulo(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprSumaResta(ZetarianoParser.ExprSumaRestaContext ctx) {
        // visitar operando izquierdo
        NodoASTZetariano operandoIzquierdo = visit(ctx.expresion(0));
        // determinar operador suma resta
        String operador;
        if (ctx.SUMA() != null) {
            operador = ctx.SUMA().getText();
        } else {
            operador = ctx.RESTA().getText();
        }
        // visitar operando derecho
        NodoASTZetariano operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo suma resta
        return new ExprSumaResta(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprRelacional(ZetarianoParser.ExprRelacionalContext ctx) {
        // visitar operando izquierdo
        NodoASTZetariano operandoIzquierdo = visit(ctx.expresion(0));
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
        NodoASTZetariano operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion relacional
        return new ExprRelacional(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprAnd(ZetarianoParser.ExprAndContext ctx) {
        // visitar operando izquierdo
        NodoASTZetariano operandoIzquierdo = visit(ctx.expresion(0));
        // obtener operador and
        String operador = ctx.AND().getText();
        // visitar operando derecho
        NodoASTZetariano operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion and
        return new ExprAnd(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprOr(ZetarianoParser.ExprOrContext ctx) {
        // visitar operando izquierdo
        NodoASTZetariano operandoIzquierdo = visit(ctx.expresion(0));
        // obtener operador or
        String operador = ctx.OR().getText();
        // visitar operando derecho
        NodoASTZetariano operandoDerecho = visit(ctx.expresion(1));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion or
        return new ExprOr(operandoIzquierdo, operador, operandoDerecho, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprTernario(ZetarianoParser.ExprTernarioContext ctx) {
        // visitar condicion
        NodoASTZetariano condicion = visit(ctx.expresion(0));
        // visitar valor verdadero
        NodoASTZetariano valorVerdadero = visit(ctx.expresion(1));
        // visitar valor falso
        NodoASTZetariano valorFalso = visit(ctx.expresion(2));
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion ternario
        return new ExprTernario(condicion, valorVerdadero, valorFalso, linea, columna);
    }

    @Override
    public NodoASTZetariano visitExprPrimitivo(ZetarianoParser.ExprPrimitivoContext ctx) {
        // visitar valor primitivo
        NodoASTZetariano valor = visit(ctx.valor_primitivo());
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
    public NodoASTZetariano visitValor_primitivo(ZetarianoParser.Valor_primitivoContext ctx) {
        // determinar valor segun token presente
        String valor;
        TipoPrimitivo tipoDato;
        if (ctx.NUMERO_ENTERO() != null) {
            valor = ctx.NUMERO_ENTERO().getText();
            tipoDato = TipoPrimitivo.ENTERO;
        } else if (ctx.NUMERO_DECIMAL() != null) {
            valor = ctx.NUMERO_DECIMAL().getText();
            tipoDato = TipoPrimitivo.DECIMAL;
        } else if (ctx.CADENA() != null) {
            valor = ctx.CADENA().getText();
            tipoDato = TipoPrimitivo.CADENA;
        } else if (ctx.CARACTER() != null) {
            valor = ctx.CARACTER().getText();
            tipoDato = TipoPrimitivo.CARACTER;
        } else if (ctx.TRUE() != null) {
            valor = "true";
            tipoDato = TipoPrimitivo.BOOLEANO;
        } else if (ctx.FALSE() != null) {
            valor = "false";
            tipoDato = TipoPrimitivo.BOOLEANO;
        } else if (ctx.NULL() != null) {
            valor = "null";
            tipoDato = TipoPrimitivo.NULO;
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
    public NodoASTZetariano visitLista_expresiones(ZetarianoParser.Lista_expresionesContext ctx) {
        // crear lista para expresiones
        List<NodoASTZetariano> expresiones = new ArrayList<>();
        // recorrer cada expresion de la lista
        for (ZetarianoParser.ExpresionContext exprCtx : ctx.expresion()) {
            expresiones.add(visit(exprCtx));
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lista expresiones
        return new ListaExpresiones(expresiones, linea, columna);
    }
}