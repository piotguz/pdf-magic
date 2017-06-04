package org.pg.magic.pdf.operations;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class ListOfPDFs {
	List<File> inputFileList;
	
	protected void loadSamplePDFs(String...names) throws UnsupportedEncodingException {
		inputFileList = new ArrayList<>();
		
		for (String name:names) {
			URL inputPDFUrl = getClass().getResource(name);
			inputFileList.add(new File(URLDecoder.decode(inputPDFUrl.getFile(),"utf-8")));
		}
	}
}
