package org.jrg.analisis.yLenguaje.cuartetas;

import java.util.List;

import org.jrg.model.ast.yLenguaje.Asignacion;
import org.jrg.model.ast.yLenguaje.Bloque;
import org.jrg.model.ast.yLenguaje.CasoDefecto;
import org.jrg.model.ast.yLenguaje.CasoSeleccion;
import org.jrg.model.ast.yLenguaje.CuerpoFuncion;
import org.jrg.model.ast.yLenguaje.ListaExpresiones;
import org.jrg.model.ast.yLenguaje.Parametros;
import org.jrg.model.ast.yLenguaje.Programa;
import org.jrg.model.ast.yLenguaje.SeccionEstructuras;
import org.jrg.model.ast.yLenguaje.SeccionFunciones;
import org.jrg.model.ast.yLenguaje.TipoDato;
import org.jrg.model.ast.yLenguaje.ValorPrimitivo;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoArray;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoSimple;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;
import org.jrg.model.ast.yLenguaje.ciclo.CicloHacer;
import org.jrg.model.ast.yLenguaje.ciclo.CicloMientras;
import org.jrg.model.ast.yLenguaje.ciclo.CicloPara;
import org.jrg.model.ast.yLenguaje.condicional.StatementSi;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArrayConValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArraySinValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclConTipoYValor;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatriz;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatrizConValores;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionConRetorno;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionSinRetorno;
import org.jrg.model.ast.yLenguaje.definicion_struct.DefEstructura;
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
import org.jrg.model.ast.yLenguaje.init_para.InitParaAsig;
import org.jrg.model.ast.yLenguaje.init_para.InitParaDecl;
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
import org.jrg.model.ast.yLenguaje.parametro.ParamArray;
import org.jrg.model.ast.yLenguaje.parametro.ParamEstructura;
import org.jrg.model.ast.yLenguaje.parametro.ParamSimple;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaAsig;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaExpr;
import org.jrg.model.ast.yLenguaje.seleccion.StatementElegir;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarArray;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarMiembro;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarSimple;
import org.jrg.model.cuarteta.Cuarteta;

// generador de cuartetas para el lenguaje Y que delega en manejadoras
public class GeneradorCuartetasY implements YAstVisitor<String> {

    // estado compartido de la generacion
    private final ContextoCuartetasY ctx;
    // manejadora de programa y secciones
    private final ManejadorProgramaY manejadorPrograma;
    // manejadora de estructuras
    private final ManejadorEstructurasY manejadorEstructuras;
    // manejadora de funciones
    private final ManejadorFuncionesY manejadorFunciones;
    // manejadora de declaraciones y asignables
    private final ManejadorDeclaracionesY manejadorDeclaraciones;
    // manejadora de flujo y ciclos
    private final ManejadorFlujoY manejadorFlujo;
    // manejadora de expresiones
    private final ManejadorExpresionesY manejadorExpresiones;

    /**
     * Crear el generador de cuartetas para el lenguaje Y.
     */
    public GeneradorCuartetasY() {
        // inicializar el contexto compartido
        this.ctx = new ContextoCuartetasY();
        // crear las manejadoras con el contexto y este generador
        this.manejadorPrograma = new ManejadorProgramaY(ctx, this);
        this.manejadorEstructuras = new ManejadorEstructurasY(ctx, this);
        this.manejadorFunciones = new ManejadorFuncionesY(ctx, this);
        this.manejadorDeclaraciones = new ManejadorDeclaracionesY(ctx, this);
        this.manejadorFlujo = new ManejadorFlujoY(ctx, this);
        this.manejadorExpresiones = new ManejadorExpresionesY(ctx, this);
    }

    /**
     * Obtener la lista de cuartetas generadas.
     */
    public List<Cuarteta> getCuartetas() {
        return ctx.getCuartetas();
    }

    // registrar el tipo de una variable declarada
    public void registrarTipoVariable(String nombre, String tipo) {
        // delegar el registro al contexto compartido
        ctx.registrarTipoVariable(nombre, tipo);
    }

    // ==================== PROGRAMA Y SECCIONES ====================

    @Override
    public String visitarPrograma(Programa nodo) {
        return manejadorPrograma.visitarPrograma(nodo);
    }

    @Override
    public String visitarSeccionEstructuras(SeccionEstructuras nodo) {
        return manejadorPrograma.visitarSeccionEstructuras(nodo);
    }

    @Override
    public String visitarSeccionFunciones(SeccionFunciones nodo) {
        return manejadorPrograma.visitarSeccionFunciones(nodo);
    }

    @Override
    public String visitarTipoDato(TipoDato nodo) {
        return manejadorPrograma.visitarTipoDato(nodo);
    }

    @Override
    public String visitarParametros(Parametros nodo) {
        return manejadorPrograma.visitarParametros(nodo);
    }

