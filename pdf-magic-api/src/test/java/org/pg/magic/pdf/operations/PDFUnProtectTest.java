package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFConfigExcpetion;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

public class PDFUnProtectTest extends SimplePDF {
	
	@Test(expectedExceptions = {PDFConfigExcpetion.class})
	public void unprotectShouldFail() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple_protected.pdf");
		
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_UNPROTECT, null);

		
		o.execute(inputPdf);
		File unprotectedPdf = o.getOutputFile();
		
		assertNotNull(unprotectedPdf);
		assertTrue(unprotectedPdf.exists());
	}
	
	@Test
	public void unprotect() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple_protected.pdf");
		Properties config = new Properties();
		config.setProperty("password", "test1");
		
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_UNPROTECT, config);
		
		o.execute(inputPdf);
		File unprotectedPdf = o.getOutputFile();
		
		assertNotNull(unprotectedPdf);
		assertTrue(unprotectedPdf.exists());
	}

}
