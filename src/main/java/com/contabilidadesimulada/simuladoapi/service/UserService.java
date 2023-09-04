package com.contabilidadesimulada.simuladoapi.service;

import com.contabilidadesimulada.simuladoapi.api.model.RegistrationBody;
import com.contabilidadesimulada.simuladoapi.entities.User;
import com.contabilidadesimulada.simuladoapi.entities.dao.UserDAO;
import com.contabilidadesimulada.simuladoapi.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserDAO userDAO;

    private EncryptionService encryptionService;
    public UserService(UserDAO userDAO, EncryptionService encryptionService) {
        this.userDAO = userDAO;
        this.encryptionService = encryptionService;
    }

    public User registerUser(RegistrationBody registrationBody) {
        if(userDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() || userDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new BadRequestException("User already exists.");
        }
        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return userDAO.save(user);
    }
}
