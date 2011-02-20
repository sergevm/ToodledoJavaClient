package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeleteTasksResponse extends Response<List<String>> {

	public DeleteTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<String> parse() throws JSONException, Exception {
		
		String response = getResponse();
		JSONArray json = new JSONArray(response);
		
		List<String> result = new ArrayList<String>();
		for(int index = 0; index < json.length(); index++) {
			JSONObject confirmed = json.getJSONObject(index);
			result.add(confirmed.getString("id"));
		}
		
		return result;
	}
}
