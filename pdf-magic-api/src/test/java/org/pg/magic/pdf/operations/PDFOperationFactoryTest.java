package org.pg.magic.pdf.operations;

import java.io.File;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFFileNotFound;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

public class PDFOperationFactoryTest extends SimplePDF {

	@Test(expectedExceptions = { PDFFileNotFound.class })
	public void getOperationWithNullFile() throws PDFOperationException {
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation operation = factory.getOperation(PDFStandardOperationTypes.PDF_FROM_IMAGE, null);


		operation.execute(new File(""));
	}

}
