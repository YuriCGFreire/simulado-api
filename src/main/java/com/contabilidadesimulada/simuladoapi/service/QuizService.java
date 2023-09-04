package com.contabilidadesimulada.simuladoapi.service;

import com.contabilidadesimulada.simuladoapi.api.model.QuizBody;
import com.contabilidadesimulada.simuladoapi.entities.Question;
import com.contabilidadesimulada.simuladoapi.entities.Quiz;
import com.contabilidadesimulada.simuladoapi.entities.dao.QuizDAO;
import com.contabilidadesimulada.simuladoapi.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private QuizDAO quizDAO;

    public QuizService(QuizDAO quizDAO) {
        this.quizDAO = quizDAO;
    }

    public Quiz createQuiz(QuizBody quizBody) {
        if(quizDAO.findByQuizNameIgnoreCase(quizBody.getQuizName()).isPresent()){
            throw new BadRequestException("Quiz already Exists.");
        }
        Quiz quiz = new Quiz();
        quiz.setQuizName(quizBody.getQuizName());
        return quizDAO.save(quiz);
    }

    public Quiz findQuizByName(@RequestParam String quizName){
        Optional<Quiz> quiz = quizDAO.findByQuizNameIgnoreCase(quizName);
        if(!quiz.isPresent()){
            throw new BadRequestException("Quiz does not Exist.");
        }
        return quiz.get();
    }

}
