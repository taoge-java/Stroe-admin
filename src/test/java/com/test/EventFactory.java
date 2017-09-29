/**
 * 
 */
package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月27日下午9:52:37
 */
public class EventFactory {

	private ExecutorService executorService;
	
	
	public EventFactory(){
		executorService = Executors.newFixedThreadPool(1);
	}
	
	public void doEvent(Event event){
		executorService.submit(new MyTask(event));
		System.out.println("executorService:"+ executorService);
	}
	
	public void shutdown(){
		System.out.println("executorService:"+ executorService);
		executorService.shutdown();
	}
}
