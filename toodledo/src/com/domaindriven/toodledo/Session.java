package com.domaindriven.toodledo;

public interface Session {

	public abstract boolean isExpired();
	public abstract String getUserId();
	public abstract String getKey();
	
	public void Log(final String tag, final String logmessage);
}