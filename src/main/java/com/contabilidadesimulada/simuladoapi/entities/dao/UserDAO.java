package com.contabilidadesimulada.simuladoapi.entities.dao;

import com.contabilidadesimulada.simuladoapi.entities.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserDAO extends ListCrudRepository<User, UUID> {
    Optional<User> findByUsernameIgnoreCase(String username);

    Optional<User> findByEmailIgnoreCase(String email);
}
