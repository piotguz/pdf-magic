package org.pg.magic.pdf.cmd;

import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;

import com.beust.jcommander.IStringConverter;

public class EnumTypeConverter implements IStringConverter<PDFStandardOperationTypes> {

	@Override
	public PDFStandardOperationTypes convert(String type) {
		return PDFStandardOperationTypes.valueOf(type);
	}

}
