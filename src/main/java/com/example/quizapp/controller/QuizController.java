package com.example.quizapp.controller;


import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizWraper;
import com.example.quizapp.model.Response;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Quiz Service!";
    }

    @PostMapping("create")
    public ResponseEntity<String> Createquiz(@RequestParam String category , @RequestParam int numQ, @RequestParam String title){

        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{num}")
    public ResponseEntity<List<QuizWraper>> getQuizById(@PathVariable int num){
        return quizService.getQuizById(num);
    }


    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> Responses){


        return quizService.calculateQuiz(id,Responses);
    }


}
