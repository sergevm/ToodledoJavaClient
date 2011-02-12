package com.domaindriven.toodledo;

import java.util.HashMap;

import com.domaindriven.toodledo.RestClient.RequestMethod;

public abstract class Request {

	private RestClient client;
	private final RequestMethod method;
	protected final Session authentication;
	private final HashMap<String,String> parameters;

	public Request(Session authentication, RequestMethod method) {
		this.method = method;
		this.authentication = authentication;
		this.parameters = new HashMap<String, String>();
	}

	public String execute() throws Exception {
		client = new RestClient(getUrl());

		for(String key : parameters.keySet()){
			client.AddParam(key, parameters.get(key));
		}
		
		client.Execute(method);
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
	
	protected void addParameter(final String name, final String value) {
		parameters.put(name, value);
	}
}