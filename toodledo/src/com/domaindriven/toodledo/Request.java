package com.domaindriven.toodledo;

import java.util.HashMap;

public abstract class Request {

	private RestClient client;
	private final RequestMethod method;
	protected final Session session;
	private final HashMap<String, String> parameters;
	private final RestClientFactory clientFactory;

	public Request(Session session, RestClientFactory clientFactory, RequestMethod method) {
		this.method = method;
		this.clientFactory = clientFactory;
		this.session = session;
		this.parameters = new HashMap<String, String>();
	}

	public String execute() throws Exception {
		client = clientFactory.create(getUrl());

		for (String key : parameters.keySet()) {
			client.AddParam(key, parameters.get(key));
		}

		client.Execute(method);
		return client.getResponse();
	}

	protected abstract String getUrl();

	protected String getAuthenticationKey() {
		return session.getKey();
	}

	protected String getErrorMessage() {
		return client.getErrorMessage();
	}

	protected int getResponseCode() {
		return client.getResponseCode();
	}

	protected void addParameter(final String name, final String value) {
		parameters.put(name, value);
	}
}