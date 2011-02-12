package com.domaindriven.toodledo;

import com.domaindriven.toodledo.HttpRestClient.RequestMethod;

public interface RestClient {

	public abstract String getResponse();

	public abstract String getErrorMessage();

	public abstract int getResponseCode();

	public abstract void AddParam(String name, String value);

	public abstract void AddHeader(String name, String value);

	public abstract void Execute(RequestMethod method) throws Exception;

}