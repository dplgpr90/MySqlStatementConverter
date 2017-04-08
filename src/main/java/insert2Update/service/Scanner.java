/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.service
* File Name   : Scanner.java
* 
***************************************************************************/
package main.java.insert2Update.service;

/**
 * The Interface Scanner.
 */
public interface Scanner {

	/**
	 * Next token.
	 *
	 * @return the item type
	 */
	public abstract ItemType nextToken();

	/**
	 * Gets the input.
	 *
	 * @return the input
	 */
	public abstract Tokenizer getInput();

}
