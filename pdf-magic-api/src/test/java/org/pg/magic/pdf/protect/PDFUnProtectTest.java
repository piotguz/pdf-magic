package org.pg.magic.pdf.protect;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.SimplePDF;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.exceptions.PDFPasswordRequiredException;
import org.pg.magic.pdf.operations.SingleOutputOperation;
import org.pg.magic.pdf.operations.SingleOutputOperation.Type;
import org.testng.annotations.Test;

public class PDFUnProtectTest extends SimplePDF {
	
	@Test(expectedExceptions = {PDFPasswordRequiredException.class})
	public void unprotectShouldFail() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple_protected.pdf");
		PDFOperationFactory f = new PDFOperationFactory();
		
		SingleOutputOperation o = f.getOperation(Type.PDF_UNPROTECT, null);
		
		File unprotectedPdf = o.execute(inputPdf);
		
		assertNotNull(unprotectedPdf);
		assertTrue(unprotectedPdf.exists());
	}
	
	@Test
	public void unprotect() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple_protected.pdf");
		PDFOperationFactory f = new PDFOperationFactory();
		
		Properties config = new Properties();
		config.setProperty("password", "test1");
		
		SingleOutputOperation o = f.getOperation(Type.PDF_UNPROTECT, config);
		
		File unprotectedPdf = o.execute(inputPdf);
		
		assertNotNull(unprotectedPdf);
		assertTrue(unprotectedPdf.exists());
	}

}
