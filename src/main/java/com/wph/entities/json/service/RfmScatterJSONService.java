package com.wph.entities.json.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.wph.entities.json.RfmScatterJSON;

public class RfmScatterJSONService {
	private static Integer rfmtimeReference;

	@Value("#{prop.rfmtimeReference}")
	// @Value表示去beans.xml文件中找id="prop"的bean，它是通过注解的方式读取properties配置文件的，然后去相应的配置文件中读取key=filePath的值
	public void setRfmtimeReference(Integer rfmtimeReference) {
		this.rfmtimeReference = rfmtimeReference;
	}

	public static List<RfmScatterJSON> customerrfmtoJSON(List<Object[]> rfmlist) {
		List<RfmScatterJSON> rfmJSONlist = new ArrayList<RfmScatterJSON>();
		for (Object[] o : rfmlist) {
			Integer id = (Integer) o[0];
			Integer customer_id = (Integer) o[1];
			Timestamp lastbuytime = null;
			if (o[2] != null) {
				lastbuytime = (Timestamp) o[2];
			}
			Integer monthbuytimes = (Integer) o[3];
			Integer lastmonthbuytimes = (Integer) o[4];
			Integer monthbuysum = (Integer) o[5];
			Integer lastmonthbuysum = (Integer) o[6];

			RfmScatterJSON rfm = new RfmScatterJSON();
			rfm.setBuysum(monthbuysum + lastmonthbuysum);
			rfm.setBuytimes(monthbuytimes + lastmonthbuytimes);

			Integer year = new Timestamp(System.currentTimeMillis()).getYear() - 1;
			Timestamp refertime = new Timestamp(year, 1, 1, 0, 0, 0, 0);
			Double time = lastbuytime.getTime() * 1.0 / 1000 / 3600 / 24 - refertime.getTime() * 1.0 / 1000 / 3600 / 24;
			if (time < 0) {
				rfm.setLastbuytime((double) 0);
			} else {
				rfm.setLastbuytime(time);
			}
			rfmJSONlist.add(rfm);
		}
		return rfmJSONlist;
	}
}
