package com.domaindriven.toodledo;

import java.security.NoSuchAlgorithmException;
import com.domaindriven.toodledo.HttpRestClient.RequestMethod;


public class SessionTokenRequest extends Request {

	private final static String URL_TEMPLATE = "http://api.toodledo.com/2/account/token.php?userid=%s;appid=%s;sig=%s";
	private final static String TAG = SessionTokenRequest.class.getSimpleName();
	private final String userId;
	private final com.domaindriven.toodledo.ToodledoSession.Log log;

	public SessionTokenRequest(final String userId, ToodledoSession.Log log) {
		super(null, RequestMethod.GET);
		this.userId = userId;
		this.log = log;
	}

	@Override
	protected String getUrl() {
		return String.format(URL_TEMPLATE, userId, Constants.TOODLEDO_APPID, calculateSignature());
	}
	
	private String calculateSignature() {
		try {
			String toDigest = userId + Constants.TOODLEDO_APPTOKEN;
			return MD5Helper.calculate(toDigest);
		} catch (NoSuchAlgorithmException e) {
			log.log(TAG, e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}
