/**************************************************************************
* 
* Created on  : 18-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Operator.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Enum Operator.
 */
public enum Operator {

	/** The like. */
	LIKE("LIKE"),
	/** The equals. */
	EQUALS("="),
	/** The not equals. */
	NOT_EQUALS("!="),
	/** The greather than. */
	GREATHER_THAN(">"),
	/** The lesser than. */
	LESSER_THAN("<"),
	/** The greather than equals. */
	GREATHER_THAN_EQUALS(">="),
	/** The lesser than equals. */
	LESSER_THAN_EQUALS("<=");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new keyword.
	 *
	 * @param value
	 *            the value
	 */
	private Operator(String value) {
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
