package org.jrg.analisis.yLenguaje;

import org.jrg.antlrBase.yLenguaje.YLenguajeBaseVisitor;
import org.jrg.antlrBase.yLenguaje.YLenguajeParser;
import org.jrg.model.ast.yLenguaje.*;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoArray;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoSimple;
import org.jrg.model.ast.yLenguaje.ciclo.CicloHacer;
import org.jrg.model.ast.yLenguaje.ciclo.CicloPara;
import org.jrg.model.ast.yLenguaje.ciclo.CicloMientras;
import org.jrg.model.ast.yLenguaje.condicional.StatementSi;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionConRetorno;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionSinRetorno;
import org.jrg.model.ast.yLenguaje.definicion_struct.DefEstructura;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArrayConValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArraySinValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclConTipoYValor;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatriz;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatrizConValores;
import org.jrg.model.ast.yLenguaje.expresion.ExprAccesoArray;
import org.jrg.model.ast.yLenguaje.expresion.ExprAccesoMiembro;
import org.jrg.model.ast.yLenguaje.expresion.ExprAnd;
import org.jrg.model.ast.yLenguaje.expresion.ExprLlamadaFuncion;
import org.jrg.model.ast.yLenguaje.expresion.ExprListaLiteral;
import org.jrg.model.ast.yLenguaje.expresion.ExprMultiplicacionDivision;
import org.jrg.model.ast.yLenguaje.expresion.ExprNegada;
import org.jrg.model.ast.yLenguaje.expresion.ExprNegativa;
import org.jrg.model.ast.yLenguaje.expresion.ExprOr;
import org.jrg.model.ast.yLenguaje.expresion.ExprParentesis;
import org.jrg.model.ast.yLenguaje.expresion.ExprPostDecremento;
import org.jrg.model.ast.yLenguaje.expresion.ExprPostIncremento;
import org.jrg.model.ast.yLenguaje.expresion.ExprPrimitivo;
import org.jrg.model.ast.yLenguaje.expresion.ExprRelacional;
import org.jrg.model.ast.yLenguaje.expresion.ExprSumaResta;
import org.jrg.model.ast.yLenguaje.instruccion.StmtAsignacion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtCiclo;
import org.jrg.model.ast.yLenguaje.instruccion.StmtCondicional;
import org.jrg.model.ast.yLenguaje.instruccion.StmtContinuar;
import org.jrg.model.ast.yLenguaje.instruccion.StmtDeclaracion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtEstructuraLocal;
import org.jrg.model.ast.yLenguaje.instruccion.StmtExpresion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtRetorno;
import org.jrg.model.ast.yLenguaje.instruccion.StmtRomper;
import org.jrg.model.ast.yLenguaje.instruccion.StmtSeleccion;
import org.jrg.model.ast.yLenguaje.init_para.InitParaAsig;
import org.jrg.model.ast.yLenguaje.init_para.InitParaDecl;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaAsig;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaExpr;
import org.jrg.model.ast.yLenguaje.parametro.ParamArray;
import org.jrg.model.ast.yLenguaje.parametro.ParamEstructura;
import org.jrg.model.ast.yLenguaje.parametro.ParamSimple;
import org.jrg.model.ast.yLenguaje.seleccion.StatementElegir;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarArray;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarMiembro;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarSimple;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.CasoDefecto;
import org.jrg.model.ast.yLenguaje.CasoSeleccion;
import org.jrg.model.base.TipoPrimitivo;

import java.util.ArrayList;
import java.util.List;

public class YLenguajeASTBuilder extends YLenguajeBaseVisitor<NodoASTY> {

    @Override
    public NodoASTY visitPrograma(YLenguajeParser.ProgramaContext ctx) {
        // visitar seccion de estructuras si existe
        NodoASTY seccionEstructuras = null;
        if (ctx.seccion_estructuras() != null) {
            seccionEstructuras = visit(ctx.seccion_estructuras());
        }
        // visitar seccion de funciones
        NodoASTY seccionFunciones = visit(ctx.seccion_funciones());
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo programa
        return new Programa(seccionEstructuras, seccionFunciones, linea, columna);
    }

