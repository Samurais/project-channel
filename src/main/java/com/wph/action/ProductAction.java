package com.wph.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wph.entities.Product;
import com.wph.entities.json.OrderJSON;
import com.wph.entities.json.ProductJSON;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<ProductJSON> {
	// ********************************************************************************
	// ==================================图片上传功能=====================================
	private File file;
	private String fileFileName;
	private String fileFileContentType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

	public String saveimage() throws Exception {
		if (file == null)
			return null;
		String pic = fileUpload.saveFile(file, fileFileName, fileFileContentType);
		inputStream = new ByteArrayInputStream(pic.getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==================================保存产品事件部分==================================

	public String save() {
		String pic = model.getPic().substring(6, model.getPic().lastIndexOf("</pre>"));
		System.out.println(pic);
		model.setPic(pic);
		Product product = productService.productJSONtoProduct(model);
		productService.save(product);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ================================ID重复验证部分=====================================
	// 查看ID是否重复
	public String idValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", productService.idValid(model.getId()));
		return "jsonMap";
	}

	// 查看ID是否存在
	public String catrgoryidValid() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("valid", productService.categoryidValid(model.getCategory_id()));
		return "jsonMap";
	}

	// ********************************************************************************
	// ==================================分页查询部分=====================================
	private Integer companyid;

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public String pageQuery() {
		jsonMap = new HashMap<String, Object>();
		jsonMap.put("rows", productService.pageQuery(companyid, limit, offset, search));
		jsonMap.put("total", productService.getCount(companyid));
		return "jsonMap";
	}

	// ********************************************************************************
	// =================================delete方法======================================
	public String deleteByIds() {
		System.out.println(ids);
		productService.deleteByIds(ids);
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}

	// ********************************************************************************
	// ==========================修改对应的onEditableSave方法=============================
	public String editUpdate() {
		productService.update(model, companyid);
		inputStream = new ByteArrayInputStream("success".getBytes());
		return "stream";
	}
	
	// ********************************************************************************
	// ======================================获得商品信息方法=============================
	public String getProduct(){
		jsonMap = new HashMap<String, Object>();
		ProductJSON productJSON = productService.getProdcut(model.getId());
		jsonMap.put("productJSON", productJSON);
		return "jsonMap";
	}
}
