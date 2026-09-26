package org.jrg.analisis.zetariano.cuartetas;

import java.util.List;

import org.jrg.model.ast.zetariano.Bloque;
import org.jrg.model.ast.zetariano.CasoDefault;
import org.jrg.model.ast.zetariano.CasoSwitch;
import org.jrg.model.ast.zetariano.ListaExpresiones;
import org.jrg.model.ast.zetariano.Parametros;
import org.jrg.model.ast.zetariano.Programa;
import org.jrg.model.ast.zetariano.TipoDato;
import org.jrg.model.ast.zetariano.ValorPrimitivo;
import org.jrg.model.ast.zetariano.asignacion.AsignacionCompuesta;
import org.jrg.model.ast.zetariano.asignacion.AsignacionSimple;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoArray;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoSimple;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;
import org.jrg.model.ast.zetariano.ciclo.CicloDoWhile;
import org.jrg.model.ast.zetariano.ciclo.CicloFor;
import org.jrg.model.ast.zetariano.ciclo.CicloWhile;
import org.jrg.model.ast.zetariano.condicional.StatementIf;
import org.jrg.model.ast.zetariano.constructor.DefConstructor;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConListaLiteral;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConMatrizLiteral;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConTipo;
import org.jrg.model.ast.zetariano.definicion_clase.DefClase;
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
import org.jrg.model.ast.zetariano.parametro.ParamArray;
import org.jrg.model.ast.zetariano.parametro.ParamSimple;
import org.jrg.model.ast.zetariano.paso_for.PasoForAsig;
import org.jrg.model.ast.zetariano.paso_for.PasoForExpr;
import org.jrg.model.ast.zetariano.seleccion.StatementSwitch;
import org.jrg.model.ast.zetariano.variable_asignable.VarArray;
import org.jrg.model.ast.zetariano.variable_asignable.VarMiembro;
import org.jrg.model.ast.zetariano.variable_asignable.VarSimple;
import org.jrg.model.cuarteta.Cuarteta;

// generador de cuartetas para el lenguaje Zetariano que delega en manejadoras
public class GeneradorCuartetasZetariano implements ZetarianoAstVisitor<String> {

    // estado compartido de la generacion
    private final ContextoCuartetasZetariano ctx;
    // manejadora de programa y base
    private final ManejadorProgramaZetariano manejadorPrograma;
    // manejadora de clases constructores y metodos
    private final ManejadorClasesZetariano manejadorClases;
    // manejadora de declaraciones y asignaciones
    private final ManejadorDeclaracionesZetariano manejadorDeclaraciones;
    // manejadora de flujo seleccion y ciclos
    private final ManejadorFlujoZetariano manejadorFlujo;
    // manejadora de expresiones
    private final ManejadorExpresionesZetariano manejadorExpresiones;

    /**
     * Crear el generador de cuartetas para el lenguaje Zetariano.
     */
    public GeneradorCuartetasZetariano() {
        // inicializar el contexto compartido
        this.ctx = new ContextoCuartetasZetariano();
        // crear las manejadoras con el contexto y este generador
        this.manejadorPrograma = new ManejadorProgramaZetariano(ctx, this);
        this.manejadorClases = new ManejadorClasesZetariano(ctx, this);
        this.manejadorDeclaraciones = new ManejadorDeclaracionesZetariano(ctx, this);
        this.manejadorFlujo = new ManejadorFlujoZetariano(ctx, this);
        this.manejadorExpresiones = new ManejadorExpresionesZetariano(ctx, this);
    }

    /**
     * Obtener la lista de cuartetas generadas.
     */
    public List<Cuarteta> getCuartetas() {
        return ctx.getCuartetas();
    }

    // registrar el tipo de una variable declarada
    public void registrarTipoVariable(String nombre, String tipo) {
        // anotarlo en el contexto
        ctx.registrarTipoVariable(nombre, tipo);
    }

    // ==================== PROGRAMA Y BASE ====================

