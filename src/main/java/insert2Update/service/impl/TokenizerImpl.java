/**************************************************************************
*  
* Created on  : 8-apr-2017
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.service.impl
* File Name   : TokenizerImpl.java
* 
***************************************************************************/
package main.java.insert2Update.service.impl;

import java.io.IOException;
import java.io.Reader;

import main.java.insert2Update.service.Tokenizer;

/**
 * The Class TokenizerImpl.
 */
public class TokenizerImpl implements Tokenizer {

	/** The Constant OPEN_BRACKET. */
	private static final char OPEN_BRACKET = '(';

	/** The Constant CLOSED_BRACKET. */
	private static final char CLOSED_BRACKET = ')';

	/** The Constant SEMICOLON. */
	private static final char SEMICOLON = ';';

	/** The Constant COMMA. */
	private static final char COMMA = ',';

	/** The Constant DOT. */
	private static final char DOT = '.';

	/** The Constant APEX. */
	private static final char APEX = '\'';

	/** The Constant QUOTES. */
	private static final char QUOTES = '"';

	/** The Constant BACKSLASH. */
	private static final char BACKSLASH = '\\';

	/** The stop chars. stop-char for stopping reading text */
	int[] stopChars = { COMMA, SEMICOLON, OPEN_BRACKET, CLOSED_BRACKET, DOT, APEX, QUOTES, BACKSLASH };

	/** The reader. */
	public Reader reader = null;

	/** The sval. current token string. */
	public String sval = null;

	/**
	 * The is push back required. Flag true if pushBack method has been invoked
	 */
	public boolean isPushBackRequired = false;

	/** The token type. */
	public TokenType tokenType = TokenType.TT_NULL;

	/** The previous char. */
	public int previousChar;

	/**
	 * Instantiates a new tokenizer impl.
	 *
	 * @param reader
	 *            the reader
	 */
	public TokenizerImpl(Reader reader) {
		this.reader = reader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.insert2Update.service.Tokenizer#getSval()
	 */
	@Override
	public String getSval() {
		return sval;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.insert2Update.service.Tokenizer#pushBack()
	 */
	@Override
	public void pushBack() {
		if (tokenType != TokenType.TT_NULL)
			isPushBackRequired = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.insert2Update.service.Tokenizer#nextToken()
	 */
	@Override
	public TokenType nextToken() throws IOException {
		if (isPushBackRequired) {
			// token of the previous call must be returned

			// reset flag
			isPushBackRequired = false;

			return tokenType;
		}

		// current char
		int currentChar = -1;

		// set sval to undefined
		sval = null;

		if (tokenType != TokenType.TT_TEXT) {
			// previous call did not return text type
			currentChar = reader.read();
		} else {
			// set previous char as current
			currentChar = previousChar;
		}

		if (currentChar < 0) {
			// end of file reached
			tokenType = TokenType.TT_EOF;
			return tokenType;
		}

		while (currentChar <= ' ') {
			// ignore whitespace chars
			currentChar = reader.read();

			// read next char
			if (currentChar < 0) {
				// EOF reached
				tokenType = TokenType.TT_EOF;
				return tokenType;
			}
		}

		// set previous char as current
		char charsReadBuffer[] = new char[20];

		// chars read counter
		int i = 0;

		switch (currentChar) {
		case COMMA:
			tokenType = TokenType.TT_COMMA;
			break;
		case SEMICOLON:
			tokenType = TokenType.TT_SEMICOLON;
			break;
		case CLOSED_BRACKET:
			tokenType = TokenType.TT_CLOSED_BRACKET;
			break;
		case OPEN_BRACKET:
			tokenType = TokenType.TT_OPEN_BRACKET;
			break;
		case DOT:
			tokenType = TokenType.TT_DOT;
			break;
		case APEX:
			tokenType = TokenType.TT_APEX;
			break;
		case QUOTES:
			tokenType = TokenType.TT_QUOTES;
			break;
		case BACKSLASH:
			tokenType = TokenType.TT_BACKSLASH;
			break;
		default:
			// current char is not a syntax constant
			tokenType = TokenType.TT_TEXT;

			// insert current char into the buffer
			charsReadBuffer[i++] = (char) currentChar;

			// read next char
			currentChar = reader.read();

			while (currentChar > 0 && !isStoppingChar(currentChar)) {
				// continue reading chars while current char is not EOF neither
				// stop
				// char
				if ((currentChar == '\n') || (currentChar == '\r')) {
					// skip new lines
					currentChar = reader.read();
					continue;
				}
				if (i >= charsReadBuffer.length) {
					// buffer is full, extend it
					char newBuffer[] = new char[charsReadBuffer.length * 2];
					System.arraycopy(charsReadBuffer, 0, newBuffer, 0, charsReadBuffer.length);
					charsReadBuffer = newBuffer;
				}

				// insert current char into the buffer
				charsReadBuffer[i++] = (char) currentChar;
				currentChar = reader.read();
			}

			if (currentChar < 0) {
				// end of file reached
				tokenType = TokenType.TT_EOF;
				return tokenType;
			}

			// set previous char to current char
			previousChar = currentChar;

			// copy buffer content (chars read) into sval
			sval = String.copyValueOf(charsReadBuffer, 0, i);

			// remove leading and trailing whitespace
			sval.trim();
			break;
		}

		// return the type of the token read
		return tokenType;
	}

	/**
	 * Checks if is stopping char.
	 *
	 * @param currentChar
	 *            the current char
	 * @return true, if is stopping char
	 */
	private boolean isStoppingChar(int currentChar) {
		for (int i : stopChars) {
			if (i == currentChar)
				return true;
		}
		return false;
	}

}
