package com.example.myapp;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;

//次にやること
//ドロップダウンリストでかくする
//ラジオボタンを電大のやつっぽく解答含めたボタンにする
//submit, topに戻るボタンをでかくする
//ページのレイアウトを決める
//result画面をもっと見やすく
//解いてない問題の入力を促す

@Controller
public class TopController {
	private List<EnglishWord> words = new ArrayList<EnglishWord>();

	@GetMapping("/")
	public String topPage() {
		return "redirect:top.html";
	}
	
	@PostMapping("/top")
	public String backTopPage() {
		return "redirect:top.html";
	}

	@PostMapping("/select")
	public String selectQuestion(@RequestParam String chapter, @RequestParam String part, @RequestParam String direction, @RequestParam String size, Model model){
		
		try{
			String jsonName = chapter + "_" + part + ".json";
			this.words = JsonToListUtil.jsonToList(jsonName);
			//List<EnglishWord> questions = SelectEWUtils.ramdomSelect(size, words);
			Map<EnglishWord, List<EnglishWord>> questions = SelectEWUtils.createQuestion(size, words);

			//model.addAttribute("allwords", words);
			//model.addAttribute("words", questions);
			model.addAttribute("words", questions);

			
			if(direction.equals("e-to-j")){
				return "EnglishTest";
			} else {
				return "JapaneseTest";
			}
		}catch (Exception e){
			model.addAttribute("error", "データの取得に失敗しました");
			return "error";
		}
	}

	//解答解説を入れる
	//modelに問題と正誤を入れて返却
	//正解率を計算してmodelに入れる
	@PostMapping("/engResult")
	public String resultEngQuestion(@RequestParam Map<String, String> qmap, Model model) {
		int size = 0;
		int correct = 0;
		List<Result> resultList = SelectEWUtils.engJudge(qmap, this.words);
		size = resultList.size();
		for(Result r : resultList){
			if(r.getJudge()){
				correct += 1;
			}
		}
		model.addAttribute("resultList", resultList);
		model.addAttribute("size", size);
		model.addAttribute("correct", correct);
		if(size == 0){
			model.addAttribute("rate", 0);
		}else{
			Double corRate = Math.floor(((double) correct/size) * 100);
			model.addAttribute("rate", corRate);
		}
		//System.out.println("size:" + size + " correct:" + correct);
		return "result";
	}
	
	@PostMapping("/japResult")
	public String resultJapQuestion(@RequestParam Map<String, String> qmap, Model model) {
		int size = 0;
		int correct = 0;
		List<Result> resultList = SelectEWUtils.japJudge(qmap, this.words);
		size = resultList.size();
		for(Result r : resultList){
			if(r.getJudge()){
				correct += 1;
			}
		}
		model.addAttribute("resultList", resultList);
		model.addAttribute("size", size);
		model.addAttribute("correct", correct);
		if(size == 0){
			model.addAttribute("rate", 0);
		}else{
			Double corRate = Math.floor(((double) correct/size) * 100);
			model.addAttribute("rate", corRate);
		}
		return "result";
	}
}
