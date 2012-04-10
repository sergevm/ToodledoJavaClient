package com.domaindriven.toodledo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.google.gson.stream.JsonWriter;

public class AddTasksRequest extends Request {

	final static String TAG = AddTasksRequest.class.getSimpleName();
	final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/add.php?key=%s";

	List<Task> tasks;
	
	public AddTasksRequest(Session authentication, List<Task> tasks, RestClientFactory factory) {
		super(authentication, factory, RequestMethod.POST);
		this.tasks = tasks;
	}

	@Override
	public String execute() throws IOException, SyncException {
				
		String body = formatJSON();
		
		session.Log(TAG, getUrl());
		session.Log(TAG, body);

		addParameter("tasks", body);
		String response = super.execute();
		
		return response;
	}

	private String formatJSON() throws IOException {
		
		StringWriter sw = new StringWriter();
		JsonWriter jsonWriter = new JsonWriter(sw);
		
		jsonWriter.beginArray();
		
		for(Task task : tasks) {
			jsonWriter.beginObject();
			
			jsonWriter
			.name("title").value(task.getTitle())
			.name("completed").value(task.getCompleted());
			
			if(task.getNote() != null && task.getNote().length() > 0) {
				jsonWriter.name("note").value(task.getNote());
			}
			
			if(task.getDueDate() > 0) {
				jsonWriter.name("duedate").value(task.getDueDate())
				.name("duetime").value(task.getDueDate());
			}
			
			jsonWriter.endObject();
		}
		
		jsonWriter.endArray();
		
		String json = sw.toString();
		sw.close();
		
		return json;
	}

	@Override
	protected String getUrl() {
		return String.format(URL_TEMPLATE, getAuthenticationKey());
	}

}
