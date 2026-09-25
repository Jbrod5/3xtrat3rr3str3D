package org.jrg.model.ast.pigLatin.base;

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

public interface LatinusAstVisitor<T> {
    T visitPrograma(Programa nodo);
    T visitSeccionImportaciones(SeccionImportaciones nodo);
    T visitRutaImportacion(RutaImportacion nodo);
    T visitSeccionGlobalVariables(SeccionGlobalVariables nodo);
    T visitSeccionMaior(SeccionMaior nodo);
    T visitTipoDato(TipoDato nodo);
    T visitValorPrimitivo(ValorPrimitivo nodo);
    T visitListaExpresiones(ListaExpresiones nodo);
    T visitListaAtributosInstancia(ListaAtributosInstancia nodo);
    T visitBloque(Bloque nodo);
    T visitElementoImprimir(ElementoImprimir nodo);

    T visitValorAsignableSimple(ValorAsignableSimple nodo);
    T visitValorAsignableArray(ValorAsignableArray nodo);
    T visitValorAsignableMiembroEstructura(ValorAsignableMiembroEstructura nodo);

    T visitExprParentesis(ExprParentesis nodo);
    T visitExprInstanciaObjeto(ExprInstanciaObjeto nodo);
    T visitExprLlamadaFuncion(ExprLlamadaFuncion nodo);
    T visitExprLlamadaMetodo(ExprLlamadaMetodo nodo);
    T visitExprAccesoPosicionArray(ExprAccesoPosicionArray nodo);
    T visitExprAccesoMiembroEstructura(ExprAccesoMiembroEstructura nodo);
    T visitExprPostIncremento(ExprPostIncremento nodo);
    T visitExprPostDecremento(ExprPostDecremento nodo);
    T visitExprListaLiteral(ExprListaLiteral nodo);
    T visitExprNegativa(ExprNegativa nodo);
    T visitExprNegada(ExprNegada nodo);
    T visitExprPreIncremento(ExprPreIncremento nodo);
    T visitExprPreDecremento(ExprPreDecremento nodo);
    T visitExprMultiplicacionDivision(ExprMultiplicacionDivision nodo);
    T visitExprSumaResta(ExprSumaResta nodo);
    T visitExprRelacional(ExprRelacional nodo);
    T visitExprAnd(ExprAnd nodo);
    T visitExprOr(ExprOr nodo);
    T visitExprPrimitivo(ExprPrimitivo nodo);

    T visitCampoConNombre(CampoConNombre nodo);
    T visitCampoPosicional(CampoPosicional nodo);

    T visitDeclObjetoNovus(DeclObjetoNovus nodo);
    T visitDeclEstructuraConValores(DeclEstructuraConValores nodo);
    T visitDeclConTipoYValor(DeclConTipoYValor nodo);
    T visitDeclBooleanaImplicita(DeclBooleanaImplicita nodo);
    T visitDeclArraySinDatos(DeclArraySinDatos nodo);
    T visitDeclArrayConDatos(DeclArrayConDatos nodo);
    T visitDeclArrayEstructura(DeclArrayEstructura nodo);
    T visitDeclMatrizSinDatos(DeclMatrizSinDatos nodo);
    T visitDeclMatrizConDatos(DeclMatrizConDatos nodo);

    T visitAsignacionGeneral(AsignacionGeneral nodo);

    T visitStmtAsignacion(StmtAsignacion nodo);
    T visitStmtCondicional(StmtCondicional nodo);
    T visitStmtCiclo(StmtCiclo nodo);
    T visitStmtLectura(StmtLectura nodo);
    T visitStmtImpresion(StmtImpresion nodo);
    T visitStmtInterrumpe(StmtInterrumpe nodo);
    T visitStmtPerge(StmtPerge nodo);
    T visitStmtExpresion(StmtExpresion nodo);

    T visitStatementSi(StatementSi nodo);

    T visitCicloDum(CicloDum nodo);
    T visitCicloFacere(CicloFacere nodo);
    T visitCicloPer(CicloPer nodo);

    T visitInitPerDecl(InitPerDecl nodo);
    T visitInitPerAsig(InitPerAsig nodo);

    T visitPasoPerExpr(PasoPerExpr nodo);
    T visitPasoPerAsig(PasoPerAsig nodo);

    T visitLecturaConsolaSimple(LecturaConsolaSimple nodo);
    T visitLecturaConsolaAVariable(LecturaConsolaAVariable nodo);

    T visitImpresionEncadenada(ImpresionEncadenada nodo);
}
