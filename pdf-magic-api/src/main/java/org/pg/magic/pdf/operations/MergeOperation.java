package org.pg.magic.pdf.operations;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public class MergeOperation extends MultipleInputsOperation {

	private static final String OUTPUT_FILE_NAME = "output.file.name";
	private static final String OUTPUT_FOLDER = "output.folder";

	public MergeOperation(Properties config) throws PDFOperationException {
		super(config);
	}

	@Override
	public File execute(List<File> inputPdfFiles) throws PDFOperationException {
		checkRequiredParameters(OUTPUT_FOLDER, OUTPUT_FILE_NAME);
		
		PDDocument mergedPdf = new PDDocument();

		try {
			
			for (File inputPdf:inputPdfFiles) {
				checkInputFile(inputPdf, "pdf");
				
				PDDocument partPdf = PDDocument.load(inputPdf);
				PDPageTree pages = partPdf.getDocumentCatalog().getPages();
				
				pages.forEach(page -> mergedPdf.addPage(page));
				
//				partPdf.close();
			}
			
			String outputFolder = config.getProperty(OUTPUT_FOLDER);
			String outputFileName = config.getProperty(OUTPUT_FILE_NAME);
			File mergedPdfFile = new File(String.format("%s/%s", outputFolder, outputFileName));
			
			mergedPdf.save(mergedPdfFile);
			mergedPdf.close();
			return mergedPdfFile;
		} catch (Exception e) {
			throw new PDFOperationException(e);
		}

	}

}
