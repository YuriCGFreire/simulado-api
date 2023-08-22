package com.contabilidadesimulada.simuladoapi.service;

import com.contabilidadesimulada.simuladoapi.api.model.RegistrationBody;
import com.contabilidadesimulada.simuladoapi.entities.User;
import com.contabilidadesimulada.simuladoapi.entities.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerUser(RegistrationBody registrationBody) throws Exception{
        if(userDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() || userDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new Exception();
        }
        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(registrationBody.getPassword());
        return userDAO.save(user);
    }
}
