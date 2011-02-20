package com.domaindriven.toodledo;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;

public class AddTasksRequest extends Request {

	final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/add.php?key=%s";

	List<Task> tasks;
	
	public AddTasksRequest(Session authentication, List<Task> tasks) {
		super(authentication, RequestMethod.POST);
		this.tasks = tasks;
	}
	
	@Override
	public String execute() throws Exception {
		String body = formatJSON();
		addParameter("tasks", body);
		String response = super.execute();
		
		return response;
	}

	private String formatJSON() throws JSONException {
		
		JSONStringer jsonWriter = new JSONStringer();
		jsonWriter.array();
		
		for(Task task : tasks) {
			jsonWriter.object().key("title").value(task.getTitle()).endObject();
		}
		
		jsonWriter.endArray();
		
		return jsonWriter.toString();
	}

	@Override
	protected String getUrl() {
		return String.format(URL_TEMPLATE, getAuthenticationKey());
	}

}
