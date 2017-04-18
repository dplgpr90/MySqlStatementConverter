/**************************************************************************
* 
* Created on  : 18-apr-2017  
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
	@SuppressWarnings("rawtypes")
	public Value[] vals;

	/** The where condition. */
	public Condition[] whereCondition;

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
	 * @param whereCondition
	 *            the where condition
	 */
	@SuppressWarnings("rawtypes")
	public Update(Target target, Column[] cols, Value[] vals, Condition[] whereCondition) {
		this.target = target;
		this.cols = cols;
		this.vals = vals;
		this.whereCondition = whereCondition;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String res = Keyword.UPDATE.value() + Keyword.SPACE.value() + target + Keyword.SPACE.value()
				+ Keyword.SET.value() + Keyword.SPACE.value();
		String where = Keyword.SPACE.value() + Keyword.WHERE.value() + Keyword.SPACE.value();
		boolean isFirst = true;
		for (int i = 0; i < cols.length; i++) {
			Condition cond = getCondition(cols[i]);
			if (cond == null) {
				if (!isFirst) {
					res += Keyword.COMMA.value() + Keyword.SPACE.value();
				}
				res += cols[i] + Keyword.SPACE.value() + Keyword.EQUALS.value() + Keyword.SPACE.value() + vals[i];
				isFirst = false;
			} else {
				where += cond.toString() + Keyword.SPACE.value();
				String valToString = vals[i].toString();
				if (cond.isLike()) {
					valToString = vals[i].formatForLikeLeftRight();
				}
				where += valToString;
			}
		}
		res += where + Keyword.SEMICOLON.value();
		return res;
	}

	/**
	 * Gets the condition.
	 *
	 * @param column
	 *            the column
	 * @return the condition
	 */
	private Condition getCondition(Column column) {
		if (whereCondition != null) {
			for (Condition cond : whereCondition) {
				if (cond.column.equals(column)) {
					return cond;
				}
			}
		}
		return null;
	}
}