package com.wph.util.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetKeyword {
	public static void main(String[] arg) {
		String referer = "http://www.baidu.com/s?wd=java%D1%A7%CF%B0%CA%D2";
		if (arg.length != 0) {
			referer = arg[0];
		}
		GetKeyword getKeyword = new GetKeyword();
		String searchEngine = getKeyword.getSearchEngine(referer);
		System.out.println("searchEngine:" + searchEngine);
		System.out.println("keyword:" + getKeyword.getKeyword(referer));
	}

	public String getKeyword(String refererUrl) {
		StringBuffer sb = new StringBuffer();
		if (refererUrl != null) {
			sb.append("(google\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)").append("|iask\\.[a-zA-Z]+/.+[\\&|\\?]k=([^\\&]*)")
					.append("|iask\\.[a-zA-Z]+/.+[\\&|\\?]_searchkey=([^\\&]*)")
					.append("|sogou\\.[a-zA-Z]+/.+[\\&|\\?]query=([^\\&]*)")
					.append("|163\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)")
					.append("|yahoo\\.[a-zA-Z]+/.+[\\&|\\?]p=([^\\&]*)")
					.append("|baidu\\.[a-zA-Z]+/.+[\\&|\\?]wd=([^\\&]*)")
					.append("|baidu\\.[a-zA-Z]+/.+[\\&|\\?]word=([^\\&]*)")
					.append("|lycos\\.[a-zA-Z]+/.*[\\&|\\?]query=([^\\&]*)")
					.append("|aol\\.[a-zA-Z]+/.+[\\&|\\?]encquery=([^\\&]*)")
					.append("|3721\\.[a-zA-Z]+/.+[\\&|\\?]p=([^\\&]*)")
					.append("|3721\\.[a-zA-Z]+/.+[\\&|\\?]name=([^\\&]*)")
					.append("|search\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)")
					.append("|soso\\.[a-zA-Z]+/.+[\\&|\\?]w=([^\\&]*)")
					.append("|zhongsou\\.[a-zA-Z]+/.+[\\&|\\?]w=([^\\&]*)")
					.append("|alexa\\.[a-zA-Z]+/.+[\\&|\\?]q=([^\\&]*)").append(")");
			Pattern p = Pattern.compile(sb.toString());
			Matcher m = p.matcher(refererUrl);
			return decoderKeyword(m, refererUrl);
		}
		return null;
	}

	public String decoderKeyword(Matcher m, String refererUrl) {
		String keyword = null;
		String encode = "UTF-8";
		String searchEngine = getSearchEngine(refererUrl);
		if (searchEngine != null) {
			if ((checkCode("3721|iask|sogou|163|baidu|soso|zhongsou", searchEngine)
					|| (checkCode("yahoo", searchEngine) && !checkCode("ei=utf-8", refererUrl.toLowerCase())))) {
				encode = "GBK";
			}

			if (m.find()) {
				for (int i = 2; i <= m.groupCount(); i++) {
					if (m.group(i) != null)// ������Թؼ��ַ�����õ���
					{
						try {
							keyword = URLDecoder.decode(m.group(i), encode);
						} catch (UnsupportedEncodingException e) {
							System.out.println(e.getMessage());
						}
						break;
					}
				}
			}
		}
		return keyword;
	}

public String getSearchEngine(String refUrl) {
    if(refUrl.length()>11)
    {
      //p��ƥ��������������������ʽ
      Pattern p = 
	  Pattern.compile("http:\\/\\/.*\\.(google\\.com(:\\d{1,}){0,1}\\/|"+
       "google\\.cn(:\\d{1,}){0,1}\\/|baidu\\.com(:\\d{1,}){0,1}\\/|"+
       "yahoo\\.com(:\\d{1,}){0,1}\\/|iask\\.com(:\\d{1,}){0,1}\\/|"+
       "sogou\\.com(:\\d{1,}){0,1}\\/|163\\.com(:\\d{1,}){0,1}\\/|"+
       "lycos\\.com(:\\d{1,}){0,1}\\/|aol\\.com(:\\d{1,}){0,1}\\/|"+
       "3721\\.com(:\\d{1,}){0,1}\\/|search\\.com(:\\d{1,}){0,1}\\/|"+
       "soso.com(:\\d{1,}){0,1}\\/|zhongsou\\.com(:\\d{1,}){0,1}\\/|"+
       "alexa\\.com(:\\d{1,}){0,1}\\/)");
      Matcher m = p.matcher(refUrl);
      if (m.find())
      {
        return insteadCode(m.group(1),
		"(\\.com(:\\d{1,}){0,1}\\/|\\.cn(:\\d{1,}){0,1}\\/|\\.org(:\\d{1,}){0,1}\\/)","");
      }
    }
    return "δ��������������";
}

	public String insteadCode(String str, String regEx, String code) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String s = m.replaceAll(code);
		return s;
	}

	public boolean checkCode(String regEx, String str) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
}