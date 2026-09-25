// Generated from org/jrg/antlrBase/yLenguaje/YLenguaje.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.yLenguaje;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link YLenguajeParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface YLenguajeVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(YLenguajeParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#seccion_estructuras}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccion_estructuras(YLenguajeParser.Seccion_estructurasContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#seccion_funciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccion_funciones(YLenguajeParser.Seccion_funcionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#tipo_dato}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_dato(YLenguajeParser.Tipo_datoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defEstructura}
	 * labeled alternative in {@link YLenguajeParser#definicion_struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefEstructura(YLenguajeParser.DefEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atributoSimple}
	 * labeled alternative in {@link YLenguajeParser#atributo_struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributoSimple(YLenguajeParser.AtributoSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atributoArray}
	 * labeled alternative in {@link YLenguajeParser#atributo_struct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributoArray(YLenguajeParser.AtributoArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defFuncionSinRetorno}
	 * labeled alternative in {@link YLenguajeParser#definicion_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefFuncionSinRetorno(YLenguajeParser.DefFuncionSinRetornoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code defFuncionConRetorno}
	 * labeled alternative in {@link YLenguajeParser#definicion_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefFuncionConRetorno(YLenguajeParser.DefFuncionConRetornoContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#parametros}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametros(YLenguajeParser.ParametrosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramSimple}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamSimple(YLenguajeParser.ParamSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramArray}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamArray(YLenguajeParser.ParamArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramEstructura}
	 * labeled alternative in {@link YLenguajeParser#parametro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamEstructura(YLenguajeParser.ParamEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#cuerpo_funcion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCuerpo_funcion(YLenguajeParser.Cuerpo_funcionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtDeclaracion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtDeclaracion(YLenguajeParser.StmtDeclaracionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAsignacion(YLenguajeParser.StmtAsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtEstructuraLocal}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtEstructuraLocal(YLenguajeParser.StmtEstructuraLocalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCondicional(YLenguajeParser.StmtCondicionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtSeleccion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtSeleccion(YLenguajeParser.StmtSeleccionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCiclo(YLenguajeParser.StmtCicloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtRetorno}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtRetorno(YLenguajeParser.StmtRetornoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtContinuar}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtContinuar(YLenguajeParser.StmtContinuarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtRomper}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtRomper(YLenguajeParser.StmtRomperContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link YLenguajeParser#instruccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtExpresion(YLenguajeParser.StmtExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#terminador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerminador(YLenguajeParser.TerminadorContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#retorno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRetorno(YLenguajeParser.RetornoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declConTipoYValor}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclConTipoYValor(YLenguajeParser.DeclConTipoYValorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declArraySinValores}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclArraySinValores(YLenguajeParser.DeclArraySinValoresContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declArrayConValores}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclArrayConValores(YLenguajeParser.DeclArrayConValoresContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declMatriz}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclMatriz(YLenguajeParser.DeclMatrizContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declMatrizConValores}
	 * labeled alternative in {@link YLenguajeParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclMatrizConValores(YLenguajeParser.DeclMatrizConValoresContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#fila_matriz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFila_matriz(YLenguajeParser.Fila_matrizContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacion(YLenguajeParser.AsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varSimple}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarSimple(YLenguajeParser.VarSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varMiembro}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarMiembro(YLenguajeParser.VarMiembroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varArray}
	 * labeled alternative in {@link YLenguajeParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarArray(YLenguajeParser.VarArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementSi}
	 * labeled alternative in {@link YLenguajeParser#condicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementSi(YLenguajeParser.StatementSiContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(YLenguajeParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementElegir}
	 * labeled alternative in {@link YLenguajeParser#seleccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementElegir(YLenguajeParser.StatementElegirContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#caso_seleccion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso_seleccion(YLenguajeParser.Caso_seleccionContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#caso_defecto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaso_defecto(YLenguajeParser.Caso_defectoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloPara}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloPara(YLenguajeParser.CicloParaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloMientras}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloMientras(YLenguajeParser.CicloMientrasContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloHacer}
	 * labeled alternative in {@link YLenguajeParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloHacer(YLenguajeParser.CicloHacerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code initParaDecl}
	 * labeled alternative in {@link YLenguajeParser#init_para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitParaDecl(YLenguajeParser.InitParaDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code initParaAsig}
	 * labeled alternative in {@link YLenguajeParser#init_para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitParaAsig(YLenguajeParser.InitParaAsigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pasoParaExpr}
	 * labeled alternative in {@link YLenguajeParser#paso_para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasoParaExpr(YLenguajeParser.PasoParaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pasoParaAsig}
	 * labeled alternative in {@link YLenguajeParser#paso_para}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasoParaAsig(YLenguajeParser.PasoParaAsigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNegativa(YLenguajeParser.ExprNegativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRelacional(YLenguajeParser.ExprRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParentesis(YLenguajeParser.ExprParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMultiplicacionDivision}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultiplicacionDivision(YLenguajeParser.ExprMultiplicacionDivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostIncremento(YLenguajeParser.ExprPostIncrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSumaResta(YLenguajeParser.ExprSumaRestaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(YLenguajeParser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNegada(YLenguajeParser.ExprNegadaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrimitivo(YLenguajeParser.ExprPrimitivoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostDecremento(YLenguajeParser.ExprPostDecrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(YLenguajeParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaFuncion(YLenguajeParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListaLiteral}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListaLiteral(YLenguajeParser.ExprListaLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAccesoMiembro}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoMiembro(YLenguajeParser.ExprAccesoMiembroContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAccesoArray}
	 * labeled alternative in {@link YLenguajeParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoArray(YLenguajeParser.ExprAccesoArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#valor_primitivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_primitivo(YLenguajeParser.Valor_primitivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link YLenguajeParser#lista_expresiones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_expresiones(YLenguajeParser.Lista_expresionesContext ctx);
}