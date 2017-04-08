package main.java.insert2Update.service.impl;

import java.io.IOException;
import java.io.Reader;

import main.java.insert2Update.service.Scanner;
import main.java.insert2Update.service.Tokenizer;
import main.java.insert2Update.service.ItemType;

public class ScannerImpl implements Scanner {
	
	public Tokenizer input;

	public ScannerImpl(Reader r) {
		input = new TokenizerImpl(r);
	}

	@Override
	public ItemType nextToken() {
		try {
			switch (input.nextToken()) {
			case TT_EOF:
				return ItemType.EOF;
			case TT_TEXT:
				return ItemType.TEXT;
			case TT_CLOSED_BRACKET:
				return ItemType.CLOSED_BRACKET;
			case TT_OPEN_BRACKET:
				return ItemType.OPEN_BRACKET;
			case TT_COMMA:
				return ItemType.COMMA;
			case TT_NULL:
				return ItemType.NULL;
			case TT_SEMICOLON:
				return ItemType.SEMICOLON;
			case TT_DOT:
				return ItemType.DOT;
			case TT_APEX:
				return ItemType.APEX;
			case TT_QUOTES:
				return ItemType.QUOTES;
			default:
				return ItemType.OTHER;
			}
		} catch (IOException ex) {
			return ItemType.EOF;
		}
	}

	@Override
	public Tokenizer getInput() {
		return input;
	}
}
