package com.contabilidadesimulada.simuladoapi.service;

import com.contabilidadesimulada.simuladoapi.api.model.QuestionBody;
import com.contabilidadesimulada.simuladoapi.entities.Choice;
import com.contabilidadesimulada.simuladoapi.entities.Question;
import com.contabilidadesimulada.simuladoapi.entities.Quiz;
import com.contabilidadesimulada.simuladoapi.entities.dao.QuestionDAO;
import com.contabilidadesimulada.simuladoapi.entities.dao.QuizDAO;
import com.contabilidadesimulada.simuladoapi.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private QuestionDAO questionDAO;

    private QuizService quizService;

    public QuestionService(QuestionDAO questionDAO, QuizService quizService) {
        this.questionDAO = questionDAO; this.quizService = quizService;
    }

    public Question createQuestion(QuestionBody questionBody) {
        Question question = new Question();
        question.setQuestionDescription(questionBody.getQuestionDescription());
        Quiz quiz = quizService.findQuizByName(questionBody.getQuiz().getQuizName());
        question.setQuiz(quiz);
        if(!questionBody.getChoices().isEmpty()){
            List<Choice> choiceList = new ArrayList<>();
            for (Choice choiceItem: questionBody.getChoices()) {
                Choice choice = new Choice();
                choice.setChoiceDescription(choiceItem.getChoiceDescription());
                choice.setIsAnswer(choiceItem.getIsAnswer());
                choice.setQuestion(question);
                choiceList.add(choice);
            }
            question.setChoiceList(choiceList);
        }
        return questionDAO.save(question);
    }

}
