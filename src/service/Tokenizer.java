package service;

import java.io.IOException;

import service.impl.TokenType;

public interface Tokenizer {

	// This method allows to come one token back
	// (i.e. the next call to nextToken() will return the same value)
	public void pushBack();

	// This method returns the type of the token read
	public TokenType nextToken() throws IOException;

}