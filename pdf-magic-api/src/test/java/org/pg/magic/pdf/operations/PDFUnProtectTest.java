package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperationFactory.OperationTypes;
import org.pg.magic.pdf.SingleOuptutOperationFactory;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.exceptions.PDFConfigExcpetion;
import org.pg.magic.pdf.exceptions.PDFIncorrectFileType;
import org.testng.annotations.Test;

public class PDFUnProtectTest extends SimplePDF {
	
	@Test(expectedExceptions = {PDFConfigExcpetion.class})
	public void unprotectShouldFail() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple_protected.pdf");
		SingleOuptutOperationFactory f = new SingleOuptutOperationFactory();
		
		SingleOutputOperation o = f.getOperation(OperationTypes.PDF_UNPROTECT, null);
		
		File unprotectedPdf = o.execute(inputPdf);
		
		assertNotNull(unprotectedPdf);
		assertTrue(unprotectedPdf.exists());
	}
	
	@Test
	public void unprotect() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple_protected.pdf");
		SingleOuptutOperationFactory f = new SingleOuptutOperationFactory();
		
		Properties config = new Properties();
		config.setProperty("password", "test1");
		
		SingleOutputOperation o = f.getOperation(OperationTypes.PDF_UNPROTECT, config);
		
		File unprotectedPdf = o.execute(inputPdf);
		
		assertNotNull(unprotectedPdf);
		assertTrue(unprotectedPdf.exists());
	}

}
