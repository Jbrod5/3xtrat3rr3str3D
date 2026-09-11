// Generated from org/jrg/antlrBase/zetariano/Zetariano.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.zetariano;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ZetarianoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ZetarianoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(ZetarianoParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defClase}
	 * labeled alternative in {@link ZetarianoParser#definicion_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefClase(ZetarianoParser.DefClaseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code miembroAtributo}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembroAtributo(ZetarianoParser.MiembroAtributoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code miembroConstructor}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembroConstructor(ZetarianoParser.MiembroConstructorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code miembroMetodo}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMiembroMetodo(ZetarianoParser.MiembroMetodoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#tipo_dato}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_dato(ZetarianoParser.Tipo_datoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atributoSimple}
	 * labeled alternative in {@link ZetarianoParser#atributo_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributoSimple(ZetarianoParser.AtributoSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atributoArray}
	 * labeled alternative in {@link ZetarianoParser#atributo_clase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributoArray(ZetarianoParser.AtributoArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defConstructor}
	 * labeled alternative in {@link ZetarianoParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefConstructor(ZetarianoParser.DefConstructorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code metodoSinRetorno}
	 * labeled alternative in {@link ZetarianoParser#metodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetodoSinRetorno(ZetarianoParser.MetodoSinRetornoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code metodoConRetorno}
	 * labeled alternative in {@link ZetarianoParser#metodo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMetodoConRetorno(ZetarianoParser.MetodoConRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(ZetarianoParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramSimple}
	 * labeled alternative in {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamSimple(ZetarianoParser.ParamSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramArray}
	 * labeled alternative in {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamArray(ZetarianoParser.ParamArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtDeclaracion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtDeclaracion(ZetarianoParser.StmtDeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAsignacion(ZetarianoParser.StmtAsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCondicional(ZetarianoParser.StmtCondicionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtSeleccion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtSeleccion(ZetarianoParser.StmtSeleccionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCiclo(ZetarianoParser.StmtCicloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtReturn}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtReturn(ZetarianoParser.StmtReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtBreak}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtBreak(ZetarianoParser.StmtBreakContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtContinue}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtContinue(ZetarianoParser.StmtContinueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtExpresion(ZetarianoParser.StmtExpresionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declConTipo}
	 * labeled alternative in {@link ZetarianoParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclConTipo(ZetarianoParser.DeclConTipoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declConListaLiteral}
	 * labeled alternative in {@link ZetarianoParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclConListaLiteral(ZetarianoParser.DeclConListaLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asignacionSimple}
	 * labeled alternative in {@link ZetarianoParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionSimple(ZetarianoParser.AsignacionSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asignacionCompuesta}
	 * labeled alternative in {@link ZetarianoParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionCompuesta(ZetarianoParser.AsignacionCompuestaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varSimple}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarSimple(ZetarianoParser.VarSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varMiembro}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarMiembro(ZetarianoParser.VarMiembroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varArray}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarArray(ZetarianoParser.VarArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementIf}
	 * labeled alternative in {@link ZetarianoParser#condicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementIf(ZetarianoParser.StatementIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementSwitch}
	 * labeled alternative in {@link ZetarianoParser#seleccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementSwitch(ZetarianoParser.StatementSwitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#caso_switch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso_switch(ZetarianoParser.Caso_switchContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#caso_default}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso_default(ZetarianoParser.Caso_defaultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloFor}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloFor(ZetarianoParser.CicloForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloWhile}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloWhile(ZetarianoParser.CicloWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloDoWhile}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloDoWhile(ZetarianoParser.CicloDoWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code initForDecl}
	 * labeled alternative in {@link ZetarianoParser#init_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitForDecl(ZetarianoParser.InitForDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code initForAsig}
	 * labeled alternative in {@link ZetarianoParser#init_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitForAsig(ZetarianoParser.InitForAsigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pasoForExpr}
	 * labeled alternative in {@link ZetarianoParser#paso_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasoForExpr(ZetarianoParser.PasoForExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pasoForAsig}
	 * labeled alternative in {@link ZetarianoParser#paso_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasoForAsig(ZetarianoParser.PasoForAsigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNegativa(ZetarianoParser.ExprNegativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMultiplicacionDivisionModulo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultiplicacionDivisionModulo(ZetarianoParser.ExprMultiplicacionDivisionModuloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRelacional(ZetarianoParser.ExprRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParentesis(ZetarianoParser.ExprParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostIncremento(ZetarianoParser.ExprPostIncrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSumaResta(ZetarianoParser.ExprSumaRestaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(ZetarianoParser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprInstanciaArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInstanciaArreglo(ZetarianoParser.ExprInstanciaArregloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNegada(ZetarianoParser.ExprNegadaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrimitivo(ZetarianoParser.ExprPrimitivoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostDecremento(ZetarianoParser.ExprPostDecrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(ZetarianoParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprTernario}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprTernario(ZetarianoParser.ExprTernarioContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaFuncion(ZetarianoParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAccesoMiembro}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprInstanciaObjeto}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInstanciaObjeto(ZetarianoParser.ExprInstanciaObjetoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAccesoArray}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoArray(ZetarianoParser.ExprAccesoArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#valor_primitivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_primitivo(ZetarianoParser.Valor_primitivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ZetarianoParser#lista_expresiones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_expresiones(ZetarianoParser.Lista_expresionesContext ctx);
}