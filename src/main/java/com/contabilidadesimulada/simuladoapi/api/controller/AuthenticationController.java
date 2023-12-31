package com.contabilidadesimulada.simuladoapi.api.controller;


import com.contabilidadesimulada.simuladoapi.api.model.LoginResponse;
import com.contabilidadesimulada.simuladoapi.api.model.RegistrationBody;
import com.contabilidadesimulada.simuladoapi.service.UserService;
import com.contabilidadesimulada.simuladoapi.api.model.LoginBody;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser( @RequestBody @Valid RegistrationBody registrationBody) {
        return new ResponseEntity<>(userService.registerUser(registrationBody), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody @Valid LoginBody loginBody) {
        String jwt = userService.login(loginBody);
        LoginResponse response = new LoginResponse();
        response.setJwt(jwt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
