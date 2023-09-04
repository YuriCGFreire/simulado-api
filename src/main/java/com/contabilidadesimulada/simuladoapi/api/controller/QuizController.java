package com.contabilidadesimulada.simuladoapi.api.controller;

import com.contabilidadesimulada.simuladoapi.api.model.QuizBody;
import com.contabilidadesimulada.simuladoapi.entities.Quiz;
import com.contabilidadesimulada.simuladoapi.exception.BadRequestException;
import com.contabilidadesimulada.simuladoapi.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity createQuiz(@RequestBody @Valid QuizBody quizBody) throws BadRequestException {
        return new ResponseEntity<>(quizService.createQuiz(quizBody), HttpStatus.CREATED);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<Quiz> findQuizByName(@RequestParam String quizName){
        return ResponseEntity.ok(quizService.findQuizByName(quizName));
    }
}
