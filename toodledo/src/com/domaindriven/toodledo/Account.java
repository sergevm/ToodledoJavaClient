package com.domaindriven.toodledo;

import java.io.IOException;

import com.google.gson.annotations.SerializedName;


public class Account {

	@SerializedName("userid") private String userId;
	@SerializedName("lastedit_task") private long lastEditTask;
	@SerializedName("lastdelete_task") private long lastDeleteTask;
	@SerializedName("dateformat") private int dateFormat;

	private static final String TAG = Account.class.getSimpleName();

	public static Account create(final Session session, RestClientFactory factory) throws IOException, SyncException {

		session.Log(TAG, "Getting account info");

		AccountRequest request = new AccountRequest(session, factory);
		AccountResponse response = new AccountResponse(session, request);
		Account account = response.parse();
		
		if(account == null) {
			throw new SyncException("Unable to retrieve the Toodledo account.");
		}
		
		return account;
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
