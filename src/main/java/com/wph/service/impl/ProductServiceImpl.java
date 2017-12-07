package com.wph.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.wph.collaborativefiltering.recommend;
import com.wph.entities.Category;
import com.wph.entities.Product;
import com.wph.entities.json.ProductJSON;
import com.wph.entities.json.service.ProductJSONService;
import com.wph.service.ProductService;

@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	@Override
	public Product productJSONtoProduct(ProductJSON productJSON) {
		Product product = new Product();
		Object category = getSession().get(Category.class, productJSON.getCategory_id());
		if (category != null) {
			product.setCategory((Category) category);
		}
		product.setId(productJSON.getId());
		product.setName(productJSON.getName());
		product.setPic(productJSON.getPic());
		product.setPrice(productJSON.getPrice());
		product.setRemark(productJSON.getRemark());
		product.setXremark(productJSON.getXremark());
		product.setStatus(productJSON.getStatus());
		return product;
	}

	@Override
	public List<ProductJSON> pageQuery(Integer companyid, Integer limit, Integer offset, String search) {
		List<Object[]> productList = null;
		if (search == null) {
			String sql = "select * from product p where p.category_id in(select c.id from category c where c.company_id=:companyid)";
			productList = getSession().createSQLQuery(sql).setInteger("companyid", companyid).setFirstResult(offset)
					.setMaxResults(limit).list();
		} else {
			String sql = "select * from product p where p.category_id in (select c.id from category c where c.company_id =:companyid) and p.name like:search";
			productList = getSession().createQuery(sql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").setFirstResult(offset).setMaxResults(limit).list();
		}

		return ProductJSONService.producttoJSON(productList);
	}

	@Override
	public BigInteger getCount(Integer companyid) {
		String sql = "select count(*) from product p where p.category_id in (select id from category c where c.company_id =:companyid)";

		return (BigInteger) getSession().createSQLQuery(sql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Product c where c.id =" + id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean categoryidValid(Integer category_id) {
		String hql = "select count(*) from Product c where c.category.id =" + category_id;
		Long count = (Long) getSession().createQuery(hql).uniqueResult();
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getpic(Integer productid) {
		Product product = get(productid);
		String pic = null;
		if (product != null) {
			pic = product.getPic();
		}
		return pic;
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();

	}

	@Override
	public void update(ProductJSON model, Integer companyid) {
		Product product = new Product();
		product.setCategory((Category) getSession().get(Category.class, model.getCategory_id()));
		product.setId(model.getId());
		product.setName(model.getName());
		product.setPic(model.getPic());
		product.setPrice(model.getPrice());
		product.setRemark(model.getRemark());
		product.setStatus(model.getStatus());
		product.setXremark(model.getXremark());
		update(product);

	}

	// 基于产品的协调算法
	// 根据公司ID和客户ID获得推荐商品
	@Override
	public Integer getRecommandProductId(Integer customerid, Integer companyid) {
		Map<Integer, Map<Integer, Integer>> userPerfMap = new HashMap<Integer, Map<Integer, Integer>>();
		String sql1 = "select p.id from product p where p.category_id in (select c.id from category c where c.company_id = :companyid)";
		List<Integer> pids = getSession().createSQLQuery(sql1).setInteger("companyid", companyid).list();
		if (pids.size() == 0) {
			return null;
		}
		for (Integer pid : pids) {
			Map<Integer, Integer> pref = new HashMap<Integer, Integer>();
			// 这里有兼容性问题
			// 将购买过商品的顾客,标记为1,放入Map中
			String sql2 = "select o.customer_id from access.order o where o.status='notfinish' or o.status='finish' and o.product_id = :productid group by o.company_id";
			List<Integer> cids = getSession().createSQLQuery(sql2).setInteger("productid", pid).list();
			if (cids.size() != 0) {
				for (Integer cid : cids) {
					pref.put(cid, 1);
				}
			}
			// 将未购买过商品的顾客,标记未0,放入Map中
			String cidsstr = cids.toString().substring(1, cids.toString().length() - 1);
			String sql3 = "select c.ct_id from customer c where c.company_id = :companyid and c.ct_id not in (:cidsstr)";
			List<Integer> ncids = getSession().createSQLQuery(sql3).setInteger("companyid", companyid)
					.setString("cidsstr", cidsstr).list();
			if (ncids.size() != 0) {
				for (Integer nid : ncids) {
					pref.put(nid, 0);
				}
			}
			// 将购买记录Map和商品Id放入Map中
			if (userPerfMap != null) {
				userPerfMap.put(pid, pref);
			}
		}
		return recommend.getRecommend(userPerfMap, customerid);
	}

	// 基于客户的协调算法
	@Override
	public Integer getRecommandProductId2(Integer customerid, Integer companyid) {
		Map<Integer, Map<Integer, Integer>> userPerfMap = new HashMap<Integer, Map<Integer, Integer>>();
		String sql1 = "select c.ct_id from customer c where c.company_id = :companyid";
		List<Integer> cids = getSession().createSQLQuery(sql1).setInteger("companyid", companyid).list();
		if (cids.size() == 0) {
			return null;
		}
		for (Integer cid : cids) {
			Map<Integer, Integer> pref = new HashMap<Integer, Integer>();
			// 兼容性问题
			String sql2 = "select o.product_id from access.order o where o.customer_id = :cid and (o.status='notfinish' or o.status='finish') and o.product_id is not null group by o.product_id";
			List<Integer> pids = getSession().createSQLQuery(sql2).setInteger("cid", cid).list();
			if (pids.size() != 0) {
				for (Integer pid : pids) {
					pref.put(pid, 1);
				}
			}
			String pidsstr = null;
			if(pids.size()!=0){				
				pidsstr = pids.toString().substring(1, pids.toString().length() - 1);
			}
			String sql3 = null;
			if(pidsstr!=null){				
				sql3 = "select p.id from product p where p.id not in (" + pidsstr + ") and p.category_id in(select c.id from category c where c.company_id = :companyid)";
			}else{
				sql3 = "select p.id from product p where p.category_id in(select c.id from category c where c.company_id = :companyid)";
			}
			List<Integer> npids = getSession().createSQLQuery(sql3)
					.setInteger("companyid", companyid).list();
			if (npids.size() != 0) {
				for (Integer npid : npids) {
					pref.put(npid, 0);
				}
			}
			if (userPerfMap != null) {
				userPerfMap.put(cid, pref);
			}
		}
		return recommend.getRecommend(userPerfMap, customerid);
	}

	@Override
	public ProductJSON getProdcut(Integer productid) {
		ProductJSON productJSON = new ProductJSON();
		Product product = get(productid);
		Integer id = product.getId();
		Category category = product.getCategory();
		Integer category_id = null;
		if(category!=null){
			category_id = category.getId();
		}
		String name = product.getName();
		String pic = product.getPic();
		Integer price = product.getPrice();
		String remark = product.getRemark();
		String xremark = product.getXremark();
		Integer status = product.getStatus();
		
		productJSON.setId(id);
		productJSON.setCategory_id(category_id);
		productJSON.setName(name);
		productJSON.setPic(pic);
		productJSON.setPrice(price);
		productJSON.setRemark(remark);
		productJSON.setXremark(xremark);
		productJSON.setStatus(status);
		
		return productJSON;
	}

	@Override
	public Integer getRandomProductId(Integer companyid) {
		String hql ="from Product p where p.category.company.cpId = :companyid";
		List<Product> plist = null;
		try {			
			plist = getSession().createQuery(hql).setInteger("companyid", companyid).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Integer length = plist.size();
		if(length==0){
			return -1;
		}else{
			Random r = new Random();
			System.out.println(r.nextInt(length));
			Integer productid = plist.get(r.nextInt(length)).getId();
			return productid;
		}
	}
}
