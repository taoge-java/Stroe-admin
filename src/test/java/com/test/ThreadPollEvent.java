/**
 * 
 */
package com.test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月29日下午4:30:13
 */
public class ThreadPollEvent implements Event{

	// 加入event队列锁，防止多线程加入Event时，出现错误。
	ReentrantLock pushLock = new ReentrantLock();
		
	private ExecutorService executorService;
	
	private ConcurrentLinkedQueue<MyTask> mainQueue;
	
	public ThreadPollEvent(){
		mainQueue = new ConcurrentLinkedQueue<MyTask>();
		//executorService = Executors.newFixedThreadPool(1);
		executorService = new ThreadPoolExecutor(1
				, 5,
                60L, TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
	}
	
	public void doEvent(Event event){
		MyTask task = new MyTask(event);
		mainQueue.offer(task);
		checkQueue();
	//	executorService.submit();
		System.out.println("executorService:"+ executorService);
	}
	
	/**
	 * 
	 */
	private void checkQueue() {
		pushLock.lock();
		MyTask task = mainQueue.poll();
		executorService.execute(task);
		pushLock.unlock();
	}

	public void shutdown(){
		if(mainQueue.isEmpty()){
			executorService.shutdown();
		}
		executorService.shutdown();
	}

	@Override
	public int getBuyCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}
