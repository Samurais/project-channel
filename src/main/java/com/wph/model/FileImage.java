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

	public void setUpload(File file) { // set�������Բ��ú�������һ��������ǰ̨������ʱ�Ĳ����ú�set��������ͬ����ǰ̨���Ĳ���ΪfileImage.upload
		this.upload = upload;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}
}
