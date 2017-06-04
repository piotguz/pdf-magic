package org.pg.magic.pdf.operations;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public abstract class MultipleOutputsOperation extends PDFOperation<List<File>, File> {

	public enum Type {
		PDF_SPLIT
	}

	protected MultipleOutputsOperation(Properties config) throws PDFOperationException {
		super(config);
	}

}
