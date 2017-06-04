package org.pg.magic.pdf.operations;

import java.io.IOException;

import org.pg.magic.pdf.PDFOperationFactory.OperationTypes;
import org.pg.magic.pdf.SingleOuptutOperationFactory;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

public class PDFFromImageTest extends SimplePDF {
	@Test
	public void fromImage() throws PDFOperationException, IOException {
		SingleOuptutOperationFactory f = new SingleOuptutOperationFactory();
		SingleOutputOperation o = f.getOperation(OperationTypes.PDF_FROM_IMAGE, null);
		
		loadSamplePDF("/Image01.jpg");
		
		o.execute(inputPdf);
	}
}
