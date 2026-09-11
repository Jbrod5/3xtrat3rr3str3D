// Generated from org/jrg/antlrBase/zetariano/Zetariano.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.zetariano;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ZetarianoParser}.
 */
public interface ZetarianoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(ZetarianoParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(ZetarianoParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defClase}
	 * labeled alternative in {@link ZetarianoParser#definicion_clase}.
	 * @param ctx the parse tree
	 */
	void enterDefClase(ZetarianoParser.DefClaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defClase}
	 * labeled alternative in {@link ZetarianoParser#definicion_clase}.
	 * @param ctx the parse tree
	 */
	void exitDefClase(ZetarianoParser.DefClaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code miembroAtributo}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void enterMiembroAtributo(ZetarianoParser.MiembroAtributoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code miembroAtributo}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void exitMiembroAtributo(ZetarianoParser.MiembroAtributoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code miembroConstructor}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void enterMiembroConstructor(ZetarianoParser.MiembroConstructorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code miembroConstructor}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void exitMiembroConstructor(ZetarianoParser.MiembroConstructorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code miembroMetodo}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void enterMiembroMetodo(ZetarianoParser.MiembroMetodoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code miembroMetodo}
	 * labeled alternative in {@link ZetarianoParser#miembro_clase}.
	 * @param ctx the parse tree
	 */
	void exitMiembroMetodo(ZetarianoParser.MiembroMetodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void enterTipo_dato(ZetarianoParser.Tipo_datoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void exitTipo_dato(ZetarianoParser.Tipo_datoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atributoSimple}
	 * labeled alternative in {@link ZetarianoParser#atributo_clase}.
	 * @param ctx the parse tree
	 */
	void enterAtributoSimple(ZetarianoParser.AtributoSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atributoSimple}
	 * labeled alternative in {@link ZetarianoParser#atributo_clase}.
	 * @param ctx the parse tree
	 */
	void exitAtributoSimple(ZetarianoParser.AtributoSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atributoArray}
	 * labeled alternative in {@link ZetarianoParser#atributo_clase}.
	 * @param ctx the parse tree
	 */
	void enterAtributoArray(ZetarianoParser.AtributoArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atributoArray}
	 * labeled alternative in {@link ZetarianoParser#atributo_clase}.
	 * @param ctx the parse tree
	 */
	void exitAtributoArray(ZetarianoParser.AtributoArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defConstructor}
	 * labeled alternative in {@link ZetarianoParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterDefConstructor(ZetarianoParser.DefConstructorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defConstructor}
	 * labeled alternative in {@link ZetarianoParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitDefConstructor(ZetarianoParser.DefConstructorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code metodoSinRetorno}
	 * labeled alternative in {@link ZetarianoParser#metodo}.
	 * @param ctx the parse tree
	 */
	void enterMetodoSinRetorno(ZetarianoParser.MetodoSinRetornoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code metodoSinRetorno}
	 * labeled alternative in {@link ZetarianoParser#metodo}.
	 * @param ctx the parse tree
	 */
	void exitMetodoSinRetorno(ZetarianoParser.MetodoSinRetornoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code metodoConRetorno}
	 * labeled alternative in {@link ZetarianoParser#metodo}.
	 * @param ctx the parse tree
	 */
	void enterMetodoConRetorno(ZetarianoParser.MetodoConRetornoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code metodoConRetorno}
	 * labeled alternative in {@link ZetarianoParser#metodo}.
	 * @param ctx the parse tree
	 */
	void exitMetodoConRetorno(ZetarianoParser.MetodoConRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(ZetarianoParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(ZetarianoParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paramSimple}
	 * labeled alternative in {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParamSimple(ZetarianoParser.ParamSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramSimple}
	 * labeled alternative in {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParamSimple(ZetarianoParser.ParamSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paramArray}
	 * labeled alternative in {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParamArray(ZetarianoParser.ParamArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramArray}
	 * labeled alternative in {@link ZetarianoParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParamArray(ZetarianoParser.ParamArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtDeclaracion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtDeclaracion(ZetarianoParser.StmtDeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtDeclaracion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtDeclaracion(ZetarianoParser.StmtDeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtAsignacion(ZetarianoParser.StmtAsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtAsignacion(ZetarianoParser.StmtAsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtCondicional(ZetarianoParser.StmtCondicionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtCondicional(ZetarianoParser.StmtCondicionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtSeleccion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtSeleccion(ZetarianoParser.StmtSeleccionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtSeleccion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtSeleccion(ZetarianoParser.StmtSeleccionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtCiclo(ZetarianoParser.StmtCicloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtCiclo(ZetarianoParser.StmtCicloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtReturn}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtReturn(ZetarianoParser.StmtReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtReturn}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtReturn(ZetarianoParser.StmtReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtBreak}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtBreak(ZetarianoParser.StmtBreakContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtBreak}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtBreak(ZetarianoParser.StmtBreakContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtContinue}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtContinue(ZetarianoParser.StmtContinueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtContinue}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtContinue(ZetarianoParser.StmtContinueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtExpresion(ZetarianoParser.StmtExpresionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link ZetarianoParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtExpresion(ZetarianoParser.StmtExpresionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declConTipo}
	 * labeled alternative in {@link ZetarianoParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclConTipo(ZetarianoParser.DeclConTipoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declConTipo}
	 * labeled alternative in {@link ZetarianoParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclConTipo(ZetarianoParser.DeclConTipoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declConListaLiteral}
	 * labeled alternative in {@link ZetarianoParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclConListaLiteral(ZetarianoParser.DeclConListaLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declConListaLiteral}
	 * labeled alternative in {@link ZetarianoParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclConListaLiteral(ZetarianoParser.DeclConListaLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code asignacionSimple}
	 * labeled alternative in {@link ZetarianoParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionSimple(ZetarianoParser.AsignacionSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code asignacionSimple}
	 * labeled alternative in {@link ZetarianoParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionSimple(ZetarianoParser.AsignacionSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code asignacionCompuesta}
	 * labeled alternative in {@link ZetarianoParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionCompuesta(ZetarianoParser.AsignacionCompuestaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code asignacionCompuesta}
	 * labeled alternative in {@link ZetarianoParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionCompuesta(ZetarianoParser.AsignacionCompuestaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varSimple}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterVarSimple(ZetarianoParser.VarSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varSimple}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitVarSimple(ZetarianoParser.VarSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varMiembro}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterVarMiembro(ZetarianoParser.VarMiembroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varMiembro}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitVarMiembro(ZetarianoParser.VarMiembroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varArray}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterVarArray(ZetarianoParser.VarArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varArray}
	 * labeled alternative in {@link ZetarianoParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitVarArray(ZetarianoParser.VarArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementIf}
	 * labeled alternative in {@link ZetarianoParser#condicional}.
	 * @param ctx the parse tree
	 */
	void enterStatementIf(ZetarianoParser.StatementIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementIf}
	 * labeled alternative in {@link ZetarianoParser#condicional}.
	 * @param ctx the parse tree
	 */
	void exitStatementIf(ZetarianoParser.StatementIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(ZetarianoParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementSwitch}
	 * labeled alternative in {@link ZetarianoParser#seleccion}.
	 * @param ctx the parse tree
	 */
	void enterStatementSwitch(ZetarianoParser.StatementSwitchContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementSwitch}
	 * labeled alternative in {@link ZetarianoParser#seleccion}.
	 * @param ctx the parse tree
	 */
	void exitStatementSwitch(ZetarianoParser.StatementSwitchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#caso_switch}.
	 * @param ctx the parse tree
	 */
	void enterCaso_switch(ZetarianoParser.Caso_switchContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#caso_switch}.
	 * @param ctx the parse tree
	 */
	void exitCaso_switch(ZetarianoParser.Caso_switchContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#caso_default}.
	 * @param ctx the parse tree
	 */
	void enterCaso_default(ZetarianoParser.Caso_defaultContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#caso_default}.
	 * @param ctx the parse tree
	 */
	void exitCaso_default(ZetarianoParser.Caso_defaultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloFor}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloFor(ZetarianoParser.CicloForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloFor}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloFor(ZetarianoParser.CicloForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloWhile}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloWhile(ZetarianoParser.CicloWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloWhile}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloWhile(ZetarianoParser.CicloWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloDoWhile}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloDoWhile(ZetarianoParser.CicloDoWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloDoWhile}
	 * labeled alternative in {@link ZetarianoParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloDoWhile(ZetarianoParser.CicloDoWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initForDecl}
	 * labeled alternative in {@link ZetarianoParser#init_for}.
	 * @param ctx the parse tree
	 */
	void enterInitForDecl(ZetarianoParser.InitForDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initForDecl}
	 * labeled alternative in {@link ZetarianoParser#init_for}.
	 * @param ctx the parse tree
	 */
	void exitInitForDecl(ZetarianoParser.InitForDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initForAsig}
	 * labeled alternative in {@link ZetarianoParser#init_for}.
	 * @param ctx the parse tree
	 */
	void enterInitForAsig(ZetarianoParser.InitForAsigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initForAsig}
	 * labeled alternative in {@link ZetarianoParser#init_for}.
	 * @param ctx the parse tree
	 */
	void exitInitForAsig(ZetarianoParser.InitForAsigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pasoForExpr}
	 * labeled alternative in {@link ZetarianoParser#paso_for}.
	 * @param ctx the parse tree
	 */
	void enterPasoForExpr(ZetarianoParser.PasoForExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pasoForExpr}
	 * labeled alternative in {@link ZetarianoParser#paso_for}.
	 * @param ctx the parse tree
	 */
	void exitPasoForExpr(ZetarianoParser.PasoForExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pasoForAsig}
	 * labeled alternative in {@link ZetarianoParser#paso_for}.
	 * @param ctx the parse tree
	 */
	void enterPasoForAsig(ZetarianoParser.PasoForAsigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pasoForAsig}
	 * labeled alternative in {@link ZetarianoParser#paso_for}.
	 * @param ctx the parse tree
	 */
	void exitPasoForAsig(ZetarianoParser.PasoForAsigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNegativa(ZetarianoParser.ExprNegativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNegativa(ZetarianoParser.ExprNegativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprMultiplicacionDivisionModulo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMultiplicacionDivisionModulo(ZetarianoParser.ExprMultiplicacionDivisionModuloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprMultiplicacionDivisionModulo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMultiplicacionDivisionModulo(ZetarianoParser.ExprMultiplicacionDivisionModuloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprRelacional(ZetarianoParser.ExprRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprRelacional(ZetarianoParser.ExprRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprParentesis(ZetarianoParser.ExprParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprParentesis(ZetarianoParser.ExprParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostIncremento(ZetarianoParser.ExprPostIncrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostIncremento(ZetarianoParser.ExprPostIncrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLlamadaMetodo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprSumaResta(ZetarianoParser.ExprSumaRestaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprSumaResta(ZetarianoParser.ExprSumaRestaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(ZetarianoParser.ExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(ZetarianoParser.ExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprInstanciaArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprInstanciaArreglo(ZetarianoParser.ExprInstanciaArregloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprInstanciaArreglo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprInstanciaArreglo(ZetarianoParser.ExprInstanciaArregloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNegada(ZetarianoParser.ExprNegadaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNegada(ZetarianoParser.ExprNegadaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPrimitivo(ZetarianoParser.ExprPrimitivoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPrimitivo(ZetarianoParser.ExprPrimitivoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostDecremento(ZetarianoParser.ExprPostDecrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostDecremento(ZetarianoParser.ExprPostDecrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(ZetarianoParser.ExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(ZetarianoParser.ExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprTernario}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprTernario(ZetarianoParser.ExprTernarioContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprTernario}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprTernario(ZetarianoParser.ExprTernarioContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaFuncion(ZetarianoParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaFuncion(ZetarianoParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAccesoMiembro}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAccesoMiembro}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprInstanciaObjeto}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprInstanciaObjeto(ZetarianoParser.ExprInstanciaObjetoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprInstanciaObjeto}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprInstanciaObjeto(ZetarianoParser.ExprInstanciaObjetoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAccesoArray}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoArray(ZetarianoParser.ExprAccesoArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAccesoArray}
	 * labeled alternative in {@link ZetarianoParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoArray(ZetarianoParser.ExprAccesoArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#valor_primitivo}.
	 * @param ctx the parse tree
	 */
	void enterValor_primitivo(ZetarianoParser.Valor_primitivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#valor_primitivo}.
	 * @param ctx the parse tree
	 */
	void exitValor_primitivo(ZetarianoParser.Valor_primitivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ZetarianoParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void enterLista_expresiones(ZetarianoParser.Lista_expresionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ZetarianoParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void exitLista_expresiones(ZetarianoParser.Lista_expresionesContext ctx);
}