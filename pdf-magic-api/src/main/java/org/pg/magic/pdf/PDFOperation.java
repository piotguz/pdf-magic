package org.pg.magic.pdf;

import java.io.File;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.pg.magic.pdf.exceptions.PDFConfigExcpetion;
import org.pg.magic.pdf.exceptions.PDFFileNotFound;
import org.pg.magic.pdf.exceptions.PDFIncorrectFileType;
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
		checkInputFile(inputFile, new String[] {});
	}

	protected void checkInputFile(File inputFile, String... expectedExtension) throws PDFOperationException {
		log.info("Checking input file");

		if (inputFile == null || !inputFile.exists())
			throw new PDFFileNotFound("File %s does not exist!", inputFile);

		if (!inputFile.canRead())
			throw new PDFFileNotFound("Cannot read file %s!", inputFile);

		if (expectedExtension.length != 0) {
			String extension = FilenameUtils.getExtension(inputFile.getName());

			String extensionsList = StringUtils.join(expectedExtension, ", ");
			if (!extensionsList.contains(extension)) {
				throw new PDFIncorrectFileType("%s file type is not supported! Allowed formats are: %s",
						inputFile.getName(), extensionsList);
			}
		}

		log.info(inputFile.getAbsolutePath());
	}

	protected void checkRequiredParameters(String... requiredConfigParams) throws PDFConfigExcpetion {
		for (String required : requiredConfigParams) {
			if (!config.containsKey(required))
				throw new PDFConfigExcpetion("%s property must be configured!", required);
		}
	}

	public enum Type {
		PDF_SPLIT, PDF_TEXT_EXTRACT, PDF_PROTECT, PDF_UNPROTECT;
	}
}
