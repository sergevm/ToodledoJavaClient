package com.domaindriven.toodledo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUpdatedTasksResponse extends Response<Task[]> {

	public GetUpdatedTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public Task[] parse() throws JSONException, Exception {
		
		JSONArray jsonArray = new JSONArray(getResponse());
		
		Task[] tasks = new Task[jsonArray.length()];
		
		for(int index = 0; index < jsonArray.length(); index++) {
			
			JSONObject json = jsonArray.getJSONObject(index);
			Task task = new Task();
			task.setId(json.getString("id"));
			task.setTitle(json.getString("title"));
			task.setModified(json.getLong("modified"));
			
			tasks[index] = task;
		}
		
		return tasks;
	}
}
