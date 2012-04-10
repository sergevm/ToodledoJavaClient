package com.domaindriven.toodledo;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public abstract class Response<T> {

	private String response;
	private Request request;
	protected Session session;

	protected Response(final Session session, final Request request) {
		this.request = request;
		this.session = session;
	}

	public abstract T parse() throws SyncException, IOException;

	protected String getResponse() throws SyncException, IOException {
		if (response == null) {
			response = request.execute();
		}

		return response;
	}

	public String getErrorMessage() {
		return request.getErrorMessage();
	}

	public int getResponseCode() {
		return request.getResponseCode();
	}

	public Session getSession() {
		return session;
	}

	protected boolean isError(JsonObject jsonObject) {
		return jsonObject.has("errorCode") && jsonObject.has("errorDesc");
	}
	
	protected boolean isError(JsonElement jsonElement) {
		if(!jsonElement.isJsonObject()) {
			return false;
		}
		
		JsonObject obj = (JsonObject)jsonElement;
		return isError(obj);
	}
	
	protected long getOptionalJsonAsLong(final JsonObject target, final String key) {
		
		if(target.has(key) == false) return 0;
		if(target.get(key).isJsonNull()) return 0;
		
		JsonPrimitive primitive = target.getAsJsonPrimitive(key);
		if(primitive != null) {
			return primitive.getAsLong();
		}
		
		return 0;
	}

	protected String getOptionalJsonAsString(final JsonObject target, final String key) {
		
		if(target.has(key) == false) return null;
		if(target.get(key).isJsonNull()) return null;
		
		JsonPrimitive primitive = target.getAsJsonPrimitive(key);
		
		if(primitive != null) {
			return primitive.getAsString();
		}
		
		return null;
	}

	protected boolean HandleErrors(JsonElement json) throws SyncException {
	
		if(isError(json)) {
			throwSyncException(json);
			return true;
		}
		
		return false;
	}

	private void throwSyncException(JsonElement json) throws SyncException {
		JsonObject obj = (JsonObject)json;
		String message = obj.getAsJsonPrimitive("errorDesc").getAsString();
		throw new SyncException(message);
	}
}
