package com.example.myapp;

import java.util.List;
import java.util.ArrayList;
import jakarta.json.bind.annotation.JsonbProperty;
import java.util.Collections;

public class EnglishWordList {
	private String name;
	@JsonbProperty(value = "englishWordList")
	private List<EnglishWord> ewList = new ArrayList<EnglishWord>();
	public EnglishWordList(){

	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setList(List<EnglishWord> ewList){
		this.ewList = ewList;
	}
	public List<EnglishWord> getList(){
		return this.ewList;
	}
	public void shuffle(){
		Collections.shuffle(this.ewList);
	}
	public void add(EnglishWord ew){
		for(EnglishWord e : this.ewList){
			if(e.getEnglish().equals(ew.getEnglish())){
				System.out.println("add error");
				throw new IllegalArgumentException("the same English was registered :" + e.getEnglish());
			}
		}
		this.ewList.add(ew);
	}
	public EnglishWord searchEnglish(String english){
		for(EnglishWord ew : this.ewList){
			if(ew.getEnglish().equals(english)){
				return ew;
			}
		}
		return null;
	}
	public EnglishWord searchJapanese(String japanese){
		for(EnglishWord ew : this.ewList){
			if(ew.getJapanese().equals(japanese)){
				return ew;
			}
		}
		return null;
	}
	public EnglishWord get(int index){
		return this.ewList.get(index);
	}
	public EnglishWord remove(int index){
		return this.ewList.remove(index);
	}
	public int size(){
		return this.ewList.size();
	}
}
