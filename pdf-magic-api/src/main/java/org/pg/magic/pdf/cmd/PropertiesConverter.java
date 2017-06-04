package org.pg.magic.pdf.cmd;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.beust.jcommander.IStringConverter;

public class PropertiesConverter implements IStringConverter<Properties> {

	@Override
	public Properties convert(String propFile) {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File(propFile)));
			return p;
		} catch (Exception e) {
			return null;
		}
	}

}
