package com.domaindriven.toodledo;

import android.util.Log;


public class Account {
	
	private String userId;
	private long lastEditTask;
	private long lastDeleteTask;
	private String dateFormat;
	
	private static final String TAG = Account.class.getSimpleName();
	
	public static Account create(final Session authentication) {

		Log.v(TAG, "Getting account info");
		
		try {
			AccountRequest request = new AccountRequest(authentication);
			AccountResponse response = new AccountResponse(authentication, request);
			Log.v(TAG, "Account info retrieved");
			return response.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	Account(final String userId) {
		this.userId = userId;
	}
	
	public void setLastEditTask(long lastEditTask) {
		this.lastEditTask = lastEditTask;
	}
	
	public long getLastEditTask() {
		return lastEditTask;
	}
	
	public void setLastDeleteTask(long lastDeleteTask) {
		this.lastDeleteTask = lastDeleteTask;
	}
	
	public long getLastDeleteTask() {
		return lastDeleteTask;
	}

	public String getUserId() {
		return userId;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getDateFormat() {
		return dateFormat;
	}
}
