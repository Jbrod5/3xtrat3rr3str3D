package org.jrg.analisis.yLenguaje.lexer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;
import org.jrg.antlrBase.yLenguaje.YLenguajeLexer;

/**
 * Envuelve el lexer generado de Y? e inserta tokens INDENT y DEDENT
 * segun la columna del primer token real de cada linea, igual que
 * hace el tokenizador de Python.
 */
public class YLenguajeIndentTokenSource implements TokenSource {

    private final Lexer lexerDelegado;
    private final Deque<Integer> pilaIndentacion = new ArrayDeque<>();
    private final List<Token> tokensPendientes = new ArrayList<>();

    private Token ultimoTokenReal = null;
    private boolean inicioDeLinea = true;
    private boolean finDeArchivoProcesado = false;

    public YLenguajeIndentTokenSource(Lexer lexerDelegado) {
        this.lexerDelegado = lexerDelegado;
        // el nivel base (columna 0) siempre esta en la pila
        this.pilaIndentacion.push(0);
    }

    @Override
    public Token nextToken() {
        if (!tokensPendientes.isEmpty()) {
            return tokensPendientes.remove(0);
        }

        Token token = lexerDelegado.nextToken();

        if (token.getType() == Token.EOF) {
            return manejarFinDeArchivo(token);
        }

        // los tokens en canal oculto (comentarios) no cuentan para la indentacion
        if (token.getChannel() != Token.DEFAULT_CHANNEL) {
            return token;
        }

        if (inicioDeLinea && token.getType() != YLenguajeLexer.NEWLINE) {
            manejarIndentacion(token);
            inicioDeLinea = false;
        }

        if (token.getType() == YLenguajeLexer.NEWLINE) {
            inicioDeLinea = true;
        }

        ultimoTokenReal = token;
        tokensPendientes.add(token);
        return tokensPendientes.remove(0);
    }

    // comparar la columna del token contra el tope de la pila y encolar
    // los INDENT/DEDENT necesarios antes de ese token
    private void manejarIndentacion(Token token) {
        int columna = token.getCharPositionInLine();
        int nivelActual = pilaIndentacion.peek();

        if (columna > nivelActual) {
            pilaIndentacion.push(columna);
            tokensPendientes.add(crearToken(token, YLenguajeLexer.INDENT, ""));
        } else if (columna < nivelActual) {
            while (pilaIndentacion.size() > 1 && pilaIndentacion.peek() > columna) {
                pilaIndentacion.pop();
                tokensPendientes.add(crearToken(token, YLenguajeLexer.DEDENT, ""));
            }
            // nota: si columna no coincide exactamente con ningun nivel de la
            // pila despues del while, es indentacion inconsistente. de momento
            // se acepta el nivel mas cercano; conviene reportarlo como error
            // lexico mas adelante conectando esta clase con RecolectorErrores.
        }
    }

    // al llegar EOF: sintetizar un NEWLINE final si el archivo no termino con
    // uno, y cerrar todos los niveles de indentacion abiertos con DEDENT
    private Token manejarFinDeArchivo(Token tokenEOF) {
        if (finDeArchivoProcesado) {
            return tokenEOF;
        }
        finDeArchivoProcesado = true;

        if (ultimoTokenReal != null && ultimoTokenReal.getType() != YLenguajeLexer.NEWLINE) {
            tokensPendientes.add(crearToken(tokenEOF, YLenguajeLexer.NEWLINE, "\n"));
        }

        while (pilaIndentacion.size() > 1) {
            pilaIndentacion.pop();
            tokensPendientes.add(crearToken(tokenEOF, YLenguajeLexer.DEDENT, ""));
        }

        tokensPendientes.add(tokenEOF);
        return tokensPendientes.remove(0);
    }

    // crear un token sintetico (INDENT/DEDENT/NEWLINE) copiando la posicion
    // del token de referencia, para que los mensajes de error apunten al
    // lugar correcto
    private Token crearToken(Token referencia, int tipo, String texto) {
        CommonToken nuevo = new CommonToken(referencia);
        nuevo.setType(tipo);
        nuevo.setText(texto);
        nuevo.setChannel(Token.DEFAULT_CHANNEL);
        return nuevo;
    }

    // delegar el resto de la interfaz TokenSource al lexer original
    @Override
    public int getLine() {
        return lexerDelegado.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return lexerDelegado.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return lexerDelegado.getInputStream();
    }

    @Override
    public String getSourceName() {
        return lexerDelegado.getSourceName();
    }

    @Override
    public void setTokenFactory(TokenFactory<?> factory) {
        lexerDelegado.setTokenFactory(factory);
    }

    @Override
    public TokenFactory<?> getTokenFactory() {
        return lexerDelegado.getTokenFactory();
    }
}