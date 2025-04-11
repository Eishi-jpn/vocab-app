package com.example.myapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnglishWord{
	private String english;
	private String japanese;
	private String example;
	/*public EnglishWord(){
		
	}
	public EnglishWord(String english, String japanese, String example){
		this.english = english;
		this.japanese = japanese;
		this.example = example;
	}*/
	public String getEnglish(){
		return this.english;
	}
	public void setEnglish(String english){
		this.english = english;
	}
	public String getJapanese(){
		return this.japanese;
	}
	public void setJapanese(String japanese){
		this.japanese = japanese;
	}
	public String getExample(){
		return this.example;
	}
	public void setExample(String example){
		this.example = example;
	}
}