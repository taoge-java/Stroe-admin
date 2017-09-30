/**
 * 
 */
package com.stroe.admin.util;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月29日下午3:42:19
 */
public class XmlKit {

	public void parseXml(String fileName){
		SAXReader saxReader = new SAXReader();
		InputStream inputStream = getClassLoader().getResourceAsStream(fileName);
		Document document;
		try {
			document = saxReader.read(inputStream);
			Element root = document.getRootElement();//获取根节点  
			getElement(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void getElement(Element node){
	    System.out.println("--------------------");  
		List<Attribute> attributes = node.attributes();
		for(Attribute attribute : attributes){
			System.out.println("属性名称:"+attribute.getName());
		}
		List<Element> elements = node.elements();
		for(Element e : elements){
			System.out.println("子节点名称:"+e.getName());
			System.out.println("子节点内容:"+e.getTextTrim());
			getElement(e);//递归调用
	    }
	}
	
	private ClassLoader getClassLoader() {
		ClassLoader ret = Thread.currentThread().getContextClassLoader();
		return ret != null ? ret : getClass().getClassLoader();
	}
}
