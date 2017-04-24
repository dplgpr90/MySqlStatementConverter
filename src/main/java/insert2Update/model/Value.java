/**************************************************************************
* 
* Created on  : 18-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Value.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Class Value.
 */
public class Value {

	/** The val. */
	public String value;

	/**
	 * Instantiates a new value.
	 *
	 * @param value
	 *            the value
	 */
	public Value(String value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return value.toString();
	}

	/**
	 * Format for like left right.
	 *
	 * @return the string
	 */
	public String formatForLikeLeftRight() {
		String string = this.value.toString().substring(1, this.value.toString().length() - 1);
		String firstChar = this.value.toString().substring(0, 1);
		String lastChar = this.value.toString().substring(this.value.toString().length() - 1,
				this.value.toString().length());
		return firstChar + Keyword.PERCENTAGE.value() + string + Keyword.PERCENTAGE.value() + lastChar;
	}
}