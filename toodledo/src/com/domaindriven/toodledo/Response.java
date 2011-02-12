package com.domaindriven.toodledo;

import org.json.JSONException;

public abstract class Response<T> {
	
	private String response;
	private Request request;
	protected Session session;
		
	protected Response(final Session session, final Request request) {
		this.request = request;
		this.session = session;
	}
	
	public abstract T parse() throws JSONException, Exception;
	
	protected String getResponse() throws JSONException, Exception {
		if(response == null) {
			response = request.execute();
		}
		
		return response;
	}
	
	public String getErrorMessage() {
		return request.getErrorMessage();
	}
	
	public int getResponseCode() {
		return request.getResponseCode();
	}
	
	public Session getSession() {
		return session;
	}
}
