package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetUpdatedTasksResponse extends Response<List<Task>> {

	public final static String TAG = GetUpdatedTasksResponse.class.getSimpleName();
	
	public GetUpdatedTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<Task> parse() throws Exception {
		
		session.Log(TAG, getResponse());
		
		List<Task> tasks = new ArrayList<Task>();
		
		JsonElement element = new JsonParser().parse(getResponse());
		if(element.isJsonArray() == false) {
			return tasks;
		}
		
		JsonArray jsonArray = element.getAsJsonArray();
		
		JsonObject info = jsonArray.get(0).getAsJsonObject();
		int updatedCount = new Integer(info.getAsJsonPrimitive("num").getAsInt());
		
		if(updatedCount == 0) return tasks;
				
		for(int index = 1; index <= updatedCount; index++) {
			
			JsonObject item = jsonArray.get(index).getAsJsonObject();
			
			if(isError(item)) continue;
			
			Task task = new Task();
			task.setId(item.getAsJsonPrimitive("id").getAsString());
			task.setTitle(item.getAsJsonPrimitive("title").getAsString());
			task.setModified(item.getAsJsonPrimitive("modified").getAsLong());
			
			tasks.add(task);
		}
	
		return tasks;		
	}
}
