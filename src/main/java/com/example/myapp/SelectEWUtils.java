package com.example.myapp;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Random;

import java.util.Collections;

public class SelectEWUtils {
	
	public static List<EnglishWord> ramdomSelect(String size, List<EnglishWord> ewList){
		Random random = new Random();
		List<EnglishWord> list = new ArrayList<EnglishWord>();
		try{
			int s = Integer.parseInt(size);
			for(int i = 0; i < s; i++){
				list.add(ewList.get(random.nextInt(ewList.size())));
			}
			return list;
		}catch(NumberFormatException e){
			throw new RuntimeException("can't parse str to int ", e);
		}
	}

	//List<EnglishWord>はkeyの解答を含んだ長さ4の選択肢のリスト
	//MapはEnglishWord毎に解答の選択肢を持ったmap
	//List<Map>は問題のリスト
	public static Map<EnglishWord, List<EnglishWord>> createQuestion(String size, List<EnglishWord> ewList){
		Random random = new Random();
		//Collections.shuffle(ewList);
		Map<EnglishWord, List<EnglishWord>> questions = new HashMap<EnglishWord, List<EnglishWord>>();
		try{
			int s = Integer.parseInt(size);
			while(questions.size() < s){
			//for(int i = 0; i < s; i++){
				List<EnglishWord> choices = new ArrayList<EnglishWord>();
				EnglishWord ew = ewList.get(random.nextInt(ewList.size()));
				choices.add(ew);
				while(choices.size() < 4){
					EnglishWord choice = ewList.get(random.nextInt(ewList.size()));
					if(!(ew.equals(choice))){
						choices.add(choice);
					}
				}
				Collections.shuffle(choices);

				questions.put(ew, choices);
			}
			return questions;
		} catch (NumberFormatException e){
			throw new RuntimeException("can't parse str to int ", e);
		}
	}

	public static List<Result> engJudge(Map<String, String> qmap, List<EnglishWord> ewList){
		int flag = 1;
		//entry　key:英単語問題   value:日訳解答
		List<Result> resultList = new ArrayList<Result>();
		for(Entry<String, String> entry : qmap.entrySet()){
			Boolean judge = false;
			String english = entry.getKey();
			String ans = entry.getValue();
			String corAns = search(english, ewList, flag);
			if(ans.equals(corAns)){
				judge = true;
			}
			resultList.add(new Result(english, ans, corAns, judge));
			//System.out.println(english + " " + ans + " " + corAns + " " + judge);
		}
		return resultList;
	}
	
	public static List<Result> japJudge(Map<String, String> qmap, List<EnglishWord> ewList){
		int flag = 0;
		//entry　key:日本語問題   value:英単語解答
		List<Result> resultList = new ArrayList<Result>();
		for(Entry<String, String> entry : qmap.entrySet()){
			Boolean judge = false;
			String japanese = entry.getKey();
			String ans = entry.getValue();
			String corAns = search(japanese, ewList, flag);
			if(ans.equals(corAns)){
				judge = true;
			}
			resultList.add(new Result(japanese, ans, corAns, judge));
			//System.out.println(ans + " " + corAns + " " + judge);
		}
		return resultList;
	}
	

	public static String search(String question, List<EnglishWord> ewList, int flag){
		for(EnglishWord ew : ewList){
			if(flag==0){//日->英
				if(question.equals(ew.getJapanese())){
					return ew.getEnglish();
				}
			} else if(flag==1){//英->日
				if(question.equals(ew.getEnglish())){
					return ew.getJapanese();
				}
			} else {
				//error
			}
		}
		return null;
	}
}
