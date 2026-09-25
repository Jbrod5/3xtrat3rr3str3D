package org.jrg.analisis.pigLatin.cuartetas;

import java.util.List;

import org.jrg.model.ast.pigLatin.*;
import org.jrg.model.ast.pigLatin.asignacion.AsignacionGeneral;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoConNombre;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoPosicional;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.ciclo.CicloDum;
import org.jrg.model.ast.pigLatin.ciclo.CicloFacere;
import org.jrg.model.ast.pigLatin.ciclo.CicloPer;
import org.jrg.model.ast.pigLatin.condicional.StatementSi;
import org.jrg.model.ast.pigLatin.declaracion_variable.*;
import org.jrg.model.ast.pigLatin.expresion.*;
import org.jrg.model.ast.pigLatin.init_per.InitPerAsig;
import org.jrg.model.ast.pigLatin.init_per.InitPerDecl;
import org.jrg.model.ast.pigLatin.instruccion_flujo.*;
import org.jrg.model.ast.pigLatin.instruccion_impresion.ImpresionEncadenada;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaAVariable;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaSimple;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerAsig;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerExpr;
import org.jrg.model.ast.pigLatin.variable_asignable.*;
import org.jrg.model.cuarteta.Cuarteta;

// generador de cuartetas para Pig Latin que delega en manejadoras
public class GeneradorCuartetasPigLatin implements LatinusAstVisitor<String> {

    // estado compartido de la generacion
    private final ContextoCuartetasPigLatin ctx;
    // manejadora de programa y secciones
    private final ManejadorProgramaPigLatin manejadorPrograma;
    // manejadora de expresiones y asignables
    private final ManejadorExpresionesPigLatin manejadorExpresiones;
    // manejadora de declaraciones y asignaciones
    private final ManejadorDeclaracionesPigLatin manejadorDeclaraciones;
    // manejadora de flujo lectura e impresion
    private final ManejadorFlujoPigLatin manejadorFlujo;

    // crear el generador con su contexto y sus manejadoras
    public GeneradorCuartetasPigLatin() {
        // inicializar el contexto compartido
        this.ctx = new ContextoCuartetasPigLatin();
        // crear las manejadoras con el contexto y este generador
        this.manejadorPrograma = new ManejadorProgramaPigLatin(ctx, this);
        this.manejadorExpresiones = new ManejadorExpresionesPigLatin(ctx, this);
        this.manejadorDeclaraciones = new ManejadorDeclaracionesPigLatin(ctx, this);
        this.manejadorFlujo = new ManejadorFlujoPigLatin(ctx, this);
    }

    // obtener la lista de cuartetas generadas
    public List<Cuarteta> getCuartetas() {
        return ctx.getCuartetas();
    }

    // registrar los campos de un struct importado en orden
    public void registrarCamposDeStruct(String nombreStruct, List<String> campos) {
        // delegar el registro al contexto compartido
        ctx.registrarCamposDeStruct(nombreStruct, campos);
    }

    // registrar el tipo de una variable declarada
    public void registrarTipoVariable(String nombre, String tipo) {
        // delegar el registro al contexto compartido
        ctx.registrarTipoVariable(nombre, tipo);
    }

    // ==================== PROGRAMA Y SECCIONES ====================

    @Override
    public String visitPrograma(Programa nodo) {
        return manejadorPrograma.visitPrograma(nodo);
    }

    @Override
    public String visitSeccionImportaciones(SeccionImportaciones nodo) {
        return manejadorPrograma.visitSeccionImportaciones(nodo);
    }

    @Override
    public String visitRutaImportacion(RutaImportacion nodo) {
        return manejadorPrograma.visitRutaImportacion(nodo);
    }

    @Override
    public String visitSeccionGlobalVariables(SeccionGlobalVariables nodo) {
        return manejadorPrograma.visitSeccionGlobalVariables(nodo);
    }

    @Override
    public String visitSeccionMaior(SeccionMaior nodo) {
        return manejadorPrograma.visitSeccionMaior(nodo);
    }

    @Override
    public String visitTipoDato(TipoDato nodo) {
        return manejadorPrograma.visitTipoDato(nodo);
    }

    @Override
    public String visitValorPrimitivo(ValorPrimitivo nodo) {
        return manejadorPrograma.visitValorPrimitivo(nodo);
    }

    @Override
    public String visitListaExpresiones(ListaExpresiones nodo) {
        return manejadorPrograma.visitListaExpresiones(nodo);
    }

