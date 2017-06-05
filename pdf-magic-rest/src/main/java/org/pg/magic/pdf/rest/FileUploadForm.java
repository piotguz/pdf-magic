package org.pg.magic.pdf.rest;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class FileUploadForm {
	public FileUploadForm() {
		
	}

	private byte[] data;

	public byte[] getData() {
		return data;
	}

	@FormParam("upload-form")
	@PartType("application/octet-stream")
	public void setData(byte[] data) {
		this.data = data;
	}
}
