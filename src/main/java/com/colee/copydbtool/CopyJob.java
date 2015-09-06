package com.colee.copydbtool;

import java.util.List;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

import com.cheyooh.tools.log.Logger;

/**
 * cmd命令定义执行操作
 */
@PersistJobDataAfterExecution  
@DisallowConcurrentExecution  
public class CopyJob implements Job {
	private Logger logger = new Logger(CopyJob.class);


	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("开始运行拷贝任务");
		JobDataMap map = context.getMergedJobDataMap();
		List<ICopyJob> list = (List<ICopyJob>) map.get("runObject");
		if(list != null){
			int i = 1;
			for(ICopyJob job : list){
				try {
					logger.info("[" + i +"][" + job.getName() + "] 开始执行");
					job.run();
					logger.info("[" + i +"][" + job.getName() + "] 执行成功");
				} catch (Exception e) {
					logger.error("[" + i +"][" + job.getName() + "] 执行失败", e);
				}
			}
		}
		
		logger.info("拷贝任务执行成功");
	}
	


}
