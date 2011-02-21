package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetDeletedTasksResponse extends Response<List<String>> {

	private final static String TAG = GetDeletedTasksResponse.class.getSimpleName();
	
	public GetDeletedTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<String> parse() throws JSONException, Exception {
		
		session.Log(TAG, getResponse());
		
		List<String> ids = new ArrayList<String>();
		
		JSONArray jsonArray = createJSONArray(getResponse());
				
		JSONObject info = jsonArray.getJSONObject(0);
		int deletedCount = new Integer(info.getString("num"));
		
		if(deletedCount == 0)
			return ids;
				
		for(int index = 1; index <= deletedCount - 1; index++) {
			JSONObject item = jsonArray.getJSONObject(index);
			ids.add(item.getString("id"));
		}
		
		return ids;
	}
}
