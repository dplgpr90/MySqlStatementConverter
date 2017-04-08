/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Keyword.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Enum Keyword.
 */
public enum Keyword {

	/** The open bracket. */
	OPEN_BRACKET("("),
	/** The closed bracket. */
	CLOSED_BRACKET(")"),
	/** The semicolon. */
	SEMICOLON(";"),
	/** The comma. */
	COMMA(","),
	/** The update. */
	UPDATE("UPDATE"),
	/** The set. */
	SET("SET"),
	/** The insert. */
	INSERT("INSERT"),
	/** The into. */
	INTO("INTO"),
	/** The space. */
	SPACE(" "),
	/** The equals. */
	EQUALS("="),
	/** The dot. */
	DOT("."),
	/** The values. */
	VALUES("VALUES");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new keyword.
	 *
	 * @param value
	 *            the value
	 */
	private Keyword(String value) {
		this.value = value;
	}

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value() {
		return this.value;
	}
}
