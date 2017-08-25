package com.stroe.admin.util;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author zengjintao
 * @version 1.0
 * 2017年4月七日上午8:05
 */
public class XMLUtil {

	/**
	 * xml转成map
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlTomap(HttpServletRequest request){
		Map<String,String> map=new HashMap<String, String>();
		try {
		    InputStream in=request.getInputStream();
		    SAXReader reader=new SAXReader();
		    Document document= reader.read(in);
		    Element element= document.getRootElement();
		    List<Element> list=element.elements();
		    for(Element e:list){
		    	map.put(e.getName(), e.getTextTrim());
		    }
		    return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object mapToBean(HttpServletRequest request){
		 try {
			InputStream in=request.getInputStream();
			InputStreamReader read=new InputStreamReader(in);
			char[] bytes=new char[10];
			int flag=0;
			StringBuffer buffer=new StringBuffer();
			while((flag=read.read(bytes, 0, 10))!=-1){
				buffer.append(new String(bytes,0,flag));
			}
			System.err.println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
