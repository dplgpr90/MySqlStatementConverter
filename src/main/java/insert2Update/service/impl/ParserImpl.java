/**************************************************************************
* 
* Created on  : 18-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : main.java.insert2Update.service.impl
* File Name   : ParserImpl.java
* 
***************************************************************************/
package main.java.insert2Update.service.impl;

import java.io.IOException;
import java.io.Reader;

import main.java.insert2Update.model.Condition;
import main.java.insert2Update.model.Insert;
import main.java.insert2Update.model.Statement;
import main.java.insert2Update.model.Update;
import main.java.insert2Update.service.Parser;

/**
 * The Class ParserImpl.
 */
public class ParserImpl implements Parser {

	/*
	 * STATEMENT ::= INSERT | *add here grammar extensions*
	 * 
	 * INSERT ::= insert into TARGET (COLUMNS) values VALUES_LIST; TARGET ::=
	 * SCHEMA . TABLE | TABLE COLUMNS ::= COLUMN | COLUMN , COLUMNS VALUES_LIST
	 * ::= ( VALUES ) | ( VALUES ) , VALUES_LIST VALUES ::= VALUE | VALUE ,
	 * VALUES COLUMN ::= stringa di testo TABLE ::= stringa di testo SCHEMA ::=
	 * stringa di testo VALUE ::= stringa di testo | stringa di testo () |
	 * "stringa di testo" | 'stringa di testo'
	 * 
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see main.java.insert2Update.service.Parser#insert2Update(java.io.Reader,
	 * main.java.insert2Update.model.Condition[])
	 */
	@Override
	public Statement[] insert2Update(Reader reader, Condition[] whereCondition) throws IOException {
		Converter c = new Converter(reader, whereCondition);
		return c.parse(Insert.class, Update.class);
	}

}
