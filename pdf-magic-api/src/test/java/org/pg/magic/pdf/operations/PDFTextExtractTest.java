package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

@Test
public class PDFTextExtractTest extends SimplePDF {
	
	public void extractText() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple.pdf");
		
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_EXTRACT_TEXT, null);

		
		o.execute(inputPdf);
		File textFile = o.getOutputFile();
		
		assertNotNull(textFile);
		assertTrue(textFile.exists());
	}

}
