/**************************************************************************
* 
* Created on  : 18-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.service.impl
* File Name   : Converter.java
* 
***************************************************************************/
package main.java.insert2Update.service.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import main.java.insert2Update.model.Column;
import main.java.insert2Update.model.Condition;
import main.java.insert2Update.model.Insert;
import main.java.insert2Update.model.Keyword;
import main.java.insert2Update.model.Statement;
import main.java.insert2Update.model.Target;
import main.java.insert2Update.model.Update;
import main.java.insert2Update.model.Value;
import main.java.insert2Update.service.ItemType;
import main.java.insert2Update.service.Scanner;

/**
 * The Class Converter.
 */
public class Converter {

	/** The reader. */
	private Reader reader;

	/** The scanner. */
	private Scanner scanner;

	/** The token. */
	private ItemType token;

	/** The where condition. */
	private Condition[] whereCondition;

	/**
	 * Instantiates a new core logic impl.
	 *
	 * @param reader
	 *            the reader
	 * @param whereCondition
	 *            the where condition
	 */
	public Converter(Reader reader, Condition[] whereCondition) {
		this.whereCondition = whereCondition;
		this.reader = reader;
		this.scanner = new ScannerImpl(reader);
	}

	/**
	 * Parses the.
	 *
	 * @param sourceClass
	 *            the source class
	 * @param targetClass
	 *            the target class
	 * @return the statement
	 * @throws IOException
	 */
	public Statement[] parse(Class<?> sourceClass, Class<?> targetClass) throws IOException {
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
	 * @throws IOException
	 */
	private Statement[] insert2Statement(Class<?> targetClass) throws IOException {
		// replace initial insert into keywords
		consumeInitialKeywords(reader, Insert.class);
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
	private Update[] insert2Update() {
		nextToken();
		Target target = target();
		Column[] cols = columns();
		checkKeywordByName(Keyword.VALUES.value());
		if (token == ItemType.SEMICOLON) {
			error("Syntax error: values not found.");
		}
		List<Update> returnStatement = new ArrayList<Update>(0);
		while (token != ItemType.SEMICOLON) {
			Value[] vals = values();
			Update update = new Update(target, cols, vals, whereCondition);
			returnStatement.add(update);
			if (token != ItemType.SEMICOLON) {
				expect(ItemType.COMMA);
			}
		}
		expect(ItemType.SEMICOLON);
		return returnStatement.toArray(new Update[0]);
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
			table = scanner.getInput().getSval().trim();
			expect(ItemType.TEXT);
		}

		if (token == ItemType.DOT) {
			expect(ItemType.DOT);

			if (token == ItemType.TEXT) {
				schema = table;
				table = scanner.getInput().getSval().trim();
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
			val = scanner.getInput().getSval().trim();
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

		// TODO gestire caratteri tt in possibile valore

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

		// TODO gestire virgolette apici in valori stringa

		String sval = "";
		if (token == ItemType.TEXT) {
			sval = scanner.getInput().getSval().trim();
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
			error("Syntax error: '" + scanner.getInput().getSval().trim() + "'.");
		}
		expect(ItemType.TEXT);
	}

	/**
	 * Consume initial keywords.
	 *
	 * @param reader
	 *            the reader
	 * @param inputClass
	 *            the input class
	 * @return the reader
	 * @throws IOException
	 */
	private void consumeInitialKeywords(Reader reader, Class<?> inputClass) throws IOException {
		String keywords = getKeywordsString(inputClass);
		int readChar = -1;
		int charIndex = 0;
		while ((readChar = reader.read()) != -1 && charIndex < keywords.length()) {
			if (readChar > ' ') {
				// white spaces are consumed
				if (Character.toUpperCase(keywords.charAt(charIndex)) == Character.toUpperCase(readChar)) {
					charIndex++;
				} else {
					error("Syntax error - expected keywords: " + Keyword.INSERT.value() + Keyword.SPACE.value()
							+ Keyword.INTO.value());
				}
			}
		}
	}

	/**
	 * Gets the keywords string.
	 *
	 * @param inputClass
	 *            the input class
	 * @return the keywords string
	 */
	private String getKeywordsString(Class<?> inputClass) {
		if (inputClass.equals(Insert.class)) {
			return Keyword.INSERT.value() + Keyword.INTO.value();
		}
		return null;
	}

	/**
	 * Expect.
	 *
	 * @param t
	 *            the t
	 */
	private void expect(ItemType t) {
		if (token != t)
			error("Syntax error - expected token Type: " + t);
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
