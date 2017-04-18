/**************************************************************************
* 
* Created on  : 18-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.model
* File Name   : Condition.java
* 
***************************************************************************/
package main.java.insert2Update.model;

/**
 * The Class Condition.
 */
public class Condition {

	/** The column. */
	public Column column;

	/** The operator. */
	public Operator operator;

	/** The logic operator. */
	public LogicOperator logicOperator;

	/**
	 * Instantiates a new condition.
	 *
	 * @param column
	 *            the column
	 * @param operator
	 *            the operator
	 * @param logicOperator
	 *            the logic operator
	 */
	public Condition(Column column, Operator operator, LogicOperator logicOperator) {
		this.column = column;
		this.operator = operator;
		this.logicOperator = logicOperator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String where = Keyword.SPACE.value();
		if (this.logicOperator != null) {
			where += this.logicOperator.value() + Keyword.SPACE.value();
		}
		return where + this.column.name + Keyword.SPACE.value() + this.operator.value();
	}

	/**
	 * Checks if is like.
	 *
	 * @return true, if is like
	 */
	public boolean isLike() {
		if (this.operator != null) {
			return this.operator.equals(Operator.LIKE);
		}
		return false;
	}
}