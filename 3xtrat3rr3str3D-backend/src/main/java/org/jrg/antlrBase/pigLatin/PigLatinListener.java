// Generated from org/jrg/antlrBase/pigLatin/PigLatin.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.pigLatin;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PigLatinParser}.
 */
public interface PigLatinListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(PigLatinParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(PigLatinParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#seccion_importaciones}.
	 * @param ctx the parse tree
	 */
	void enterSeccion_importaciones(PigLatinParser.Seccion_importacionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#seccion_importaciones}.
	 * @param ctx the parse tree
	 */
	void exitSeccion_importaciones(PigLatinParser.Seccion_importacionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#ruta_importacion}.
	 * @param ctx the parse tree
	 */
	void enterRuta_importacion(PigLatinParser.Ruta_importacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#ruta_importacion}.
	 * @param ctx the parse tree
	 */
	void exitRuta_importacion(PigLatinParser.Ruta_importacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#seccion_global_variables}.
	 * @param ctx the parse tree
	 */
	void enterSeccion_global_variables(PigLatinParser.Seccion_global_variablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#seccion_global_variables}.
	 * @param ctx the parse tree
	 */
	void exitSeccion_global_variables(PigLatinParser.Seccion_global_variablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#seccion_maior}.
	 * @param ctx the parse tree
	 */
	void enterSeccion_maior(PigLatinParser.Seccion_maiorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#seccion_maior}.
	 * @param ctx the parse tree
	 */
	void exitSeccion_maior(PigLatinParser.Seccion_maiorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void enterTipo_dato(PigLatinParser.Tipo_datoContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#tipo_dato}.
	 * @param ctx the parse tree
	 */
	void exitTipo_dato(PigLatinParser.Tipo_datoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorAsignableMiembroEstructura}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterValorAsignableMiembroEstructura(PigLatinParser.ValorAsignableMiembroEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valorAsignableMiembroEstructura}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitValorAsignableMiembroEstructura(PigLatinParser.ValorAsignableMiembroEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorAsignableArray}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterValorAsignableArray(PigLatinParser.ValorAsignableArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valorAsignableArray}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitValorAsignableArray(PigLatinParser.ValorAsignableArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorAsignableSimple}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void enterValorAsignableSimple(PigLatinParser.ValorAsignableSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valorAsignableSimple}
	 * labeled alternative in {@link PigLatinParser#variable_asignable}.
	 * @param ctx the parse tree
	 */
	void exitValorAsignableSimple(PigLatinParser.ValorAsignableSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNegativa(PigLatinParser.ExprNegativaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNegativa}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNegativa(PigLatinParser.ExprNegativaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPreIncremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPreIncremento(PigLatinParser.ExprPreIncrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPreIncremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPreIncremento(PigLatinParser.ExprPreIncrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprRelacional(PigLatinParser.ExprRelacionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprRelacional}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprRelacional(PigLatinParser.ExprRelacionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprParentesis(PigLatinParser.ExprParentesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprParentesis}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprParentesis(PigLatinParser.ExprParentesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprMultiplicacionDivision}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprMultiplicacionDivision(PigLatinParser.ExprMultiplicacionDivisionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprMultiplicacionDivision}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprMultiplicacionDivision(PigLatinParser.ExprMultiplicacionDivisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostIncremento(PigLatinParser.ExprPostIncrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPostIncremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostIncremento(PigLatinParser.ExprPostIncrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLlamadaMetodo}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaMetodo(PigLatinParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLlamadaMetodo}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaMetodo(PigLatinParser.ExprLlamadaMetodoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprSumaResta(PigLatinParser.ExprSumaRestaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSumaResta}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprSumaResta(PigLatinParser.ExprSumaRestaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprOr(PigLatinParser.ExprOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprOr}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprOr(PigLatinParser.ExprOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprNegada(PigLatinParser.ExprNegadaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNegada}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprNegada(PigLatinParser.ExprNegadaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPrimitivo(PigLatinParser.ExprPrimitivoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPrimitivo}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPrimitivo(PigLatinParser.ExprPrimitivoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPostDecremento(PigLatinParser.ExprPostDecrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPostDecremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPostDecremento(PigLatinParser.ExprPostDecrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAccesoPosicionArray}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoPosicionArray(PigLatinParser.ExprAccesoPosicionArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAccesoPosicionArray}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoPosicionArray(PigLatinParser.ExprAccesoPosicionArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAnd(PigLatinParser.ExprAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAnd}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAnd(PigLatinParser.ExprAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAccesoMiembroEstructura}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprAccesoMiembroEstructura(PigLatinParser.ExprAccesoMiembroEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAccesoMiembroEstructura}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprAccesoMiembroEstructura(PigLatinParser.ExprAccesoMiembroEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprLlamadaFuncion(PigLatinParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLlamadaFuncion}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprLlamadaFuncion(PigLatinParser.ExprLlamadaFuncionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprListaLiteral}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprListaLiteral(PigLatinParser.ExprListaLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprListaLiteral}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprListaLiteral(PigLatinParser.ExprListaLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprPreDecremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprPreDecremento(PigLatinParser.ExprPreDecrementoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprPreDecremento}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprPreDecremento(PigLatinParser.ExprPreDecrementoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprInstanciaObjeto}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void enterExprInstanciaObjeto(PigLatinParser.ExprInstanciaObjetoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprInstanciaObjeto}
	 * labeled alternative in {@link PigLatinParser#expresion}.
	 * @param ctx the parse tree
	 */
	void exitExprInstanciaObjeto(PigLatinParser.ExprInstanciaObjetoContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#valor_primitivo}.
	 * @param ctx the parse tree
	 */
	void enterValor_primitivo(PigLatinParser.Valor_primitivoContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#valor_primitivo}.
	 * @param ctx the parse tree
	 */
	void exitValor_primitivo(PigLatinParser.Valor_primitivoContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void enterLista_expresiones(PigLatinParser.Lista_expresionesContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#lista_expresiones}.
	 * @param ctx the parse tree
	 */
	void exitLista_expresiones(PigLatinParser.Lista_expresionesContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#lista_atributos_instancia}.
	 * @param ctx the parse tree
	 */
	void enterLista_atributos_instancia(PigLatinParser.Lista_atributos_instanciaContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#lista_atributos_instancia}.
	 * @param ctx the parse tree
	 */
	void exitLista_atributos_instancia(PigLatinParser.Lista_atributos_instanciaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code campoConNombre}
	 * labeled alternative in {@link PigLatinParser#atributo_instancia}.
	 * @param ctx the parse tree
	 */
	void enterCampoConNombre(PigLatinParser.CampoConNombreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code campoConNombre}
	 * labeled alternative in {@link PigLatinParser#atributo_instancia}.
	 * @param ctx the parse tree
	 */
	void exitCampoConNombre(PigLatinParser.CampoConNombreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code campoPosicional}
	 * labeled alternative in {@link PigLatinParser#atributo_instancia}.
	 * @param ctx the parse tree
	 */
	void enterCampoPosicional(PigLatinParser.CampoPosicionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code campoPosicional}
	 * labeled alternative in {@link PigLatinParser#atributo_instancia}.
	 * @param ctx the parse tree
	 */
	void exitCampoPosicional(PigLatinParser.CampoPosicionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declObjetoNovus}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclObjetoNovus(PigLatinParser.DeclObjetoNovusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declObjetoNovus}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclObjetoNovus(PigLatinParser.DeclObjetoNovusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declEstructuraConValores}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclEstructuraConValores(PigLatinParser.DeclEstructuraConValoresContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declEstructuraConValores}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclEstructuraConValores(PigLatinParser.DeclEstructuraConValoresContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declConTipoYValor}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclConTipoYValor(PigLatinParser.DeclConTipoYValorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declConTipoYValor}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclConTipoYValor(PigLatinParser.DeclConTipoYValorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declBooleanaImplicita}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclBooleanaImplicita(PigLatinParser.DeclBooleanaImplicitaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declBooleanaImplicita}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclBooleanaImplicita(PigLatinParser.DeclBooleanaImplicitaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declArraySinDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclArraySinDatos(PigLatinParser.DeclArraySinDatosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declArraySinDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclArraySinDatos(PigLatinParser.DeclArraySinDatosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declArrayConDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclArrayConDatos(PigLatinParser.DeclArrayConDatosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declArrayConDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclArrayConDatos(PigLatinParser.DeclArrayConDatosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declArrayEstructura}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclArrayEstructura(PigLatinParser.DeclArrayEstructuraContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declArrayEstructura}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclArrayEstructura(PigLatinParser.DeclArrayEstructuraContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declMatrizSinDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclMatrizSinDatos(PigLatinParser.DeclMatrizSinDatosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declMatrizSinDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclMatrizSinDatos(PigLatinParser.DeclMatrizSinDatosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declMatrizConDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void enterDeclMatrizConDatos(PigLatinParser.DeclMatrizConDatosContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declMatrizConDatos}
	 * labeled alternative in {@link PigLatinParser#declaracion_variable}.
	 * @param ctx the parse tree
	 */
	void exitDeclMatrizConDatos(PigLatinParser.DeclMatrizConDatosContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#fila_matriz_pig}.
	 * @param ctx the parse tree
	 */
	void enterFila_matriz_pig(PigLatinParser.Fila_matriz_pigContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#fila_matriz_pig}.
	 * @param ctx the parse tree
	 */
	void exitFila_matriz_pig(PigLatinParser.Fila_matriz_pigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code asignacionGeneral}
	 * labeled alternative in {@link PigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacionGeneral(PigLatinParser.AsignacionGeneralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code asignacionGeneral}
	 * labeled alternative in {@link PigLatinParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacionGeneral(PigLatinParser.AsignacionGeneralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtAsignacion(PigLatinParser.StmtAsignacionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtAsignacion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtAsignacion(PigLatinParser.StmtAsignacionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtCondicional(PigLatinParser.StmtCondicionalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtCondicional}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtCondicional(PigLatinParser.StmtCondicionalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtCiclo(PigLatinParser.StmtCicloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtCiclo}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtCiclo(PigLatinParser.StmtCicloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtLectura}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtLectura(PigLatinParser.StmtLecturaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtLectura}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtLectura(PigLatinParser.StmtLecturaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtImpresion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtImpresion(PigLatinParser.StmtImpresionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtImpresion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtImpresion(PigLatinParser.StmtImpresionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtInterrumpe}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtInterrumpe(PigLatinParser.StmtInterrumpeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtInterrumpe}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtInterrumpe(PigLatinParser.StmtInterrumpeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtPerge}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtPerge(PigLatinParser.StmtPergeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtPerge}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtPerge(PigLatinParser.StmtPergeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void enterStmtExpresion(PigLatinParser.StmtExpresionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtExpresion}
	 * labeled alternative in {@link PigLatinParser#instruccion_flujo}.
	 * @param ctx the parse tree
	 */
	void exitStmtExpresion(PigLatinParser.StmtExpresionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#bloque}.
	 * @param ctx the parse tree
	 */
	void enterBloque(PigLatinParser.BloqueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#bloque}.
	 * @param ctx the parse tree
	 */
	void exitBloque(PigLatinParser.BloqueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statementSi}
	 * labeled alternative in {@link PigLatinParser#condicional}.
	 * @param ctx the parse tree
	 */
	void enterStatementSi(PigLatinParser.StatementSiContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statementSi}
	 * labeled alternative in {@link PigLatinParser#condicional}.
	 * @param ctx the parse tree
	 */
	void exitStatementSi(PigLatinParser.StatementSiContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloDum}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloDum(PigLatinParser.CicloDumContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloDum}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloDum(PigLatinParser.CicloDumContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloFacere}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloFacere(PigLatinParser.CicloFacereContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloFacere}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloFacere(PigLatinParser.CicloFacereContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cicloPer}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void enterCicloPer(PigLatinParser.CicloPerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cicloPer}
	 * labeled alternative in {@link PigLatinParser#ciclo}.
	 * @param ctx the parse tree
	 */
	void exitCicloPer(PigLatinParser.CicloPerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initPerDecl}
	 * labeled alternative in {@link PigLatinParser#init_per}.
	 * @param ctx the parse tree
	 */
	void enterInitPerDecl(PigLatinParser.InitPerDeclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initPerDecl}
	 * labeled alternative in {@link PigLatinParser#init_per}.
	 * @param ctx the parse tree
	 */
	void exitInitPerDecl(PigLatinParser.InitPerDeclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code initPerAsig}
	 * labeled alternative in {@link PigLatinParser#init_per}.
	 * @param ctx the parse tree
	 */
	void enterInitPerAsig(PigLatinParser.InitPerAsigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code initPerAsig}
	 * labeled alternative in {@link PigLatinParser#init_per}.
	 * @param ctx the parse tree
	 */
	void exitInitPerAsig(PigLatinParser.InitPerAsigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pasoPerExpr}
	 * labeled alternative in {@link PigLatinParser#paso_per}.
	 * @param ctx the parse tree
	 */
	void enterPasoPerExpr(PigLatinParser.PasoPerExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pasoPerExpr}
	 * labeled alternative in {@link PigLatinParser#paso_per}.
	 * @param ctx the parse tree
	 */
	void exitPasoPerExpr(PigLatinParser.PasoPerExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pasoPerAsig}
	 * labeled alternative in {@link PigLatinParser#paso_per}.
	 * @param ctx the parse tree
	 */
	void enterPasoPerAsig(PigLatinParser.PasoPerAsigContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pasoPerAsig}
	 * labeled alternative in {@link PigLatinParser#paso_per}.
	 * @param ctx the parse tree
	 */
	void exitPasoPerAsig(PigLatinParser.PasoPerAsigContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lecturaConsolaSimple}
	 * labeled alternative in {@link PigLatinParser#instruccion_lectura}.
	 * @param ctx the parse tree
	 */
	void enterLecturaConsolaSimple(PigLatinParser.LecturaConsolaSimpleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lecturaConsolaSimple}
	 * labeled alternative in {@link PigLatinParser#instruccion_lectura}.
	 * @param ctx the parse tree
	 */
	void exitLecturaConsolaSimple(PigLatinParser.LecturaConsolaSimpleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lecturaConsolaAVariable}
	 * labeled alternative in {@link PigLatinParser#instruccion_lectura}.
	 * @param ctx the parse tree
	 */
	void enterLecturaConsolaAVariable(PigLatinParser.LecturaConsolaAVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lecturaConsolaAVariable}
	 * labeled alternative in {@link PigLatinParser#instruccion_lectura}.
	 * @param ctx the parse tree
	 */
	void exitLecturaConsolaAVariable(PigLatinParser.LecturaConsolaAVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code impresionEncadenada}
	 * labeled alternative in {@link PigLatinParser#instruccion_impresion}.
	 * @param ctx the parse tree
	 */
	void enterImpresionEncadenada(PigLatinParser.ImpresionEncadenadaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code impresionEncadenada}
	 * labeled alternative in {@link PigLatinParser#instruccion_impresion}.
	 * @param ctx the parse tree
	 */
	void exitImpresionEncadenada(PigLatinParser.ImpresionEncadenadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link PigLatinParser#elemento_imprimir}.
	 * @param ctx the parse tree
	 */
	void enterElemento_imprimir(PigLatinParser.Elemento_imprimirContext ctx);
	/**
	 * Exit a parse tree produced by {@link PigLatinParser#elemento_imprimir}.
	 * @param ctx the parse tree
	 */
	void exitElemento_imprimir(PigLatinParser.Elemento_imprimirContext ctx);
}