    @Override
    public String visitarPrograma(Programa nodo) {
        return manejadorPrograma.visitarPrograma(nodo);
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
    public String visitarBloque(Bloque nodo) {
        return manejadorPrograma.visitarBloque(nodo);
    }

    @Override
    public String visitarCasoSwitch(CasoSwitch nodo) {
        return manejadorPrograma.visitarCasoSwitch(nodo);
    }

    @Override
    public String visitarCasoDefault(CasoDefault nodo) {
        return manejadorPrograma.visitarCasoDefault(nodo);
    }

    @Override
    public String visitarValorPrimitivo(ValorPrimitivo nodo) {
        return manejadorPrograma.visitarValorPrimitivo(nodo);
    }

    @Override
    public String visitarListaExpresiones(ListaExpresiones nodo) {
        return manejadorPrograma.visitarListaExpresiones(nodo);
    }

    // ==================== CLASE Y MIEMBROS ====================

    @Override
    public String visitarDefClase(DefClase nodo) {
        return manejadorClases.visitarDefClase(nodo);
    }

    @Override
    public String visitarMiembroAtributo(MiembroAtributo nodo) {
        return manejadorClases.visitarMiembroAtributo(nodo);
    }

    @Override
    public String visitarMiembroConstructor(MiembroConstructor nodo) {
        return manejadorClases.visitarMiembroConstructor(nodo);
    }

    @Override
    public String visitarMiembroMetodo(MiembroMetodo nodo) {
        return manejadorClases.visitarMiembroMetodo(nodo);
    }

    @Override
    public String visitarAtributoSimple(AtributoSimple nodo) {
        return manejadorClases.visitarAtributoSimple(nodo);
    }

    @Override
    public String visitarAtributoArray(AtributoArray nodo) {
        return manejadorClases.visitarAtributoArray(nodo);
    }

    // ==================== CONSTRUCTOR Y METODOS ====================

    @Override
    public String visitarDefConstructor(DefConstructor nodo) {
        return manejadorClases.visitarDefConstructor(nodo);
    }

    @Override
    public String visitarMetodoSinRetorno(MetodoSinRetorno nodo) {
        return manejadorClases.visitarMetodoSinRetorno(nodo);
    }

    @Override
    public String visitarMetodoConRetorno(MetodoConRetorno nodo) {
        return manejadorClases.visitarMetodoConRetorno(nodo);
    }

    @Override
    public String visitarParamSimple(ParamSimple nodo) {
        return manejadorClases.visitarParamSimple(nodo);
    }

    @Override
    public String visitarParamArray(ParamArray nodo) {
        return manejadorClases.visitarParamArray(nodo);
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
    public String visitarStmtReturn(StmtReturn nodo) {
        return manejadorDeclaraciones.visitarStmtReturn(nodo);
    }

    @Override
    public String visitarStmtBreak(StmtBreak nodo) {
        return manejadorDeclaraciones.visitarStmtBreak(nodo);
    }

    @Override
    public String visitarStmtContinue(StmtContinue nodo) {
        return manejadorDeclaraciones.visitarStmtContinue(nodo);
    }

    @Override
    public String visitarStmtExpresion(StmtExpresion nodo) {
        return manejadorDeclaraciones.visitarStmtExpresion(nodo);
    }

    // ==================== DECLARACIONES ====================

    @Override
    public String visitarDeclConTipo(DeclConTipo nodo) {
        return manejadorDeclaraciones.visitarDeclConTipo(nodo);
    }

    @Override
    public String visitarDeclConListaLiteral(DeclConListaLiteral nodo) {
        return manejadorDeclaraciones.visitarDeclConListaLiteral(nodo);
    }

    @Override
    public String visitarDeclConMatrizLiteral(DeclConMatrizLiteral nodo) {
        return manejadorDeclaraciones.visitarDeclConMatrizLiteral(nodo);
    }

    // ==================== ASIGNACIONES ====================

    @Override
    public String visitarAsignacionSimple(AsignacionSimple nodo) {
        return manejadorDeclaraciones.visitarAsignacionSimple(nodo);
    }

    @Override
    public String visitarAsignacionCompuesta(AsignacionCompuesta nodo) {
        return manejadorDeclaraciones.visitarAsignacionCompuesta(nodo);
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
    public String visitarStatementIf(StatementIf nodo) {
        return manejadorFlujo.visitarStatementIf(nodo);
    }

    // ==================== SELECCION ====================

    @Override
    public String visitarStatementSwitch(StatementSwitch nodo) {
        return manejadorFlujo.visitarStatementSwitch(nodo);
    }

    // ==================== CICLOS ====================

    @Override
    public String visitarCicloFor(CicloFor nodo) {
        return manejadorFlujo.visitarCicloFor(nodo);
    }

    @Override
    public String visitarCicloWhile(CicloWhile nodo) {
        return manejadorFlujo.visitarCicloWhile(nodo);
    }

    @Override
    public String visitarCicloDoWhile(CicloDoWhile nodo) {
        return manejadorFlujo.visitarCicloDoWhile(nodo);
    }

    // ==================== INIT Y PASO DEL FOR ====================

    @Override
    public String visitarInitForDecl(InitForDecl nodo) {
        return manejadorFlujo.visitarInitForDecl(nodo);
    }

    @Override
    public String visitarInitForAsig(InitForAsig nodo) {
        return manejadorFlujo.visitarInitForAsig(nodo);
    }

    @Override
    public String visitarPasoForExpr(PasoForExpr nodo) {
        return manejadorFlujo.visitarPasoForExpr(nodo);
    }

    @Override
    public String visitarPasoForAsig(PasoForAsig nodo) {
        return manejadorFlujo.visitarPasoForAsig(nodo);
    }

    // ==================== EXPRESIONES ====================

    @Override
    public String visitarExprParentesis(ExprParentesis nodo) {
        return manejadorExpresiones.visitarExprParentesis(nodo);
    }

    @Override
    public String visitarExprInstanciaObjeto(ExprInstanciaObjeto nodo) {
        return manejadorExpresiones.visitarExprInstanciaObjeto(nodo);
    }

    @Override
    public String visitarExprInstanciaArreglo(ExprInstanciaArreglo nodo) {
        return manejadorExpresiones.visitarExprInstanciaArreglo(nodo);
    }

    @Override
    public String visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo) {
        return manejadorExpresiones.visitarExprLlamadaFuncion(nodo);
    }

    @Override
    public String visitarExprLlamadaMetodo(ExprLlamadaMetodo nodo) {
        return manejadorExpresiones.visitarExprLlamadaMetodo(nodo);
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
    public String visitarExprNegativa(ExprNegativa nodo) {
        return manejadorExpresiones.visitarExprNegativa(nodo);
    }

    @Override
    public String visitarExprNegada(ExprNegada nodo) {
        return manejadorExpresiones.visitarExprNegada(nodo);
    }

    @Override
    public String visitarExprMultiplicacionDivisionModulo(ExprMultiplicacionDivisionModulo nodo) {
        return manejadorExpresiones.visitarExprMultiplicacionDivisionModulo(nodo);
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
    public String visitarExprTernario(ExprTernario nodo) {
        return manejadorExpresiones.visitarExprTernario(nodo);
    }

    @Override
    public String visitarExprPrimitivo(ExprPrimitivo nodo) {
        return manejadorExpresiones.visitarExprPrimitivo(nodo);
    }
}
