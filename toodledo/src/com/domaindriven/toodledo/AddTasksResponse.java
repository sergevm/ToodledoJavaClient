package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AddTasksResponse extends Response<List<Task>> {

	private final static String TAG = AddTasksResponse.class.getSimpleName();
	
	public AddTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<Task> parse() throws Exception {
		
		session.Log(TAG, getResponse());
		
		JsonElement json = new JsonParser().parse(getResponse());
		JsonArray jsonTasks = json.getAsJsonArray();

		List<Task> tasks = new ArrayList<Task>();

		for(int index = 0; index < jsonTasks.size(); index++) {
			
			JsonObject jsonTask = (JsonObject) jsonTasks.get(index);
			
			if(isError(jsonTask)) continue;

			Task task = new Task();
			task.setId(jsonTask.getAsJsonPrimitive("id").getAsString());
			task.setTitle(jsonTask.getAsJsonPrimitive("title").getAsString());
			task.setModified(jsonTask.getAsJsonPrimitive("modified").getAsLong());
			task.setCompleted(getOptionalJsonAsLong(jsonTask, "completed"));
			task.setNote(getOptionalJsonAsString(jsonTask, "note"));
			
			task.setDueDate(getOptionalJsonAsLong(jsonTask, "duetime"));
			if(task.getDueDate() == 0) {
				task.setDueDate(getOptionalJsonAsLong(jsonTask, "duedate"));
			}


			tasks.add(task);
		}

		return tasks;
	}
}
