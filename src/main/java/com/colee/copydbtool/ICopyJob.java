package com.colee.copydbtool;

public interface ICopyJob {
	
	public void run() throws Exception;
	public String getName();
}
