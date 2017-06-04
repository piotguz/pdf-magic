package org.pg.magic.pdf.operations;

import java.io.File;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public abstract class SingleOutputOperation extends PDFOperation<File, File> {

	public enum Type {
		PDF_TEXT_EXTRACT, PDF_PROTECT, PDF_UNPROTECT;
	}

	protected SingleOutputOperation(Properties config) throws PDFOperationException {
		super(config);
	}


}
