package com.example.myapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class WordService {

    private List<EnglishWord> words;

    // コンストラクタ
	
    public WordService() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("Chapter1_verb.json");
            if (inputStream == null) {
                throw new RuntimeException("Chapter1_verb.json not found");
            }

            JsonWrapper wrapper = objectMapper.readValue(inputStream, JsonWrapper.class);
            this.words = wrapper.getList();  // "list" の中身を取得
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Chapter1_verb.json", e);
        }
    }
	/*public WordService(){
		EnglishWordDB ewDB = new EnglishWordDB("Chapter1_verb.json");
		EnglishWordList ewList = ewDB.getEWList();
		this.words = ewList.getList();
	}*/

    public List<EnglishWord> getAllWords() {
        return words;
    }

    public EnglishWord getRandomWord() {
        int index = (int) (Math.random() * words.size());
        return words.get(index);
    }
}
