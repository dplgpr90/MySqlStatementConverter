/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Target.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Class Target.
 */
public class Target {

	/** The schema. */
	public String schema;

	/** The table. */
	public String table;

	/**
	 * Instantiates a new target.
	 *
	 * @param schema
	 *            the schema
	 * @param table
	 *            the table
	 */
	public Target(String schema, String table) {
		this.schema = schema;
		this.table = table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (schema == null || schema.equals("")) {
			return table;
		}
		return schema + "." + table;
	}

}