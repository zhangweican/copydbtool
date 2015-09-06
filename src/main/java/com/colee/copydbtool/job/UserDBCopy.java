package com.colee.copydbtool.job;

import com.cheyooh.tools.log.Logger;
import com.colee.copydbtool.ICopyJob;

public class UserDBCopy implements ICopyJob {
	private static Logger logger = new Logger(UserDBCopy.class);
	
	public void run() throws Exception {
		logger.info(getName() + " 冬冬");
	}

	public String getName() {
		return "copy User";
	}

}
