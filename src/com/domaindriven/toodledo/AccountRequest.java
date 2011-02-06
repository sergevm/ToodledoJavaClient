package com.domaindriven.toodledo;


public class AccountRequest extends Request {
	final static String URL_TEMPLATE = "http://api.toodledo.com/2/account/get.php?key=%s";
	
	public AccountRequest(Session authentication) {
		super(authentication);
	}
	
	@Override 
	protected String getUrl() {
		return String.format(URL_TEMPLATE, getAuthenticationKey());
	}
}