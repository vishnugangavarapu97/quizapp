package com.example.quizapp.service;


import com.example.quizapp.Dao.QuestionDao;
import com.example.quizapp.Dao.QuizDao;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.Quiz;
import com.example.quizapp.model.QuizWraper;
import com.example.quizapp.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions=questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success",HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuizWraper>> getQuizById(int num) {

        Optional<Quiz> quiz=quizDao.findById(num);
        List<Question> questionfromDb=quiz.get().getQuestions();
        List<QuizWraper> questionForUser=new ArrayList<>();

        for (Question q:questionfromDb){
            QuizWraper qw=new QuizWraper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateQuiz(Integer id, List<Response> responses) {

        Quiz quiz =quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=1;
        for (Response response : responses) {
            String userAnswer = response.getResponses();
            String correctAnswer = questions.get(i).getRightAnswer();

            if (userAnswer != null && userAnswer.equals(correctAnswer)) {
                right++;
            }
            i++;
        }
        return  new ResponseEntity<>(right,HttpStatus.OK);
    }

}
