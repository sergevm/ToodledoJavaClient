package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DeleteTasksResponse extends Response<List<String>> {

	public final static String TAG = DeleteTasksResponse.class.getSimpleName();
	
	public DeleteTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<String> parse() throws Exception {
		
		session.Log(TAG, getResponse());

		List<String> result = new ArrayList<String>();

		JsonElement json = new JsonParser().parse(getResponse());
		
		if(json.isJsonArray() == false) {
			return result;
		}
		
		JsonArray jsonTasks = json.getAsJsonArray();
		
		for(int index = 0; index < jsonTasks.size(); index++) {
			
			JsonObject jsonObject = jsonTasks.get(index).getAsJsonObject();
			
			if(isError(jsonObject)) continue;
			
			String jsonTaskId = jsonObject.getAsJsonPrimitive("id").getAsString();
			result.add(jsonTaskId);
		}
		
		return result;
	}
}
