package com.domaindriven.toodledo;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;

public class GetUpdatedTasksRequest extends Request {

	public final static String URL_TEMPLATE = "ttp://api.toodledo.com/2/tasks/get.php?key=%s;modafter=%l;fields=id,title,modified,completed";
	private final String url;

	public GetUpdatedTasksRequest(Session session, long after) {
		super(session, RequestMethod.GET);
		this.url = String.format(URL_TEMPLATE, getAuthenticationKey(), after);
	}

	@Override
	protected String getUrl() {
		return url;
	}

}
