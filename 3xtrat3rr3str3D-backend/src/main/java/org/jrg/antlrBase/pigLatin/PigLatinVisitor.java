// Generated from org/jrg/antlrBase/pigLatin/PigLatin.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.pigLatin;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PigLatinParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PigLatinVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(PigLatinParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#seccion_importaciones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccion_importaciones(PigLatinParser.Seccion_importacionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#ruta_importacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRuta_importacion(PigLatinParser.Ruta_importacionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#seccion_global_variables}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccion_global_variables(PigLatinParser.Seccion_global_variablesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#seccion_maior}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSeccion_maior(PigLatinParser.Seccion_maiorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#tipo_dato}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTipo_dato(PigLatinParser.Tipo_datoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorAsignableMiembroEstructura}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorAsignableMiembroEstructura(PigLatinParser.ValorAsignableMiembroEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorAsignableArray}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorAsignableArray(PigLatinParser.ValorAsignableArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorAsignableSimple}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorAsignableSimple(PigLatinParser.ValorAsignableSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNegativa(PigLatinParser.ExprNegativaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPreIncremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPreIncremento(PigLatinParser.ExprPreIncrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprRelacional(PigLatinParser.ExprRelacionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParentesis(PigLatinParser.ExprParentesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMultiplicacionDivision}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMultiplicacionDivision(PigLatinParser.ExprMultiplicacionDivisionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostIncremento(PigLatinParser.ExprPostIncrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLlamadaMetodo}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaMetodo(PigLatinParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSumaResta(PigLatinParser.ExprSumaRestaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprOr(PigLatinParser.ExprOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprNegada(PigLatinParser.ExprNegadaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPrimitivo(PigLatinParser.ExprPrimitivoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPostDecremento(PigLatinParser.ExprPostDecrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAccesoPosicionArray}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoPosicionArray(PigLatinParser.ExprAccesoPosicionArrayContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAnd(PigLatinParser.ExprAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprAccesoMiembroEstructura}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprAccesoMiembroEstructura(PigLatinParser.ExprAccesoMiembroEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLlamadaFuncion(PigLatinParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprListaLiteral}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListaLiteral(PigLatinParser.ExprListaLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprPreDecremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprPreDecremento(PigLatinParser.ExprPreDecrementoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprInstanciaObjeto}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInstanciaObjeto(PigLatinParser.ExprInstanciaObjetoContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#valor_primitivo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValor_primitivo(PigLatinParser.Valor_primitivoContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#lista_expresiones}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_expresiones(PigLatinParser.Lista_expresionesContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#lista_atributos_instancia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLista_atributos_instancia(PigLatinParser.Lista_atributos_instanciaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code campoConNombre}
	 * labeled alternative in {@link PigLatinParser#atributo_instancia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCampoConNombre(PigLatinParser.CampoConNombreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code campoPosicional}
	 * labeled alternative in {@link PigLatinParser#atributo_instancia}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCampoPosicional(PigLatinParser.CampoPosicionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declObjetoNovus}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclObjetoNovus(PigLatinParser.DeclObjetoNovusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declEstructuraConValores}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclEstructuraConValores(PigLatinParser.DeclEstructuraConValoresContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declConTipoYValor}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclConTipoYValor(PigLatinParser.DeclConTipoYValorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declBooleanaImplicita}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclBooleanaImplicita(PigLatinParser.DeclBooleanaImplicitaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declArraySinDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclArraySinDatos(PigLatinParser.DeclArraySinDatosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declArrayConDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclArrayConDatos(PigLatinParser.DeclArrayConDatosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declArrayEstructura}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclArrayEstructura(PigLatinParser.DeclArrayEstructuraContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declMatrizSinDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclMatrizSinDatos(PigLatinParser.DeclMatrizSinDatosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declMatrizConDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclMatrizConDatos(PigLatinParser.DeclMatrizConDatosContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#fila_matriz_pig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFila_matriz_pig(PigLatinParser.Fila_matriz_pigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code asignacionGeneral}
	 * labeled alternative in {@link PigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAsignacionGeneral(PigLatinParser.AsignacionGeneralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtAsignacion(PigLatinParser.StmtAsignacionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCondicional(PigLatinParser.StmtCondicionalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCiclo(PigLatinParser.StmtCicloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtLectura}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtLectura(PigLatinParser.StmtLecturaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtImpresion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtImpresion(PigLatinParser.StmtImpresionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtInterrumpe}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtInterrumpe(PigLatinParser.StmtInterrumpeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtPerge}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtPerge(PigLatinParser.StmtPergeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtExpresion(PigLatinParser.StmtExpresionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#bloque}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloque(PigLatinParser.BloqueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statementSi}
	 * labeled alternative in {@link PigLatinParser#condicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementSi(PigLatinParser.StatementSiContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloDum}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloDum(PigLatinParser.CicloDumContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloFacere}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloFacere(PigLatinParser.CicloFacereContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cicloPer}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCicloPer(PigLatinParser.CicloPerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code initPerDecl}
	 * labeled alternative in {@link PigLatinParser#init_per}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitPerDecl(PigLatinParser.InitPerDeclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code initPerAsig}
	 * labeled alternative in {@link PigLatinParser#init_per}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitPerAsig(PigLatinParser.InitPerAsigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pasoPerExpr}
	 * labeled alternative in {@link PigLatinParser#paso_per}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasoPerExpr(PigLatinParser.PasoPerExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pasoPerAsig}
	 * labeled alternative in {@link PigLatinParser#paso_per}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPasoPerAsig(PigLatinParser.PasoPerAsigContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lecturaConsolaSimple}
	 * labeled alternative in {@link PigLatinParser#instruccion_lectura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLecturaConsolaSimple(PigLatinParser.LecturaConsolaSimpleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lecturaConsolaAVariable}
	 * labeled alternative in {@link PigLatinParser#instruccion_lectura}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLecturaConsolaAVariable(PigLatinParser.LecturaConsolaAVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code impresionEncadenada}
	 * labeled alternative in {@link PigLatinParser#instruccion_impresion}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImpresionEncadenada(PigLatinParser.ImpresionEncadenadaContext ctx);
	/**
	 * Visit a parse tree produced by {@link PigLatinParser#elemento_imprimir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElemento_imprimir(PigLatinParser.Elemento_imprimirContext ctx);
}