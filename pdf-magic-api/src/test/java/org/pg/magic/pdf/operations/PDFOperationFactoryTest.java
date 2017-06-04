package org.pg.magic.pdf.operations;

import org.pg.magic.pdf.PDFOperationFactory.OperationTypes;
import org.pg.magic.pdf.SingleOuptutOperationFactory;
import org.pg.magic.pdf.exceptions.PDFFileNotFound;
import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.testng.annotations.Test;

public class PDFOperationFactoryTest extends SimplePDF {

	@Test(expectedExceptions = { PDFFileNotFound.class })
	public void getOperationWithNullFile() throws PDFOperationException {
		SingleOuptutOperationFactory f = new SingleOuptutOperationFactory();
		SingleOutputOperation operation = f.getOperation(OperationTypes.PDF_EXTRACT_TEXT, null);

		operation.execute(null);
	}

}
