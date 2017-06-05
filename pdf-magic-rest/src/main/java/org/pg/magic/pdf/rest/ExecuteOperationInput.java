package org.pg.magic.pdf.rest;

import java.util.List;
import java.util.Properties;

import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;

public class ExecuteOperationInput {
	List<String> fileNames;
	Properties config;
	PDFStandardOperationTypes operation;
	
	public List<String> getFileNames() {
		return fileNames;
	}
	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}
	public Properties getConfig() {
		return config;
	}
	public void setConfig(Properties config) {
		this.config = config;
	}
	public PDFStandardOperationTypes getOperation() {
		return operation;
	}
	public void setOperation(PDFStandardOperationTypes operation) {
		this.operation = operation;
	}
	
	
}
