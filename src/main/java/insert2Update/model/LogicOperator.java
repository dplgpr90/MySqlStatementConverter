/**************************************************************************
* 
* Created on  : 18-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : LogicOperator.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Enum LogicOperator.
 */
public enum LogicOperator {

	/** The into. */
	AND("AND"),
	/** The space. */
	OR("OR");

	/** The value. */
	private final String value;

	/**
	 * Instantiates a new keyword.
	 *
	 * @param value
	 *            the value
	 */
	private LogicOperator(String value) {
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
