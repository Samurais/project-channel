package com.wph.model;

import java.io.File;

public class FileImage {
	private File upload;
	private String contentType;
	private String filename;

	public File getFile() {
		return upload;
	}

	public String getContentType() {
		return contentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setUpload(File file) { // set方法可以不用和属性名一样，但是前台传进来时的参数得和set方法名相同。即前台传的参数为fileImage.upload
		this.upload = upload;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}
}
