package org.pg.magic.pdf.operations;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.exceptions.PDFPasswordRequiredException;

public class UnProtectOperation extends SingleOutputOperation {

	public UnProtectOperation(Properties config) throws PDFOperationException {
		super(config);
	}

	@Override
	public File execute(File inputFile) throws PDFOperationException {
		checkInputFile(inputFile);
		
		if (!config.containsKey("password")) {
			throw new PDFPasswordRequiredException("This operation requires [password] parameter to be set in config!");
		}
		try {
			PDDocument document = PDDocument.load(inputFile,config.getProperty("password"));
			
			document.setAllSecurityToBeRemoved(true);

			String filePath = config.getProperty("file.path", inputFile.getParent());
			String fileBaseName = config.getProperty("file.base.name", FilenameUtils.getBaseName(inputFile.getName()));
			String fileExtension = config.getProperty("file.extension",FilenameUtils.getExtension(inputFile.getName()));
			
			File outputFile = new File(String.format("%s/%s_unprotected.%s", filePath , fileBaseName, fileExtension));
			
			document.save(outputFile);
			document.close();
			
			return outputFile;
		} catch (IOException e) {
			throw new PDFOperationException(e);
		}

	}

}
