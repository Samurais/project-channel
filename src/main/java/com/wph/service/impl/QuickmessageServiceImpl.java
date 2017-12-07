package com.wph.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wph.entities.Quickmessage;
import com.wph.service.QuickmessageService;

@Service("quickmessageService")
public class QuickmessageServiceImpl extends BaseServiceImpl<Quickmessage> implements QuickmessageService {

	@Override
	public List<Quickmessage> pageQuery(Integer limit, Integer offset, String search, Integer serviceid) {
		if (serviceid == null) {
			return null;
		}
		if (search == null || search == "") {

			String hql = "from Quickmessage q where q.customerservice.csId =:serviceid";
			return getSession().createQuery(hql).setInteger("serviceid", serviceid).list();
		} else {
			String hql = "from Quickmessage q where q.customerservice.csId =:serviceid and q.content like :search";
			return getSession().createQuery(hql).setInteger("serviceid", serviceid).setString("search", search).list();
		}
	}

	@Override
	public Long getCount(Integer serviceid) {
		if (serviceid == null) {
			return null;
		}
		String hql = "select count(*) from Quickmessage q where q.customerservice.csId =:serviceid";
		return (Long) getSession().createQuery(hql).setInteger("serviceid", serviceid).uniqueResult();
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Quickmessage where id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
	}

}
