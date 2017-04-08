/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Insert.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Class Insert.
 */
public class Insert implements Statement {

	/** The cols. */
	public Column[] cols;

	/** The vals. */
	public Value[] vals;

	/** The target. */
	public Target target;

	/**
	 * Instantiates a new insert.
	 *
	 * @param target
	 *            the target
	 * @param cols
	 *            the cols
	 * @param vals
	 *            the vals
	 */
	public Insert(Target target, Column[] cols, Value[] vals) {
		this.target = target;
		this.cols = cols;
		this.vals = vals;
	}

}