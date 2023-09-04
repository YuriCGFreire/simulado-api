package com.contabilidadesimulada.simuladoapi.entities.dao;

import com.contabilidadesimulada.simuladoapi.entities.Question;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionDAO extends ListCrudRepository<Question, Long> {

}
