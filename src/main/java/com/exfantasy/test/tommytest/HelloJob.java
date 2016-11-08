package com.exfantasy.test.tommytest;
/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 


import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * This is just a simple job that says "Hello" to the world.
 * </p>
 * 
 * @author Bill Kratzer
 */
@DisallowConcurrentExecution
public class HelloJob implements InterruptableJob {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);
    
    private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * <p>
     * Empty constructor for job initilization
     * </p>
     * <p>
     * Quartz requires a public empty constructor so that the
     * scheduler can instantiate the class whenever it needs.
     * </p>
     */
    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext context)
        throws JobExecutionException {

        // Say Hello to the World and display the date/time
        _log.info("Say Hello World at " + timeFormat.format(new Date()));
        
        _log.info("Sleep 10 seconds...");
        try {
			Thread.sleep(10L * 1000L);
		} catch (InterruptedException e) {}
    }

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		// TODO Auto-generated method stub
		
	}

}