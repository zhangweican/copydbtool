package com.colee.copydbtool;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.cheyooh.tools.log.Logger;
import com.colee.copydbtool.job.UserDBCopy;

public class CopyScheduler {
	private static Logger logger = new Logger(CopyScheduler.class);
	
	public static void main(String[] args) {
		try {
			logger.info("开始启动任务");
			CopyScheduler sch = new CopyScheduler();
			sch.run();
		} catch (Exception ex) {
			logger.error("运行任务失败", ex);
		} 
	}
	
	public void run() throws Exception {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();		
		JobBuilder jb = JobBuilder.newJob(CopyJob.class);
		
		List<ICopyJob> list = new ArrayList<ICopyJob>();
		list.add(new UserDBCopy());
		
		JobDataMap map = new JobDataMap();
		map.put("runObject", list);			
		jb.setJobData(map);
		
		
		String cronexp = Cfg.getCronExp();
		if(StringUtils.isEmpty(cronexp)){
			logger.error("没有设置调度表达式");
			return ;
		}
		logger.info("设置的调度表达式为：" + cronexp);
		
		JobDetail job = jb.withIdentity("job-copy", "group1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger-copy", "group1").withSchedule(CronScheduleBuilder.cronSchedule(cronexp)).build();
		sched.scheduleJob(job, trigger);
		sched.start();
	}

}
