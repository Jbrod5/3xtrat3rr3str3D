// Generated from org/jrg/antlrBase/pigLatin/PigLatin.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.pigLatin;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class PigLatinParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IMPORT=1, VARIABILES=2, MAIOR=3, FINIS=4, NUMERUS=5, TEXTUM=6, DECIMALIS=7, 
		LITTERA=8, BOOL=9, VERUM=10, FALSUS=11, ESTO=12, SERIES=13, NOVUS=14, 
		SI=15, ALITER=16, DUM=17, FACERE=18, PER=19, INTERRUMPE=20, PERGE=21, 
		FINIS_BLOQUE=22, NON=23, LEER=24, IMPRIMIR=25, INCREMENTO=26, DECREMENTO=27, 
		IGUAL_QUE=28, DIFERENTE_QUE=29, MAYOR_IGUAL_QUE=30, MENOR_IGUAL_QUE=31, 
		MAYOR_QUE=32, MENOR_QUE=33, AND=34, OR=35, ASIGNACION=36, SUMA=37, RESTA=38, 
		MULT=39, DIV=40, CORCHETE_IZQ=41, CORCHETE_DER=42, LLAVE_IZQ=43, LLAVE_DER=44, 
		PAR_IZQ=45, PAR_DER=46, PUNTO_Y_COMA=47, DOS_PUNTOS=48, COMA=49, PUNTO=50, 
		CADENA=51, CARACTER=52, DECIMAL=53, ENTERO=54, IDENTIFICADOR=55, COMENTARIO_LINEA=56, 
		COMENTARIO_BLOQUE=57, ESPACIOS_BLANCO=58;
	public static final int
		RULE_programa = 0, RULE_seccion_importaciones = 1, RULE_ruta_importacion = 2, 
		RULE_seccion_global_variables = 3, RULE_seccion_maior = 4, RULE_tipo_dato = 5, 
		RULE_variable_asignable = 6, RULE_expresion = 7, RULE_valor_primitivo = 8, 
		RULE_lista_expresiones = 9, RULE_lista_atributos_instancia = 10, RULE_atributo_instancia = 11, 
		RULE_declaracion_variable = 12, RULE_fila_matriz_pig = 13, RULE_asignacion = 14, 
		RULE_instruccion_flujo = 15, RULE_bloque = 16, RULE_condicional = 17, 
		RULE_ciclo = 18, RULE_init_per = 19, RULE_paso_per = 20, RULE_instruccion_lectura = 21, 
		RULE_instruccion_impresion = 22, RULE_elemento_imprimir = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "seccion_importaciones", "ruta_importacion", "seccion_global_variables", 
			"seccion_maior", "tipo_dato", "variable_asignable", "expresion", "valor_primitivo", 
			"lista_expresiones", "lista_atributos_instancia", "atributo_instancia", 
			"declaracion_variable", "fila_matriz_pig", "asignacion", "instruccion_flujo", 
			"bloque", "condicional", "ciclo", "init_per", "paso_per", "instruccion_lectura", 
			"instruccion_impresion", "elemento_imprimir"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", "'VARIABILES'", "'MAIOR'", "'FINIS'", "'numerus'", 
			"'textum'", "'decimalis'", "'littera'", "'bool'", "'verum'", "'falsus'", 
			"'esto'", "'series'", "'novus'", "'si'", "'aliter'", "'dum'", "'facere'", 
			"'per'", "'interrumpe'", "'perge'", "'finis'", "'non'", "'<<'", "'>>'", 
			"'++'", "'--'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'&&'", 
			"'||'", "'='", "'+'", "'-'", "'*'", "'/'", "'['", "']'", "'{'", "'}'", 
			"'('", "')'", "';'", "':'", "','", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IMPORT", "VARIABILES", "MAIOR", "FINIS", "NUMERUS", "TEXTUM", 
			"DECIMALIS", "LITTERA", "BOOL", "VERUM", "FALSUS", "ESTO", "SERIES", 
			"NOVUS", "SI", "ALITER", "DUM", "FACERE", "PER", "INTERRUMPE", "PERGE", 
			"FINIS_BLOQUE", "NON", "LEER", "IMPRIMIR", "INCREMENTO", "DECREMENTO", 
			"IGUAL_QUE", "DIFERENTE_QUE", "MAYOR_IGUAL_QUE", "MENOR_IGUAL_QUE", "MAYOR_QUE", 
			"MENOR_QUE", "AND", "OR", "ASIGNACION", "SUMA", "RESTA", "MULT", "DIV", 
			"CORCHETE_IZQ", "CORCHETE_DER", "LLAVE_IZQ", "LLAVE_DER", "PAR_IZQ", 
			"PAR_DER", "PUNTO_Y_COMA", "DOS_PUNTOS", "COMA", "PUNTO", "CADENA", "CARACTER", 
			"DECIMAL", "ENTERO", "IDENTIFICADOR", "COMENTARIO_LINEA", "COMENTARIO_BLOQUE", 
			"ESPACIOS_BLANCO"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PigLatin.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PigLatinParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public Seccion_maiorContext seccion_maior() {
			return getRuleContext(Seccion_maiorContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PigLatinParser.EOF, 0); }
		public Seccion_importacionesContext seccion_importaciones() {
			return getRuleContext(Seccion_importacionesContext.class,0);
		}
		public Seccion_global_variablesContext seccion_global_variables() {
			return getRuleContext(Seccion_global_variablesContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(48);
				seccion_importaciones();
				}
				break;
			}
			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABILES) {
				{
				setState(51);
				seccion_global_variables();
				}
			}

			setState(54);
			seccion_maior();
			setState(55);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Seccion_importacionesContext extends ParserRuleContext {
		public List<TerminalNode> IMPORT() { return getTokens(PigLatinParser.IMPORT); }
		public TerminalNode IMPORT(int i) {
			return getToken(PigLatinParser.IMPORT, i);
		}
		public List<Ruta_importacionContext> ruta_importacion() {
			return getRuleContexts(Ruta_importacionContext.class);
		}
		public Ruta_importacionContext ruta_importacion(int i) {
			return getRuleContext(Ruta_importacionContext.class,i);
		}
		public Seccion_importacionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccion_importaciones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterSeccion_importaciones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitSeccion_importaciones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitSeccion_importaciones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Seccion_importacionesContext seccion_importaciones() throws RecognitionException {
		Seccion_importacionesContext _localctx = new Seccion_importacionesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_seccion_importaciones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(57);
				match(IMPORT);
				setState(58);
				ruta_importacion();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ruta_importacionContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFICADOR() { return getTokens(PigLatinParser.IDENTIFICADOR); }
		public TerminalNode IDENTIFICADOR(int i) {
			return getToken(PigLatinParser.IDENTIFICADOR, i);
		}
		public List<TerminalNode> PUNTO() { return getTokens(PigLatinParser.PUNTO); }
		public TerminalNode PUNTO(int i) {
			return getToken(PigLatinParser.PUNTO, i);
		}
		public Ruta_importacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruta_importacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterRuta_importacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitRuta_importacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitRuta_importacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Ruta_importacionContext ruta_importacion() throws RecognitionException {
		Ruta_importacionContext _localctx = new Ruta_importacionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ruta_importacion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(IDENTIFICADOR);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PUNTO) {
				{
				{
				setState(65);
				match(PUNTO);
				setState(66);
				match(IDENTIFICADOR);
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Seccion_global_variablesContext extends ParserRuleContext {
		public TerminalNode VARIABILES() { return getToken(PigLatinParser.VARIABILES, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(PigLatinParser.MAYOR_QUE, 0); }
		public List<Declaracion_variableContext> declaracion_variable() {
			return getRuleContexts(Declaracion_variableContext.class);
		}
		public Declaracion_variableContext declaracion_variable(int i) {
			return getRuleContext(Declaracion_variableContext.class,i);
		}
		public Seccion_global_variablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccion_global_variables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterSeccion_global_variables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitSeccion_global_variables(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitSeccion_global_variables(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Seccion_global_variablesContext seccion_global_variables() throws RecognitionException {
		Seccion_global_variablesContext _localctx = new Seccion_global_variablesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_seccion_global_variables);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(VARIABILES);
			setState(73);
			match(MAYOR_QUE);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ESTO || _la==SERIES) {
				{
				{
				setState(74);
				declaracion_variable();
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Seccion_maiorContext extends ParserRuleContext {
		public TerminalNode MAIOR() { return getToken(PigLatinParser.MAIOR, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(PigLatinParser.MAYOR_QUE, 0); }
		public TerminalNode FINIS() { return getToken(PigLatinParser.FINIS, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public List<Instruccion_flujoContext> instruccion_flujo() {
			return getRuleContexts(Instruccion_flujoContext.class);
		}
		public Instruccion_flujoContext instruccion_flujo(int i) {
			return getRuleContext(Instruccion_flujoContext.class,i);
		}
		public Seccion_maiorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccion_maior; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterSeccion_maior(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitSeccion_maior(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitSeccion_maior(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Seccion_maiorContext seccion_maior() throws RecognitionException {
		Seccion_maiorContext _localctx = new Seccion_maiorContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_seccion_maior);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(MAIOR);
			setState(81);
			match(MAYOR_QUE);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049831422976L) != 0)) {
				{
				{
				setState(82);
				instruccion_flujo();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			match(FINIS);
			setState(89);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Tipo_datoContext extends ParserRuleContext {
		public TerminalNode NUMERUS() { return getToken(PigLatinParser.NUMERUS, 0); }
		public TerminalNode TEXTUM() { return getToken(PigLatinParser.TEXTUM, 0); }
		public TerminalNode DECIMALIS() { return getToken(PigLatinParser.DECIMALIS, 0); }
		public TerminalNode LITTERA() { return getToken(PigLatinParser.LITTERA, 0); }
		public TerminalNode BOOL() { return getToken(PigLatinParser.BOOL, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public Tipo_datoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_dato; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterTipo_dato(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitTipo_dato(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitTipo_dato(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tipo_datoContext tipo_dato() throws RecognitionException {
		Tipo_datoContext _localctx = new Tipo_datoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tipo_dato);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 36028797018964960L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Variable_asignableContext extends ParserRuleContext {
		public Variable_asignableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_asignable; }
	 
		public Variable_asignableContext() { }
		public void copyFrom(Variable_asignableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ValorAsignableMiembroEstructuraContext extends Variable_asignableContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(PigLatinParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public ValorAsignableMiembroEstructuraContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterValorAsignableMiembroEstructura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitValorAsignableMiembroEstructura(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitValorAsignableMiembroEstructura(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ValorAsignableArrayContext extends Variable_asignableContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode CORCHETE_IZQ() { return getToken(PigLatinParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(PigLatinParser.CORCHETE_DER, 0); }
		public ValorAsignableArrayContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterValorAsignableArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitValorAsignableArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitValorAsignableArray(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ValorAsignableSimpleContext extends Variable_asignableContext {
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public ValorAsignableSimpleContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterValorAsignableSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitValorAsignableSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitValorAsignableSimple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_asignableContext variable_asignable() throws RecognitionException {
		return variable_asignable(0);
	}

	private Variable_asignableContext variable_asignable(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Variable_asignableContext _localctx = new Variable_asignableContext(_ctx, _parentState);
		Variable_asignableContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_variable_asignable, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new ValorAsignableSimpleContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(94);
			match(IDENTIFICADOR);
			}
			_ctx.stop = _input.LT(-1);
			setState(106);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(104);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ValorAsignableArrayContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(96);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(97);
						match(CORCHETE_IZQ);
						setState(98);
						expresion(0);
						setState(99);
						match(CORCHETE_DER);
						}
						break;
					case 2:
						{
						_localctx = new ValorAsignableMiembroEstructuraContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(101);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(102);
						match(PUNTO);
						setState(103);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpresionContext extends ParserRuleContext {
		public ExpresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expresion; }
	 
		public ExpresionContext() { }
		public void copyFrom(ExpresionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNegativaContext extends ExpresionContext {
		public TerminalNode RESTA() { return getToken(PigLatinParser.RESTA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNegativaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprNegativa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprNegativa(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprNegativa(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPreIncrementoContext extends ExpresionContext {
		public TerminalNode INCREMENTO() { return getToken(PigLatinParser.INCREMENTO, 0); }
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public ExprPreIncrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprPreIncremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprPreIncremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprPreIncremento(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprRelacionalContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode IGUAL_QUE() { return getToken(PigLatinParser.IGUAL_QUE, 0); }
		public TerminalNode DIFERENTE_QUE() { return getToken(PigLatinParser.DIFERENTE_QUE, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(PigLatinParser.MAYOR_QUE, 0); }
		public TerminalNode MENOR_QUE() { return getToken(PigLatinParser.MENOR_QUE, 0); }
		public TerminalNode MAYOR_IGUAL_QUE() { return getToken(PigLatinParser.MAYOR_IGUAL_QUE, 0); }
		public TerminalNode MENOR_IGUAL_QUE() { return getToken(PigLatinParser.MENOR_IGUAL_QUE, 0); }
		public ExprRelacionalContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprRelacional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprRelacional(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParentesisContext extends ExpresionContext {
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public ExprParentesisContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprParentesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprParentesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprParentesis(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMultiplicacionDivisionContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MULT() { return getToken(PigLatinParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(PigLatinParser.DIV, 0); }
		public ExprMultiplicacionDivisionContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprMultiplicacionDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprMultiplicacionDivision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprMultiplicacionDivision(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPostIncrementoContext extends ExpresionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode INCREMENTO() { return getToken(PigLatinParser.INCREMENTO, 0); }
		public ExprPostIncrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprPostIncremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprPostIncremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprPostIncremento(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLlamadaMetodoContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(PigLatinParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public ExprLlamadaMetodoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprLlamadaMetodo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprLlamadaMetodo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprLlamadaMetodo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprSumaRestaContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode SUMA() { return getToken(PigLatinParser.SUMA, 0); }
		public TerminalNode RESTA() { return getToken(PigLatinParser.RESTA, 0); }
		public ExprSumaRestaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprSumaResta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprSumaResta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprSumaResta(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprOrContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode OR() { return getToken(PigLatinParser.OR, 0); }
		public ExprOrContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNegadaContext extends ExpresionContext {
		public TerminalNode NON() { return getToken(PigLatinParser.NON, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNegadaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprNegada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprNegada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprNegada(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPrimitivoContext extends ExpresionContext {
		public Valor_primitivoContext valor_primitivo() {
			return getRuleContext(Valor_primitivoContext.class,0);
		}
		public ExprPrimitivoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprPrimitivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprPrimitivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprPrimitivo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPostDecrementoContext extends ExpresionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode DECREMENTO() { return getToken(PigLatinParser.DECREMENTO, 0); }
		public ExprPostDecrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprPostDecremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprPostDecremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprPostDecremento(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAccesoPosicionArrayContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode CORCHETE_IZQ() { return getToken(PigLatinParser.CORCHETE_IZQ, 0); }
		public TerminalNode CORCHETE_DER() { return getToken(PigLatinParser.CORCHETE_DER, 0); }
		public ExprAccesoPosicionArrayContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprAccesoPosicionArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprAccesoPosicionArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprAccesoPosicionArray(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAndContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode AND() { return getToken(PigLatinParser.AND, 0); }
		public ExprAndContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAccesoMiembroEstructuraContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(PigLatinParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public ExprAccesoMiembroEstructuraContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprAccesoMiembroEstructura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprAccesoMiembroEstructura(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprAccesoMiembroEstructura(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLlamadaFuncionContext extends ExpresionContext {
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public ExprLlamadaFuncionContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprLlamadaFuncion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprLlamadaFuncion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprLlamadaFuncion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprListaLiteralContext extends ExpresionContext {
		public TerminalNode LLAVE_IZQ() { return getToken(PigLatinParser.LLAVE_IZQ, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(PigLatinParser.LLAVE_DER, 0); }
		public ExprListaLiteralContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprListaLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprListaLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprListaLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPreDecrementoContext extends ExpresionContext {
		public TerminalNode DECREMENTO() { return getToken(PigLatinParser.DECREMENTO, 0); }
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public ExprPreDecrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprPreDecremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprPreDecremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprPreDecremento(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprInstanciaObjetoContext extends ExpresionContext {
		public TerminalNode NOVUS() { return getToken(PigLatinParser.NOVUS, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public ExprInstanciaObjetoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterExprInstanciaObjeto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitExprInstanciaObjeto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitExprInstanciaObjeto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpresionContext expresion() throws RecognitionException {
		return expresion(0);
	}

	private ExpresionContext expresion(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpresionContext _localctx = new ExpresionContext(_ctx, _parentState);
		ExpresionContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expresion, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new ExprParentesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(110);
				match(PAR_IZQ);
				setState(111);
				expresion(0);
				setState(112);
				match(PAR_DER);
				}
				break;
			case 2:
				{
				_localctx = new ExprInstanciaObjetoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(114);
				match(NOVUS);
				setState(115);
				match(IDENTIFICADOR);
				setState(116);
				match(PAR_IZQ);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
					{
					setState(117);
					lista_expresiones();
					}
				}

				setState(120);
				match(PAR_DER);
				}
				break;
			case 3:
				{
				_localctx = new ExprLlamadaFuncionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(121);
				match(IDENTIFICADOR);
				setState(122);
				match(PAR_IZQ);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
					{
					setState(123);
					lista_expresiones();
					}
				}

				setState(126);
				match(PAR_DER);
				}
				break;
			case 4:
				{
				_localctx = new ExprPostIncrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				variable_asignable(0);
				setState(128);
				match(INCREMENTO);
				}
				break;
			case 5:
				{
				_localctx = new ExprPostDecrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				variable_asignable(0);
				setState(131);
				match(DECREMENTO);
				}
				break;
			case 6:
				{
				_localctx = new ExprListaLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				match(LLAVE_IZQ);
				setState(134);
				lista_expresiones();
				setState(135);
				match(LLAVE_DER);
				}
				break;
			case 7:
				{
				_localctx = new ExprNegativaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				match(RESTA);
				setState(138);
				expresion(10);
				}
				break;
			case 8:
				{
				_localctx = new ExprNegadaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				match(NON);
				setState(140);
				expresion(9);
				}
				break;
			case 9:
				{
				_localctx = new ExprPreIncrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(INCREMENTO);
				setState(142);
				variable_asignable(0);
				}
				break;
			case 10:
				{
				_localctx = new ExprPreDecrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(DECREMENTO);
				setState(144);
				variable_asignable(0);
				}
				break;
			case 11:
				{
				_localctx = new ExprPrimitivoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				valor_primitivo();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(181);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(179);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMultiplicacionDivisionContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(148);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(149);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(150);
						expresion(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprSumaRestaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(151);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(152);
						_la = _input.LA(1);
						if ( !(_la==SUMA || _la==RESTA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(153);
						expresion(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprRelacionalContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(154);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(155);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16911433728L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(156);
						expresion(5);
						}
						break;
					case 4:
						{
						_localctx = new ExprAndContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(157);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(158);
						match(AND);
						setState(159);
						expresion(4);
						}
						break;
					case 5:
						{
						_localctx = new ExprOrContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(160);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(161);
						match(OR);
						setState(162);
						expresion(3);
						}
						break;
					case 6:
						{
						_localctx = new ExprLlamadaMetodoContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(163);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(164);
						match(PUNTO);
						setState(165);
						match(IDENTIFICADOR);
						setState(166);
						match(PAR_IZQ);
						setState(168);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
							{
							setState(167);
							lista_expresiones();
							}
						}

						setState(170);
						match(PAR_DER);
						}
						break;
					case 7:
						{
						_localctx = new ExprAccesoPosicionArrayContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(171);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(172);
						match(CORCHETE_IZQ);
						setState(173);
						expresion(0);
						setState(174);
						match(CORCHETE_DER);
						}
						break;
					case 8:
						{
						_localctx = new ExprAccesoMiembroEstructuraContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(176);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(177);
						match(PUNTO);
						setState(178);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(183);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Valor_primitivoContext extends ParserRuleContext {
		public TerminalNode ENTERO() { return getToken(PigLatinParser.ENTERO, 0); }
		public TerminalNode DECIMAL() { return getToken(PigLatinParser.DECIMAL, 0); }
		public TerminalNode CADENA() { return getToken(PigLatinParser.CADENA, 0); }
		public TerminalNode CARACTER() { return getToken(PigLatinParser.CARACTER, 0); }
		public TerminalNode VERUM() { return getToken(PigLatinParser.VERUM, 0); }
		public TerminalNode FALSUS() { return getToken(PigLatinParser.FALSUS, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public Valor_primitivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valor_primitivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterValor_primitivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitValor_primitivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitValor_primitivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Valor_primitivoContext valor_primitivo() throws RecognitionException {
		Valor_primitivoContext _localctx = new Valor_primitivoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_valor_primitivo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 69805794224245760L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Lista_expresionesContext extends ParserRuleContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(PigLatinParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(PigLatinParser.COMA, i);
		}
		public Lista_expresionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_expresiones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterLista_expresiones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitLista_expresiones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitLista_expresiones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lista_expresionesContext lista_expresiones() throws RecognitionException {
		Lista_expresionesContext _localctx = new Lista_expresionesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lista_expresiones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			expresion(0);
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(187);
				match(COMA);
				setState(188);
				expresion(0);
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Lista_atributos_instanciaContext extends ParserRuleContext {
		public List<Atributo_instanciaContext> atributo_instancia() {
			return getRuleContexts(Atributo_instanciaContext.class);
		}
		public Atributo_instanciaContext atributo_instancia(int i) {
			return getRuleContext(Atributo_instanciaContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(PigLatinParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(PigLatinParser.COMA, i);
		}
		public Lista_atributos_instanciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_atributos_instancia; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterLista_atributos_instancia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitLista_atributos_instancia(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitLista_atributos_instancia(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lista_atributos_instanciaContext lista_atributos_instancia() throws RecognitionException {
		Lista_atributos_instanciaContext _localctx = new Lista_atributos_instanciaContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_lista_atributos_instancia);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			atributo_instancia();
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(195);
					match(COMA);
					setState(196);
					atributo_instancia();
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMA) {
				{
				setState(202);
				match(COMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atributo_instanciaContext extends ParserRuleContext {
		public Atributo_instanciaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributo_instancia; }
	 
		public Atributo_instanciaContext() { }
		public void copyFrom(Atributo_instanciaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CampoConNombreContext extends Atributo_instanciaContext {
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public CampoConNombreContext(Atributo_instanciaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterCampoConNombre(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitCampoConNombre(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitCampoConNombre(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CampoPosicionalContext extends Atributo_instanciaContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public CampoPosicionalContext(Atributo_instanciaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterCampoPosicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitCampoPosicional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitCampoPosicional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atributo_instanciaContext atributo_instancia() throws RecognitionException {
		Atributo_instanciaContext _localctx = new Atributo_instanciaContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_atributo_instancia);
		try {
			setState(209);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new CampoConNombreContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				match(IDENTIFICADOR);
				setState(206);
				match(DOS_PUNTOS);
				setState(207);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new CampoPosicionalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaracion_variableContext extends ParserRuleContext {
		public Declaracion_variableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion_variable; }
	 
		public Declaracion_variableContext() { }
		public void copyFrom(Declaracion_variableContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclArrayConDatosContext extends Declaracion_variableContext {
		public TerminalNode SERIES() { return getToken(PigLatinParser.SERIES, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode CORCHETE_IZQ() { return getToken(PigLatinParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(PigLatinParser.CORCHETE_DER, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode LLAVE_IZQ() { return getToken(PigLatinParser.LLAVE_IZQ, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(PigLatinParser.LLAVE_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public DeclArrayConDatosContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclArrayConDatos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclArrayConDatos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclArrayConDatos(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclEstructuraConValoresContext extends Declaracion_variableContext {
		public TerminalNode ESTO() { return getToken(PigLatinParser.ESTO, 0); }
		public List<TerminalNode> IDENTIFICADOR() { return getTokens(PigLatinParser.IDENTIFICADOR); }
		public TerminalNode IDENTIFICADOR(int i) {
			return getToken(PigLatinParser.IDENTIFICADOR, i);
		}
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(PigLatinParser.LLAVE_IZQ, 0); }
		public Lista_atributos_instanciaContext lista_atributos_instancia() {
			return getRuleContext(Lista_atributos_instanciaContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(PigLatinParser.LLAVE_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public DeclEstructuraConValoresContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclEstructuraConValores(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclEstructuraConValores(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclEstructuraConValores(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclArrayEstructuraContext extends Declaracion_variableContext {
		public TerminalNode SERIES() { return getToken(PigLatinParser.SERIES, 0); }
		public List<TerminalNode> IDENTIFICADOR() { return getTokens(PigLatinParser.IDENTIFICADOR); }
		public TerminalNode IDENTIFICADOR(int i) {
			return getToken(PigLatinParser.IDENTIFICADOR, i);
		}
		public TerminalNode CORCHETE_IZQ() { return getToken(PigLatinParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(PigLatinParser.CORCHETE_DER, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public DeclArrayEstructuraContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclArrayEstructura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclArrayEstructura(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclArrayEstructura(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclConTipoYValorContext extends Declaracion_variableContext {
		public TerminalNode ESTO() { return getToken(PigLatinParser.ESTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(PigLatinParser.ASIGNACION, 0); }
		public DeclConTipoYValorContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclConTipoYValor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclConTipoYValor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclConTipoYValor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclArraySinDatosContext extends Declaracion_variableContext {
		public TerminalNode SERIES() { return getToken(PigLatinParser.SERIES, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode CORCHETE_IZQ() { return getToken(PigLatinParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(PigLatinParser.CORCHETE_DER, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public DeclArraySinDatosContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclArraySinDatos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclArraySinDatos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclArraySinDatos(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclBooleanaImplicitaContext extends Declaracion_variableContext {
		public TerminalNode ESTO() { return getToken(PigLatinParser.ESTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public TerminalNode VERUM() { return getToken(PigLatinParser.VERUM, 0); }
		public TerminalNode FALSUS() { return getToken(PigLatinParser.FALSUS, 0); }
		public DeclBooleanaImplicitaContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclBooleanaImplicita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclBooleanaImplicita(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclBooleanaImplicita(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclMatrizSinDatosContext extends Declaracion_variableContext {
		public TerminalNode SERIES() { return getToken(PigLatinParser.SERIES, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(PigLatinParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(PigLatinParser.CORCHETE_IZQ, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(PigLatinParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(PigLatinParser.CORCHETE_DER, i);
		}
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public DeclMatrizSinDatosContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclMatrizSinDatos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclMatrizSinDatos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclMatrizSinDatos(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclMatrizConDatosContext extends Declaracion_variableContext {
		public TerminalNode SERIES() { return getToken(PigLatinParser.SERIES, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(PigLatinParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(PigLatinParser.CORCHETE_IZQ, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(PigLatinParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(PigLatinParser.CORCHETE_DER, i);
		}
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode LLAVE_IZQ() { return getToken(PigLatinParser.LLAVE_IZQ, 0); }
		public List<Fila_matriz_pigContext> fila_matriz_pig() {
			return getRuleContexts(Fila_matriz_pigContext.class);
		}
		public Fila_matriz_pigContext fila_matriz_pig(int i) {
			return getRuleContext(Fila_matriz_pigContext.class,i);
		}
		public TerminalNode LLAVE_DER() { return getToken(PigLatinParser.LLAVE_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public List<TerminalNode> COMA() { return getTokens(PigLatinParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(PigLatinParser.COMA, i);
		}
		public DeclMatrizConDatosContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclMatrizConDatos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclMatrizConDatos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclMatrizConDatos(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclObjetoNovusContext extends Declaracion_variableContext {
		public TerminalNode ESTO() { return getToken(PigLatinParser.ESTO, 0); }
		public List<TerminalNode> IDENTIFICADOR() { return getTokens(PigLatinParser.IDENTIFICADOR); }
		public TerminalNode IDENTIFICADOR(int i) {
			return getToken(PigLatinParser.IDENTIFICADOR, i);
		}
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public TerminalNode NOVUS() { return getToken(PigLatinParser.NOVUS, 0); }
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public DeclObjetoNovusContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterDeclObjetoNovus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitDeclObjetoNovus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitDeclObjetoNovus(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaracion_variableContext declaracion_variable() throws RecognitionException {
		Declaracion_variableContext _localctx = new Declaracion_variableContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_declaracion_variable);
		int _la;
		try {
			setState(312);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new DeclObjetoNovusContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(211);
				match(ESTO);
				setState(212);
				match(IDENTIFICADOR);
				setState(213);
				match(DOS_PUNTOS);
				setState(214);
				match(NOVUS);
				setState(215);
				match(IDENTIFICADOR);
				setState(216);
				match(PAR_IZQ);
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
					{
					setState(217);
					lista_expresiones();
					}
				}

				setState(220);
				match(PAR_DER);
				setState(221);
				match(PUNTO_Y_COMA);
				}
				break;
			case 2:
				_localctx = new DeclEstructuraConValoresContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(222);
				match(ESTO);
				setState(223);
				match(IDENTIFICADOR);
				setState(224);
				match(DOS_PUNTOS);
				setState(225);
				match(IDENTIFICADOR);
				setState(226);
				match(LLAVE_IZQ);
				setState(227);
				lista_atributos_instancia();
				setState(228);
				match(LLAVE_DER);
				setState(229);
				match(PUNTO_Y_COMA);
				}
				break;
			case 3:
				_localctx = new DeclConTipoYValorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(231);
				match(ESTO);
				setState(232);
				match(IDENTIFICADOR);
				setState(233);
				match(DOS_PUNTOS);
				setState(234);
				tipo_dato();
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850118496472064L) != 0)) {
					{
					setState(236);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ASIGNACION) {
						{
						setState(235);
						match(ASIGNACION);
						}
					}

					setState(238);
					expresion(0);
					}
				}

				setState(241);
				match(PUNTO_Y_COMA);
				}
				break;
			case 4:
				_localctx = new DeclBooleanaImplicitaContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(243);
				match(ESTO);
				setState(244);
				match(IDENTIFICADOR);
				setState(245);
				match(DOS_PUNTOS);
				setState(246);
				_la = _input.LA(1);
				if ( !(_la==VERUM || _la==FALSUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(247);
				match(PUNTO_Y_COMA);
				}
				break;
			case 5:
				_localctx = new DeclArraySinDatosContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(248);
				match(SERIES);
				setState(249);
				match(IDENTIFICADOR);
				setState(250);
				match(CORCHETE_IZQ);
				setState(251);
				expresion(0);
				setState(252);
				match(CORCHETE_DER);
				setState(253);
				match(DOS_PUNTOS);
				setState(254);
				tipo_dato();
				setState(255);
				match(PUNTO_Y_COMA);
				}
				break;
			case 6:
				_localctx = new DeclArrayConDatosContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(257);
				match(SERIES);
				setState(258);
				match(IDENTIFICADOR);
				setState(259);
				match(CORCHETE_IZQ);
				setState(260);
				expresion(0);
				setState(261);
				match(CORCHETE_DER);
				setState(262);
				match(DOS_PUNTOS);
				setState(263);
				tipo_dato();
				setState(264);
				match(LLAVE_IZQ);
				setState(265);
				lista_expresiones();
				setState(266);
				match(LLAVE_DER);
				setState(267);
				match(PUNTO_Y_COMA);
				}
				break;
			case 7:
				_localctx = new DeclArrayEstructuraContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(269);
				match(SERIES);
				setState(270);
				match(IDENTIFICADOR);
				setState(271);
				match(CORCHETE_IZQ);
				setState(272);
				expresion(0);
				setState(273);
				match(CORCHETE_DER);
				setState(274);
				match(DOS_PUNTOS);
				setState(275);
				match(IDENTIFICADOR);
				setState(276);
				match(PUNTO_Y_COMA);
				}
				break;
			case 8:
				_localctx = new DeclMatrizSinDatosContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(278);
				match(SERIES);
				setState(279);
				match(IDENTIFICADOR);
				setState(280);
				match(CORCHETE_IZQ);
				setState(281);
				expresion(0);
				setState(282);
				match(CORCHETE_DER);
				setState(283);
				match(CORCHETE_IZQ);
				setState(284);
				expresion(0);
				setState(285);
				match(CORCHETE_DER);
				setState(286);
				match(DOS_PUNTOS);
				setState(287);
				tipo_dato();
				setState(288);
				match(PUNTO_Y_COMA);
				}
				break;
			case 9:
				_localctx = new DeclMatrizConDatosContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(290);
				match(SERIES);
				setState(291);
				match(IDENTIFICADOR);
				setState(292);
				match(CORCHETE_IZQ);
				setState(293);
				expresion(0);
				setState(294);
				match(CORCHETE_DER);
				setState(295);
				match(CORCHETE_IZQ);
				setState(296);
				expresion(0);
				setState(297);
				match(CORCHETE_DER);
				setState(298);
				match(DOS_PUNTOS);
				setState(299);
				tipo_dato();
				setState(300);
				match(LLAVE_IZQ);
				setState(301);
				fila_matriz_pig();
				setState(306);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMA) {
					{
					{
					setState(302);
					match(COMA);
					setState(303);
					fila_matriz_pig();
					}
					}
					setState(308);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(309);
				match(LLAVE_DER);
				setState(310);
				match(PUNTO_Y_COMA);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Fila_matriz_pigContext extends ParserRuleContext {
		public TerminalNode LLAVE_IZQ() { return getToken(PigLatinParser.LLAVE_IZQ, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(PigLatinParser.LLAVE_DER, 0); }
		public Fila_matriz_pigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fila_matriz_pig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterFila_matriz_pig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitFila_matriz_pig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitFila_matriz_pig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fila_matriz_pigContext fila_matriz_pig() throws RecognitionException {
		Fila_matriz_pigContext _localctx = new Fila_matriz_pigContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_fila_matriz_pig);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			match(LLAVE_IZQ);
			setState(315);
			lista_expresiones();
			setState(316);
			match(LLAVE_DER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AsignacionContext extends ParserRuleContext {
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
	 
		public AsignacionContext() { }
		public void copyFrom(AsignacionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AsignacionGeneralContext extends AsignacionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(PigLatinParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public AsignacionGeneralContext(AsignacionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterAsignacionGeneral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitAsignacionGeneral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitAsignacionGeneral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_asignacion);
		try {
			_localctx = new AsignacionGeneralContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			variable_asignable(0);
			setState(319);
			match(ASIGNACION);
			setState(320);
			expresion(0);
			setState(321);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Instruccion_flujoContext extends ParserRuleContext {
		public Instruccion_flujoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion_flujo; }
	 
		public Instruccion_flujoContext() { }
		public void copyFrom(Instruccion_flujoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtAsignacionContext extends Instruccion_flujoContext {
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public StmtAsignacionContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtAsignacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtAsignacion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtImpresionContext extends Instruccion_flujoContext {
		public Instruccion_impresionContext instruccion_impresion() {
			return getRuleContext(Instruccion_impresionContext.class,0);
		}
		public StmtImpresionContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtImpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtImpresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtImpresion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtPergeContext extends Instruccion_flujoContext {
		public TerminalNode PERGE() { return getToken(PigLatinParser.PERGE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public StmtPergeContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtPerge(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtPerge(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtPerge(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtExpresionContext extends Instruccion_flujoContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public StmtExpresionContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtExpresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtExpresion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtCicloContext extends Instruccion_flujoContext {
		public CicloContext ciclo() {
			return getRuleContext(CicloContext.class,0);
		}
		public StmtCicloContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtCiclo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtCiclo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtCiclo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtLecturaContext extends Instruccion_flujoContext {
		public Instruccion_lecturaContext instruccion_lectura() {
			return getRuleContext(Instruccion_lecturaContext.class,0);
		}
		public StmtLecturaContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtLectura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtLectura(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtLectura(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtInterrumpeContext extends Instruccion_flujoContext {
		public TerminalNode INTERRUMPE() { return getToken(PigLatinParser.INTERRUMPE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public StmtInterrumpeContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtInterrumpe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtInterrumpe(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtInterrumpe(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtCondicionalContext extends Instruccion_flujoContext {
		public CondicionalContext condicional() {
			return getRuleContext(CondicionalContext.class,0);
		}
		public StmtCondicionalContext(Instruccion_flujoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStmtCondicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStmtCondicional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStmtCondicional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruccion_flujoContext instruccion_flujo() throws RecognitionException {
		Instruccion_flujoContext _localctx = new Instruccion_flujoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_instruccion_flujo);
		try {
			setState(335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				_localctx = new StmtAsignacionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				asignacion();
				}
				break;
			case 2:
				_localctx = new StmtCondicionalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(324);
				condicional();
				}
				break;
			case 3:
				_localctx = new StmtCicloContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
				ciclo();
				}
				break;
			case 4:
				_localctx = new StmtLecturaContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(326);
				instruccion_lectura();
				}
				break;
			case 5:
				_localctx = new StmtImpresionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(327);
				instruccion_impresion();
				}
				break;
			case 6:
				_localctx = new StmtInterrumpeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(328);
				match(INTERRUMPE);
				setState(329);
				match(PUNTO_Y_COMA);
				}
				break;
			case 7:
				_localctx = new StmtPergeContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(330);
				match(PERGE);
				setState(331);
				match(PUNTO_Y_COMA);
				}
				break;
			case 8:
				_localctx = new StmtExpresionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(332);
				expresion(0);
				setState(333);
				match(PUNTO_Y_COMA);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BloqueContext extends ParserRuleContext {
		public TerminalNode LLAVE_IZQ() { return getToken(PigLatinParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(PigLatinParser.LLAVE_DER, 0); }
		public List<Instruccion_flujoContext> instruccion_flujo() {
			return getRuleContexts(Instruccion_flujoContext.class);
		}
		public Instruccion_flujoContext instruccion_flujo(int i) {
			return getRuleContext(Instruccion_flujoContext.class,i);
		}
		public BloqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterBloque(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitBloque(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitBloque(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BloqueContext bloque() throws RecognitionException {
		BloqueContext _localctx = new BloqueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_bloque);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			match(LLAVE_IZQ);
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049831422976L) != 0)) {
				{
				{
				setState(338);
				instruccion_flujo();
				}
				}
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(344);
			match(LLAVE_DER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondicionalContext extends ParserRuleContext {
		public CondicionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicional; }
	 
		public CondicionalContext() { }
		public void copyFrom(CondicionalContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementSiContext extends CondicionalContext {
		public TerminalNode SI() { return getToken(PigLatinParser.SI, 0); }
		public List<TerminalNode> PAR_IZQ() { return getTokens(PigLatinParser.PAR_IZQ); }
		public TerminalNode PAR_IZQ(int i) {
			return getToken(PigLatinParser.PAR_IZQ, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> PAR_DER() { return getTokens(PigLatinParser.PAR_DER); }
		public TerminalNode PAR_DER(int i) {
			return getToken(PigLatinParser.PAR_DER, i);
		}
		public List<BloqueContext> bloque() {
			return getRuleContexts(BloqueContext.class);
		}
		public BloqueContext bloque(int i) {
			return getRuleContext(BloqueContext.class,i);
		}
		public TerminalNode FINIS_BLOQUE() { return getToken(PigLatinParser.FINIS_BLOQUE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public List<TerminalNode> ALITER() { return getTokens(PigLatinParser.ALITER); }
		public TerminalNode ALITER(int i) {
			return getToken(PigLatinParser.ALITER, i);
		}
		public StatementSiContext(CondicionalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterStatementSi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitStatementSi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitStatementSi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicionalContext condicional() throws RecognitionException {
		CondicionalContext _localctx = new CondicionalContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_condicional);
		int _la;
		try {
			int _alt;
			_localctx = new StatementSiContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(SI);
			setState(347);
			match(PAR_IZQ);
			setState(348);
			expresion(0);
			setState(349);
			match(PAR_DER);
			setState(350);
			bloque();
			setState(359);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(351);
					match(ALITER);
					setState(352);
					match(PAR_IZQ);
					setState(353);
					expresion(0);
					setState(354);
					match(PAR_DER);
					setState(355);
					bloque();
					}
					} 
				}
				setState(361);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ALITER) {
				{
				setState(362);
				match(ALITER);
				setState(363);
				bloque();
				}
			}

			setState(366);
			match(FINIS_BLOQUE);
			setState(367);
			match(PUNTO_Y_COMA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CicloContext extends ParserRuleContext {
		public CicloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ciclo; }
	 
		public CicloContext() { }
		public void copyFrom(CicloContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CicloFacereContext extends CicloContext {
		public TerminalNode FACERE() { return getToken(PigLatinParser.FACERE, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode DUM() { return getToken(PigLatinParser.DUM, 0); }
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public CicloFacereContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterCicloFacere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitCicloFacere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitCicloFacere(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CicloPerContext extends CicloContext {
		public TerminalNode PER() { return getToken(PigLatinParser.PER, 0); }
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public Init_perContext init_per() {
			return getRuleContext(Init_perContext.class,0);
		}
		public List<TerminalNode> PUNTO_Y_COMA() { return getTokens(PigLatinParser.PUNTO_Y_COMA); }
		public TerminalNode PUNTO_Y_COMA(int i) {
			return getToken(PigLatinParser.PUNTO_Y_COMA, i);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public Paso_perContext paso_per() {
			return getRuleContext(Paso_perContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINIS_BLOQUE() { return getToken(PigLatinParser.FINIS_BLOQUE, 0); }
		public CicloPerContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterCicloPer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitCicloPer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitCicloPer(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CicloDumContext extends CicloContext {
		public TerminalNode DUM() { return getToken(PigLatinParser.DUM, 0); }
		public TerminalNode PAR_IZQ() { return getToken(PigLatinParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(PigLatinParser.PAR_DER, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode FINIS_BLOQUE() { return getToken(PigLatinParser.FINIS_BLOQUE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public CicloDumContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterCicloDum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitCicloDum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitCicloDum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CicloContext ciclo() throws RecognitionException {
		CicloContext _localctx = new CicloContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_ciclo);
		int _la;
		try {
			setState(398);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DUM:
				_localctx = new CicloDumContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				match(DUM);
				setState(370);
				match(PAR_IZQ);
				setState(371);
				expresion(0);
				setState(372);
				match(PAR_DER);
				setState(373);
				bloque();
				setState(374);
				match(FINIS_BLOQUE);
				setState(375);
				match(PUNTO_Y_COMA);
				}
				break;
			case FACERE:
				_localctx = new CicloFacereContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(FACERE);
				setState(378);
				bloque();
				setState(379);
				match(DUM);
				setState(380);
				match(PAR_IZQ);
				setState(381);
				expresion(0);
				setState(382);
				match(PAR_DER);
				setState(383);
				match(PUNTO_Y_COMA);
				}
				break;
			case PER:
				_localctx = new CicloPerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(385);
				match(PER);
				setState(386);
				match(PAR_IZQ);
				setState(387);
				init_per();
				setState(388);
				match(PUNTO_Y_COMA);
				setState(389);
				expresion(0);
				setState(390);
				match(PUNTO_Y_COMA);
				setState(391);
				paso_per();
				setState(392);
				match(PAR_DER);
				setState(393);
				bloque();
				setState(396);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINIS_BLOQUE) {
					{
					setState(394);
					match(FINIS_BLOQUE);
					setState(395);
					match(PUNTO_Y_COMA);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Init_perContext extends ParserRuleContext {
		public Init_perContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init_per; }
	 
		public Init_perContext() { }
		public void copyFrom(Init_perContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InitPerAsigContext extends Init_perContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(PigLatinParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public InitPerAsigContext(Init_perContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterInitPerAsig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitInitPerAsig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitInitPerAsig(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InitPerDeclContext extends Init_perContext {
		public TerminalNode ESTO() { return getToken(PigLatinParser.ESTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(PigLatinParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(PigLatinParser.DOS_PUNTOS, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(PigLatinParser.ASIGNACION, 0); }
		public InitPerDeclContext(Init_perContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterInitPerDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitInitPerDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitInitPerDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Init_perContext init_per() throws RecognitionException {
		Init_perContext _localctx = new Init_perContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_init_per);
		int _la;
		try {
			setState(413);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESTO:
				_localctx = new InitPerDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(400);
				match(ESTO);
				setState(401);
				match(IDENTIFICADOR);
				setState(402);
				match(DOS_PUNTOS);
				setState(403);
				tipo_dato();
				setState(405);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASIGNACION) {
					{
					setState(404);
					match(ASIGNACION);
					}
				}

				setState(407);
				expresion(0);
				}
				break;
			case IDENTIFICADOR:
				_localctx = new InitPerAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				variable_asignable(0);
				setState(410);
				match(ASIGNACION);
				setState(411);
				expresion(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Paso_perContext extends ParserRuleContext {
		public Paso_perContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paso_per; }
	 
		public Paso_perContext() { }
		public void copyFrom(Paso_perContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PasoPerExprContext extends Paso_perContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public PasoPerExprContext(Paso_perContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterPasoPerExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitPasoPerExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitPasoPerExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PasoPerAsigContext extends Paso_perContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(PigLatinParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public PasoPerAsigContext(Paso_perContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterPasoPerAsig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitPasoPerAsig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitPasoPerAsig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Paso_perContext paso_per() throws RecognitionException {
		Paso_perContext _localctx = new Paso_perContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_paso_per);
		try {
			setState(420);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				_localctx = new PasoPerExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(415);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new PasoPerAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				variable_asignable(0);
				setState(417);
				match(ASIGNACION);
				setState(418);
				expresion(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Instruccion_lecturaContext extends ParserRuleContext {
		public Instruccion_lecturaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion_lectura; }
	 
		public Instruccion_lecturaContext() { }
		public void copyFrom(Instruccion_lecturaContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LecturaConsolaSimpleContext extends Instruccion_lecturaContext {
		public TerminalNode LEER() { return getToken(PigLatinParser.LEER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public LecturaConsolaSimpleContext(Instruccion_lecturaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterLecturaConsolaSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitLecturaConsolaSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitLecturaConsolaSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LecturaConsolaAVariableContext extends Instruccion_lecturaContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode LEER() { return getToken(PigLatinParser.LEER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public LecturaConsolaAVariableContext(Instruccion_lecturaContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterLecturaConsolaAVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitLecturaConsolaAVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitLecturaConsolaAVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruccion_lecturaContext instruccion_lectura() throws RecognitionException {
		Instruccion_lecturaContext _localctx = new Instruccion_lecturaContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_instruccion_lectura);
		int _la;
		try {
			setState(431);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEER:
				_localctx = new LecturaConsolaSimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(422);
				match(LEER);
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUNTO_Y_COMA) {
					{
					setState(423);
					match(PUNTO_Y_COMA);
					}
				}

				}
				break;
			case IDENTIFICADOR:
				_localctx = new LecturaConsolaAVariableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(426);
				variable_asignable(0);
				setState(427);
				match(LEER);
				setState(429);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUNTO_Y_COMA) {
					{
					setState(428);
					match(PUNTO_Y_COMA);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Instruccion_impresionContext extends ParserRuleContext {
		public Instruccion_impresionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion_impresion; }
	 
		public Instruccion_impresionContext() { }
		public void copyFrom(Instruccion_impresionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImpresionEncadenadaContext extends Instruccion_impresionContext {
		public List<TerminalNode> IMPRIMIR() { return getTokens(PigLatinParser.IMPRIMIR); }
		public TerminalNode IMPRIMIR(int i) {
			return getToken(PigLatinParser.IMPRIMIR, i);
		}
		public List<Elemento_imprimirContext> elemento_imprimir() {
			return getRuleContexts(Elemento_imprimirContext.class);
		}
		public Elemento_imprimirContext elemento_imprimir(int i) {
			return getRuleContext(Elemento_imprimirContext.class,i);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(PigLatinParser.PUNTO_Y_COMA, 0); }
		public ImpresionEncadenadaContext(Instruccion_impresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterImpresionEncadenada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitImpresionEncadenada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitImpresionEncadenada(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instruccion_impresionContext instruccion_impresion() throws RecognitionException {
		Instruccion_impresionContext _localctx = new Instruccion_impresionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_instruccion_impresion);
		int _la;
		try {
			int _alt;
			_localctx = new ImpresionEncadenadaContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(433);
			match(IMPRIMIR);
			setState(434);
			elemento_imprimir();
			setState(439);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(435);
					match(IMPRIMIR);
					setState(436);
					elemento_imprimir();
					}
					} 
				}
				setState(441);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			}
			setState(443);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNTO_Y_COMA) {
				{
				setState(442);
				match(PUNTO_Y_COMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Elemento_imprimirContext extends ParserRuleContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public Elemento_imprimirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elemento_imprimir; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).enterElemento_imprimir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PigLatinListener ) ((PigLatinListener)listener).exitElemento_imprimir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PigLatinVisitor ) return ((PigLatinVisitor<? extends T>)visitor).visitElemento_imprimir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Elemento_imprimirContext elemento_imprimir() throws RecognitionException {
		Elemento_imprimirContext _localctx = new Elemento_imprimirContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_elemento_imprimir);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			expresion(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return variable_asignable_sempred((Variable_asignableContext)_localctx, predIndex);
		case 7:
			return expresion_sempred((ExpresionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean variable_asignable_sempred(Variable_asignableContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expresion_sempred(ExpresionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 3);
		case 6:
			return precpred(_ctx, 2);
		case 7:
			return precpred(_ctx, 16);
		case 8:
			return precpred(_ctx, 15);
		case 9:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001:\u01c0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0003\u0000"+
		"2\b\u0000\u0001\u0000\u0003\u00005\b\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0005\u0001<\b\u0001\n\u0001\f\u0001?\t"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002D\b\u0002\n\u0002"+
		"\f\u0002G\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003L\b\u0003"+
		"\n\u0003\f\u0003O\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"T\b\u0004\n\u0004\f\u0004W\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006i\b\u0006\n\u0006\f\u0006l\t\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007w\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007}\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u0093\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a9\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00b4\b\u0007\n\u0007"+
		"\f\u0007\u00b7\t\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0005\t"+
		"\u00be\b\t\n\t\f\t\u00c1\t\t\u0001\n\u0001\n\u0001\n\u0005\n\u00c6\b\n"+
		"\n\n\f\n\u00c9\t\n\u0001\n\u0003\n\u00cc\b\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00d2\b\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u00db\b\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0003\f\u00ed\b\f\u0001\f\u0003\f\u00f0\b\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0005\f\u0131\b\f\n\f\f\f\u0134\t\f\u0001\f\u0001\f\u0001\f\u0003\f"+
		"\u0139\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0150\b\u000f\u0001\u0010\u0001"+
		"\u0010\u0005\u0010\u0154\b\u0010\n\u0010\f\u0010\u0157\t\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0005\u0011\u0166\b\u0011\n\u0011\f\u0011\u0169\t\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u016d\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u018d\b\u0012\u0003\u0012\u018f"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u0196\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u019e\b\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u01a5\b\u0014\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u01a9\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003"+
		"\u0015\u01ae\b\u0015\u0003\u0015\u01b0\b\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0005\u0016\u01b6\b\u0016\n\u0016\f\u0016\u01b9"+
		"\t\u0016\u0001\u0016\u0003\u0016\u01bc\b\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0000\u0002\f\u000e\u0018\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.\u0000\u0006"+
		"\u0002\u0000\u0005\t77\u0001\u0000\'(\u0001\u0000%&\u0001\u0000\u001c"+
		"!\u0002\u0000\n\u000b37\u0001\u0000\n\u000b\u01e9\u00001\u0001\u0000\u0000"+
		"\u0000\u0002=\u0001\u0000\u0000\u0000\u0004@\u0001\u0000\u0000\u0000\u0006"+
		"H\u0001\u0000\u0000\u0000\bP\u0001\u0000\u0000\u0000\n[\u0001\u0000\u0000"+
		"\u0000\f]\u0001\u0000\u0000\u0000\u000e\u0092\u0001\u0000\u0000\u0000"+
		"\u0010\u00b8\u0001\u0000\u0000\u0000\u0012\u00ba\u0001\u0000\u0000\u0000"+
		"\u0014\u00c2\u0001\u0000\u0000\u0000\u0016\u00d1\u0001\u0000\u0000\u0000"+
		"\u0018\u0138\u0001\u0000\u0000\u0000\u001a\u013a\u0001\u0000\u0000\u0000"+
		"\u001c\u013e\u0001\u0000\u0000\u0000\u001e\u014f\u0001\u0000\u0000\u0000"+
		" \u0151\u0001\u0000\u0000\u0000\"\u015a\u0001\u0000\u0000\u0000$\u018e"+
		"\u0001\u0000\u0000\u0000&\u019d\u0001\u0000\u0000\u0000(\u01a4\u0001\u0000"+
		"\u0000\u0000*\u01af\u0001\u0000\u0000\u0000,\u01b1\u0001\u0000\u0000\u0000"+
		".\u01bd\u0001\u0000\u0000\u000002\u0003\u0002\u0001\u000010\u0001\u0000"+
		"\u0000\u000012\u0001\u0000\u0000\u000024\u0001\u0000\u0000\u000035\u0003"+
		"\u0006\u0003\u000043\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u0000"+
		"56\u0001\u0000\u0000\u000067\u0003\b\u0004\u000078\u0005\u0000\u0000\u0001"+
		"8\u0001\u0001\u0000\u0000\u00009:\u0005\u0001\u0000\u0000:<\u0003\u0004"+
		"\u0002\u0000;9\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001"+
		"\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>\u0003\u0001\u0000\u0000"+
		"\u0000?=\u0001\u0000\u0000\u0000@E\u00057\u0000\u0000AB\u00052\u0000\u0000"+
		"BD\u00057\u0000\u0000CA\u0001\u0000\u0000\u0000DG\u0001\u0000\u0000\u0000"+
		"EC\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000\u0000F\u0005\u0001\u0000"+
		"\u0000\u0000GE\u0001\u0000\u0000\u0000HI\u0005\u0002\u0000\u0000IM\u0005"+
		" \u0000\u0000JL\u0003\u0018\f\u0000KJ\u0001\u0000\u0000\u0000LO\u0001"+
		"\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"N\u0007\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005\u0003"+
		"\u0000\u0000QU\u0005 \u0000\u0000RT\u0003\u001e\u000f\u0000SR\u0001\u0000"+
		"\u0000\u0000TW\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001"+
		"\u0000\u0000\u0000VX\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000"+
		"XY\u0005\u0004\u0000\u0000YZ\u0005/\u0000\u0000Z\t\u0001\u0000\u0000\u0000"+
		"[\\\u0007\u0000\u0000\u0000\\\u000b\u0001\u0000\u0000\u0000]^\u0006\u0006"+
		"\uffff\uffff\u0000^_\u00057\u0000\u0000_j\u0001\u0000\u0000\u0000`a\n"+
		"\u0002\u0000\u0000ab\u0005)\u0000\u0000bc\u0003\u000e\u0007\u0000cd\u0005"+
		"*\u0000\u0000di\u0001\u0000\u0000\u0000ef\n\u0001\u0000\u0000fg\u0005"+
		"2\u0000\u0000gi\u00057\u0000\u0000h`\u0001\u0000\u0000\u0000he\u0001\u0000"+
		"\u0000\u0000il\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001"+
		"\u0000\u0000\u0000k\r\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000"+
		"mn\u0006\u0007\uffff\uffff\u0000no\u0005-\u0000\u0000op\u0003\u000e\u0007"+
		"\u0000pq\u0005.\u0000\u0000q\u0093\u0001\u0000\u0000\u0000rs\u0005\u000e"+
		"\u0000\u0000st\u00057\u0000\u0000tv\u0005-\u0000\u0000uw\u0003\u0012\t"+
		"\u0000vu\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u0001\u0000"+
		"\u0000\u0000x\u0093\u0005.\u0000\u0000yz\u00057\u0000\u0000z|\u0005-\u0000"+
		"\u0000{}\u0003\u0012\t\u0000|{\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000"+
		"\u0000}~\u0001\u0000\u0000\u0000~\u0093\u0005.\u0000\u0000\u007f\u0080"+
		"\u0003\f\u0006\u0000\u0080\u0081\u0005\u001a\u0000\u0000\u0081\u0093\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0003\f\u0006\u0000\u0083\u0084\u0005\u001b"+
		"\u0000\u0000\u0084\u0093\u0001\u0000\u0000\u0000\u0085\u0086\u0005+\u0000"+
		"\u0000\u0086\u0087\u0003\u0012\t\u0000\u0087\u0088\u0005,\u0000\u0000"+
		"\u0088\u0093\u0001\u0000\u0000\u0000\u0089\u008a\u0005&\u0000\u0000\u008a"+
		"\u0093\u0003\u000e\u0007\n\u008b\u008c\u0005\u0017\u0000\u0000\u008c\u0093"+
		"\u0003\u000e\u0007\t\u008d\u008e\u0005\u001a\u0000\u0000\u008e\u0093\u0003"+
		"\f\u0006\u0000\u008f\u0090\u0005\u001b\u0000\u0000\u0090\u0093\u0003\f"+
		"\u0006\u0000\u0091\u0093\u0003\u0010\b\u0000\u0092m\u0001\u0000\u0000"+
		"\u0000\u0092r\u0001\u0000\u0000\u0000\u0092y\u0001\u0000\u0000\u0000\u0092"+
		"\u007f\u0001\u0000\u0000\u0000\u0092\u0082\u0001\u0000\u0000\u0000\u0092"+
		"\u0085\u0001\u0000\u0000\u0000\u0092\u0089\u0001\u0000\u0000\u0000\u0092"+
		"\u008b\u0001\u0000\u0000\u0000\u0092\u008d\u0001\u0000\u0000\u0000\u0092"+
		"\u008f\u0001\u0000\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093"+
		"\u00b5\u0001\u0000\u0000\u0000\u0094\u0095\n\u0006\u0000\u0000\u0095\u0096"+
		"\u0007\u0001\u0000\u0000\u0096\u00b4\u0003\u000e\u0007\u0007\u0097\u0098"+
		"\n\u0005\u0000\u0000\u0098\u0099\u0007\u0002\u0000\u0000\u0099\u00b4\u0003"+
		"\u000e\u0007\u0006\u009a\u009b\n\u0004\u0000\u0000\u009b\u009c\u0007\u0003"+
		"\u0000\u0000\u009c\u00b4\u0003\u000e\u0007\u0005\u009d\u009e\n\u0003\u0000"+
		"\u0000\u009e\u009f\u0005\"\u0000\u0000\u009f\u00b4\u0003\u000e\u0007\u0004"+
		"\u00a0\u00a1\n\u0002\u0000\u0000\u00a1\u00a2\u0005#\u0000\u0000\u00a2"+
		"\u00b4\u0003\u000e\u0007\u0003\u00a3\u00a4\n\u0010\u0000\u0000\u00a4\u00a5"+
		"\u00052\u0000\u0000\u00a5\u00a6\u00057\u0000\u0000\u00a6\u00a8\u0005-"+
		"\u0000\u0000\u00a7\u00a9\u0003\u0012\t\u0000\u00a8\u00a7\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000"+
		"\u0000\u00aa\u00b4\u0005.\u0000\u0000\u00ab\u00ac\n\u000f\u0000\u0000"+
		"\u00ac\u00ad\u0005)\u0000\u0000\u00ad\u00ae\u0003\u000e\u0007\u0000\u00ae"+
		"\u00af\u0005*\u0000\u0000\u00af\u00b4\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\n\u000e\u0000\u0000\u00b1\u00b2\u00052\u0000\u0000\u00b2\u00b4\u0005"+
		"7\u0000\u0000\u00b3\u0094\u0001\u0000\u0000\u0000\u00b3\u0097\u0001\u0000"+
		"\u0000\u0000\u00b3\u009a\u0001\u0000\u0000\u0000\u00b3\u009d\u0001\u0000"+
		"\u0000\u0000\u00b3\u00a0\u0001\u0000\u0000\u0000\u00b3\u00a3\u0001\u0000"+
		"\u0000\u0000\u00b3\u00ab\u0001\u0000\u0000\u0000\u00b3\u00b0\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b7\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u000f\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b8\u00b9\u0007\u0004"+
		"\u0000\u0000\u00b9\u0011\u0001\u0000\u0000\u0000\u00ba\u00bf\u0003\u000e"+
		"\u0007\u0000\u00bb\u00bc\u00051\u0000\u0000\u00bc\u00be\u0003\u000e\u0007"+
		"\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000"+
		"\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000"+
		"\u0000\u00c0\u0013\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c7\u0003\u0016\u000b\u0000\u00c3\u00c4\u00051\u0000\u0000"+
		"\u00c4\u00c6\u0003\u0016\u000b\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00cb\u0001\u0000\u0000\u0000"+
		"\u00c9\u00c7\u0001\u0000\u0000\u0000\u00ca\u00cc\u00051\u0000\u0000\u00cb"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc"+
		"\u0015\u0001\u0000\u0000\u0000\u00cd\u00ce\u00057\u0000\u0000\u00ce\u00cf"+
		"\u00050\u0000\u0000\u00cf\u00d2\u0003\u000e\u0007\u0000\u00d0\u00d2\u0003"+
		"\u000e\u0007\u0000\u00d1\u00cd\u0001\u0000\u0000\u0000\u00d1\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d2\u0017\u0001\u0000\u0000\u0000\u00d3\u00d4\u0005"+
		"\f\u0000\u0000\u00d4\u00d5\u00057\u0000\u0000\u00d5\u00d6\u00050\u0000"+
		"\u0000\u00d6\u00d7\u0005\u000e\u0000\u0000\u00d7\u00d8\u00057\u0000\u0000"+
		"\u00d8\u00da\u0005-\u0000\u0000\u00d9\u00db\u0003\u0012\t\u0000\u00da"+
		"\u00d9\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db"+
		"\u00dc\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005.\u0000\u0000\u00dd\u0139"+
		"\u0005/\u0000\u0000\u00de\u00df\u0005\f\u0000\u0000\u00df\u00e0\u0005"+
		"7\u0000\u0000\u00e0\u00e1\u00050\u0000\u0000\u00e1\u00e2\u00057\u0000"+
		"\u0000\u00e2\u00e3\u0005+\u0000\u0000\u00e3\u00e4\u0003\u0014\n\u0000"+
		"\u00e4\u00e5\u0005,\u0000\u0000\u00e5\u00e6\u0005/\u0000\u0000\u00e6\u0139"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\f\u0000\u0000\u00e8\u00e9\u0005"+
		"7\u0000\u0000\u00e9\u00ea\u00050\u0000\u0000\u00ea\u00ef\u0003\n\u0005"+
		"\u0000\u00eb\u00ed\u0005$\u0000\u0000\u00ec\u00eb\u0001\u0000\u0000\u0000"+
		"\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000"+
		"\u00ee\u00f0\u0003\u000e\u0007\u0000\u00ef\u00ec\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f0\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f1\u00f2\u0005/\u0000\u0000\u00f2\u0139\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f4\u0005\f\u0000\u0000\u00f4\u00f5\u00057\u0000\u0000\u00f5\u00f6"+
		"\u00050\u0000\u0000\u00f6\u00f7\u0007\u0005\u0000\u0000\u00f7\u0139\u0005"+
		"/\u0000\u0000\u00f8\u00f9\u0005\r\u0000\u0000\u00f9\u00fa\u00057\u0000"+
		"\u0000\u00fa\u00fb\u0005)\u0000\u0000\u00fb\u00fc\u0003\u000e\u0007\u0000"+
		"\u00fc\u00fd\u0005*\u0000\u0000\u00fd\u00fe\u00050\u0000\u0000\u00fe\u00ff"+
		"\u0003\n\u0005\u0000\u00ff\u0100\u0005/\u0000\u0000\u0100\u0139\u0001"+
		"\u0000\u0000\u0000\u0101\u0102\u0005\r\u0000\u0000\u0102\u0103\u00057"+
		"\u0000\u0000\u0103\u0104\u0005)\u0000\u0000\u0104\u0105\u0003\u000e\u0007"+
		"\u0000\u0105\u0106\u0005*\u0000\u0000\u0106\u0107\u00050\u0000\u0000\u0107"+
		"\u0108\u0003\n\u0005\u0000\u0108\u0109\u0005+\u0000\u0000\u0109\u010a"+
		"\u0003\u0012\t\u0000\u010a\u010b\u0005,\u0000\u0000\u010b\u010c\u0005"+
		"/\u0000\u0000\u010c\u0139\u0001\u0000\u0000\u0000\u010d\u010e\u0005\r"+
		"\u0000\u0000\u010e\u010f\u00057\u0000\u0000\u010f\u0110\u0005)\u0000\u0000"+
		"\u0110\u0111\u0003\u000e\u0007\u0000\u0111\u0112\u0005*\u0000\u0000\u0112"+
		"\u0113\u00050\u0000\u0000\u0113\u0114\u00057\u0000\u0000\u0114\u0115\u0005"+
		"/\u0000\u0000\u0115\u0139\u0001\u0000\u0000\u0000\u0116\u0117\u0005\r"+
		"\u0000\u0000\u0117\u0118\u00057\u0000\u0000\u0118\u0119\u0005)\u0000\u0000"+
		"\u0119\u011a\u0003\u000e\u0007\u0000\u011a\u011b\u0005*\u0000\u0000\u011b"+
		"\u011c\u0005)\u0000\u0000\u011c\u011d\u0003\u000e\u0007\u0000\u011d\u011e"+
		"\u0005*\u0000\u0000\u011e\u011f\u00050\u0000\u0000\u011f\u0120\u0003\n"+
		"\u0005\u0000\u0120\u0121\u0005/\u0000\u0000\u0121\u0139\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0005\r\u0000\u0000\u0123\u0124\u00057\u0000\u0000"+
		"\u0124\u0125\u0005)\u0000\u0000\u0125\u0126\u0003\u000e\u0007\u0000\u0126"+
		"\u0127\u0005*\u0000\u0000\u0127\u0128\u0005)\u0000\u0000\u0128\u0129\u0003"+
		"\u000e\u0007\u0000\u0129\u012a\u0005*\u0000\u0000\u012a\u012b\u00050\u0000"+
		"\u0000\u012b\u012c\u0003\n\u0005\u0000\u012c\u012d\u0005+\u0000\u0000"+
		"\u012d\u0132\u0003\u001a\r\u0000\u012e\u012f\u00051\u0000\u0000\u012f"+
		"\u0131\u0003\u001a\r\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u0131\u0134"+
		"\u0001\u0000\u0000\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0132\u0133"+
		"\u0001\u0000\u0000\u0000\u0133\u0135\u0001\u0000\u0000\u0000\u0134\u0132"+
		"\u0001\u0000\u0000\u0000\u0135\u0136\u0005,\u0000\u0000\u0136\u0137\u0005"+
		"/\u0000\u0000\u0137\u0139\u0001\u0000\u0000\u0000\u0138\u00d3\u0001\u0000"+
		"\u0000\u0000\u0138\u00de\u0001\u0000\u0000\u0000\u0138\u00e7\u0001\u0000"+
		"\u0000\u0000\u0138\u00f3\u0001\u0000\u0000\u0000\u0138\u00f8\u0001\u0000"+
		"\u0000\u0000\u0138\u0101\u0001\u0000\u0000\u0000\u0138\u010d\u0001\u0000"+
		"\u0000\u0000\u0138\u0116\u0001\u0000\u0000\u0000\u0138\u0122\u0001\u0000"+
		"\u0000\u0000\u0139\u0019\u0001\u0000\u0000\u0000\u013a\u013b\u0005+\u0000"+
		"\u0000\u013b\u013c\u0003\u0012\t\u0000\u013c\u013d\u0005,\u0000\u0000"+
		"\u013d\u001b\u0001\u0000\u0000\u0000\u013e\u013f\u0003\f\u0006\u0000\u013f"+
		"\u0140\u0005$\u0000\u0000\u0140\u0141\u0003\u000e\u0007\u0000\u0141\u0142"+
		"\u0005/\u0000\u0000\u0142\u001d\u0001\u0000\u0000\u0000\u0143\u0150\u0003"+
		"\u001c\u000e\u0000\u0144\u0150\u0003\"\u0011\u0000\u0145\u0150\u0003$"+
		"\u0012\u0000\u0146\u0150\u0003*\u0015\u0000\u0147\u0150\u0003,\u0016\u0000"+
		"\u0148\u0149\u0005\u0014\u0000\u0000\u0149\u0150\u0005/\u0000\u0000\u014a"+
		"\u014b\u0005\u0015\u0000\u0000\u014b\u0150\u0005/\u0000\u0000\u014c\u014d"+
		"\u0003\u000e\u0007\u0000\u014d\u014e\u0005/\u0000\u0000\u014e\u0150\u0001"+
		"\u0000\u0000\u0000\u014f\u0143\u0001\u0000\u0000\u0000\u014f\u0144\u0001"+
		"\u0000\u0000\u0000\u014f\u0145\u0001\u0000\u0000\u0000\u014f\u0146\u0001"+
		"\u0000\u0000\u0000\u014f\u0147\u0001\u0000\u0000\u0000\u014f\u0148\u0001"+
		"\u0000\u0000\u0000\u014f\u014a\u0001\u0000\u0000\u0000\u014f\u014c\u0001"+
		"\u0000\u0000\u0000\u0150\u001f\u0001\u0000\u0000\u0000\u0151\u0155\u0005"+
		"+\u0000\u0000\u0152\u0154\u0003\u001e\u000f\u0000\u0153\u0152\u0001\u0000"+
		"\u0000\u0000\u0154\u0157\u0001\u0000\u0000\u0000\u0155\u0153\u0001\u0000"+
		"\u0000\u0000\u0155\u0156\u0001\u0000\u0000\u0000\u0156\u0158\u0001\u0000"+
		"\u0000\u0000\u0157\u0155\u0001\u0000\u0000\u0000\u0158\u0159\u0005,\u0000"+
		"\u0000\u0159!\u0001\u0000\u0000\u0000\u015a\u015b\u0005\u000f\u0000\u0000"+
		"\u015b\u015c\u0005-\u0000\u0000\u015c\u015d\u0003\u000e\u0007\u0000\u015d"+
		"\u015e\u0005.\u0000\u0000\u015e\u0167\u0003 \u0010\u0000\u015f\u0160\u0005"+
		"\u0010\u0000\u0000\u0160\u0161\u0005-\u0000\u0000\u0161\u0162\u0003\u000e"+
		"\u0007\u0000\u0162\u0163\u0005.\u0000\u0000\u0163\u0164\u0003 \u0010\u0000"+
		"\u0164\u0166\u0001\u0000\u0000\u0000\u0165\u015f\u0001\u0000\u0000\u0000"+
		"\u0166\u0169\u0001\u0000\u0000\u0000\u0167\u0165\u0001\u0000\u0000\u0000"+
		"\u0167\u0168\u0001\u0000\u0000\u0000\u0168\u016c\u0001\u0000\u0000\u0000"+
		"\u0169\u0167\u0001\u0000\u0000\u0000\u016a\u016b\u0005\u0010\u0000\u0000"+
		"\u016b\u016d\u0003 \u0010\u0000\u016c\u016a\u0001\u0000\u0000\u0000\u016c"+
		"\u016d\u0001\u0000\u0000\u0000\u016d\u016e\u0001\u0000\u0000\u0000\u016e"+
		"\u016f\u0005\u0016\u0000\u0000\u016f\u0170\u0005/\u0000\u0000\u0170#\u0001"+
		"\u0000\u0000\u0000\u0171\u0172\u0005\u0011\u0000\u0000\u0172\u0173\u0005"+
		"-\u0000\u0000\u0173\u0174\u0003\u000e\u0007\u0000\u0174\u0175\u0005.\u0000"+
		"\u0000\u0175\u0176\u0003 \u0010\u0000\u0176\u0177\u0005\u0016\u0000\u0000"+
		"\u0177\u0178\u0005/\u0000\u0000\u0178\u018f\u0001\u0000\u0000\u0000\u0179"+
		"\u017a\u0005\u0012\u0000\u0000\u017a\u017b\u0003 \u0010\u0000\u017b\u017c"+
		"\u0005\u0011\u0000\u0000\u017c\u017d\u0005-\u0000\u0000\u017d\u017e\u0003"+
		"\u000e\u0007\u0000\u017e\u017f\u0005.\u0000\u0000\u017f\u0180\u0005/\u0000"+
		"\u0000\u0180\u018f\u0001\u0000\u0000\u0000\u0181\u0182\u0005\u0013\u0000"+
		"\u0000\u0182\u0183\u0005-\u0000\u0000\u0183\u0184\u0003&\u0013\u0000\u0184"+
		"\u0185\u0005/\u0000\u0000\u0185\u0186\u0003\u000e\u0007\u0000\u0186\u0187"+
		"\u0005/\u0000\u0000\u0187\u0188\u0003(\u0014\u0000\u0188\u0189\u0005."+
		"\u0000\u0000\u0189\u018c\u0003 \u0010\u0000\u018a\u018b\u0005\u0016\u0000"+
		"\u0000\u018b\u018d\u0005/\u0000\u0000\u018c\u018a\u0001\u0000\u0000\u0000"+
		"\u018c\u018d\u0001\u0000\u0000\u0000\u018d\u018f\u0001\u0000\u0000\u0000"+
		"\u018e\u0171\u0001\u0000\u0000\u0000\u018e\u0179\u0001\u0000\u0000\u0000"+
		"\u018e\u0181\u0001\u0000\u0000\u0000\u018f%\u0001\u0000\u0000\u0000\u0190"+
		"\u0191\u0005\f\u0000\u0000\u0191\u0192\u00057\u0000\u0000\u0192\u0193"+
		"\u00050\u0000\u0000\u0193\u0195\u0003\n\u0005\u0000\u0194\u0196\u0005"+
		"$\u0000\u0000\u0195\u0194\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000"+
		"\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u0198\u0003\u000e"+
		"\u0007\u0000\u0198\u019e\u0001\u0000\u0000\u0000\u0199\u019a\u0003\f\u0006"+
		"\u0000\u019a\u019b\u0005$\u0000\u0000\u019b\u019c\u0003\u000e\u0007\u0000"+
		"\u019c\u019e\u0001\u0000\u0000\u0000\u019d\u0190\u0001\u0000\u0000\u0000"+
		"\u019d\u0199\u0001\u0000\u0000\u0000\u019e\'\u0001\u0000\u0000\u0000\u019f"+
		"\u01a5\u0003\u000e\u0007\u0000\u01a0\u01a1\u0003\f\u0006\u0000\u01a1\u01a2"+
		"\u0005$\u0000\u0000\u01a2\u01a3\u0003\u000e\u0007\u0000\u01a3\u01a5\u0001"+
		"\u0000\u0000\u0000\u01a4\u019f\u0001\u0000\u0000\u0000\u01a4\u01a0\u0001"+
		"\u0000\u0000\u0000\u01a5)\u0001\u0000\u0000\u0000\u01a6\u01a8\u0005\u0018"+
		"\u0000\u0000\u01a7\u01a9\u0005/\u0000\u0000\u01a8\u01a7\u0001\u0000\u0000"+
		"\u0000\u01a8\u01a9\u0001\u0000\u0000\u0000\u01a9\u01b0\u0001\u0000\u0000"+
		"\u0000\u01aa\u01ab\u0003\f\u0006\u0000\u01ab\u01ad\u0005\u0018\u0000\u0000"+
		"\u01ac\u01ae\u0005/\u0000\u0000\u01ad\u01ac\u0001\u0000\u0000\u0000\u01ad"+
		"\u01ae\u0001\u0000\u0000\u0000\u01ae\u01b0\u0001\u0000\u0000\u0000\u01af"+
		"\u01a6\u0001\u0000\u0000\u0000\u01af\u01aa\u0001\u0000\u0000\u0000\u01b0"+
		"+\u0001\u0000\u0000\u0000\u01b1\u01b2\u0005\u0019\u0000\u0000\u01b2\u01b7"+
		"\u0003.\u0017\u0000\u01b3\u01b4\u0005\u0019\u0000\u0000\u01b4\u01b6\u0003"+
		".\u0017\u0000\u01b5\u01b3\u0001\u0000\u0000\u0000\u01b6\u01b9\u0001\u0000"+
		"\u0000\u0000\u01b7\u01b5\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000"+
		"\u0000\u0000\u01b8\u01bb\u0001\u0000\u0000\u0000\u01b9\u01b7\u0001\u0000"+
		"\u0000\u0000\u01ba\u01bc\u0005/\u0000\u0000\u01bb\u01ba\u0001\u0000\u0000"+
		"\u0000\u01bb\u01bc\u0001\u0000\u0000\u0000\u01bc-\u0001\u0000\u0000\u0000"+
		"\u01bd\u01be\u0003\u000e\u0007\u0000\u01be/\u0001\u0000\u0000\u0000%1"+
		"4=EMUhjv|\u0092\u00a8\u00b3\u00b5\u00bf\u00c7\u00cb\u00d1\u00da\u00ec"+
		"\u00ef\u0132\u0138\u014f\u0155\u0167\u016c\u018c\u018e\u0195\u019d\u01a4"+
		"\u01a8\u01ad\u01af\u01b7\u01bb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}