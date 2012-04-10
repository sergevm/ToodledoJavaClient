package com.domaindriven.toodledo;

import java.io.IOException;
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
	public List<Task> parse() throws SyncException, IOException {
		
		session.Log(TAG, getResponse());

		List<Task> tasks = new ArrayList<Task>();

		JsonElement element = new JsonParser().parse(getResponse());
		
		if(HandleErrors(element)) return tasks;
		
		if(element.isJsonArray() == false) return tasks;

		JsonArray jsonTasks = element.getAsJsonArray();

		for(int index = 0; index < jsonTasks.size(); index++) {

			JsonObject jsonTask = jsonTasks.get(index).getAsJsonObject();
			
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
