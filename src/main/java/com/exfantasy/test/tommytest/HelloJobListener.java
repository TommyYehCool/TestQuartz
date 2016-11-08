package com.exfantasy.test.tommytest;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class HelloJobListener implements JobListener {

	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		System.out.println("<jobToBeExecuted> Job : " + jobName + " is going to start...");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println("jobExecutionVetoed");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobName = context.getJobDetail().getKey().toString();
		System.out.println("<jobWasExecuted> Job : " + jobName + " is finished...");

		if (jobException != null && !"".equals(jobException.getMessage())) {
			System.out.println("Exception thrown by: " + jobName
				+ " Exception: " + jobException.getMessage());
		}
	}
}
