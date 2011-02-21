package com.domaindriven.toodledo;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;

public class GetUpdatedTasksRequest extends Request {

	private final static String TAG = GetUpdatedTasksRequest.class.getSimpleName();
	public final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/get.php?key=%s;modafter=%d";
	
	private final String url;

	public GetUpdatedTasksRequest(Session session, long after) {
		super(session, RequestMethod.GET);
		this.url = String.format(URL_TEMPLATE, getAuthenticationKey(), after);
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	public String execute() throws Exception {
		session.Log(TAG, getUrl());
		return super.execute();
	}
}
