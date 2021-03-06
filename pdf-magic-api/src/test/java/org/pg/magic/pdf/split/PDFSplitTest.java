package org.pg.magic.pdf.split;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.operations.MultipleOutputsOperation;
import org.pg.magic.pdf.operations.MultipleOutputsOperation.Type;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.SimplePDF;
import org.testng.annotations.Test;

@Test
public class PDFSplitTest extends SimplePDF {
	public void split() throws PDFOperationException, IOException {
		loadSamplePDF("/TestPDF_multipage.pdf");
		PDFOperationFactory f = new PDFOperationFactory();
		MultipleOutputsOperation o = f.getOperation(Type.PDF_SPLIT, null);
		
		List<File> results = o.execute(inputPdf);
		
		assertNotNull(results);
		assertNotEquals(results.size(), 0);
		assertEquals(results.size(), 4);
	}

}
