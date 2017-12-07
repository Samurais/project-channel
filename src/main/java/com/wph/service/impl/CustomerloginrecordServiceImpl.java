package com.wph.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.wph.entities.Customer;
import com.wph.entities.Customerloginrecord;
import com.wph.entities.json.VisitRefererJSON;
import com.wph.entities.json.VisitRegionJSON;
import com.wph.service.CustomerloginrecordService;
import com.wph.util.impl.AddressUtils;
import com.wph.util.impl.GetKeyword;
import com.wph.util.impl.SystemUtils;

@Service("customerloginrecordService")
public class CustomerloginrecordServiceImpl extends BaseServiceImpl<Customerloginrecord>
		implements CustomerloginrecordService {

	@Override
	public void saveCustomerloginrecord(HttpServletRequest request, Integer loginid) {
		Customerloginrecord customerloginrecord = new Customerloginrecord();
		if (loginid != null) {
			Object customer = getSession().get(Customer.class, loginid);
			customerloginrecord.setCustomer((Customer) customer);
		}
		// 获取IP,浏览器信息,主机名字,系统版本,mac地址
		String ipAddr = SystemUtils.getIpAddr(request);
		String browserInfo = SystemUtils.getRequestBrowserInfo(request);
		String hostName = SystemUtils.getHostName(ipAddr);
		String systemInfo = SystemUtils.getRequestSystemInfo(request);
		// 获得mac地址太消耗时间
		// String macAddr = SystemUtils.getMacAddress(ipAddr);

		// 获得搜索引擎来源和搜索关键字
		GetKeyword getKeywordUtil = new GetKeyword();
		String referer = request.getHeader("Referer");
		String searchEngine = getKeywordUtil.getSearchEngine(referer);
		String keyWord = getKeywordUtil.getKeyword(referer);

		// 获得IP地址的所在地区,并单独抽取出省份
		AddressUtils addressUtil = new AddressUtils();
		String area = null;
		try {
			area = addressUtil.getAddress(ipAddr, "utf-8");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String region = null;
		if (area != null) {
			region = area.substring(0, area.indexOf(","));
		}

		customerloginrecord.setIpaddr(ipAddr);
		customerloginrecord.setBrowserinfo(browserInfo);
		customerloginrecord.setHostname(hostName);
		customerloginrecord.setSysteminfo(systemInfo);
		// customerloginrecord.setMacaddr(macAddr);
		customerloginrecord.setReferer(referer);
		customerloginrecord.setRefererkeyword(keyWord);
		customerloginrecord.setArea(area);
		customerloginrecord.setRegion(region);
		customerloginrecord.setLogintime(new Timestamp(System.currentTimeMillis()));

		getSession().save(customerloginrecord);
		System.out.println("customerloginrecord保存成功");
	}

	@Override
	public List<VisitRegionJSON> getVisitregion(Integer companyid) {
		List<VisitRegionJSON> visitRegionList = new ArrayList<VisitRegionJSON>();
		String sql = "select c.region from customerloginrecord c where c.region is not null and "
				+ "c.customer_id in(select o.ct_id from customer o where o.company_id = :companyid)";
		List<String> regionlist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).list();
		if (regionlist.size() == 0) {
			return null;
		}
		for (String region : regionlist) {
			if (region == null) {
				continue;
			}
			Boolean isnew = true;
			for (VisitRegionJSON vj : visitRegionList) {
				if (region.equals(vj.getName())) {
					isnew = false;
					vj.setValue(vj.getValue() + 1);
					break;
				}
			}
			if (isnew == true) {
				VisitRegionJSON newvj = new VisitRegionJSON();
				newvj.setName(region);
				newvj.setValue(1);
				visitRegionList.add(newvj);
			}
		}
		return visitRegionList;
	}

	@Override
	public List<VisitRegionJSON> getVisitregionLastMonth(Integer companyid) {
		List<VisitRegionJSON> visitRegionList = new ArrayList<VisitRegionJSON>();
		String sql = "select c.region from customerloginrecord c where c.region is not null "
				+ "and DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= c.logintime and "
				+ "c.customer_id in(select o.ct_id from customer o where o.company_id = :companyid)";
		List<String> regionlist = getSession().createSQLQuery(sql).setInteger("companyid", companyid).list();
		if (regionlist.size() == 0) {
			return null;
		}
		for (String region : regionlist) {
			if (region == null) {
				continue;
			}
			Boolean isnew = true;
			for (VisitRegionJSON vj : visitRegionList) {
				if (region.equals(vj.getName())) {
					isnew = false;
					vj.setValue(vj.getValue() + 1);
					break;
				}
			}
			if (isnew == true) {
				VisitRegionJSON newvj = new VisitRegionJSON();
				newvj.setName(region);
				newvj.setValue(1);
				visitRegionList.add(newvj);
			}
		}
		return visitRegionList;
	}

	@Override
	public List<VisitRefererJSON> getVisitRefererJSON(Integer companyid) {
		String sql = "select c.referer from customerloginrecord c where c.referer is not null and "
				+ "DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= c.logintime and"
				+ " c.customer_id in (select o.ct_id from customer o where o.company_id = :companyid)";
		List<String> RefererList = getSession().createSQLQuery(sql).setInteger("companyid", companyid).list();
		if (RefererList.size() == 0) {
			return null;
		}
		List<VisitRefererJSON> visitRefererJSONList = new ArrayList<VisitRefererJSON>();
		for (String referer : RefererList) {
			if (referer == null) {
				continue;
			}
			Boolean isnew = true;
			for (VisitRefererJSON str : visitRefererJSONList) {
				if (referer.equals(str.getName())) {
					isnew = false;
					str.setValue(str.getValue() + 1);
					break;
				}
			}
			if (isnew == true) {
				VisitRefererJSON newReferer = new VisitRefererJSON();
				newReferer.setName(referer);
				newReferer.setValue(1);
				visitRefererJSONList.add(newReferer);
			}
		}
		Collections.sort(visitRefererJSONList);
		if (visitRefererJSONList.size() < 10) {
			return visitRefererJSONList;

		} else {
			visitRefererJSONList = visitRefererJSONList.subList(visitRefererJSONList.size() - 9,
					visitRefererJSONList.size());
			return visitRefererJSONList;
		}
	}

}
