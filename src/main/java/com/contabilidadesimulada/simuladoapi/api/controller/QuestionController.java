package com.contabilidadesimulada.simuladoapi.api.controller;


import com.contabilidadesimulada.simuladoapi.api.model.QuestionBody;
import com.contabilidadesimulada.simuladoapi.entities.Question;
import com.contabilidadesimulada.simuladoapi.exception.BadRequestException;
import com.contabilidadesimulada.simuladoapi.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody @Valid QuestionBody questionBody) throws BadRequestException {
        return new ResponseEntity<>(questionService.createQuestion(questionBody), HttpStatus.CREATED);
    }
}
