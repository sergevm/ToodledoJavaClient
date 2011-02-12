package com.domaindriven.toodledo;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;


public class AccountRequest extends Request {
	public final static String URL_TEMPLATE = "http://api.toodledo.com/2/account/get.php?key=%s";
	
	public AccountRequest(Session session) {
		super(session, RequestMethod.GET);
	}
	
	public AccountRequest(Session session, RestClientFactory clientFactory) {
		super(session, clientFactory, RequestMethod.GET);
	}
	
	@Override 
	protected String getUrl() {
		return String.format(URL_TEMPLATE, getAuthenticationKey());
	}
}