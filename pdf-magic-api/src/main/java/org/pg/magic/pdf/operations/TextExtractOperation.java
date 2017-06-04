package org.pg.magic.pdf.operations;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public class TextExtractOperation extends SingleOutputOperation {
	
	public TextExtractOperation(Properties config) throws PDFOperationException {
		super(config);
	}

	@Override
	public File execute(File inputFile) throws PDFOperationException {
		checkInputFile(inputFile);
		
		try {
			PDDocument document = PDDocument.load(inputFile);
			PDFTextStripper stripper = new PDFTextStripper();
			
			log.info("Extracting plan text...");
			String text = stripper.getText(document);
			
			String filePath = config.getProperty("file.path", inputFile.getParent());
			String fileBaseName = config.getProperty("file.base.name", FilenameUtils.getBaseName(inputFile.getName()));
			String fileExtension = config.getProperty("file.extension","txt");
			
			File outputFile = new File(String.format("%s/%s.%s", filePath , fileBaseName, fileExtension));
			FileUtils.writeStringToFile(outputFile, text, config.getProperty("file.encoding","utf-8"));
			
			document.close();
			
			log.info(outputFile.toString());
			
			return outputFile;
		} catch (IOException e) {
			throw new PDFOperationException(e);
		}
	}

}
