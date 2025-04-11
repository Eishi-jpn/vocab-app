package com.example.myapp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
	private String question;
	private String correctAnswer;
	private String answer;
	private Boolean judge;

	public Result(String question, String answer, String correctAnswer, Boolean judge){
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.answer = answer;
		this.judge = judge;
	}
	public String getQuestion(){
		return this.question;
	}
	public void setQuestion(String question){
		this.question = question;
	}
	public String getCorrectAnswer(){
		return this.correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer){
		this.correctAnswer = correctAnswer;
	}
	public String getAnswer(){
		return this.answer;
	}
	public void setAnswer(String answer){
		this.answer = answer;
	}
	public Boolean getJudge(){
		return this.judge;
	}
	public void setJudge(Boolean judge){
		this.judge = judge;
	}
}
