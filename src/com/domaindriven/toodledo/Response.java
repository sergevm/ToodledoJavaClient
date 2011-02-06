package com.domaindriven.toodledo;

public abstract class Response<T> {
	
	private String response;
	private Request request;
		
	protected Response(Request request) {
		this.request = request;
	}
	
	abstract T parse();
	
	protected String getResponse() throws Exception {
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
}
