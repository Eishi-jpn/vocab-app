package com.example.myapp;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EnglishWordDB {
	private EnglishWordList ewList;
	public EnglishWordDB(String filename){
		String all = JsonUtils.readAll(Paths.get(filename));
		Jsonb jsonb = JsonbBuilder.create();
		this.ewList = jsonb.fromJson(all, EnglishWordList.class);
	}
	public EnglishWordList getEWList(){
		return this.ewList;
	}
	public void setEWList(EnglishWordList ewList){
		this.ewList = ewList;
	}
	public void update(String jsonName){
		Path path = Paths.get(jsonName);
		JsonbConfig jsonbConfig = new JsonbConfig();
		jsonbConfig.withFormatting(true);
		Jsonb jsonb = JsonbBuilder.create(jsonbConfig);
		JsonUtils.writeString(path, jsonb.toJson(this.ewList));
	}
}
