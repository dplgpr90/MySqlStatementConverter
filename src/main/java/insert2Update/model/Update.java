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
		String res = Keyword.UPDATE.value() + Keyword.SPACE.value() + target + Keyword.SPACE.value()
				+ Keyword.SET.value() + Keyword.SPACE.value();
		for (int i = 0; i < cols.length; i++) {
			if (i > 0) {
				res += Keyword.COMMA.value() + Keyword.SPACE.value();
			}
			res += cols[i] + Keyword.SPACE.value() + Keyword.EQUALS.value() + Keyword.SPACE.value() + vals[i];
		}
		res += Keyword.SEMICOLON.value();
		return res;
	}
}