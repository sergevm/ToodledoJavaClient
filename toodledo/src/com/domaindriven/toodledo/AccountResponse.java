package com.domaindriven.toodledo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountResponse extends Response<Account> {

	public final static String TAG = AccountResponse.class.getSimpleName();

	private final Session session;

	public AccountResponse(Session session, Request request) {
		super(session, request);
		this.session = session;
	}

	@Override
	public Account parse() throws JSONException, Exception {
		if (getResponse() == null || getResponse() == "") {
			session.Log(TAG, "Get request for account info returned empty response");
			return null;
		}

		JSONObject json = new JSONObject(getResponse());
		Account account = new Account(session.getUserId());

		long lastEditTask = json.getLong("lastedit_task");
		Date lastEditDate = new Date(lastEditTask * 1000);
		session.Log(TAG, String.format("Last edit date reported by Toodledo is %s", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lastEditDate)));
		account.setLastEditTask(lastEditTask);

		long lastDeleteTask = json.getLong("lastdelete_task");
		Date lastDeleteDate = new Date(lastDeleteTask * 1000);
		session.Log(TAG, String.format("Last delete date reported by Toodledo is %s", new SimpleDateFormat("dd/MM/yyyy HH:mm").format(lastDeleteDate)));
		account.setLastDeleteTask(lastDeleteTask);

		switch (json.getInt("dateformat")) {
		case 0:
			account.setDateFormat("MM yyyy");
			break;
		case 1:
			account.setDateFormat("MM/dd/yyyy");
			break;
		case 2:
			account.setDateFormat("dd/MM/yyyy");
			break;
		case 3:
			account.setDateFormat("yyyy-MM-dd");
			break;
		}

		return account;
	}
}
