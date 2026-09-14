package org.jrg.model.ast.zetariano.base;

import org.jrg.model.ast.zetariano.*;
import org.jrg.model.ast.zetariano.asignacion.*;
import org.jrg.model.ast.zetariano.atributo_clase.*;
import org.jrg.model.ast.zetariano.ciclo.*;
import org.jrg.model.ast.zetariano.condicional.*;
import org.jrg.model.ast.zetariano.constructor.*;
import org.jrg.model.ast.zetariano.declaracion_variable.*;
import org.jrg.model.ast.zetariano.definicion_clase.*;
import org.jrg.model.ast.zetariano.expresion.*;
import org.jrg.model.ast.zetariano.init_for.*;
import org.jrg.model.ast.zetariano.instruccion.*;
import org.jrg.model.ast.zetariano.miembro_clase.*;
import org.jrg.model.ast.zetariano.metodo.*;
import org.jrg.model.ast.zetariano.paso_for.*;
import org.jrg.model.ast.zetariano.parametro.*;
import org.jrg.model.ast.zetariano.seleccion.*;
import org.jrg.model.ast.zetariano.variable_asignable.*;

public interface ZetarianoAstVisitor<T> {

    T visitarPrograma(Programa nodo);
    T visitarTipoDato(TipoDato nodo);
    T visitarParametros(Parametros nodo);
    T visitarBloque(Bloque nodo);
    T visitarCasoSwitch(CasoSwitch nodo);
    T visitarCasoDefault(CasoDefault nodo);
    T visitarValorPrimitivo(ValorPrimitivo nodo);
    T visitarListaExpresiones(ListaExpresiones nodo);

    T visitarDefClase(DefClase nodo);

    T visitarMiembroAtributo(MiembroAtributo nodo);
    T visitarMiembroConstructor(MiembroConstructor nodo);
    T visitarMiembroMetodo(MiembroMetodo nodo);

    T visitarAtributoSimple(AtributoSimple nodo);
    T visitarAtributoArray(AtributoArray nodo);

    T visitarDefConstructor(DefConstructor nodo);

    T visitarMetodoSinRetorno(MetodoSinRetorno nodo);
    T visitarMetodoConRetorno(MetodoConRetorno nodo);

    T visitarParamSimple(ParamSimple nodo);
    T visitarParamArray(ParamArray nodo);

    T visitarStmtDeclaracion(StmtDeclaracion nodo);
    T visitarStmtAsignacion(StmtAsignacion nodo);
    T visitarStmtCondicional(StmtCondicional nodo);
    T visitarStmtSeleccion(StmtSeleccion nodo);
    T visitarStmtCiclo(StmtCiclo nodo);
    T visitarStmtReturn(StmtReturn nodo);
    T visitarStmtBreak(StmtBreak nodo);
    T visitarStmtContinue(StmtContinue nodo);
    T visitarStmtExpresion(StmtExpresion nodo);

    T visitarDeclConTipo(DeclConTipo nodo);
    T visitarDeclConListaLiteral(DeclConListaLiteral nodo);

    T visitarAsignacionSimple(AsignacionSimple nodo);
    T visitarAsignacionCompuesta(AsignacionCompuesta nodo);

    T visitarVarSimple(VarSimple nodo);
    T visitarVarArray(VarArray nodo);
    T visitarVarMiembro(VarMiembro nodo);

    T visitarStatementIf(StatementIf nodo);

    T visitarStatementSwitch(StatementSwitch nodo);

    T visitarCicloFor(CicloFor nodo);
    T visitarCicloWhile(CicloWhile nodo);
    T visitarCicloDoWhile(CicloDoWhile nodo);

    T visitarInitForDecl(InitForDecl nodo);
    T visitarInitForAsig(InitForAsig nodo);

    T visitarPasoForExpr(PasoForExpr nodo);
    T visitarPasoForAsig(PasoForAsig nodo);

    T visitarExprParentesis(ExprParentesis nodo);
    T visitarExprInstanciaObjeto(ExprInstanciaObjeto nodo);
    T visitarExprInstanciaArreglo(ExprInstanciaArreglo nodo);
    T visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo);
    T visitarExprLlamadaMetodo(ExprLlamadaMetodo nodo);
    T visitarExprAccesoArray(ExprAccesoArray nodo);
    T visitarExprAccesoMiembro(ExprAccesoMiembro nodo);
    T visitarExprPostIncremento(ExprPostIncremento nodo);
    T visitarExprPostDecremento(ExprPostDecremento nodo);
    T visitarExprNegativa(ExprNegativa nodo);
    T visitarExprNegada(ExprNegada nodo);
    T visitarExprMultiplicacionDivisionModulo(ExprMultiplicacionDivisionModulo nodo);
    T visitarExprSumaResta(ExprSumaResta nodo);
    T visitarExprRelacional(ExprRelacional nodo);
    T visitarExprAnd(ExprAnd nodo);
    T visitarExprOr(ExprOr nodo);
    T visitarExprTernario(ExprTernario nodo);
    T visitarExprPrimitivo(ExprPrimitivo nodo);
}
