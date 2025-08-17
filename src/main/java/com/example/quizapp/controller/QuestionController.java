package com.example.quizapp.controller;


import com.example.quizapp.model.Question;
import com.example.quizapp.model.Response;
import com.example.quizapp.service.QuestionService;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    private QuizService quizService;

    @GetMapping("allQuestions")
    public ResponseEntity < List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity <List<Question>> getQuestionByCategory(@PathVariable String category){

        System.out.println("called controller");
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("addquestion")
    public String addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }


}
