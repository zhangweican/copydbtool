package com.colee.copydbtool;

import com.cheyooh.tools.cfg.EnvUtil;

public class Cfg {
	public static final String Key_CronExp = "colee.cron.express"; 
	public static final String task_sqlserver_drivername = "task.sqlserver.drivername"; 
	public static final String task_sqlserver_dburl = "task.sqlserver.dburl"; 
	public static final String task_sqlserver_name = "task.sqlserver.name"; 
	public static final String task_sqlserver_password = "task.sqlserver.password"; 
	public static final String task_mysql_drivername = "task.mysql.drivername"; 
	public static final String task_mysql_dburl = "task.mysql.dburl"; 
	public static final String task_mysql_name = "task.mysql.name"; 
	public static final String task_mysql_password = "task.mysql.password"; 
	
	public static String getCronExp(){
		return  EnvUtil.getValue(Key_CronExp);
	}
}
