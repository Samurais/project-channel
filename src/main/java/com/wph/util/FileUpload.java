package com.wph.util;

import java.io.File;

import com.wph.model.FileImage;

public interface FileUpload {
	// ʵ���ļ��ϴ��Ĺ��ܣ������ϴ����µ��ļ�����
	public abstract String uploadFile(FileImage fileImage);
	
	public String saveFile(FileImage fileImage);

	String saveFile(File file, String fileFileName, String fileFileContentType);
}
