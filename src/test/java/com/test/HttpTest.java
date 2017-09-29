/**
 * 
 */
package com.test;

import com.jfinal.kit.Kv;
import com.stroe.admin.util.HttpClientUtil;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月28日下午8:02:26
 */
public class HttpTest {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		long statr = System.currentTimeMillis();
		EventFactory factory = new EventFactory();
		
		ThreadPollEvent threadPollEvent = new ThreadPollEvent();
		factory.doEvent(new SimleEvent(10));
		factory.doEvent(new SimleEvent(20));
		factory.doEvent(new SimleEvent(30));
		factory.doEvent(new SimleEvent(5));
		factory.doEvent(new SimleEvent(5));
		factory.doEvent(new SimleEvent(5));
//		factory.shutdown();
//		doEvent(10);
//		doEvent(20);
//		doEvent(30);
//		doEvent(5);
//		doEvent(5);
//		doEvent(5);
		System.out.println("消耗时间:");
		System.err.println(System.currentTimeMillis()-statr);
	}
	
	@SuppressWarnings("unused")
	private static void doEvent(int buyNumber){
		new Thread(new Runnable() {
			@Override
			public  void run() {
				post(buyNumber);
			}
		}).start();
	}
	
	@SuppressWarnings("unchecked")
	private static synchronized void post(int buyNumber){
		String context = HttpClientUtil.httpPostRequest("http://localhost:8082/Stroe-Admin/information/test",Kv.by("name", String.valueOf(buyNumber)));
		System.out.println(context);
	}
	
}
