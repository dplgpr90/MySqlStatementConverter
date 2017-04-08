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
import main.java.insert2Update.service.Scanner;
import main.java.insert2Update.service.ItemType;

public class CoreLogicImpl {

	private Scanner scanner;
	private ItemType token;

	public CoreLogicImpl(Reader r) {
		scanner = new ScannerImpl(r);
	}

	public Statement parse(Class<?> sourceClass, Class<?> targetClass) {
		if (sourceClass.equals(Insert.class)) {
			return insert2Statement(targetClass);
		}
		return null;
	}

	private Statement insert2Statement(Class<?> targetClass) {
		if (targetClass.equals(Update.class)) {
			return insert2Update();
		}
		return null;
	}

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

	private Column column() {
		String val = "";
		if (token == ItemType.TEXT) {
			val = scanner.getInput().getSval();
			expect(ItemType.TEXT);
		}
		Column col = new Column(val);
		return col;
	}

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

	private Value value() {
		String sval = "";
		if (token == ItemType.TEXT) {
			sval = scanner.getInput().getSval();
			expect(ItemType.TEXT);
		}
		Value val = new Value(sval);
		return val;
	}

	private void checkKeywordByName(String keyword) {
		if (!keyword.equalsIgnoreCase(scanner.getInput().getSval().trim())) {
			error("Syntax error: '" + scanner.getInput().getSval() + "'.");
		}
		expect(ItemType.TEXT);
	}

	private void expect(ItemType t) {
		if (token != t)
			error("Syntax error: expected token Type." + t);
		nextToken();
	}

	private void nextToken() {
		token = scanner.nextToken();
	}

	private void error(String msg) {
		System.err.println(msg);
		System.exit(1);
	}

}
