package com.domaindriven.toodledo;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import com.google.gson.stream.JsonWriter;

public class DeleteTasksRequest extends Request {

	final static String TAG = DeleteTasksRequest.class.getSimpleName();
	final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/delete.php?key=%s";
	
	private final List<String> keys;

	public DeleteTasksRequest(Session authentication, List<String> keys, RestClientFactory factory) {
		super(authentication, factory, RequestMethod.POST);
		this.keys = keys;
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

	private String formatJSON() throws IOException {
		StringWriter sw = new StringWriter();
		JsonWriter jsonWriter = new JsonWriter(sw);
		
		jsonWriter.beginArray();
		
		for(String key : keys) {
			jsonWriter.value(key);
		}
		
		jsonWriter.endArray();
		
		String json = sw.toString();
		sw.close();
		
		return json;
	}
}
