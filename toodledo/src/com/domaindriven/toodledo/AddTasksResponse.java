package com.domaindriven.toodledo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddTasksResponse extends Response<Task[]> {

	public AddTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public Task[] parse() throws JSONException, Exception {
		JSONArray jsonTasks = new JSONArray(getResponse());

		Task[] tasks = new Task[jsonTasks.length()];

		for(int index = 0; index < jsonTasks.length(); index++) {

			JSONObject jsonTask = jsonTasks.getJSONObject(index);

			Task task = new Task();
			task.setId(jsonTask.getString("id"));
			task.setTitle(jsonTask.getString("title"));

			tasks[index] = task;
		}

		return tasks;
	}
}
