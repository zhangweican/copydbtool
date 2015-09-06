package com.colee.copydbtool.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;

import com.cheyooh.tools.cfg.EnvUtil;
import com.cheyooh.tools.log.Logger;
import com.colee.copydbtool.Cfg;

public class MySqlDBUtils {
	private static Logger logger = new Logger(MySqlDBUtils.class);
	public static String Driver_MySql  = "com.mysql.jdbc.Driver";
	public static String Driver_SqlServer = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	public static Connection getConnection(String drivername,String dburl,String name,String password)  {
		Connection conn = null;
		try {
			Class.forName(drivername);
			conn = DriverManager.getConnection(dburl,name,password);  
		} catch (Exception e) {
			logger.error("数据库【"+dburl+" | "+name+"/"+password+"】连接异常...",e);
		}
		return conn;
	}
	
	private static Connection getConnectionByEnvValue(String drivername,String dburl,String name,String pwd) {
		String dname = EnvUtil.getValue(drivername);
		if(StringUtils.isEmpty(drivername) || StringUtils.isEmpty(dname)) {
			dname = Driver_MySql;    //默认为mysql数据库连接
		}
		return getConnection(dname,EnvUtil.getValue(dburl),EnvUtil.getValue(name),EnvUtil.getValue(pwd));
	}
	
	public static Connection getSqlServerConnection() {
		return getConnectionByEnvValue(Cfg.task_sqlserver_drivername,Cfg.task_sqlserver_dburl,
				Cfg.task_sqlserver_name,Cfg.task_sqlserver_password);
	}
	
	public static Connection getMysqlConnection(){
		return getConnectionByEnvValue(Cfg.task_mysql_drivername,Cfg.task_mysql_dburl,
				Cfg.task_mysql_name,Cfg.task_mysql_password);
	}
	
	public static void CloseDbResource(ResultSet rs,Statement st,Connection cn){
		try{
			if(rs != null)rs.close();
			if(st != null)st.close();
			if(cn != null)cn.close();
		}catch(Exception ex){
			logger.error("数据库资源关闭异常....",ex);
		}
	}
	
}
