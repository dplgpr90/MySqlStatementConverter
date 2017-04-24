/**************************************************************************
* 
* Created on  : 24-apr-2017  
* Author      : Giampiero Di Paolo
* Project Name: Insert2Update  
* Package     : test.java
* File Name   : Insert2Update.java
* 
***************************************************************************/
package main.java.insert2Update.executor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import main.java.insert2Update.model.Condition;
import main.java.insert2Update.model.Statement;
import main.java.insert2Update.service.impl.ParserImpl;

/**
 * The Class Insert2Update.
 */
public class Executor {

	/**
	 * Execute.
	 *
	 * @param inputFile
	 *            the input file
	 * @param outputFile
	 *            the output file
	 * @param listaWhere
	 *            the lista where
	 */
	public void execute(String inputFile, String outputFile, List<Condition> listaWhere) {
		Reader reader = null;
		try {
			reader = new FileReader(inputFile);
		} catch (FileNotFoundException fnfe) {
			System.err.println("CONVERTER ERROR! Cannot find input file " + inputFile);
			System.exit(1);
		}

		Statement[] resultStatements = new Statement[0];
		try {
			Condition[] whereCondition = listaWhere.toArray(new Condition[0]);
			resultStatements = new ParserImpl().insert2Update(reader, whereCondition);
		} catch (IOException ioe) {
			System.err.println("CONVERTER ERROR! Unexpected error during coversion.");
			System.exit(1);
		} finally {
			try {
				reader.close();
			} catch (IOException ioe) {
				System.err.println("CONVERTER ERROR! Cannot close file " + inputFile);
				System.exit(1);
			}
		}

		Writer writer = null;
		try {
			writer = new FileWriter(outputFile);
		} catch (IOException ioe) {
			System.err.println("CONVERTER ERROR! Cannot create output file " + outputFile);
			System.exit(1);
		}

		for (Statement statement : resultStatements) {
			try {
				writer.write(statement.toString());
			} catch (IOException e) {
				System.err.println("CONVERTER ERROR! Cannot write output file " + outputFile);
				System.exit(1);
			}
		}

		try {
			writer.close();
		} catch (IOException ioe) {
			System.err.println("CONVERTER ERROR! Cannot close file " + outputFile);
			System.exit(1);
		}

		System.out.println("CONVERTER SUCCESS!");
	}

}
