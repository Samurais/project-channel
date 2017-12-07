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
	// @Value��ʾȥbeans.xml�ļ�����id="prop"��bean������ͨ��ע��ķ�ʽ��ȡproperties�����ļ��ģ�Ȼ��ȥ��Ӧ�������ļ��ж�ȡkey=filePath��ֵ
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	// 1. ͨ���ļ�����ȡ��չ��
	private String getFileExt(String fileName) {
		return FilenameUtils.getExtension(fileName);
	}

	// 2. ����UUID���������Ϊ�µ��ļ���
	private String newFileName(String fileName) {
		String ext = getFileExt(fileName);
		return UUID.randomUUID().toString() + "." + ext;
	}
	
	// ʵ���ļ��ϴ��Ĺ��ܣ������ϴ����µ��ļ�����
	@Override
	public String uploadFile(FileImage fileImage) {
		// ��ȡ��Ψһ�ļ���
		System.out.println(fileImage);
		String pic = newFileName(fileImage.getFilename());
		try {
			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));// ��һ���������ϴ����ļ����ڶ��������ǽ��ļ���������·����
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
