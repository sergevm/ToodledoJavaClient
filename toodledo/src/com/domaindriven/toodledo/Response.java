package com.domaindriven.toodledo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Response<T> {

	private String response;
	private Request request;
	protected Session session;

	private final String TAG = getClass().getSimpleName();

	protected Response(final Session session, final Request request) {
		this.request = request;
		this.session = session;
	}

	public abstract T parse() throws JSONException, Exception;

	protected String getResponse() throws JSONException, Exception {
		if (response == null) {
			response = request.execute();
		}

		return response;
	}

	protected JSONArray createJSONArray(String json) throws JSONException {

		JSONArray jsonArray = null;

		try {
			jsonArray = new JSONArray(json);
		} catch(JSONException e) {
			JSONObject jsonObject = new JSONObject(json);

			if (jsonObject.has("errorCode") && jsonObject.has("errorDesc")) {
				session.Log(TAG, String.format("JSON response contains error: %s", json));
			}
		}

		return jsonArray;
	}

	protected JSONObject createJSONObject(String json) throws JSONException {

		JSONObject jsonObject = new JSONObject(json);

		if (jsonObject.has("errorCode") && jsonObject.has("errorDesc")) {
			session.Log(TAG, String.format("JSON response contains error: %s", json));
		}

		return jsonObject;
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
