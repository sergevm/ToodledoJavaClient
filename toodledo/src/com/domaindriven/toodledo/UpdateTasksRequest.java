package com.domaindriven.toodledo;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;

public class UpdateTasksRequest extends Request {

	final static String TAG = UpdateTasksRequest.class.getSimpleName();
	final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/edit.php?key=%s;";
	
	private final List<Task> tasks;

	public UpdateTasksRequest(Session session, List<Task> tasks) {
		super(session, RequestMethod.POST);
		this.tasks = tasks;
	}

	@Override
	protected String getUrl() {
		return String.format(URL_TEMPLATE, getAuthenticationKey());
	}
	
	@Override
	public String execute() throws Exception {
		
		session.Log(TAG, getUrl());
		
		String body = formatJSON();
		addParameter("tasks", body);
		String response = super.execute();
		
		return response;
	}

	private String formatJSON() throws JSONException {
		JSONStringer jsonWriter = new JSONStringer();
		jsonWriter.array();

		for(Task task : tasks) {
			jsonWriter
			.object()
			.key("id")
			.value(task.getId())
			.key("title")
			.value(task.getTitle())
			.endObject();
		}

		jsonWriter.endArray();

		return jsonWriter.toString();	
	}
}
