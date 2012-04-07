package com.domaindriven.toodledo;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class ToodledoSession implements Session {
	
	public interface Log {
		void log(final String tag, final String message);
	}
	
	private static final double A_LITTLE_LESS_THAN_4_HOURS = 1000 * 60 * 60 * 3.99;
	private static final String TAG = Session.class.getSimpleName();
	
	private static Calendar sessionTokenTime;
	private static String sessionToken;
	private static String key;
	
	private String appId;
	private String appToken;
	
	private String user;
	
	private static Log log;
	private static RestClientFactory factory;
	
	public static Session create(final String user, final String password, final String apiKey, final String apiToken, final Log log, final RestClientFactory factory) {
		ToodledoSession.log = log;
		ToodledoSession.factory = factory;

		ToodledoSession session = new ToodledoSession(user, apiKey, apiToken);
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

	private ToodledoSession(final String userId, final String appId, final String appToken) {
		this.user = userId;
		this.appId = appId;
		this.appToken = appToken;
	}

	private void create(final String password) {
		if (isExpired()) {
			log.log(TAG, "Session token expired. Retrieving a new token ...");
	
			sessionTokenTime = Calendar.getInstance();
			sessionToken = requestSessionToken(getUserId());
			key = calculateKeyFrom(password, sessionToken);
	
			log.log(TAG, "Session token requested and acquired.");
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

		SessionTokenRequest request = new SessionTokenRequest(userId, this.appId, this.appToken, log, factory);
		SessionTokenResponse response = new SessionTokenResponse(log, request);
		
		return response.parse();
	}
	
	private String calculateKeyFrom(final String password, final String sessionToken) {
		try {
			return MD5Helper.calculate(MD5Helper.calculate(password) + this.appToken + sessionToken);
		} catch (NoSuchAlgorithmException e) {
			log.log(TAG, e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void Log(String tag, String logmessage) {
		log.log(tag, logmessage);
	}
}
