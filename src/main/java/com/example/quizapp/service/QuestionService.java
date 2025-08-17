package com.example.quizapp.service;


import com.example.quizapp.Dao.QuestionDao;
import com.example.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>>  getAllQuestions() {

        List<Question> result= questionDao.findAll();

        if(result.isEmpty()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    public ResponseEntity <List<Question>> getQuestionByCategory(String category) {
        System.out.println("called serive");
        List<Question> result= questionDao.findByCategoryIgnoreCase(category);

        if(result.isEmpty()){
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Success";
    }
}
