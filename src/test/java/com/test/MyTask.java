/**
 * 
 */
package com.test;


import com.jfinal.kit.Kv;
import com.stroe.admin.util.HttpClientUtil;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月28日下午9:05:46
 */
public class MyTask extends Thread{

	Event event;
	
	public MyTask(Event event){
		this.event = event;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		System.out.println("购买数量:"+event.getBuyCount());
		System.err.println(Thread.currentThread().getName()+"开始执行");
		String context = HttpClientUtil.httpPostRequest("http://localhost:8082/Stroe-Admin/information/test",Kv.by("name", String.valueOf(event.getBuyCount())));
		System.out.println(context);
	}
}
