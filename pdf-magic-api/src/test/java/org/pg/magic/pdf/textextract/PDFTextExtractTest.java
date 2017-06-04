package org.pg.magic.pdf.textextract;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.SimplePDF;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.operations.SingleOutputOperation;
import org.pg.magic.pdf.operations.SingleOutputOperation.Type;
import org.testng.annotations.Test;

@Test
public class PDFTextExtractTest extends SimplePDF {
	
	public void extractText() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple.pdf");
		
		PDFOperationFactory f = new PDFOperationFactory();
		SingleOutputOperation o = f.getOperation(Type.PDF_TEXT_EXTRACT, null);
		
		File textFile = o.execute(inputPdf);
		
		assertNotNull(textFile);
		assertTrue(textFile.exists());
	}

}
