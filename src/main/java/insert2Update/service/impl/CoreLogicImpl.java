/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.service.impl
* File Name   : CoreLogicImpl.java
* 
***************************************************************************/
package main.java.insert2Update.service.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import main.java.insert2Update.model.Column;
import main.java.insert2Update.model.Insert;
import main.java.insert2Update.model.Statement;
import main.java.insert2Update.model.Target;
import main.java.insert2Update.model.Update;
import main.java.insert2Update.model.Value;
import main.java.insert2Update.service.ItemType;
import main.java.insert2Update.service.Scanner;

/**
 * The Class CoreLogicImpl.
 */
public class CoreLogicImpl {

	/** The scanner. */
	private Scanner scanner;

	/** The token. */
	private ItemType token;

	/**
	 * Instantiates a new core logic impl.
	 *
	 * @param reader
	 *            the reader
	 */
	public CoreLogicImpl(Reader reader) {
		scanner = new ScannerImpl(reader);
	}

	/**
	 * Parses the.
	 *
	 * @param sourceClass
	 *            the source class
	 * @param targetClass
	 *            the target class
	 * @return the statement
	 */
	public Statement parse(Class<?> sourceClass, Class<?> targetClass) {
		if (sourceClass.equals(Insert.class)) {
			return insert2Statement(targetClass);
		}
		return null;
	}

	/**
	 * Insert 2 statement.
	 *
	 * @param targetClass
	 *            the target class
	 * @return the statement
	 */
	private Statement insert2Statement(Class<?> targetClass) {
		if (targetClass.equals(Update.class)) {
			return insert2Update();
		}
		return null;
	}

	/**
	 * Insert 2 update.
	 *
	 * @return the update
	 */
	private Update insert2Update() {
		nextToken();
		// TODO replace insert into
		Target target = target();
		Column[] cols = columns();
		checkKeywordByName("values");

		// TODO gestire caratteri tt in possibile valore
		Value[] vals = values();

		Update table = new Update(target, cols, vals);
		expect(ItemType.SEMICOLON);
		return table;
	}

	/**
	 * Target.
	 *
	 * @return the target
	 */
	private Target target() {
		String schema = "";
		String table = "";

		if (token == ItemType.TEXT) {
			table = scanner.getInput().getSval();
			expect(ItemType.TEXT);
		}

		if (token == ItemType.DOT) {
			expect(ItemType.DOT);

			if (token == ItemType.TEXT) {
				schema = table;
				table = scanner.getInput().getSval();
				expect(ItemType.TEXT);
			}
		}

		Target target = new Target(schema, table);
		return target;
	}

	/**
	 * Columns.
	 *
	 * @return the column[]
	 */
	private Column[] columns() {
		List<Column> cols = new ArrayList<Column>();
		expect(ItemType.OPEN_BRACKET);
		while (token != ItemType.CLOSED_BRACKET) {
			cols.add(column());
			if (token != ItemType.CLOSED_BRACKET) {
				expect(ItemType.COMMA);
			}
		}
		expect(ItemType.CLOSED_BRACKET);
		return cols.toArray(new Column[0]);
	}

	/**
	 * Column.
	 *
	 * @return the column
	 */
	private Column column() {
		String val = "";
		if (token == ItemType.TEXT) {
			val = scanner.getInput().getSval();
			expect(ItemType.TEXT);
		}
		Column col = new Column(val);
		return col;
	}

	/**
	 * Values.
	 *
	 * @return the value[]
	 */
	private Value[] values() {
		List<Value> vals = new ArrayList<Value>();
		expect(ItemType.OPEN_BRACKET);
		while (token != ItemType.CLOSED_BRACKET) {
			vals.add(value());
			if (token != ItemType.CLOSED_BRACKET) {
				expect(ItemType.COMMA);
			}
		}
		expect(ItemType.CLOSED_BRACKET);
		return vals.toArray(new Value[0]);
	}

	/**
	 * Value.
	 *
	 * @return the value
	 */
	private Value value() {
		String sval = "";
		if (token == ItemType.TEXT) {
			sval = scanner.getInput().getSval();
			expect(ItemType.TEXT);
		}
		Value val = new Value(sval);
		return val;
	}

	/**
	 * Check keyword by name.
	 *
	 * @param keyword
	 *            the keyword
	 */
	private void checkKeywordByName(String keyword) {
		if (!keyword.equalsIgnoreCase(scanner.getInput().getSval().trim())) {
			error("Syntax error: '" + scanner.getInput().getSval() + "'.");
		}
		expect(ItemType.TEXT);
	}

	/**
	 * Expect.
	 *
	 * @param t
	 *            the t
	 */
	private void expect(ItemType t) {
		if (token != t)
			error("Syntax error: expected token Type." + t);
		nextToken();
	}

	/**
	 * Next token.
	 */
	private void nextToken() {
		token = scanner.nextToken();
	}

	/**
	 * Error.
	 *
	 * @param msg
	 *            the msg
	 */
	private void error(String msg) {
		System.err.println(msg);
		System.exit(1);
	}

}
