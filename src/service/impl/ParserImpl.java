package service.impl;

import java.io.Reader;

import model.Insert;
import model.Statement;
import service.Parser;

public class ParserImpl implements Parser {

	/*
	 * STATEMENT ::= INSERT | *add here grammar extensions*
	 * 
	 * INSERT ::= insert into TARGET (COLUMNS) values VALUES_LIST; TARGET ::=
	 * SCHEMA . TABLE | TABLE COLUMNS ::= COLUMN | COLUMN , COLUMNS VALUES_LIST
	 * ::= ( VALUES ) | ( VALUES ) , VALUES_LIST VALUES ::= VALUE | VALUE ,
	 * VALUES COLUMN ::= stringa di testo TABLE ::= stringa di testo SCHEMA ::=
	 * stringa di testo VALUE ::= stringa di testo
	 */

	@Override
	public Statement insert2Update(Reader r) {
		Helper h = new Helper(r);
		return h.parse(Insert.class);
	}

}
