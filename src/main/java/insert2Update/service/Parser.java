/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.service
* File Name   : Parser.java
* 
***************************************************************************/
package main.java.insert2Update.service;

import java.io.IOException;
import java.io.Reader;

import main.java.insert2Update.model.Statement;

/**
 * The Interface Parser.
 */
public interface Parser {

	/**
	 * Insert 2 update.
	 *
	 * @param reader
	 *            the reader
	 * @return the statement[]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Statement[] insert2Update(Reader reader) throws IOException;

}