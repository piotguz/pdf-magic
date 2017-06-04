package org.pg.magic.pdf;

import java.util.Properties;

import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.operations.MergeOperation;
import org.pg.magic.pdf.operations.MultipleInputsOperation;

public class MultipleInputsOperationFactory implements PDFOperationFactory<MultipleInputsOperation> {

	@Override
	public MultipleInputsOperation getOperation(OperationTypes type,
			Properties config) throws PDFOperationException {
		switch (type) {
		case PDF_MERGE:
			return new MergeOperation(config);

		default:
			throw new PDFOperationException("%s is not a multiple inputs operation!", type.toString());
		}
	}

}
