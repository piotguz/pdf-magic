package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class SimplePDF {
	protected File inputPdf;
	
	protected void loadSamplePDF(String pdfFile) throws IOException {
		loadSamplePDF(pdfFile, false);
	}
	protected void loadSamplePDF(String pdfFile, boolean skipAsserts) throws IOException {
		URL inputPDFUrl = getClass().getResource(pdfFile);

		if (!skipAsserts)
			assertNotNull(inputPDFUrl, "Looks like test pdf does not exist :-(");
		
		inputPdf = new File(inputPDFUrl.getFile());

		if (!skipAsserts) {			
			assertTrue(inputPdf.exists());
			assertTrue(inputPdf.canRead());
		}
	}

}
