package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateTasksResponse extends Response<List<Task>> {

	final static String TAG = UpdateTasksResponse.class.getSimpleName();
	
	public UpdateTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<Task> parse() throws JSONException, Exception {
		
		session.Log(TAG, getResponse());
		
		JSONArray jsonTasks = new JSONArray(getResponse());

		List<Task> tasks = new ArrayList<Task>();

		for(int index = 0; index < jsonTasks.length(); index++) {

			JSONObject jsonTask = jsonTasks.getJSONObject(index);

			Task task = new Task();
			task.setId(jsonTask.getString("id"));
			task.setTitle(jsonTask.getString("title"));
			task.setModified(jsonTask.getLong("modified"));
			task.setCompleted(jsonTask.getLong("completed") == 0 ? false : true);

			tasks.add(task);
		}

		return tasks;
	}
}
