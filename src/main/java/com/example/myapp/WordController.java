package com.example.myapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @GetMapping("/random")
    public EnglishWord getRandomWord() {
        return wordService.getRandomWord();
    }

    @GetMapping("/all")
    public List<EnglishWord> getAllWords() {
        return wordService.getAllWords();
    }
}

