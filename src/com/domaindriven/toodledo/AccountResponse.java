package com.domaindriven.toodledo;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class AccountResponse extends Response<Account> {

	public final static String TAG = AccountResponse.class.getSimpleName();
	
	private final Session session;

	public AccountResponse(Session session, Request request) {
		super(request);
		this.session = session;
	}

	@Override
	Account parse() {
		try {
			if (getResponse() == null || getResponse() == "") {
				Log.d(TAG, "Get request for account info returned empty response");
				return null;
			}

			JSONObject json = new JSONObject(getResponse());
			Account account = new Account(session.getUserId());
			account.setLastEditTask(json.getLong("lastedit_task"));
			account.setLastDeleteTask(json.getLong("lastdelete_task"));
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

		} catch (JSONException ex) {
			Log.e(TAG, ex.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
}
