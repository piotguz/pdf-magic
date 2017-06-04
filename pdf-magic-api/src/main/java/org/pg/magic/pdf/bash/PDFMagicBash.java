package org.pg.magic.pdf.bash;

import java.util.ArrayList;

import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.OperationTypes;

import jline.console.ConsoleReader;
import jline.console.completer.FileNameCompleter;
import jline.console.completer.StringsCompleter;

public class PDFMagicBash {
	//how to test? consider: http://www.nathanbak.com/?p=388
	public static void main(String[] args) {
		
		ArrayList<String> test = collectOperations();
		
		try {
			ConsoleReader reader = new ConsoleReader();
			reader.setPrompt("pdf-magic> ");
			reader.addCompleter(new StringsCompleter(test));
			reader.addCompleter(new FileNameCompleter());

			String line;

			while ((line = reader.readLine()) != null) {
				if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
					break;
				}
				if (line.equalsIgnoreCase("cls")) {
					reader.clearScreen();
				}

				new PDFOperationParser(reader, line).parse();
				
				
			}
			
			reader.close();

		} catch (Exception e) {
			e.printStackTrace(System.err);
		} 
	}

	private static ArrayList<String> collectOperations() {
		ArrayList<String> autoComplete = new ArrayList<>();
		OperationTypes[] values = PDFOperationFactory.OperationTypes.values();
		
		for (OperationTypes type: values) {
			String name = type.name();
			toAutoComplete(autoComplete, name);
		}
		return autoComplete;
	}

	private static void toAutoComplete(ArrayList<String> autoComplete, String name) {
		name = name.replace("PDF_", "");
		name = name.toLowerCase();
		name = name.replace("_", "-");
		autoComplete.add(name);
	}
}
