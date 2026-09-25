// Generated from org/jrg/antlrBase/zetariano/Zetariano.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.zetariano;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ZetarianoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PUBLIC=1, CLASS=2, VOID=3, NEW=4, NULL=5, INT=6, DOUBLE=7, CHAR=8, BOOLEAN=9, 
		STRING=10, TRUE=11, FALSE=12, IF=13, ELSE=14, SWITCH=15, CASE=16, DEFAULT=17, 
		FOR=18, WHILE=19, DO=20, BREAK=21, CONTINUE=22, RETURN=23, INCREMENTO=24, 
		DECREMENTO=25, IGUAL_QUE=26, DIFERENTE_QUE=27, MAYOR_IGUAL_QUE=28, MENOR_IGUAL_QUE=29, 
		MAYOR_QUE=30, MENOR_QUE=31, AND=32, OR=33, NEGACION=34, MAS_IGUAL=35, 
		MENOS_IGUAL=36, MULT_IGUAL=37, ASIGNACION=38, INTERROGACION=39, SUMA=40, 
		RESTA=41, MULT=42, DIV=43, MOD=44, CORCHETE_IZQ=45, CORCHETE_DER=46, LLAVE_IZQ=47, 
		LLAVE_DER=48, PAR_IZQ=49, PAR_DER=50, PUNTO_Y_COMA=51, DOS_PUNTOS=52, 
		COMA=53, PUNTO=54, CADENA=55, CARACTER=56, NUMERO_DECIMAL=57, NUMERO_ENTERO=58, 
		IDENTIFICADOR=59, COMENTARIO_LINEA=60, COMENTARIO_BLOQUE=61, ESPACIOS_BLANCO=62;
	public static final int
		RULE_programa = 0, RULE_definicion_clase = 1, RULE_miembro_clase = 2, 
		RULE_tipo_dato = 3, RULE_atributo_clase = 4, RULE_constructor = 5, RULE_metodo = 6, 
		RULE_parametros = 7, RULE_parametro = 8, RULE_instruccion = 9, RULE_declaracion_variable = 10, 
		RULE_init_matriz = 11, RULE_init_elemento = 12, RULE_asignacion = 13, 
		RULE_variable_asignable = 14, RULE_condicional = 15, RULE_bloque = 16, 
		RULE_seleccion = 17, RULE_caso_switch = 18, RULE_caso_default = 19, RULE_ciclo = 20, 
		RULE_init_for = 21, RULE_paso_for = 22, RULE_expresion = 23, RULE_valor_primitivo = 24, 
		RULE_lista_expresiones = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "definicion_clase", "miembro_clase", "tipo_dato", "atributo_clase", 
			"constructor", "metodo", "parametros", "parametro", "instruccion", "declaracion_variable", 
			"init_matriz", "init_elemento", "asignacion", "variable_asignable", "condicional", 
			"bloque", "seleccion", "caso_switch", "caso_default", "ciclo", "init_for", 
			"paso_for", "expresion", "valor_primitivo", "lista_expresiones"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'public'", "'class'", "'void'", "'new'", "'null'", "'int'", "'double'", 
			"'char'", "'boolean'", "'String'", "'true'", "'false'", "'if'", "'else'", 
			"'switch'", "'case'", "'default'", "'for'", "'while'", "'do'", "'break'", 
			"'continue'", "'return'", "'++'", "'--'", "'=='", "'!='", "'>='", "'<='", 
			"'>'", "'<'", "'&&'", "'||'", "'!'", "'+='", "'-='", "'*='", "'='", "'?'", 
			"'+'", "'-'", "'*'", "'/'", "'%'", "'['", "']'", "'{'", "'}'", "'('", 
			"')'", "';'", "':'", "','", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PUBLIC", "CLASS", "VOID", "NEW", "NULL", "INT", "DOUBLE", "CHAR", 
			"BOOLEAN", "STRING", "TRUE", "FALSE", "IF", "ELSE", "SWITCH", "CASE", 
			"DEFAULT", "FOR", "WHILE", "DO", "BREAK", "CONTINUE", "RETURN", "INCREMENTO", 
			"DECREMENTO", "IGUAL_QUE", "DIFERENTE_QUE", "MAYOR_IGUAL_QUE", "MENOR_IGUAL_QUE", 
			"MAYOR_QUE", "MENOR_QUE", "AND", "OR", "NEGACION", "MAS_IGUAL", "MENOS_IGUAL", 
			"MULT_IGUAL", "ASIGNACION", "INTERROGACION", "SUMA", "RESTA", "MULT", 
			"DIV", "MOD", "CORCHETE_IZQ", "CORCHETE_DER", "LLAVE_IZQ", "LLAVE_DER", 
			"PAR_IZQ", "PAR_DER", "PUNTO_Y_COMA", "DOS_PUNTOS", "COMA", "PUNTO", 
			"CADENA", "CARACTER", "NUMERO_DECIMAL", "NUMERO_ENTERO", "IDENTIFICADOR", 
			"COMENTARIO_LINEA", "COMENTARIO_BLOQUE", "ESPACIOS_BLANCO"
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
	public String getGrammarFileName() { return "Zetariano.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ZetarianoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public Definicion_claseContext definicion_clase() {
			return getRuleContext(Definicion_claseContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ZetarianoParser.EOF, 0); }
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitPrograma(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			definicion_clase();
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
	public static class Definicion_claseContext extends ParserRuleContext {
		public Definicion_claseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definicion_clase; }
	 
		public Definicion_claseContext() { }
		public void copyFrom(Definicion_claseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DefClaseContext extends Definicion_claseContext {
		public TerminalNode PUBLIC() { return getToken(ZetarianoParser.PUBLIC, 0); }
		public TerminalNode CLASS() { return getToken(ZetarianoParser.CLASS, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public List<Miembro_claseContext> miembro_clase() {
			return getRuleContexts(Miembro_claseContext.class);
		}
		public Miembro_claseContext miembro_clase(int i) {
			return getRuleContext(Miembro_claseContext.class,i);
		}
		public DefClaseContext(Definicion_claseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterDefClase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitDefClase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitDefClase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Definicion_claseContext definicion_clase() throws RecognitionException {
		Definicion_claseContext _localctx = new Definicion_claseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_definicion_clase);
		int _la;
		try {
			_localctx = new DefClaseContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(PUBLIC);
			setState(56);
			match(CLASS);
			setState(57);
			match(IDENTIFICADOR);
			setState(58);
			match(LLAVE_IZQ);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460752303425474L) != 0)) {
				{
				{
				setState(59);
				miembro_clase();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
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
	public static class Miembro_claseContext extends ParserRuleContext {
		public Miembro_claseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_miembro_clase; }
	 
		public Miembro_claseContext() { }
		public void copyFrom(Miembro_claseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MiembroAtributoContext extends Miembro_claseContext {
		public Atributo_claseContext atributo_clase() {
			return getRuleContext(Atributo_claseContext.class,0);
		}
		public MiembroAtributoContext(Miembro_claseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterMiembroAtributo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitMiembroAtributo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitMiembroAtributo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MiembroMetodoContext extends Miembro_claseContext {
		public MetodoContext metodo() {
			return getRuleContext(MetodoContext.class,0);
		}
		public MiembroMetodoContext(Miembro_claseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterMiembroMetodo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitMiembroMetodo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitMiembroMetodo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MiembroConstructorContext extends Miembro_claseContext {
		public ConstructorContext constructor() {
			return getRuleContext(ConstructorContext.class,0);
		}
		public MiembroConstructorContext(Miembro_claseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterMiembroConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitMiembroConstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitMiembroConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Miembro_claseContext miembro_clase() throws RecognitionException {
		Miembro_claseContext _localctx = new Miembro_claseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_miembro_clase);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				_localctx = new MiembroAtributoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				atributo_clase();
				}
				break;
			case 2:
				_localctx = new MiembroConstructorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				constructor();
				}
				break;
			case 3:
				_localctx = new MiembroMetodoContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(69);
				metodo();
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
	public static class Tipo_datoContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ZetarianoParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(ZetarianoParser.DOUBLE, 0); }
		public TerminalNode CHAR() { return getToken(ZetarianoParser.CHAR, 0); }
		public TerminalNode BOOLEAN() { return getToken(ZetarianoParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(ZetarianoParser.STRING, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public Tipo_datoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_dato; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterTipo_dato(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitTipo_dato(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitTipo_dato(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tipo_datoContext tipo_dato() throws RecognitionException {
		Tipo_datoContext _localctx = new Tipo_datoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo_dato);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460752303425472L) != 0)) ) {
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
	public static class Atributo_claseContext extends ParserRuleContext {
		public Atributo_claseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributo_clase; }
	 
		public Atributo_claseContext() { }
		public void copyFrom(Atributo_claseContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtributoSimpleContext extends Atributo_claseContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public TerminalNode PUBLIC() { return getToken(ZetarianoParser.PUBLIC, 0); }
		public AtributoSimpleContext(Atributo_claseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterAtributoSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitAtributoSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitAtributoSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtributoArrayContext extends Atributo_claseContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public TerminalNode PUBLIC() { return getToken(ZetarianoParser.PUBLIC, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(ZetarianoParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(ZetarianoParser.CORCHETE_IZQ, i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(ZetarianoParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(ZetarianoParser.CORCHETE_DER, i);
		}
		public AtributoArrayContext(Atributo_claseContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterAtributoArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitAtributoArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitAtributoArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atributo_claseContext atributo_clase() throws RecognitionException {
		Atributo_claseContext _localctx = new Atributo_claseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atributo_clase);
		int _la;
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new AtributoSimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC) {
					{
					setState(74);
					match(PUBLIC);
					}
				}

				setState(77);
				tipo_dato();
				setState(78);
				match(IDENTIFICADOR);
				setState(79);
				match(PUNTO_Y_COMA);
				}
				break;
			case 2:
				_localctx = new AtributoArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PUBLIC) {
					{
					setState(81);
					match(PUBLIC);
					}
				}

				setState(84);
				tipo_dato();
				setState(87); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(85);
					match(CORCHETE_IZQ);
					setState(86);
					match(CORCHETE_DER);
					}
					}
					setState(89); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CORCHETE_IZQ );
				setState(91);
				match(IDENTIFICADOR);
				setState(92);
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
	public static class ConstructorContext extends ParserRuleContext {
		public ConstructorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor; }
	 
		public ConstructorContext() { }
		public void copyFrom(ConstructorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DefConstructorContext extends ConstructorContext {
		public TerminalNode PUBLIC() { return getToken(ZetarianoParser.PUBLIC, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public DefConstructorContext(ConstructorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterDefConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitDefConstructor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitDefConstructor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorContext constructor() throws RecognitionException {
		ConstructorContext _localctx = new ConstructorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constructor);
		int _la;
		try {
			_localctx = new DefConstructorContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(PUBLIC);
			setState(97);
			match(IDENTIFICADOR);
			setState(98);
			match(PAR_IZQ);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460752303425472L) != 0)) {
				{
				setState(99);
				parametros();
				}
			}

			setState(102);
			match(PAR_DER);
			setState(103);
			match(LLAVE_IZQ);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873760993264L) != 0)) {
				{
				{
				setState(104);
				instruccion();
				}
				}
				setState(109);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(110);
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
	public static class MetodoContext extends ParserRuleContext {
		public MetodoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metodo; }
	 
		public MetodoContext() { }
		public void copyFrom(MetodoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MetodoSinRetornoContext extends MetodoContext {
		public TerminalNode PUBLIC() { return getToken(ZetarianoParser.PUBLIC, 0); }
		public TerminalNode VOID() { return getToken(ZetarianoParser.VOID, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public MetodoSinRetornoContext(MetodoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterMetodoSinRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitMetodoSinRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitMetodoSinRetorno(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MetodoConRetornoContext extends MetodoContext {
		public TerminalNode PUBLIC() { return getToken(ZetarianoParser.PUBLIC, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public MetodoConRetornoContext(MetodoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterMetodoConRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitMetodoConRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitMetodoConRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MetodoContext metodo() throws RecognitionException {
		MetodoContext _localctx = new MetodoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_metodo);
		int _la;
		try {
			setState(145);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new MetodoSinRetornoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(112);
				match(PUBLIC);
				setState(113);
				match(VOID);
				setState(114);
				match(IDENTIFICADOR);
				setState(115);
				match(PAR_IZQ);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460752303425472L) != 0)) {
					{
					setState(116);
					parametros();
					}
				}

				setState(119);
				match(PAR_DER);
				setState(120);
				match(LLAVE_IZQ);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873760993264L) != 0)) {
					{
					{
					setState(121);
					instruccion();
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(127);
				match(LLAVE_DER);
				}
				break;
			case 2:
				_localctx = new MetodoConRetornoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				match(PUBLIC);
				setState(129);
				tipo_dato();
				setState(130);
				match(IDENTIFICADOR);
				setState(131);
				match(PAR_IZQ);
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460752303425472L) != 0)) {
					{
					setState(132);
					parametros();
					}
				}

				setState(135);
				match(PAR_DER);
				setState(136);
				match(LLAVE_IZQ);
				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873760993264L) != 0)) {
					{
					{
					setState(137);
					instruccion();
					}
					}
					setState(142);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(143);
				match(LLAVE_DER);
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
	public static class ParametrosContext extends ParserRuleContext {
		public List<ParametroContext> parametro() {
			return getRuleContexts(ParametroContext.class);
		}
		public ParametroContext parametro(int i) {
			return getRuleContext(ParametroContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(ZetarianoParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(ZetarianoParser.COMA, i);
		}
		public ParametrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterParametros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitParametros(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitParametros(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametrosContext parametros() throws RecognitionException {
		ParametrosContext _localctx = new ParametrosContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parametros);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			parametro();
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(148);
				match(COMA);
				setState(149);
				parametro();
				}
				}
				setState(154);
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
	public static class ParametroContext extends ParserRuleContext {
		public ParametroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametro; }
	 
		public ParametroContext() { }
		public void copyFrom(ParametroContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParamArrayContext extends ParametroContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(ZetarianoParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(ZetarianoParser.CORCHETE_IZQ, i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(ZetarianoParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(ZetarianoParser.CORCHETE_DER, i);
		}
		public ParamArrayContext(ParametroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterParamArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitParamArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitParamArray(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParamSimpleContext extends ParametroContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public ParamSimpleContext(ParametroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterParamSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitParamSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitParamSimple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parametro);
		int _la;
		try {
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				_localctx = new ParamSimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				tipo_dato();
				setState(156);
				match(IDENTIFICADOR);
				}
				break;
			case 2:
				_localctx = new ParamArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(158);
				tipo_dato();
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(159);
					match(CORCHETE_IZQ);
					setState(160);
					match(CORCHETE_DER);
					}
					}
					setState(163); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CORCHETE_IZQ );
				setState(165);
				match(IDENTIFICADOR);
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
	public static class InstruccionContext extends ParserRuleContext {
		public InstruccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion; }
	 
		public InstruccionContext() { }
		public void copyFrom(InstruccionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtAsignacionContext extends InstruccionContext {
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public StmtAsignacionContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtAsignacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtAsignacion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtSeleccionContext extends InstruccionContext {
		public SeleccionContext seleccion() {
			return getRuleContext(SeleccionContext.class,0);
		}
		public StmtSeleccionContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtSeleccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtSeleccion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtSeleccion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtDeclaracionContext extends InstruccionContext {
		public Declaracion_variableContext declaracion_variable() {
			return getRuleContext(Declaracion_variableContext.class,0);
		}
		public StmtDeclaracionContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtDeclaracion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtDeclaracion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtDeclaracion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtBreakContext extends InstruccionContext {
		public TerminalNode BREAK() { return getToken(ZetarianoParser.BREAK, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public StmtBreakContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtBreak(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtBreak(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtBreak(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtExpresionContext extends InstruccionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public StmtExpresionContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtExpresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtExpresion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtCicloContext extends InstruccionContext {
		public CicloContext ciclo() {
			return getRuleContext(CicloContext.class,0);
		}
		public StmtCicloContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtCiclo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtCiclo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtCiclo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtCondicionalContext extends InstruccionContext {
		public CondicionalContext condicional() {
			return getRuleContext(CondicionalContext.class,0);
		}
		public StmtCondicionalContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtCondicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtCondicional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtCondicional(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtReturnContext extends InstruccionContext {
		public TerminalNode RETURN() { return getToken(ZetarianoParser.RETURN, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public StmtReturnContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtContinueContext extends InstruccionContext {
		public TerminalNode CONTINUE() { return getToken(ZetarianoParser.CONTINUE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public StmtContinueContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStmtContinue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStmtContinue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStmtContinue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_instruccion);
		int _la;
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new StmtDeclaracionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				declaracion_variable();
				}
				break;
			case 2:
				_localctx = new StmtAsignacionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				asignacion();
				}
				break;
			case 3:
				_localctx = new StmtCondicionalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				condicional();
				}
				break;
			case 4:
				_localctx = new StmtSeleccionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(172);
				seleccion();
				}
				break;
			case 5:
				_localctx = new StmtCicloContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(173);
				ciclo();
				}
				break;
			case 6:
				_localctx = new StmtReturnContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(174);
				match(RETURN);
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873744435248L) != 0)) {
					{
					setState(175);
					expresion(0);
					}
				}

				setState(178);
				match(PUNTO_Y_COMA);
				}
				break;
			case 7:
				_localctx = new StmtBreakContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(179);
				match(BREAK);
				setState(180);
				match(PUNTO_Y_COMA);
				}
				break;
			case 8:
				_localctx = new StmtContinueContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(181);
				match(CONTINUE);
				setState(182);
				match(PUNTO_Y_COMA);
				}
				break;
			case 9:
				_localctx = new StmtExpresionContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(183);
				expresion(0);
				setState(184);
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
	public static class DeclConListaLiteralContext extends Declaracion_variableContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode ASIGNACION() { return getToken(ZetarianoParser.ASIGNACION, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(ZetarianoParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(ZetarianoParser.CORCHETE_IZQ, i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(ZetarianoParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(ZetarianoParser.CORCHETE_DER, i);
		}
		public DeclConListaLiteralContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterDeclConListaLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitDeclConListaLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitDeclConListaLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclConTipoContext extends Declaracion_variableContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(ZetarianoParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(ZetarianoParser.CORCHETE_IZQ, i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(ZetarianoParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(ZetarianoParser.CORCHETE_DER, i);
		}
		public TerminalNode ASIGNACION() { return getToken(ZetarianoParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public DeclConTipoContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterDeclConTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitDeclConTipo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitDeclConTipo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclConMatrizLiteralContext extends Declaracion_variableContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode ASIGNACION() { return getToken(ZetarianoParser.ASIGNACION, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public Init_matrizContext init_matriz() {
			return getRuleContext(Init_matrizContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(ZetarianoParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(ZetarianoParser.CORCHETE_IZQ, i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(ZetarianoParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(ZetarianoParser.CORCHETE_DER, i);
		}
		public DeclConMatrizLiteralContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterDeclConMatrizLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitDeclConMatrizLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitDeclConMatrizLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaracion_variableContext declaracion_variable() throws RecognitionException {
		Declaracion_variableContext _localctx = new Declaracion_variableContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declaracion_variable);
		int _la;
		try {
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new DeclConTipoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				tipo_dato();
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CORCHETE_IZQ) {
					{
					{
					setState(189);
					match(CORCHETE_IZQ);
					setState(190);
					match(CORCHETE_DER);
					}
					}
					setState(195);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(196);
				match(IDENTIFICADOR);
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASIGNACION) {
					{
					setState(197);
					match(ASIGNACION);
					setState(198);
					expresion(0);
					}
				}

				setState(201);
				match(PUNTO_Y_COMA);
				}
				break;
			case 2:
				_localctx = new DeclConListaLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				tipo_dato();
				setState(206); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(204);
					match(CORCHETE_IZQ);
					setState(205);
					match(CORCHETE_DER);
					}
					}
					setState(208); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CORCHETE_IZQ );
				setState(210);
				match(IDENTIFICADOR);
				setState(211);
				match(ASIGNACION);
				setState(212);
				match(LLAVE_IZQ);
				setState(213);
				lista_expresiones();
				setState(214);
				match(LLAVE_DER);
				setState(215);
				match(PUNTO_Y_COMA);
				}
				break;
			case 3:
				_localctx = new DeclConMatrizLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
				tipo_dato();
				setState(220); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(218);
					match(CORCHETE_IZQ);
					setState(219);
					match(CORCHETE_DER);
					}
					}
					setState(222); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CORCHETE_IZQ );
				setState(224);
				match(IDENTIFICADOR);
				setState(225);
				match(ASIGNACION);
				setState(226);
				match(LLAVE_IZQ);
				setState(227);
				init_matriz();
				setState(228);
				match(LLAVE_DER);
				setState(229);
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
	public static class Init_matrizContext extends ParserRuleContext {
		public List<Init_elementoContext> init_elemento() {
			return getRuleContexts(Init_elementoContext.class);
		}
		public Init_elementoContext init_elemento(int i) {
			return getRuleContext(Init_elementoContext.class,i);
		}
		public List<TerminalNode> COMA() { return getTokens(ZetarianoParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(ZetarianoParser.COMA, i);
		}
		public Init_matrizContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init_matriz; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterInit_matriz(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitInit_matriz(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitInit_matriz(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Init_matrizContext init_matriz() throws RecognitionException {
		Init_matrizContext _localctx = new Init_matrizContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_init_matriz);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			init_elemento();
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(234);
				match(COMA);
				setState(235);
				init_elemento();
				}
				}
				setState(240);
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
	public static class Init_elementoContext extends ParserRuleContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public Init_matrizContext init_matriz() {
			return getRuleContext(Init_matrizContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public Init_elementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init_elemento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterInit_elemento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitInit_elemento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitInit_elemento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Init_elementoContext init_elemento() throws RecognitionException {
		Init_elementoContext _localctx = new Init_elementoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_init_elemento);
		try {
			setState(246);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEW:
			case NULL:
			case TRUE:
			case FALSE:
			case NEGACION:
			case RESTA:
			case PAR_IZQ:
			case CADENA:
			case CARACTER:
			case NUMERO_DECIMAL:
			case NUMERO_ENTERO:
			case IDENTIFICADOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(241);
				expresion(0);
				}
				break;
			case LLAVE_IZQ:
				enterOuterAlt(_localctx, 2);
				{
				setState(242);
				match(LLAVE_IZQ);
				setState(243);
				init_matriz();
				setState(244);
				match(LLAVE_DER);
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
	public static class AsignacionSimpleContext extends AsignacionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(ZetarianoParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public AsignacionSimpleContext(AsignacionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterAsignacionSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitAsignacionSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitAsignacionSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AsignacionCompuestaContext extends AsignacionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public TerminalNode MAS_IGUAL() { return getToken(ZetarianoParser.MAS_IGUAL, 0); }
		public TerminalNode MENOS_IGUAL() { return getToken(ZetarianoParser.MENOS_IGUAL, 0); }
		public TerminalNode MULT_IGUAL() { return getToken(ZetarianoParser.MULT_IGUAL, 0); }
		public AsignacionCompuestaContext(AsignacionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterAsignacionCompuesta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitAsignacionCompuesta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitAsignacionCompuesta(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_asignacion);
		int _la;
		try {
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				_localctx = new AsignacionSimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				variable_asignable(0);
				setState(249);
				match(ASIGNACION);
				setState(250);
				expresion(0);
				setState(251);
				match(PUNTO_Y_COMA);
				}
				break;
			case 2:
				_localctx = new AsignacionCompuestaContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(253);
				variable_asignable(0);
				setState(254);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 240518168576L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(255);
				expresion(0);
				setState(256);
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
	public static class VarSimpleContext extends Variable_asignableContext {
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public VarSimpleContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterVarSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitVarSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitVarSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarMiembroContext extends Variable_asignableContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(ZetarianoParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public VarMiembroContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterVarMiembro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitVarMiembro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitVarMiembro(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarArrayContext extends Variable_asignableContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode CORCHETE_IZQ() { return getToken(ZetarianoParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(ZetarianoParser.CORCHETE_DER, 0); }
		public VarArrayContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterVarArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitVarArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitVarArray(this);
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
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_variable_asignable, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new VarSimpleContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(261);
			match(IDENTIFICADOR);
			}
			_ctx.stop = _input.LT(-1);
			setState(273);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(271);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new VarArrayContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(263);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(264);
						match(CORCHETE_IZQ);
						setState(265);
						expresion(0);
						setState(266);
						match(CORCHETE_DER);
						}
						break;
					case 2:
						{
						_localctx = new VarMiembroContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(268);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(269);
						match(PUNTO);
						setState(270);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(275);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
	public static class StatementIfContext extends CondicionalContext {
		public List<TerminalNode> IF() { return getTokens(ZetarianoParser.IF); }
		public TerminalNode IF(int i) {
			return getToken(ZetarianoParser.IF, i);
		}
		public List<TerminalNode> PAR_IZQ() { return getTokens(ZetarianoParser.PAR_IZQ); }
		public TerminalNode PAR_IZQ(int i) {
			return getToken(ZetarianoParser.PAR_IZQ, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> PAR_DER() { return getTokens(ZetarianoParser.PAR_DER); }
		public TerminalNode PAR_DER(int i) {
			return getToken(ZetarianoParser.PAR_DER, i);
		}
		public List<BloqueContext> bloque() {
			return getRuleContexts(BloqueContext.class);
		}
		public BloqueContext bloque(int i) {
			return getRuleContext(BloqueContext.class,i);
		}
		public List<TerminalNode> ELSE() { return getTokens(ZetarianoParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(ZetarianoParser.ELSE, i);
		}
		public StatementIfContext(CondicionalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStatementIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStatementIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStatementIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicionalContext condicional() throws RecognitionException {
		CondicionalContext _localctx = new CondicionalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_condicional);
		try {
			int _alt;
			_localctx = new StatementIfContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(IF);
			setState(277);
			match(PAR_IZQ);
			setState(278);
			expresion(0);
			setState(279);
			match(PAR_DER);
			setState(280);
			bloque();
			setState(290);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(281);
					match(ELSE);
					setState(282);
					match(IF);
					setState(283);
					match(PAR_IZQ);
					setState(284);
					expresion(0);
					setState(285);
					match(PAR_DER);
					setState(286);
					bloque();
					}
					} 
				}
				setState(292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			setState(295);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(293);
				match(ELSE);
				setState(294);
				bloque();
				}
				break;
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
	public static class BloqueContext extends ParserRuleContext {
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public BloqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloque; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterBloque(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitBloque(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitBloque(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BloqueContext bloque() throws RecognitionException {
		BloqueContext _localctx = new BloqueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_bloque);
		int _la;
		try {
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LLAVE_IZQ:
				enterOuterAlt(_localctx, 1);
				{
				setState(297);
				match(LLAVE_IZQ);
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873760993264L) != 0)) {
					{
					{
					setState(298);
					instruccion();
					}
					}
					setState(303);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(304);
				match(LLAVE_DER);
				}
				break;
			case NEW:
			case NULL:
			case INT:
			case DOUBLE:
			case CHAR:
			case BOOLEAN:
			case STRING:
			case TRUE:
			case FALSE:
			case IF:
			case SWITCH:
			case FOR:
			case WHILE:
			case DO:
			case BREAK:
			case CONTINUE:
			case RETURN:
			case NEGACION:
			case RESTA:
			case PAR_IZQ:
			case CADENA:
			case CARACTER:
			case NUMERO_DECIMAL:
			case NUMERO_ENTERO:
			case IDENTIFICADOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(305);
				instruccion();
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
	public static class SeleccionContext extends ParserRuleContext {
		public SeleccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seleccion; }
	 
		public SeleccionContext() { }
		public void copyFrom(SeleccionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementSwitchContext extends SeleccionContext {
		public TerminalNode SWITCH() { return getToken(ZetarianoParser.SWITCH, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(ZetarianoParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(ZetarianoParser.LLAVE_DER, 0); }
		public List<Caso_switchContext> caso_switch() {
			return getRuleContexts(Caso_switchContext.class);
		}
		public Caso_switchContext caso_switch(int i) {
			return getRuleContext(Caso_switchContext.class,i);
		}
		public Caso_defaultContext caso_default() {
			return getRuleContext(Caso_defaultContext.class,0);
		}
		public StatementSwitchContext(SeleccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterStatementSwitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitStatementSwitch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitStatementSwitch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeleccionContext seleccion() throws RecognitionException {
		SeleccionContext _localctx = new SeleccionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_seleccion);
		int _la;
		try {
			_localctx = new StatementSwitchContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(SWITCH);
			setState(309);
			match(PAR_IZQ);
			setState(310);
			expresion(0);
			setState(311);
			match(PAR_DER);
			setState(312);
			match(LLAVE_IZQ);
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE) {
				{
				{
				setState(313);
				caso_switch();
				}
				}
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(319);
				caso_default();
				}
			}

			setState(322);
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
	public static class Caso_switchContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(ZetarianoParser.CASE, 0); }
		public Valor_primitivoContext valor_primitivo() {
			return getRuleContext(Valor_primitivoContext.class,0);
		}
		public TerminalNode DOS_PUNTOS() { return getToken(ZetarianoParser.DOS_PUNTOS, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Caso_switchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caso_switch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterCaso_switch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitCaso_switch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitCaso_switch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Caso_switchContext caso_switch() throws RecognitionException {
		Caso_switchContext _localctx = new Caso_switchContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_caso_switch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			match(CASE);
			setState(325);
			valor_primitivo();
			setState(326);
			match(DOS_PUNTOS);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873760993264L) != 0)) {
				{
				{
				setState(327);
				instruccion();
				}
				}
				setState(332);
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
	public static class Caso_defaultContext extends ParserRuleContext {
		public TerminalNode DEFAULT() { return getToken(ZetarianoParser.DEFAULT, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(ZetarianoParser.DOS_PUNTOS, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Caso_defaultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caso_default; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterCaso_default(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitCaso_default(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitCaso_default(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Caso_defaultContext caso_default() throws RecognitionException {
		Caso_defaultContext _localctx = new Caso_defaultContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_caso_default);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(DEFAULT);
			setState(334);
			match(DOS_PUNTOS);
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873760993264L) != 0)) {
				{
				{
				setState(335);
				instruccion();
				}
				}
				setState(340);
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
	public static class CicloDoWhileContext extends CicloContext {
		public TerminalNode DO() { return getToken(ZetarianoParser.DO, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(ZetarianoParser.WHILE, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(ZetarianoParser.PUNTO_Y_COMA, 0); }
		public CicloDoWhileContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterCicloDoWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitCicloDoWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitCicloDoWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CicloWhileContext extends CicloContext {
		public TerminalNode WHILE() { return getToken(ZetarianoParser.WHILE, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public CicloWhileContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterCicloWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitCicloWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitCicloWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CicloForContext extends CicloContext {
		public TerminalNode FOR() { return getToken(ZetarianoParser.FOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public List<TerminalNode> PUNTO_Y_COMA() { return getTokens(ZetarianoParser.PUNTO_Y_COMA); }
		public TerminalNode PUNTO_Y_COMA(int i) {
			return getToken(ZetarianoParser.PUNTO_Y_COMA, i);
		}
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public Init_forContext init_for() {
			return getRuleContext(Init_forContext.class,0);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public Paso_forContext paso_for() {
			return getRuleContext(Paso_forContext.class,0);
		}
		public CicloForContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterCicloFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitCicloFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitCicloFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CicloContext ciclo() throws RecognitionException {
		CicloContext _localctx = new CicloContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_ciclo);
		int _la;
		try {
			setState(370);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				_localctx = new CicloForContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(341);
				match(FOR);
				setState(342);
				match(PAR_IZQ);
				setState(344);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460752303425472L) != 0)) {
					{
					setState(343);
					init_for();
					}
				}

				setState(346);
				match(PUNTO_Y_COMA);
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873744435248L) != 0)) {
					{
					setState(347);
					expresion(0);
					}
				}

				setState(350);
				match(PUNTO_Y_COMA);
				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873744435248L) != 0)) {
					{
					setState(351);
					paso_for();
					}
				}

				setState(354);
				match(PAR_DER);
				setState(355);
				bloque();
				}
				break;
			case WHILE:
				_localctx = new CicloWhileContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(356);
				match(WHILE);
				setState(357);
				match(PAR_IZQ);
				setState(358);
				expresion(0);
				setState(359);
				match(PAR_DER);
				setState(360);
				bloque();
				}
				break;
			case DO:
				_localctx = new CicloDoWhileContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(362);
				match(DO);
				setState(363);
				bloque();
				setState(364);
				match(WHILE);
				setState(365);
				match(PAR_IZQ);
				setState(366);
				expresion(0);
				setState(367);
				match(PAR_DER);
				setState(368);
				match(PUNTO_Y_COMA);
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
	public static class Init_forContext extends ParserRuleContext {
		public Init_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init_for; }
	 
		public Init_forContext() { }
		public void copyFrom(Init_forContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InitForAsigContext extends Init_forContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(ZetarianoParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public InitForAsigContext(Init_forContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterInitForAsig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitInitForAsig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitInitForAsig(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InitForDeclContext extends Init_forContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode ASIGNACION() { return getToken(ZetarianoParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public InitForDeclContext(Init_forContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterInitForDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitInitForDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitInitForDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Init_forContext init_for() throws RecognitionException {
		Init_forContext _localctx = new Init_forContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_init_for);
		try {
			setState(381);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				_localctx = new InitForDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
				tipo_dato();
				setState(373);
				match(IDENTIFICADOR);
				setState(374);
				match(ASIGNACION);
				setState(375);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new InitForAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				variable_asignable(0);
				setState(378);
				match(ASIGNACION);
				setState(379);
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
	public static class Paso_forContext extends ParserRuleContext {
		public Paso_forContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paso_for; }
	 
		public Paso_forContext() { }
		public void copyFrom(Paso_forContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PasoForExprContext extends Paso_forContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public PasoForExprContext(Paso_forContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterPasoForExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitPasoForExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitPasoForExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PasoForAsigContext extends Paso_forContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(ZetarianoParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public PasoForAsigContext(Paso_forContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterPasoForAsig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitPasoForAsig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitPasoForAsig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Paso_forContext paso_for() throws RecognitionException {
		Paso_forContext _localctx = new Paso_forContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_paso_for);
		try {
			setState(388);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				_localctx = new PasoForExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(383);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new PasoForAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(384);
				variable_asignable(0);
				setState(385);
				match(ASIGNACION);
				setState(386);
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
		public TerminalNode RESTA() { return getToken(ZetarianoParser.RESTA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNegativaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprNegativa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprNegativa(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprNegativa(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprMultiplicacionDivisionModuloContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode MULT() { return getToken(ZetarianoParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(ZetarianoParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(ZetarianoParser.MOD, 0); }
		public ExprMultiplicacionDivisionModuloContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprMultiplicacionDivisionModulo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprMultiplicacionDivisionModulo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprMultiplicacionDivisionModulo(this);
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
		public TerminalNode IGUAL_QUE() { return getToken(ZetarianoParser.IGUAL_QUE, 0); }
		public TerminalNode DIFERENTE_QUE() { return getToken(ZetarianoParser.DIFERENTE_QUE, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(ZetarianoParser.MAYOR_QUE, 0); }
		public TerminalNode MENOR_QUE() { return getToken(ZetarianoParser.MENOR_QUE, 0); }
		public TerminalNode MAYOR_IGUAL_QUE() { return getToken(ZetarianoParser.MAYOR_IGUAL_QUE, 0); }
		public TerminalNode MENOR_IGUAL_QUE() { return getToken(ZetarianoParser.MENOR_IGUAL_QUE, 0); }
		public ExprRelacionalContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprRelacional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprRelacional(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParentesisContext extends ExpresionContext {
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public ExprParentesisContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprParentesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprParentesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprParentesis(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPostIncrementoContext extends ExpresionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode INCREMENTO() { return getToken(ZetarianoParser.INCREMENTO, 0); }
		public ExprPostIncrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprPostIncremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprPostIncremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprPostIncremento(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLlamadaMetodoContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(ZetarianoParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public ExprLlamadaMetodoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprLlamadaMetodo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprLlamadaMetodo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprLlamadaMetodo(this);
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
		public TerminalNode SUMA() { return getToken(ZetarianoParser.SUMA, 0); }
		public TerminalNode RESTA() { return getToken(ZetarianoParser.RESTA, 0); }
		public ExprSumaRestaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprSumaResta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprSumaResta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprSumaResta(this);
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
		public TerminalNode OR() { return getToken(ZetarianoParser.OR, 0); }
		public ExprOrContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprInstanciaArregloContext extends ExpresionContext {
		public TerminalNode NEW() { return getToken(ZetarianoParser.NEW, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(ZetarianoParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(ZetarianoParser.CORCHETE_IZQ, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(ZetarianoParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(ZetarianoParser.CORCHETE_DER, i);
		}
		public ExprInstanciaArregloContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprInstanciaArreglo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprInstanciaArreglo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprInstanciaArreglo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNegadaContext extends ExpresionContext {
		public TerminalNode NEGACION() { return getToken(ZetarianoParser.NEGACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNegadaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprNegada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprNegada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprNegada(this);
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
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprPrimitivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprPrimitivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprPrimitivo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPostDecrementoContext extends ExpresionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode DECREMENTO() { return getToken(ZetarianoParser.DECREMENTO, 0); }
		public ExprPostDecrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprPostDecremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprPostDecremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprPostDecremento(this);
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
		public TerminalNode AND() { return getToken(ZetarianoParser.AND, 0); }
		public ExprAndContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprTernarioContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode INTERROGACION() { return getToken(ZetarianoParser.INTERROGACION, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(ZetarianoParser.DOS_PUNTOS, 0); }
		public ExprTernarioContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprTernario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprTernario(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprTernario(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLlamadaFuncionContext extends ExpresionContext {
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public ExprLlamadaFuncionContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprLlamadaFuncion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprLlamadaFuncion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprLlamadaFuncion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAccesoMiembroContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(ZetarianoParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public ExprAccesoMiembroContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprAccesoMiembro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprAccesoMiembro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprAccesoMiembro(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprInstanciaObjetoContext extends ExpresionContext {
		public TerminalNode NEW() { return getToken(ZetarianoParser.NEW, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(ZetarianoParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(ZetarianoParser.PAR_DER, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public ExprInstanciaObjetoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprInstanciaObjeto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprInstanciaObjeto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprInstanciaObjeto(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAccesoArrayContext extends ExpresionContext {
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public TerminalNode CORCHETE_IZQ() { return getToken(ZetarianoParser.CORCHETE_IZQ, 0); }
		public TerminalNode CORCHETE_DER() { return getToken(ZetarianoParser.CORCHETE_DER, 0); }
		public ExprAccesoArrayContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterExprAccesoArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitExprAccesoArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitExprAccesoArray(this);
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
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_expresion, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				_localctx = new ExprParentesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(391);
				match(PAR_IZQ);
				setState(392);
				expresion(0);
				setState(393);
				match(PAR_DER);
				}
				break;
			case 2:
				{
				_localctx = new ExprInstanciaObjetoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(395);
				match(NEW);
				setState(396);
				match(IDENTIFICADOR);
				setState(397);
				match(PAR_IZQ);
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873744435248L) != 0)) {
					{
					setState(398);
					lista_expresiones();
					}
				}

				setState(401);
				match(PAR_DER);
				}
				break;
			case 3:
				{
				_localctx = new ExprInstanciaArregloContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(402);
				match(NEW);
				setState(403);
				tipo_dato();
				setState(408); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(404);
						match(CORCHETE_IZQ);
						setState(405);
						expresion(0);
						setState(406);
						match(CORCHETE_DER);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(410); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 4:
				{
				_localctx = new ExprLlamadaFuncionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(412);
				match(IDENTIFICADOR);
				setState(413);
				match(PAR_IZQ);
				setState(415);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873744435248L) != 0)) {
					{
					setState(414);
					lista_expresiones();
					}
				}

				setState(417);
				match(PAR_DER);
				}
				break;
			case 5:
				{
				_localctx = new ExprPostIncrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(418);
				variable_asignable(0);
				setState(419);
				match(INCREMENTO);
				}
				break;
			case 6:
				{
				_localctx = new ExprPostDecrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(421);
				variable_asignable(0);
				setState(422);
				match(DECREMENTO);
				}
				break;
			case 7:
				{
				_localctx = new ExprNegativaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(424);
				match(RESTA);
				setState(425);
				expresion(9);
				}
				break;
			case 8:
				{
				_localctx = new ExprNegadaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(426);
				match(NEGACION);
				setState(427);
				expresion(8);
				}
				break;
			case 9:
				{
				_localctx = new ExprPrimitivoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(428);
				valor_primitivo();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(470);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(468);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMultiplicacionDivisionModuloContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(431);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(432);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30786325577728L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(433);
						expresion(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprSumaRestaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(434);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(435);
						_la = _input.LA(1);
						if ( !(_la==SUMA || _la==RESTA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(436);
						expresion(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprRelacionalContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(437);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(438);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4227858432L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(439);
						expresion(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprAndContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(440);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(441);
						match(AND);
						setState(442);
						expresion(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprOrContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(443);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(444);
						match(OR);
						setState(445);
						expresion(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprTernarioContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(446);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(447);
						match(INTERROGACION);
						setState(448);
						expresion(0);
						setState(449);
						match(DOS_PUNTOS);
						setState(450);
						expresion(3);
						}
						break;
					case 7:
						{
						_localctx = new ExprLlamadaMetodoContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(452);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(453);
						match(PUNTO);
						setState(454);
						match(IDENTIFICADOR);
						setState(455);
						match(PAR_IZQ);
						setState(457);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1117457873744435248L) != 0)) {
							{
							setState(456);
							lista_expresiones();
							}
						}

						setState(459);
						match(PAR_DER);
						}
						break;
					case 8:
						{
						_localctx = new ExprAccesoArrayContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(460);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(461);
						match(CORCHETE_IZQ);
						setState(462);
						expresion(0);
						setState(463);
						match(CORCHETE_DER);
						}
						break;
					case 9:
						{
						_localctx = new ExprAccesoMiembroContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(465);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(466);
						match(PUNTO);
						setState(467);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(472);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
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
		public TerminalNode NUMERO_ENTERO() { return getToken(ZetarianoParser.NUMERO_ENTERO, 0); }
		public TerminalNode NUMERO_DECIMAL() { return getToken(ZetarianoParser.NUMERO_DECIMAL, 0); }
		public TerminalNode CADENA() { return getToken(ZetarianoParser.CADENA, 0); }
		public TerminalNode CARACTER() { return getToken(ZetarianoParser.CARACTER, 0); }
		public TerminalNode TRUE() { return getToken(ZetarianoParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(ZetarianoParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(ZetarianoParser.NULL, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(ZetarianoParser.IDENTIFICADOR, 0); }
		public Valor_primitivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valor_primitivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterValor_primitivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitValor_primitivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitValor_primitivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Valor_primitivoContext valor_primitivo() throws RecognitionException {
		Valor_primitivoContext _localctx = new Valor_primitivoContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_valor_primitivo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1116892707587889184L) != 0)) ) {
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
		public List<TerminalNode> COMA() { return getTokens(ZetarianoParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(ZetarianoParser.COMA, i);
		}
		public Lista_expresionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_expresiones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).enterLista_expresiones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ZetarianoListener ) ((ZetarianoListener)listener).exitLista_expresiones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ZetarianoVisitor ) return ((ZetarianoVisitor<? extends T>)visitor).visitLista_expresiones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lista_expresionesContext lista_expresiones() throws RecognitionException {
		Lista_expresionesContext _localctx = new Lista_expresionesContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_lista_expresiones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(475);
			expresion(0);
			setState(480);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(476);
				match(COMA);
				setState(477);
				expresion(0);
				}
				}
				setState(482);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return variable_asignable_sempred((Variable_asignableContext)_localctx, predIndex);
		case 23:
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
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		case 8:
			return precpred(_ctx, 14);
		case 9:
			return precpred(_ctx, 13);
		case 10:
			return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001>\u01e4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001=\b\u0001"+
		"\n\u0001\f\u0001@\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002G\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0003\u0004L\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0003\u0004S\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0004\u0004X\b\u0004\u000b\u0004\f\u0004Y\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004_\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005e\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005"+
		"\u0005j\b\u0005\n\u0005\f\u0005m\t\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006v\b"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006{\b\u0006\n\u0006"+
		"\f\u0006~\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u0086\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0005\u0006\u008b\b\u0006\n\u0006\f\u0006\u008e\t\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u0092\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0005\u0007\u0097\b\u0007\n\u0007\f\u0007\u009a\t\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0004\b\u00a2\b\b\u000b\b\f\b\u00a3"+
		"\u0001\b\u0001\b\u0003\b\u00a8\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u00b1\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u00bb\b\t\u0001\n\u0001\n\u0001\n\u0005"+
		"\n\u00c0\b\n\n\n\f\n\u00c3\t\n\u0001\n\u0001\n\u0001\n\u0003\n\u00c8\b"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0004\n\u00cf\b\n\u000b\n\f"+
		"\n\u00d0\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0004\n\u00dd\b\n\u000b\n\f\n\u00de\u0001\n\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00e8\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00ed\b\u000b\n\u000b\f\u000b\u00f0"+
		"\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00f7\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u0103\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u0110\b\u000e\n\u000e\f\u000e\u0113\t\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f"+
		"\u0121\b\u000f\n\u000f\f\u000f\u0124\t\u000f\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u0128\b\u000f\u0001\u0010\u0001\u0010\u0005\u0010\u012c\b\u0010"+
		"\n\u0010\f\u0010\u012f\t\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0133"+
		"\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0005\u0011\u013b\b\u0011\n\u0011\f\u0011\u013e\t\u0011\u0001\u0011"+
		"\u0003\u0011\u0141\b\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0005\u0012\u0149\b\u0012\n\u0012\f\u0012\u014c"+
		"\t\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u0151\b\u0013"+
		"\n\u0013\f\u0013\u0154\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u0159\b\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u015d\b\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u0161\b\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0003\u0014\u0173\b\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u017e\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0185\b\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u0190\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0004\u0017\u0199\b\u0017"+
		"\u000b\u0017\f\u0017\u019a\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u01a0\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u01ae\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017"+
		"\u01ca\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u01d5\b\u0017"+
		"\n\u0017\f\u0017\u01d8\t\u0017\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0005\u0019\u01df\b\u0019\n\u0019\f\u0019\u01e2\t\u0019"+
		"\u0001\u0019\u0000\u0002\u001c.\u001a\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02\u0000\u0006"+
		"\u0002\u0000\u0006\n;;\u0001\u0000#%\u0001\u0000*,\u0001\u0000()\u0001"+
		"\u0000\u001a\u001f\u0003\u0000\u0005\u0005\u000b\f7;\u0213\u00004\u0001"+
		"\u0000\u0000\u0000\u00027\u0001\u0000\u0000\u0000\u0004F\u0001\u0000\u0000"+
		"\u0000\u0006H\u0001\u0000\u0000\u0000\b^\u0001\u0000\u0000\u0000\n`\u0001"+
		"\u0000\u0000\u0000\f\u0091\u0001\u0000\u0000\u0000\u000e\u0093\u0001\u0000"+
		"\u0000\u0000\u0010\u00a7\u0001\u0000\u0000\u0000\u0012\u00ba\u0001\u0000"+
		"\u0000\u0000\u0014\u00e7\u0001\u0000\u0000\u0000\u0016\u00e9\u0001\u0000"+
		"\u0000\u0000\u0018\u00f6\u0001\u0000\u0000\u0000\u001a\u0102\u0001\u0000"+
		"\u0000\u0000\u001c\u0104\u0001\u0000\u0000\u0000\u001e\u0114\u0001\u0000"+
		"\u0000\u0000 \u0132\u0001\u0000\u0000\u0000\"\u0134\u0001\u0000\u0000"+
		"\u0000$\u0144\u0001\u0000\u0000\u0000&\u014d\u0001\u0000\u0000\u0000("+
		"\u0172\u0001\u0000\u0000\u0000*\u017d\u0001\u0000\u0000\u0000,\u0184\u0001"+
		"\u0000\u0000\u0000.\u01ad\u0001\u0000\u0000\u00000\u01d9\u0001\u0000\u0000"+
		"\u00002\u01db\u0001\u0000\u0000\u000045\u0003\u0002\u0001\u000056\u0005"+
		"\u0000\u0000\u00016\u0001\u0001\u0000\u0000\u000078\u0005\u0001\u0000"+
		"\u000089\u0005\u0002\u0000\u00009:\u0005;\u0000\u0000:>\u0005/\u0000\u0000"+
		";=\u0003\u0004\u0002\u0000<;\u0001\u0000\u0000\u0000=@\u0001\u0000\u0000"+
		"\u0000><\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0001\u0000"+
		"\u0000\u0000@>\u0001\u0000\u0000\u0000AB\u00050\u0000\u0000B\u0003\u0001"+
		"\u0000\u0000\u0000CG\u0003\b\u0004\u0000DG\u0003\n\u0005\u0000EG\u0003"+
		"\f\u0006\u0000FC\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000FE\u0001"+
		"\u0000\u0000\u0000G\u0005\u0001\u0000\u0000\u0000HI\u0007\u0000\u0000"+
		"\u0000I\u0007\u0001\u0000\u0000\u0000JL\u0005\u0001\u0000\u0000KJ\u0001"+
		"\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000"+
		"MN\u0003\u0006\u0003\u0000NO\u0005;\u0000\u0000OP\u00053\u0000\u0000P"+
		"_\u0001\u0000\u0000\u0000QS\u0005\u0001\u0000\u0000RQ\u0001\u0000\u0000"+
		"\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000TW\u0003\u0006"+
		"\u0003\u0000UV\u0005-\u0000\u0000VX\u0005.\u0000\u0000WU\u0001\u0000\u0000"+
		"\u0000XY\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000YZ\u0001\u0000"+
		"\u0000\u0000Z[\u0001\u0000\u0000\u0000[\\\u0005;\u0000\u0000\\]\u0005"+
		"3\u0000\u0000]_\u0001\u0000\u0000\u0000^K\u0001\u0000\u0000\u0000^R\u0001"+
		"\u0000\u0000\u0000_\t\u0001\u0000\u0000\u0000`a\u0005\u0001\u0000\u0000"+
		"ab\u0005;\u0000\u0000bd\u00051\u0000\u0000ce\u0003\u000e\u0007\u0000d"+
		"c\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0001\u0000\u0000"+
		"\u0000fg\u00052\u0000\u0000gk\u0005/\u0000\u0000hj\u0003\u0012\t\u0000"+
		"ih\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000"+
		"\u0000kl\u0001\u0000\u0000\u0000ln\u0001\u0000\u0000\u0000mk\u0001\u0000"+
		"\u0000\u0000no\u00050\u0000\u0000o\u000b\u0001\u0000\u0000\u0000pq\u0005"+
		"\u0001\u0000\u0000qr\u0005\u0003\u0000\u0000rs\u0005;\u0000\u0000su\u0005"+
		"1\u0000\u0000tv\u0003\u000e\u0007\u0000ut\u0001\u0000\u0000\u0000uv\u0001"+
		"\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000wx\u00052\u0000\u0000x|\u0005"+
		"/\u0000\u0000y{\u0003\u0012\t\u0000zy\u0001\u0000\u0000\u0000{~\u0001"+
		"\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000"+
		"}\u007f\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000\u007f\u0092"+
		"\u00050\u0000\u0000\u0080\u0081\u0005\u0001\u0000\u0000\u0081\u0082\u0003"+
		"\u0006\u0003\u0000\u0082\u0083\u0005;\u0000\u0000\u0083\u0085\u00051\u0000"+
		"\u0000\u0084\u0086\u0003\u000e\u0007\u0000\u0085\u0084\u0001\u0000\u0000"+
		"\u0000\u0085\u0086\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000"+
		"\u0000\u0087\u0088\u00052\u0000\u0000\u0088\u008c\u0005/\u0000\u0000\u0089"+
		"\u008b\u0003\u0012\t\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b\u008e"+
		"\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000\u008c\u008d"+
		"\u0001\u0000\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000\u008e\u008c"+
		"\u0001\u0000\u0000\u0000\u008f\u0090\u00050\u0000\u0000\u0090\u0092\u0001"+
		"\u0000\u0000\u0000\u0091p\u0001\u0000\u0000\u0000\u0091\u0080\u0001\u0000"+
		"\u0000\u0000\u0092\r\u0001\u0000\u0000\u0000\u0093\u0098\u0003\u0010\b"+
		"\u0000\u0094\u0095\u00055\u0000\u0000\u0095\u0097\u0003\u0010\b\u0000"+
		"\u0096\u0094\u0001\u0000\u0000\u0000\u0097\u009a\u0001\u0000\u0000\u0000"+
		"\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000"+
		"\u0099\u000f\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000"+
		"\u009b\u009c\u0003\u0006\u0003\u0000\u009c\u009d\u0005;\u0000\u0000\u009d"+
		"\u00a8\u0001\u0000\u0000\u0000\u009e\u00a1\u0003\u0006\u0003\u0000\u009f"+
		"\u00a0\u0005-\u0000\u0000\u00a0\u00a2\u0005.\u0000\u0000\u00a1\u009f\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0005;\u0000\u0000\u00a6\u00a8\u0001\u0000"+
		"\u0000\u0000\u00a7\u009b\u0001\u0000\u0000\u0000\u00a7\u009e\u0001\u0000"+
		"\u0000\u0000\u00a8\u0011\u0001\u0000\u0000\u0000\u00a9\u00bb\u0003\u0014"+
		"\n\u0000\u00aa\u00bb\u0003\u001a\r\u0000\u00ab\u00bb\u0003\u001e\u000f"+
		"\u0000\u00ac\u00bb\u0003\"\u0011\u0000\u00ad\u00bb\u0003(\u0014\u0000"+
		"\u00ae\u00b0\u0005\u0017\u0000\u0000\u00af\u00b1\u0003.\u0017\u0000\u00b0"+
		"\u00af\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b2\u00bb\u00053\u0000\u0000\u00b3\u00b4"+
		"\u0005\u0015\u0000\u0000\u00b4\u00bb\u00053\u0000\u0000\u00b5\u00b6\u0005"+
		"\u0016\u0000\u0000\u00b6\u00bb\u00053\u0000\u0000\u00b7\u00b8\u0003.\u0017"+
		"\u0000\u00b8\u00b9\u00053\u0000\u0000\u00b9\u00bb\u0001\u0000\u0000\u0000"+
		"\u00ba\u00a9\u0001\u0000\u0000\u0000\u00ba\u00aa\u0001\u0000\u0000\u0000"+
		"\u00ba\u00ab\u0001\u0000\u0000\u0000\u00ba\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ba\u00ad\u0001\u0000\u0000\u0000\u00ba\u00ae\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b3\u0001\u0000\u0000\u0000\u00ba\u00b5\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b7\u0001\u0000\u0000\u0000\u00bb\u0013\u0001\u0000\u0000\u0000"+
		"\u00bc\u00c1\u0003\u0006\u0003\u0000\u00bd\u00be\u0005-\u0000\u0000\u00be"+
		"\u00c0\u0005.\u0000\u0000\u00bf\u00bd\u0001\u0000\u0000\u0000\u00c0\u00c3"+
		"\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c1\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c4\u0001\u0000\u0000\u0000\u00c3\u00c1"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c7\u0005;\u0000\u0000\u00c5\u00c6\u0005"+
		"&\u0000\u0000\u00c6\u00c8\u0003.\u0017\u0000\u00c7\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000"+
		"\u0000\u00c9\u00ca\u00053\u0000\u0000\u00ca\u00e8\u0001\u0000\u0000\u0000"+
		"\u00cb\u00ce\u0003\u0006\u0003\u0000\u00cc\u00cd\u0005-\u0000\u0000\u00cd"+
		"\u00cf\u0005.\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000\u0000\u00cf\u00d0"+
		"\u0001\u0000\u0000\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d0\u00d1"+
		"\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3"+
		"\u0005;\u0000\u0000\u00d3\u00d4\u0005&\u0000\u0000\u00d4\u00d5\u0005/"+
		"\u0000\u0000\u00d5\u00d6\u00032\u0019\u0000\u00d6\u00d7\u00050\u0000\u0000"+
		"\u00d7\u00d8\u00053\u0000\u0000\u00d8\u00e8\u0001\u0000\u0000\u0000\u00d9"+
		"\u00dc\u0003\u0006\u0003\u0000\u00da\u00db\u0005-\u0000\u0000\u00db\u00dd"+
		"\u0005.\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00de\u0001"+
		"\u0000\u0000\u0000\u00de\u00dc\u0001\u0000\u0000\u0000\u00de\u00df\u0001"+
		"\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005"+
		";\u0000\u0000\u00e1\u00e2\u0005&\u0000\u0000\u00e2\u00e3\u0005/\u0000"+
		"\u0000\u00e3\u00e4\u0003\u0016\u000b\u0000\u00e4\u00e5\u00050\u0000\u0000"+
		"\u00e5\u00e6\u00053\u0000\u0000\u00e6\u00e8\u0001\u0000\u0000\u0000\u00e7"+
		"\u00bc\u0001\u0000\u0000\u0000\u00e7\u00cb\u0001\u0000\u0000\u0000\u00e7"+
		"\u00d9\u0001\u0000\u0000\u0000\u00e8\u0015\u0001\u0000\u0000\u0000\u00e9"+
		"\u00ee\u0003\u0018\f\u0000\u00ea\u00eb\u00055\u0000\u0000\u00eb\u00ed"+
		"\u0003\u0018\f\u0000\u00ec\u00ea\u0001\u0000\u0000\u0000\u00ed\u00f0\u0001"+
		"\u0000\u0000\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ee\u00ef\u0001"+
		"\u0000\u0000\u0000\u00ef\u0017\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001"+
		"\u0000\u0000\u0000\u00f1\u00f7\u0003.\u0017\u0000\u00f2\u00f3\u0005/\u0000"+
		"\u0000\u00f3\u00f4\u0003\u0016\u000b\u0000\u00f4\u00f5\u00050\u0000\u0000"+
		"\u00f5\u00f7\u0001\u0000\u0000\u0000\u00f6\u00f1\u0001\u0000\u0000\u0000"+
		"\u00f6\u00f2\u0001\u0000\u0000\u0000\u00f7\u0019\u0001\u0000\u0000\u0000"+
		"\u00f8\u00f9\u0003\u001c\u000e\u0000\u00f9\u00fa\u0005&\u0000\u0000\u00fa"+
		"\u00fb\u0003.\u0017\u0000\u00fb\u00fc\u00053\u0000\u0000\u00fc\u0103\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fe\u0003\u001c\u000e\u0000\u00fe\u00ff\u0007"+
		"\u0001\u0000\u0000\u00ff\u0100\u0003.\u0017\u0000\u0100\u0101\u00053\u0000"+
		"\u0000\u0101\u0103\u0001\u0000\u0000\u0000\u0102\u00f8\u0001\u0000\u0000"+
		"\u0000\u0102\u00fd\u0001\u0000\u0000\u0000\u0103\u001b\u0001\u0000\u0000"+
		"\u0000\u0104\u0105\u0006\u000e\uffff\uffff\u0000\u0105\u0106\u0005;\u0000"+
		"\u0000\u0106\u0111\u0001\u0000\u0000\u0000\u0107\u0108\n\u0002\u0000\u0000"+
		"\u0108\u0109\u0005-\u0000\u0000\u0109\u010a\u0003.\u0017\u0000\u010a\u010b"+
		"\u0005.\u0000\u0000\u010b\u0110\u0001\u0000\u0000\u0000\u010c\u010d\n"+
		"\u0001\u0000\u0000\u010d\u010e\u00056\u0000\u0000\u010e\u0110\u0005;\u0000"+
		"\u0000\u010f\u0107\u0001\u0000\u0000\u0000\u010f\u010c\u0001\u0000\u0000"+
		"\u0000\u0110\u0113\u0001\u0000\u0000\u0000\u0111\u010f\u0001\u0000\u0000"+
		"\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112\u001d\u0001\u0000\u0000"+
		"\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0114\u0115\u0005\r\u0000\u0000"+
		"\u0115\u0116\u00051\u0000\u0000\u0116\u0117\u0003.\u0017\u0000\u0117\u0118"+
		"\u00052\u0000\u0000\u0118\u0122\u0003 \u0010\u0000\u0119\u011a\u0005\u000e"+
		"\u0000\u0000\u011a\u011b\u0005\r\u0000\u0000\u011b\u011c\u00051\u0000"+
		"\u0000\u011c\u011d\u0003.\u0017\u0000\u011d\u011e\u00052\u0000\u0000\u011e"+
		"\u011f\u0003 \u0010\u0000\u011f\u0121\u0001\u0000\u0000\u0000\u0120\u0119"+
		"\u0001\u0000\u0000\u0000\u0121\u0124\u0001\u0000\u0000\u0000\u0122\u0120"+
		"\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0127"+
		"\u0001\u0000\u0000\u0000\u0124\u0122\u0001\u0000\u0000\u0000\u0125\u0126"+
		"\u0005\u000e\u0000\u0000\u0126\u0128\u0003 \u0010\u0000\u0127\u0125\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u001f\u0001"+
		"\u0000\u0000\u0000\u0129\u012d\u0005/\u0000\u0000\u012a\u012c\u0003\u0012"+
		"\t\u0000\u012b\u012a\u0001\u0000\u0000\u0000\u012c\u012f\u0001\u0000\u0000"+
		"\u0000\u012d\u012b\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000"+
		"\u0000\u012e\u0130\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000"+
		"\u0000\u0130\u0133\u00050\u0000\u0000\u0131\u0133\u0003\u0012\t\u0000"+
		"\u0132\u0129\u0001\u0000\u0000\u0000\u0132\u0131\u0001\u0000\u0000\u0000"+
		"\u0133!\u0001\u0000\u0000\u0000\u0134\u0135\u0005\u000f\u0000\u0000\u0135"+
		"\u0136\u00051\u0000\u0000\u0136\u0137\u0003.\u0017\u0000\u0137\u0138\u0005"+
		"2\u0000\u0000\u0138\u013c\u0005/\u0000\u0000\u0139\u013b\u0003$\u0012"+
		"\u0000\u013a\u0139\u0001\u0000\u0000\u0000\u013b\u013e\u0001\u0000\u0000"+
		"\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000"+
		"\u0000\u013d\u0140\u0001\u0000\u0000\u0000\u013e\u013c\u0001\u0000\u0000"+
		"\u0000\u013f\u0141\u0003&\u0013\u0000\u0140\u013f\u0001\u0000\u0000\u0000"+
		"\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000"+
		"\u0142\u0143\u00050\u0000\u0000\u0143#\u0001\u0000\u0000\u0000\u0144\u0145"+
		"\u0005\u0010\u0000\u0000\u0145\u0146\u00030\u0018\u0000\u0146\u014a\u0005"+
		"4\u0000\u0000\u0147\u0149\u0003\u0012\t\u0000\u0148\u0147\u0001\u0000"+
		"\u0000\u0000\u0149\u014c\u0001\u0000\u0000\u0000\u014a\u0148\u0001\u0000"+
		"\u0000\u0000\u014a\u014b\u0001\u0000\u0000\u0000\u014b%\u0001\u0000\u0000"+
		"\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014d\u014e\u0005\u0011\u0000"+
		"\u0000\u014e\u0152\u00054\u0000\u0000\u014f\u0151\u0003\u0012\t\u0000"+
		"\u0150\u014f\u0001\u0000\u0000\u0000\u0151\u0154\u0001\u0000\u0000\u0000"+
		"\u0152\u0150\u0001\u0000\u0000\u0000\u0152\u0153\u0001\u0000\u0000\u0000"+
		"\u0153\'\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0155"+
		"\u0156\u0005\u0012\u0000\u0000\u0156\u0158\u00051\u0000\u0000\u0157\u0159"+
		"\u0003*\u0015\u0000\u0158\u0157\u0001\u0000\u0000\u0000\u0158\u0159\u0001"+
		"\u0000\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015c\u0005"+
		"3\u0000\u0000\u015b\u015d\u0003.\u0017\u0000\u015c\u015b\u0001\u0000\u0000"+
		"\u0000\u015c\u015d\u0001\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000"+
		"\u0000\u015e\u0160\u00053\u0000\u0000\u015f\u0161\u0003,\u0016\u0000\u0160"+
		"\u015f\u0001\u0000\u0000\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0161"+
		"\u0162\u0001\u0000\u0000\u0000\u0162\u0163\u00052\u0000\u0000\u0163\u0173"+
		"\u0003 \u0010\u0000\u0164\u0165\u0005\u0013\u0000\u0000\u0165\u0166\u0005"+
		"1\u0000\u0000\u0166\u0167\u0003.\u0017\u0000\u0167\u0168\u00052\u0000"+
		"\u0000\u0168\u0169\u0003 \u0010\u0000\u0169\u0173\u0001\u0000\u0000\u0000"+
		"\u016a\u016b\u0005\u0014\u0000\u0000\u016b\u016c\u0003 \u0010\u0000\u016c"+
		"\u016d\u0005\u0013\u0000\u0000\u016d\u016e\u00051\u0000\u0000\u016e\u016f"+
		"\u0003.\u0017\u0000\u016f\u0170\u00052\u0000\u0000\u0170\u0171\u00053"+
		"\u0000\u0000\u0171\u0173\u0001\u0000\u0000\u0000\u0172\u0155\u0001\u0000"+
		"\u0000\u0000\u0172\u0164\u0001\u0000\u0000\u0000\u0172\u016a\u0001\u0000"+
		"\u0000\u0000\u0173)\u0001\u0000\u0000\u0000\u0174\u0175\u0003\u0006\u0003"+
		"\u0000\u0175\u0176\u0005;\u0000\u0000\u0176\u0177\u0005&\u0000\u0000\u0177"+
		"\u0178\u0003.\u0017\u0000\u0178\u017e\u0001\u0000\u0000\u0000\u0179\u017a"+
		"\u0003\u001c\u000e\u0000\u017a\u017b\u0005&\u0000\u0000\u017b\u017c\u0003"+
		".\u0017\u0000\u017c\u017e\u0001\u0000\u0000\u0000\u017d\u0174\u0001\u0000"+
		"\u0000\u0000\u017d\u0179\u0001\u0000\u0000\u0000\u017e+\u0001\u0000\u0000"+
		"\u0000\u017f\u0185\u0003.\u0017\u0000\u0180\u0181\u0003\u001c\u000e\u0000"+
		"\u0181\u0182\u0005&\u0000\u0000\u0182\u0183\u0003.\u0017\u0000\u0183\u0185"+
		"\u0001\u0000\u0000\u0000\u0184\u017f\u0001\u0000\u0000\u0000\u0184\u0180"+
		"\u0001\u0000\u0000\u0000\u0185-\u0001\u0000\u0000\u0000\u0186\u0187\u0006"+
		"\u0017\uffff\uffff\u0000\u0187\u0188\u00051\u0000\u0000\u0188\u0189\u0003"+
		".\u0017\u0000\u0189\u018a\u00052\u0000\u0000\u018a\u01ae\u0001\u0000\u0000"+
		"\u0000\u018b\u018c\u0005\u0004\u0000\u0000\u018c\u018d\u0005;\u0000\u0000"+
		"\u018d\u018f\u00051\u0000\u0000\u018e\u0190\u00032\u0019\u0000\u018f\u018e"+
		"\u0001\u0000\u0000\u0000\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0191"+
		"\u0001\u0000\u0000\u0000\u0191\u01ae\u00052\u0000\u0000\u0192\u0193\u0005"+
		"\u0004\u0000\u0000\u0193\u0198\u0003\u0006\u0003\u0000\u0194\u0195\u0005"+
		"-\u0000\u0000\u0195\u0196\u0003.\u0017\u0000\u0196\u0197\u0005.\u0000"+
		"\u0000\u0197\u0199\u0001\u0000\u0000\u0000\u0198\u0194\u0001\u0000\u0000"+
		"\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a\u0198\u0001\u0000\u0000"+
		"\u0000\u019a\u019b\u0001\u0000\u0000\u0000\u019b\u01ae\u0001\u0000\u0000"+
		"\u0000\u019c\u019d\u0005;\u0000\u0000\u019d\u019f\u00051\u0000\u0000\u019e"+
		"\u01a0\u00032\u0019\u0000\u019f\u019e\u0001\u0000\u0000\u0000\u019f\u01a0"+
		"\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1\u01ae"+
		"\u00052\u0000\u0000\u01a2\u01a3\u0003\u001c\u000e\u0000\u01a3\u01a4\u0005"+
		"\u0018\u0000\u0000\u01a4\u01ae\u0001\u0000\u0000\u0000\u01a5\u01a6\u0003"+
		"\u001c\u000e\u0000\u01a6\u01a7\u0005\u0019\u0000\u0000\u01a7\u01ae\u0001"+
		"\u0000\u0000\u0000\u01a8\u01a9\u0005)\u0000\u0000\u01a9\u01ae\u0003.\u0017"+
		"\t\u01aa\u01ab\u0005\"\u0000\u0000\u01ab\u01ae\u0003.\u0017\b\u01ac\u01ae"+
		"\u00030\u0018\u0000\u01ad\u0186\u0001\u0000\u0000\u0000\u01ad\u018b\u0001"+
		"\u0000\u0000\u0000\u01ad\u0192\u0001\u0000\u0000\u0000\u01ad\u019c\u0001"+
		"\u0000\u0000\u0000\u01ad\u01a2\u0001\u0000\u0000\u0000\u01ad\u01a5\u0001"+
		"\u0000\u0000\u0000\u01ad\u01a8\u0001\u0000\u0000\u0000\u01ad\u01aa\u0001"+
		"\u0000\u0000\u0000\u01ad\u01ac\u0001\u0000\u0000\u0000\u01ae\u01d6\u0001"+
		"\u0000\u0000\u0000\u01af\u01b0\n\u0007\u0000\u0000\u01b0\u01b1\u0007\u0002"+
		"\u0000\u0000\u01b1\u01d5\u0003.\u0017\b\u01b2\u01b3\n\u0006\u0000\u0000"+
		"\u01b3\u01b4\u0007\u0003\u0000\u0000\u01b4\u01d5\u0003.\u0017\u0007\u01b5"+
		"\u01b6\n\u0005\u0000\u0000\u01b6\u01b7\u0007\u0004\u0000\u0000\u01b7\u01d5"+
		"\u0003.\u0017\u0006\u01b8\u01b9\n\u0004\u0000\u0000\u01b9\u01ba\u0005"+
		" \u0000\u0000\u01ba\u01d5\u0003.\u0017\u0005\u01bb\u01bc\n\u0003\u0000"+
		"\u0000\u01bc\u01bd\u0005!\u0000\u0000\u01bd\u01d5\u0003.\u0017\u0004\u01be"+
		"\u01bf\n\u0002\u0000\u0000\u01bf\u01c0\u0005\'\u0000\u0000\u01c0\u01c1"+
		"\u0003.\u0017\u0000\u01c1\u01c2\u00054\u0000\u0000\u01c2\u01c3\u0003."+
		"\u0017\u0003\u01c3\u01d5\u0001\u0000\u0000\u0000\u01c4\u01c5\n\u000e\u0000"+
		"\u0000\u01c5\u01c6\u00056\u0000\u0000\u01c6\u01c7\u0005;\u0000\u0000\u01c7"+
		"\u01c9\u00051\u0000\u0000\u01c8\u01ca\u00032\u0019\u0000\u01c9\u01c8\u0001"+
		"\u0000\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb\u0001"+
		"\u0000\u0000\u0000\u01cb\u01d5\u00052\u0000\u0000\u01cc\u01cd\n\r\u0000"+
		"\u0000\u01cd\u01ce\u0005-\u0000\u0000\u01ce\u01cf\u0003.\u0017\u0000\u01cf"+
		"\u01d0\u0005.\u0000\u0000\u01d0\u01d5\u0001\u0000\u0000\u0000\u01d1\u01d2"+
		"\n\f\u0000\u0000\u01d2\u01d3\u00056\u0000\u0000\u01d3\u01d5\u0005;\u0000"+
		"\u0000\u01d4\u01af\u0001\u0000\u0000\u0000\u01d4\u01b2\u0001\u0000\u0000"+
		"\u0000\u01d4\u01b5\u0001\u0000\u0000\u0000\u01d4\u01b8\u0001\u0000\u0000"+
		"\u0000\u01d4\u01bb\u0001\u0000\u0000\u0000\u01d4\u01be\u0001\u0000\u0000"+
		"\u0000\u01d4\u01c4\u0001\u0000\u0000\u0000\u01d4\u01cc\u0001\u0000\u0000"+
		"\u0000\u01d4\u01d1\u0001\u0000\u0000\u0000\u01d5\u01d8\u0001\u0000\u0000"+
		"\u0000\u01d6\u01d4\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000\u0000"+
		"\u0000\u01d7/\u0001\u0000\u0000\u0000\u01d8\u01d6\u0001\u0000\u0000\u0000"+
		"\u01d9\u01da\u0007\u0005\u0000\u0000\u01da1\u0001\u0000\u0000\u0000\u01db"+
		"\u01e0\u0003.\u0017\u0000\u01dc\u01dd\u00055\u0000\u0000\u01dd\u01df\u0003"+
		".\u0017\u0000\u01de\u01dc\u0001\u0000\u0000\u0000\u01df\u01e2\u0001\u0000"+
		"\u0000\u0000\u01e0\u01de\u0001\u0000\u0000\u0000\u01e0\u01e1\u0001\u0000"+
		"\u0000\u0000\u01e13\u0001\u0000\u0000\u0000\u01e2\u01e0\u0001\u0000\u0000"+
		"\u00002>FKRY^dku|\u0085\u008c\u0091\u0098\u00a3\u00a7\u00b0\u00ba\u00c1"+
		"\u00c7\u00d0\u00de\u00e7\u00ee\u00f6\u0102\u010f\u0111\u0122\u0127\u012d"+
		"\u0132\u013c\u0140\u014a\u0152\u0158\u015c\u0160\u0172\u017d\u0184\u018f"+
		"\u019a\u019f\u01ad\u01c9\u01d4\u01d6\u01e0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}