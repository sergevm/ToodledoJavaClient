package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UpdateTasksResponse extends Response<List<Task>> {

	public final static String TAG = UpdateTasksResponse.class.getSimpleName();
	
	public UpdateTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<Task> parse() throws Exception {
		
		session.Log(TAG, getResponse());

		List<Task> tasks = new ArrayList<Task>();

		JsonElement element = new JsonParser().parse(getResponse());
		if(element.isJsonArray() == false) return tasks;

		JsonArray jsonTasks = element.getAsJsonArray();

		for(int index = 0; index < jsonTasks.size(); index++) {

			JsonObject jsonTask = jsonTasks.get(index).getAsJsonObject();
			
			if(isError(jsonTask)) continue;

			Task task = new Task();
			task.setId(jsonTask.getAsJsonPrimitive("id").getAsString());
			task.setTitle(jsonTask.getAsJsonPrimitive("title").getAsString());
			task.setModified(jsonTask.getAsJsonPrimitive("modified").getAsLong());
			task.setCompleted(GsonHelpers.parseBoolean(jsonTask, "completed"));

			tasks.add(task);
		}

		return tasks;
	}
}
