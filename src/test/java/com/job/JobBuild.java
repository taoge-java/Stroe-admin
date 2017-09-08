/**
 * 
 */
package com.job;

import org.quartz.Scheduler;

/**
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月6日下午8:55:41
 */
public abstract class JobBuild {

	public abstract void build(Scheduler scheduler);
}
