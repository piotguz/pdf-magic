package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.pg.magic.pdf.PDFOperationFactory.OperationTypes;
import org.pg.magic.pdf.SingleOuptutOperationFactory;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;


public class PDFProtectTest extends SimplePDF {
	
	@Test
	public void protect() throws PDFOperationException, IOException {
		loadSamplePDF("/Simple.pdf");
		
		SingleOuptutOperationFactory f = new SingleOuptutOperationFactory();
		
		Properties passwordConfig = new Properties();
		passwordConfig.setProperty("owner.password", "test1");
		passwordConfig.setProperty("user.password", "test2");
		
		SingleOutputOperation o = f.getOperation(OperationTypes.PDF_PROTECT, passwordConfig);
		
		Logger log = Logger.getLogger("TEST");
		
		log.warning(inputPdf.getAbsolutePath());
		
		File protectedPdf = o.execute(inputPdf);
		
		assertNotNull(protectedPdf);
		assertTrue(protectedPdf.exists());
	}

}
