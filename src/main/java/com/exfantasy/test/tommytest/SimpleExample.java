package com.exfantasy.test.tommytest;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExample {
	private Logger log = LoggerFactory.getLogger(SimpleExample.class);
	
	private int intervalInSeconds = 5;
	
	private void run() throws Exception {
		log.info("------- Initializing Scheduler ----------------------");
		
		SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();

	    log.info("------- Initialization Scheduler Complete -----------");
	    
	    log.info("------- Creating Job and Trigger  -------------------");
	    
	    JobKey jobKey = new JobKey("Job1", "Group1"); 
	    JobDetail job = 
	    	newJob(HelloJob.class).withIdentity(jobKey).build();
	    
	    TriggerKey triggerKey = new TriggerKey("Trigger1", "Group1");
	    Trigger trigger = 
	    	newTrigger().withIdentity(triggerKey)
	    		.startNow().withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(intervalInSeconds)).build();
	    
	    sched.getListenerManager().addJobListener(new HelloJobListener(), KeyMatcher.keyEquals(jobKey));
	    log.info("------- Add Job Listener done  -------------------");
	    
	    sched.scheduleJob(job, trigger);
	    log.info(job.getKey() + " will run immediately with interval " + intervalInSeconds + " seconds");
	    
	    sched.start();
	    
	    log.info("------- Started Scheduler -----------------");
	    
	    log.info("------- Waiting 65 seconds... -------------");
	    try {
	      // wait 65 seconds to show job
	      Thread.sleep(65L * 1000L);
	      // executing...
	    } catch (Exception e) {}

	    // shut down the scheduler
	    log.info("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    log.info("------- Shutdown Complete -----------------");
	}
	
	public static void main(String[] args) throws Exception {
		SimpleExample example = new SimpleExample();
		example.run();
	}
}
