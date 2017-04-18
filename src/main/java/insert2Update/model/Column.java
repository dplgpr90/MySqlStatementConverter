/**************************************************************************
* 
* Created on  : 18-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Column.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Class Column.
 */
public class Column {

	/** The name. */
	public String name;

	/**
	 * Instantiates a new column.
	 *
	 * @param name
	 *            the name
	 */
	public Column(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}

	/**
	 * Equals.
	 *
	 * @param column
	 *            the column
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object column) {
		if (column == null) {
			return false;
		}
		if (column instanceof Column) {
			return ((Column) column).name.equalsIgnoreCase(this.name);
		}
		return false;
	}
}