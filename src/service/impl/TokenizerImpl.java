package service.impl;

import java.io.IOException;
import java.io.Reader;

import service.Tokenizer;

public class TokenizerImpl implements Tokenizer {

	private static final char OPEN_BRACKET = '(';

	private static final char CLOSED_BRACKET = ')';

	private static final char SEMICOLON = ';';

	private static final char COMMA = ',';

	// stop-char for stopping reading text
	int[] stopChars = { COMMA, SEMICOLON, OPEN_BRACKET, CLOSED_BRACKET };

	// reader
	public Reader reader = null;

	// flag true if pushBack method has been invoked
	public boolean isPushBackRequired = false;

	// current token type
	public TokenType tokenType = TokenType.TT_NULL;

	// previous char
	public int previousChar;

	// current token string
	public String sval = null;

	// constructor
	public TokenizerImpl(Reader r) {
		reader = r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Service.MySQLServiceImpl.Tokenizer#pushBack()
	 */
	@Override
	public void pushBack() {
		if (tokenType != TokenType.TT_NULL)
			isPushBackRequired = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see Service.MySQLServiceImpl.Tokenizer#nextToken()
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

	private boolean isStoppingChar(int currentChar) {
		for (int i : stopChars) {
			if (i == currentChar)
				return true;
		}
		return false;
	}

}
