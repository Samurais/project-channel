package com.wph.util.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class AddressUtils {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AddressUtils addressUtils = new AddressUtils();
		String ip = "118.213.176.78";
		String address = "";
		try {
			address = addressUtils.getAddress("ip=" + ip, "utf-8");
//			String region = addressUtils.getSingleAddress("ip=" + ip, "utf-8", "region");
//			String city = addressUtils.getSingleAddress("ip=" + ip, "utf-8", "city");
//			String country = addressUtils.getSingleAddress("ip=" + ip, "utf-8", "country");
//			String isp = addressUtils.getSingleAddress("ip=" + ip, "utf-8", "isp");
			System.out.println(address);
//			System.out.println(region);
//			System.out.println(city);
//			System.out.println(country);
//			System.out.println(isp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * %u947E%u5CF0%u5F47%u9366%u677F%u6F43
	 * 
	 * @param params
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public String getAddress(String params, String encoding) throws Exception {

		String path = "http://ip.taobao.com/service/getIpInfo.php";

		String returnStr = this.getRs(path, params, encoding);

		JSONObject json = null;

		if (returnStr != null) {

			json = JSONObject.fromObject(returnStr);

			if ("0".equals(json.get("code").toString())) {

				StringBuffer buffer = new StringBuffer();

				buffer.append( decodeUnicode(json.optJSONObject("data").getString("region")));// 省份

				buffer.append("," +decodeUnicode(json.optJSONObject("data").getString("city")));// 市区

				buffer.append("," +decodeUnicode(json.optJSONObject("data").getString("county")));// 地区

				buffer.append("," +decodeUnicode(json.optJSONObject("data").getString("isp")));// ISP公司

				System.out.println(buffer.toString());

				return buffer.toString();

			} else {

				return null;

			}

		}

		return null;

	}

	public String getSingleAddress(String params, String encoding, String single) {
		String path = "http://ip.taobao.com/service/getIpInfo.php";

		String returnStr = this.getRs(path, params, encoding);

		JSONObject json = null;

		if (returnStr != null) {

			json = JSONObject.fromObject(returnStr);

			if ("0".equals(json.get("code").toString())) {

				StringBuffer buffer = new StringBuffer();

				if (single == "region") {
					return decodeUnicode(json.optJSONObject("data").getString("region"));// 省份
					
				}
				if (single == "city") {

					return decodeUnicode(json.optJSONObject("data").getString("city"));// 市区
				}
				if (single == "county") {

					return decodeUnicode(json.optJSONObject("data").getString("county"));// 地区
				}
				if (single == "isp") {
		
					return decodeUnicode(json.optJSONObject("data").getString("isp"));// ISP公司
				}

				System.out.println(buffer.toString());
			} else {

				return "获取地址失败";

			}

		}

		return null;

	}

	/**
	 * %u6D60%u5DD9rl%u947E%u5CF0%u5F47%u7F01%u64B4%u7049
	 * 
	 * @param path
	 * @param params
	 * @param encoding
	 * @return
	 */
	public String getRs(String path, String params, String encoding) {

		URL url = null;

		HttpURLConnection connection = null;

		try {

			url = new URL(path + "?ip=" + params);

			connection = (HttpURLConnection) url.openConnection();//

			connection.setConnectTimeout(2000);//

			connection.setReadTimeout(2000);//

			connection.setDoInput(true);//
										// true|false

			connection.setDoOutput(true);//

			connection.setRequestMethod("GET");//

			connection.setUseCaches(false);//

			connection.connect();//

//			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//
//			out.writeBytes(params);
//
//			out.flush();
//
//			out.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));

			StringBuffer buffer = new StringBuffer();

			String line = "";

			while ((line = reader.readLine()) != null) {

				buffer.append(line);

			}

			reader.close();

			return buffer.toString();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			connection.disconnect();

		}

		return null;
	}

	/**
	 * %u701B%u6943%uE0C1%u675E%uE102%u721C
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {

		char aChar;

		int len = theString.length();

		StringBuffer buffer = new StringBuffer(len);

		for (int i = 0; i < len;) {

			aChar = theString.charAt(i++);

			if (aChar == '\\') {

				aChar = theString.charAt(i++);

				if (aChar == 'u') {

					int val = 0;

					for (int j = 0; j < 4; j++) {

						aChar = theString.charAt(i++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':

						case '7':

						case '8':

						case '9':

							val = (val << 4) + aChar - '0';

							break;

						case 'a':

						case 'b':

						case 'c':

						case 'd':

						case 'e':

						case 'f':

							val = (val << 4) + 10 + aChar - 'a';

							break;

						case 'A':

						case 'B':

						case 'C':

						case 'D':

						case 'E':

						case 'F':

							val = (val << 4) + 10 + aChar - 'A';

							break;

						default:

							throw new IllegalArgumentException(

									"Malformed      encoding.");
						}

					}

					buffer.append((char) val);

				} else {

					if (aChar == 't') {

						aChar = '\t';
					}

					if (aChar == 'r') {

						aChar = '\r';
					}

					if (aChar == 'n') {

						aChar = '\n';
					}

					if (aChar == 'f') {

						aChar = '\f';

					}

					buffer.append(aChar);
				}

			} else {

				buffer.append(aChar);

			}

		}

		return buffer.toString();

	}

}
