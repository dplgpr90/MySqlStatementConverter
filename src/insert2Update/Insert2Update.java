package insert2Update;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import service.Scanner;
import service.Type;
import service.impl.ScannerImpl;

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
			Scanner scanner = new ScannerImpl(r);

			int i = 0;
			while (true) {
				i++;
				Type t = scanner.nextToken();
				System.out.println(t);
				if (t.equals(Type.EOF) || i==40) {
					break;
				}

			}
		} else {
			System.err.println("USAGE - 2 parameters requested: [inputFile] [outputFile].");
		}

	}

}
