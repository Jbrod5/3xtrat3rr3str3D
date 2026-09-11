// Generated from org/jrg/antlrBase/yLenguaje/YLenguaje.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.yLenguaje;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link YLenguajeParser}.
 */
public interface YLenguajeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(YLenguajeParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(YLenguajeParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#seccion_estructuras}.
	 * @param ctx the parse tree
	 */
	void enterSeccion_estructuras(YLenguajeParser.Seccion_estructurasContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#seccion_estructuras}.
	 * @param ctx the parse tree
	 */
	void exitSeccion_estructuras(YLenguajeParser.Seccion_estructurasContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#seccion_funciones}.
	 * @param ctx the parse tree
	 */
	void enterSeccion_funciones(YLenguajeParser.Seccion_funcionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#seccion_funciones}.
	 * @param ctx the parse tree
	 */
	void exitSeccion_funciones(YLenguajeParser.Seccion_funcionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void enterTipo_dato(YLenguajeParser.Tipo_datoContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void exitTipo_dato(YLenguajeParser.Tipo_datoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defEstructura}
	 * labeled alternative in {@link YLenguajeParser#definicion_struct}.
	 * @param ctx the parse tree
	 */
	void enterDefEstructura(YLenguajeParser.DefEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defEstructura}
	 * labeled alternative in {@link YLenguajeParser#definicion_struct}.
	 * @param ctx the parse tree
	 */
	void exitDefEstructura(YLenguajeParser.DefEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atributoSimple}
	 * labeled alternative in {@link YLenguajeParser#atributo_struct}.
	 * @param ctx the parse tree
	 */
	void enterAtributoSimple(YLenguajeParser.AtributoSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atributoSimple}
	 * labeled alternative in {@link YLenguajeParser#atributo_struct}.
	 * @param ctx the parse tree
	 */
	void exitAtributoSimple(YLenguajeParser.AtributoSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atributoArray}
	 * labeled alternative in {@link YLenguajeParser#atributo_struct}.
	 * @param ctx the parse tree
	 */
	void enterAtributoArray(YLenguajeParser.AtributoArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atributoArray}
	 * labeled alternative in {@link YLenguajeParser#atributo_struct}.
	 * @param ctx the parse tree
	 */
	void exitAtributoArray(YLenguajeParser.AtributoArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defFuncionSinRetorno}
	 * labeled alternative in {@link YLenguajeParser#definicion_funcion}.
	 * @param ctx the parse tree
	 */
	void enterDefFuncionSinRetorno(YLenguajeParser.DefFuncionSinRetornoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defFuncionSinRetorno}
	 * labeled alternative in {@link YLenguajeParser#definicion_funcion}.
	 * @param ctx the parse tree
	 */
	void exitDefFuncionSinRetorno(YLenguajeParser.DefFuncionSinRetornoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code defFuncionConRetorno}
	 * labeled alternative in {@link YLenguajeParser#definicion_funcion}.
	 * @param ctx the parse tree
	 */
	void enterDefFuncionConRetorno(YLenguajeParser.DefFuncionConRetornoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code defFuncionConRetorno}
	 * labeled alternative in {@link YLenguajeParser#definicion_funcion}.
	 * @param ctx the parse tree
	 */
	void exitDefFuncionConRetorno(YLenguajeParser.DefFuncionConRetornoContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#parametros}.
	 * @param ctx the parse tree
	 */
	void enterParametros(YLenguajeParser.ParametrosContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#parametros}.
	 * @param ctx the parse tree
	 */
	void exitParametros(YLenguajeParser.ParametrosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paramSimple}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParamSimple(YLenguajeParser.ParamSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramSimple}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParamSimple(YLenguajeParser.ParamSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paramArray}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParamArray(YLenguajeParser.ParamArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramArray}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParamArray(YLenguajeParser.ParamArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paramEstructura}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 */
	void enterParamEstructura(YLenguajeParser.ParamEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paramEstructura}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 */
	void exitParamEstructura(YLenguajeParser.ParamEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#cuerpo_funcion}.
	 * @param ctx the parse tree
	 */
	void enterCuerpo_funcion(YLenguajeParser.Cuerpo_funcionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#cuerpo_funcion}.
	 * @param ctx the parse tree
	 */
	void exitCuerpo_funcion(YLenguajeParser.Cuerpo_funcionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtDeclaracion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtDeclaracion(YLenguajeParser.StmtDeclaracionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtDeclaracion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtDeclaracion(YLenguajeParser.StmtDeclaracionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtAsignacion(YLenguajeParser.StmtAsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtAsignacion(YLenguajeParser.StmtAsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtEstructuraLocal}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtEstructuraLocal(YLenguajeParser.StmtEstructuraLocalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtEstructuraLocal}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtEstructuraLocal(YLenguajeParser.StmtEstructuraLocalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtCondicional(YLenguajeParser.StmtCondicionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtCondicional(YLenguajeParser.StmtCondicionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtSeleccion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtSeleccion(YLenguajeParser.StmtSeleccionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtSeleccion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtSeleccion(YLenguajeParser.StmtSeleccionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtCiclo(YLenguajeParser.StmtCicloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtCiclo(YLenguajeParser.StmtCicloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtRetorno}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtRetorno(YLenguajeParser.StmtRetornoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtRetorno}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtRetorno(YLenguajeParser.StmtRetornoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtContinuar}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtContinuar(YLenguajeParser.StmtContinuarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtContinuar}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtContinuar(YLenguajeParser.StmtContinuarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtRomper}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtRomper(YLenguajeParser.StmtRomperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtRomper}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtRomper(YLenguajeParser.StmtRomperContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterStmtExpresion(YLenguajeParser.StmtExpresionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitStmtExpresion(YLenguajeParser.StmtExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#terminador}.
	 * @param ctx the parse tree
	 */
	void enterTerminador(YLenguajeParser.TerminadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#terminador}.
	 * @param ctx the parse tree
	 */
	void exitTerminador(YLenguajeParser.TerminadorContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#retorno}.
	 * @param ctx the parse tree
	 */
	void enterRetorno(YLenguajeParser.RetornoContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#retorno}.
	 * @param ctx the parse tree
	 */
	void exitRetorno(YLenguajeParser.RetornoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declConTipoYValor}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclConTipoYValor(YLenguajeParser.DeclConTipoYValorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declConTipoYValor}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclConTipoYValor(YLenguajeParser.DeclConTipoYValorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declArraySinValores}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclArraySinValores(YLenguajeParser.DeclArraySinValoresContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declArraySinValores}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclArraySinValores(YLenguajeParser.DeclArraySinValoresContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declArrayConValores}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclArrayConValores(YLenguajeParser.DeclArrayConValoresContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declArrayConValores}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclArrayConValores(YLenguajeParser.DeclArrayConValoresContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declMatriz}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclMatriz(YLenguajeParser.DeclMatrizContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declMatriz}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclMatriz(YLenguajeParser.DeclMatrizContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(YLenguajeParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(YLenguajeParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varSimple}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterVarSimple(YLenguajeParser.VarSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varSimple}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitVarSimple(YLenguajeParser.VarSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varMiembro}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterVarMiembro(YLenguajeParser.VarMiembroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varMiembro}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitVarMiembro(YLenguajeParser.VarMiembroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varArray}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterVarArray(YLenguajeParser.VarArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varArray}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitVarArray(YLenguajeParser.VarArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementSi}
	 * labeled alternative in {@link YLenguajeParser#condicional}.
	 * @param ctx the parse tree
	 */
	void enterStatementSi(YLenguajeParser.StatementSiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementSi}
	 * labeled alternative in {@link YLenguajeParser#condicional}.
	 * @param ctx the parse tree
	 */
	void exitStatementSi(YLenguajeParser.StatementSiContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(YLenguajeParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(YLenguajeParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementElegir}
	 * labeled alternative in {@link YLenguajeParser#seleccion}.
	 * @param ctx the parse tree
	 */
	void enterStatementElegir(YLenguajeParser.StatementElegirContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementElegir}
	 * labeled alternative in {@link YLenguajeParser#seleccion}.
	 * @param ctx the parse tree
	 */
	void exitStatementElegir(YLenguajeParser.StatementElegirContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#caso_seleccion}.
	 * @param ctx the parse tree
	 */
	void enterCaso_seleccion(YLenguajeParser.Caso_seleccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#caso_seleccion}.
	 * @param ctx the parse tree
	 */
	void exitCaso_seleccion(YLenguajeParser.Caso_seleccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#caso_defecto}.
	 * @param ctx the parse tree
	 */
	void enterCaso_defecto(YLenguajeParser.Caso_defectoContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#caso_defecto}.
	 * @param ctx the parse tree
	 */
	void exitCaso_defecto(YLenguajeParser.Caso_defectoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloPara}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloPara(YLenguajeParser.CicloParaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloPara}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloPara(YLenguajeParser.CicloParaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloMientras}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloMientras(YLenguajeParser.CicloMientrasContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloMientras}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloMientras(YLenguajeParser.CicloMientrasContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloHacer}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloHacer(YLenguajeParser.CicloHacerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloHacer}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloHacer(YLenguajeParser.CicloHacerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initParaDecl}
	 * labeled alternative in {@link YLenguajeParser#init_para}.
	 * @param ctx the parse tree
	 */
	void enterInitParaDecl(YLenguajeParser.InitParaDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initParaDecl}
	 * labeled alternative in {@link YLenguajeParser#init_para}.
	 * @param ctx the parse tree
	 */
	void exitInitParaDecl(YLenguajeParser.InitParaDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initParaAsig}
	 * labeled alternative in {@link YLenguajeParser#init_para}.
	 * @param ctx the parse tree
	 */
	void enterInitParaAsig(YLenguajeParser.InitParaAsigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initParaAsig}
	 * labeled alternative in {@link YLenguajeParser#init_para}.
	 * @param ctx the parse tree
	 */
	void exitInitParaAsig(YLenguajeParser.InitParaAsigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pasoParaExpr}
	 * labeled alternative in {@link YLenguajeParser#paso_para}.
	 * @param ctx the parse tree
	 */
	void enterPasoParaExpr(YLenguajeParser.PasoParaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pasoParaExpr}
	 * labeled alternative in {@link YLenguajeParser#paso_para}.
	 * @param ctx the parse tree
	 */
	void exitPasoParaExpr(YLenguajeParser.PasoParaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pasoParaAsig}
	 * labeled alternative in {@link YLenguajeParser#paso_para}.
	 * @param ctx the parse tree
	 */
	void enterPasoParaAsig(YLenguajeParser.PasoParaAsigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pasoParaAsig}
	 * labeled alternative in {@link YLenguajeParser#paso_para}.
	 * @param ctx the parse tree
	 */
	void exitPasoParaAsig(YLenguajeParser.PasoParaAsigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNegativa(YLenguajeParser.ExprNegativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNegativa(YLenguajeParser.ExprNegativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprRelacional(YLenguajeParser.ExprRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprRelacional(YLenguajeParser.ExprRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprParentesis(YLenguajeParser.ExprParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprParentesis(YLenguajeParser.ExprParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprMultiplicacionDivision}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMultiplicacionDivision(YLenguajeParser.ExprMultiplicacionDivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprMultiplicacionDivision}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMultiplicacionDivision(YLenguajeParser.ExprMultiplicacionDivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostIncremento(YLenguajeParser.ExprPostIncrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostIncremento(YLenguajeParser.ExprPostIncrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprSumaResta(YLenguajeParser.ExprSumaRestaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprSumaResta(YLenguajeParser.ExprSumaRestaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(YLenguajeParser.ExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(YLenguajeParser.ExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNegada(YLenguajeParser.ExprNegadaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNegada(YLenguajeParser.ExprNegadaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPrimitivo(YLenguajeParser.ExprPrimitivoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPrimitivo(YLenguajeParser.ExprPrimitivoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostDecremento(YLenguajeParser.ExprPostDecrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostDecremento(YLenguajeParser.ExprPostDecrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(YLenguajeParser.ExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(YLenguajeParser.ExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaFuncion(YLenguajeParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaFuncion(YLenguajeParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprListaLiteral}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprListaLiteral(YLenguajeParser.ExprListaLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprListaLiteral}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprListaLiteral(YLenguajeParser.ExprListaLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAccesoMiembro}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoMiembro(YLenguajeParser.ExprAccesoMiembroContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAccesoMiembro}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoMiembro(YLenguajeParser.ExprAccesoMiembroContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAccesoArray}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoArray(YLenguajeParser.ExprAccesoArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAccesoArray}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoArray(YLenguajeParser.ExprAccesoArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#valor_primitivo}.
	 * @param ctx the parse tree
	 */
	void enterValor_primitivo(YLenguajeParser.Valor_primitivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#valor_primitivo}.
	 * @param ctx the parse tree
	 */
	void exitValor_primitivo(YLenguajeParser.Valor_primitivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link YLenguajeParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void enterLista_expresiones(YLenguajeParser.Lista_expresionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link YLenguajeParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void exitLista_expresiones(YLenguajeParser.Lista_expresionesContext ctx);
}