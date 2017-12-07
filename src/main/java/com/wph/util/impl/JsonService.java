package com.wph.util.impl;

import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonService {
	private JsonService() {
	}

	private static final JsonConfig jsonConfig = init();

	private static JsonConfig init() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
			private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				return value == null ? "" : sd.format(value);
			}

			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return null;
			}
		});
		return jsonConfig;
	}

	public static String getJsonStr(String key, Object value) {
		// 使用config对象.
		JSONObject jsonObject = JSONObject.fromObject(value, jsonConfig);
		return jsonObject.toString();
	}
}
