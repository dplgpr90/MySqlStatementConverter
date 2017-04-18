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
public class Value<T> {

	/** The val. */
	public T value;

	/**
	 * Instantiates a new value.
	 *
	 * @param value
	 *            the value
	 */
	public Value(T value) {
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
		return Keyword.PERCENTAGE.value() + this.value.toString() + Keyword.PERCENTAGE.value();
	}
}