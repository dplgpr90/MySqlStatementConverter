package service.impl;

import java.io.IOException;
import java.io.Reader;

import service.Scanner;
import service.Tokenizer;
import service.Type;

public class ScannerImpl implements Scanner {
	
	public Tokenizer input;

	public ScannerImpl(Reader r) {
		input = new TokenizerImpl(r);
	}

	@Override
	public Type nextToken() {
		try {
			switch (input.nextToken()) {
			case TT_EOF:
				return Type.EOF;
			case TT_TEXT:
				return Type.TEXT;
			case TT_CLOSED_BRACKET:
				return Type.CLOSED_BRACKET;
			case TT_OPEN_BRACKET:
				return Type.OPEN_BRACKET;
			case TT_COMMA:
				return Type.COMMA;
			case TT_NULL:
				return Type.NULL;
			case TT_SEMICOLON:
				return Type.SEMICOLON;
			case TT_DOT:
				return Type.DOT;
			case TT_APICES:
				return Type.APICES;
			case TT_APICES2:
				return Type.APICES2;
			default:
				return Type.OTHER;
			}
		} catch (IOException ex) {
			return Type.EOF;
		}
	}

	@Override
	public Tokenizer getInput() {
		return input;
	}
}
