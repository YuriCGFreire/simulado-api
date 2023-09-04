package com.contabilidadesimulada.simuladoapi.api.model;

import com.contabilidadesimulada.simuladoapi.entities.Choice;
import com.contabilidadesimulada.simuladoapi.entities.Quiz;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class QuestionBody {

    @NotEmpty
    private String questionDescription;

    @NotNull(message = "The quiz name cannot be null.")
    private Quiz quiz;

    @NotEmpty
    private List<Choice> choices;

    public String getQuestionDescription() {
        return questionDescription;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Quiz getQuiz() {
        return quiz;
    }
}
