package com.contabilidadesimulada.simuladoapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Long choiceid;

    @Column(name = "choice_description", nullable = false)
    private String choiceDescription;

    @Column(name = "is_answer")
    private Boolean isAnswer = false;

    @Column(name = "is_answered")
    private Boolean isAnswered = false;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    @JsonIgnoreProperties("choices")
    private Question question;


}
