package insert2Update;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import model.Statement;
import service.Parser;
import service.impl.ParserImpl;

/**
 * @author Giampiero Di Paolo
 *
 */
public class Insert2Update {

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
