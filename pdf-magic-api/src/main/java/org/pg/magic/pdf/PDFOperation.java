package org.pg.magic.pdf;

import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;

import org.pg.magic.pdf.exceptions.PDFFileNotFound;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public abstract class PDFOperation<OutputType, InputType> {
	protected Logger log = Logger.getLogger(getClass().getName());
	protected Properties config = new Properties();
	
	protected PDFOperation(Properties config) throws PDFOperationException {
		if (config == null)
			this.config = new Properties();
		else
			this.config = config;
	}
	
	public abstract OutputType execute(InputType input) throws PDFOperationException;

	protected void checkInputFile(File inputFile) throws PDFOperationException {
		log.info("Checking input file");
		
		if (inputFile == null || !inputFile.exists())
			throw new PDFFileNotFound("File %s does not exist!", inputFile);
		
		if (!inputFile.canRead())
			throw new PDFFileNotFound("Cannot read file %s!", inputFile);
		
		log.info(inputFile.getAbsolutePath());
	}

}
