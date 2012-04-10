package com.domaindriven.toodledo;

import java.io.IOException;

public class GetUpdatedTasksRequest extends Request {

	private final static String TAG = GetUpdatedTasksRequest.class.getSimpleName();
	public final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/get.php?key=%s;modafter=%d;fields=duedate,duetime,note";
	
	private final String url;

	public GetUpdatedTasksRequest(Session session, long after, RestClientFactory factory) {
		super(session, factory, RequestMethod.GET);
		this.url = String.format(URL_TEMPLATE, getAuthenticationKey(), after);
	}

	@Override
	protected String getUrl() {
		return url;
	}

	@Override
	public String execute() throws IOException, SyncException {
		session.Log(TAG, getUrl());
		return super.execute();
	}
}
