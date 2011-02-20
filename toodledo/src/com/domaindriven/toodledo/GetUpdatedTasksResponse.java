package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUpdatedTasksResponse extends Response<List<Task>> {

	public final static String TAG = GetUpdatedTasksResponse.class.getSimpleName();
	
	public GetUpdatedTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<Task> parse() throws JSONException, Exception {
		
		JSONArray jsonArray = createJSONArray(getResponse());
		JSONObject info = jsonArray.getJSONObject(0);
		
		long updatedCount = info.getLong("num");
		
		session.Log(TAG, String.format(
				"Number of modified tasks returned by Toodledo: %d", updatedCount));
				
		List<Task> tasks = new ArrayList<Task>();
		
		if(updatedCount == 0) {
			return tasks;
		}
		
		for(int index = 1; index <= jsonArray.length() - 1; index++) {
			
			JSONObject json = jsonArray.getJSONObject(index);
			
			Task task = new Task();
			task.setId(json.getString("id"));
			task.setTitle(json.getString("title"));
			task.setModified(json.getLong("modified"));
			
			tasks.add(task);
		}
		
		return tasks;
	}
}
