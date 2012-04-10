package com.domaindriven.toodledo;

import java.io.IOException;

import com.domaindriven.toodledo.ToodledoSession.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SessionTokenResponse extends Response<String> {
	
	private final static String TAG = SessionTokenResponse.class.getSimpleName();
	private Log log;

	public SessionTokenResponse(Log log, Request request) {
		super(null, request);
		this.log = log;
	}

	@Override
	public String parse() throws SyncException, IOException {
		if (getResponse() == null || getResponse() == "") {
			log.log(TAG, "SessionTokenRequest returned empty response");
			return null;
		}
			
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(getResponse());
		
		if(HandleErrors(element)) return null;
		
		String token = ((JsonObject)element).getAsJsonPrimitive("token").getAsString();
		
		return token;
	}
}
