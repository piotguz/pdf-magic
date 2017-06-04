package org.pg.magic.pdf;

import java.util.Properties;

import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.operations.MultipleOutputsOperation;
import org.pg.magic.pdf.operations.SplitOperation;

public class MultipleOuptutOperationFactory implements PDFOperationFactory<MultipleOutputsOperation> {

	@Override
	public MultipleOutputsOperation getOperation(OperationTypes type, Properties config) throws PDFOperationException {
		switch (type) {
		case PDF_SPLIT:
			return new SplitOperation(config);
	
		default:
			throw new PDFOperationException("%s is not a multiple output operation!", type.toString());
		}
	}

}
