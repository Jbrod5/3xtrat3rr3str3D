// Generated from org/jrg/antlrBase/yLenguaje/YLenguaje.g4 by ANTLR 4.13.2
package org.jrg.antlrBase.yLenguaje;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class YLenguajeParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ESTRUCTURAS_TAG=1, FUNCIONES_TAG=2, TIPO_ENTERO=3, TIPO_CADENA=4, TIPO_FLOTANTE=5, 
		TIPO_CARACTER=6, TIPO_BOOLEANO=7, VERDADERO=8, FALSO=9, ESTRUCTURA=10, 
		DEFINIR=11, RETORNAR=12, SI=13, ENTONCES=14, SINO=15, CONTRARIO=16, ELEGIR=17, 
		CASO=18, SIEMPRE=19, ROMPER=20, CONTINUAR=21, PARA=22, MIENTRAS=23, HACER=24, 
		FLECHA=25, INCREMENTO=26, DECREMENTO=27, IGUAL_QUE=28, DIFERENTE_QUE=29, 
		MAYOR_QUE=30, MENOR_QUE=31, AND=32, OR=33, NEGACION=34, ASIGNACION=35, 
		SUMA=36, RESTA=37, MULT=38, DIV=39, CORCHETE_IZQ=40, CORCHETE_DER=41, 
		LLAVE_IZQ=42, LLAVE_DER=43, PAR_IZQ=44, PAR_DER=45, PUNTO_Y_COMA=46, DOS_PUNTOS=47, 
		COMA=48, PUNTO=49, CADENA=50, CARACTER=51, NUMERO_DECIMAL=52, NUMERO_ENTERO=53, 
		IDENTIFICADOR=54, COMENTARIO_LINEA=55, NEWLINE=56, ESPACIOS_BLANCO=57, 
		INDENT=58, DEDENT=59;
	public static final int
		RULE_programa = 0, RULE_seccion_estructuras = 1, RULE_seccion_funciones = 2, 
		RULE_tipo_dato = 3, RULE_definicion_struct = 4, RULE_atributo_struct = 5, 
		RULE_definicion_funcion = 6, RULE_parametros = 7, RULE_parametro = 8, 
		RULE_cuerpo_funcion = 9, RULE_instruccion = 10, RULE_terminador = 11, 
		RULE_retorno = 12, RULE_declaracion_variable = 13, RULE_asignacion = 14, 
		RULE_variable_asignable = 15, RULE_condicional = 16, RULE_bloque = 17, 
		RULE_seleccion = 18, RULE_caso_seleccion = 19, RULE_caso_defecto = 20, 
		RULE_ciclo = 21, RULE_init_para = 22, RULE_paso_para = 23, RULE_expresion = 24, 
		RULE_valor_primitivo = 25, RULE_lista_expresiones = 26;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "seccion_estructuras", "seccion_funciones", "tipo_dato", 
			"definicion_struct", "atributo_struct", "definicion_funcion", "parametros", 
			"parametro", "cuerpo_funcion", "instruccion", "terminador", "retorno", 
			"declaracion_variable", "asignacion", "variable_asignable", "condicional", 
			"bloque", "seleccion", "caso_seleccion", "caso_defecto", "ciclo", "init_para", 
			"paso_para", "expresion", "valor_primitivo", "lista_expresiones"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'%estructuras'", "'%funciones'", "'entero'", "'cadena'", "'flotante'", 
			"'caracter'", "'booleano'", "'verdadero'", "'falso'", "'estructura'", 
			"'definir'", "'retornar'", "'si'", "'entonces'", "'sino'", "'contrario'", 
			"'elegir'", "'caso'", "'siempre'", "'romper'", "'continuar'", "'para'", 
			"'mientras'", "'hacer'", "'->'", "'++'", "'--'", "'=='", "'!='", "'>'", 
			"'<'", "'&&'", "'||'", "'!'", "'='", "'+'", "'-'", "'*'", "'/'", "'['", 
			"']'", "'{'", "'}'", "'('", "')'", "';'", "':'", "','", "'.'", null, 
			null, null, null, null, null, null, null, "'INDENT'", "'DEDENT'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ESTRUCTURAS_TAG", "FUNCIONES_TAG", "TIPO_ENTERO", "TIPO_CADENA", 
			"TIPO_FLOTANTE", "TIPO_CARACTER", "TIPO_BOOLEANO", "VERDADERO", "FALSO", 
			"ESTRUCTURA", "DEFINIR", "RETORNAR", "SI", "ENTONCES", "SINO", "CONTRARIO", 
			"ELEGIR", "CASO", "SIEMPRE", "ROMPER", "CONTINUAR", "PARA", "MIENTRAS", 
			"HACER", "FLECHA", "INCREMENTO", "DECREMENTO", "IGUAL_QUE", "DIFERENTE_QUE", 
			"MAYOR_QUE", "MENOR_QUE", "AND", "OR", "NEGACION", "ASIGNACION", "SUMA", 
			"RESTA", "MULT", "DIV", "CORCHETE_IZQ", "CORCHETE_DER", "LLAVE_IZQ", 
			"LLAVE_DER", "PAR_IZQ", "PAR_DER", "PUNTO_Y_COMA", "DOS_PUNTOS", "COMA", 
			"PUNTO", "CADENA", "CARACTER", "NUMERO_DECIMAL", "NUMERO_ENTERO", "IDENTIFICADOR", 
			"COMENTARIO_LINEA", "NEWLINE", "ESPACIOS_BLANCO", "INDENT", "DEDENT"
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
	public String getGrammarFileName() { return "YLenguaje.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public YLenguajeParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public Seccion_funcionesContext seccion_funciones() {
			return getRuleContext(Seccion_funcionesContext.class,0);
		}
		public TerminalNode EOF() { return getToken(YLenguajeParser.EOF, 0); }
		public Seccion_estructurasContext seccion_estructuras() {
			return getRuleContext(Seccion_estructurasContext.class,0);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitPrograma(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitPrograma(this);
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
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ESTRUCTURAS_TAG) {
				{
				setState(54);
				seccion_estructuras();
				}
			}

			setState(57);
			seccion_funciones();
			setState(58);
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
	public static class Seccion_estructurasContext extends ParserRuleContext {
		public TerminalNode ESTRUCTURAS_TAG() { return getToken(YLenguajeParser.ESTRUCTURAS_TAG, 0); }
		public List<Definicion_structContext> definicion_struct() {
			return getRuleContexts(Definicion_structContext.class);
		}
		public Definicion_structContext definicion_struct(int i) {
			return getRuleContext(Definicion_structContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(YLenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(YLenguajeParser.NEWLINE, i);
		}
		public Seccion_estructurasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccion_estructuras; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterSeccion_estructuras(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitSeccion_estructuras(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitSeccion_estructuras(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Seccion_estructurasContext seccion_estructuras() throws RecognitionException {
		Seccion_estructurasContext _localctx = new Seccion_estructurasContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_seccion_estructuras);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(ESTRUCTURAS_TAG);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ESTRUCTURA || _la==NEWLINE) {
				{
				setState(63);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ESTRUCTURA:
					{
					setState(61);
					definicion_struct();
					}
					break;
				case NEWLINE:
					{
					setState(62);
					match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(67);
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
	public static class Seccion_funcionesContext extends ParserRuleContext {
		public TerminalNode FUNCIONES_TAG() { return getToken(YLenguajeParser.FUNCIONES_TAG, 0); }
		public List<Definicion_funcionContext> definicion_funcion() {
			return getRuleContexts(Definicion_funcionContext.class);
		}
		public Definicion_funcionContext definicion_funcion(int i) {
			return getRuleContext(Definicion_funcionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(YLenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(YLenguajeParser.NEWLINE, i);
		}
		public Seccion_funcionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_seccion_funciones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterSeccion_funciones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitSeccion_funciones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitSeccion_funciones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Seccion_funcionesContext seccion_funciones() throws RecognitionException {
		Seccion_funcionesContext _localctx = new Seccion_funcionesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_seccion_funciones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(FUNCIONES_TAG);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DEFINIR || _la==NEWLINE) {
				{
				setState(71);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DEFINIR:
					{
					setState(69);
					definicion_funcion();
					}
					break;
				case NEWLINE:
					{
					setState(70);
					match(NEWLINE);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(75);
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
	public static class Tipo_datoContext extends ParserRuleContext {
		public TerminalNode TIPO_ENTERO() { return getToken(YLenguajeParser.TIPO_ENTERO, 0); }
		public TerminalNode TIPO_CADENA() { return getToken(YLenguajeParser.TIPO_CADENA, 0); }
		public TerminalNode TIPO_FLOTANTE() { return getToken(YLenguajeParser.TIPO_FLOTANTE, 0); }
		public TerminalNode TIPO_CARACTER() { return getToken(YLenguajeParser.TIPO_CARACTER, 0); }
		public TerminalNode TIPO_BOOLEANO() { return getToken(YLenguajeParser.TIPO_BOOLEANO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public Tipo_datoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo_dato; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterTipo_dato(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitTipo_dato(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitTipo_dato(this);
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
			setState(76);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 18014398509482232L) != 0)) ) {
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
	public static class Definicion_structContext extends ParserRuleContext {
		public Definicion_structContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definicion_struct; }
	 
		public Definicion_structContext() { }
		public void copyFrom(Definicion_structContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DefEstructuraContext extends Definicion_structContext {
		public TerminalNode ESTRUCTURA() { return getToken(YLenguajeParser.ESTRUCTURA, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public TerminalNode NEWLINE() { return getToken(YLenguajeParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(YLenguajeParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(YLenguajeParser.DEDENT, 0); }
		public List<Atributo_structContext> atributo_struct() {
			return getRuleContexts(Atributo_structContext.class);
		}
		public Atributo_structContext atributo_struct(int i) {
			return getRuleContext(Atributo_structContext.class,i);
		}
		public DefEstructuraContext(Definicion_structContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterDefEstructura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitDefEstructura(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitDefEstructura(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Definicion_structContext definicion_struct() throws RecognitionException {
		Definicion_structContext _localctx = new Definicion_structContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_definicion_struct);
		int _la;
		try {
			_localctx = new DefEstructuraContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(ESTRUCTURA);
			setState(79);
			match(IDENTIFICADOR);
			setState(80);
			match(DOS_PUNTOS);
			setState(81);
			match(NEWLINE);
			setState(82);
			match(INDENT);
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				atributo_struct();
				}
				}
				setState(86); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 18014398509482232L) != 0) );
			setState(88);
			match(DEDENT);
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
	public static class Atributo_structContext extends ParserRuleContext {
		public Atributo_structContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributo_struct; }
	 
		public Atributo_structContext() { }
		public void copyFrom(Atributo_structContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtributoSimpleContext extends Atributo_structContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode NEWLINE() { return getToken(YLenguajeParser.NEWLINE, 0); }
		public AtributoSimpleContext(Atributo_structContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterAtributoSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitAtributoSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitAtributoSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AtributoArrayContext extends Atributo_structContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode CORCHETE_IZQ() { return getToken(YLenguajeParser.CORCHETE_IZQ, 0); }
		public TerminalNode NUMERO_ENTERO() { return getToken(YLenguajeParser.NUMERO_ENTERO, 0); }
		public TerminalNode CORCHETE_DER() { return getToken(YLenguajeParser.CORCHETE_DER, 0); }
		public TerminalNode NEWLINE() { return getToken(YLenguajeParser.NEWLINE, 0); }
		public AtributoArrayContext(Atributo_structContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterAtributoArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitAtributoArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitAtributoArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atributo_structContext atributo_struct() throws RecognitionException {
		Atributo_structContext _localctx = new Atributo_structContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_atributo_struct);
		try {
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new AtributoSimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				tipo_dato();
				setState(91);
				match(IDENTIFICADOR);
				setState(92);
				match(NEWLINE);
				}
				break;
			case 2:
				_localctx = new AtributoArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(94);
				tipo_dato();
				setState(95);
				match(IDENTIFICADOR);
				setState(96);
				match(CORCHETE_IZQ);
				setState(97);
				match(NUMERO_ENTERO);
				setState(98);
				match(CORCHETE_DER);
				setState(99);
				match(NEWLINE);
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
	public static class Definicion_funcionContext extends ParserRuleContext {
		public Definicion_funcionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definicion_funcion; }
	 
		public Definicion_funcionContext() { }
		public void copyFrom(Definicion_funcionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DefFuncionSinRetornoContext extends Definicion_funcionContext {
		public TerminalNode DEFINIR() { return getToken(YLenguajeParser.DEFINIR, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public Cuerpo_funcionContext cuerpo_funcion() {
			return getRuleContext(Cuerpo_funcionContext.class,0);
		}
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public DefFuncionSinRetornoContext(Definicion_funcionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterDefFuncionSinRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitDefFuncionSinRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitDefFuncionSinRetorno(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DefFuncionConRetornoContext extends Definicion_funcionContext {
		public TerminalNode DEFINIR() { return getToken(YLenguajeParser.DEFINIR, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public TerminalNode FLECHA() { return getToken(YLenguajeParser.FLECHA, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public Cuerpo_funcionContext cuerpo_funcion() {
			return getRuleContext(Cuerpo_funcionContext.class,0);
		}
		public ParametrosContext parametros() {
			return getRuleContext(ParametrosContext.class,0);
		}
		public DefFuncionConRetornoContext(Definicion_funcionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterDefFuncionConRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitDefFuncionConRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitDefFuncionConRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Definicion_funcionContext definicion_funcion() throws RecognitionException {
		Definicion_funcionContext _localctx = new Definicion_funcionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_definicion_funcion);
		int _la;
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				_localctx = new DefFuncionSinRetornoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(103);
				match(DEFINIR);
				setState(104);
				match(IDENTIFICADOR);
				setState(105);
				match(PAR_IZQ);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18019896067621112L) != 0)) {
					{
					setState(106);
					parametros();
					}
				}

				setState(109);
				match(PAR_DER);
				setState(110);
				match(DOS_PUNTOS);
				setState(111);
				cuerpo_funcion();
				}
				break;
			case 2:
				_localctx = new DefFuncionConRetornoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(DEFINIR);
				setState(113);
				match(IDENTIFICADOR);
				setState(114);
				match(PAR_IZQ);
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18019896067621112L) != 0)) {
					{
					setState(115);
					parametros();
					}
				}

				setState(118);
				match(PAR_DER);
				setState(119);
				match(FLECHA);
				setState(120);
				tipo_dato();
				setState(121);
				match(DOS_PUNTOS);
				setState(122);
				cuerpo_funcion();
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
		public List<TerminalNode> COMA() { return getTokens(YLenguajeParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(YLenguajeParser.COMA, i);
		}
		public ParametrosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parametros; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterParametros(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitParametros(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitParametros(this);
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
			setState(126);
			parametro();
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(127);
				match(COMA);
				setState(128);
				parametro();
				}
				}
				setState(133);
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
		public TerminalNode CORCHETE_IZQ() { return getToken(YLenguajeParser.CORCHETE_IZQ, 0); }
		public TerminalNode CORCHETE_DER() { return getToken(YLenguajeParser.CORCHETE_DER, 0); }
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public ParamArrayContext(ParametroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterParamArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitParamArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitParamArray(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParamSimpleContext extends ParametroContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public ParamSimpleContext(ParametroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterParamSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitParamSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitParamSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParamEstructuraContext extends ParametroContext {
		public TerminalNode LLAVE_IZQ() { return getToken(YLenguajeParser.LLAVE_IZQ, 0); }
		public TerminalNode LLAVE_DER() { return getToken(YLenguajeParser.LLAVE_DER, 0); }
		public List<TerminalNode> IDENTIFICADOR() { return getTokens(YLenguajeParser.IDENTIFICADOR); }
		public TerminalNode IDENTIFICADOR(int i) {
			return getToken(YLenguajeParser.IDENTIFICADOR, i);
		}
		public ParamEstructuraContext(ParametroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterParamEstructura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitParamEstructura(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitParamEstructura(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParametroContext parametro() throws RecognitionException {
		ParametroContext _localctx = new ParametroContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_parametro);
		try {
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TIPO_ENTERO:
			case TIPO_CADENA:
			case TIPO_FLOTANTE:
			case TIPO_CARACTER:
			case TIPO_BOOLEANO:
			case IDENTIFICADOR:
				_localctx = new ParamSimpleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				tipo_dato();
				setState(135);
				match(IDENTIFICADOR);
				}
				break;
			case CORCHETE_IZQ:
				_localctx = new ParamArrayContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(CORCHETE_IZQ);
				setState(138);
				match(CORCHETE_DER);
				setState(139);
				tipo_dato();
				setState(140);
				match(IDENTIFICADOR);
				}
				break;
			case LLAVE_IZQ:
				_localctx = new ParamEstructuraContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(142);
				match(LLAVE_IZQ);
				setState(143);
				match(LLAVE_DER);
				setState(144);
				match(IDENTIFICADOR);
				setState(145);
				match(IDENTIFICADOR);
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
	public static class Cuerpo_funcionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(YLenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(YLenguajeParser.NEWLINE, i);
		}
		public TerminalNode INDENT() { return getToken(YLenguajeParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(YLenguajeParser.DEDENT, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Cuerpo_funcionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cuerpo_funcion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterCuerpo_funcion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitCuerpo_funcion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitCuerpo_funcion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cuerpo_funcionContext cuerpo_funcion() throws RecognitionException {
		Cuerpo_funcionContext _localctx = new Cuerpo_funcionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cuerpo_funcion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(NEWLINE);
			setState(149);
			match(INDENT);
			setState(151); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(150);
				instruccion();
				}
				}
				setState(153); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 34925041996150776L) != 0) );
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEWLINE) {
				{
				setState(155);
				match(NEWLINE);
				}
			}

			setState(158);
			match(DEDENT);
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
		public TerminadorContext terminador() {
			return getRuleContext(TerminadorContext.class,0);
		}
		public StmtAsignacionContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtAsignacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtAsignacion(this);
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
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtSeleccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtSeleccion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtSeleccion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtDeclaracionContext extends InstruccionContext {
		public Declaracion_variableContext declaracion_variable() {
			return getRuleContext(Declaracion_variableContext.class,0);
		}
		public TerminadorContext terminador() {
			return getRuleContext(TerminadorContext.class,0);
		}
		public StmtDeclaracionContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtDeclaracion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtDeclaracion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtDeclaracion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtRetornoContext extends InstruccionContext {
		public RetornoContext retorno() {
			return getRuleContext(RetornoContext.class,0);
		}
		public TerminadorContext terminador() {
			return getRuleContext(TerminadorContext.class,0);
		}
		public StmtRetornoContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtRetorno(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtExpresionContext extends InstruccionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminadorContext terminador() {
			return getRuleContext(TerminadorContext.class,0);
		}
		public StmtExpresionContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtExpresion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtExpresion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtExpresion(this);
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
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtCiclo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtCiclo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtCiclo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtRomperContext extends InstruccionContext {
		public TerminalNode ROMPER() { return getToken(YLenguajeParser.ROMPER, 0); }
		public TerminadorContext terminador() {
			return getRuleContext(TerminadorContext.class,0);
		}
		public StmtRomperContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtRomper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtRomper(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtRomper(this);
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
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtCondicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtCondicional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtCondicional(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtEstructuraLocalContext extends InstruccionContext {
		public Definicion_structContext definicion_struct() {
			return getRuleContext(Definicion_structContext.class,0);
		}
		public StmtEstructuraLocalContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtEstructuraLocal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtEstructuraLocal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtEstructuraLocal(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StmtContinuarContext extends InstruccionContext {
		public TerminalNode CONTINUAR() { return getToken(YLenguajeParser.CONTINUAR, 0); }
		public TerminadorContext terminador() {
			return getRuleContext(TerminadorContext.class,0);
		}
		public StmtContinuarContext(InstruccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStmtContinuar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStmtContinuar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStmtContinuar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_instruccion);
		try {
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new StmtDeclaracionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				declaracion_variable();
				setState(161);
				terminador();
				}
				break;
			case 2:
				_localctx = new StmtAsignacionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				asignacion();
				setState(164);
				terminador();
				}
				break;
			case 3:
				_localctx = new StmtEstructuraLocalContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				definicion_struct();
				}
				break;
			case 4:
				_localctx = new StmtCondicionalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(167);
				condicional();
				}
				break;
			case 5:
				_localctx = new StmtSeleccionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(168);
				seleccion();
				}
				break;
			case 6:
				_localctx = new StmtCicloContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(169);
				ciclo();
				}
				break;
			case 7:
				_localctx = new StmtRetornoContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(170);
				retorno();
				setState(171);
				terminador();
				}
				break;
			case 8:
				_localctx = new StmtContinuarContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(173);
				match(CONTINUAR);
				setState(174);
				terminador();
				}
				break;
			case 9:
				_localctx = new StmtRomperContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(175);
				match(ROMPER);
				setState(176);
				terminador();
				}
				break;
			case 10:
				_localctx = new StmtExpresionContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(177);
				expresion(0);
				setState(178);
				terminador();
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
	public static class TerminadorContext extends ParserRuleContext {
		public TerminalNode NEWLINE() { return getToken(YLenguajeParser.NEWLINE, 0); }
		public TerminalNode PUNTO_Y_COMA() { return getToken(YLenguajeParser.PUNTO_Y_COMA, 0); }
		public TerminadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_terminador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterTerminador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitTerminador(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitTerminador(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TerminadorContext terminador() throws RecognitionException {
		TerminadorContext _localctx = new TerminadorContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_terminador);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PUNTO_Y_COMA) {
				{
				setState(182);
				match(PUNTO_Y_COMA);
				}
			}

			setState(185);
			match(NEWLINE);
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
	public static class RetornoContext extends ParserRuleContext {
		public TerminalNode RETORNAR() { return getToken(YLenguajeParser.RETORNAR, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public RetornoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_retorno; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterRetorno(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitRetorno(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitRetorno(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetornoContext retorno() throws RecognitionException {
		RetornoContext _localctx = new RetornoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_retorno);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			match(RETORNAR);
			setState(188);
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
	public static class DeclArraySinValoresContext extends Declaracion_variableContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode CORCHETE_IZQ() { return getToken(YLenguajeParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(YLenguajeParser.CORCHETE_DER, 0); }
		public DeclArraySinValoresContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterDeclArraySinValores(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitDeclArraySinValores(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitDeclArraySinValores(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclConTipoYValorContext extends Declaracion_variableContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode ASIGNACION() { return getToken(YLenguajeParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public DeclConTipoYValorContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterDeclConTipoYValor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitDeclConTipoYValor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitDeclConTipoYValor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclArrayConValoresContext extends Declaracion_variableContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode CORCHETE_IZQ() { return getToken(YLenguajeParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(YLenguajeParser.CORCHETE_DER, 0); }
		public TerminalNode ASIGNACION() { return getToken(YLenguajeParser.ASIGNACION, 0); }
		public TerminalNode LLAVE_IZQ() { return getToken(YLenguajeParser.LLAVE_IZQ, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(YLenguajeParser.LLAVE_DER, 0); }
		public DeclArrayConValoresContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterDeclArrayConValores(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitDeclArrayConValores(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitDeclArrayConValores(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DeclMatrizContext extends Declaracion_variableContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public List<TerminalNode> CORCHETE_IZQ() { return getTokens(YLenguajeParser.CORCHETE_IZQ); }
		public TerminalNode CORCHETE_IZQ(int i) {
			return getToken(YLenguajeParser.CORCHETE_IZQ, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> CORCHETE_DER() { return getTokens(YLenguajeParser.CORCHETE_DER); }
		public TerminalNode CORCHETE_DER(int i) {
			return getToken(YLenguajeParser.CORCHETE_DER, i);
		}
		public DeclMatrizContext(Declaracion_variableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterDeclMatriz(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitDeclMatriz(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitDeclMatriz(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declaracion_variableContext declaracion_variable() throws RecognitionException {
		Declaracion_variableContext _localctx = new Declaracion_variableContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_declaracion_variable);
		int _la;
		try {
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new DeclConTipoYValorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(190);
				tipo_dato();
				setState(191);
				match(IDENTIFICADOR);
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASIGNACION) {
					{
					setState(192);
					match(ASIGNACION);
					setState(193);
					expresion(0);
					}
				}

				}
				break;
			case 2:
				_localctx = new DeclArraySinValoresContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				tipo_dato();
				setState(197);
				match(IDENTIFICADOR);
				setState(198);
				match(CORCHETE_IZQ);
				setState(199);
				expresion(0);
				setState(200);
				match(CORCHETE_DER);
				}
				break;
			case 3:
				_localctx = new DeclArrayConValoresContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(202);
				tipo_dato();
				setState(203);
				match(IDENTIFICADOR);
				setState(204);
				match(CORCHETE_IZQ);
				setState(205);
				expresion(0);
				setState(206);
				match(CORCHETE_DER);
				setState(207);
				match(ASIGNACION);
				setState(208);
				match(LLAVE_IZQ);
				setState(209);
				lista_expresiones();
				setState(210);
				match(LLAVE_DER);
				}
				break;
			case 4:
				_localctx = new DeclMatrizContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(212);
				tipo_dato();
				setState(213);
				match(IDENTIFICADOR);
				setState(214);
				match(CORCHETE_IZQ);
				setState(215);
				expresion(0);
				setState(216);
				match(CORCHETE_DER);
				setState(217);
				match(CORCHETE_IZQ);
				setState(218);
				expresion(0);
				setState(219);
				match(CORCHETE_DER);
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
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(YLenguajeParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitAsignacion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitAsignacion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_asignacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			variable_asignable(0);
			setState(224);
			match(ASIGNACION);
			setState(225);
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
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public VarSimpleContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterVarSimple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitVarSimple(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitVarSimple(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarMiembroContext extends Variable_asignableContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(YLenguajeParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public VarMiembroContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterVarMiembro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitVarMiembro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitVarMiembro(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarArrayContext extends Variable_asignableContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode CORCHETE_IZQ() { return getToken(YLenguajeParser.CORCHETE_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode CORCHETE_DER() { return getToken(YLenguajeParser.CORCHETE_DER, 0); }
		public VarArrayContext(Variable_asignableContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterVarArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitVarArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitVarArray(this);
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
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_variable_asignable, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new VarSimpleContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(228);
			match(IDENTIFICADOR);
			}
			_ctx.stop = _input.LT(-1);
			setState(240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(238);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						_localctx = new VarArrayContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(230);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(231);
						match(CORCHETE_IZQ);
						setState(232);
						expresion(0);
						setState(233);
						match(CORCHETE_DER);
						}
						break;
					case 2:
						{
						_localctx = new VarMiembroContext(new Variable_asignableContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_variable_asignable);
						setState(235);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(236);
						match(PUNTO);
						setState(237);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
	public static class StatementSiContext extends CondicionalContext {
		public TerminalNode SI() { return getToken(YLenguajeParser.SI, 0); }
		public List<TerminalNode> PAR_IZQ() { return getTokens(YLenguajeParser.PAR_IZQ); }
		public TerminalNode PAR_IZQ(int i) {
			return getToken(YLenguajeParser.PAR_IZQ, i);
		}
		public List<ExpresionContext> expresion() {
			return getRuleContexts(ExpresionContext.class);
		}
		public ExpresionContext expresion(int i) {
			return getRuleContext(ExpresionContext.class,i);
		}
		public List<TerminalNode> PAR_DER() { return getTokens(YLenguajeParser.PAR_DER); }
		public TerminalNode PAR_DER(int i) {
			return getToken(YLenguajeParser.PAR_DER, i);
		}
		public List<TerminalNode> ENTONCES() { return getTokens(YLenguajeParser.ENTONCES); }
		public TerminalNode ENTONCES(int i) {
			return getToken(YLenguajeParser.ENTONCES, i);
		}
		public List<BloqueContext> bloque() {
			return getRuleContexts(BloqueContext.class);
		}
		public BloqueContext bloque(int i) {
			return getRuleContext(BloqueContext.class,i);
		}
		public List<TerminalNode> SINO() { return getTokens(YLenguajeParser.SINO); }
		public TerminalNode SINO(int i) {
			return getToken(YLenguajeParser.SINO, i);
		}
		public TerminalNode CONTRARIO() { return getToken(YLenguajeParser.CONTRARIO, 0); }
		public StatementSiContext(CondicionalContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStatementSi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStatementSi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStatementSi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicionalContext condicional() throws RecognitionException {
		CondicionalContext _localctx = new CondicionalContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_condicional);
		try {
			int _alt;
			_localctx = new StatementSiContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(SI);
			setState(244);
			match(PAR_IZQ);
			setState(245);
			expresion(0);
			setState(246);
			match(PAR_DER);
			setState(247);
			match(ENTONCES);
			setState(248);
			bloque();
			setState(258);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(249);
					match(SINO);
					setState(250);
					match(PAR_IZQ);
					setState(251);
					expresion(0);
					setState(252);
					match(PAR_DER);
					setState(253);
					match(ENTONCES);
					setState(254);
					bloque();
					}
					} 
				}
				setState(260);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			setState(263);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(261);
				match(CONTRARIO);
				setState(262);
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
		public List<TerminalNode> NEWLINE() { return getTokens(YLenguajeParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(YLenguajeParser.NEWLINE, i);
		}
		public TerminalNode INDENT() { return getToken(YLenguajeParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(YLenguajeParser.DEDENT, 0); }
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
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterBloque(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitBloque(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitBloque(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BloqueContext bloque() throws RecognitionException {
		BloqueContext _localctx = new BloqueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_bloque);
		int _la;
		try {
			setState(278);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEWLINE:
				enterOuterAlt(_localctx, 1);
				{
				setState(265);
				match(NEWLINE);
				setState(266);
				match(INDENT);
				setState(268); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(267);
					instruccion();
					}
					}
					setState(270); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 34925041996150776L) != 0) );
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NEWLINE) {
					{
					setState(272);
					match(NEWLINE);
					}
				}

				setState(275);
				match(DEDENT);
				}
				break;
			case TIPO_ENTERO:
			case TIPO_CADENA:
			case TIPO_FLOTANTE:
			case TIPO_CARACTER:
			case TIPO_BOOLEANO:
			case VERDADERO:
			case FALSO:
			case ESTRUCTURA:
			case RETORNAR:
			case SI:
			case ELEGIR:
			case ROMPER:
			case CONTINUAR:
			case PARA:
			case MIENTRAS:
			case HACER:
			case NEGACION:
			case RESTA:
			case LLAVE_IZQ:
			case PAR_IZQ:
			case CADENA:
			case CARACTER:
			case NUMERO_DECIMAL:
			case NUMERO_ENTERO:
			case IDENTIFICADOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(277);
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
	public static class StatementElegirContext extends SeleccionContext {
		public TerminalNode ELEGIR() { return getToken(YLenguajeParser.ELEGIR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public TerminalNode NEWLINE() { return getToken(YLenguajeParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(YLenguajeParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(YLenguajeParser.DEDENT, 0); }
		public List<Caso_seleccionContext> caso_seleccion() {
			return getRuleContexts(Caso_seleccionContext.class);
		}
		public Caso_seleccionContext caso_seleccion(int i) {
			return getRuleContext(Caso_seleccionContext.class,i);
		}
		public Caso_defectoContext caso_defecto() {
			return getRuleContext(Caso_defectoContext.class,0);
		}
		public StatementElegirContext(SeleccionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterStatementElegir(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitStatementElegir(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitStatementElegir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SeleccionContext seleccion() throws RecognitionException {
		SeleccionContext _localctx = new SeleccionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_seleccion);
		int _la;
		try {
			_localctx = new StatementElegirContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(ELEGIR);
			setState(281);
			match(PAR_IZQ);
			setState(282);
			expresion(0);
			setState(283);
			match(PAR_DER);
			setState(284);
			match(DOS_PUNTOS);
			setState(285);
			match(NEWLINE);
			setState(286);
			match(INDENT);
			setState(288); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(287);
				caso_seleccion();
				}
				}
				setState(290); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASO );
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SIEMPRE) {
				{
				setState(292);
				caso_defecto();
				}
			}

			setState(295);
			match(DEDENT);
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
	public static class Caso_seleccionContext extends ParserRuleContext {
		public TerminalNode CASO() { return getToken(YLenguajeParser.CASO, 0); }
		public Valor_primitivoContext valor_primitivo() {
			return getRuleContext(Valor_primitivoContext.class,0);
		}
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public TerminalNode NEWLINE() { return getToken(YLenguajeParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(YLenguajeParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(YLenguajeParser.DEDENT, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Caso_seleccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caso_seleccion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterCaso_seleccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitCaso_seleccion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitCaso_seleccion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Caso_seleccionContext caso_seleccion() throws RecognitionException {
		Caso_seleccionContext _localctx = new Caso_seleccionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_caso_seleccion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(CASO);
			setState(298);
			valor_primitivo();
			setState(299);
			match(DOS_PUNTOS);
			setState(300);
			match(NEWLINE);
			setState(301);
			match(INDENT);
			setState(303); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(302);
				instruccion();
				}
				}
				setState(305); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 34925041996150776L) != 0) );
			setState(307);
			match(DEDENT);
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
	public static class Caso_defectoContext extends ParserRuleContext {
		public TerminalNode SIEMPRE() { return getToken(YLenguajeParser.SIEMPRE, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public TerminalNode NEWLINE() { return getToken(YLenguajeParser.NEWLINE, 0); }
		public TerminalNode INDENT() { return getToken(YLenguajeParser.INDENT, 0); }
		public TerminalNode DEDENT() { return getToken(YLenguajeParser.DEDENT, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Caso_defectoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caso_defecto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterCaso_defecto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitCaso_defecto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitCaso_defecto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Caso_defectoContext caso_defecto() throws RecognitionException {
		Caso_defectoContext _localctx = new Caso_defectoContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_caso_defecto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(309);
			match(SIEMPRE);
			setState(310);
			match(DOS_PUNTOS);
			setState(311);
			match(NEWLINE);
			setState(312);
			match(INDENT);
			setState(314); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(313);
				instruccion();
				}
				}
				setState(316); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 34925041996150776L) != 0) );
			setState(318);
			match(DEDENT);
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
	public static class CicloParaContext extends CicloContext {
		public TerminalNode PARA() { return getToken(YLenguajeParser.PARA, 0); }
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public Init_paraContext init_para() {
			return getRuleContext(Init_paraContext.class,0);
		}
		public List<TerminalNode> PUNTO_Y_COMA() { return getTokens(YLenguajeParser.PUNTO_Y_COMA); }
		public TerminalNode PUNTO_Y_COMA(int i) {
			return getToken(YLenguajeParser.PUNTO_Y_COMA, i);
		}
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public Paso_paraContext paso_para() {
			return getRuleContext(Paso_paraContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public CicloParaContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterCicloPara(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitCicloPara(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitCicloPara(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CicloHacerContext extends CicloContext {
		public TerminalNode HACER() { return getToken(YLenguajeParser.HACER, 0); }
		public TerminalNode DOS_PUNTOS() { return getToken(YLenguajeParser.DOS_PUNTOS, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public TerminalNode MIENTRAS() { return getToken(YLenguajeParser.MIENTRAS, 0); }
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public CicloHacerContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterCicloHacer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitCicloHacer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitCicloHacer(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CicloMientrasContext extends CicloContext {
		public TerminalNode MIENTRAS() { return getToken(YLenguajeParser.MIENTRAS, 0); }
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public TerminalNode HACER() { return getToken(YLenguajeParser.HACER, 0); }
		public BloqueContext bloque() {
			return getRuleContext(BloqueContext.class,0);
		}
		public CicloMientrasContext(CicloContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterCicloMientras(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitCicloMientras(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitCicloMientras(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CicloContext ciclo() throws RecognitionException {
		CicloContext _localctx = new CicloContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ciclo);
		try {
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PARA:
				_localctx = new CicloParaContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				match(PARA);
				setState(321);
				match(PAR_IZQ);
				setState(322);
				init_para();
				setState(323);
				match(PUNTO_Y_COMA);
				setState(324);
				expresion(0);
				setState(325);
				match(PUNTO_Y_COMA);
				setState(326);
				paso_para();
				setState(327);
				match(PAR_DER);
				setState(328);
				match(DOS_PUNTOS);
				setState(329);
				bloque();
				}
				break;
			case MIENTRAS:
				_localctx = new CicloMientrasContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				match(MIENTRAS);
				setState(332);
				match(PAR_IZQ);
				setState(333);
				expresion(0);
				setState(334);
				match(PAR_DER);
				setState(335);
				match(HACER);
				setState(336);
				bloque();
				}
				break;
			case HACER:
				_localctx = new CicloHacerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(338);
				match(HACER);
				setState(339);
				match(DOS_PUNTOS);
				setState(340);
				bloque();
				setState(341);
				match(MIENTRAS);
				setState(342);
				match(PAR_IZQ);
				setState(343);
				expresion(0);
				setState(344);
				match(PAR_DER);
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
	public static class Init_paraContext extends ParserRuleContext {
		public Init_paraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init_para; }
	 
		public Init_paraContext() { }
		public void copyFrom(Init_paraContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InitParaDeclContext extends Init_paraContext {
		public Tipo_datoContext tipo_dato() {
			return getRuleContext(Tipo_datoContext.class,0);
		}
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode ASIGNACION() { return getToken(YLenguajeParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public InitParaDeclContext(Init_paraContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterInitParaDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitInitParaDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitInitParaDecl(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InitParaAsigContext extends Init_paraContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(YLenguajeParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public InitParaAsigContext(Init_paraContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterInitParaAsig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitInitParaAsig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitInitParaAsig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Init_paraContext init_para() throws RecognitionException {
		Init_paraContext _localctx = new Init_paraContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_init_para);
		try {
			setState(357);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				_localctx = new InitParaDeclContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(348);
				tipo_dato();
				setState(349);
				match(IDENTIFICADOR);
				setState(350);
				match(ASIGNACION);
				setState(351);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new InitParaAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				variable_asignable(0);
				setState(354);
				match(ASIGNACION);
				setState(355);
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
	public static class Paso_paraContext extends ParserRuleContext {
		public Paso_paraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paso_para; }
	 
		public Paso_paraContext() { }
		public void copyFrom(Paso_paraContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PasoParaExprContext extends Paso_paraContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public PasoParaExprContext(Paso_paraContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterPasoParaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitPasoParaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitPasoParaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PasoParaAsigContext extends Paso_paraContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode ASIGNACION() { return getToken(YLenguajeParser.ASIGNACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public PasoParaAsigContext(Paso_paraContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterPasoParaAsig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitPasoParaAsig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitPasoParaAsig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Paso_paraContext paso_para() throws RecognitionException {
		Paso_paraContext _localctx = new Paso_paraContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_paso_para);
		try {
			setState(364);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				_localctx = new PasoParaExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				expresion(0);
				}
				break;
			case 2:
				_localctx = new PasoParaAsigContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				variable_asignable(0);
				setState(361);
				match(ASIGNACION);
				setState(362);
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
		public TerminalNode RESTA() { return getToken(YLenguajeParser.RESTA, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNegativaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprNegativa(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprNegativa(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprNegativa(this);
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
		public TerminalNode IGUAL_QUE() { return getToken(YLenguajeParser.IGUAL_QUE, 0); }
		public TerminalNode DIFERENTE_QUE() { return getToken(YLenguajeParser.DIFERENTE_QUE, 0); }
		public TerminalNode MAYOR_QUE() { return getToken(YLenguajeParser.MAYOR_QUE, 0); }
		public TerminalNode MENOR_QUE() { return getToken(YLenguajeParser.MENOR_QUE, 0); }
		public ExprRelacionalContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprRelacional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprRelacional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprRelacional(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprParentesisContext extends ExpresionContext {
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public ExprParentesisContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprParentesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprParentesis(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprParentesis(this);
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
		public TerminalNode MULT() { return getToken(YLenguajeParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(YLenguajeParser.DIV, 0); }
		public ExprMultiplicacionDivisionContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprMultiplicacionDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprMultiplicacionDivision(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprMultiplicacionDivision(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPostIncrementoContext extends ExpresionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode INCREMENTO() { return getToken(YLenguajeParser.INCREMENTO, 0); }
		public ExprPostIncrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprPostIncremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprPostIncremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprPostIncremento(this);
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
		public TerminalNode SUMA() { return getToken(YLenguajeParser.SUMA, 0); }
		public TerminalNode RESTA() { return getToken(YLenguajeParser.RESTA, 0); }
		public ExprSumaRestaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprSumaResta(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprSumaResta(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprSumaResta(this);
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
		public TerminalNode OR() { return getToken(YLenguajeParser.OR, 0); }
		public ExprOrContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprOr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprNegadaContext extends ExpresionContext {
		public TerminalNode NEGACION() { return getToken(YLenguajeParser.NEGACION, 0); }
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public ExprNegadaContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprNegada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprNegada(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprNegada(this);
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
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprPrimitivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprPrimitivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprPrimitivo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprPostDecrementoContext extends ExpresionContext {
		public Variable_asignableContext variable_asignable() {
			return getRuleContext(Variable_asignableContext.class,0);
		}
		public TerminalNode DECREMENTO() { return getToken(YLenguajeParser.DECREMENTO, 0); }
		public ExprPostDecrementoContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprPostDecremento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprPostDecremento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprPostDecremento(this);
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
		public TerminalNode AND() { return getToken(YLenguajeParser.AND, 0); }
		public ExprAndContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprAnd(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprLlamadaFuncionContext extends ExpresionContext {
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public TerminalNode PAR_IZQ() { return getToken(YLenguajeParser.PAR_IZQ, 0); }
		public TerminalNode PAR_DER() { return getToken(YLenguajeParser.PAR_DER, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public ExprLlamadaFuncionContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprLlamadaFuncion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprLlamadaFuncion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprLlamadaFuncion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprListaLiteralContext extends ExpresionContext {
		public TerminalNode LLAVE_IZQ() { return getToken(YLenguajeParser.LLAVE_IZQ, 0); }
		public Lista_expresionesContext lista_expresiones() {
			return getRuleContext(Lista_expresionesContext.class,0);
		}
		public TerminalNode LLAVE_DER() { return getToken(YLenguajeParser.LLAVE_DER, 0); }
		public ExprListaLiteralContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprListaLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprListaLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprListaLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExprAccesoMiembroContext extends ExpresionContext {
		public ExpresionContext expresion() {
			return getRuleContext(ExpresionContext.class,0);
		}
		public TerminalNode PUNTO() { return getToken(YLenguajeParser.PUNTO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public ExprAccesoMiembroContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprAccesoMiembro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprAccesoMiembro(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprAccesoMiembro(this);
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
		public TerminalNode CORCHETE_IZQ() { return getToken(YLenguajeParser.CORCHETE_IZQ, 0); }
		public TerminalNode CORCHETE_DER() { return getToken(YLenguajeParser.CORCHETE_DER, 0); }
		public ExprAccesoArrayContext(ExpresionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterExprAccesoArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitExprAccesoArray(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitExprAccesoArray(this);
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
		int _startState = 48;
		enterRecursionRule(_localctx, 48, RULE_expresion, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				_localctx = new ExprParentesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(367);
				match(PAR_IZQ);
				setState(368);
				expresion(0);
				setState(369);
				match(PAR_DER);
				}
				break;
			case 2:
				{
				_localctx = new ExprLlamadaFuncionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(371);
				match(IDENTIFICADOR);
				setState(372);
				match(PAR_IZQ);
				setState(374);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 34925041963500288L) != 0)) {
					{
					setState(373);
					lista_expresiones();
					}
				}

				setState(376);
				match(PAR_DER);
				}
				break;
			case 3:
				{
				_localctx = new ExprPostIncrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(377);
				variable_asignable(0);
				setState(378);
				match(INCREMENTO);
				}
				break;
			case 4:
				{
				_localctx = new ExprPostDecrementoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(380);
				variable_asignable(0);
				setState(381);
				match(DECREMENTO);
				}
				break;
			case 5:
				{
				_localctx = new ExprListaLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(383);
				match(LLAVE_IZQ);
				setState(384);
				lista_expresiones();
				setState(385);
				match(LLAVE_DER);
				}
				break;
			case 6:
				{
				_localctx = new ExprNegativaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(387);
				match(RESTA);
				setState(388);
				expresion(8);
				}
				break;
			case 7:
				{
				_localctx = new ExprNegadaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(389);
				match(NEGACION);
				setState(390);
				expresion(7);
				}
				break;
			case 8:
				{
				_localctx = new ExprPrimitivoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(391);
				valor_primitivo();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(419);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(417);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
					case 1:
						{
						_localctx = new ExprMultiplicacionDivisionContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(394);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(395);
						_la = _input.LA(1);
						if ( !(_la==MULT || _la==DIV) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(396);
						expresion(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprSumaRestaContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(397);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(398);
						_la = _input.LA(1);
						if ( !(_la==SUMA || _la==RESTA) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(399);
						expresion(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprRelacionalContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(400);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(401);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4026531840L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(402);
						expresion(5);
						}
						break;
					case 4:
						{
						_localctx = new ExprAndContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(403);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(404);
						match(AND);
						setState(405);
						expresion(4);
						}
						break;
					case 5:
						{
						_localctx = new ExprOrContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(406);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(407);
						match(OR);
						setState(408);
						expresion(3);
						}
						break;
					case 6:
						{
						_localctx = new ExprAccesoArrayContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(409);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(410);
						match(CORCHETE_IZQ);
						setState(411);
						expresion(0);
						setState(412);
						match(CORCHETE_DER);
						}
						break;
					case 7:
						{
						_localctx = new ExprAccesoMiembroContext(new ExpresionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expresion);
						setState(414);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(415);
						match(PUNTO);
						setState(416);
						match(IDENTIFICADOR);
						}
						break;
					}
					} 
				}
				setState(421);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
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
		public TerminalNode NUMERO_ENTERO() { return getToken(YLenguajeParser.NUMERO_ENTERO, 0); }
		public TerminalNode NUMERO_DECIMAL() { return getToken(YLenguajeParser.NUMERO_DECIMAL, 0); }
		public TerminalNode CADENA() { return getToken(YLenguajeParser.CADENA, 0); }
		public TerminalNode CARACTER() { return getToken(YLenguajeParser.CARACTER, 0); }
		public TerminalNode VERDADERO() { return getToken(YLenguajeParser.VERDADERO, 0); }
		public TerminalNode FALSO() { return getToken(YLenguajeParser.FALSO, 0); }
		public TerminalNode IDENTIFICADOR() { return getToken(YLenguajeParser.IDENTIFICADOR, 0); }
		public Valor_primitivoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valor_primitivo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterValor_primitivo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitValor_primitivo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitValor_primitivo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Valor_primitivoContext valor_primitivo() throws RecognitionException {
		Valor_primitivoContext _localctx = new Valor_primitivoContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_valor_primitivo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34902897112122112L) != 0)) ) {
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
		public List<TerminalNode> COMA() { return getTokens(YLenguajeParser.COMA); }
		public TerminalNode COMA(int i) {
			return getToken(YLenguajeParser.COMA, i);
		}
		public Lista_expresionesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lista_expresiones; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).enterLista_expresiones(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof YLenguajeListener ) ((YLenguajeListener)listener).exitLista_expresiones(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof YLenguajeVisitor ) return ((YLenguajeVisitor<? extends T>)visitor).visitLista_expresiones(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lista_expresionesContext lista_expresiones() throws RecognitionException {
		Lista_expresionesContext _localctx = new Lista_expresionesContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_lista_expresiones);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			expresion(0);
			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMA) {
				{
				{
				setState(425);
				match(COMA);
				setState(426);
				expresion(0);
				}
				}
				setState(431);
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
		case 15:
			return variable_asignable_sempred((Variable_asignableContext)_localctx, predIndex);
		case 24:
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
			return precpred(_ctx, 13);
		case 8:
			return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001;\u01b1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0001\u0000\u0003\u0000"+
		"8\b\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u0001@\b\u0001\n\u0001\f\u0001C\t\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002H\b\u0002\n\u0002\f\u0002K\t\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0004\u0004U\b\u0004\u000b\u0004\f\u0004V\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005f\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006l\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006u\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006}\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u0082"+
		"\b\u0007\n\u0007\f\u0007\u0085\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0093\b\b\u0001\t\u0001\t\u0001\t\u0004\t\u0098\b\t\u000b\t\f\t\u0099"+
		"\u0001\t\u0003\t\u009d\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00b5"+
		"\b\n\u0001\u000b\u0003\u000b\u00b8\b\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00c3\b\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00de\b\r\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00ef\b\u000f\n\u000f\f\u000f"+
		"\u00f2\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0005\u0010\u0101\b\u0010\n\u0010\f\u0010\u0104"+
		"\t\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0108\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0004\u0011\u010d\b\u0011\u000b\u0011\f\u0011"+
		"\u010e\u0001\u0011\u0003\u0011\u0112\b\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u0117\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0004\u0012\u0121"+
		"\b\u0012\u000b\u0012\f\u0012\u0122\u0001\u0012\u0003\u0012\u0126\b\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0004\u0013\u0130\b\u0013\u000b\u0013\f\u0013"+
		"\u0131\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0004\u0014\u013b\b\u0014\u000b\u0014\f\u0014\u013c"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u015b\b\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0166\b\u0016\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u016d\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0003\u0018\u0177\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0003\u0018\u0189\b\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u01a2\b\u0018\n\u0018"+
		"\f\u0018\u01a5\t\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u01ac\b\u001a\n\u001a\f\u001a\u01af\t\u001a\u0001"+
		"\u001a\u0000\u0002\u001e0\u001b\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.024\u0000\u0005\u0002"+
		"\u0000\u0003\u000766\u0001\u0000&\'\u0001\u0000$%\u0001\u0000\u001c\u001f"+
		"\u0002\u0000\b\t26\u01d1\u00007\u0001\u0000\u0000\u0000\u0002<\u0001\u0000"+
		"\u0000\u0000\u0004D\u0001\u0000\u0000\u0000\u0006L\u0001\u0000\u0000\u0000"+
		"\bN\u0001\u0000\u0000\u0000\ne\u0001\u0000\u0000\u0000\f|\u0001\u0000"+
		"\u0000\u0000\u000e~\u0001\u0000\u0000\u0000\u0010\u0092\u0001\u0000\u0000"+
		"\u0000\u0012\u0094\u0001\u0000\u0000\u0000\u0014\u00b4\u0001\u0000\u0000"+
		"\u0000\u0016\u00b7\u0001\u0000\u0000\u0000\u0018\u00bb\u0001\u0000\u0000"+
		"\u0000\u001a\u00dd\u0001\u0000\u0000\u0000\u001c\u00df\u0001\u0000\u0000"+
		"\u0000\u001e\u00e3\u0001\u0000\u0000\u0000 \u00f3\u0001\u0000\u0000\u0000"+
		"\"\u0116\u0001\u0000\u0000\u0000$\u0118\u0001\u0000\u0000\u0000&\u0129"+
		"\u0001\u0000\u0000\u0000(\u0135\u0001\u0000\u0000\u0000*\u015a\u0001\u0000"+
		"\u0000\u0000,\u0165\u0001\u0000\u0000\u0000.\u016c\u0001\u0000\u0000\u0000"+
		"0\u0188\u0001\u0000\u0000\u00002\u01a6\u0001\u0000\u0000\u00004\u01a8"+
		"\u0001\u0000\u0000\u000068\u0003\u0002\u0001\u000076\u0001\u0000\u0000"+
		"\u000078\u0001\u0000\u0000\u000089\u0001\u0000\u0000\u00009:\u0003\u0004"+
		"\u0002\u0000:;\u0005\u0000\u0000\u0001;\u0001\u0001\u0000\u0000\u0000"+
		"<A\u0005\u0001\u0000\u0000=@\u0003\b\u0004\u0000>@\u00058\u0000\u0000"+
		"?=\u0001\u0000\u0000\u0000?>\u0001\u0000\u0000\u0000@C\u0001\u0000\u0000"+
		"\u0000A?\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000B\u0003\u0001"+
		"\u0000\u0000\u0000CA\u0001\u0000\u0000\u0000DI\u0005\u0002\u0000\u0000"+
		"EH\u0003\f\u0006\u0000FH\u00058\u0000\u0000GE\u0001\u0000\u0000\u0000"+
		"GF\u0001\u0000\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000"+
		"\u0000IJ\u0001\u0000\u0000\u0000J\u0005\u0001\u0000\u0000\u0000KI\u0001"+
		"\u0000\u0000\u0000LM\u0007\u0000\u0000\u0000M\u0007\u0001\u0000\u0000"+
		"\u0000NO\u0005\n\u0000\u0000OP\u00056\u0000\u0000PQ\u0005/\u0000\u0000"+
		"QR\u00058\u0000\u0000RT\u0005:\u0000\u0000SU\u0003\n\u0005\u0000TS\u0001"+
		"\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VT\u0001\u0000\u0000\u0000"+
		"VW\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0005;\u0000\u0000"+
		"Y\t\u0001\u0000\u0000\u0000Z[\u0003\u0006\u0003\u0000[\\\u00056\u0000"+
		"\u0000\\]\u00058\u0000\u0000]f\u0001\u0000\u0000\u0000^_\u0003\u0006\u0003"+
		"\u0000_`\u00056\u0000\u0000`a\u0005(\u0000\u0000ab\u00055\u0000\u0000"+
		"bc\u0005)\u0000\u0000cd\u00058\u0000\u0000df\u0001\u0000\u0000\u0000e"+
		"Z\u0001\u0000\u0000\u0000e^\u0001\u0000\u0000\u0000f\u000b\u0001\u0000"+
		"\u0000\u0000gh\u0005\u000b\u0000\u0000hi\u00056\u0000\u0000ik\u0005,\u0000"+
		"\u0000jl\u0003\u000e\u0007\u0000kj\u0001\u0000\u0000\u0000kl\u0001\u0000"+
		"\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0005-\u0000\u0000no\u0005/\u0000"+
		"\u0000o}\u0003\u0012\t\u0000pq\u0005\u000b\u0000\u0000qr\u00056\u0000"+
		"\u0000rt\u0005,\u0000\u0000su\u0003\u000e\u0007\u0000ts\u0001\u0000\u0000"+
		"\u0000tu\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000\u0000vw\u0005-\u0000"+
		"\u0000wx\u0005\u0019\u0000\u0000xy\u0003\u0006\u0003\u0000yz\u0005/\u0000"+
		"\u0000z{\u0003\u0012\t\u0000{}\u0001\u0000\u0000\u0000|g\u0001\u0000\u0000"+
		"\u0000|p\u0001\u0000\u0000\u0000}\r\u0001\u0000\u0000\u0000~\u0083\u0003"+
		"\u0010\b\u0000\u007f\u0080\u00050\u0000\u0000\u0080\u0082\u0003\u0010"+
		"\b\u0000\u0081\u007f\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000"+
		"\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000"+
		"\u0000\u0084\u000f\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000"+
		"\u0000\u0086\u0087\u0003\u0006\u0003\u0000\u0087\u0088\u00056\u0000\u0000"+
		"\u0088\u0093\u0001\u0000\u0000\u0000\u0089\u008a\u0005(\u0000\u0000\u008a"+
		"\u008b\u0005)\u0000\u0000\u008b\u008c\u0003\u0006\u0003\u0000\u008c\u008d"+
		"\u00056\u0000\u0000\u008d\u0093\u0001\u0000\u0000\u0000\u008e\u008f\u0005"+
		"*\u0000\u0000\u008f\u0090\u0005+\u0000\u0000\u0090\u0091\u00056\u0000"+
		"\u0000\u0091\u0093\u00056\u0000\u0000\u0092\u0086\u0001\u0000\u0000\u0000"+
		"\u0092\u0089\u0001\u0000\u0000\u0000\u0092\u008e\u0001\u0000\u0000\u0000"+
		"\u0093\u0011\u0001\u0000\u0000\u0000\u0094\u0095\u00058\u0000\u0000\u0095"+
		"\u0097\u0005:\u0000\u0000\u0096\u0098\u0003\u0014\n\u0000\u0097\u0096"+
		"\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u0097"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009c"+
		"\u0001\u0000\u0000\u0000\u009b\u009d\u00058\u0000\u0000\u009c\u009b\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001"+
		"\u0000\u0000\u0000\u009e\u009f\u0005;\u0000\u0000\u009f\u0013\u0001\u0000"+
		"\u0000\u0000\u00a0\u00a1\u0003\u001a\r\u0000\u00a1\u00a2\u0003\u0016\u000b"+
		"\u0000\u00a2\u00b5\u0001\u0000\u0000\u0000\u00a3\u00a4\u0003\u001c\u000e"+
		"\u0000\u00a4\u00a5\u0003\u0016\u000b\u0000\u00a5\u00b5\u0001\u0000\u0000"+
		"\u0000\u00a6\u00b5\u0003\b\u0004\u0000\u00a7\u00b5\u0003 \u0010\u0000"+
		"\u00a8\u00b5\u0003$\u0012\u0000\u00a9\u00b5\u0003*\u0015\u0000\u00aa\u00ab"+
		"\u0003\u0018\f\u0000\u00ab\u00ac\u0003\u0016\u000b\u0000\u00ac\u00b5\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ae\u0005\u0015\u0000\u0000\u00ae\u00b5\u0003"+
		"\u0016\u000b\u0000\u00af\u00b0\u0005\u0014\u0000\u0000\u00b0\u00b5\u0003"+
		"\u0016\u000b\u0000\u00b1\u00b2\u00030\u0018\u0000\u00b2\u00b3\u0003\u0016"+
		"\u000b\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00a0\u0001\u0000"+
		"\u0000\u0000\u00b4\u00a3\u0001\u0000\u0000\u0000\u00b4\u00a6\u0001\u0000"+
		"\u0000\u0000\u00b4\u00a7\u0001\u0000\u0000\u0000\u00b4\u00a8\u0001\u0000"+
		"\u0000\u0000\u00b4\u00a9\u0001\u0000\u0000\u0000\u00b4\u00aa\u0001\u0000"+
		"\u0000\u0000\u00b4\u00ad\u0001\u0000\u0000\u0000\u00b4\u00af\u0001\u0000"+
		"\u0000\u0000\u00b4\u00b1\u0001\u0000\u0000\u0000\u00b5\u0015\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b8\u0005.\u0000\u0000\u00b7\u00b6\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u00058\u0000\u0000\u00ba\u0017\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0005\f\u0000\u0000\u00bc\u00bd\u00030\u0018\u0000\u00bd"+
		"\u0019\u0001\u0000\u0000\u0000\u00be\u00bf\u0003\u0006\u0003\u0000\u00bf"+
		"\u00c2\u00056\u0000\u0000\u00c0\u00c1\u0005#\u0000\u0000\u00c1\u00c3\u0003"+
		"0\u0018\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c3\u00de\u0001\u0000\u0000\u0000\u00c4\u00c5\u0003\u0006"+
		"\u0003\u0000\u00c5\u00c6\u00056\u0000\u0000\u00c6\u00c7\u0005(\u0000\u0000"+
		"\u00c7\u00c8\u00030\u0018\u0000\u00c8\u00c9\u0005)\u0000\u0000\u00c9\u00de"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cb\u0003\u0006\u0003\u0000\u00cb\u00cc"+
		"\u00056\u0000\u0000\u00cc\u00cd\u0005(\u0000\u0000\u00cd\u00ce\u00030"+
		"\u0018\u0000\u00ce\u00cf\u0005)\u0000\u0000\u00cf\u00d0\u0005#\u0000\u0000"+
		"\u00d0\u00d1\u0005*\u0000\u0000\u00d1\u00d2\u00034\u001a\u0000\u00d2\u00d3"+
		"\u0005+\u0000\u0000\u00d3\u00de\u0001\u0000\u0000\u0000\u00d4\u00d5\u0003"+
		"\u0006\u0003\u0000\u00d5\u00d6\u00056\u0000\u0000\u00d6\u00d7\u0005(\u0000"+
		"\u0000\u00d7\u00d8\u00030\u0018\u0000\u00d8\u00d9\u0005)\u0000\u0000\u00d9"+
		"\u00da\u0005(\u0000\u0000\u00da\u00db\u00030\u0018\u0000\u00db\u00dc\u0005"+
		")\u0000\u0000\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00be\u0001\u0000"+
		"\u0000\u0000\u00dd\u00c4\u0001\u0000\u0000\u0000\u00dd\u00ca\u0001\u0000"+
		"\u0000\u0000\u00dd\u00d4\u0001\u0000\u0000\u0000\u00de\u001b\u0001\u0000"+
		"\u0000\u0000\u00df\u00e0\u0003\u001e\u000f\u0000\u00e0\u00e1\u0005#\u0000"+
		"\u0000\u00e1\u00e2\u00030\u0018\u0000\u00e2\u001d\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e4\u0006\u000f\uffff\uffff\u0000\u00e4\u00e5\u00056\u0000\u0000"+
		"\u00e5\u00f0\u0001\u0000\u0000\u0000\u00e6\u00e7\n\u0002\u0000\u0000\u00e7"+
		"\u00e8\u0005(\u0000\u0000\u00e8\u00e9\u00030\u0018\u0000\u00e9\u00ea\u0005"+
		")\u0000\u0000\u00ea\u00ef\u0001\u0000\u0000\u0000\u00eb\u00ec\n\u0001"+
		"\u0000\u0000\u00ec\u00ed\u00051\u0000\u0000\u00ed\u00ef\u00056\u0000\u0000"+
		"\u00ee\u00e6\u0001\u0000\u0000\u0000\u00ee\u00eb\u0001\u0000\u0000\u0000"+
		"\u00ef\u00f2\u0001\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000"+
		"\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u001f\u0001\u0000\u0000\u0000"+
		"\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005\r\u0000\u0000\u00f4"+
		"\u00f5\u0005,\u0000\u0000\u00f5\u00f6\u00030\u0018\u0000\u00f6\u00f7\u0005"+
		"-\u0000\u0000\u00f7\u00f8\u0005\u000e\u0000\u0000\u00f8\u0102\u0003\""+
		"\u0011\u0000\u00f9\u00fa\u0005\u000f\u0000\u0000\u00fa\u00fb\u0005,\u0000"+
		"\u0000\u00fb\u00fc\u00030\u0018\u0000\u00fc\u00fd\u0005-\u0000\u0000\u00fd"+
		"\u00fe\u0005\u000e\u0000\u0000\u00fe\u00ff\u0003\"\u0011\u0000\u00ff\u0101"+
		"\u0001\u0000\u0000\u0000\u0100\u00f9\u0001\u0000\u0000\u0000\u0101\u0104"+
		"\u0001\u0000\u0000\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0102\u0103"+
		"\u0001\u0000\u0000\u0000\u0103\u0107\u0001\u0000\u0000\u0000\u0104\u0102"+
		"\u0001\u0000\u0000\u0000\u0105\u0106\u0005\u0010\u0000\u0000\u0106\u0108"+
		"\u0003\"\u0011\u0000\u0107\u0105\u0001\u0000\u0000\u0000\u0107\u0108\u0001"+
		"\u0000\u0000\u0000\u0108!\u0001\u0000\u0000\u0000\u0109\u010a\u00058\u0000"+
		"\u0000\u010a\u010c\u0005:\u0000\u0000\u010b\u010d\u0003\u0014\n\u0000"+
		"\u010c\u010b\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000"+
		"\u010e\u010c\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000"+
		"\u010f\u0111\u0001\u0000\u0000\u0000\u0110\u0112\u00058\u0000\u0000\u0111"+
		"\u0110\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112"+
		"\u0113\u0001\u0000\u0000\u0000\u0113\u0114\u0005;\u0000\u0000\u0114\u0117"+
		"\u0001\u0000\u0000\u0000\u0115\u0117\u0003\u0014\n\u0000\u0116\u0109\u0001"+
		"\u0000\u0000\u0000\u0116\u0115\u0001\u0000\u0000\u0000\u0117#\u0001\u0000"+
		"\u0000\u0000\u0118\u0119\u0005\u0011\u0000\u0000\u0119\u011a\u0005,\u0000"+
		"\u0000\u011a\u011b\u00030\u0018\u0000\u011b\u011c\u0005-\u0000\u0000\u011c"+
		"\u011d\u0005/\u0000\u0000\u011d\u011e\u00058\u0000\u0000\u011e\u0120\u0005"+
		":\u0000\u0000\u011f\u0121\u0003&\u0013\u0000\u0120\u011f\u0001\u0000\u0000"+
		"\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0120\u0001\u0000\u0000"+
		"\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123\u0125\u0001\u0000\u0000"+
		"\u0000\u0124\u0126\u0003(\u0014\u0000\u0125\u0124\u0001\u0000\u0000\u0000"+
		"\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000"+
		"\u0127\u0128\u0005;\u0000\u0000\u0128%\u0001\u0000\u0000\u0000\u0129\u012a"+
		"\u0005\u0012\u0000\u0000\u012a\u012b\u00032\u0019\u0000\u012b\u012c\u0005"+
		"/\u0000\u0000\u012c\u012d\u00058\u0000\u0000\u012d\u012f\u0005:\u0000"+
		"\u0000\u012e\u0130\u0003\u0014\n\u0000\u012f\u012e\u0001\u0000\u0000\u0000"+
		"\u0130\u0131\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000"+
		"\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0133\u0001\u0000\u0000\u0000"+
		"\u0133\u0134\u0005;\u0000\u0000\u0134\'\u0001\u0000\u0000\u0000\u0135"+
		"\u0136\u0005\u0013\u0000\u0000\u0136\u0137\u0005/\u0000\u0000\u0137\u0138"+
		"\u00058\u0000\u0000\u0138\u013a\u0005:\u0000\u0000\u0139\u013b\u0003\u0014"+
		"\n\u0000\u013a\u0139\u0001\u0000\u0000\u0000\u013b\u013c\u0001\u0000\u0000"+
		"\u0000\u013c\u013a\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000\u0000"+
		"\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u013f\u0005;\u0000\u0000"+
		"\u013f)\u0001\u0000\u0000\u0000\u0140\u0141\u0005\u0016\u0000\u0000\u0141"+
		"\u0142\u0005,\u0000\u0000\u0142\u0143\u0003,\u0016\u0000\u0143\u0144\u0005"+
		".\u0000\u0000\u0144\u0145\u00030\u0018\u0000\u0145\u0146\u0005.\u0000"+
		"\u0000\u0146\u0147\u0003.\u0017\u0000\u0147\u0148\u0005-\u0000\u0000\u0148"+
		"\u0149\u0005/\u0000\u0000\u0149\u014a\u0003\"\u0011\u0000\u014a\u015b"+
		"\u0001\u0000\u0000\u0000\u014b\u014c\u0005\u0017\u0000\u0000\u014c\u014d"+
		"\u0005,\u0000\u0000\u014d\u014e\u00030\u0018\u0000\u014e\u014f\u0005-"+
		"\u0000\u0000\u014f\u0150\u0005\u0018\u0000\u0000\u0150\u0151\u0003\"\u0011"+
		"\u0000\u0151\u015b\u0001\u0000\u0000\u0000\u0152\u0153\u0005\u0018\u0000"+
		"\u0000\u0153\u0154\u0005/\u0000\u0000\u0154\u0155\u0003\"\u0011\u0000"+
		"\u0155\u0156\u0005\u0017\u0000\u0000\u0156\u0157\u0005,\u0000\u0000\u0157"+
		"\u0158\u00030\u0018\u0000\u0158\u0159\u0005-\u0000\u0000\u0159\u015b\u0001"+
		"\u0000\u0000\u0000\u015a\u0140\u0001\u0000\u0000\u0000\u015a\u014b\u0001"+
		"\u0000\u0000\u0000\u015a\u0152\u0001\u0000\u0000\u0000\u015b+\u0001\u0000"+
		"\u0000\u0000\u015c\u015d\u0003\u0006\u0003\u0000\u015d\u015e\u00056\u0000"+
		"\u0000\u015e\u015f\u0005#\u0000\u0000\u015f\u0160\u00030\u0018\u0000\u0160"+
		"\u0166\u0001\u0000\u0000\u0000\u0161\u0162\u0003\u001e\u000f\u0000\u0162"+
		"\u0163\u0005#\u0000\u0000\u0163\u0164\u00030\u0018\u0000\u0164\u0166\u0001"+
		"\u0000\u0000\u0000\u0165\u015c\u0001\u0000\u0000\u0000\u0165\u0161\u0001"+
		"\u0000\u0000\u0000\u0166-\u0001\u0000\u0000\u0000\u0167\u016d\u00030\u0018"+
		"\u0000\u0168\u0169\u0003\u001e\u000f\u0000\u0169\u016a\u0005#\u0000\u0000"+
		"\u016a\u016b\u00030\u0018\u0000\u016b\u016d\u0001\u0000\u0000\u0000\u016c"+
		"\u0167\u0001\u0000\u0000\u0000\u016c\u0168\u0001\u0000\u0000\u0000\u016d"+
		"/\u0001\u0000\u0000\u0000\u016e\u016f\u0006\u0018\uffff\uffff\u0000\u016f"+
		"\u0170\u0005,\u0000\u0000\u0170\u0171\u00030\u0018\u0000\u0171\u0172\u0005"+
		"-\u0000\u0000\u0172\u0189\u0001\u0000\u0000\u0000\u0173\u0174\u00056\u0000"+
		"\u0000\u0174\u0176\u0005,\u0000\u0000\u0175\u0177\u00034\u001a\u0000\u0176"+
		"\u0175\u0001\u0000\u0000\u0000\u0176\u0177\u0001\u0000\u0000\u0000\u0177"+
		"\u0178\u0001\u0000\u0000\u0000\u0178\u0189\u0005-\u0000\u0000\u0179\u017a"+
		"\u0003\u001e\u000f\u0000\u017a\u017b\u0005\u001a\u0000\u0000\u017b\u0189"+
		"\u0001\u0000\u0000\u0000\u017c\u017d\u0003\u001e\u000f\u0000\u017d\u017e"+
		"\u0005\u001b\u0000\u0000\u017e\u0189\u0001\u0000\u0000\u0000\u017f\u0180"+
		"\u0005*\u0000\u0000\u0180\u0181\u00034\u001a\u0000\u0181\u0182\u0005+"+
		"\u0000\u0000\u0182\u0189\u0001\u0000\u0000\u0000\u0183\u0184\u0005%\u0000"+
		"\u0000\u0184\u0189\u00030\u0018\b\u0185\u0186\u0005\"\u0000\u0000\u0186"+
		"\u0189\u00030\u0018\u0007\u0187\u0189\u00032\u0019\u0000\u0188\u016e\u0001"+
		"\u0000\u0000\u0000\u0188\u0173\u0001\u0000\u0000\u0000\u0188\u0179\u0001"+
		"\u0000\u0000\u0000\u0188\u017c\u0001\u0000\u0000\u0000\u0188\u017f\u0001"+
		"\u0000\u0000\u0000\u0188\u0183\u0001\u0000\u0000\u0000\u0188\u0185\u0001"+
		"\u0000\u0000\u0000\u0188\u0187\u0001\u0000\u0000\u0000\u0189\u01a3\u0001"+
		"\u0000\u0000\u0000\u018a\u018b\n\u0006\u0000\u0000\u018b\u018c\u0007\u0001"+
		"\u0000\u0000\u018c\u01a2\u00030\u0018\u0007\u018d\u018e\n\u0005\u0000"+
		"\u0000\u018e\u018f\u0007\u0002\u0000\u0000\u018f\u01a2\u00030\u0018\u0006"+
		"\u0190\u0191\n\u0004\u0000\u0000\u0191\u0192\u0007\u0003\u0000\u0000\u0192"+
		"\u01a2\u00030\u0018\u0005\u0193\u0194\n\u0003\u0000\u0000\u0194\u0195"+
		"\u0005 \u0000\u0000\u0195\u01a2\u00030\u0018\u0004\u0196\u0197\n\u0002"+
		"\u0000\u0000\u0197\u0198\u0005!\u0000\u0000\u0198\u01a2\u00030\u0018\u0003"+
		"\u0199\u019a\n\r\u0000\u0000\u019a\u019b\u0005(\u0000\u0000\u019b\u019c"+
		"\u00030\u0018\u0000\u019c\u019d\u0005)\u0000\u0000\u019d\u01a2\u0001\u0000"+
		"\u0000\u0000\u019e\u019f\n\f\u0000\u0000\u019f\u01a0\u00051\u0000\u0000"+
		"\u01a0\u01a2\u00056\u0000\u0000\u01a1\u018a\u0001\u0000\u0000\u0000\u01a1"+
		"\u018d\u0001\u0000\u0000\u0000\u01a1\u0190\u0001\u0000\u0000\u0000\u01a1"+
		"\u0193\u0001\u0000\u0000\u0000\u01a1\u0196\u0001\u0000\u0000\u0000\u01a1"+
		"\u0199\u0001\u0000\u0000\u0000\u01a1\u019e\u0001\u0000\u0000\u0000\u01a2"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a3\u01a1\u0001\u0000\u0000\u0000\u01a3"+
		"\u01a4\u0001\u0000\u0000\u0000\u01a41\u0001\u0000\u0000\u0000\u01a5\u01a3"+
		"\u0001\u0000\u0000\u0000\u01a6\u01a7\u0007\u0004\u0000\u0000\u01a73\u0001"+
		"\u0000\u0000\u0000\u01a8\u01ad\u00030\u0018\u0000\u01a9\u01aa\u00050\u0000"+
		"\u0000\u01aa\u01ac\u00030\u0018\u0000\u01ab\u01a9\u0001\u0000\u0000\u0000"+
		"\u01ac\u01af\u0001\u0000\u0000\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000"+
		"\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae5\u0001\u0000\u0000\u0000\u01af"+
		"\u01ad\u0001\u0000\u0000\u0000%7?AGIVekt|\u0083\u0092\u0099\u009c\u00b4"+
		"\u00b7\u00c2\u00dd\u00ee\u00f0\u0102\u0107\u010e\u0111\u0116\u0122\u0125"+
		"\u0131\u013c\u015a\u0165\u016c\u0176\u0188\u01a1\u01a3\u01ad";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}