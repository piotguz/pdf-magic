package org.pg.magic.pdf;

import java.util.Properties;

import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.operations.MultipleOutputsOperation;
import org.pg.magic.pdf.operations.ProtectOperation;
import org.pg.magic.pdf.operations.SingleOutputOperation;
import org.pg.magic.pdf.operations.SplitOperation;
import org.pg.magic.pdf.operations.TextExtractOperation;
import org.pg.magic.pdf.operations.UnProtectOperation;

public class PDFOperationFactory {

	public SingleOutputOperation getOperation(SingleOutputOperation.Type type, Properties config) throws PDFOperationException {
		switch (type) {
		case PDF_TEXT_EXTRACT:
			return new TextExtractOperation(config);
		case PDF_PROTECT:
			return new ProtectOperation(config);
		case PDF_UNPROTECT:
			return new UnProtectOperation(config);
		default:
			throw new PDFOperationException("Unknown operation!");
		}
	}
	
	public MultipleOutputsOperation getOperation(MultipleOutputsOperation.Type type, Properties config) throws PDFOperationException {
		switch (type) {
		case PDF_SPLIT:
			return new SplitOperation(config);

		default:
			throw new PDFOperationException("Unknown operation!");
		}
	}

}
