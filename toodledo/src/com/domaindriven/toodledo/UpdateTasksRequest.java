package com.domaindriven.toodledo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.google.gson.stream.JsonWriter;

public class UpdateTasksRequest extends Request {

	final static String TAG = UpdateTasksRequest.class.getSimpleName();
	final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/edit.php?key=%s;";
	
	private final List<Task> tasks;

	public UpdateTasksRequest(Session session, List<Task> tasks, RestClientFactory factory) {
		super(session, factory, RequestMethod.POST);
		this.tasks = tasks;
	}

	@Override
	protected String getUrl() {
		return String.format(URL_TEMPLATE, getAuthenticationKey());
	}
	
	@Override
	public String execute() throws Exception {
		
		
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
			.name("id")
			.value(task.getId())
			.name("title")
			.value(task.getTitle())
			.name("completed")
			.value(task.getCompleted());

			if(task.getNote() != null && task.getNote().length() > 0) {
				jsonWriter.name("note")
				.value(task.getNote());
			}
			else {
				jsonWriter.name("note").nullValue();
			}
			
			if(task.getDueDate() > 0) {
				jsonWriter.name("duedate")
				.value(task.getDueDate())
				.name("duetime")
				.value(task.getDueDate());
			}
			
			jsonWriter.endObject();
		}

		jsonWriter.endArray();
		
		String json = sw.toString();
		sw.close();

		return json;	
	}
}
