package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

@Test
public class PDFSplitTest extends SimplePDF {
	public void split() throws PDFOperationException, IOException {
		loadSamplePDF("/TestPDF_multipage.pdf");
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_SPLIT, null);

		
		o.execute(inputPdf);
		List<File> results = o.getOutputFiles();
		
		assertNotNull(results);
		assertNotEquals(results.size(), 0);
		assertEquals(results.size(), 4);
	}

}