    @Override
    public String visitarCuerpoFuncion(CuerpoFuncion nodo) {
        return manejadorPrograma.visitarCuerpoFuncion(nodo);
    }

    @Override
    public String visitarBloque(Bloque nodo) {
        return manejadorPrograma.visitarBloque(nodo);
    }

    @Override
    public String visitarAsignacion(Asignacion nodo) {
        return manejadorPrograma.visitarAsignacion(nodo);
    }

    @Override
    public String visitarCasoSeleccion(CasoSeleccion nodo) {
        return manejadorPrograma.visitarCasoSeleccion(nodo);
    }

    @Override
    public String visitarCasoDefecto(CasoDefecto nodo) {
        return manejadorPrograma.visitarCasoDefecto(nodo);
    }

    @Override
    public String visitarValorPrimitivo(ValorPrimitivo nodo) {
        return manejadorPrograma.visitarValorPrimitivo(nodo);
    }

    @Override
    public String visitarListaExpresiones(ListaExpresiones nodo) {
        return manejadorPrograma.visitarListaExpresiones(nodo);
    }

    // ==================== ESTRUCTURAS ====================

    @Override
    public String visitarDefEstructura(DefEstructura nodo) {
        return manejadorEstructuras.visitarDefEstructura(nodo);
    }

    @Override
    public String visitarAtributoSimple(AtributoSimple nodo) {
        return manejadorEstructuras.visitarAtributoSimple(nodo);
    }

    @Override
    public String visitarAtributoArray(AtributoArray nodo) {
        return manejadorEstructuras.visitarAtributoArray(nodo);
    }

    // ==================== FUNCIONES ====================

    @Override
    public String visitarDefFuncionSinRetorno(DefFuncionSinRetorno nodo) {
        return manejadorFunciones.visitarDefFuncionSinRetorno(nodo);
    }

    @Override
    public String visitarDefFuncionConRetorno(DefFuncionConRetorno nodo) {
        return manejadorFunciones.visitarDefFuncionConRetorno(nodo);
    }

    @Override
    public String visitarParamSimple(ParamSimple nodo) {
        return manejadorFunciones.visitarParamSimple(nodo);
    }

    @Override
    public String visitarParamArray(ParamArray nodo) {
        return manejadorFunciones.visitarParamArray(nodo);
    }

    @Override
    public String visitarParamEstructura(ParamEstructura nodo) {
        return manejadorFunciones.visitarParamEstructura(nodo);
    }

    // ==================== INSTRUCCIONES ====================

    @Override
    public String visitarStmtDeclaracion(StmtDeclaracion nodo) {
        return manejadorDeclaraciones.visitarStmtDeclaracion(nodo);
    }

    @Override
    public String visitarStmtAsignacion(StmtAsignacion nodo) {
        return manejadorDeclaraciones.visitarStmtAsignacion(nodo);
    }

    @Override
    public String visitarStmtEstructuraLocal(StmtEstructuraLocal nodo) {
        return manejadorDeclaraciones.visitarStmtEstructuraLocal(nodo);
    }

    @Override
    public String visitarStmtCondicional(StmtCondicional nodo) {
        return manejadorFlujo.visitarStmtCondicional(nodo);
    }

    @Override
    public String visitarStmtSeleccion(StmtSeleccion nodo) {
        return manejadorFlujo.visitarStmtSeleccion(nodo);
    }

    @Override
    public String visitarStmtCiclo(StmtCiclo nodo) {
        return manejadorFlujo.visitarStmtCiclo(nodo);
    }

    @Override
    public String visitarStmtRetorno(StmtRetorno nodo) {
        return manejadorDeclaraciones.visitarStmtRetorno(nodo);
    }

    @Override
    public String visitarStmtContinuar(StmtContinuar nodo) {
        return manejadorDeclaraciones.visitarStmtContinuar(nodo);
    }

    @Override
    public String visitarStmtRomper(StmtRomper nodo) {
        return manejadorDeclaraciones.visitarStmtRomper(nodo);
    }

    @Override
    public String visitarStmtExpresion(StmtExpresion nodo) {
        return manejadorDeclaraciones.visitarStmtExpresion(nodo);
    }

    // ==================== DECLARACIONES ====================

    @Override
    public String visitarDeclConTipoYValor(DeclConTipoYValor nodo) {
        return manejadorDeclaraciones.visitarDeclConTipoYValor(nodo);
    }

    @Override
    public String visitarDeclArraySinValores(DeclArraySinValores nodo) {
        return manejadorDeclaraciones.visitarDeclArraySinValores(nodo);
    }

    @Override
    public String visitarDeclArrayConValores(DeclArrayConValores nodo) {
        return manejadorDeclaraciones.visitarDeclArrayConValores(nodo);
    }

