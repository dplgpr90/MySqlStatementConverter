/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.service
* File Name   : Tokenizer.java
* 
***************************************************************************/
package main.java.insert2Update.service;

import java.io.IOException;

import main.java.insert2Update.service.impl.TokenType;

/**
 * The Interface Tokenizer.
 *
 * @author Giampiero Di Paolo
 */
public interface Tokenizer {

	/**
	 * Gets the sval.
	 *
	 * @return the sval read
	 */
	public abstract String getSval();

	/**
	 * This method allows to come one token back (i.e. the next call to
	 * nextToken() will return the same value)
	 */
	public void pushBack();

	/**
	 * Next token.
	 *
	 * @return the type of the token read
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public TokenType nextToken() throws IOException;

}