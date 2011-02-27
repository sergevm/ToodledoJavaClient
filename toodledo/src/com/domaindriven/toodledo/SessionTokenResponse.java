package com.domaindriven.toodledo;

import com.domaindriven.toodledo.ToodledoSession.Log;
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
	public String parse() {
		try {
			if (getResponse() == null || getResponse() == "") {
				log.log(TAG, "SessionTokenRequest returned empty response");
				return null;
			}
				
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject)parser.parse(getResponse());
			
			if(isError(object)) return null;
			
			String token = object.getAsJsonPrimitive("token").getAsString();
			
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
