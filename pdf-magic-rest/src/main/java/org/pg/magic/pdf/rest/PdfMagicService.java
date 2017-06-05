package org.pg.magic.pdf.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.pg.magic.pdf.PDFOperation;
import org.pg.magic.pdf.PDFOperationFactory;
import org.pg.magic.pdf.exceptions.PDFOperationException;

@Path("/pdf")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PdfMagicService {
	
	@POST
	public Response executeOperation(ExecuteOperationInput input) {
		PDFOperationFactory factory = new PDFOperationFactory();
		try {
			PDFOperation operation = factory.getOperation(input.getOperation(), input.getConfig());
			List<File> inputFiles = new ArrayList<>();
			
			for (String fileName:input.getFileNames()) {
				inputFiles.add(new File(String.format("%s/%s", PdfFileService.UPLOAD_ROOT, fileName)));
			}
			
			operation.execute(inputFiles);
			
			return Response.ok(operation.getOutputFiles()).build();
		} catch (PDFOperationException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
