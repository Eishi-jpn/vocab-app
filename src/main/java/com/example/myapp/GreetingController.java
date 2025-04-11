package com.example.myapp;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class GreetingController {

    /*@GetMapping("/")
    public String hello() {
        return "top";
    }*/

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    @PostMapping("/greet")
	//@ResponseBody
    public String greet(@RequestParam String name) {
		if (name == null || name.trim().isEmpty()) {
            return "Please enter a name."; 
        }
		//return "dropdown";
        return "redirect:/dropdown.html";
    }

	@PostMapping("/list")
	public String showList(Model model){
		List<String> words = List.of("Apple", "Banana", "Cherry", "Date", "Elderberry");
		model.addAttribute("wordList", words);
		return "list";
	}

	@PostMapping("/test")
	public String postMethodName(@RequestParam String chapter, @RequestParam String direction, @RequestParam String size, Model model) {
		//TODO: process POST request
		model.addAttribute("chapter", chapter);
		model.addAttribute("direction", direction);
		model.addAttribute("size", size);

		return "resultpage";
	}
}
