package com.domaindriven.toodledo;

public class GetDeletedTasksRequest extends Request {

	public final static String TAG = GetDeletedTasksRequest.class.getSimpleName();
	public final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/deleted.php?key=%s;after=%d";
	
	private final String url;

	public GetDeletedTasksRequest(Session session, long after, RestClientFactory factory) {
		super(session, factory, RequestMethod.GET);
		this.url = String.format(URL_TEMPLATE, getAuthenticationKey(), after);
	}

	@Override
	protected String getUrl() {
		return this.url;
	}
	
	@Override
	public String execute() throws Exception {
		session.Log(TAG, getUrl());
		return super.execute();
	}
}
