package org.pg.magic.pdf;

import java.util.Properties;

import org.pg.magic.pdf.exceptions.PDFOperationException;
import org.pg.magic.pdf.operations.ExtractTextOperation;
import org.pg.magic.pdf.operations.FromImageOperation;
import org.pg.magic.pdf.operations.MergeOperation;
import org.pg.magic.pdf.operations.ProtectOperation;
import org.pg.magic.pdf.operations.SplitOperation;
import org.pg.magic.pdf.operations.UnProtectOperation;

public class PDFOperationFactory {
	public enum PDFStandardOperationTypes implements PDFOperationType {
		PDF_SPLIT, PDF_MERGE, PDF_PROTECT, PDF_UNPROTECT, PDF_EXTRACT_TEXT, PDF_FROM_IMAGE;
	}

	public PDFOperation getOperation(PDFOperationType type, Properties config) throws PDFOperationException {
		if (type instanceof PDFStandardOperationTypes) {
			switch ((PDFStandardOperationTypes) type) {
			case PDF_EXTRACT_TEXT:
				return new ExtractTextOperation(config);
			case PDF_FROM_IMAGE:
				return new FromImageOperation(config);
			case PDF_MERGE:
				return new MergeOperation(config);
			case PDF_PROTECT:
				return new ProtectOperation(config);
			case PDF_SPLIT:
				return new SplitOperation(config);
			case PDF_UNPROTECT:
				return new UnProtectOperation(config);
			default:
				return null;
			}
		} else {
			throw new PDFOperationException(
					"This factory does not support enum of type %s! Implement your own factory!",
					type.getClass().getSimpleName());
		}
	}
}
