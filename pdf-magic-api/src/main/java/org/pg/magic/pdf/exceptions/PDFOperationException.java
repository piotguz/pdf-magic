package org.pg.magic.pdf.exceptions;

public class PDFOperationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public PDFOperationException(String string, Object ... obj) {
		super(String.format(string, obj));
	}


	public PDFOperationException(Throwable arg0) {
		super(arg0);
	}

}