    @Override
    public String visitarDeclMatriz(DeclMatriz nodo) {
        return manejadorDeclaraciones.visitarDeclMatriz(nodo);
    }

    @Override
    public String visitarDeclMatrizConValores(DeclMatrizConValores nodo) {
        return manejadorDeclaraciones.visitarDeclMatrizConValores(nodo);
    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public String visitarVarSimple(VarSimple nodo) {
        return manejadorDeclaraciones.visitarVarSimple(nodo);
    }

    @Override
    public String visitarVarArray(VarArray nodo) {
        return manejadorDeclaraciones.visitarVarArray(nodo);
    }

    @Override
    public String visitarVarMiembro(VarMiembro nodo) {
        return manejadorDeclaraciones.visitarVarMiembro(nodo);
    }

    // ==================== CONDICIONAL ====================

    @Override
    public String visitarStatementSi(StatementSi nodo) {
        return manejadorFlujo.visitarStatementSi(nodo);
    }

    // ==================== SELECCION ====================

    @Override
    public String visitarStatementElegir(StatementElegir nodo) {
        return manejadorFlujo.visitarStatementElegir(nodo);
    }

    // ==================== CICLOS ====================

    @Override
    public String visitarCicloPara(CicloPara nodo) {
        return manejadorFlujo.visitarCicloPara(nodo);
    }

    @Override
    public String visitarCicloMientras(CicloMientras nodo) {
        return manejadorFlujo.visitarCicloMientras(nodo);
    }

    @Override
    public String visitarCicloHacer(CicloHacer nodo) {
        return manejadorFlujo.visitarCicloHacer(nodo);
    }

    // ==================== INIT Y PASO DEL PARA ====================

    @Override
    public String visitarInitParaDecl(InitParaDecl nodo) {
        return manejadorFlujo.visitarInitParaDecl(nodo);
    }

    @Override
    public String visitarInitParaAsig(InitParaAsig nodo) {
        return manejadorFlujo.visitarInitParaAsig(nodo);
    }

    @Override
    public String visitarPasoParaExpr(PasoParaExpr nodo) {
        return manejadorFlujo.visitarPasoParaExpr(nodo);
    }

    @Override
    public String visitarPasoParaAsig(PasoParaAsig nodo) {
        return manejadorFlujo.visitarPasoParaAsig(nodo);
    }

    // ==================== EXPRESIONES ====================

    @Override
    public String visitarExprParentesis(ExprParentesis nodo) {
        return manejadorExpresiones.visitarExprParentesis(nodo);
    }

    @Override
    public String visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo) {
        return manejadorExpresiones.visitarExprLlamadaFuncion(nodo);
    }

    @Override
    public String visitarExprAccesoArray(ExprAccesoArray nodo) {
        return manejadorExpresiones.visitarExprAccesoArray(nodo);
    }

    @Override
    public String visitarExprAccesoMiembro(ExprAccesoMiembro nodo) {
        return manejadorExpresiones.visitarExprAccesoMiembro(nodo);
    }

    @Override
    public String visitarExprPostIncremento(ExprPostIncremento nodo) {
        return manejadorExpresiones.visitarExprPostIncremento(nodo);
    }

    @Override
    public String visitarExprPostDecremento(ExprPostDecremento nodo) {
        return manejadorExpresiones.visitarExprPostDecremento(nodo);
    }

    @Override
    public String visitarExprListaLiteral(ExprListaLiteral nodo) {
        return manejadorExpresiones.visitarExprListaLiteral(nodo);
    }

    @Override
    public String visitarExprNegativa(ExprNegativa nodo) {
        return manejadorExpresiones.visitarExprNegativa(nodo);
    }

    @Override
    public String visitarExprNegada(ExprNegada nodo) {
        return manejadorExpresiones.visitarExprNegada(nodo);
    }

    @Override
    public String visitarExprMultiplicacionDivision(ExprMultiplicacionDivision nodo) {
        return manejadorExpresiones.visitarExprMultiplicacionDivision(nodo);
    }

    @Override
    public String visitarExprSumaResta(ExprSumaResta nodo) {
        return manejadorExpresiones.visitarExprSumaResta(nodo);
    }

    @Override
    public String visitarExprRelacional(ExprRelacional nodo) {
        return manejadorExpresiones.visitarExprRelacional(nodo);
    }

    @Override
    public String visitarExprAnd(ExprAnd nodo) {
        return manejadorExpresiones.visitarExprAnd(nodo);
    }

    @Override
    public String visitarExprOr(ExprOr nodo) {
        return manejadorExpresiones.visitarExprOr(nodo);
    }

    @Override
    public String visitarExprPrimitivo(ExprPrimitivo nodo) {
        return manejadorExpresiones.visitarExprPrimitivo(nodo);
    }
}
