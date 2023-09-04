package com.contabilidadesimulada.simuladoapi.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class QuizBody {

    @NotBlank
    @NotNull
    private String quizName;

    public String getQuizName() {
        return quizName;
    }

}
