package com.domaindriven.toodledo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetDeletedTasksResponse extends Response<List<String>> {

	public final static String TAG = GetDeletedTasksResponse.class.getSimpleName();
	
	public GetDeletedTasksResponse(Session session, Request request) {
		super(session, request);
	}

	@Override
	public List<String> parse() throws Exception {
		
		session.Log(TAG, getResponse());
		
		List<String> ids = new ArrayList<String>();
		
		JsonElement element = new JsonParser().parse(getResponse());
		if(element.isJsonArray() == false) {
			return ids;
		}
		
		JsonArray jsonArray = element.getAsJsonArray();
		
		JsonObject info = jsonArray.get(0).getAsJsonObject();
		
		int deletedCount = new Integer(info.getAsJsonPrimitive("num").getAsInt());
		
		if(deletedCount == 0)
			return ids;
				
		for(int index = 1; index <= deletedCount; index++) {
			JsonObject item = jsonArray.get(index).getAsJsonObject();
			
			if(isError(item)) continue;
			
			ids.add(item.getAsJsonPrimitive("id").getAsString());
		}
		
		return ids;
	}
}
