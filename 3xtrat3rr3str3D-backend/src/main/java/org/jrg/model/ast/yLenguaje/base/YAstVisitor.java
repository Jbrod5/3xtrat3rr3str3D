package org.jrg.model.ast.yLenguaje.base;

import org.jrg.model.ast.yLenguaje.*;
import org.jrg.model.ast.yLenguaje.atributo_struct.*;
import org.jrg.model.ast.yLenguaje.ciclo.*;
import org.jrg.model.ast.yLenguaje.condicional.*;
import org.jrg.model.ast.yLenguaje.definicion_funcion.*;
import org.jrg.model.ast.yLenguaje.definicion_struct.*;
import org.jrg.model.ast.yLenguaje.declaracion_variable.*;
import org.jrg.model.ast.yLenguaje.expresion.*;
import org.jrg.model.ast.yLenguaje.instruccion.*;
import org.jrg.model.ast.yLenguaje.init_para.*;
import org.jrg.model.ast.yLenguaje.paso_para.*;
import org.jrg.model.ast.yLenguaje.parametro.*;
import org.jrg.model.ast.yLenguaje.seleccion.*;
import org.jrg.model.ast.yLenguaje.variable_asignable.*;

public interface YAstVisitor<T> {

    T visitarPrograma(Programa nodo);
    T visitarSeccionEstructuras(SeccionEstructuras nodo);
    T visitarSeccionFunciones(SeccionFunciones nodo);
    T visitarTipoDato(TipoDato nodo);
    T visitarParametros(Parametros nodo);
    T visitarCuerpoFuncion(CuerpoFuncion nodo);
    T visitarBloque(Bloque nodo);
    T visitarAsignacion(Asignacion nodo);
    T visitarCasoSeleccion(CasoSeleccion nodo);
    T visitarCasoDefecto(CasoDefecto nodo);
    T visitarValorPrimitivo(ValorPrimitivo nodo);
    T visitarListaExpresiones(ListaExpresiones nodo);

    T visitarDefEstructura(DefEstructura nodo);

    T visitarAtributoSimple(AtributoSimple nodo);
    T visitarAtributoArray(AtributoArray nodo);

    T visitarDefFuncionSinRetorno(DefFuncionSinRetorno nodo);
    T visitarDefFuncionConRetorno(DefFuncionConRetorno nodo);

    T visitarParamSimple(ParamSimple nodo);
    T visitarParamArray(ParamArray nodo);
    T visitarParamEstructura(ParamEstructura nodo);

    T visitarStmtDeclaracion(StmtDeclaracion nodo);
    T visitarStmtAsignacion(StmtAsignacion nodo);
    T visitarStmtEstructuraLocal(StmtEstructuraLocal nodo);
    T visitarStmtCondicional(StmtCondicional nodo);
    T visitarStmtSeleccion(StmtSeleccion nodo);
    T visitarStmtCiclo(StmtCiclo nodo);
    T visitarStmtRetorno(StmtRetorno nodo);
    T visitarStmtContinuar(StmtContinuar nodo);
    T visitarStmtRomper(StmtRomper nodo);
    T visitarStmtExpresion(StmtExpresion nodo);

    T visitarDeclConTipoYValor(DeclConTipoYValor nodo);
    T visitarDeclArraySinValores(DeclArraySinValores nodo);
    T visitarDeclArrayConValores(DeclArrayConValores nodo);
    T visitarDeclMatriz(DeclMatriz nodo);

    T visitarVarSimple(VarSimple nodo);
    T visitarVarArray(VarArray nodo);
    T visitarVarMiembro(VarMiembro nodo);

    T visitarStatementSi(StatementSi nodo);

    T visitarStatementElegir(StatementElegir nodo);

    T visitarCicloPara(CicloPara nodo);
    T visitarCicloMientras(CicloMientras nodo);
    T visitarCicloHacer(CicloHacer nodo);

    T visitarInitParaDecl(InitParaDecl nodo);
    T visitarInitParaAsig(InitParaAsig nodo);

    T visitarPasoParaExpr(PasoParaExpr nodo);
    T visitarPasoParaAsig(PasoParaAsig nodo);

    T visitarExprParentesis(ExprParentesis nodo);
    T visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo);
    T visitarExprAccesoArray(ExprAccesoArray nodo);
    T visitarExprAccesoMiembro(ExprAccesoMiembro nodo);
    T visitarExprPostIncremento(ExprPostIncremento nodo);
    T visitarExprPostDecremento(ExprPostDecremento nodo);
    T visitarExprListaLiteral(ExprListaLiteral nodo);
    T visitarExprNegativa(ExprNegativa nodo);
    T visitarExprNegada(ExprNegada nodo);
    T visitarExprMultiplicacionDivision(ExprMultiplicacionDivision nodo);
    T visitarExprSumaResta(ExprSumaResta nodo);
    T visitarExprRelacional(ExprRelacional nodo);
    T visitarExprAnd(ExprAnd nodo);
    T visitarExprOr(ExprOr nodo);
    T visitarExprPrimitivo(ExprPrimitivo nodo);
}