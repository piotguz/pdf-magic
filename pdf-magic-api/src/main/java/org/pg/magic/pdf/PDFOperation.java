package org.pg.magic.pdf;

import java.io.File;
import java.util.List;

import org.pg.magic.pdf.exceptions.PDFOperationException;

public interface PDFOperation {
	void execute(List<File> input) throws PDFOperationException;
	void execute(File input) throws PDFOperationException;
	File getOutputFile();
	List<File> getOutputFiles();

}