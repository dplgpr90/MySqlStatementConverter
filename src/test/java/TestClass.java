/**************************************************************************
* 
* Created on  : 24-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : test.java
* File Name   : TestClass.java
* 
***************************************************************************/
package test.java;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import main.java.insert2Update.executor.Executor;
import main.java.insert2Update.model.Column;
import main.java.insert2Update.model.Condition;
import main.java.insert2Update.model.Operator;

/**
 * The Class TestClass.
 */
public class TestClass {
	/** The test resource path. */
	private static final String testResourcePath = Executor.class.getProtectionDomain().getCodeSource().getLocation()
			.getPath()
			.substring(0, Executor.class.getProtectionDomain().getCodeSource().getLocation().getPath().length() - 4)
			+ "\\src\\test\\resources\\";

	/** The input file. */
	private static final String inputFile = testResourcePath + "insert.sql";

	/** The output file. */
	private static final String outputFile = testResourcePath + "converted_" + Calendar.getInstance().getTimeInMillis()
			+ ".sql";

	/** The Constant listaWhere. */
	private static final List<Condition> listaWhere = Arrays
			.asList(new Condition(new Column("ID_DOCUMENTO"), Operator.EQUALS, null));

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		Executor e = new Executor();
		e.execute(inputFile, outputFile, listaWhere);
	}

}
