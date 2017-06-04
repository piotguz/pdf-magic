package org.pg.magic.pdf.operations;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public class FromImageOperation extends SingleOutputOperation {

	public FromImageOperation(Properties config) throws PDFOperationException {
		super(config);
	}

	@Override
	public File execute(File inputFile) throws PDFOperationException {
		checkInputFile(inputFile, "jpg", "jpeg", "png");

		try {
			
			PDDocument newPDF = new PDDocument();
			String outputFolder = config.getProperty("output.folder", inputFile.getParent());
			String outputFileName = config.getProperty("outpt.file.name", FilenameUtils.getBaseName(inputFile.getName()));
			
			PDImageXObject pdImage = PDImageXObject.createFromFile(inputFile.getAbsolutePath(), newPDF);
			PDPage page = new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight()));
			
			PDPageContentStream contentStream = new PDPageContentStream(newPDF, page);
			contentStream.drawImage(pdImage, 70, 250);

			contentStream.close();
			
			newPDF.addPage(page);
			
			File newPDFFile = new File(String.format("%s/%s.pdf", outputFolder, outputFileName));
			newPDF.save(newPDFFile );
			newPDF.close();
			
			return newPDFFile;
		} catch (Exception e) {
			throw new PDFOperationException(e);
		}
	}

}
