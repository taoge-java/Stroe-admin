/**
 * 
 */
package com.test;

/**
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年9月28日下午9:49:12
 */
public class SimleEvent implements Event{

	private int buyNumber;
	public SimleEvent(int buyNumber){
		this.buyNumber = buyNumber;
	}
	@Override
	public int getBuyCount() {
		return buyNumber;
	}

}
