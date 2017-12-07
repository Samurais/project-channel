package com.wph.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Customerserviceright;
import com.wph.service.CustomerservicerightService;

@Service("customerservicerightService")
public class CustomerservicerightServiceImpl extends BaseServiceImpl<Customerserviceright>
		implements CustomerservicerightService {

	@Override
	public Long getCount(Integer companyid) {
		String hql = "select count(*) from Customerserviceright c where c.company.cpId = :companyid";
		return (Long) getSession().createQuery(hql).setInteger("companyid", companyid).uniqueResult();
	}

	@Override
	public List<Customerserviceright> pageQuery(Integer limit, Integer offset, String search, Integer companyid) {
		if (search == null) {
			String hql = "from Customerserviceright c where c.company.cpId = :companyid";
			return getSession().createQuery(hql).setInteger("companyid", companyid).list();
		} else {
			String hql = "from Customerserviceright c where c.company.cpId = :companyid and c.type like :search";
			return getSession().createQuery(hql).setInteger("companyid", companyid)
					.setString("search", "%" + search + "%").list();
		}
	}

	@Override
	public void editUpdate(Customerserviceright csr) {
		update(csr);
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Customerserviceright c where c.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public boolean idValid(Integer id) {
		String hql = "select count(*) from Customerserviceright c where c.id = :id";
		Long count = (Long) getSession().createQuery(hql).setInteger("id", id).uniqueResult();
		return count == 0;
	}

}
