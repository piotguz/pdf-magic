package org.pg.magic.pdf.operations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.pg.magic.pdf.exceptions.PDFOperationException;

public class SplitOperation extends PDFOperationImpl {

	public SplitOperation(Properties config) throws PDFOperationException {
		super(config);
	}

	@Override
	public void execute(File inputFile) throws PDFOperationException {
		checkInputFile(inputFile);

		try {
			PDDocument document = PDDocument.load(inputFile);
			Splitter splitter = new Splitter();
			List<PDDocument> pages = splitter.split(document);

			String originalFileName = FilenameUtils.getBaseName(inputFile.getName());
			String originalFileExt = FilenameUtils.getExtension(inputFile.getName());
			String originalFileNameFolder = config.getProperty("output.folder", inputFile.getParent());

			int pageNum = 0;
			List<File> output = new ArrayList<File>();

			for (PDDocument page : pages) {
				String pageFileStr = String.format("%s/%s_page%d.%s", originalFileNameFolder, originalFileName, pageNum,
						originalFileExt);
				File pageFile = new File(pageFileStr);
				page.save(pageFile);
				output.add(pageFile);

				pageNum++;
				log.info(pageFileStr);
			}

			document.close();
			super.outputFiles = output;
		} catch (Exception e) {
			throw new PDFOperationException(e);
		}

	}

}
