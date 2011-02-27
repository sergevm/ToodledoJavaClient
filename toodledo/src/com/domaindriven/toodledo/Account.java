package com.domaindriven.toodledo;

import com.google.gson.annotations.SerializedName;


public class Account {

	@SerializedName("userid") private String userId;
	@SerializedName("lastedit_task") private long lastEditTask;
	@SerializedName("lastdelete_task") private long lastDeleteTask;
	@SerializedName("dateformat") private int dateFormat;

	private static final String TAG = Account.class.getSimpleName();

	public static Account create(final Session session, RestClientFactory factory) {

		session.Log(TAG, "Getting account info");

		try {
			AccountRequest request = new AccountRequest(session, factory);
			AccountResponse response = new AccountResponse(session, request);
			session.Log(TAG, "Account info retrieved");
			return response.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Account() {
	}
	
	public Account(final String userId) {
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

	public String getDateFormat() {
		switch (dateFormat) {
		case 0:
			return "MM yyyy";
		case 1:
			return "MM/dd/yyyy";
		case 2:
			return "dd/MM/yyyy";
		case 3:
		default:
			return "yyyy-MM-dd";
		}
	}
}
