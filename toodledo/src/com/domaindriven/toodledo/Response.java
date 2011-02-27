package com.domaindriven.toodledo;

import com.google.gson.JsonObject;

public abstract class Response<T> {

	private String response;
	private Request request;
	protected Session session;

	protected Response(final Session session, final Request request) {
		this.request = request;
		this.session = session;
	}

	public abstract T parse() throws Exception;

	protected String getResponse() throws Exception {
		if (response == null) {
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

	/**
	 * @param jsonObject
	 * @return
	 */
	protected boolean isError(JsonObject jsonObject) {
		return jsonObject.has("errorCode") && jsonObject.has("errorDesc");
	}
}
