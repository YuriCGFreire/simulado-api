package com.contabilidadesimulada.simuladoapi.service;

import com.contabilidadesimulada.simuladoapi.api.model.LoginBody;
import com.contabilidadesimulada.simuladoapi.api.model.RegistrationBody;
import com.contabilidadesimulada.simuladoapi.entities.User;
import com.contabilidadesimulada.simuladoapi.entities.dao.UserDAO;
import com.contabilidadesimulada.simuladoapi.exception.BadRequestException;
import com.contabilidadesimulada.simuladoapi.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class UserService {
    private UserDAO userDAO;

    private EncryptionService encryptionService;

    private JWTService jwtService;
    public UserService(UserDAO userDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.userDAO = userDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    public User registerUser(RegistrationBody registrationBody) throws BadRequestException {
        if(userDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() || userDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new BadRequestException("User already exists.");
        }
        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return userDAO.save(user);
    }

    @Transactional
    public String login(LoginBody loginBody) throws UnauthorizedException {
        Optional<User> opUser = userDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if(opUser.isPresent()){
            User user = opUser.get();
            if(encryptionService.veriFyPassword(loginBody.getPassword(), user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        throw new UnauthorizedException("Usuário ou senha inválidos.");
    }
}
