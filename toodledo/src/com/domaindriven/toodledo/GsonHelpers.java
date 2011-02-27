package com.domaindriven.toodledo;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class GsonHelpers {
	public static boolean parseBoolean(final JsonObject object, final String member) {
		
		JsonPrimitive primitive = object.getAsJsonPrimitive(member);
		if(primitive == null) return false;
		
		return primitive.isJsonNull() ? false : primitive.getAsShort() == 0 ? false : true;
	}
}
