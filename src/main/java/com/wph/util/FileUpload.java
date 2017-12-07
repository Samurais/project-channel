package com.wph.util;

import java.io.File;

import com.wph.model.FileImage;

public interface FileUpload {
	// 实现文件上传的功能，返回上传后新的文件名称
	public abstract String uploadFile(FileImage fileImage);
	
	public String saveFile(FileImage fileImage);

	String saveFile(File file, String fileFileName, String fileFileContentType);
}
