/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Update.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Class Update.
 */
public class Update implements Statement {

	/** The cols. */
	public Column[] cols;

	/** The vals. */
	public Value[] vals;

	/** The target. */
	public Target target;

	/**
	 * Instantiates a new update.
	 *
	 * @param target
	 *            the target
	 * @param cols
	 *            the cols
	 * @param vals
	 *            the vals
	 */
	public Update(Target target, Column[] cols, Value[] vals) {
		this.target = target;
		this.cols = cols;
		this.vals = vals;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = UPDATE + " " + target + SET + " ";
		for (int i = 0; i < cols.length; i++) {
			if (i > 0) {
				res += ", ";
			}
			res += cols[i] + " = " + vals[i];
		}
		res += ";";
		return res;
	}
}