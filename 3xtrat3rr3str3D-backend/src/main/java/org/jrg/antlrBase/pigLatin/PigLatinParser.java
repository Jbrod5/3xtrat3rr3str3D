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
		RULE_declaracion_variable = 12, RULE_asignacion = 13, RULE_instruccion_flujo = 14, 
		RULE_bloque = 15, RULE_condicional = 16, RULE_ciclo = 17, RULE_init_per = 18, 
		RULE_paso_per = 19, RULE_instruccion_lectura = 20, RULE_instruccion_impresion = 21, 
		RULE_elemento_imprimir = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "seccion_importaciones", "ruta_importacion", "seccion_global_variables", 
			"seccion_maior", "tipo_dato", "variable_asignable", "expresion", "valor_primitivo", 
			"lista_expresiones", "lista_atributos_instancia", "atributo_instancia", 
			"declaracion_variable", "asignacion", "instruccion_flujo", "bloque", 
			"condicional", "ciclo", "init_per", "paso_per", "instruccion_lectura", 
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
			setState(47);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(46);
				seccion_importaciones();
				}
				break;
			}
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==VARIABILES) {
				{
				setState(49);
				seccion_global_variables();
				}
			}

			setState(52);
			seccion_maior();
			setState(53);
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
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(55);
				match(IMPORT);
				setState(56);
				ruta_importacion();
				}
				}
				setState(61);
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
			setState(62);
			match(IDENTIFICADOR);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PUNTO) {
				{
				{
				setState(63);
				match(PUNTO);
				setState(64);
				match(IDENTIFICADOR);
				}
				}
				setState(69);
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
			setState(70);
			match(VARIABILES);
			setState(71);
			match(MAYOR_QUE);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ESTO || _la==SERIES) {
				{
				{
				setState(72);
				declaracion_variable();
				}
				}
				setState(77);
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
			setState(78);
			match(MAIOR);
			setState(79);
			match(MAYOR_QUE);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049831422976L) != 0)) {
				{
				{
				setState(80);
				instruccion_flujo();
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(86);
			match(FINIS);
			setState(87);
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
			setState(89);
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

			setState(92);
			match(IDENTIFICADOR);
			}
			_ctx.stop = _input.LT(-1);
			setState(104);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(102);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ValorAsignableArrayContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(94);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(95);
						match(CORCHETE_IZQ);
						setState(96);
						expresion(0);
						setState(97);
						match(CORCHETE_DER);
						}
						break;
					case 2:
						{
						_localctx = new ValorAsignableMiembroEstructuraContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(99);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(100);
						match(PUNTO);
						setState(101);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(106);
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
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new ExprParentesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(108);
				match(PAR_IZQ);
				setState(109);
				expresion(0);
				setState(110);
				match(PAR_DER);
				}
				break;
			case 2:
				{
				_localctx = new ExprInstanciaObjetoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(NOVUS);
				setState(113);
				match(IDENTIFICADOR);
				setState(114);
				match(PAR_IZQ);
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
					{
					setState(115);
					lista_expresiones();
					}
				}

				setState(118);
				match(PAR_DER);
				}
				break;
			case 3:
				{
				_localctx = new ExprLlamadaFuncionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(119);
				match(IDENTIFICADOR);
				setState(120);
				match(PAR_IZQ);
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
					{
					setState(121);
					lista_expresiones();
					}
				}

				setState(124);
				match(PAR_DER);
				}
				break;
			case 4:
				{
				_localctx = new ExprPostIncrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				variable_asignable(0);
				setState(126);
				match(INCREMENTO);
				}
				break;
			case 5:
				{
				_localctx = new ExprPostDecrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				variable_asignable(0);
				setState(129);
				match(DECREMENTO);
				}
				break;
			case 6:
				{
				_localctx = new ExprListaLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				match(LLAVE_IZQ);
				setState(132);
				lista_expresiones();
				setState(133);
				match(LLAVE_DER);
				}
				break;
			case 7:
				{
				_localctx = new ExprNegativaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				match(RESTA);
				setState(136);
				expresion(10);
				}
				break;
			case 8:
				{
				_localctx = new ExprNegadaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				match(NON);
				setState(138);
				expresion(9);
				}
				break;
			case 9:
				{
				_localctx = new ExprPreIncrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(139);
				match(INCREMENTO);
				setState(140);
				variable_asignable(0);
				}
				break;
			case 10:
				{
				_localctx = new ExprPreDecrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(DECREMENTO);
				setState(142);
				variable_asignable(0);
				}
				break;
			case 11:
				{
				_localctx = new ExprPrimitivoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				valor_primitivo();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(179);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(177);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMultiplicacionDivisionContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(146);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(147);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(148);
						expresion(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprSumaRestaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(149);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(150);
						_la = _input.LA(1);
						if ( !(_la==SUMA || _la==RESTA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(151);
						expresion(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprRelacionalContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(152);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(153);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16911433728L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(154);
						expresion(5);
						}
						break;
					case 4:
						{
						_localctx = new ExprAndContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(155);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(156);
						match(AND);
						setState(157);
						expresion(4);
						}
						break;
					case 5:
						{
						_localctx = new ExprOrContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(158);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(159);
						match(OR);
						setState(160);
						expresion(3);
						}
						break;
					case 6:
						{
						_localctx = new ExprLlamadaMetodoContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(161);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(162);
						match(PUNTO);
						setState(163);
						match(IDENTIFICADOR);
						setState(164);
						match(PAR_IZQ);
						setState(166);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
							{
							setState(165);
							lista_expresiones();
							}
						}

						setState(168);
						match(PAR_DER);
						}
						break;
					case 7:
						{
						_localctx = new ExprAccesoPosicionArrayContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(169);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(170);
						match(CORCHETE_IZQ);
						setState(171);
						expresion(0);
						setState(172);
						match(CORCHETE_DER);
						}
						break;
					case 8:
						{
						_localctx = new ExprAccesoMiembroEstructuraContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(174);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(175);
						match(PUNTO);
						setState(176);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(181);
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
			setState(182);
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
			setState(184);
			expresion(0);
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(185);
				match(COMA);
				setState(186);
				expresion(0);
				}
				}
				setState(191);
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
			setState(192);
			atributo_instancia();
			setState(197);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(193);
					match(COMA);
					setState(194);
					atributo_instancia();
					}
					} 
				}
				setState(199);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMA) {
				{
				setState(200);
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
			setState(207);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new CampoConNombreContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				match(IDENTIFICADOR);
				setState(204);
				match(DOS_PUNTOS);
				setState(205);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new CampoPosicionalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
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
			setState(276);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				_localctx = new DeclObjetoNovusContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				match(ESTO);
				setState(210);
				match(IDENTIFICADOR);
				setState(211);
				match(DOS_PUNTOS);
				setState(212);
				match(NOVUS);
				setState(213);
				match(IDENTIFICADOR);
				setState(214);
				match(PAR_IZQ);
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049776995328L) != 0)) {
					{
					setState(215);
					lista_expresiones();
					}
				}

				setState(218);
				match(PAR_DER);
				setState(219);
				match(PUNTO_Y_COMA);
				}
				break;
			case 2:
				_localctx = new DeclEstructuraConValoresContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(220);
				match(ESTO);
				setState(221);
				match(IDENTIFICADOR);
				setState(222);
				match(DOS_PUNTOS);
				setState(223);
				match(IDENTIFICADOR);
				setState(224);
				match(LLAVE_IZQ);
				setState(225);
				lista_atributos_instancia();
				setState(226);
				match(LLAVE_DER);
				setState(227);
				match(PUNTO_Y_COMA);
				}
				break;
			case 3:
				_localctx = new DeclConTipoYValorContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				match(ESTO);
				setState(230);
				match(IDENTIFICADOR);
				setState(231);
				match(DOS_PUNTOS);
				setState(232);
				tipo_dato();
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850118496472064L) != 0)) {
					{
					setState(234);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ASIGNACION) {
						{
						setState(233);
						match(ASIGNACION);
						}
					}

					setState(236);
					expresion(0);
					}
				}

				setState(239);
				match(PUNTO_Y_COMA);
				}
				break;
			case 4:
				_localctx = new DeclBooleanaImplicitaContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(241);
				match(ESTO);
				setState(242);
				match(IDENTIFICADOR);
				setState(243);
				match(DOS_PUNTOS);
				setState(244);
				_la = _input.LA(1);
				if ( !(_la==VERUM || _la==FALSUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(245);
				match(PUNTO_Y_COMA);
				}
				break;
			case 5:
				_localctx = new DeclArraySinDatosContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(246);
				match(SERIES);
				setState(247);
				match(IDENTIFICADOR);
				setState(248);
				match(CORCHETE_IZQ);
				setState(249);
				expresion(0);
				setState(250);
				match(CORCHETE_DER);
				setState(251);
				match(DOS_PUNTOS);
				setState(252);
				tipo_dato();
				setState(253);
				match(PUNTO_Y_COMA);
				}
				break;
			case 6:
				_localctx = new DeclArrayConDatosContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(255);
				match(SERIES);
				setState(256);
				match(IDENTIFICADOR);
				setState(257);
				match(CORCHETE_IZQ);
				setState(258);
				expresion(0);
				setState(259);
				match(CORCHETE_DER);
				setState(260);
				match(DOS_PUNTOS);
				setState(261);
				tipo_dato();
				setState(262);
				match(LLAVE_IZQ);
				setState(263);
				lista_expresiones();
				setState(264);
				match(LLAVE_DER);
				setState(265);
				match(PUNTO_Y_COMA);
				}
				break;
			case 7:
				_localctx = new DeclArrayEstructuraContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(267);
				match(SERIES);
				setState(268);
				match(IDENTIFICADOR);
				setState(269);
				match(CORCHETE_IZQ);
				setState(270);
				expresion(0);
				setState(271);
				match(CORCHETE_DER);
				setState(272);
				match(DOS_PUNTOS);
				setState(273);
				match(IDENTIFICADOR);
				setState(274);
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
		enterRule(_localctx, 26, RULE_asignacion);
		try {
			_localctx = new AsignacionGeneralContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			variable_asignable(0);
			setState(279);
			match(ASIGNACION);
			setState(280);
			expresion(0);
			setState(281);
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
		enterRule(_localctx, 28, RULE_instruccion_flujo);
		try {
			setState(295);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new StmtAsignacionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				asignacion();
				}
				break;
			case 2:
				_localctx = new StmtCondicionalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				condicional();
				}
				break;
			case 3:
				_localctx = new StmtCicloContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(285);
				ciclo();
				}
				break;
			case 4:
				_localctx = new StmtLecturaContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(286);
				instruccion_lectura();
				}
				break;
			case 5:
				_localctx = new StmtImpresionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(287);
				instruccion_impresion();
				}
				break;
			case 6:
				_localctx = new StmtInterrumpeContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(288);
				match(INTERRUMPE);
				setState(289);
				match(PUNTO_Y_COMA);
				}
				break;
			case 7:
				_localctx = new StmtPergeContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(290);
				match(PERGE);
				setState(291);
				match(PUNTO_Y_COMA);
				}
				break;
			case 8:
				_localctx = new StmtExpresionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(292);
				expresion(0);
				setState(293);
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
		enterRule(_localctx, 30, RULE_bloque);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(LLAVE_IZQ);
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 69850049831422976L) != 0)) {
				{
				{
				setState(298);
				instruccion_flujo();
				}
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(304);
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
		enterRule(_localctx, 32, RULE_condicional);
		int _la;
		try {
			int _alt;
			_localctx = new StatementSiContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(SI);
			setState(307);
			match(PAR_IZQ);
			setState(308);
			expresion(0);
			setState(309);
			match(PAR_DER);
			setState(310);
			bloque();
			setState(319);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(311);
					match(ALITER);
					setState(312);
					match(PAR_IZQ);
					setState(313);
					expresion(0);
					setState(314);
					match(PAR_DER);
					setState(315);
					bloque();
					}
					} 
				}
				setState(321);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(324);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ALITER) {
				{
				setState(322);
				match(ALITER);
				setState(323);
				bloque();
				}
			}

			setState(326);
			match(FINIS_BLOQUE);
			setState(327);
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
		enterRule(_localctx, 34, RULE_ciclo);
		int _la;
		try {
			setState(358);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DUM:
				_localctx = new CicloDumContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				match(DUM);
				setState(330);
				match(PAR_IZQ);
				setState(331);
				expresion(0);
				setState(332);
				match(PAR_DER);
				setState(333);
				bloque();
				setState(334);
				match(FINIS_BLOQUE);
				setState(335);
				match(PUNTO_Y_COMA);
				}
				break;
			case FACERE:
				_localctx = new CicloFacereContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(337);
				match(FACERE);
				setState(338);
				bloque();
				setState(339);
				match(DUM);
				setState(340);
				match(PAR_IZQ);
				setState(341);
				expresion(0);
				setState(342);
				match(PAR_DER);
				setState(343);
				match(PUNTO_Y_COMA);
				}
				break;
			case PER:
				_localctx = new CicloPerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(345);
				match(PER);
				setState(346);
				match(PAR_IZQ);
				setState(347);
				init_per();
				setState(348);
				match(PUNTO_Y_COMA);
				setState(349);
				expresion(0);
				setState(350);
				match(PUNTO_Y_COMA);
				setState(351);
				paso_per();
				setState(352);
				match(PAR_DER);
				setState(353);
				bloque();
				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINIS_BLOQUE) {
					{
					setState(354);
					match(FINIS_BLOQUE);
					setState(355);
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
		enterRule(_localctx, 36, RULE_init_per);
		int _la;
		try {
			setState(373);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ESTO:
				_localctx = new InitPerDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(360);
				match(ESTO);
				setState(361);
				match(IDENTIFICADOR);
				setState(362);
				match(DOS_PUNTOS);
				setState(363);
				tipo_dato();
				setState(365);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASIGNACION) {
					{
					setState(364);
					match(ASIGNACION);
					}
				}

				setState(367);
				expresion(0);
				}
				break;
			case IDENTIFICADOR:
				_localctx = new InitPerAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(369);
				variable_asignable(0);
				setState(370);
				match(ASIGNACION);
				setState(371);
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
		enterRule(_localctx, 38, RULE_paso_per);
		try {
			setState(380);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new PasoPerExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new PasoPerAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				variable_asignable(0);
				setState(377);
				match(ASIGNACION);
				setState(378);
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
		enterRule(_localctx, 40, RULE_instruccion_lectura);
		int _la;
		try {
			setState(391);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LEER:
				_localctx = new LecturaConsolaSimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(382);
				match(LEER);
				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUNTO_Y_COMA) {
					{
					setState(383);
					match(PUNTO_Y_COMA);
					}
				}

				}
				break;
			case IDENTIFICADOR:
				_localctx = new LecturaConsolaAVariableContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(386);
				variable_asignable(0);
				setState(387);
				match(LEER);
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUNTO_Y_COMA) {
					{
					setState(388);
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
		enterRule(_localctx, 42, RULE_instruccion_impresion);
		int _la;
		try {
			int _alt;
			_localctx = new ImpresionEncadenadaContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			match(IMPRIMIR);
			setState(394);
			elemento_imprimir();
			setState(399);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(395);
					match(IMPRIMIR);
					setState(396);
					elemento_imprimir();
					}
					} 
				}
				setState(401);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNTO_Y_COMA) {
				{
				setState(402);
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
		enterRule(_localctx, 44, RULE_elemento_imprimir);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
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
		"\u0004\u0001:\u0198\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0001\u0000\u0003\u00000\b\u0000\u0001\u0000"+
		"\u0003\u00003\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0005\u0001:\b\u0001\n\u0001\f\u0001=\t\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002B\b\u0002\n\u0002\f\u0002E\t\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003J\b\u0003\n\u0003\f\u0003"+
		"M\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004R\b\u0004\n\u0004"+
		"\f\u0004U\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005"+
		"\u0006g\b\u0006\n\u0006\f\u0006j\t\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007u\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007{\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0091"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a7\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0005\u0007\u00b2\b\u0007\n\u0007\f\u0007\u00b5\t\u0007"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0005\t\u00bc\b\t\n\t\f\t\u00bf"+
		"\t\t\u0001\n\u0001\n\u0001\n\u0005\n\u00c4\b\n\n\n\f\n\u00c7\t\n\u0001"+
		"\n\u0003\n\u00ca\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00d0\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00d9\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0003\f\u00eb\b\f\u0001\f\u0003\f\u00ee\b\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0115"+
		"\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0128\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u012c\b\u000f\n\u000f\f\u000f\u012f\t\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u013e\b\u0010\n\u0010\f\u0010\u0141\t\u0010\u0001"+
		"\u0010\u0001\u0010\u0003\u0010\u0145\b\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0165\b\u0011\u0003"+
		"\u0011\u0167\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u016e\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0176\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u017d\b\u0013\u0001"+
		"\u0014\u0001\u0014\u0003\u0014\u0181\b\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0003\u0014\u0186\b\u0014\u0003\u0014\u0188\b\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0005\u0015\u018e\b\u0015\n\u0015"+
		"\f\u0015\u0191\t\u0015\u0001\u0015\u0003\u0015\u0194\b\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0000\u0002\f\u000e\u0017\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,\u0000"+
		"\u0006\u0002\u0000\u0005\t77\u0001\u0000\'(\u0001\u0000%&\u0001\u0000"+
		"\u001c!\u0002\u0000\n\u000b37\u0001\u0000\n\u000b\u01bf\u0000/\u0001\u0000"+
		"\u0000\u0000\u0002;\u0001\u0000\u0000\u0000\u0004>\u0001\u0000\u0000\u0000"+
		"\u0006F\u0001\u0000\u0000\u0000\bN\u0001\u0000\u0000\u0000\nY\u0001\u0000"+
		"\u0000\u0000\f[\u0001\u0000\u0000\u0000\u000e\u0090\u0001\u0000\u0000"+
		"\u0000\u0010\u00b6\u0001\u0000\u0000\u0000\u0012\u00b8\u0001\u0000\u0000"+
		"\u0000\u0014\u00c0\u0001\u0000\u0000\u0000\u0016\u00cf\u0001\u0000\u0000"+
		"\u0000\u0018\u0114\u0001\u0000\u0000\u0000\u001a\u0116\u0001\u0000\u0000"+
		"\u0000\u001c\u0127\u0001\u0000\u0000\u0000\u001e\u0129\u0001\u0000\u0000"+
		"\u0000 \u0132\u0001\u0000\u0000\u0000\"\u0166\u0001\u0000\u0000\u0000"+
		"$\u0175\u0001\u0000\u0000\u0000&\u017c\u0001\u0000\u0000\u0000(\u0187"+
		"\u0001\u0000\u0000\u0000*\u0189\u0001\u0000\u0000\u0000,\u0195\u0001\u0000"+
		"\u0000\u0000.0\u0003\u0002\u0001\u0000/.\u0001\u0000\u0000\u0000/0\u0001"+
		"\u0000\u0000\u000002\u0001\u0000\u0000\u000013\u0003\u0006\u0003\u0000"+
		"21\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000034\u0001\u0000\u0000"+
		"\u000045\u0003\b\u0004\u000056\u0005\u0000\u0000\u00016\u0001\u0001\u0000"+
		"\u0000\u000078\u0005\u0001\u0000\u00008:\u0003\u0004\u0002\u000097\u0001"+
		"\u0000\u0000\u0000:=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000"+
		";<\u0001\u0000\u0000\u0000<\u0003\u0001\u0000\u0000\u0000=;\u0001\u0000"+
		"\u0000\u0000>C\u00057\u0000\u0000?@\u00052\u0000\u0000@B\u00057\u0000"+
		"\u0000A?\u0001\u0000\u0000\u0000BE\u0001\u0000\u0000\u0000CA\u0001\u0000"+
		"\u0000\u0000CD\u0001\u0000\u0000\u0000D\u0005\u0001\u0000\u0000\u0000"+
		"EC\u0001\u0000\u0000\u0000FG\u0005\u0002\u0000\u0000GK\u0005 \u0000\u0000"+
		"HJ\u0003\u0018\f\u0000IH\u0001\u0000\u0000\u0000JM\u0001\u0000\u0000\u0000"+
		"KI\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000L\u0007\u0001\u0000"+
		"\u0000\u0000MK\u0001\u0000\u0000\u0000NO\u0005\u0003\u0000\u0000OS\u0005"+
		" \u0000\u0000PR\u0003\u001c\u000e\u0000QP\u0001\u0000\u0000\u0000RU\u0001"+
		"\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000"+
		"TV\u0001\u0000\u0000\u0000US\u0001\u0000\u0000\u0000VW\u0005\u0004\u0000"+
		"\u0000WX\u0005/\u0000\u0000X\t\u0001\u0000\u0000\u0000YZ\u0007\u0000\u0000"+
		"\u0000Z\u000b\u0001\u0000\u0000\u0000[\\\u0006\u0006\uffff\uffff\u0000"+
		"\\]\u00057\u0000\u0000]h\u0001\u0000\u0000\u0000^_\n\u0002\u0000\u0000"+
		"_`\u0005)\u0000\u0000`a\u0003\u000e\u0007\u0000ab\u0005*\u0000\u0000b"+
		"g\u0001\u0000\u0000\u0000cd\n\u0001\u0000\u0000de\u00052\u0000\u0000e"+
		"g\u00057\u0000\u0000f^\u0001\u0000\u0000\u0000fc\u0001\u0000\u0000\u0000"+
		"gj\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000i\r\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000kl\u0006\u0007"+
		"\uffff\uffff\u0000lm\u0005-\u0000\u0000mn\u0003\u000e\u0007\u0000no\u0005"+
		".\u0000\u0000o\u0091\u0001\u0000\u0000\u0000pq\u0005\u000e\u0000\u0000"+
		"qr\u00057\u0000\u0000rt\u0005-\u0000\u0000su\u0003\u0012\t\u0000ts\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000"+
		"v\u0091\u0005.\u0000\u0000wx\u00057\u0000\u0000xz\u0005-\u0000\u0000y"+
		"{\u0003\u0012\t\u0000zy\u0001\u0000\u0000\u0000z{\u0001\u0000\u0000\u0000"+
		"{|\u0001\u0000\u0000\u0000|\u0091\u0005.\u0000\u0000}~\u0003\f\u0006\u0000"+
		"~\u007f\u0005\u001a\u0000\u0000\u007f\u0091\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0003\f\u0006\u0000\u0081\u0082\u0005\u001b\u0000\u0000\u0082\u0091"+
		"\u0001\u0000\u0000\u0000\u0083\u0084\u0005+\u0000\u0000\u0084\u0085\u0003"+
		"\u0012\t\u0000\u0085\u0086\u0005,\u0000\u0000\u0086\u0091\u0001\u0000"+
		"\u0000\u0000\u0087\u0088\u0005&\u0000\u0000\u0088\u0091\u0003\u000e\u0007"+
		"\n\u0089\u008a\u0005\u0017\u0000\u0000\u008a\u0091\u0003\u000e\u0007\t"+
		"\u008b\u008c\u0005\u001a\u0000\u0000\u008c\u0091\u0003\f\u0006\u0000\u008d"+
		"\u008e\u0005\u001b\u0000\u0000\u008e\u0091\u0003\f\u0006\u0000\u008f\u0091"+
		"\u0003\u0010\b\u0000\u0090k\u0001\u0000\u0000\u0000\u0090p\u0001\u0000"+
		"\u0000\u0000\u0090w\u0001\u0000\u0000\u0000\u0090}\u0001\u0000\u0000\u0000"+
		"\u0090\u0080\u0001\u0000\u0000\u0000\u0090\u0083\u0001\u0000\u0000\u0000"+
		"\u0090\u0087\u0001\u0000\u0000\u0000\u0090\u0089\u0001\u0000\u0000\u0000"+
		"\u0090\u008b\u0001\u0000\u0000\u0000\u0090\u008d\u0001\u0000\u0000\u0000"+
		"\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u00b3\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\n\u0006\u0000\u0000\u0093\u0094\u0007\u0001\u0000\u0000\u0094"+
		"\u00b2\u0003\u000e\u0007\u0007\u0095\u0096\n\u0005\u0000\u0000\u0096\u0097"+
		"\u0007\u0002\u0000\u0000\u0097\u00b2\u0003\u000e\u0007\u0006\u0098\u0099"+
		"\n\u0004\u0000\u0000\u0099\u009a\u0007\u0003\u0000\u0000\u009a\u00b2\u0003"+
		"\u000e\u0007\u0005\u009b\u009c\n\u0003\u0000\u0000\u009c\u009d\u0005\""+
		"\u0000\u0000\u009d\u00b2\u0003\u000e\u0007\u0004\u009e\u009f\n\u0002\u0000"+
		"\u0000\u009f\u00a0\u0005#\u0000\u0000\u00a0\u00b2\u0003\u000e\u0007\u0003"+
		"\u00a1\u00a2\n\u0010\u0000\u0000\u00a2\u00a3\u00052\u0000\u0000\u00a3"+
		"\u00a4\u00057\u0000\u0000\u00a4\u00a6\u0005-\u0000\u0000\u00a5\u00a7\u0003"+
		"\u0012\t\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00b2\u0005.\u0000"+
		"\u0000\u00a9\u00aa\n\u000f\u0000\u0000\u00aa\u00ab\u0005)\u0000\u0000"+
		"\u00ab\u00ac\u0003\u000e\u0007\u0000\u00ac\u00ad\u0005*\u0000\u0000\u00ad"+
		"\u00b2\u0001\u0000\u0000\u0000\u00ae\u00af\n\u000e\u0000\u0000\u00af\u00b0"+
		"\u00052\u0000\u0000\u00b0\u00b2\u00057\u0000\u0000\u00b1\u0092\u0001\u0000"+
		"\u0000\u0000\u00b1\u0095\u0001\u0000\u0000\u0000\u00b1\u0098\u0001\u0000"+
		"\u0000\u0000\u00b1\u009b\u0001\u0000\u0000\u0000\u00b1\u009e\u0001\u0000"+
		"\u0000\u0000\u00b1\u00a1\u0001\u0000\u0000\u0000\u00b1\u00a9\u0001\u0000"+
		"\u0000\u0000\u00b1\u00ae\u0001\u0000\u0000\u0000\u00b2\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b4\u000f\u0001\u0000\u0000\u0000\u00b5\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0007\u0004\u0000\u0000\u00b7\u0011\u0001\u0000"+
		"\u0000\u0000\u00b8\u00bd\u0003\u000e\u0007\u0000\u00b9\u00ba\u00051\u0000"+
		"\u0000\u00ba\u00bc\u0003\u000e\u0007\u0000\u00bb\u00b9\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be\u0013\u0001\u0000\u0000"+
		"\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00c5\u0003\u0016\u000b"+
		"\u0000\u00c1\u00c2\u00051\u0000\u0000\u00c2\u00c4\u0003\u0016\u000b\u0000"+
		"\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c9\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000"+
		"\u00c8\u00ca\u00051\u0000\u0000\u00c9\u00c8\u0001\u0000\u0000\u0000\u00c9"+
		"\u00ca\u0001\u0000\u0000\u0000\u00ca\u0015\u0001\u0000\u0000\u0000\u00cb"+
		"\u00cc\u00057\u0000\u0000\u00cc\u00cd\u00050\u0000\u0000\u00cd\u00d0\u0003"+
		"\u000e\u0007\u0000\u00ce\u00d0\u0003\u000e\u0007\u0000\u00cf\u00cb\u0001"+
		"\u0000\u0000\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00d0\u0017\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d2\u0005\f\u0000\u0000\u00d2\u00d3\u00057"+
		"\u0000\u0000\u00d3\u00d4\u00050\u0000\u0000\u00d4\u00d5\u0005\u000e\u0000"+
		"\u0000\u00d5\u00d6\u00057\u0000\u0000\u00d6\u00d8\u0005-\u0000\u0000\u00d7"+
		"\u00d9\u0003\u0012\t\u0000\u00d8\u00d7\u0001\u0000\u0000\u0000\u00d8\u00d9"+
		"\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000\u00da\u00db"+
		"\u0005.\u0000\u0000\u00db\u0115\u0005/\u0000\u0000\u00dc\u00dd\u0005\f"+
		"\u0000\u0000\u00dd\u00de\u00057\u0000\u0000\u00de\u00df\u00050\u0000\u0000"+
		"\u00df\u00e0\u00057\u0000\u0000\u00e0\u00e1\u0005+\u0000\u0000\u00e1\u00e2"+
		"\u0003\u0014\n\u0000\u00e2\u00e3\u0005,\u0000\u0000\u00e3\u00e4\u0005"+
		"/\u0000\u0000\u00e4\u0115\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\f"+
		"\u0000\u0000\u00e6\u00e7\u00057\u0000\u0000\u00e7\u00e8\u00050\u0000\u0000"+
		"\u00e8\u00ed\u0003\n\u0005\u0000\u00e9\u00eb\u0005$\u0000\u0000\u00ea"+
		"\u00e9\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ec\u0001\u0000\u0000\u0000\u00ec\u00ee\u0003\u000e\u0007\u0000\u00ed"+
		"\u00ea\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee"+
		"\u00ef\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005/\u0000\u0000\u00f0\u0115"+
		"\u0001\u0000\u0000\u0000\u00f1\u00f2\u0005\f\u0000\u0000\u00f2\u00f3\u0005"+
		"7\u0000\u0000\u00f3\u00f4\u00050\u0000\u0000\u00f4\u00f5\u0007\u0005\u0000"+
		"\u0000\u00f5\u0115\u0005/\u0000\u0000\u00f6\u00f7\u0005\r\u0000\u0000"+
		"\u00f7\u00f8\u00057\u0000\u0000\u00f8\u00f9\u0005)\u0000\u0000\u00f9\u00fa"+
		"\u0003\u000e\u0007\u0000\u00fa\u00fb\u0005*\u0000\u0000\u00fb\u00fc\u0005"+
		"0\u0000\u0000\u00fc\u00fd\u0003\n\u0005\u0000\u00fd\u00fe\u0005/\u0000"+
		"\u0000\u00fe\u0115\u0001\u0000\u0000\u0000\u00ff\u0100\u0005\r\u0000\u0000"+
		"\u0100\u0101\u00057\u0000\u0000\u0101\u0102\u0005)\u0000\u0000\u0102\u0103"+
		"\u0003\u000e\u0007\u0000\u0103\u0104\u0005*\u0000\u0000\u0104\u0105\u0005"+
		"0\u0000\u0000\u0105\u0106\u0003\n\u0005\u0000\u0106\u0107\u0005+\u0000"+
		"\u0000\u0107\u0108\u0003\u0012\t\u0000\u0108\u0109\u0005,\u0000\u0000"+
		"\u0109\u010a\u0005/\u0000\u0000\u010a\u0115\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0005\r\u0000\u0000\u010c\u010d\u00057\u0000\u0000\u010d\u010e"+
		"\u0005)\u0000\u0000\u010e\u010f\u0003\u000e\u0007\u0000\u010f\u0110\u0005"+
		"*\u0000\u0000\u0110\u0111\u00050\u0000\u0000\u0111\u0112\u00057\u0000"+
		"\u0000\u0112\u0113\u0005/\u0000\u0000\u0113\u0115\u0001\u0000\u0000\u0000"+
		"\u0114\u00d1\u0001\u0000\u0000\u0000\u0114\u00dc\u0001\u0000\u0000\u0000"+
		"\u0114\u00e5\u0001\u0000\u0000\u0000\u0114\u00f1\u0001\u0000\u0000\u0000"+
		"\u0114\u00f6\u0001\u0000\u0000\u0000\u0114\u00ff\u0001\u0000\u0000\u0000"+
		"\u0114\u010b\u0001\u0000\u0000\u0000\u0115\u0019\u0001\u0000\u0000\u0000"+
		"\u0116\u0117\u0003\f\u0006\u0000\u0117\u0118\u0005$\u0000\u0000\u0118"+
		"\u0119\u0003\u000e\u0007\u0000\u0119\u011a\u0005/\u0000\u0000\u011a\u001b"+
		"\u0001\u0000\u0000\u0000\u011b\u0128\u0003\u001a\r\u0000\u011c\u0128\u0003"+
		" \u0010\u0000\u011d\u0128\u0003\"\u0011\u0000\u011e\u0128\u0003(\u0014"+
		"\u0000\u011f\u0128\u0003*\u0015\u0000\u0120\u0121\u0005\u0014\u0000\u0000"+
		"\u0121\u0128\u0005/\u0000\u0000\u0122\u0123\u0005\u0015\u0000\u0000\u0123"+
		"\u0128\u0005/\u0000\u0000\u0124\u0125\u0003\u000e\u0007\u0000\u0125\u0126"+
		"\u0005/\u0000\u0000\u0126\u0128\u0001\u0000\u0000\u0000\u0127\u011b\u0001"+
		"\u0000\u0000\u0000\u0127\u011c\u0001\u0000\u0000\u0000\u0127\u011d\u0001"+
		"\u0000\u0000\u0000\u0127\u011e\u0001\u0000\u0000\u0000\u0127\u011f\u0001"+
		"\u0000\u0000\u0000\u0127\u0120\u0001\u0000\u0000\u0000\u0127\u0122\u0001"+
		"\u0000\u0000\u0000\u0127\u0124\u0001\u0000\u0000\u0000\u0128\u001d\u0001"+
		"\u0000\u0000\u0000\u0129\u012d\u0005+\u0000\u0000\u012a\u012c\u0003\u001c"+
		"\u000e\u0000\u012b\u012a\u0001\u0000\u0000\u0000\u012c\u012f\u0001\u0000"+
		"\u0000\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000"+
		"\u0000\u0000\u012e\u0130\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000"+
		"\u0000\u0000\u0130\u0131\u0005,\u0000\u0000\u0131\u001f\u0001\u0000\u0000"+
		"\u0000\u0132\u0133\u0005\u000f\u0000\u0000\u0133\u0134\u0005-\u0000\u0000"+
		"\u0134\u0135\u0003\u000e\u0007\u0000\u0135\u0136\u0005.\u0000\u0000\u0136"+
		"\u013f\u0003\u001e\u000f\u0000\u0137\u0138\u0005\u0010\u0000\u0000\u0138"+
		"\u0139\u0005-\u0000\u0000\u0139\u013a\u0003\u000e\u0007\u0000\u013a\u013b"+
		"\u0005.\u0000\u0000\u013b\u013c\u0003\u001e\u000f\u0000\u013c\u013e\u0001"+
		"\u0000\u0000\u0000\u013d\u0137\u0001\u0000\u0000\u0000\u013e\u0141\u0001"+
		"\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u013f\u0140\u0001"+
		"\u0000\u0000\u0000\u0140\u0144\u0001\u0000\u0000\u0000\u0141\u013f\u0001"+
		"\u0000\u0000\u0000\u0142\u0143\u0005\u0010\u0000\u0000\u0143\u0145\u0003"+
		"\u001e\u000f\u0000\u0144\u0142\u0001\u0000\u0000\u0000\u0144\u0145\u0001"+
		"\u0000\u0000\u0000\u0145\u0146\u0001\u0000\u0000\u0000\u0146\u0147\u0005"+
		"\u0016\u0000\u0000\u0147\u0148\u0005/\u0000\u0000\u0148!\u0001\u0000\u0000"+
		"\u0000\u0149\u014a\u0005\u0011\u0000\u0000\u014a\u014b\u0005-\u0000\u0000"+
		"\u014b\u014c\u0003\u000e\u0007\u0000\u014c\u014d\u0005.\u0000\u0000\u014d"+
		"\u014e\u0003\u001e\u000f\u0000\u014e\u014f\u0005\u0016\u0000\u0000\u014f"+
		"\u0150\u0005/\u0000\u0000\u0150\u0167\u0001\u0000\u0000\u0000\u0151\u0152"+
		"\u0005\u0012\u0000\u0000\u0152\u0153\u0003\u001e\u000f\u0000\u0153\u0154"+
		"\u0005\u0011\u0000\u0000\u0154\u0155\u0005-\u0000\u0000\u0155\u0156\u0003"+
		"\u000e\u0007\u0000\u0156\u0157\u0005.\u0000\u0000\u0157\u0158\u0005/\u0000"+
		"\u0000\u0158\u0167\u0001\u0000\u0000\u0000\u0159\u015a\u0005\u0013\u0000"+
		"\u0000\u015a\u015b\u0005-\u0000\u0000\u015b\u015c\u0003$\u0012\u0000\u015c"+
		"\u015d\u0005/\u0000\u0000\u015d\u015e\u0003\u000e\u0007\u0000\u015e\u015f"+
		"\u0005/\u0000\u0000\u015f\u0160\u0003&\u0013\u0000\u0160\u0161\u0005."+
		"\u0000\u0000\u0161\u0164\u0003\u001e\u000f\u0000\u0162\u0163\u0005\u0016"+
		"\u0000\u0000\u0163\u0165\u0005/\u0000\u0000\u0164\u0162\u0001\u0000\u0000"+
		"\u0000\u0164\u0165\u0001\u0000\u0000\u0000\u0165\u0167\u0001\u0000\u0000"+
		"\u0000\u0166\u0149\u0001\u0000\u0000\u0000\u0166\u0151\u0001\u0000\u0000"+
		"\u0000\u0166\u0159\u0001\u0000\u0000\u0000\u0167#\u0001\u0000\u0000\u0000"+
		"\u0168\u0169\u0005\f\u0000\u0000\u0169\u016a\u00057\u0000\u0000\u016a"+
		"\u016b\u00050\u0000\u0000\u016b\u016d\u0003\n\u0005\u0000\u016c\u016e"+
		"\u0005$\u0000\u0000\u016d\u016c\u0001\u0000\u0000\u0000\u016d\u016e\u0001"+
		"\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016f\u0170\u0003"+
		"\u000e\u0007\u0000\u0170\u0176\u0001\u0000\u0000\u0000\u0171\u0172\u0003"+
		"\f\u0006\u0000\u0172\u0173\u0005$\u0000\u0000\u0173\u0174\u0003\u000e"+
		"\u0007\u0000\u0174\u0176\u0001\u0000\u0000\u0000\u0175\u0168\u0001\u0000"+
		"\u0000\u0000\u0175\u0171\u0001\u0000\u0000\u0000\u0176%\u0001\u0000\u0000"+
		"\u0000\u0177\u017d\u0003\u000e\u0007\u0000\u0178\u0179\u0003\f\u0006\u0000"+
		"\u0179\u017a\u0005$\u0000\u0000\u017a\u017b\u0003\u000e\u0007\u0000\u017b"+
		"\u017d\u0001\u0000\u0000\u0000\u017c\u0177\u0001\u0000\u0000\u0000\u017c"+
		"\u0178\u0001\u0000\u0000\u0000\u017d\'\u0001\u0000\u0000\u0000\u017e\u0180"+
		"\u0005\u0018\u0000\u0000\u017f\u0181\u0005/\u0000\u0000\u0180\u017f\u0001"+
		"\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0188\u0001"+
		"\u0000\u0000\u0000\u0182\u0183\u0003\f\u0006\u0000\u0183\u0185\u0005\u0018"+
		"\u0000\u0000\u0184\u0186\u0005/\u0000\u0000\u0185\u0184\u0001\u0000\u0000"+
		"\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186\u0188\u0001\u0000\u0000"+
		"\u0000\u0187\u017e\u0001\u0000\u0000\u0000\u0187\u0182\u0001\u0000\u0000"+
		"\u0000\u0188)\u0001\u0000\u0000\u0000\u0189\u018a\u0005\u0019\u0000\u0000"+
		"\u018a\u018f\u0003,\u0016\u0000\u018b\u018c\u0005\u0019\u0000\u0000\u018c"+
		"\u018e\u0003,\u0016\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018e\u0191"+
		"\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000\u0000\u0000\u018f\u0190"+
		"\u0001\u0000\u0000\u0000\u0190\u0193\u0001\u0000\u0000\u0000\u0191\u018f"+
		"\u0001\u0000\u0000\u0000\u0192\u0194\u0005/\u0000\u0000\u0193\u0192\u0001"+
		"\u0000\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000\u0194+\u0001\u0000"+
		"\u0000\u0000\u0195\u0196\u0003\u000e\u0007\u0000\u0196-\u0001\u0000\u0000"+
		"\u0000$/2;CKSfhtz\u0090\u00a6\u00b1\u00b3\u00bd\u00c5\u00c9\u00cf\u00d8"+
		"\u00ea\u00ed\u0114\u0127\u012d\u013f\u0144\u0164\u0166\u016d\u0175\u017c"+
		"\u0180\u0185\u0187\u018f\u0193";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}