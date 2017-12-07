package com.wph.util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.wph.model.FileImage;
import com.wph.util.FileUpload;

@Component("fileUpload")
public class FileUploadUtil implements FileUpload{
	private String filePath;

	@Value("#{prop.filePath}")
	// @Value表示去beans.xml文件中找id="prop"的bean，它是通过注解的方式读取properties配置文件的，然后去相应的配置文件中读取key=filePath的值
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	// 1. 通过文件名获取扩展名
	private String getFileExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	// 2. 生成UUID随机数，作为新的文件名
	private String newFileName(String fileName) {
		String ext = getFileExt(fileName);
		return UUID.randomUUID().toString() + "." + ext;
	}
	
	// 实现文件上传的功能，返回上传后新的文件名称
	@Override
	public String uploadFile(FileImage fileImage) {
		// 获取新唯一文件名
		System.out.println(fileImage);
		String pic = newFileName(fileImage.getFilename());
		try {
			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));// 第一个参数是上传的文件，第二个参数是将文件拷贝到新路径下
			return pic;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			fileImage.getFile().delete();
		}
	}
	
	@Override
	public String saveFile(File file,String fileFileName,String fileFileContentType) {
		String path = ServletActionContext.getRequest().getRealPath("/upload");
		String newFileName = newFileName(fileFileName);
		String newFilePath = path + "/" + newFileName;
		String sqlFilePath = "/upload/" + newFileName;
		try {
			File f = file;
			if (fileFileName.endsWith(".exe")) {
			}
			FileInputStream inputStream = new FileInputStream(f);
			FileOutputStream outputStream = new FileOutputStream(newFilePath);
			System.out.println(newFilePath);
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			inputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlFilePath;
	}

	public String saveFile(FileImage fileImage) {
		File file = fileImage.getFile();
		String fileFileName = fileImage.getFilename();
		String outputFileName = newFileName(fileImage.getFilename());
		String fileFileContentType = fileImage.getContentType();
		String path = ServletActionContext.getRequest().getRealPath("/upload");
		try {
			File f = file;
			if (fileFileName.endsWith(".exe")) {
			}
			FileInputStream inputStream = new FileInputStream(f);
			FileOutputStream outputStream = new FileOutputStream(path + "/" + outputFileName);
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, length);
			}
			inputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return outputFileName;
	}

}