    @Override
    public NodoASTY visitSeccion_estructuras(YLenguajeParser.Seccion_estructurasContext ctx) {
        // crear lista para estructuras
        List<NodoASTY> estructuras = new ArrayList<>();
        // recorrer cada definicion de estructura
        if (ctx.definicion_struct() != null) {
            for (YLenguajeParser.Definicion_structContext defCtx : ctx.definicion_struct()) {
                estructuras.add(visit(defCtx));
            }
        }
        // obtener ubicacion del nodo
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo seccion estructuras
        return new SeccionEstructuras(estructuras, linea, columna);
    }

    @Override
    public NodoASTY visitSeccion_funciones(YLenguajeParser.Seccion_funcionesContext ctx) {
        // crear lista para funciones
        List<NodoASTY> funciones = new ArrayList<>();
        // recorrer cada definicion de funcion
        if (ctx.definicion_funcion() != null) {
            for (YLenguajeParser.Definicion_funcionContext defCtx : ctx.definicion_funcion()) {
                funciones.add(visit(defCtx));
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo seccion funciones
        return new SeccionFunciones(funciones, linea, columna);
    }

    @Override
    public NodoASTY visitTipo_dato(YLenguajeParser.Tipo_datoContext ctx) {
        // obtener el texto del token que representa el tipo
        String nombre;
        if (ctx.TIPO_ENTERO() != null) {
            nombre = ctx.TIPO_ENTERO().getText();
        } else if (ctx.TIPO_CADENA() != null) {
            nombre = ctx.TIPO_CADENA().getText();
        } else if (ctx.TIPO_FLOTANTE() != null) {
            nombre = ctx.TIPO_FLOTANTE().getText();
        } else if (ctx.TIPO_CARACTER() != null) {
            nombre = ctx.TIPO_CARACTER().getText();
        } else if (ctx.TIPO_BOOLEANO() != null) {
            nombre = ctx.TIPO_BOOLEANO().getText();
        } else if (ctx.IDENTIFICADOR() != null) {
            nombre = ctx.IDENTIFICADOR().getText();
        } else {
            nombre = "";
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo tipo dato
        return new TipoDato(nombre, linea, columna);
    }

    @Override
    public NodoASTY visitParametros(YLenguajeParser.ParametrosContext ctx) {
        // crear lista para parametros
        List<NodoASTY> parametros = new ArrayList<>();
        // recorrer cada parametro
        if (ctx.parametro() != null) {
            for (YLenguajeParser.ParametroContext paramCtx : ctx.parametro()) {
                parametros.add(visit(paramCtx));
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo parametros
        return new Parametros(parametros, linea, columna);
    }

    @Override
    public NodoASTY visitCuerpo_funcion(YLenguajeParser.Cuerpo_funcionContext ctx) {
        // crear lista para instrucciones
        List<NodoASTY> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del cuerpo
        if (ctx.instruccion() != null) {
            for (YLenguajeParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo cuerpo funcion
        return new CuerpoFuncion(instrucciones, linea, columna);
    }

    @Override
    public NodoASTY visitBloque(YLenguajeParser.BloqueContext ctx) {
        // crear lista para instrucciones
        List<NodoASTY> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del bloque
        if (ctx.instruccion() != null) {
            for (YLenguajeParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo bloque
        return new Bloque(instrucciones, linea, columna);
    }

    @Override
    public NodoASTY visitDefEstructura(YLenguajeParser.DefEstructuraContext ctx) {
        // obtener nombre de la estructura
        String nombre = ctx.IDENTIFICADOR().getText();
        // crear lista para atributos
        List<NodoASTY> atributos = new ArrayList<>();
        // recorrer cada atributo
        if (ctx.atributo_struct() != null) {
            for (YLenguajeParser.Atributo_structContext attrCtx : ctx.atributo_struct()) {
                atributos.add(visit(attrCtx));
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo definicion estructura
        return new DefEstructura(nombre, atributos, linea, columna);
    }

    @Override
    public NodoASTY visitAtributoSimple(YLenguajeParser.AtributoSimpleContext ctx) {
        // visitar tipo de dato
        NodoASTY tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo atributo simple
        return new AtributoSimple(tipo, identificador, linea, columna);
    }

    @Override
    public NodoASTY visitAtributoArray(YLenguajeParser.AtributoArrayContext ctx) {
        // ver que tipo es
        NodoASTY tipo = visit(ctx.tipo_dato());
        // sacar el nombre
        String identificador = ctx.IDENTIFICADOR().getText();
        // parsear tamano del array
        int tamano = Integer.parseInt(ctx.NUMERO_ENTERO().getText());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo atributo array
        return new AtributoArray(tipo, identificador, tamano, linea, columna);
    }

    @Override
    public NodoASTY visitDefFuncionSinRetorno(YLenguajeParser.DefFuncionSinRetornoContext ctx) {
        // obtener nombre de la funcion
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar parametros si existen
        NodoASTY parametros = null;
        if (ctx.parametros() != null) {
            parametros = visit(ctx.parametros());
        }
        // visitar cuerpo de la funcion
        NodoASTY cuerpo = visit(ctx.cuerpo_funcion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo definicion funcion sin retorno
        return new DefFuncionSinRetorno(nombre, parametros, cuerpo, linea, columna);
    }

    @Override
    public NodoASTY visitDefFuncionConRetorno(YLenguajeParser.DefFuncionConRetornoContext ctx) {
        // visitar tipo de retorno
        NodoASTY tipoRetorno = visit(ctx.tipo_dato());
        // obtener nombre de la funcion
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar parametros si existen
        NodoASTY parametros = null;
        if (ctx.parametros() != null) {
            parametros = visit(ctx.parametros());
        }
        // visitar cuerpo de la funcion
        NodoASTY cuerpo = visit(ctx.cuerpo_funcion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo definicion funcion con retorno
        return new DefFuncionConRetorno(nombre, tipoRetorno, parametros, cuerpo, linea, columna);
    }

    @Override
    public NodoASTY visitParamSimple(YLenguajeParser.ParamSimpleContext ctx) {
        NodoASTY tipo = visit(ctx.tipo_dato());
        String identificador = ctx.IDENTIFICADOR().getText();
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo parametro simple
        return new ParamSimple(tipo, identificador, linea, columna);
    }

    @Override
    public NodoASTY visitParamArray(YLenguajeParser.ParamArrayContext ctx) {
        // visitar tipo de dato
        NodoASTY tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String identificador = ctx.IDENTIFICADOR().getText();
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo parametro array
        return new ParamArray(tipo, identificador, linea, columna);
    }

    @Override
    public NodoASTY visitParamEstructura(YLenguajeParser.ParamEstructuraContext ctx) {
        // obtener tipo de estructura y nombre
        String tipoEstructura = ctx.IDENTIFICADOR(0).getText();
        String nombre = ctx.IDENTIFICADOR(1).getText();
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo parametro estructura
        return new ParamEstructura(tipoEstructura, nombre, linea, columna);
    }

    @Override
    public NodoASTY visitStmtDeclaracion(YLenguajeParser.StmtDeclaracionContext ctx) {
        // visitar declaracion de variable
        NodoASTY declaracion = visit(ctx.declaracion_variable());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt declaracion
        return new StmtDeclaracion(declaracion, linea, columna);
    }

    @Override
    public NodoASTY visitStmtAsignacion(YLenguajeParser.StmtAsignacionContext ctx) {
        // visitar asignacion
        NodoASTY asignacion = visit(ctx.asignacion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt asignacion
        return new StmtAsignacion(asignacion, linea, columna);
    }

    @Override
    public NodoASTY visitStmtEstructuraLocal(YLenguajeParser.StmtEstructuraLocalContext ctx) {
        // visitar definicion de estructura
        NodoASTY estructura = visit(ctx.definicion_struct());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt estructura local
        return new StmtEstructuraLocal(estructura, linea, columna);
    }

    @Override
    public NodoASTY visitStmtCondicional(YLenguajeParser.StmtCondicionalContext ctx) {
        // visitar condicional
        NodoASTY condicional = visit(ctx.condicional());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt condicional
        return new StmtCondicional(condicional, linea, columna);
    }

    @Override
    public NodoASTY visitStmtSeleccion(YLenguajeParser.StmtSeleccionContext ctx) {
        // visitar seleccion
        NodoASTY seleccion = visit(ctx.seleccion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt seleccion
        return new StmtSeleccion(seleccion, linea, columna);
    }

    @Override
    public NodoASTY visitStmtCiclo(YLenguajeParser.StmtCicloContext ctx) {
        // visitar ciclo
        NodoASTY ciclo = visit(ctx.ciclo());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt ciclo
        return new StmtCiclo(ciclo, linea, columna);
    }

    @Override
    public NodoASTY visitStmtRetorno(YLenguajeParser.StmtRetornoContext ctx) {
        // visitar expresion de retorno
        NodoASTY expresion = visit(ctx.retorno().expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt retorno
        return new StmtRetorno(expresion, linea, columna);
    }

    @Override
    public NodoASTY visitStmtContinuar(YLenguajeParser.StmtContinuarContext ctx) {
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt continuar
        return new StmtContinuar(linea, columna);
    }

    @Override
    public NodoASTY visitStmtRomper(YLenguajeParser.StmtRomperContext ctx) {
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt romper
        return new StmtRomper(linea, columna);
    }

    @Override
    public NodoASTY visitStmtExpresion(YLenguajeParser.StmtExpresionContext ctx) {
        // visitar expresion
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo stmt expresion
        return new StmtExpresion(expresion, linea, columna);
    }

    @Override
    public NodoASTY visitDeclConTipoYValor(YLenguajeParser.DeclConTipoYValorContext ctx) {
        // ver que tipo es
        NodoASTY tipo = visit(ctx.tipo_dato());
        // sacar el nombre
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar valor inicial si existe
        NodoASTY valor = null;
        if (ctx.expresion() != null) {
            valor = visit(ctx.expresion());
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion con tipo y valor
        return new DeclConTipoYValor(tipo, nombre, valor, linea, columna);
    }

    @Override
    public NodoASTY visitDeclArraySinValores(YLenguajeParser.DeclArraySinValoresContext ctx) {
        NodoASTY tipo = visit(ctx.tipo_dato());
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar expresion de tamano
        NodoASTY tamano = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion array sin valores
        return new DeclArraySinValores(tipo, nombre, tamano, linea, columna);
    }

    @Override
    public NodoASTY visitDeclArrayConValores(YLenguajeParser.DeclArrayConValoresContext ctx) {
        // visitar tipo de dato
        NodoASTY tipo = visit(ctx.tipo_dato());
        // obtener identificador
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar expresion de tamano
        NodoASTY tamano = visit(ctx.expresion());
        // visitar lista de expresiones
        NodoASTY listaValores = visit(ctx.lista_expresiones());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion array con valores
        return new DeclArrayConValores(tipo, nombre, tamano, listaValores, linea, columna);
    }

    @Override
    public NodoASTY visitDeclMatriz(YLenguajeParser.DeclMatrizContext ctx) {
        // ver que tipo es
        NodoASTY tipo = visit(ctx.tipo_dato());
        // sacar el nombre
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar expresiones de tamano
        NodoASTY tamanoFilas = visit(ctx.expresion(0));
        NodoASTY tamanoColumnas = visit(ctx.expresion(1));
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion matriz
        return new DeclMatriz(tipo, nombre, tamanoFilas, tamanoColumnas, linea, columna);
    }

    @Override
    public NodoASTY visitDeclMatrizConValores(YLenguajeParser.DeclMatrizConValoresContext ctx) {
        NodoASTY tipo = visit(ctx.tipo_dato());
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar expresiones de tamano
        NodoASTY tamanoFilas = visit(ctx.expresion(0));
        NodoASTY tamanoColumnas = visit(ctx.expresion(1));
        // juntar las filas con sus valores
        List<List<NodoASTY>> filas = new ArrayList<>();
        for (int i = 0; i < ctx.fila_matriz().size(); i++) {
            // visitar la lista de la fila actual
            NodoASTY lista = visit(ctx.fila_matriz(i).lista_expresiones());
            // guardar los valores si es lista de expresiones
            List<NodoASTY> valores = new ArrayList<>();
            if (lista instanceof ListaExpresiones) {
                valores.addAll(((ListaExpresiones) lista).getExpresiones());
            }
            filas.add(valores);
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo declaracion matriz con valores
        return new DeclMatrizConValores(tipo, nombre, tamanoFilas, tamanoColumnas, filas, linea, columna);
    }

    @Override
    public NodoASTY visitAsignacion(YLenguajeParser.AsignacionContext ctx) {
        // visitar variable asignable
        NodoASTY variable = visit(ctx.variable_asignable());
        // visitar expresion valor
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo asignacion
        return new Asignacion(variable, expresion, linea, columna);
    }

    @Override
    public NodoASTY visitVarSimple(YLenguajeParser.VarSimpleContext ctx) {
        // obtener identificador
        String nombre = ctx.IDENTIFICADOR().getText();
        // sacar linea y columna del ctx
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable simple
        return new VarSimple(nombre, linea, columna);
    }

    @Override
    public NodoASTY visitVarArray(YLenguajeParser.VarArrayContext ctx) {
        // visitar variable base
        NodoASTY base = visit(ctx.variable_asignable());
        // visitar indice
        NodoASTY indice = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable array
        return new VarArray(base, indice, linea, columna);
    }

    @Override
    public NodoASTY visitVarMiembro(YLenguajeParser.VarMiembroContext ctx) {
        // visitar variable base
        NodoASTY base = visit(ctx.variable_asignable());
        // obtener nombre del miembro
        String miembro = ctx.IDENTIFICADOR().getText();
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo variable miembro
        return new VarMiembro(base, miembro, linea, columna);
    }

    @Override
    public NodoASTY visitStatementSi(YLenguajeParser.StatementSiContext ctx) {
        // visitar condicion principal
        NodoASTY condicionPrincipal = visit(ctx.expresion(0));
        // visitar bloque principal
        NodoASTY bloquePrincipal = visit(ctx.bloque(0));
        // crear listas para else if
        List<NodoASTY> condicionesSino = new ArrayList<>();
        List<NodoASTY> bloquesSino = new ArrayList<>();
        // recorrer cada else if
        for (int i = 1; i < ctx.expresion().size(); i++) {
            condicionesSino.add(visit(ctx.expresion(i)));
            bloquesSino.add(visit(ctx.bloque(i)));
        }
        // visitar bloque contrario si existe
        NodoASTY bloqueContrario = null;
        if (ctx.bloque().size() > ctx.expresion().size()) {
            bloqueContrario = visit(ctx.bloque(ctx.bloque().size() - 1));
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo statement si
        return new StatementSi(condicionPrincipal, bloquePrincipal, condicionesSino, bloquesSino, bloqueContrario, linea, columna);
    }

    @Override
    public NodoASTY visitStatementElegir(YLenguajeParser.StatementElegirContext ctx) {
        // visitar expresion de seleccion
        NodoASTY expresion = visit(ctx.expresion());
        // crear lista para casos
        List<NodoASTY> casos = new ArrayList<>();
        // recorrer cada caso
        if (ctx.caso_seleccion() != null) {
            for (YLenguajeParser.Caso_seleccionContext casoCtx : ctx.caso_seleccion()) {
                casos.add(visit(casoCtx));
            }
        }
        // visitar caso defecto si existe
        NodoASTY casoDefecto = null;
        if (ctx.caso_defecto() != null) {
            casoDefecto = visit(ctx.caso_defecto());
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo statement elegir
        return new StatementElegir(expresion, casos, casoDefecto, linea, columna);
    }

    @Override
    public NodoASTY visitCaso_seleccion(YLenguajeParser.Caso_seleccionContext ctx) {
        // visitar valor primitivo del caso
        NodoASTY valor = visit(ctx.valor_primitivo());
        // crear lista para instrucciones
        List<NodoASTY> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del caso
        if (ctx.instruccion() != null) {
            for (YLenguajeParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo caso seleccion
        return new CasoSeleccion(valor, instrucciones, linea, columna);
    }

    @Override
    public NodoASTY visitCaso_defecto(YLenguajeParser.Caso_defectoContext ctx) {
        // crear lista para instrucciones
        List<NodoASTY> instrucciones = new ArrayList<>();
        // recorrer cada instruccion del defecto
        if (ctx.instruccion() != null) {
            for (YLenguajeParser.InstruccionContext instCtx : ctx.instruccion()) {
                instrucciones.add(visit(instCtx));
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo caso defecto
        return new CasoDefecto(instrucciones, linea, columna);
    }

    @Override
    public NodoASTY visitCicloPara(YLenguajeParser.CicloParaContext ctx) {
        // visitar inicializacion
        NodoASTY inicializacion = visit(ctx.init_para());
        // visitar condicion
        NodoASTY condicion = visit(ctx.expresion());
        // visitar paso
        NodoASTY paso = visit(ctx.paso_para());
        // visitar bloque
        NodoASTY bloque = visit(ctx.bloque());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo para
        return new CicloPara(inicializacion, condicion, paso, bloque, linea, columna);
    }

    @Override
    public NodoASTY visitCicloMientras(YLenguajeParser.CicloMientrasContext ctx) {
        // visitar condicion
        NodoASTY condicion = visit(ctx.expresion());
        // visitar bloque
        NodoASTY bloque = visit(ctx.bloque());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo mientras
        return new CicloMientras(condicion, bloque, linea, columna);
    }

    @Override
    public NodoASTY visitCicloHacer(YLenguajeParser.CicloHacerContext ctx) {
        // visitar bloque
        NodoASTY bloque = visit(ctx.bloque());
        // visitar condicion
        NodoASTY condicion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo ciclo hacer
        return new CicloHacer(bloque, condicion, linea, columna);
    }

    @Override
    public NodoASTY visitInitParaDecl(YLenguajeParser.InitParaDeclContext ctx) {
        // visitar tipo de dato
        NodoASTY tipo = visit(ctx.tipo_dato());
        // sacar el nombre
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar expresion
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo init para decl
        return new InitParaDecl(tipo, nombre, expresion, linea, columna);
    }

    @Override
    public NodoASTY visitInitParaAsig(YLenguajeParser.InitParaAsigContext ctx) {
        // visitar variable asignable
        NodoASTY variable = visit(ctx.variable_asignable());
        // visitar expresion
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo init para asig
        return new InitParaAsig(variable, expresion, linea, columna);
    }

    @Override
    public NodoASTY visitPasoParaExpr(YLenguajeParser.PasoParaExprContext ctx) {
        // visitar expresion
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo paso para expr
        return new PasoParaExpr(expresion, linea, columna);
    }

    @Override
    public NodoASTY visitPasoParaAsig(YLenguajeParser.PasoParaAsigContext ctx) {
        // visitar variable asignable
        NodoASTY variable = visit(ctx.variable_asignable());
        // visitar expresion
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo paso para asig
        return new PasoParaAsig(variable, expresion, linea, columna);
    }

    @Override
    public NodoASTY visitExprParentesis(YLenguajeParser.ExprParentesisContext ctx) {
        // visitar expresion entre parentesis
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion parentesis
        return new ExprParentesis(expresion, linea, columna);
    }

    @Override
    public NodoASTY visitExprLlamadaFuncion(YLenguajeParser.ExprLlamadaFuncionContext ctx) {
        // obtener nombre de la funcion
        String nombre = ctx.IDENTIFICADOR().getText();
        // visitar argumentos si existen
        List<NodoASTY> argumentos = new ArrayList<>();
        if (ctx.lista_expresiones() != null) {
            NodoASTY lista = visit(ctx.lista_expresiones());
            if (lista instanceof ListaExpresiones) {
                argumentos = ((ListaExpresiones) lista).getExpresiones();
            }
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo llamada funcion
        return new ExprLlamadaFuncion(nombre, argumentos, linea, columna);
    }

    @Override
    public NodoASTY visitExprAccesoArray(YLenguajeParser.ExprAccesoArrayContext ctx) {
        // visitar objeto base
        NodoASTY objeto = visit(ctx.expresion(0));
        // visitar indice
        NodoASTY indice = visit(ctx.expresion(1));
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo acceso array
        return new ExprAccesoArray(objeto, indice, linea, columna);
    }

    @Override
    public NodoASTY visitExprAccesoMiembro(YLenguajeParser.ExprAccesoMiembroContext ctx) {
        // visitar objeto base
        NodoASTY objeto = visit(ctx.expresion());
        // obtener nombre del miembro
        String miembro = ctx.IDENTIFICADOR().getText();
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo acceso miembro
        return new ExprAccesoMiembro(objeto, miembro, linea, columna);
    }

    @Override
    public NodoASTY visitExprPostIncremento(YLenguajeParser.ExprPostIncrementoContext ctx) {
        // visitar variable
        NodoASTY variable = visit(ctx.variable_asignable());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo post incremento
        return new ExprPostIncremento(variable, linea, columna);
    }

    @Override
    public NodoASTY visitExprPostDecremento(YLenguajeParser.ExprPostDecrementoContext ctx) {
        // visitar variable
        NodoASTY variable = visit(ctx.variable_asignable());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo post decremento
        return new ExprPostDecremento(variable, linea, columna);
    }

    @Override
    public NodoASTY visitExprListaLiteral(YLenguajeParser.ExprListaLiteralContext ctx) {
        // visitar lista de expresiones
        NodoASTY lista = visit(ctx.lista_expresiones());
        List<NodoASTY> elementos = new ArrayList<>();
        if (lista instanceof ListaExpresiones) {
            elementos = ((ListaExpresiones) lista).getExpresiones();
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lista literal
        return new ExprListaLiteral(elementos, linea, columna);
    }

    @Override
    public NodoASTY visitExprNegativa(YLenguajeParser.ExprNegativaContext ctx) {
        // visitar expresion
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion negativa
        return new ExprNegativa(expresion, linea, columna);
    }

    @Override
    public NodoASTY visitExprNegada(YLenguajeParser.ExprNegadaContext ctx) {
        // visitar expresion
        NodoASTY expresion = visit(ctx.expresion());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion negada
        return new ExprNegada(expresion, linea, columna);
    }

    @Override
    public NodoASTY visitExprMultiplicacionDivision(YLenguajeParser.ExprMultiplicacionDivisionContext ctx) {
        // visitar operando izquierdo
        NodoASTY izquierdo = visit(ctx.expresion(0));
        // obtener operador
        String operador = ctx.getChild(1).getText();
        // visitar operando derecho
        NodoASTY derecho = visit(ctx.expresion(1));
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo multiplicacion division
        return new ExprMultiplicacionDivision(izquierdo, operador, derecho, linea, columna);
    }

    @Override
    public NodoASTY visitExprSumaResta(YLenguajeParser.ExprSumaRestaContext ctx) {
        // ver lado izq
        NodoASTY izquierdo = visit(ctx.expresion(0));
        // obtener operador
        String operador = ctx.getChild(1).getText();
        // ver lado der
        NodoASTY derecho = visit(ctx.expresion(1));
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo suma resta
        return new ExprSumaResta(izquierdo, operador, derecho, linea, columna);
    }

    @Override
    public NodoASTY visitExprRelacional(YLenguajeParser.ExprRelacionalContext ctx) {
        NodoASTY izquierdo = visit(ctx.expresion(0));
        // obtener operador
        String operador = ctx.getChild(1).getText();
        NodoASTY derecho = visit(ctx.expresion(1));
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion relacional
        return new ExprRelacional(izquierdo, operador, derecho, linea, columna);
    }

    @Override
    public NodoASTY visitExprAnd(YLenguajeParser.ExprAndContext ctx) {
        // visitar operando izquierdo
        NodoASTY izquierdo = visit(ctx.expresion(0));
        // visitar operando derecho
        NodoASTY derecho = visit(ctx.expresion(1));
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion and
        return new ExprAnd(izquierdo, derecho, linea, columna);
    }

    @Override
    public NodoASTY visitExprOr(YLenguajeParser.ExprOrContext ctx) {
        // ver lado izq
        NodoASTY izquierdo = visit(ctx.expresion(0));
        // ver lado der
        NodoASTY derecho = visit(ctx.expresion(1));
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion or
        return new ExprOr(izquierdo, derecho, linea, columna);
    }

    @Override
    public NodoASTY visitExprPrimitivo(YLenguajeParser.ExprPrimitivoContext ctx) {
        // visitar valor primitivo
        NodoASTY valor = visit(ctx.valor_primitivo());
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo expresion primitivo
        return new ExprPrimitivo(valor, linea, columna);
    }

    @Override
    public NodoASTY visitValor_primitivo(YLenguajeParser.Valor_primitivoContext ctx) {
        // determinar valor y tipo segun token presente
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
        } else if (ctx.VERDADERO() != null) {
            valor = "verdadero";
            tipoDato = TipoPrimitivo.BOOLEANO;
        } else if (ctx.FALSO() != null) {
            valor = "falso";
            tipoDato = TipoPrimitivo.BOOLEANO;
        } else if (ctx.IDENTIFICADOR() != null) {
            valor = ctx.IDENTIFICADOR().getText();
            tipoDato = TipoPrimitivo.IDENTIFICADOR;
        } else {
            valor = "";
            tipoDato = TipoPrimitivo.DESCONOCIDO;
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo valor primitivo
        return new ValorPrimitivo(valor, tipoDato, linea, columna);
    }

    @Override
    public NodoASTY visitLista_expresiones(YLenguajeParser.Lista_expresionesContext ctx) {
        // crear lista para expresiones
        List<NodoASTY> expresiones = new ArrayList<>();
        // recorrer cada expresion de la lista
        for (YLenguajeParser.ExpresionContext exprCtx : ctx.expresion()) {
            expresiones.add(visit(exprCtx));
        }
        int linea = ctx.getStart().getLine();
        int columna = ctx.getStart().getCharPositionInLine();
        // crear nodo lista expresiones
        return new ListaExpresiones(expresiones, linea, columna);
    }
}