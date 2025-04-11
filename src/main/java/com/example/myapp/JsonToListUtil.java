package com.example.myapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToListUtil {
	public static List<EnglishWord> jsonToList(String jsonName){
		try (InputStream inputStream = JsonToListUtil.class.getClassLoader().getResourceAsStream(jsonName)){
			if(inputStream == null){
				throw new RuntimeException(jsonName + " not found");
			}
			ObjectMapper om = new ObjectMapper();
			JsonWrapper wrapper = om.readValue(inputStream, JsonWrapper.class);
			return wrapper.getList();
		} catch(IOException e){
			throw new RuntimeException("Failed to load " + jsonName, e);
		}
	}
}
