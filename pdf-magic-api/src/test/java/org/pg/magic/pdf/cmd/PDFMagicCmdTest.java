package org.pg.magic.pdf.cmd;

import java.io.IOException;

import org.pg.magic.pdf.operations.SimplePDF;
import org.testng.annotations.Test;

public class PDFMagicCmdTest extends SimplePDF {
	@Test
	public void testRun1() throws IOException {
		loadSamplePDF("/Simple.pdf");
		PDFMagicCmd.main("-op","PDF_EXTRACT_TEXT","-i", inputPdf.getAbsolutePath());
	}
	
	@Test
	public void testRun2() throws IOException {
		loadSamplePDF("/Simple.pdf");
		PDFMagicCmd.main("-op","OOPS");
	}
}
