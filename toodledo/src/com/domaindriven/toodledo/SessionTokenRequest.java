package com.domaindriven.toodledo;

import java.security.NoSuchAlgorithmException;

public class SessionTokenRequest extends Request {

	private final static String URL_TEMPLATE = "http://api.toodledo.com/2/account/token.php?userid=%s;appid=%s;sig=%s";
	private final static String TAG = SessionTokenRequest.class.getSimpleName();
	private final com.domaindriven.toodledo.ToodledoSession.Log log;
	private final String userId;
	private final String appId;
	private final String appToken;

	public SessionTokenRequest(final String userId, final String appId, final String appToken,  ToodledoSession.Log log, RestClientFactory factory) {
		super(null, factory, RequestMethod.GET);
		this.userId = userId;
		this.log = log;		
		this.appId = appId;
		this.appToken = appToken;
	}
	
	@Override
	protected String getUrl() {
		return String.format(URL_TEMPLATE, userId, this.appId, calculateSignature());
	}
	
	private String calculateSignature() {
		try {
			String toDigest = userId + this.appToken;
			return MD5Helper.calculate(toDigest);
		} catch (NoSuchAlgorithmException e) {
			log.log(TAG, e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}
