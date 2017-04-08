/**************************************************************************
* 
* Created on  : 8-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : test
* File Name   : Insert2Update.java
* 
***************************************************************************/
package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import main.java.insert2Update.model.Statement;
import main.java.insert2Update.service.Parser;
import main.java.insert2Update.service.impl.ParserImpl;

/**
 * The Class Insert2Update.
 */
public class Insert2Update {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws FileNotFoundException
	 *             the file not found exception
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// check args
		if (args.length == 2) {
			String inputFile = args[0];
			String outputFile = args[1];

			System.out.println(inputFile);
			System.out.println(outputFile);

			// TODO

			Reader r = new FileReader(inputFile);
			Parser p = new ParserImpl();

			Statement u = p.insert2Update(r);

			System.out.println(u);

		} else {
			System.err.println("USAGE - 2 parameters requested: [inputFile] [outputFile].");
		}

	}

}
