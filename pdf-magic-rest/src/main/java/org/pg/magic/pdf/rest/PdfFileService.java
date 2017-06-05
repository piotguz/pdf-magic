package org.pg.magic.pdf.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

// https://www.mkyong.com/webservices/jax-rs/file-upload-example-in-resteasy/
@Path("/pdf")
public class PdfFileService {

	public static final String UPLOAD_ROOT = "C:/temp";

	@Path("/upload")
	@POST
	@Consumes("multipart/form-data")
	public Response upload(@MultipartForm FileUploadForm form) {
		String fileName = String.format("%s/%s.uploaded",
				System.getProperty(UPLOAD_ROOT, UUID.randomUUID().toString()));
		UUID.randomUUID().toString();

		try {
			writeFile(form.getData(), fileName);
		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out.println("Done");

		return Response.status(200).entity(fileName).build();
	}

	@GET
	@Path("/download")
	public Response download(@QueryParam("file") String file) {
		File inputFile = new File(String.format("%s/%s", UPLOAD_ROOT, file));

		if (!inputFile.exists())
			return Response.status(Status.BAD_REQUEST).build();

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"" + file + "\"");
		return response.build();
	}

	private void writeFile(byte[] content, String fileName) throws IOException {
		File file = new File(fileName);

		if (!file.exists()) {
			file.createNewFile();
		}

		FileOutputStream fop = new FileOutputStream(file);

		fop.write(content);
		fop.flush();
		fop.close();
	}
}
