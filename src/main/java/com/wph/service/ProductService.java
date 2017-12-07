package com.wph.service;

import java.math.BigInteger;
import java.util.List;

import com.wph.entities.Product;
import com.wph.entities.json.ProductJSON;

public interface ProductService extends BaseService<Product>{
	public Product productJSONtoProduct(ProductJSON productJSON);
	
	public List<ProductJSON> pageQuery(Integer companyid,Integer limit, Integer offset, String search);

	public BigInteger getCount(Integer companyid);

	public boolean idValid(Integer id);

	public boolean categoryidValid(Integer category_id);
	
	public String getpic(Integer productid);

	public void deleteByIds(String ids);

	public void update(ProductJSON model, Integer companyid);
	
	public Integer getRecommandProductId(Integer customerid,Integer companyid);
	
	public Integer getRecommandProductId2(Integer customerid,Integer companyid);
	
	public Integer getRandomProductId(Integer companyid);
	
	public ProductJSON getProdcut(Integer productid);
}
