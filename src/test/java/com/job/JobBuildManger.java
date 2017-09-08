/**
 * 
 */
package com.job;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taoge
 * @version 1.0
 * @create_at 2017年9月6日下午8:54:25
 */
public class JobBuildManger {
	
	private List<JobBuild> jobs = new ArrayList<>();
	
	public void add(JobBuild jobBuild){
		jobs.add(jobBuild);
	}
}
