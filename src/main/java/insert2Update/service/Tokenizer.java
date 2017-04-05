package main.java.insert2Update.service;

import java.io.IOException;

import main.java.insert2Update.service.impl.TokenType;

public interface Tokenizer {

	// This method allows to come one token back
	// (i.e. the next call to nextToken() will return the same value)
	public void pushBack();

	// This method returns the type of the token read
	public TokenType nextToken() throws IOException;
	
	public abstract String getSval();

}