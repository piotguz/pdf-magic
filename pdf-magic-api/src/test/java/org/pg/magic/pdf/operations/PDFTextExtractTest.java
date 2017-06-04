package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.pg.magic.pdf.PDFOperationFactory.OperationTypes;
import org.pg.magic.pdf.SingleOuptutOperationFactory;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

@Test
public class PDFTextExtractTest extends SimplePDF {
	
	public void extractText() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple.pdf");
		
		SingleOuptutOperationFactory f = new SingleOuptutOperationFactory();
		SingleOutputOperation o = f.getOperation(OperationTypes.PDF_EXTRACT_TEXT, null);
		
		File textFile = o.execute(inputPdf);
		
		assertNotNull(textFile);
		assertTrue(textFile.exists());
	}

}
