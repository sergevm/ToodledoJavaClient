package com.domaindriven.toodledo;

import com.domaindriven.toodledo.RestClient.RequestMethod;

public abstract class Request {

	private RestClient client;
	protected final Session authentication;

	public Request(Session authentication) {
		this.authentication = authentication;
	}

	public String execute() throws Exception {
		client = new RestClient(getUrl());
		client.Execute(RequestMethod.GET);
		return client.getResponse();
	}

	protected abstract String getUrl();
	
	protected String getAuthenticationKey(){
		return authentication.getKey();
	}
	
	protected String getErrorMessage() {
		return client.getErrorMessage();
	}
	
	protected int getResponseCode() {
		return client.getResponseCode();
	}
}