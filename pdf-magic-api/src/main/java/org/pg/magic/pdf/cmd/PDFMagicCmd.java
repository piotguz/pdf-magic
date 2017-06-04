package org.pg.magic.pdf.cmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.PDFOperationFactory.PDFStandardOperationTypes;
import org.pg.magic.pdf.exceptions.PDFOperationException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class PDFMagicCmd {
	static Logger log = Logger.getLogger("PDF-MAGIC");
	
	@Parameter(names = "--help", help = true, description="Display usage")
	private boolean help;
	
	@Parameter(names = {"--operation", "-op"}, description="PDF Operation", converter=EnumTypeConverter.class, required=true)
	private PDFStandardOperationTypes operation;
	
	@Parameter(names = {"--input", "-i"}, description="Input pdf", converter=FileTypeConverter.class, required=true)
	private File file;
	
	@Parameter(names={"--config","-c"}, description="Configruation properties path", converter=PropertiesConverter.class)
	private Properties config;

	public static void main(String... args) {
		PDFMagicCmd pdfMagic = new PDFMagicCmd();
		JCommander commander = new JCommander(pdfMagic);
		commander.setProgramName("pdf-magic");
		try {
			log.info("Parsing input params...");
			commander.parse(args);
			pdfMagic.run();
			log.info("Well done!");
		} catch (Exception e) {
			log.severe(ExceptionUtils.getMessage(e));
			e.printStackTrace();
			commander.usage();
		}
	}

	public void run() throws FileNotFoundException, PDFOperationException {
		log.info("Running...");
		PDFOperationFactory factory = new PDFOperationFactory();
		PDFOperation o = factory.getOperation(operation, config);
		o.execute(file);
	}
}
