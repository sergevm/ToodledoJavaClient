package com.domaindriven.toodledo;

import java.io.IOException;

import com.google.gson.Gson;

public class AccountResponse extends Response<Account> {

	public final static String TAG = AccountResponse.class.getSimpleName();

	private final Session session;

	public AccountResponse(Session session, Request request) {
		super(session, request);
		this.session = session;
	}

	@Override
	public Account parse() throws SyncException, IOException {
		if (getResponse() == null || getResponse() == "") {
			session.Log(TAG, "Get request for account info returned empty response");
			return null;
		}
		
		Gson gson = new Gson();
		Account account = gson.fromJson(getResponse(), Account.class);

		return account;
	}
}
