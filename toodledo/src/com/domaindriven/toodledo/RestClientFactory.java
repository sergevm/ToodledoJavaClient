package com.domaindriven.toodledo;

public interface RestClientFactory {
	RestClient create(final String url);
}
