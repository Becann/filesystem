package com.cgutech.filesystem.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private static final Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
			.create();

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

	public static Gson getGson() {
		return gson;
	}

}
