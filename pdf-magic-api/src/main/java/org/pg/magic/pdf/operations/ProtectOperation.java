package org.pg.magic.pdf.operations;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public class ProtectOperation extends SingleOutputOperation {
	Logger log = Logger.getLogger(getClass().getName());
	
	public ProtectOperation(Properties config) throws PDFOperationException {
		super(config);
	}

	@Override
	public File execute(File inputFile) throws PDFOperationException {
		log.warning(inputFile.getAbsolutePath());
		checkInputFile(inputFile);
		
		try {
			PDDocument document = PDDocument.load(inputFile);
			String ownerPassword = config.getProperty("owner.password", "default");
			String userPassword = config.getProperty("user.password", "default");
			AccessPermission permissions = new AccessPermission();
			
			log.info("Setting password...");
			document.protect(new StandardProtectionPolicy(ownerPassword, userPassword, permissions));
			
			String filePath = config.getProperty("file.path", inputFile.getParent());
			String fileBaseName = config.getProperty("file.base.name", FilenameUtils.getBaseName(inputFile.getName()));
			String fileExtension = config.getProperty("file.extension",FilenameUtils.getExtension(inputFile.getName()));
			
			File outputFile = new File(String.format("%s/%s_protected.%s", filePath , fileBaseName, fileExtension));
			
			log.info("Saving new pdf...");
			document.save(outputFile);
			document.close();
			
			return outputFile;
		} catch (IOException e) {
			throw new PDFOperationException(e);
		}
	}

}
