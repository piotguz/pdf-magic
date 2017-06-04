package org.pg.magic.pdf.operations;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public class UnProtectOperation extends SingleOutputOperation {

	private static final String FILE_EXTENSION = "file.extension";
	private static final String FILE_BASE_NAME = "file.base.name";
	private static final String FILE_PATH = "file.path";
	private static final String PASSWORD = "password";

	public UnProtectOperation(Properties config) throws PDFOperationException {
		super(config);
	}

	@Override
	public File execute(File inputFile) throws PDFOperationException {
		checkRequiredParameters(PASSWORD);
		checkInputFile(inputFile);
		
		try {
			PDDocument document = PDDocument.load(inputFile,config.getProperty(PASSWORD));
			
			document.setAllSecurityToBeRemoved(true);

			String filePath = config.getProperty(FILE_PATH, inputFile.getParent());
			String fileBaseName = config.getProperty(FILE_BASE_NAME, FilenameUtils.getBaseName(inputFile.getName()));
			String fileExtension = config.getProperty(FILE_EXTENSION,FilenameUtils.getExtension(inputFile.getName()));
			
			File outputFile = new File(String.format("%s/%s_unprotected.%s", filePath , fileBaseName, fileExtension));
			
			document.save(outputFile);
			document.close();
			
			return outputFile;
		} catch (IOException e) {
			throw new PDFOperationException(e);
		}

	}

}
