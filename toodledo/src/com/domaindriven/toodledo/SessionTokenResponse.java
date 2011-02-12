package com.domaindriven.toodledo;

import org.json.JSONException;
import org.json.JSONObject;

import com.domaindriven.toodledo.ToodledoSession.Log;

public class SessionTokenResponse extends Response<String> {
	
	private final static String TAG = SessionTokenResponse.class.getSimpleName();
	private Log log;

	protected SessionTokenResponse(Log log, Request request) {
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
	
			JSONObject json = new JSONObject(getResponse());
			String token = json.getString("token");
			return token;
	
		} catch (JSONException ex) {
			log.log(TAG, ex.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
