package org.pg.magic.pdf;

import java.util.Properties;

import org.pg.magic.pdf.exceptions.PDFOperationException;

public interface PDFOperationFactory<OutputType> {
	public enum OperationTypes {
		PDF_SPLIT, PDF_MERGE, PDF_EXTRACT_TEXT, PDF_PROTECT, PDF_UNPROTECT, PDF_FROM_IMAGE;
	}
	OutputType getOperation(OperationTypes type, Properties config) throws PDFOperationException;

}