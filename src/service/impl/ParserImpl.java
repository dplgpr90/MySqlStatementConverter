package service.impl;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import model.Row;
import model.Table;
import model.TableData;
import service.Parser;
import service.Scanner;
import service.Type;

public class ParserImpl implements Parser {
	
	/* 
	 * STATEMENT ::= INSERT |  *add here grammar extensions*
	 * 
	 * INSERT 		::= insert into TARGET (COLUMNS) values VALUES_LIST;
	 * TARGET		::= SCHEMA . TABLE | TABLE
	 * COLUMNS 		::= COLUMN | COLUMN , COLUMNS
	 * VALUES_LIST 	::= ( VALUES ) | ( VALUES ) , VALUES_LIST
	 * VALUES 		::= VALUE | VALUE , VALUES
	 * COLUMN 		::= stringa di testo
	 * TABLE 		::= stringa di testo
	 * SCHEMA 		::= stringa di testo
	 * VALUE 		::= stringa di testo
	 */
	
	private Scanner scanner;
	private Type token;
	
	/* (non-Javadoc)
	 * @see Service.ServiceImpl.Parser#parse(java.io.Reader)
	 */
	@Override
	public Table parse(Reader r) {
		scanner = new ScannerImpl(r);
		return  htmlFile();
	}

	private Table htmlFile() {
		nextToken();
		checkTagByName("html");
		Table table = body();
		checkTagByName("/html");
		return table;
	}
	
	private Table body(){
		checkTagByName("body");
		Table table = table();
		checkTagByName("/body");
		return table;
	}
	
	private Table table(){
		checkTagByName("table");
		Table table = new Table(rows());
		checkTagByName("/table");
		return table;
	}
	
	private Row[] rows(){
		List<Row> rows = new ArrayList<Row>();
		while(token==Type.TAG && scanner.input.sval.equals("tr"))
			rows.add(row());
		return rows.toArray(new Row[0]);
	}
	
	private Row row(){		
		expect(Type.TAG);
		Row row = new Row(tableData());
		checkTagByName("/tr");
		return row;
	}
	
	private TableData[] tableData(){
		List<TableData> tds = new ArrayList<TableData>();
		while(token==Type.TAG && scanner.input.sval.equals("td"))
			tds.add(td());
		return tds.toArray(new TableData[0]);
	}
	
	private TableData td(){			
		expect(Type.TAG);
		String val = "";
		if(token == Type.TEXT){
			val = scanner.input.sval;
			expect(Type.TEXT);
		}
		TableData td = new TableData(val);
		checkTagByName("/td");
		return td;
	}
	
	private void checkTagByName(String tagName){
		if(!tagName.equals(scanner.input.sval)){
			String options = "";
			if(tagName.equals("/table"))
				options = " or '<tr>'";
			else if(tagName.equals("/tr"))
				options = " or '<td>'";
			error("Syntax error: '" + scanner.input.sval + "'. Expected " + "'<" + tagName + ">'" + options + ".");			
		}
		expect(Type.TAG);
	}
	
	private void expect(Type t) {
		if (token != t)
			error("Syntax error: expected token Type." + t);
		nextToken();
	}

	private void nextToken() {
		token = scanner.nextToken();
	}
	
	private void error(String msg){
		System.err.println(msg);
		System.exit(1);
	}
		
}
