package com.domaindriven.toodledo;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import android.util.Log;

public class ToodledoSession implements Session {
	private static final double A_LITTLE_LESS_THAN_4_HOURS = 1000 * 60 * 60 * 3.99;
	private static final String TAG = Session.class.getSimpleName();
	
	private static Calendar sessionTokenTime;
	private static String sessionToken;
	private static String key;
	private String user;
	
	public static Session create(final String user, final String password) {
		ToodledoSession session = new ToodledoSession(user);
		session.create(password);
		return session;
	}
		
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getUserId() {
		return user;
	}

	private ToodledoSession(final String userId) {
		this.user = userId;
	}

	private void create(final String password) {
		if (isExpired()) {
			Log.v(TAG, "Session token expired. Retrieving a new token ...");
	
			sessionTokenTime = Calendar.getInstance();
			sessionToken = requestSessionToken(getUserId());
			key = calculateKeyFrom(password, sessionToken);
	
			Log.v(TAG, "Session token requested and acquired.");
		}
	}

	@Override
	public boolean isExpired() {
		if (sessionTokenTime == null)
			return true;
	
		Calendar now = Calendar.getInstance();
		long sessionTokenAcquisitionAge = now.getTimeInMillis() - sessionTokenTime.getTimeInMillis();
		return (sessionTokenAcquisitionAge > A_LITTLE_LESS_THAN_4_HOURS);
	}

	private String requestSessionToken(final String userId) {

		SessionTokenRequest request = new SessionTokenRequest(userId);
		SessionTokenResponse response = new SessionTokenResponse(request);
		
		return response.parse();
	}
	
	private String calculateKeyFrom(final String password, final String sessionToken) {
		try {
			return MD5Helper.calculate(MD5Helper.calculate(password) + Constants.TOODLEDO_APPTOKEN + sessionToken);
		} catch (NoSuchAlgorithmException e) {
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