    @Override
    public String visitListaAtributosInstancia(ListaAtributosInstancia nodo) {
        return manejadorPrograma.visitListaAtributosInstancia(nodo);
    }

    @Override
    public String visitBloque(Bloque nodo) {
        return manejadorPrograma.visitBloque(nodo);
    }

    @Override
    public String visitElementoImprimir(ElementoImprimir nodo) {
        return manejadorPrograma.visitElementoImprimir(nodo);
    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public String visitValorAsignableSimple(ValorAsignableSimple v) {
        return manejadorExpresiones.visitValorAsignableSimple(v);
    }

    @Override
    public String visitValorAsignableArray(ValorAsignableArray v) {
        return manejadorExpresiones.visitValorAsignableArray(v);
    }

    @Override
    public String visitValorAsignableMiembroEstructura(ValorAsignableMiembroEstructura v) {
        return manejadorExpresiones.visitValorAsignableMiembroEstructura(v);
    }

    // ==================== EXPRESIONES ====================

    @Override
    public String visitExprParentesis(ExprParentesis expr) {
        return manejadorExpresiones.visitExprParentesis(expr);
    }

    @Override
    public String visitExprInstanciaObjeto(ExprInstanciaObjeto expr) {
        return manejadorExpresiones.visitExprInstanciaObjeto(expr);
    }

    @Override
    public String visitExprLlamadaFuncion(ExprLlamadaFuncion expr) {
        return manejadorExpresiones.visitExprLlamadaFuncion(expr);
    }

    @Override
    public String visitExprLlamadaMetodo(ExprLlamadaMetodo expr) {
        return manejadorExpresiones.visitExprLlamadaMetodo(expr);
    }

    @Override
    public String visitExprAccesoPosicionArray(ExprAccesoPosicionArray expr) {
        return manejadorExpresiones.visitExprAccesoPosicionArray(expr);
    }

    @Override
    public String visitExprAccesoMiembroEstructura(ExprAccesoMiembroEstructura expr) {
        return manejadorExpresiones.visitExprAccesoMiembroEstructura(expr);
    }

    @Override
    public String visitExprPostIncremento(ExprPostIncremento expr) {
        return manejadorExpresiones.visitExprPostIncremento(expr);
    }

    @Override
    public String visitExprPostDecremento(ExprPostDecremento expr) {
        return manejadorExpresiones.visitExprPostDecremento(expr);
    }

    @Override
    public String visitExprListaLiteral(ExprListaLiteral expr) {
        return manejadorExpresiones.visitExprListaLiteral(expr);
    }

    @Override
    public String visitExprNegativa(ExprNegativa expr) {
        return manejadorExpresiones.visitExprNegativa(expr);
    }

    @Override
    public String visitExprNegada(ExprNegada expr) {
        return manejadorExpresiones.visitExprNegada(expr);
    }

    @Override
    public String visitExprPreIncremento(ExprPreIncremento expr) {
        return manejadorExpresiones.visitExprPreIncremento(expr);
    }

    @Override
    public String visitExprPreDecremento(ExprPreDecremento expr) {
        return manejadorExpresiones.visitExprPreDecremento(expr);
    }

    @Override
    public String visitExprMultiplicacionDivision(ExprMultiplicacionDivision expr) {
        return manejadorExpresiones.visitExprMultiplicacionDivision(expr);
    }

    @Override
    public String visitExprSumaResta(ExprSumaResta expr) {
        return manejadorExpresiones.visitExprSumaResta(expr);
    }

    @Override
    public String visitExprRelacional(ExprRelacional expr) {
        return manejadorExpresiones.visitExprRelacional(expr);
    }

    @Override
    public String visitExprAnd(ExprAnd expr) {
        return manejadorExpresiones.visitExprAnd(expr);
    }

    @Override
    public String visitExprOr(ExprOr expr) {
        return manejadorExpresiones.visitExprOr(expr);
    }

    @Override
    public String visitExprPrimitivo(ExprPrimitivo expr) {
        return manejadorExpresiones.visitExprPrimitivo(expr);
    }

    // ==================== ATRIBUTOS ====================

    @Override
    public String visitCampoConNombre(CampoConNombre nodo) {
        return manejadorDeclaraciones.visitCampoConNombre(nodo);
    }

    @Override
    public String visitCampoPosicional(CampoPosicional nodo) {
        return manejadorDeclaraciones.visitCampoPosicional(nodo);
    }

    // ==================== DECLARACIONES ====================

    @Override
    public String visitDeclObjetoNovus(DeclObjetoNovus nodo) {
        return manejadorDeclaraciones.visitDeclObjetoNovus(nodo);
    }

    @Override
    public String visitDeclEstructuraConValores(DeclEstructuraConValores nodo) {
        return manejadorDeclaraciones.visitDeclEstructuraConValores(nodo);
    }

    @Override
    public String visitDeclConTipoYValor(DeclConTipoYValor nodo) {
        return manejadorDeclaraciones.visitDeclConTipoYValor(nodo);
    }

    @Override
    public String visitDeclBooleanaImplicita(DeclBooleanaImplicita nodo) {
        return manejadorDeclaraciones.visitDeclBooleanaImplicita(nodo);
    }

    @Override
    public String visitDeclArraySinDatos(DeclArraySinDatos nodo) {
        return manejadorDeclaraciones.visitDeclArraySinDatos(nodo);
    }

    @Override
    public String visitDeclArrayConDatos(DeclArrayConDatos nodo) {
        return manejadorDeclaraciones.visitDeclArrayConDatos(nodo);
    }

    @Override
    public String visitDeclArrayEstructura(DeclArrayEstructura nodo) {
        return manejadorDeclaraciones.visitDeclArrayEstructura(nodo);
    }

    // ==================== ASIGNACIONES ====================

    @Override
    public String visitAsignacionGeneral(AsignacionGeneral a) {
        return manejadorDeclaraciones.visitAsignacionGeneral(a);
    }

    // ==================== INSTRUCCIONES DE FLUJO ====================

    @Override
    public String visitStmtAsignacion(StmtAsignacion stmt) {
        return manejadorFlujo.visitStmtAsignacion(stmt);
    }

    @Override
    public String visitStmtCondicional(StmtCondicional stmt) {
        return manejadorFlujo.visitStmtCondicional(stmt);
    }

    @Override
    public String visitStmtCiclo(StmtCiclo stmt) {
        return manejadorFlujo.visitStmtCiclo(stmt);
    }

    @Override
    public String visitStmtLectura(StmtLectura stmt) {
        return manejadorFlujo.visitStmtLectura(stmt);
    }

    @Override
    public String visitStmtImpresion(StmtImpresion stmt) {
        return manejadorFlujo.visitStmtImpresion(stmt);
    }

    @Override
    public String visitStmtInterrumpe(StmtInterrumpe stmt) {
        return manejadorFlujo.visitStmtInterrumpe(stmt);
    }

    @Override
    public String visitStmtPerge(StmtPerge stmt) {
        return manejadorFlujo.visitStmtPerge(stmt);
    }

    @Override
    public String visitStmtExpresion(StmtExpresion stmt) {
        return manejadorFlujo.visitStmtExpresion(stmt);
    }

    // ==================== CONDICIONALES ====================

    @Override
    public String visitStatementSi(StatementSi stmt) {
        return manejadorFlujo.visitStatementSi(stmt);
    }

    // ==================== CICLOS ====================

    @Override
    public String visitCicloDum(CicloDum ciclo) {
        return manejadorFlujo.visitCicloDum(ciclo);
    }

    @Override
    public String visitCicloFacere(CicloFacere ciclo) {
        return manejadorFlujo.visitCicloFacere(ciclo);
    }

    @Override
    public String visitCicloPer(CicloPer ciclo) {
        return manejadorFlujo.visitCicloPer(ciclo);
    }

    // ==================== INIT Y PASO DEL FOR ====================

    @Override
    public String visitInitPerDecl(InitPerDecl init) {
        return manejadorFlujo.visitInitPerDecl(init);
    }

    @Override
    public String visitInitPerAsig(InitPerAsig init) {
        return manejadorFlujo.visitInitPerAsig(init);
    }

    @Override
    public String visitPasoPerExpr(PasoPerExpr paso) {
        return manejadorFlujo.visitPasoPerExpr(paso);
    }

    @Override
    public String visitPasoPerAsig(PasoPerAsig paso) {
        return manejadorFlujo.visitPasoPerAsig(paso);
    }

    // ==================== LECTURA ====================

    @Override
    public String visitLecturaConsolaSimple(LecturaConsolaSimple lectura) {
        return manejadorFlujo.visitLecturaConsolaSimple(lectura);
    }

    @Override
    public String visitLecturaConsolaAVariable(LecturaConsolaAVariable lectura) {
        return manejadorFlujo.visitLecturaConsolaAVariable(lectura);
    }

    // ==================== IMPRESION ====================

    @Override
    public String visitImpresionEncadenada(ImpresionEncadenada impresion) {
        return manejadorFlujo.visitImpresionEncadenada(impresion);
    }
}
