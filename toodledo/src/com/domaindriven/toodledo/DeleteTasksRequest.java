package com.domaindriven.toodledo;

import java.util.List;

import org.json.JSONException;
import org.json.JSONStringer;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;

public class DeleteTasksRequest extends Request {

	final static String TAG = DeleteTasksRequest.class.getSimpleName();
	final static String URL_TEMPLATE = "http://api.toodledo.com/2/tasks/delete.php?key=%s";
	
	private final List<String> keys;

	public DeleteTasksRequest(Session authentication, List<String> keys) {
		super(authentication, RequestMethod.POST);
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

	private String formatJSON() throws JSONException {
		
		JSONStringer jsonWriter = new JSONStringer();
		jsonWriter.array();
		
		for(String key : keys) {
			jsonWriter.value(key);
		}
		
		jsonWriter.endArray();
		
		return jsonWriter.toString();
	}
}
