package com.domaindriven.toodledo;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class SessionTokenResponse extends Response<String> {
	
	private final static String TAG = SessionTokenResponse.class.getSimpleName();

	protected SessionTokenResponse(Request request) {
		super(request);
	}

	@Override
	String parse() {
		try {
			if (getResponse() == null || getResponse() == "") {
				Log.w(TAG, "SessionTokenRequest returned empty response");
				return null;
			}
	
			JSONObject json = new JSONObject(getResponse());
			String token = json.getString("token");
			return token;
	
		} catch (JSONException ex) {
			Log.e(TAG, ex.getMessage());
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
