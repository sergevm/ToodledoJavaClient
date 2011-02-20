package com.domaindriven.toodledo;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;

public class GetDeletedTasksRequest extends Request {

	public final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/deleted.php?key=%s;after=%d";
	
	private final String url;

	public GetDeletedTasksRequest(Session session, long after) {
		super(session, RequestMethod.GET);
		this.url = String.format(URL_TEMPLATE, getAuthenticationKey(), after);
	}

	@Override
	protected String getUrl() {
		return this.url;
	}
}
