package org.pg.magic.pdf.cmd;

import java.io.File;

import com.beust.jcommander.IStringConverter;

public class FileTypeConverter implements IStringConverter<File> {

	@Override
	public File convert(String fileName) {
		return new File(fileName);
	}

}
