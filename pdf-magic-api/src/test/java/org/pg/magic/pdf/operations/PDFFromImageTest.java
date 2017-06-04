package org.pg.magic.pdf.operations;

import java.io.IOException;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

public class PDFFromImageTest extends SimplePDF {
	@Test
	public void fromImage() throws PDFOperationException, IOException {
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_FROM_IMAGE, null);
		
		loadSamplePDF("/Image01.jpg");
		
		o.execute(inputPdf);
	}
}
