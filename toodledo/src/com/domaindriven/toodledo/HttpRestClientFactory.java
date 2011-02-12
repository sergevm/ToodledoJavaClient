package com.domaindriven.toodledo;

public class HttpRestClientFactory implements RestClientFactory {

	@Override
	public RestClient create(final String url) {
		return new HttpRestClient(url);
	}
}
