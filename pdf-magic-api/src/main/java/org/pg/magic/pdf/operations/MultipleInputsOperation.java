package org.pg.magic.pdf.operations;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public abstract class MultipleInputsOperation extends PDFOperation<File, List<File>> {

	protected MultipleInputsOperation(Properties config) throws PDFOperationException {
		super(config);
	}


}
