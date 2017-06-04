package org.pg.magic.pdf.operations;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFConfigExcpetion;
import org.pg.magic.pdf.exceptions.PDFIncorrectFileType;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PDFMergeOperationTest extends ListOfPDFs {
	Properties correctConfig = new Properties();

	@BeforeTest
	public void initConfig() throws IOException {
		String tmpDir = System.getProperty("java.io.tmpdir");

		correctConfig.setProperty("output.folder", tmpDir);
		correctConfig.setProperty("output.file.name", "merged.pdf");

		correctConfig.store(System.out, "correctConfig");
	}

	@Test(expectedExceptions = { PDFConfigExcpetion.class })
	public void mergeMissingConfig() throws PDFOperationException, UnsupportedEncodingException {
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_MERGE, null);

		loadSamplePDFs("/mergeTest (1).pdf", "/mergeTest (2).pdf", "/mergeTest (3).pdf", "/mergeTest (4).pdf");
		o.execute(inputFileList);

	}

	@Test(expectedExceptions = { PDFOperationException.class, PDFIncorrectFileType.class })
	public void mergeIncorrectFiles() throws PDFOperationException, UnsupportedEncodingException {
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_MERGE, correctConfig);

		loadSamplePDFs("/mergeTest.docx", "/mergeTest (2).pdf", "/mergeTest (3).pdf", "/mergeTest (4).pdf");

		o.execute(inputFileList);
	}

	@Test
	public void merge() throws PDFOperationException, UnsupportedEncodingException {
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(PDFStandardOperationTypes.PDF_MERGE, correctConfig);

		loadSamplePDFs("/mergeTest (1).pdf", "/mergeTest (2).pdf", "/mergeTest (3).pdf", "/mergeTest (4).pdf");

		o.execute(inputFileList);
		File mergedPDF = o.getOutputFile();
		assertNotNull(mergedPDF);
		assertTrue(mergedPDF.exists());

	}
}
