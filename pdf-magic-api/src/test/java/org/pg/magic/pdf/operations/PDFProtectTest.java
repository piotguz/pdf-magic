package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;


public class PDFProtectTest extends SimplePDF {
	
	@Test
	public void protect() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple.pdf");
		
		Properties passwordConfig = new Properties();
		passwordConfig.setProperty("owner.password", "test1");
		passwordConfig.setProperty("user.password", "test2");
		
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_PROTECT, passwordConfig);

		
		Logger log = Logger.getLogger("TEST");
		
		log.warning(inputPdf.getAbsolutePath());
		
		o.execute(inputPdf);
		File protectedPdf = o.getOutputFile();
		
		assertNotNull(protectedPdf);
		assertTrue(protectedPdf.exists());
	}

}
