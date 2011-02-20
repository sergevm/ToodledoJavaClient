package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddTasksResponse extends Response<List<Task>> {

	public AddTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<Task> parse() throws JSONException, Exception {
		JSONArray jsonTasks = new JSONArray(getResponse());

		List<Task> tasks = new ArrayList<Task>();

		for(int index = 0; index < jsonTasks.length(); index++) {

			JSONObject jsonTask = jsonTasks.getJSONObject(index);

			Task task = new Task();
			task.setId(jsonTask.getString("id"));
			task.setTitle(jsonTask.getString("title"));
			task.setModified(jsonTask.getLong("modified"));

			tasks.add(task);
		}

		return tasks;
	}
}
