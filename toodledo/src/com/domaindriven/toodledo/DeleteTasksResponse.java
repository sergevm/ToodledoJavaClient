package com.domaindriven.toodledo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteTasksResponse extends Response<String[]> {

	protected DeleteTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public String[] parse() throws JSONException, Exception {
		
		String response = getResponse();
		JSONArray json = new JSONArray(response);
		
		String[] result = new String[json.length()];
		for(int index = 0; index < json.length(); index++) {
			JSONObject confirmed = json.getJSONObject(index);
			result[index] = confirmed.getString("id");
		}
		
		return result;
	}
}
