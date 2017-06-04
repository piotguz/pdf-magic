package org.pg.magic.pdf;

import java.util.Properties;

import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.operations.ProtectOperation;
import org.pg.magic.pdf.operations.SingleOutputOperation;
import org.pg.magic.pdf.operations.TextExtractOperation;
import org.pg.magic.pdf.operations.UnProtectOperation;

public class SingleOuptutOperationFactory implements PDFOperationFactory<SingleOutputOperation> {

	@Override
	public SingleOutputOperation getOperation(OperationTypes type, Properties config) throws PDFOperationException {
		switch (type) {
		case PDF_EXTRACT_TEXT:
			return new TextExtractOperation(config);
		case PDF_PROTECT:
			return new ProtectOperation(config);
		case PDF_UNPROTECT:
			return new UnProtectOperation(config);
		default:
			throw new PDFOperationException("%s is not a single output operation!", type.toString());
		}
	}

}
